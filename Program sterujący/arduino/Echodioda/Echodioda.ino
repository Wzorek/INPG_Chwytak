void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(8,OUTPUT);
  pinMode(9,INPUT);
  pinMode(9,HIGH);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(digitalRead(9)==HIGH)
  Serial.println("ON");
  if(Serial.available() > 0)
  digitalWrite(8,HIGH);
  else
  digitalWrite(8,LOW);
}
