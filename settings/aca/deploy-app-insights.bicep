@description('Application name')
param appName string = 'dapr-sample'
@description('Location for resource')
param location string = resourceGroup().location

@description('Application insights name')
param insightsName string = '${appName}-${uniqueString('ai', resourceGroup().id)}'

module insights '../bicep-templates/monitors/app-insights.bicep' = {
  name: 'nested-${insightsName}'
  params: {
    name: insightsName
    location: location
    workspaceNamePrefix: appName
    tags: {
      displayName: 'Application Insights Instance'
    }
  }
}
