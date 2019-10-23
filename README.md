## Reto Backend + Reto Pipelines

Backend : Spring Boot 2.2 + MySQL
Desplegado en : AWS EC2 t2.micro 

Endpoints:
- GET http://ec2-18-188-74-72.us-east-2.compute.amazonaws.com:8080/listclientes
- GET http://ec2-18-188-74-72.us-east-2.compute.amazonaws.com:8080/kpideclientes
- POST http://ec2-18-188-74-72.us-east-2.compute.amazonaws.com:8080/creacliente

Devops : 
Pipeline en Jenkins desplegado usando Docker en AWS EC2
Detalle en Jenkinsfile

CI Server : http://3.15.225.10:8080/