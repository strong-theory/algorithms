class Node:
    def __init__(self, state, x, y):
        self.state = state
        self.x = x
        self.y = y
        self.child = []
        self.parent = None

    def __str__(self):
        child_str = '_'.join(map(str, self.child))
        return f"({self.x}, {self.y}) -> '{self.state}' child = [{child_str}]"

WALL = '#'
FREE =  ' '
START = 'A'
END = 'B'

def load_maze(file_name: str):
    file = open(file=file_name, mode="r")
    result = []
    lines = file.readlines()

    for l in lines:

        row = []
        for c in l:
            if c == '\n':
                continue
            row.append(c)
        result.append(row)
    return result

def find_root(maze) -> Node:

    for id_x, r in enumerate(maze):
        for id_y, c in enumerate(r):
            if c == START:
                return Node(START, id_x, id_y)

    raise "Can't find the root."

def check_neighbour(current: Node, next_x, next_y, maze):

    if next_x < 0 or next_y < 0:
        return None
    
    if next_x > len(maze) - 1:
        return None
    
    if next_y > len(maze[0]) - 1:
        return None
    
    if current.parent is not None and next_x == current.parent.x and next_y == current.parent.y:
        return None

    current_value = maze[next_x][next_y]
    if current_value is WALL:
        return None

    child_node = Node(current_value, next_x, next_y)
    child_node.parent = current
    return build_chart(child_node, maze)

def build_chart(current_node: Node, maze):
    child = []

    top = check_neighbour(current_node, current_node.x - 1, current_node.y, maze)
    if top is not None:
        top.parent = current_node
        child.append(top)

    bottom = check_neighbour(current_node, current_node.x + 1, current_node.y, maze)
    if bottom is not None:
        bottom.parent = current_node
        child.append(bottom)

    left = check_neighbour(current_node, current_node.x, current_node.y - 1, maze)
    if left is not None:
        left.parent = current_node
        child.append(left)

    right = check_neighbour(current_node, current_node.x, current_node.y + 1, maze)
    if right is not None:
        right.parent = current_node
        child.append(right)

    current_node.child = child

    return current_node

maze = load_maze('maze.txt')

root = find_root(maze)

new_root = build_chart(root, maze)
print(new_root)
