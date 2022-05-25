@description('Application name')
param appName string = 'dapr-sample'
@description('location for these resources')
param location string = resourceGroup().location

@description('Name for Azure container apps environment')
param environmentName string = appName

@description('container registry name')
param acrName string

@description('Application insights name')
param insightsName string = '${appName}-${uniqueString('ai', resourceGroup().id)}'

resource acr 'Microsoft.ContainerRegistry/registries@2021-12-01-preview' existing = {
  name: acrName
}

resource environment 'Microsoft.App/managedEnvironments@2022-03-01' existing = {
  name: environmentName
}

resource queryAi 'Microsoft.Insights/components@2020-02-02-preview' existing = {
  name: insightsName
}

resource aggregator 'Microsoft.App/containerApps@2022-03-01' = {
  name: 'aggregator'
  location: location
  properties: {
    managedEnvironmentId: environment.id
    configuration: {
      secrets: [
        {
          name: 'container-regsitry-password'
          value: acr.listCredentials().passwords[0].value
        }
      ]
      registries: [
        {
          server: acr.properties.loginServer
          username: acr.name
          passwordSecretRef: 'container-regsitry-password'
        }
      ]
      ingress: {
        external: true
        targetPort: 8080
      }
      dapr: {
        enabled: true
        appId: 'aggregator'
        appProtocol: 'http'
        appPort: 8080
      }
    }
    template: {
      containers: [
        {
          image: '${acrName}.azurecr.io/dapr-sample/aggregator-javaagent:0.1.0-SNAPSHOT'
          name: 'aggregator'
          env: [
            {
              name: 'JAVA_TOOL_OPTIONS'
              value: '-Dreactor.netty.http.server.accessLogEnabled=true'
            }
            {
              name: 'TIMEFEED_HOST'
              value: 'http://localhost:3500'
            }
            {
              name: 'TIMEFEED_BASE'
              value: '/v1.0/invoke/timefeed/method/'
            }
            {
              name: 'ORDER_TOPIC_HOST'
              value: 'http://localhost:3500'
            }
            {
              name: 'ORDER_TOPIC_BASE'
              value: '/v1.0/publish/sample-pubsub/orders'
            }
            {
              name: 'SPRING_MAIN_BANNER-MODE'
              value: 'off'
            }
          ]
        }
      ]
    }
  }
}
