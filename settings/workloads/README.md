# Apply everything

```shell
export APPINSIGHTS_JAVA_AGENT_VER=3.2.10
export BASE64_INSTRUMENTATION_STRING=`echo -n ${AI_INSTRUMENTATION_STRING} | base64`
cat instrumentation-string.yaml | envsubst | kubectl apply -f -
cat helloworld.yaml | envsubst | kubectl apply -f -
cat timefeed.yaml | envsubst | kubectl apply -f -
cat aggregator.yaml | envsubst | kubectl apply -f -
cat event-consumer.yaml | envsubst | kubectl apply -f -
```