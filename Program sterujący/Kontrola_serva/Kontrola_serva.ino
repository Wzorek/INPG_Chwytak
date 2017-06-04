String odebrane = "";
bool Grab = 0;
int Angle = 0;
int Joint1 = 0;
int Joint2 = 0;
String zmienna = "";

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  pinMode(11, OUTPUT);


  digitalWrite(11, LOW);

}

void loop() 
{
  // put your main code here, to run repeatedly:
  if(Serial.available() > 0) //Sprawdzanie czy Arduino odebralo dane
  {
     odebrane = Serial.readStringUntil('=');
     zmienna = Serial.readStringUntil('\n');  //Oczytuje dane do znaku konca linii i  zapisuje w zmiennej
     zmiana(odebrane,zmienna);
  }
}

void zmiana(String odebrane, String zmienna)
{
     if (odebrane == "Grab")
     {
      if(zmienna.toInt() == 1)
      {
        Serial.println("Chwytam");
          digitalWrite(11,HIGH);
      }else
      if(zmienna.toInt() == 0)
      {
        Serial.println("Puszczam");
          digitalWrite(11,LOW);
      }else
      {
        Serial.println("Nie rozumiem");
      }
     }
     if (odebrane == "Angle")
     {
        
     }
     if (odebrane == "Joint1")
     {
      
     }
     if (odebrane == "Joint2")
     {
      
     }
}
