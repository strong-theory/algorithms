from flask import Flask, request, render_template

app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
def index():
    if request.method == "POST":
        name = request.form.get("name")
        return render_template("02-greet.html", name=name)

    return render_template("index.html")
