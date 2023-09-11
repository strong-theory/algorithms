FROM gcc:latest AS compiler

WORKDIR /app

COPY dijkstra.c .

RUN gcc -o dijkstra dijkstra.c

FROM alpine:latest

COPY --from=compiler /app/dijkstra .

CMD ./dijkstra