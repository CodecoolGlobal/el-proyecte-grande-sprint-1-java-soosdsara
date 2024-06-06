FROM node:19-alpine as build
WORKDIR /vite-app

COPY /my_pokemon_team_frontend/package*.json ./

RUN npm install
COPY /my_pokemon_team_frontend ./
#EXPOSE 5173

RUN npm run build


FROM node:slim
WORKDIR /app
COPY /my_pokemon_team_express/package*.json ./
RUN npm install
COPY /my_pokemon_team_express ./
COPY --from=build /vite-app/dist /app/public
CMD ["node", "index.js"]
