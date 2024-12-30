from flask import Flask, jsonify, request, render_template

app = Flask(__name__)

@app.route('/')
def index():
    return render_template("index.html")

@app.route('/search', methods=['GET'])
def search():
    result = []
    q = request.args.get("q")

    if not q:
        return result

    shows = ['The Office', 'Rick & Morty', 'Demon Slayer', 'Sopranos', 'Dragon Ball', 'Breaking Bad', 'Game of Thrones', 'House', 'The Bear', 'Band of Brothers', 'Stranger Things', 'The Crown', 'House M.D.', 'Homeland']

    for show in shows:
        if show.__contains__(q):
            result.append({
                "title": show
            })
    return jsonify(result)