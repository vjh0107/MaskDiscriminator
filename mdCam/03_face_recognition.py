#!/usr/bin/python
# -*- coding: utf-8 -*-

import cv2
import numpy as np
import os
import paho.mqtt.client as mqtt
import pyimgur
import time
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

cred = credentials.Certificate("Firebase/FirebaseAuth.json")
firebase_admin.initialize_app(cred, {
    'databaseURL' : 'https://kjsw-a888e.firebaseio.com/'
})

client_id = 'f93576901fc71a4'

im = pyimgur.Imgur(client_id)

mqtt = mqtt.Client("pi")
mqtt.connect("localhost", 1883)

recognizer = cv2.face.LBPHFaceRecognizer_create()
recognizer.read('trainer/trainer.yml')
cascadePath = "Cascades/haarcascade_frontalface_default.xml"
faceCascade = cv2.CascadeClassifier(cascadePath);
font = cv2.FONT_HERSHEY_SIMPLEX

id = 0

names = ['None', 'Junhyung', 'people who educated']

cam = cv2.VideoCapture(0)
cam.set(3, 640)
cam.set(4, 480)

minW = 0.1*cam.get(3)
minH = 0.1*cam.get(4)

while True:
    ret, img =cam.read()
    #img = cv2.flip(img, -1) # Flip vertically
    gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
    
    faces = faceCascade.detectMultiScale( 
        gray,
        scaleFactor = 1.2,
        minNeighbors = 5,
        minSize = (int(minW), int(minH)),
       )

    for(x,y,w,h) in faces:
        cv2.rectangle(img, (x,y), (x+w,y+h), (0,255,0), 2)
        id, confidence = recognizer.predict(gray[y:y+h,x:x+w])
        # Check if confidence is less them 100 ==> "0" is perfect match
        print(confidence)
        if (confidence < 100):
            id = names[id]
            now = str(time.localtime())
            cv2.imwrite("photo/" + id + now + ".jpg")
            uploaded_image = im.upload_image("photo/" + id + now + ".jpg", title = id)
            confidence = "  {0}%".format(round(100 - confidence))

            ref = db.reference('reports/')
            ref.set({
                now : {
                    "name" : id,
                    "value" : uploaded_image.link
                }
            })
#           mqtt.publish("hello", id + "|" + uploaded_image.link)
#           mqtt.loop(2)
        else:
            id = "unknown"
            confidence = "  {0}%".format(round(100 - confidence))
        
        cv2.putText(img, str(id), (x+5,y-5), font, 1, (255,255,255), 2)
        cv2.putText(img, str(confidence), (x+5,y+h-5), font, 1, (255,255,0), 1)  
    
    cv2.imshow('camera',img) 
    k = cv2.waitKey(10) & 0xff # Press 'ESC' for exiting video
    if k == 27:
        break
# Do a bit of cleanup
print("\n [INFO] Exiting Program and cleanup stuff")
cam.release()
cv2.destroyAllWindows()
