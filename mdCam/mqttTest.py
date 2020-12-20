import paho.mqtt.client as mqtt

mqtt = mqtt.Client("pi")
mqtt.connect("localhost", 1883)

mqtt.publish("hello", "adf")
mqtt.loop(2)
