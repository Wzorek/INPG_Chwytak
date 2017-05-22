#define przycisk A2
#define osx A0
#define osy A1
#define led1 5
#define led2 6
#define led3 9
#define led4 10

int x=0;
int y=0;
int brightness = 0;
const int midx = 500;
const int midy = 511;

void setup() {
  // put your setup code here, to run once:
  pinMode(przycisk, INPUT);
  digitalWrite(przycisk, HIGH);
  pinMode(osx, INPUT);
  pinMode(osy, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  x=analogRead(osx);
  y=analogRead(osy);
  if(x>midx)
  {
    x=(x-midx)/2;
    analogWrite(led1, x); 
  }else
  {
    x=-(x-midx)/2;
    analogWrite(led2, x);
  }
  if(y>midy)
  {
    y=(y-midy)/2;
    analogWrite(led3, y); 
  }else
  {
    y=-(y-midy)/2;
    analogWrite(led4, y);
  }
}
