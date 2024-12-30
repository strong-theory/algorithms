import sqlite3
from flask import Flask, flash, jsonify, redirect, render_template, request, session

# Configure application
app = Flask(__name__)

# Ensure templates are auto-reloaded
app.config["TEMPLATES_AUTO_RELOAD"] = True

def get_db_connection():
    """Establish a new database connection"""
    conn = sqlite3.connect("birthdays.db")
    conn.row_factory = sqlite3.Row  # To return rows as dictionaries
    return conn

@app.after_request
def after_request(response):
    """Ensure responses aren't cached"""
    response.headers["Cache-Control"] = "no-cache, no-store, must-revalidate"
    response.headers["Expires"] = 0
    response.headers["Pragma"] = "no-cache"
    return response


@app.route("/", methods=["GET", "POST"])
def index():
    if request.method == "POST":

        # TODO: Add the user's entry into the database

        name = request.form.get("name")
        day = request.form.get("day")
        month = request.form.get("month")

        conn = get_db_connection()
        cursor = conn.cursor()
        cursor.execute(
            "INSERT INTO birthdays (name, day, month) VALUES (?, ?, ?)",
            (name, day, month),
        )
        conn.commit()
        conn.close()

        return redirect("/")

    else:

        conn = get_db_connection()
        cursor = conn.cursor()
        cursor.execute("SELECT * FROM birthdays")
        birthdays = cursor.fetchall()
        conn.close()

        return render_template("index.html", birthdays=birthdays)
