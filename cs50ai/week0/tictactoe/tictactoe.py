"""
Tic Tac Toe Player
"""
import copy

X = "X"
O = "O"
EMPTY = None


def initial_state():
    """
    Returns starting state of the board.
    """
    return [[EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY]]


def player(board):
    """
    Returns player who has the next turn on a board.
    """
    total_x = 0
    total_y = 0
    for i in range(0, 3):
        for j in range(0, 3):
            if board[i][j] == X:
                total_x = total_x + 1
            elif board[i][j] == O:
                total_y = total_y + 1

    if total_x == total_y:
        return X
    return O


def actions(board):
    """
    Returns set of all possible actions (i, j) available on the board.
    """
    result_actions = set()
    for i in range(0, 3):
        for j in range(0, 3):
            if board[i][j] is EMPTY:
                result_actions.add((i, j))
    return result_actions


def result(board, action):
    """
    Returns the board that results from making move (i, j) on the board.
    """

    if action[0] < 0 or action[0] > 2:
        raise "Invalid action"
    if action[1] < 0 or action[1] > 2:
        raise "Invalid action"
    
    if board[action[0]][action[1]] is not EMPTY:
        raise "Invalid action"

    result_board = copy.deepcopy(board)

    result_board[action[0]][action[1]] = player(board)

    return result_board


def winner(board):
    """
    Returns the winner of the game, if there is one.
    """
    r = utility(board)
    if r == 1:
        return X
    elif r == -1:
        return O
    return EMPTY


def terminal(board):
    """
    Returns True if game is over, False otherwise.
    """
    if all_cells_filled(board):
        return True
    
    # check lines
    for row in range(0, 3):
        if are_all_the_same_value(board[row]):
            return True

    diagonal = [board[0][0], board[1][1], board[2][2]]
    if are_all_the_same_value(diagonal):
        return True
    diagonal = [board[0][2], board[1][1], board[2][0]]
    if are_all_the_same_value(diagonal):
        return True
    
    for i in range(0, 3):
        col = [board[0][i], board[1][i], board[2][i]]
        if are_all_the_same_value(col):
            return True

    return False


def utility(board):
    """
    Returns 1 if X has won the game, -1 if O has won, 0 otherwise.
    """
    for row in range(0, 3):
        if are_all_the_same_value(board[row]):
            return define_x_or_o(board[row][0])

    diagonal = [board[0][0], board[1][1], board[2][2]]
    if are_all_the_same_value(diagonal):
        return define_x_or_o(diagonal[0])
    
    diagonal = [board[0][2], board[1][1], board[2][0]]
    if are_all_the_same_value(diagonal):
        return define_x_or_o(diagonal[0])
    
    for i in range(0, 3):
        col = [board[0][i], board[1][i], board[2][i]]
        if are_all_the_same_value(col):
            return define_x_or_o(col[0])
    
    return 0


def define_x_or_o(value):
    """
    Returns 1 if value is X, -1 if is O, 0 otherwise.
    """
    if value == X:
        return 1
    if value == O:
        return -1
    return 0


def minimax(board):
    """
    Returns the optimal action for the current player on the board.
    """
    if terminal(board):
        return None
    
    next_player = player(board)

    if next_player == X:
        (next_action, _) = max_value(board)
        return next_action
    else:
        (next_action, _) = min_value(board)
        return next_action


def max_value(board):
    """
    Função para maximizar
    """
    if terminal(board):
        return float('-inf'), utility(board)

    future_actions = actions(board)
    result_action = None
    value = float('-inf')
    for action in future_actions:

        (_, minimized_value) = min_value(result(board, action))
        if minimized_value > value:
            value = minimized_value
            result_action = action
    return result_action, value


def min_value(board):
    """
    Função para minimizar
    """
    if terminal(board):
        return float('inf'), utility(board)

    future_actions = actions(board)
    result_action = None

    value = float('inf')
    for action in future_actions:
        (_, maximized_value) = max_value(result(board, action))
        if maximized_value < value:
            value = maximized_value
            result_action = action

    return result_action, value


def all_cells_filled(board):
    """
    Verifica se todas as celulas estao preenchidas
    """
    for i in range(0, 3):
        for j in range(0, 3):
            if board[i][j] == EMPTY:
                return False
    return True


def are_all_the_same_value(row):
    """
    verifica se a row possui apenas valores iguais
    """
    first = row[0]
    if first == EMPTY:
        return False
    if first == row[1] and first == row[2]:
        return True
    return False
