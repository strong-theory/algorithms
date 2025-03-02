from minesweeper import MinesweeperAI


def print_ai_status():
    print(f'\nAfter move:{move} with nearby_count:{nearby_count}')
    if ai.knowledge:
        print('Sentences in Knowledge Base:')
        for cnt, s in enumerate(ai.knowledge):
            # print(f'S#{cnt}: {s}')
            # create list from cell set with moves ordered by row/column
            s_as_l = sorted(list(s.cells), key=lambda t: (t[0], t[1]))
            print(f'S#{cnt}: {s_as_l} = {s.count}')
    else:
        print('NO Sentences in Knowledge Base.')
    print(f'Safe Cells: {sorted(list(ai.safes))}')
    print(f'Mine Cells: {sorted(list(ai.mines))}')


# Create AI agent
HEIGHT, WIDTH, MINES = 8, 8, 8
ai = MinesweeperAI(height=HEIGHT, width=WIDTH)

# Test new sentence logic (3rd requirement)
move, nearby_count = (1, 1), 0
ai.add_knowledge(move, nearby_count)
print_ai_status()

move, nearby_count = (2, 2), 2
ai.add_knowledge(move, nearby_count)
print_ai_status()

# Test inference logic for new safes or mines (4th requirement)
move, nearby_count = (3, 3), 0
ai.add_knowledge(move, nearby_count)
print_ai_status()

"""
After move:(1, 1) with nearby_count:0
NO Sentences in Knowledge Base.
Safe Cells: [(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2)]
Mine Cells: []

After move:(2, 2) with nearby_count:2
Sentences in Knowledge Base:
S#0: [(3, 2), (1, 3), (2, 3), (3, 3), (3, 1)] = 2
Safe Cells: [(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2)]
Mine Cells: []

After move:(3, 3) with nearby_count:0
NO Sentences in Knowledge Base.
Safe Cells: [(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2), (2, 3), (2, 4), (3, 2), (3, 3), (3, 4), (4, 2), (4, 3), (4, 4)]
Mine Cells: [(1, 3), (3, 1)]
"""