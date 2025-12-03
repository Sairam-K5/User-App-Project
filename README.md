 This is a Java SpringBoot Application

# Write a dockerfile for creating  docker image
     docker build -t myapp .

# To create and run a container
     docker run -d -p 8080:80 --name myconatiner myapp

# Write yaml file and deploy into kubernetes
     kubectl apply -f deployment.yaml 
     kubectl apply -f service.yaml
    
# To check pods info
     Kubectl get pods
    
# To get service 
     minikube service backend-service
