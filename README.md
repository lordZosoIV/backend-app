##  TEST TASK

    This project is a collection of microservices that work together to provide
    a complete e-commerce solution. The microservices are built using Spring Boot
    and communicate with each other using RabbitMQ. The microservices are deployed
    using Docker containers.

## Microservices

    The following microservices are included in this project:

    Authorization Service: This microservice is responsible for handling user
    authentication and authorization. It runs on port 8080 and provides a Swagger
    UI for testing and exploring the API.

    Order Service: This microservice is responsible for managing customer orders.
    It runs on port 8081 and communicates with the Authorization Service to ensure
    that only authorized users can place orders.

    Notification Service: This microservice is responsible for sending notifications
    to customers about their orders. It runs on port 8082 and communicates with the
    Order Service to get information about orders that need to be notified.

    Product Service: This microservice is responsible for managing products in
    the e-commerce store. It runs on port 8083 and provides APIs for creating,
    updating, and deleting products.

    Package and Delivery Service: This microservice is responsible for managing
    the packaging and delivery of orders. It runs on port 8084 and communicates with
    the Order Service to get information about orders that need to be packaged and delivered.


## Running the Microservices


    Running the Microservices
    To run the microservices, you need to have Docker installed on your system.
    Once Docker is installed, you can run the following command to start all
    the microservices: 𝙙𝙤𝙘𝙠𝙚𝙧-𝙘𝙤𝙢𝙥𝙤𝙨𝙚 𝙪𝙥

## SWAGGER URLS

    Authorization Service: http://localhost:8080/swagger-ui/index.html
    Order Service: http://localhost:8081/swagger-ui/index.html
    Notification Service: http://localhost:8082/swagger-ui/index.html
    Product Service: http://localhost:8083/swagger-ui/index.html
    Package and Delivery Service: http://localhost:8084/swagger-ui/index.html