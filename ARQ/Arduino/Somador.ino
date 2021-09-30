// C++ code
//

void setup()
{
    pinMode(LED_BUILTIN, OUTPUT);
    pinMode(10, OUTPUT);
    pinMode(11, OUTPUT);
    pinMode(12, OUTPUT);
    pinMode(13, OUTPUT);
    Serial.begin(9600);
  	int tamanho=0;
   	Serial.println("Quantos testes");
   while(Serial.available()==0);
  	
	tamanho=Serial.parseInt();
  String*teste=(String*)malloc(sizeof(String)*(2+tamanho));
  
 int i=3;
    
	teste[0]="0";
    teste[1]="0";
    teste[2]="0";
  for(;i<(tamanho+3);i++){
  while(Serial.available()==0);
  teste[i]=Serial.readString();
  
  }
  
  for(int t=0;t<i;t++){
    if(teste[t].charAt(2)=="0"){
    //A'
    A = A*(-1);
    }
    if(teste[t].charAt(2)=="1"){
    //nAoB
    }
    if(teste[t].charAt(2)=="2"){
    //AnB
    }
    if(teste[t].charAt(2)=="3"){
    //zeroL
    }
    if(teste[t].chatAt(2)=="4"){
    //nAeB
    }
    if(teste[t].chatAt(2)=="5"){
    //Bn
    }
    if(teste[t].chatAt(2)=="6"){
    //AxB
    }
    if(teste[t].chatAt(2)=="7"){
    //ABn
    }
    if(teste[t].chatAt(2)=="8"){
    //AnoB
    }
    if(teste[t].chatAt(2)=="9"){
    //nAxB
    }
    if(teste[t].chatAt(2)=="A"){
    //B
    }
    if(teste[t].chatAt(2)=="B"){
    //AB
    }
    if(teste[t].chatAt(2)=="C"){
    //umL
    }
    if(teste[t].chatAt(2)=="D"){
    //AoBn
    }
    if(teste[t].chatAt(2)=="E"){
    //AoB
    }
    if(teste[t].chatAt(2)=="F"){
    //A
    }

  }
  
}

void loop(){}

