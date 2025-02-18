class Node:
    def __init__(self, child, state):
        self.child = child
        self.state = state


def printNode(root):
    print(root.state, end=' ')
    print()
    
    queue = []
    for c in root.child:
        queue.append(c)
        print (c.state, end = ' ')
    print()

    while (len(queue) > 0):
        el = queue.pop(0)
        for c in el.child:
            printNode(c)

root = Node(state=1 ,child = [Node(state = 2, child = [Node(state = 4, child = [Node(state = 5,child = []), Node(state = 6, child = [])])]), Node(state = 3, child = [])])

printNode(root)