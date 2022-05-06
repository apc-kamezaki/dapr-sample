FROM node:16-alpine
LABEL maintainer = "AP Communications Co.,Ltd."
WORKDIR /app
RUN mkdir -p node_modules && chown -R node:node .
COPY package*.json ./
COPY tsconfig.json ./
COPY src ./
RUN npm ci --no-optional --unsafe-perms && npm run build
USER node
ENTRYPOINT [ "node" ]
CMD [ "dist/index.js" ]
EXPOSE 3000
