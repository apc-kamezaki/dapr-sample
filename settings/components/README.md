# Dapr components

## setup Azure Service Bus

```shell
export PRINCIPAL_APPLICATION_ID=`az aks show --name $CLUSTER_NAME --resource-group $RESOURCE_GROUP | jq -r '.identityProfile.kubeletidentity.clientId'`

cat sample-pubsub.yaml | envsubst | kubectl apply -f -

```