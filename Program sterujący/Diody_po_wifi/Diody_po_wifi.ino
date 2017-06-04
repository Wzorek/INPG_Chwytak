String odebrane = "";
bool stan11 = 0;
bool stan12 = 0;
bool stan13 = 0;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  pinMode(11, OUTPUT);
  pinMode(12, OUTPUT);
  pinMode(13, OUTPUT);

  digitalWrite(11, LOW);
  digitalWrite(12, LOW);
  digitalWrite(13, LOW);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available() > 0) //Sprawdzanie czy Arduino odebralo dane
  {
     odebrane = Serial.readStringUntil('\n');  //Oczytuje dane do znaku konca linii i  zapisuje w zmiennej
     if (odebrane == "11")
     {
      if(stan11 == 0)
      {
      digitalWrite(11,HIGH);
      stan11=1;
      }else
      {
      digitalWrite(11,LOW);
      stan11=0;
      }
     }
     if (odebrane == "12")
     {
      if(stan12 == 0)
      {
      digitalWrite(12,HIGH);
      stan12=1;
      }else
      {
      digitalWrite(12,LOW);
      stan12=0;
      }
     }if (odebrane == "13")
     {
      if(stan13 == 0)
      {
      digitalWrite(13,HIGH);
      stan13=1;
      }else
      {
      digitalWrite(13,LOW);
      stan13=0;
      }
     }
  }
}
