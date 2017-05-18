#define przycisk A2
#define osx A0
#define osy A1

int x=0;
int y=0;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600); //Rozpoczynamy nadawanie
  pinMode(przycisk, INPUT); //deklaracja przycisku jako wejscie
  digitalWrite(przycisk, HIGH);
  pinMode(osx, INPUT);      //deklaracja osi jako wejscia
  pinMode(osy, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  x=analogRead(osx);
  y=analogRead(osy);
  Serial.print("Osx ");
  Serial.print(x);
  Serial.print(" ");
  Serial.print("Osy ");
  Serial.print(y);
  Serial.print(" ");
  Serial.print(digitalRead(przycisk));
  Serial.print("\n");
  delay(500);
}
