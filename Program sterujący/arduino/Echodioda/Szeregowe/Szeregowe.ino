
#define zielona 8 
#define czerwona 9

String odebrane = "";
bool stanzielona = 0;
bool stanczerwona = 0;

void setup()
{
  Serial.begin(9600);         //Uruchomienie komunikacji
  pinMode(zielona,OUTPUT);    //Konfiguracja wyjsc diod
  pinMode(czerwona,OUTPUT);

  digitalWrite(zielona, LOW); //Ustawienie poczatkowego stanu diod
  digitalWrite(czerwona, LOW);
}

void loop()
{
  if(Serial.available() > 0) //Sprawdzanie czy Arduino odebralo dane
  {
     odebrane = Serial.readStringUntil('\n');  //Oczytuje dane do znaku konca linii i  zapisuje w zmiennej
     if (odebrane == "zielona")
     {
      if(stanzielona == 0)
      {
      digitalWrite(zielona,HIGH);
      stanzielona=1;
      }else
      {
      digitalWrite(zielona,LOW);
      stanzielona=0;
      }
     }else
     if (odebrane == "czerwona")
     {
      if (stanczerwona == 0)
      {
      digitalWrite(czerwona,HIGH);
      stanczerwona=1;
      }
      else
      {
      digitalWrite(czerwona,LOW);
      stanczerwona=0;
      }
     }else    //w przypadku odebrania innego komunikatu wyswietlamy komunikat bledu
     {
      Serial.println("Nie mam takiego koloru :( tylko czerwona i zielona");
     }
  }
}

