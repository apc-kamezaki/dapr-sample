import appInsights = require('applicationinsights');

import { startServer } from './server';

function isApplicationInsightsEnabled(): boolean {
  return process.env.APPLICATIONINSIGHTS_CONNECTION_STRING ? true : false;
}

function startApplicationInsights() {
  if (isApplicationInsightsEnabled()) {
    appInsights.setup();
    appInsights.defaultClient.config.disableAppInsights = false;
    appInsights.defaultClient.context.tags[
      appInsights.defaultClient.context.keys.cloudRole
    ] = 'event-consumer';
    appInsights.start();
  }
}

startApplicationInsights();
startServer();
