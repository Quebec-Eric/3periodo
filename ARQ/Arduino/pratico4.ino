// C++ code
//

void funcao_AN(char a)
{
  int t= a-'0';
  Serial.println("Entrou 0");
  Serial.println(t,BIN);
} //0

void funcao_nAoB(char a, char b)
{
  Serial.println("Entrou 1");
  Serial.println(a);
  Serial.println(b);
} //1

void funcao_AnB(char a, char b)
{
  Serial.println("Entrou 2");
  Serial.println(a);
  Serial.println(b);
} //2

void funcao_zeroL()
{
  Serial.println("Entrou 3");
} //3

void funcao_nAeB(char a, char b)
{
  Serial.println("Entrou 4");
  Serial.println(a);
  Serial.println(b);
} //4

void funcao_Bn(char b)
{
  Serial.println("Entrou 5");
  Serial.println(b);
} //5

void funcao_AxB(char a, char b)
{
  Serial.println("Entrou 6");
  Serial.println(a);
  Serial.println(b);
} //6

void funcao_ABn(char a, char b)
{
  Serial.println("Entrou 7");
  Serial.println(a);
  Serial.println(b);
} //7

void funcao_AnoB(char a, char b)
{
  Serial.println("Entrou 8");
  Serial.println(a);
  Serial.println(b);
} //8

void funcao_nAxB(char a, char b)
{
  Serial.println("Entrou 9");
  Serial.println(a);
  Serial.println(b);
} //9
void funcao_B(char b)
{
  Serial.println("Entrou A");

  Serial.println(b);
} //A

void funcao_AB(char a, char b)
{
  Serial.println("Entrou B");
  Serial.println(a);
  Serial.println(b);
} //B

void funcao_umL() { Serial.println("Entrou c"); } //C

void funcao_AoBn(char a, char b)
{
  Serial.println("Entrou D");
  Serial.println(a);
  Serial.println(b);
} //D

void funcao_AoB(char a, char b)
{
  Serial.println("Entrou E");
} //E

void funcao_A(char a)
{
  Serial.println("Entrou F");
} //F

void setup()
{
  pinMode(LED_BUILTIN, OUTPUT);
  pinMode(10, OUTPUT);
  pinMode(11, OUTPUT);
  pinMode(12, OUTPUT);
  pinMode(13, OUTPUT);
  Serial.begin(9600);

  Serial.println("Valor");
  String resultado = "";
  while (Serial.available() == 0)
    ;
  resultado = Serial.readString();
  if (resultado[resultado.length() - 1] == '0')
  {
    funcao_AN(resultado.charAt(0));
  }
  else if (resultado[resultado.length() - 1] == '1')
  {
    funcao_nAoB(resultado.charAt(0), resultado.charAt(1));
  }
  else if (resultado[resultado.length() - 1] == '2')
  {
    funcao_AnB(resultado.charAt(0), resultado.charAt(1));
  }
  else if (resultado[resultado.length() - 1] == '3')
  {
    //falta essa
  }
  else if (resultado[resultado.length() - 1] == '4')
  {
    funcao_nAeB(resultado.charAt(0), resultado.charAt(1));
  }
  else if (resultado[resultado.length() - 1] == '5')
  {

    funcao_Bn(resultado.charAt(1));
  }
  else if (resultado[resultado.length() - 1] == '6')
  {
    funcao_AxB(resultado.charAt(0), resultado.charAt(1));
  }
  else if (resultado[resultado.length() - 1] == '7')
  {
    funcao_ABn(resultado.charAt(0), resultado.charAt(1));
  }
  else if (resultado[resultado.length() - 1] == '8')
  {
    funcao_AnoB(resultado.charAt(0), resultado.charAt(1));
  }
  else if (resultado[resultado.length() - 1] == '9')
  {
    funcao_nAxB(resultado.charAt(0), resultado.charAt(1));
  }
  else if (resultado[resultado.length() - 1] == 'A')
  {
    funcao_B( resultado.charAt(1));
  }
  else if (resultado[resultado.length() - 1] == 'B')
  {
    funcao_AB(resultado.charAt(0), resultado.charAt(1));
  }
  else if (resultado[resultado.length() - 1] == 'C')
  {
   
  }
  else if (resultado[resultado.length() - 1] == 'D')
  {
    funcao_AoBn(resultado.charAt(0), resultado.charAt(1)); 
  }
  else if (resultado[resultado.length() - 1] == 'E')
  {
     funcao_AoB(resultado.charAt(0), resultado.charAt(1));
  }
  else
  {
    funcao_A(resultado.charAt(0));
  }
}

void loop() {}
