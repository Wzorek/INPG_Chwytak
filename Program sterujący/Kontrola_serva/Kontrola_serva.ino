String odebrane = "";
String angle = "";
String joint1 = "";
String joint2 = "";
String grab = "";
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
     angle = Serial.readStringUntil(':');  //Oczytuje dane do znaku konca linii i  zapisuje w zmiennej
     joint1 = Serial.readStringUntil(':');
     joint2 = Serial.readStringUntil(':');
     grab = Serial.readStringUntil(' ');
     zmiana(angle,joint1,joint2,grab);
  }
}

void zmiana(String angle, String joint1, String joint2, String grab)
{
      if(grab.toInt() == 1)
      {
        Serial.println("Chwytam");
          digitalWrite(11,HIGH);
      }else
      if(grab.toInt() == 0)
      {
        Serial.println("Puszczam");
          digitalWrite(11,LOW);
      }else
      {
        Serial.println("Nie rozumiem");
      }
     
}
