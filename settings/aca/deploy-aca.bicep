@description('Application name')
param appName string = 'dapr-sample'
@description('location for these resources')
param location string = resourceGroup().location

@description('Name for Azure container apps environment')
param environmentName string = appName

@description('Name for azure application insights')
param appInsightsName string = '${appName}-${uniqueString('ai', resourceGroup().id)}'

@description('Name prefix for log analytics workspace')
param workspacePrefx string = appName

module workspace '../bicep-templates/monitors/query-workspace.bicep' = {
  name: 'query-workplace-${workspacePrefx}'
  params: {
    workspaceNamePrefix: workspacePrefx
  }
}

module acaEnvironment '../bicep-templates/containers/aca.bicep' = {
  name: 'deploy-aca-envinronment-${environmentName}'
  params: {
    location: location
    environmentName: environmentName
    insightsName: appInsightsName
    workspaceName: workspace.outputs.name
  }
}

output id string = acaEnvironment.outputs.id
