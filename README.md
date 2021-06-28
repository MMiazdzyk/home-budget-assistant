# Home budget asistant
A prototype application that facilitates planning and maintaining a home budget.
It has four predefined registers with name and balance: "Wallet", "Savings", "Insurance policy", "Food expences".
Currently, you can charge selected register with provided amount, transfer funds between registers and check the balance of all registers in the application.

## Install
Download repository and unpack it to folder. Open comand line, navigate to application folder and run command:
```
mvn clean install
```
This command should generate `target` folder which should contains .jar file
```
home-budget-assistant-0.0.1-SNAPSHOT.jar
```
In command line navigate to `target` folder and run command:
```
java -jar home-budget-assistant-0.0.1-SNAPSHOT.jar
```

# REST API
The REST API to make an operations in application.

## Get balance for all registers

### Request

`GET /registers`
    
    curl --location
         --request GET 'http://localhost:8080/registers'

### Response
    HTTP/1.1 200 OK
    [{
        "id": 1,
        "name": "Wallet",
        "balance": 1275.00
    },
    {
        "id": 2,
        "name": "Savings",
        "balance": 5600.00
    },
    {
        "id": 3,
        "name": "Insurance_policy",
        "balance": 500.00
    },
    {
        "id": 4,
        "name": "Food_expenses",
        "balance": 1500.00
    }]
    
## Charge selected register with provided amount

### Request

`PUT /registers/{id}/charge`
    
    curl --location
         --request PUT 'http://localhost:8080/registers/1/charge' \
         --header 'Content-Type: application/json' \
         --data-raw '{
                        "registerName":"Wallet",
                        "value":"125.00"
                      }'

### Response
      HTTP/1.1 200 OK
      []
      
## Transfer funds between registers

### Request

`POST /transfer`
    
    curl --location
         --request POST 'http://localhost:8080/transfer' \
         --header 'Content-Type: application/json' \
         --data-raw '{
                        "fromRegisterName":"Wallet",
                        "toRegisterName":"Savings",
                        "value":"100.00"
                     }'

### Response
      HTTP/1.1 201 Created
      []

