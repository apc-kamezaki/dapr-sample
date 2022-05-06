import { Request, Response, Router } from 'express';

function subscribe(_req: Request, resp: Response) {
  resp.json([
    {
      pubsubName: 'sample-pubsub',
      topic: 'orders',
      route: '/consumers/simple',
    },
  ]);
}

export const routes = Router().get('/subscribe', subscribe);
