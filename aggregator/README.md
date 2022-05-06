# aggregator

## build application

```shell
az acr login --name ${ACR_NAME}

export AG_VERSION="0.1.0-SNAPSHOT"

./gradlew bootBuildImage
docker tag aggregator:${AG_VERSION} ${ACR_NAME}.azurecr.io/dapr-sample/aggregator:${AG_VERSION}
docker push ${ACR_NAME}.azurecr.io/dapr-sample/aggregator:${AG_VERSION}

```

