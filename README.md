# agente-integrador-api


#!/bin/bash
# Instalar Docker se ainda não estiver instalado
yum update -y
amazon-linux-extras install docker -y
service docker start

# Fazer login no ECR
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin /
831926614292.dkr.ecr.us-east-1.amazonaws.com/workdayzup/filtro-alura-api

# Executar o container
docker run -p9090:9090 831926614292.dkr.ecr.us-east-1.amazonaws.com/workdayzup/agent-integrador-api:latest