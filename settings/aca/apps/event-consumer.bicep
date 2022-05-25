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

resource eventConsumer 'Microsoft.App/containerApps@2022-03-01' = {
  name: 'event-consumer'
  location: location
  properties: {
    managedEnvironmentId: environment.id
    configuration: {
      secrets: [
        {
          name: 'container-regsitry-password'
          value: acr.listCredentials().passwords[0].value
        }
        {
          name: 'ai-connection-string'
          value: queryAi.properties.ConnectionString
        }
      ]
      registries: [
        {
          server: acr.properties.loginServer
          username: acr.name
          passwordSecretRef: 'container-regsitry-password'
        }
      ]
      dapr: {
        enabled: true
        appId: 'event-consumer'
        appProtocol: 'http'
        appPort: 3000
      }
    }
    template: {
      containers: [
        {
          image: '${acrName}.azurecr.io/dapr-sample/event-consumer:1.1.0'
          name: 'event-consumer'
          env: [
            {
              name: 'APPLICATIONINSIGHTS_CONNECTION_STRING'
              secretRef: 'ai-connection-string'
            }
          ]
        }
      ]
    }
  }
}
