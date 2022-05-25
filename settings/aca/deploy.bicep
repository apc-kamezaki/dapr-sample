@description('Application name')
param appName string = 'dapr-sample'
@description('Location for resource')
param location string = resourceGroup().location

module workspace 'deploy-workspace.bicep' = {
  name: 'deploy-workspace'
  params: {
    appName: appName
    location: location
  }
}

module ai 'deploy-app-insights.bicep' = {
  name: 'deploy-ai'
  params: {
    appName: appName
    location: location
  }
  dependsOn: [
    workspace
  ]
}

module environment 'deploy-aca.bicep' = {
  name: 'deploy-environment'
  params: {
    location: location
    appName: appName
  }
}
