import tictactoe as ttt

board = ttt.initial_state()

board[1][1] = ttt.X
board[0][0] = ttt.O
#board[0][2] = ttt.X
#board[0][1] = ttt.O

while True:
    game_over = ttt.terminal(board)
    if game_over:
        winner = ttt.winner(board)
        print(f"O vencedor é {winner}")
        print(board)
        break

    player = ttt.player(board)
    print(f"Player {player}")
    move = ttt.minimax(board)

    board = ttt.result(board, move)


def print(board):
    for r in range(0, 3):
        print (board[r])
        print(end = '\n')