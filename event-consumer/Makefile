APP_VER := 1.1.0
APP_NAME := event-consumer

.PHONY: build
build:
	docker build . -t $(APP_NAME) \
		-t $(APP_NAME):$(APP_VER) \
		-t $(ACR_NAME).azurecr.io/dapr-sample/$(APP_NAME):latest \
		-t $(ACR_NAME).azurecr.io/dapr-sample/$(APP_NAME):$(APP_VER)

.PHONY: push
push:
	docker push $(ACR_NAME).azurecr.io/dapr-sample/$(APP_NAME):latest
	docker push $(ACR_NAME).azurecr.io/dapr-sample/$(APP_NAME):$(APP_VER)
