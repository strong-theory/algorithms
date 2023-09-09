FROM golang:1.21-alpine as builder

WORKDIR /app

COPY . .

RUN go build -o out fibonacci.go

FROM alpine:latest

COPY --from=builder /app/out .

CMD ./out