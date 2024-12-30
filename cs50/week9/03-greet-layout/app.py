from flask import Flask, request, render_template

app = Flask(__name__)

@app.route('/')
def index():
    return render_template("index.html")

@app.route('/02-greet')
def greet():
    name = request.args.get("name", default="world")
    return render_template("02-greet.html", placeholder=name)