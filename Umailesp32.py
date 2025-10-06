import network
import time
from machine import Pin, TouchPad
import umail

SSID = "NOMBRE DE LA RED PARA UTILIZARLA"
PASSWORD = " CONTRASEÑA DE LA MISMA"

SMTP_SERVER = "smtp.gmail.com"
SMTP_PORT = 465
SENDER_EMAIL = "AQUI PONER CORREO DEL REMITENTE"
SENDER_PASSWORD = "AQUI PONER CONTRASEÑA DE APLICACION"
RECEIVER_EMAIL = "AQUI PONER CORREO DEL DESTINATARIO"

touch = TouchPad(Pin(13))

station = network.WLAN(network.STA_IF)
station.active(True)
station.connect(SSID, PASSWORD)

print("Conectando a WiFi...")
while not station.isconnected():
    time.sleep(1)
print("Conectado. IP:", station.ifconfig()[0])

def send_email(subject, content):
    try:
        smtp = umail.SMTP(SMTP_SERVER, SMTP_PORT, ssl=True)
        smtp.login(SENDER_EMAIL, SENDER_PASSWORD)
        smtp.to(RECEIVER_EMAIL)
        smtp.write("From: {}\n".format(SENDER_EMAIL))
        smtp.write("Subject: {}\n".format(subject))
        smtp.write("\n")
        smtp.write(content)
        smtp.send()
        smtp.quit()
        print("Correo enviado")
    except Exception as e:
        print("Error al enviar correo:", e)

while True:
    valor = touch.read()
    print("Lectura t璋ヽtil:", valor)

    if valor < 400:
        print("Toque detectado, enviando correo...")
        send_email("Alerta ESP32", "Se ha detectado un toque en el sensor t璋ヽtil del ESP32")
        time.sleep(10)

    time.sleep(0.5)



