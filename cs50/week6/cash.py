from cs50 import get_float


cash = get_float("Change: ")
while cash < 0:
    cash = get_float("Change: ")


def calculate(amount, total_coins):
    if (amount >= 0.25):
        amount -= 0.25
    elif (amount >= 0.10):
        amount -= 0.10
    elif (amount >= 0.05):
        amount -= 0.05
    elif (amount >= 0.01):
        amount -= 0.01
    else:
        return total_coins

    total_coins += 1
    return calculate(round(amount, 2), total_coins)


total_coins = calculate(cash, 0)

print(f"{total_coins}")
