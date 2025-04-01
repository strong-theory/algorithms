VARIABLES = ["A", "B", "C", "D", "E", "F", "G"]

CONSTRAINTS = [
    ("A", "B"),
    ("A", "C"),
    ("B", "C"),
    ("B", "D"),
    ("B", "E"),
    ("C", "E"),
    ("C", "F"),
    ("D", "E"),
    ("E", "F"),
    ("E", "G"),
    ("F", "G")
]

DOMAIN = ["Monday", "Tuesday", "Wednesday"]

def backtrack(assignment):
    # Check if all variables are assigned
    if len(assignment) == len(VARIABLES):
        return assignment

    var = select_unassigned_variable(assignment)
    for value in DOMAIN:
        new_assingment = assignment.copy()
        new_assingment[var] = value
        if is_consistent(new_assingment):
            result = backtrack(new_assingment)
            if result is not None:
                return result
    return None


def select_unassigned_variable(assignment):
    for var in VARIABLES:
        if var not in assignment:
            return var
    return None


def is_consistent(assignment):
    for (x, y) in CONSTRAINTS:
        if x not in assignment or y not in assignment:
            continue
        if assignment[x] == assignment[y]:
            return False
    return True


solution = backtrack({})
print(solution)