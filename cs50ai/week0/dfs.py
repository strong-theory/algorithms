class Node:
    def __init__(self, child, state):
        self.child = child
        self.state = state


def printNode(root):
    print(root.state, end=' ')
    print()
    for c in root.child:
        printNode(c)

root = Node(state=1 ,child = [Node(state = 2, child = [Node(state = 4, child = [Node(state = 5,child = []), Node(state = 6, child = [])])]), Node(state = 3, child = [])])

printNode(root)