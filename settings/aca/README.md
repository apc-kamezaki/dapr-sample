# Azure Container apps

## Get ACR credential



```shell
# ACAからユーザー/パスワードでアクセスできるようにする
az acr update -n ${ACR_NAME} --admin-enabled true
```

## Deploy azure container apps

```shell
az extension add --name containerapp --upgrade
az provider register --namespace Microsoft.App

az deployment group create --resource-group ${RESOURCE_GROUP} \
    -f deploy.bicep

# OR

az deployment group create --resource-group ${RESOURCE_GROUP} \
    -f deploy-workspace.bicep

az deployment group create --resource-group ${RESOURCE_GROUP} \
    -f deploy-app-insights.bicep

az deployment group create --resource-group ${RESOURCE_GROUP} \
    -f deploy-aca.bicep

```

## Setup Dapr components

```shell
az deployment group create --resource-group ${RESOURCE_GROUP} \
    --parameters serviceBusNamespace=${AZURE_SERVICEBUS_NAMESPACE} \
    -f components/sample-pubsub.bicep
```

## Setup Dapr Application

```shell
# timefeed
az deployment group create --resource-group ${RESOURCE_GROUP} \
    --parameters acrName=${ACR_NAME} \
    -f apps/timefeed.bicep
# aggregator
az deployment group create --resource-group ${RESOURCE_GROUP} \
    --parameters acrName=${ACR_NAME} \
    -f apps/aggregator.bicep
# event-consumer
az deployment group create --resource-group ${RESOURCE_GROUP} \
    --parameters acrName=${ACR_NAME} \
    -f apps/event-consumer.bicep

```