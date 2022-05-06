# Dapr

## Install

```shell
dapr init -k
# install確認
kubectl get pods --namespace dapr-system
```

by helm

```
helm repo add dapr https://dapr.github.io/helm-charts/
helm repo update
helm upgrade --install dapr dapr/dapr --namespace dapr-system --values dapr-config.yaml --wait
```

## Distributed Tracing

[手順 Using OpenTelemetry Collector to collect traces to send to AppInsights](https://docs.dapr.io/operations/monitoring/tracing/open-telemetry-collector-appinsights/)

### Application Insights作成

### Collectorのデプロイ

ここではcollectorのdeploy先 namespaceを `dapr-system` 下とする。 

準備
- 手順書にある open-telemetry-collector-appinsights.yaml と collector-config.yaml をダウンロード。
- ２つのYAMLのnamespaceを `dapr-system` に変更する。
- open-telemetry-collector-appinsights.yaml中の `INSTRUMENTATION-KEY` を `${AI_INSTRUMENTATION_KEY}` に変更。
- collector-config.yaml と同じ内容で `web` namespace 用のものを追加する。

実行

```shell
cat open-telemetry-collector-appinsights.yaml | envsubst | kubectl apply -f -
kubectl apply -f collector-config.yaml
```

## Metrics

[手順 How-To: Set up Azure Monitor to search logs and collect metrics](https://docs.dapr.io/operations/monitoring/metrics/azure-monitor/)

```
kubectl apply -f azm-config-map.yaml
```
