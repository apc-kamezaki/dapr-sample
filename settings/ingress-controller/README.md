# ingress controller

## install

```shell
cd controller

helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
# without Ddapr
helm upgrade --install ingress-nginx ingress-nginx/ingress-nginx \
    --create-namespace --namespace ingress-system
# with Ddapr
helm upgrade --install ingress-nginx ingress-nginx/ingress-nginx \
    --create-namespace --namespace ingress-system \
    --values config.yaml
```
