from flask import Flask, request, render_template

app = Flask(__name__)

@app.route('/')
def index():
    name = request.args.get("name", default="world")
    return render_template("index.html", placeholder=name)