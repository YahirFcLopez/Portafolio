#include <SoftwareSerial.h>
#include <Servo.h>

SoftwareSerial btSerial(12, 13); // RX, TX: HC-10 Bluetooth
Servo myservo; // Servo motor

// Pines del driver de motor
#define ENA_PIN   5
#define ENB_PIN   6
#define MOTOR_L_1 8
#define MOTOR_L_2 9
#define MOTOR_R_1 10
#define MOTOR_R_2 11

#define SPEED 255 // Velocidad base

void setup() {
  // Comunicación con el monitor serial
  Serial.begin(9600);
  // Comunicación con el HC-10
  btSerial.begin(9600);

  Serial.println("Sistema iniciado. Esperando comandos por Bluetooth...");

  // Inicializar pines de motores
  pinMode(ENA_PIN, OUTPUT);
  pinMode(ENB_PIN, OUTPUT);
  pinMode(MOTOR_L_1, OUTPUT);
  pinMode(MOTOR_L_2, OUTPUT);
  pinMode(MOTOR_R_1, OUTPUT);
  pinMode(MOTOR_R_2, OUTPUT);

  // Inicializar servo
  myservo.attach(2);
 myservo.write(30); // Inicializa el servo al centro
}

void loop() {
   
  // Si recibimos datos desde el Bluetooth
  if (btSerial.available()) {
    String input = btSerial.readStringUntil('\n');
    input.trim(); // Quitar espacios y saltos

    Serial.print("Comando recibido: ");
    Serial.println(input);

    if (input == "f") {
      CAR_move("FORWARD", SPEED, SPEED);
     
      Serial.println("Avanzando");
    } else if (input == "b") {
      CAR_move("BACK", SPEED, SPEED);
     
      Serial.println("Retrocediendo");
    } else if (input == "l") {
    
      myservo.write(0); // Gira el servo a la izquierda
      Serial.println("Izquierda");
      
    } else if (input == "r") {
     
      myservo.write(60); // Gira el servo a la derecha
      Serial.println("Derecha");


      
    } else if (input == "s") {
      CAR_move("STOP", 0, 0);
      myservo.write(0); // Paso intermedio hacia la izquierda
      delay(200);        // Espera 200 milisegundos
      myservo.write(30);  // Gira completamente a la izquierda
      Serial.println("Izquierda");
      Serial.println("Detenido");

      
    } else if (input.toInt() >= 0 && input.toInt() <= 180) {
      int angle = input.toInt();
      myservo.write(angle);
      Serial.print("Servo a: ");
      Serial.println(angle);

      
    } else if (input == "fl") { // Adelante-izquierda
  
  myservo.write(120); // Gira el servo a la derecha
}
else if (input == "fr") { // Adelante-derecha

  myservo.write(30);  // ← Esto afecta al servo
}
else if (input == "bl") { // Atrás-izquierda
  CAR_move("BACK_LEFT", SPEED , SPEED);
  Serial.println("Atrás-izquierda");
}
else if (input == "br") { // Atrás-derecha
  CAR_move("BACK_RIGHT", SPEED, SPEED);  // ← CORREGIDO
  Serial.println("Atrás-derecha");
}

  }
}

// Función para controlar movimiento
void CAR_move(String direction, unsigned char speed_left, unsigned char speed_right) {
  if (direction == "BACK") {
    digitalWrite(MOTOR_L_1, HIGH);
    digitalWrite(MOTOR_L_2, LOW);
    digitalWrite(MOTOR_R_1, LOW);
    digitalWrite(MOTOR_R_2, HIGH);
  } else if (direction == "FORWARD") {
    digitalWrite(MOTOR_L_1, LOW);
    digitalWrite(MOTOR_L_2, HIGH);
    digitalWrite(MOTOR_R_1, HIGH);
    digitalWrite(MOTOR_R_2, LOW);
  } else if (direction == "LEFT") {
    digitalWrite(MOTOR_L_1, LOW);
    digitalWrite(MOTOR_L_2, HIGH);
    digitalWrite(MOTOR_R_1, LOW);
    digitalWrite(MOTOR_R_2, LOW);
  } else if (direction == "RIGHT") {
    digitalWrite(MOTOR_L_1, LOW);
    digitalWrite(MOTOR_L_2, LOW);
    digitalWrite(MOTOR_R_1, HIGH);
    digitalWrite(MOTOR_R_2, LOW);
 
  } else if (direction == "STOP") {
    digitalWrite(MOTOR_L_1, LOW);
    digitalWrite(MOTOR_L_2, LOW);
    digitalWrite(MOTOR_R_1, LOW);
    digitalWrite(MOTOR_R_2, LOW);
  }

  // Velocidad
  analogWrite(ENA_PIN, speed_left);
  analogWrite(ENB_PIN, speed_right);
}
