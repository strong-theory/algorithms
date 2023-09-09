# Fibonacci

Algoritmo para descobrir o N valor da sequência de fibonacci.

## Executar local

```go
go run gibonacci.go
```

A saída será como essa:

```
$ go run fibonacci.go 
0  termo da sequencia é:  0
5  termo da sequencia é:  5
10  termo da sequencia é:  55
15  termo da sequencia é:  610
20  termo da sequencia é:  6765
25  termo da sequencia é:  75025
30  termo da sequencia é:  832040
35  termo da sequencia é:  9227465
40  termo da sequencia é:  102334155
45  termo da sequencia é:  1134903170
```

## Imagem docker

Uma imagem bem simples que faz o build e cria uma imagem final a partir da alpine para deixar a imagem com tamanho menor.

### Executar local

1. Build da image:
    `docker build -t fibonacci:v1 .`
2. Run
    `docker run fibonacci:v1`