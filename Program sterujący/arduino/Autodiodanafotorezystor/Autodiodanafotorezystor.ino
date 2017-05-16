int odczyt = 0; //zmienna pod wartosc odczytu ADC

void setup() 
{
  // put your setup code here, to run once:
  pinMode(8, OUTPUT); //Konfiguracja wyjscia pod diode
}

void loop() 
{
  // put your main code here, to run repeatedly:
  odczyt = analogRead(A0);  //Odczyt wartosci z fotorezystora
  if (odczyt<50)
  {
    digitalWrite(8, HIGH); //Jesli jest ciemno wlacz diode
  }else
  {
    digitalWrite(8, LOW);   //Jesli jest jasno wylacz
  }
  delay(50);
}
