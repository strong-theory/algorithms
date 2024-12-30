import os

from cs50 import SQL
from flask import Flask, flash, redirect, render_template, request, session
from flask_session import Session
from werkzeug.security import check_password_hash, generate_password_hash

from helpers import apology, login_required, lookup, usd

# Configure application
app = Flask(__name__)

# Custom filter
app.jinja_env.filters["usd"] = usd

# Configure session to use filesystem (instead of signed cookies)
app.config["SESSION_PERMANENT"] = False
app.config["SESSION_TYPE"] = "filesystem"
Session(app)

# Configure CS50 Library to use SQLite database
db = SQL("sqlite:///finance.db")


@app.after_request
def after_request(response):
    """Ensure responses aren't cached"""
    response.headers["Cache-Control"] = "no-cache, no-store, must-revalidate"
    response.headers["Expires"] = 0
    response.headers["Pragma"] = "no-cache"
    return response


@app.route("/")
@login_required
def index():
    user_id = session.get("user_id")

    user = db.execute('SELECT * FROM users WHERE id = ?', user_id)[0]

    stocks = db.execute(
        'SELECT symbol, SUM(price) AS price, SUM(shares) AS shares, SUM(total) AS total FROM user_stocks WHERE user_id = ? GROUP BY symbol', user_id)

    total_stock_cash = user["cash"]

    for s in stocks:
        total_stock_cash += s["total"]
        if (s["shares"] == 0):
            stocks.remove(s)

    return render_template("portifolio.html", stocks=stocks, cash=user["cash"], total=total_stock_cash)


@app.route("/buy", methods=["GET", "POST"])
@login_required
def buy():
    """Buy shares of stock"""

    if request.method == 'GET':
        return render_template("buy.html")

    symbol = request.form.get("symbol")
    if not symbol:
        return apology("Missing symbol")

    shares = int(request.form.get("shares"))
    if not shares or shares < 0:
        return apology("Missing shares")

    stock = lookup(symbol)
    if not stock:
        return apology(f"Stock {symbol} does not exists!")

    user_id = session.get("user_id")

    user = db.execute('SELECT * FROM users WHERE id = ?', user_id)[0]

    total_cost = stock["price"] * shares

    if user["cash"] < total_cost:
        return apology(f"You don't have enough cash to buy this stock. Cash: {user["cash"]}. Cost: {total_cost} !")

    user["cash"] -= total_cost

    db.execute('UPDATE users SET cash = ? WHERE id = ?', user["cash"], user_id)

    db.execute('INSERT INTO user_stocks (user_id, symbol, shares, price, total, transaction_type) VALUES (?, ?, ?, ?, ?, ?)',
               user_id, symbol, shares, stock["price"], total_cost, 'B')

    stocks = db.execute(
        'SELECT symbol, SUM(price) AS price, SUM(shares) AS shares, SUM(total) AS total FROM user_stocks WHERE user_id = ? GROUP BY symbol', user_id)

    return redirect("/")


@app.route("/history")
@login_required
def history():
    user_id = session.get("user_id")

    stocks = db.execute(
        'SELECT symbol, price, shares, transaction_type, transaction_date FROM user_stocks WHERE user_id = ?', user_id)

    print(stocks)

    return render_template("history.html", stocks=stocks)


@app.route("/login", methods=["GET", "POST"])
def login():
    """Log user in"""

    # Forget any user_id
    session.clear()

    # User reached route via POST (as by submitting a form via POST)
    if request.method == "POST":
        # Ensure username was submitted
        if not request.form.get("username"):
            return apology("must provide username", 403)

        # Ensure password was submitted
        elif not request.form.get("password"):
            return apology("must provide password", 403)

        # Query database for username
        rows = db.execute(
            "SELECT * FROM users WHERE username = ?", request.form.get("username")
        )

        # Ensure username exists and password is correct
        if len(rows) != 1 or not check_password_hash(
            rows[0]["hash"], request.form.get("password")
        ):
            return apology("invalid username and/or password", 403)

        # Remember which user has logged in
        session["user_id"] = rows[0]["id"]

        # Redirect user to home page
        return redirect("/")

    # User reached route via GET (as by clicking a link or via redirect)
    else:
        return render_template("login.html")


@app.route("/logout")
def logout():
    """Log user out"""

    # Forget any user_id
    session.clear()

    # Redirect user to login form
    return redirect("/")


@app.route("/quote", methods=["GET", "POST"])
@login_required
def quote():
    """Get stock quote."""
    if request.method == 'GET':
        return render_template("quote.html")

    symbol = request.form.get("symbol")

    stock = lookup(symbol)

    if not stock:
        return apology(f"The stock {symbol} does not exits!")

    return render_template("quoted.html", stock=stock)


@app.route("/register", methods=["GET", "POST"])
def register():

    if request.method == 'GET':
        return render_template("register.html")

    username = request.form.get("username")
    password = request.form.get("password")
    confirmation = request.form.get("confirmation")

    if not username:
        return apology("Please, insert an username!")

    if not password or not confirmation:
        return apology("Please, insert a password and confirm it!")

    if not password or not confirmation or password != confirmation:
        return apology("The passwords don't match!")

    exists_username = db.execute(
        'SELECT EXISTS (SELECT * FROM users WHERE username = ?) AS count', username)[0]
    print(exists_username)
    if exists_username["count"] > 0:
        return apology("The user already exists!")

    hash = generate_password_hash(password)
    db.execute('INSERT INTO users (username, hash) VALUES (?, ?)', username, hash)

    return redirect("/")


@app.route("/sell", methods=["GET", "POST"])
@login_required
def sell():
    user_id = session.get("user_id")
    available_stocks = db.execute(
        'SELECT DISTINCT(symbol) FROM user_stocks WHERE user_id = ? AND transaction_type = ?', user_id, 'B')

    if request.method == 'GET':
        user_id = session.get("user_id")
        return render_template("sell.html", stocks=available_stocks)

    if not request.form.get("shares"):
        return apology("Missing shares")

    shares_to_sell = int(request.form.get("shares"))
    if shares_to_sell < 0:
        return apology("Select a number of shares")

    stock_to_sell = request.form.get("symbol")

    symbols = [stock['symbol'] for stock in available_stocks]

    print(f"stock to sell {stock_to_sell}")
    print(f"available_stocks {available_stocks}")
    print(f"symbols {symbols}")

    if stock_to_sell not in symbols:
        return apology("You dont have this stock")

    stock = db.execute(
        'SELECT id, symbol, SUM(price) AS price, SUM(shares) AS shares, SUM(total) AS total FROM user_stocks WHERE user_id = ? AND symbol = ? AND transaction_type = ? GROUP BY symbol', user_id, stock_to_sell, 'B')[0]

    result_shares = stock["shares"] - shares_to_sell
    if result_shares < 0:
        return apology("You dont have enough shares")

    total_cost = stock["price"] * shares_to_sell

    db.execute('INSERT INTO user_stocks (user_id, symbol, shares, price, total, transaction_type) VALUES (?, ?, ?, ?, ?, ?)',
               user_id, stock_to_sell, -shares_to_sell, stock["price"], -total_cost, 'S')

    # update user cash

    user = db.execute('SELECT * FROM users WHERE id = ?', user_id)[0]

    user["cash"] += total_cost

    db.execute('UPDATE users SET cash = ? WHERE id = ?', user["cash"], user_id)

    return redirect("/")
