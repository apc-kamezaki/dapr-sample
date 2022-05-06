# Event consumer


# build & bake

ACRへの公開を実行する前に、ACRへのログインを行ってください。

```shell
az acr login -n ${ACR_NAME}
```

imageをビルドしたのち、ACRに公開します。

```shell
make build
make push
```
