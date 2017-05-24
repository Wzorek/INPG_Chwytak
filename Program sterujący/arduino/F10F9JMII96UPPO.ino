
String message;
  int counter = 5;
  int received = 180;
  double relaisTime = 0;
  double oldTime = 0;
  
  int led1State = 0;
  int led2State = 0;
  int led3State = 0;
  
  int brightness = 80;
  
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

pinMode(13,OUTPUT);
pinMode(6, OUTPUT);

digitalWrite(13,HIGH);
}

void loop() {

  // put your main code here, to run repeatedly:

   while(Serial.available()) {

     received = Serial.read();
 // message+=char(Serial.read());
 
    
     if(received == 187){
       Serial.write(received);

       
       delay(250);
       
       digitalWrite(13,LOW);
       delay(100);
       Serial.println("RESTARDED");
       digitalWrite(13,HIGH);      
     }
     else if(received == 1){
       
       led1State = 1;
       led2State = 0;
       led3State = 0;
  

                           
     }
          else if(received == 2){
            
       led1State = 1;
       led2State = 1;
       led3State = 0;

                           
     }
      else if(received == 3){
       led1State = 1;
       led2State = 1;
       led3State = 1;                        
                           
     }
      else if(received == 0){
       
       led1State = 0;
       led2State = 0;
       led3State = 0;  
                           
     }
     
     if(received >= 15 && received <=168){
  
       brightness = received;
            Serial.println(received);
            
          delay(10);
          MakeLight();
}
   MakeLight(); 
   
     }
     
  
}
 
  void MakeLight(){
  
  
      if(led1State == 1){
    analogWrite(3,brightness);


    }
     else if(led1State == 0){
        
          analogWrite(3,0);
        
        }
    if(led2State == 1){

     analogWrite(5,brightness);

        }
        else if(led2State == 0){
        
          analogWrite(5,0);
        
        }
     if(led3State == 1){

    analogWrite(6,brightness);

          delay(10);
     }
 else if(led3State == 0){
        
          analogWrite(6,0);
        
        }
  
  


                        
    
 



}
