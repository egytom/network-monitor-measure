# Smartdatalake-re épített microservice szolgáltatásokból álló hálózati monitorozó rendszer

## Upload into Kubernetes cluster

- copy ./kubernetes files to the host which running the Kubernetes cluster
- cd to the directory where the previously copied files are
- microk8s kubectl apply -f discovery-service.yaml
- microk8s kubectl apply -f discovery-deployment.yaml
- microk8s kubectl apply -f gateway-service.yaml
- microk8s kubectl apply -f gateway-deployment.yaml
- microk8s kubectl apply -f configuratordb-service.yaml
- microk8s kubectl apply -f configuratordb-deployment.yaml
- microk8s kubectl apply -f configurator-deployment.yaml
- microk8s kubectl apply -f monitor-deployment.yaml
- test it
