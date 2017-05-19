#define przycisk A2
#define osx A0
#define osy A1

int x=0;
int y=0;
const int midx=500;
const int midy=511;
const int center_hist=5;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(przycisk, INPUT);
  digitalWrite(przycisk, HIGH);
  pinMode(osx, INPUT);
  pinMode(osy, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  x=analogRead(osx);
  y=analogRead(osy);
  if(x>(midx+center_hist)||x<(midx+center_hist))
  {
    x=(x-midx)/16
  }else x=0;
  if(y>(midy+center_hist)||y<(midy+center_hist))
  {
    y=(y-midy)/16
  }else y=0;
  Serial.print(x);
  Serial.print(" ");  
  Serial.print(y);  
  Serial.print(" ");  
  Serial.println(digitalRead(przycisk));
}
