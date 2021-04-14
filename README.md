# WEbApi


## \login
>>>
Method: REQUEST
>>>
### Request
```json
{
"username":"admin",
"password":"123456"
}
```

### Response
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDEyMjM3NCwiaWF0IjoxNjE0MDg2Mzc0fQ.plVunJlZfICJkbOI_mOJAM6IfbwNZGc-kH8xG0YQZnY"
}
```

## \payment

>>>
Method: GET
>>>
### Request
```header
Header:
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxNDExNTIyOSwiaWF0IjoxNjE0MDc5MjI5fQ.T0oSkMRhGJy7PC6qs3gfBobkz1Uq4n0pQYyqvlK96pk
```

### Response
```json
{
    "info": "На вашем счету сумма меньше требуемой для проведения транзакции 0.30 USD. Пополните счет для проведения операций"
}
```
