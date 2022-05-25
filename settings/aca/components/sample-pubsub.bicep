@description('Application name')
param appName string = 'dapr-sample'

@description('Name for Azure container apps environment')
param environmentName string = appName

@description('Azure service bus namespace')
param serviceBusNamespace string

resource environment 'Microsoft.App/managedEnvironments@2022-03-01' existing = {
  name: environmentName
}

resource busAuthRoles 'Microsoft.ServiceBus/namespaces/AuthorizationRules@2021-11-01' existing = {
  name: '${serviceBusNamespace}/RootManageSharedAccessKey'
}

resource components 'Microsoft.App/managedEnvironments/daprComponents@2022-03-01' = {
  name: 'sample-pubsub'
  parent: environment
  properties: {
    componentType: 'pubsub.azure.servicebus'
    version: 'v1'
    ignoreErrors: false
    metadata: [
      {
        name: 'connectionString'
        value: busAuthRoles.listKeys().primaryConnectionString
      }
    ]
    scopes: [
      'aggregator'
      'event-consumer'
    ]
  }
}
