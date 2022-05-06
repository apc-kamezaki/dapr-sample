# timefeed

## build application

```shell
az acr login --name ${ACR_NAME}

export TF_VERSION="0.0.1-SNAPSHOT"

./gradlew bootBuildImage
docker tag timefeed:${TF_VERSION} ${ACR_NAME}.azurecr.io/dapr-sample/timefeed:${TF_VERSION}
docker push ${ACR_NAME}.azurecr.io/dapr-sample/timefeed:${TF_VERSION}

```