modelagem de dados



Cond
id pk uuid STRING
name unique not_null STRING
imgUrl not_null STRING
accesNumber INT
user User[]
adm Adm[]
place Place[]
payment Payment[]
reserve Reserve[]

User
id pk uuid STRING
name unique not_null STRING
bloco? SHORT
cpf STRING
apt not_null INT 
reserve Reserve[]
type STRING (sindico ou morador)
condId STRING


Adm 
id pk uuid STRING
name unique not_null STRING
cpf STRING

Place
id pk uuid STRING
name unique not_null STRING
type STRING (payment ou free)
reserve Reserve[]

Payment
id pk uuid STRING
userId STRING
amount INT
status STRING (done ou topay)
receiptUrl STRING
reserve Reserve[]

Reserve
id pk uuid STRING
date DATE
placeId STRING
userId STRING
paymentId STRING




