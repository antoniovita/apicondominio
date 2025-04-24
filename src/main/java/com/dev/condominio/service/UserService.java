    package com.dev.condominio.service;

    import com.dev.condominio.domain.model.Cond;
    import com.dev.condominio.domain.model.User;
    import com.dev.condominio.domain.security.Permission;
    import com.dev.condominio.domain.security.Role;
    import com.dev.condominio.dto.user.UserRequest;
    import com.dev.condominio.dto.user.UserResponse;
    import com.dev.condominio.repository.CondRepository;
    import com.dev.condominio.repository.RoleRepository;
    import com.dev.condominio.repository.UserRepository;
    import jakarta.persistence.EntityNotFoundException;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;
    import java.util.UUID;

    @Service
    public class UserService {

        //userToResponse method in order to transform a User into an UserResponse
        private UserResponse userToResponse(User user) {
            return new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getCpf(),
                    user.getEmail(),
                    user.getApt(),
                    user.getBloco(),
                    user.getRoles(),
                    user.getCond().getId(),
            );
        }


        // define the repositories needed to use JPA methods in order to consume data from the database
        private final UserRepository userRepository;
        private final CondRepository condRepository;
        private final RoleRepository roleRepository;
        public UserService(UserRepository userRepository, CondRepository condRepository, RoleRepository roleRepository) {
            this.userRepository = userRepository;
            this.condRepository = condRepository;
            this.roleRepository = roleRepository;
        }

        private Set<Role> getRolesFromRequest (UserRequest request) {
            Set<Role> roles =   new HashSet<>();
            if (request.getRoles() != null ) {
                for ( String roleName: request.getRoles()) {
                    Role role = roleRepository.findByName(roleName).orElseThrow(() -> new EntityNotFoundException("Role não encontrada: " + roleName));
                    roles.add(role);
                }
            }
            return roles;
        }


        //find all users
        public List<UserResponse> findAll() {
            return userRepository.findAll().stream().map(this::userToResponse).toList();
        }


        //find user by id
        public User findById(UUID id) {
            return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        }


        //create user using UserRequest and return a UserResponse using method userToResponse
        public UserResponse createUser(UserRequest request) {
            if(userRepository.existsByCpf(request.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado.");
            }

            if(userRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Email já cadastrado.");
            }

            if(userRepository.existsByName(request.getName())) {
                throw new IllegalArgumentException("Nome já cadastrado.");
            }

            // get cond by id
            Cond cond = condRepository.findById(request.getCondId()).orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado."));

            // get default role or specific roles from request
            Set<Role> roles = getRolesFromRequest(request);


            User user = new User();

            user.setName(request.getName());
            user.setCpf(request.getCpf());
            user.setEmail(request.getEmail());
            user.setBloco(request.getBloco());
            user.setApt(request.getApt());
            user.setRoles(roles);
            user.setCond(cond);

            User saved =  userRepository.save(user);
            return userToResponse(saved);
        }


        //delete user by id
        public void deleteUser(UUID id) {
            if (!userRepository.existsById(id)) {
                throw new EntityNotFoundException("Usuário não encontrado.");
            }
            userRepository.deleteById(id);
        }

        //update user and convert it to UserResponse
        public UserResponse updateUser(UUID id, UserRequest request) {

            User user = findById(id);

            Cond cond = condRepository.findById(request.getCondId()).orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado."));

            Set<Role> roles = getRolesFromRequest(request);


            user.setName(request.getName());
            user.setCpf(request.getCpf());
            user.setEmail(request.getEmail());
            user.setBloco(request.getBloco());
            user.setApt(request.getApt());
            user.setRoles(request.getRoles());
            user.setCond(cond);

            User updated = userRepository.save(user);

            return userToResponse(updated);

        }


        // update the permissions from UserType
        @Transactional
        public User updateRoles(UUID userId, Set<String> newRoleNames) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

            Set<Role> roles = new HashSet<>();
            for (String roleName : newRoleNames) {
                Role role = roleRepository.findByName(roleName)
                        .orElseThrow(() -> new EntityNotFoundException("Role não encontrada: " + roleName));
                roles.add(role);
            }

            user.setRoles(roles);

            return userRepository.save(user);
        }


    }
