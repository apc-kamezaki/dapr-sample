AGENT_VER := 3.2.11

.phony:all
all: build aggregator timefeed

.PHONY: build
build:
	curl -L https://github.com/microsoft/ApplicationInsights-Java/releases/download/$(AGENT_VER)/applicationinsights-agent-$(AGENT_VER).jar -o applicationinsights-agent.jar

AGGREGATOR_VER := 0.1.0-SNAPSHOT
REGISTRY := $(ACR_NAME).azurecr.io/dapr-sample
.phony: aggregator
aggregator:
	docker build  . \
		--build-arg REGISTRY=$(REGISTRY) \
		--build-arg APP_NAME=aggregator \
		--build-arg APP_IMAGE_VER=$(AGGREGATOR_VER) \
		-t aggregator-javaagent:$(AGGREGATOR_VER) \
		-t $(REGISTRY)/aggregator-javaagent:${AGGREGATOR_VER}	
	docker push $(REGISTRY)/aggregator-javaagent:${AGGREGATOR_VER}

TIMEFEED_VER := 0.0.1-SNAPSHOT
REGISTRY := $(ACR_NAME).azurecr.io/dapr-sample
.phony: timefeed
timefeed:
	docker build  . \
		--build-arg REGISTRY=$(REGISTRY) \
		--build-arg APP_NAME=timefeed \
		--build-arg APP_IMAGE_VER=$(TIMEFEED_VER) \
		-t timefeed-javaagent:$(TIMEFEED_VER) \
		-t $(REGISTRY)/timefeed-javaagent:${TIMEFEED_VER}
	docker push $(REGISTRY)/timefeed-javaagent:${TIMEFEED_VER}

