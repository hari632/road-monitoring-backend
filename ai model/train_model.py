import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import LabelEncoder
import joblib

# Load dataset
file = pd.read_csv("road.csv")

# Features
X = file[["vibration", "speed"]]

# Labels
encoder = LabelEncoder()
y = encoder.fit_transform(file["road_condition"])

# Split dataset
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

# Create model
model = RandomForestClassifier()

# Train model
model.fit(X_train, y_train)

# Save trained model
joblib.dump(model, "road_model.pkl")
joblib.dump(encoder, "encoder.pkl")

print("AI model trained successfully")