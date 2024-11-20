#!/usr/bin/env bash

docker build --no-cache -t agent-integrador-api:latest .

# 1. Autenticar no ECR
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 831926614292.dkr.ecr.us-east-1.amazonaws.com

# 2. Taguear a imagem
docker tag agent-integrador-api:latest 831926614292.dkr.ecr.us-east-1.amazonaws.com/workdayzup/agent-integrador-api:latest

# 3. Enviar a imagem para o ECR
docker push 831926614292.dkr.ecr.us-east-1.amazonaws.com/workdayzup/agent-integrador-api:latest

#docker system prune -f
