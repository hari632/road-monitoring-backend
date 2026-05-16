from flask import Flask, request, jsonify
import joblib
import pandas as pd

app = Flask(__name__)

# Load trained model
model = joblib.load("road_model.pkl")
encoder = joblib.load("encoder.pkl")

@app.route('/predict', methods=['POST'])
def predict():

    data = request.json

    vibration = data['vibration']
    speed = data['speed']

    input_data = pd.DataFrame(
        [[vibration, speed]],
        columns=['vibration', 'speed']
    )

    prediction = model.predict(input_data)

    result = encoder.inverse_transform(prediction)

    return jsonify({
        "prediction": result[0]
    })

if __name__ == '__main__':
    app.run(port=5000)