# Laps

In a race, how many laps will the first place take to complete a 1 lap difference over the second place ?

The program receive 2 params X and Y as args.
 - X is the seconds that the first place take to complete a lap.
 - Y is the seconds that the second place take to complete a lap.

## Examples:

Input (x y)|Exit (laps)
--|--
1 10|2
4 8|2
5 7| 4
6875 7109 | 31

## Running

`go run laps.go 1 10`

`go run laps.go 4 8`

`go run laps.go 5 7`

`go run laps.go 6875 7109`

result:
```
$ go run laps.go 6875 7109
31
```
