import { Request, Response, Router } from 'express';

async function simpleOrder(req: Request, resp: Response) {
  const simpleOrder = (req?.body?.data || req?.body) as SimpleOrderEvent;
  console.log(simpleOrder);
  resp.status(200).send();
}

export interface SimpleOrderEvent {
  orderName: string;
}

export const routes = Router().post('/simple', simpleOrder);
