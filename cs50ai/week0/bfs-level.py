class Node:
    def __init__(self, child, state):
        self.child = child
        self.state = state


result = {}
def printNode(root: Node, level = 0):

    if (level not in result.keys()):
        result[level] = []
    
    result[level].append(root.state)

    for c in root.child:
        printNode(c, level + 1)

root = Node(state=1 ,child = [Node(state = 2, child = [Node(state = 4, child = [Node(state = 5,child = []), Node(state = 6, child = [])])]), Node(state = 3, child = [])])

printNode(root, 0)

for k in result.keys():
    print(result[k])