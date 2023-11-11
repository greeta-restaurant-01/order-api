### AWS Startup Template For Spring Boot Developers
#### Welcome to AWS Full-Stack Developer Template: Swagger UI + Spring Boot + Terraform + Kubernetes + Keycloak Oauth2 Authorization Server + Github Actions + Local Docker Build and Start Environment + Integration Tests with TestContainers + Spring Cloud Gateway + Spring Cloud Stream + Dispatcher Pattern + AWS SSL Certificate + External DNS + AWS Load Balancer Controller + Spring Cloud Kubernetes + Grafana Observability Stack

#### Local Docker Environment Setup:

```
sh docker-start.sh
```

- this script will build docker images and start environment with your code changes

- Warning! Make sure that all kafka containers are successful are started successfully!
- Check containers in the following order:
-- `Kafka Server (cp-server)`: if container has errors or stopped responding (check the logs), remove container (docker stop, docker rm ) and run `docker-compose up -d` again
-- `Init Kafka (cp-kafka)`: container should finish creation of kafka topics successfully and then stop. If container is not responding or has errors, remove container (docker stop, docker rm ) and run `docker-compose up -d` again
-- `Schema Registry (cp-schema-registry)`: if container has errors or stopped responding (check the logs), remove container (docker stop, docker rm ) and run `docker-compose up -d` again
-- If everything is cussessful, containers `cp-server` and `cp-schema-registry` should be running without errors and container `cp-kafka` should finish its job and exit without errors.

- open `localhost:9000` in your Browser and switch between `Order` and `Catalog` Microservices

- Warning! If Swagger UI fails to load on the first try, please, refresh the page!

- click "Authorize" and use admin/admin or user/user for credential (clientId should be `book-app`)

- try to create Order with existing book isbn and make sure that status is ACCEPTED

- load the orders and make sure that your created order has status DISPATCHED (it means that dispatcher service has received the message and changed the status of the order)

- See this README file for AWS Infrastructure Setup: **https://github.com/greeta-book-01/book-infra**


### Remote Debugging

![Configuration to debug a containerized Java application from IntelliJ IDEA](documentation/06-14.png)

- if you want to debug your AWS kubernetes services remotely, use port forwarding:

```
kubectl port-forward 8002:8002
```
