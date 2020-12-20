import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

cred = credentials.Certificate("Firebase/FirebaseAuth.json")
firebase_admin.initialize_app(cred, {
    'databaseURL' : 'https://kjsw-a888e.firebaseio.com/'
})


ref = db.reference('reports/')
ref.set({
    "ad42f" : {
        "name" : "asd",
        "value" : "a4gvr"
    }
})