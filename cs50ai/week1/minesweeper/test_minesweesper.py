import minesweeper as m
import pytest

HEIGHT, WIDTH, MINES = 8, 8, 8
ai = m.MinesweeperAI(height=HEIGHT, width=WIDTH)


@pytest.fixture(autouse=True)
def setup():
    global ai
    ai = m.MinesweeperAI(height=HEIGHT, width=WIDTH)


def test_0_0():
    cell = (0, 0)
    count = 1
    sentence = ai.create_sentence(cell, count)
    assert 3, len(sentence.cells)
    assert {(0, 1), (1, 0), (1, 1)}, sentence.cells
    assert 1, sentence.count


def test_4_4():
    cell = (4, 4)
    count = 1

    ai44 = m.MinesweeperAI(height=5, width=5)

    sentence = ai44.create_sentence(cell, count)
    assert 3, len(sentence.cells)
    assert {(2, 3), (2, 4), (3, 3)}, sentence.cells
    assert 1, sentence.count


def test_7_7():
    cell = (7, 7)
    count = 1
    sentence = ai.create_sentence(cell, count)
    assert 3, len(sentence.cells)
    assert {(0, 6), (1, 7), (1, 6)}, sentence.cells
    assert 1, sentence.count


def test_add_knowledge_add_sentence():
    move, nearby_count = (1, 1), 0
    ai.add_knowledge(move, nearby_count)

    assert len(ai.knowledge) == 0, "The knowledge is not empty"
    assert len(ai.mines) == 0, "The mines must be empty"
    assert {(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2)}, ai.safes


def test_add_knowledge_inference_for_safes():
    move, nearby_count = (1, 1), 0
    ai.add_knowledge(move, nearby_count)

    assert len(ai.knowledge) == 0, "The knowledge is not empty"
    assert len(ai.mines) == 0, "The mines must be empty"
    assert {(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2)}, ai.safes

    move, nearby_count = (2, 2), 2
    ai.add_knowledge(move, nearby_count)

    assert len(ai.knowledge) == 1, "The knowledge must have One Sentece"
    assert len(ai.mines) == 0, "The mines must be empty"
    assert {(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2)}, ai.safes


def test_add_knowledge_3():

    move, nearby_count = (1, 1), 0
    ai.add_knowledge(move, nearby_count)

    assert len(ai.knowledge) == 0, "The knowledge is not empty"
    assert len(ai.mines) == 0, "The mines must be empty"
    assert {(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2)}, ai.safes

    move, nearby_count = (2, 2), 2
    ai.add_knowledge(move, nearby_count)

    assert len(ai.knowledge) == 1, "The knowledge must have One Sentece"
    assert len(ai.mines) == 0, "The mines must be empty"
    assert {(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2)}, ai.safes

    move, nearby_count = (3, 3), 0
    ai.add_knowledge(move, nearby_count)

    assert {(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2),
            (2, 3), (2, 4), (3, 2), (3, 3), (3, 4), (4, 2), (4, 3), (4, 4)}, ai.safes
    assert len(ai.knowledge) == 0, "The knowledge is not empty"
    assert {(1, 3), (3, 1)}, ai.mines


def test_checking_infer_mines():
    move, nearby_count = (0, 0), 2
    ai.add_knowledge(move, nearby_count)

    move, nearby_count = (1, 1), 2
    ai.add_knowledge(move, nearby_count)

    assert {(0, 2), (1, 2), (2, 0), (2, 1),(2, 2)}, ai.safes
    assert {(0, 1), (1, 0)}, ai.mines


def test_add_knowledge_situation():

    ai.add_knowledge((0, 7), 0)
    ai.add_knowledge((0, 6), 0)
    ai.add_knowledge((0, 5), 0)
    ai.add_knowledge((0, 4), 0)
    ai.add_knowledge((0, 3), 0)
    ai.add_knowledge((0, 2), 0)
    ai.add_knowledge((0, 1), 0)

    ai.add_knowledge((1, 4), 1)
    ai.add_knowledge((1, 3), 2)
    ai.add_knowledge((1, 1), 2)
    ai.add_knowledge((2, 4), 1)
    ai.add_knowledge((2, 1), 3)

    # should resolve mines
    ai.add_knowledge((1, 2), 2)

    assert (0, 0) in ai.safes
    assert (1, 0) in ai.safes
    assert (2, 5) in ai.safes
    assert (3, 3) in ai.safes
    assert (3, 4) in ai.safes
    assert (3, 5) in ai.safes
    assert (2, 0) in ai.mines
    assert (2, 2) in ai.mines
    assert (2, 3) in ai.mines


def test_add_knowledge_4():

    ai.add_knowledge((0, 7), 0)
    ai.add_knowledge((0, 6), 0)
    ai.add_knowledge((0, 5), 0)
    ai.add_knowledge((0, 4), 0)
    ai.add_knowledge((0, 3), 0)

    ai.add_knowledge((1, 7), 0)
    ai.add_knowledge((1, 6), 0)
    ai.add_knowledge((1, 5), 1)
    ai.add_knowledge((1, 4), 2)
    ai.add_knowledge((1, 3), 2)
    ai.add_knowledge((1, 2), 2)

    ai.add_knowledge((2, 7), 0)
    ai.add_knowledge((2, 6), 0)
    ai.add_knowledge((2, 5), 1)
    ai.add_knowledge((2, 2), 3)

    ai.add_knowledge((3, 7), 1)
    ai.add_knowledge((3, 6), 1)

    ai.add_knowledge((4, 5), 1)

    ai.add_knowledge((0, 2), 0)

    assert (0,1) in ai.safes
    assert (1,1) in ai.safes
    assert (3,5) in ai.safes

    assert (2, 3) in ai.mines
    assert (2, 4) in ai.mines
    assert (2, 1) in ai.mines

