    package com.dev.condominio.service;

    import com.dev.condominio.domain.model.Cond;
    import com.dev.condominio.domain.model.User;
    import com.dev.condominio.domain.security.permission.Role;
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
    import java.util.stream.Collectors;

    @Service
    public class UserService {

        //userToResponse method in order to transform a User into an UserResponse
        public UserResponse userToResponse(User user) {
            Set<String> roleNames = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet());

            Set<String> permissions = user.getRoles().stream()
                    .flatMap(role -> role.getPermissions().stream())
                    .map(Enum::name)
                    .collect(Collectors.toSet());


            return new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getCpf(),
                    user.getEmail(),
                    user.getApt(),
                    user.getBloco(),
                    roleNames,
                    user.getCond().getId(),
                    permissions
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

        public Set<Role> getRolesFromRequest(UserRequest request) {
            if (request.getRoleIds() == null || request.getRoleIds().isEmpty()) {
                throw new IllegalArgumentException("Pelo menos uma role deve ser informada.");
            }
            List<Role> foundRoles = roleRepository.findAllById(request.getRoleIds());
            if (foundRoles.size() != request.getRoleIds().size()) {
                throw new EntityNotFoundException("Uma ou mais roles não foram encontradas.");
            }
            return new HashSet<>(foundRoles);
        }



        //find all users
        public List<UserResponse> findAll() {
            return userRepository.findAll().stream().map(this::userToResponse).toList();
        }


        //find all users from a cond
        public List<UserResponse> findAllByCondId(UUID condId) {
            List<User> users = userRepository.findAllByCondId(condId);
            return users.stream()
                    .map(this::userToResponse)
                    .collect(Collectors.toList());
        }

        //find user by id
        public User findById(UUID id) {
            return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        }


        //validating the fields before creating and updating
        private void validateUniqueFields(UserRequest request, UUID userId) {
            userRepository.findByCpf(request.getCpf()).ifPresent(existingUser -> {
                if (!existingUser.getId().equals(userId)) {
                    throw new IllegalArgumentException("CPF já cadastrado.");
                }
            });

            userRepository.findByEmail(request.getEmail()).ifPresent(existingUser -> {
                if (!existingUser.getId().equals(userId)) {
                    throw new IllegalArgumentException("Email já cadastrado.");
                }
            });

            userRepository.findByName(request.getName()).ifPresent(existingUser -> {
                if (!existingUser.getId().equals(userId)) {
                    throw new IllegalArgumentException("Nome já cadastrado.");
                }
            });
        }




        //create user using UserRequest and return a UserResponse using method userToResponse
        public UserResponse createUser(UserRequest request) {

            validateUniqueFields(request, null);

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

            validateUniqueFields(request, id);

            // buscar condomínio
            Cond cond = condRepository.findById(request.getCondId()).orElseThrow(() -> new EntityNotFoundException("Condomínio não encontrado."));

            // buscar roles no banco usando os id recebidos
            Set<Role> roles = new HashSet<>(roleRepository.findAllById(request.getRoleIds()));

            // atualizar os dados do usuário
            user.setName(request.getName());
            user.setCpf(request.getCpf());
            user.setEmail(request.getEmail());
            user.setBloco(request.getBloco());
            user.setApt(request.getApt());
            user.setCond(cond);
            user.setRoles(roles);

            User updated = userRepository.save(user);

            return userToResponse(updated);
        }



        // update the permissions from UserType
        @Transactional
        public User updateRoles(UUID userId, Set<UUID> roleIds) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

            List<Role> foundRoles = roleRepository.findAllById(roleIds);
            if (foundRoles.size() != roleIds.size()) {
                throw new EntityNotFoundException("Uma ou mais roles não foram encontradas.");
            }

            user.setRoles(new HashSet<>(foundRoles));
            return userRepository.save(user);
        }



    }
