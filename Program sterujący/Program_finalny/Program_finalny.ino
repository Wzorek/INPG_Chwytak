    #include <SoftwareSerial.h>
     
    #define DEBUG true
    String angle = "";
    String joint1 = "";
    String joint2 = "";
    String grab = ""; 
    SoftwareSerial esp8266(2,3); // make RX Arduino line is pin 2, make TX Arduino line is pin 3.
                                 // This means that you need to connect the TX line from the esp to the Arduino's pin 2
                                 // and the RX line from the esp to the Arduino's pin 3
    #include <Servo.h>

Servo rotate;
Servo joint;
Servo grip;// create servo object to control a servo
    void setup()
    {
      Serial.begin(9600);
      esp8266.begin(115200); // your esp's baud rate might be different
      
      pinMode(11,OUTPUT);
      digitalWrite(11,LOW);

        rotate.attach(9);
        joint.attach (10);
        grip.attach (6);// attaches the servo on pin 9 to the servo object
      }
     
    void loop()
    {
      if(esp8266.available()>0) // check if the esp is sending a message 
      {
     
        
        if(esp8266.find("+IPD,"))
        {
         delay(1000); // wait for the serial buffer to fill up (read all the serial data)
         // get the connection id so that we can then disconnect
         //int connectionId = esp8266.read()-48; // subtract 48 because the read() function returns 
                                               // the ASCII decimal value and 0 (the first decimal number) starts at 48
              
         esp8266.find("="); // advance cursor to "pin="
         angle = esp8266.readStringUntil(':');  //Oczytuje dane do znaku konca linii i  zapisuje w zmiennej
         joint1 = esp8266.readStringUntil(':');
         joint2 = esp8266.readStringUntil(':');
         grab = esp8266.readStringUntil(' '); 
         Serial.println("a" + angle);
         Serial.println("j1" + joint1);
         Serial.println("j2" + joint2);       
         Serial.println("kkk" + grab); 
         
         zmiana(angle,joint1,joint2,grab);
         
         esp8266.print("AT+CIPSEND=0,3\r\n");
         delay(1000);
         esp8266.print("OKK");
         delay(1000);
         esp8266.print("AT+CIPCLOSE=0\r\n");
         delay(1000);
        }
      }
    }

     void zmiana(String angle, String joint1, String joint2, String grab)
{
      if(grab.toInt() == 1)
      {
          digitalWrite(11,HIGH);
      }else
      {
          digitalWrite(11,LOW);
      }    
      
      rotate.write(map(angle.toInt(),-90,90,30,150));
      joint.write(map(joint1.toInt(),0,180,30,150));
      grip.write (map(joint2.toInt(),0,180,30,150));
}

