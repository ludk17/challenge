### Iniciar App
```
docker-compose up --build
```

### Levantar Front App
```
http://localhost:4200/login
[AUTH]
username: challenge_api
password: password
```


### Tipo de campo Front App
```
http://localhost:4200/exchange-rate
```


### Obtener token de backend
```
[POST] 
http://localhost:8080/authenticate

[PAYLOAD]
{
    "username": "challenge_api",
    "password": "password"
}
```


### Listar todos los tipos de cambios
```
[GET] 
http://localhost:8080
[HEADERS]
Authorization: Bearer <token generado en la autenticación>
```

### Crear o actualizar nuevo tipo de cambio
```
[POST] 
http://localhost:8080
[HEADERS]
Authorization: Bearer <token generado en la autenticación>
[BODY]
{
    "fromCurrency": "USD",
    "toCurrency": "PEN",
    "rate": 3.90
}
```


### Calcular monto
```
[POST] 
http://localhost:8080/calculate
[HEADERS]
Authorization: Bearer <token generado en la autenticación>
[BODY]
{
    "fromCurrency": "USD",
    "toCurrency": "PEN",
    "amount": 1000
}
```