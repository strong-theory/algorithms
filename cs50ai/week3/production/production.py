import scipy.optimize

result = scipy.optimize.linprog(
    [50, 80], # Função de custo
    A_ub=[[5, 2], [-10, -12]], # Coeficientes das restrições
    b_ub=[20, -90] # Limites das restrições
)

if result.success:
    print(f"Machine x1: {round(result.x[0], 2)} hours")
    print(f"Machine x2: {round(result.x[1], 2)} hours")
else:
    print("No solution")

print(result)