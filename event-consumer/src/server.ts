import express from 'express';
import actuator = require('express-actuator');
import { routes as consumerRoutes } from './consumers';
import { routes as daprRoutes } from './dapr-configure';

export function startServer() {
  const app = express();

  const port = parseInt(process.env.PORT as string, 10) || 3000;
  app
    .use(
      express.json({
        type: ['application/cloudevents+json', 'application/json'],
      }),
    )
    .use(express.urlencoded({ extended: true }))
    .use('/consumers', consumerRoutes)
    .use('/dapr', daprRoutes)
    .use(actuator())
    .listen(port, () => {
      console.log(`up and running server on port ${port}`);
    });
}
