const int ledPin = 12;
char data;
int flag=0;

void setup()

{
	pinMode(ledPin, OUTPUT);// Set pin 5 as an output
	Serial.begin(9600); 	// Start serial communication
}

void loop()
{
	
	// Check serial connection
    if (Serial.available() > 0) {
        // read incoming data:
        data = Serial.read();
        flag=0;
    }
    
    // If data is 'A' turn off the LED
    if (data == 'A'){
    	digitalWrite(ledPin, LOW);
    	if (flag == 0){
    		Serial.println("LED is OFF");
    		flag=1; //print this message only one time!
    	}
    }
    // else if is 'B' turn on the LED
    else if (data == 'B'){
    	digitalWrite(ledPin, HIGH);
    	if (flag == 0){
    		Serial.println("LED is ON");
    		flag=1; //print this message only one time!
    	}	
    }
	
}