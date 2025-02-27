import tictactoe as ttt


def test_player_should_return_X():
    board = ttt.initial_state()
    r = ttt.player(board)
    assert ttt.X == r


def test_player_should_return_O():
    board = ttt.initial_state()
    board[0][0] = ttt.X
    r = ttt.player(board)
    assert ttt.O == r


def test_should_return_only_one_action():
    board = [[ttt.X, ttt.O, ttt.X],
            [ttt.X, ttt.X, ttt.O],
            [ttt.O, ttt.X, ttt.EMPTY]]
    r = ttt.actions(board)
    assert set([(2,2)]) == r


def test_should_return_two_actions():
    board = [[ttt.X, ttt.O, ttt.X],
            [ttt.X, ttt.EMPTY, ttt.O],
            [ttt.O, ttt.X, ttt.EMPTY]]
    r = ttt.actions(board)
    assert set([(1, 1), (2, 2)])== r


def test_should_make_a_move_with_X():
    board = ttt.initial_state()
    action = (0, 1)
    r = ttt.result(board, action)
    expected = [[ttt.EMPTY, ttt.X, ttt.EMPTY],
            [ttt.EMPTY, ttt.EMPTY, ttt.EMPTY],
            [ttt.EMPTY, ttt.EMPTY, ttt.EMPTY]]
    assert expected == r


def test_should_make_a_move_with_O():
    board = ttt.initial_state()
    
    action = (0, 1)
    board = ttt.result(board, action)
    action = (1, 1)
    r = ttt.result(board, action)

    expected = [[ttt.EMPTY, ttt.X, ttt.EMPTY],
            [ttt.EMPTY, ttt.O, ttt.EMPTY],
            [ttt.EMPTY, ttt.EMPTY, ttt.EMPTY]]
    assert expected == r


def test_should_X_be_the_winner_first_row():
    board = ttt.initial_state()
    board[0][0] = ttt.X
    board[0][1] = ttt.X
    board[0][2] = ttt.X
    r = ttt.winner(board)
    assert ttt.X == r


def test_should_O_be_the_winner_second_row():
    board = ttt.initial_state()
    board[1][0] = ttt.O
    board[1][1] = ttt.O
    board[1][2] = ttt.O
    r = ttt.winner(board)
    assert ttt.O == r


def test_should_X_be_the_winner_third_row():
    board = ttt.initial_state()
    board[2][0] = ttt.X
    board[2][1] = ttt.X
    board[2][2] = ttt.X
    r = ttt.winner(board)
    assert ttt.X == r


def test_should_O_be_the_winner_any_column():
    for i in range(0, 3):
        board = ttt.initial_state()
        board[0][i] = ttt.O
        board[1][i] = ttt.O
        board[2][i] = ttt.O
        r = ttt.winner(board)
        assert ttt.O == r


def test_should_X_be_the_winner_first_diagonal():
    board = ttt.initial_state()
    board[0][0] = ttt.X
    board[1][1] = ttt.X
    board[2][2] = ttt.X
    r = ttt.winner(board)
    assert ttt.X == r


def test_should_O_be_the_winner_second_diagonal():
    for i in range(0, 3):
        board = ttt.initial_state()
        board[0][2] = ttt.O
        board[1][1] = ttt.O
        board[2][0] = ttt.O
        r = ttt.winner(board)
        assert ttt.O == r


def test_should_not_be_terminal():
    board = ttt.initial_state()
    r = ttt.terminal(board)
    assert False == r


def test_should_be_terminal_when_X_wins():
    board = [[ttt.X, ttt.EMPTY, ttt.EMPTY],
             [ttt.X, ttt.EMPTY, ttt.EMPTY],
             [ttt.X, ttt.EMPTY, ttt.EMPTY]]

    r = ttt.terminal(board)
    assert True == r


def test_should_be_terminal_when_tie():
    board = [[ttt.X, ttt.X, ttt.O],
             [ttt.O, ttt.O, ttt.X],
             [ttt.X, ttt.X, ttt.O]]

    r = ttt.terminal(board)
    assert True == r


def test_utility_should_return_0_when_tie():
    board = [[ttt.X, ttt.X, ttt.O],
             [ttt.O, ttt.O, ttt.X],
             [ttt.X, ttt.X, ttt.O]]

    r = ttt.utility(board)
    assert 0 == r


def test_utility_should_return_1_when_X_wins():
    board = [[ttt.X, ttt.EMPTY, ttt.EMPTY],
             [ttt.X, ttt.EMPTY, ttt.EMPTY],
             [ttt.X, ttt.EMPTY, ttt.EMPTY]]

    r = ttt.utility(board)
    assert 1 == r


def test_utility_should_return__minus_1_when_O_wins():
    board = [[ttt.O, ttt.EMPTY, ttt.EMPTY],
             [ttt.O, ttt.EMPTY, ttt.EMPTY],
             [ttt.O, ttt.EMPTY, ttt.EMPTY]]

    r = ttt.utility(board)
    assert -1 == r