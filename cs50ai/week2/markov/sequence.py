from hhm import model

observations = [
    "umbrella",
    "umbrella",
    "no umbrella",
    "umbrella",
    "umbrella",
    "umbrella",
    "umbrella",
    "no umbrella",
    "no umbrella",
]

predictions = model.predict(observations)

for prediction in predictions:
    print(model.states[prediction].name)