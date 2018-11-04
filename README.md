# Cep Api

Projeto que recupera informações do endereço a partir de um cep.

# Exemplo

```
➜  ~ http :8080/zip-codes/14802510
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Date: Sun, 04 Nov 2018 17:14:13 GMT
Transfer-Encoding: chunked

{
    "address": "Rua Domingos Barbieri", 
    "city": "Araraquara", 
    "federationUnity": "SP", 
    "neighborhood": "Vila Harmonia", 
    "zipCode": "14802-510"
}
```

