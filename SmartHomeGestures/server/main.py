import os
from pathlib import Path
from flask import Flask, make_response, request, jsonify, url_for
from werkzeug.utils import secure_filename

UPLOAD_FOLDER = 'gestures'
ALLOWED_EXTENSIONS = {'txt', 'pdf', 'mp4'}

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

def get_practice_num(gesture, user):
    gesture_path = Path('gestures')
    p = gesture_path.glob('**/*')
    files = [x for x in p
        if (x.is_file() and
            user in str(x) and
            str(x.name).startswith(gesture))
    ]
    files = sorted(files, reverse=True)
    if files:
        latest = str(files[0])
        num = int(latest.split('_')[2])
        return num + 1
    else:
        return 1

@app.route('/')
def hello_world():
    return 'Hello, World!'

@app.route('/upload', methods=['POST'])
def upload_file():
    if request.method == 'POST':
        print(request.files)
        user = request.form['user']
        gesture = request.form['gesture']
        if 'file' not in request.files:
            return make_response(
                jsonify({ 'success': False, 'error': 'POST has no file' }),
                404
            )
        file = request.files['file']

        if file.filename == '':
            return make_response(jsonify({ 'success': False }), 403)
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            num = get_practice_num(gesture, user)
            path = Path(f'gestures')
            path.mkdir(parents=True, exist_ok=True)
            filename = f'{gesture}_PRACTICE_{num}_USER_{user}.mp4'
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            return make_response(jsonify({ 'success': True }), 200)


app.run(host="192.168.1.9", port=5000, debug=True)
