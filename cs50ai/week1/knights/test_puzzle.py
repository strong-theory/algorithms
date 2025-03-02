import puzzle as p
from logic import *


symbols = [p.AKnight, p.AKnave, p.BKnight, p.BKnave, p.CKnight, p.CKnave]


# Puzzle 0
# A says "I am both a knight and a knave."
def test_knowledge0():
    knowledge = p.knowledge0

    result = []
    for symbol in symbols:
        if model_check(knowledge, symbol):
            result.append(symbol)

    assert p.AKnave == result[0]
    assert 1 == len(result)


# Puzzle 1
# A says "We are both knaves."
# B says nothing.
def test_knowledge1():
    knowledge = p.knowledge1

    result = []
    for symbol in symbols:
        if model_check(knowledge, symbol):
            result.append(symbol)

    assert p.AKnave == result[0]
    assert p.BKnight == result[1]
    assert 2 == len(result)


# Puzzle 2
# A says "We are the same kind."
# B says "We are of different kinds."
def test_knowledge2():
    knowledge = p.knowledge1

    result = []
    for symbol in symbols:
        if model_check(knowledge, symbol):
            result.append(symbol)

    assert p.AKnave == result[0]
    assert p.BKnight == result[1]
    assert 2 == len(result)


# Puzzle 3
# A says either "I am a knight." or "I am a knave.", but you don't know which.
# B says "A said 'I am a knave'."
# B says "C is a knave."
# C says "A is a knight."
def test_knowledge3():
    knowledge = p.knowledge3

    result = []
    for symbol in symbols:
        if model_check(knowledge, symbol):
            result.append(symbol)

    print(result)
    assert 3 == len(result)
    assert p.AKnight == result[0]
    assert p.BKnave == result[1]
    assert p.CKnight == result[2]

