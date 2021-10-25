// C++ code
//
String vetroGlobal[100];

String fazerBina(char aH)
{

    String aD = "";
    if (aH == '1')
    {
        aD = "0001";
    }
    else if (aH == '2')
    {
        aD = "0010";
    }
    else if (aH == '3')
    {
        aD = "0011";
    }
    else if (aH == '4')
    {
        aD = "0100";
    }
    else if (aH == '5')
    {
        aD = "0101";
    }
    else if (aH == '6')
    {
        aD = "0110";
    }
    else if (aH == '7')
    {
        aD = "0111";
    }
    else if (aH == '8')
    {
        aD = "1000";
    }
    else if (aH == '9')
    {
        aD = "1001";
    }
    else if (aH == 'A')
    {
        aD = "1010";
    }
    else if (aH == 'B')
    {
        aD = "1011";
    }
    else if (aH == 'C')
    {
        aD = "1100";
    }
    else if (aH == 'D')
    {
        aD = "1101";
    }
    else if (aH == 'E')
    {
        aD = "1110";
    }
    else
    {
        aD = "1111";
    }
    return aD;
}

void funcao_AN(char a)
{
    String respostaA = fazerBina(a);

    Serial.println(respostaA);
    int tre = 13;
    for (int i = 0; i < 4; i++)
    {
        if (respostaA.charAt(i) == '0')
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);

} //0

void funcao_nAoB(char a, char b)
{
    String respostaA = fazerBina(a);
    String respostaB = fazerBina(b);
    String pA;
    String pB;
    for (int i = 0; i < 4; i++)
    {
        if (respostaA.charAt(i) == '0')
        {
            pA += '1';
        }
        else
        {
            pA += '0';
        }

        if (respostaB.charAt(i) == '0')
        {
            pB += '1';
        }
        else
        {
            pB += '0';
        }
    }
    for (int i = 0; i < 4; i++)
    {

        if (pB.charAt(i) == pA.charAt(i) && pB.charAt(i) == '0')
        {
        }
        else
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);
}

void funcao_AnB(char a, char b)
{
    String respostaA = fazerBina(a);
    String respostaB = fazerBina(b);
    String neGA;
    for (int i = 0; i < 4; i++)
    {
        if (respostaA.charAt(i) == '0')
        {
            neGA += '1';
        }
        else
        {
            neGA += '0';
        }
    }

    for (int i = 0; i < 4; i++)
    {
        if (neGA.charAt(i) == '1' && respostaB.charAt(i) == '1')
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);

} //2

void funcao_zeroL()
{
    Serial.println("Entrou 3");

    delay(2000);
} //3

void funcao_nAeB(char a, char b)
{
    String respostaA = fazerBina(a);
    String respostaB = fazerBina(b);
    String neGB;
    for (int i = 0; i < 4; i++)
    {
        if (respostaB.charAt(i) == '0')
        {
            neGB += '1';
        }
        else
        {
            neGB += '0';
        }
    }

    for (int i = 0; i < 4; i++)
    {
        if (neGB.charAt(i) == '1' && respostaA.charAt(i) == '1')
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);

} //4

void funcao_Bn(char b)
{
    String respostaB = fazerBina(b);
    for (int i = 0; i < 4; i++)
    {
        if (respostaB.charAt(i) == '0')
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);
} //5

void funcao_AxB(char a, char b)
{
    String respostaA = fazerBina(a);
    String respostaB = fazerBina(b);

    for (int i = 0; i < 4; i++)
    {
        if (respostaA.charAt(i) == '1' && respostaB.charAt(i) == '1')
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);

} //6

void funcao_ABn(char a, char b)
{
    String respostaA = fazerBina(a);
    String respostaB = fazerBina(b);
    String neGB;
    for (int i = 0; i < 4; i++)
    {
        if (respostaB.charAt(i) == '0')
        {
            neGB += '1';
        }
        else
        {
            neGB += '0';
        }
    }

    for (int i = 0; i < 4; i++)
    {
        if (neGB.charAt(i) == '1' && respostaA.charAt(i) == '1')
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);
} //7

void funcao_AnoB(char a, char b)
{
    String respostaA = fazerBina(a);
    String respostaB = fazerBina(b);
    String pA;

    for (int i = 0; i < 4; i++)
    {
        if (respostaA.charAt(i) == '0')
        {
            pA += '1';
        }
        else
        {
            pA += '0';
        }
    }

    for (int i = 0; i < 4; i++)
    {

        if (respostaB.charAt(i) == pA.charAt(i) && respostaB.charAt(i) == '0')
        {
        }
        else
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);
} //8

void funcao_nAxB(char a, char b)
{
    String respostaA = fazerBina(a);
    String respostaB = fazerBina(b);
    String pA;
    String pB;
    for (int i = 0; i < 4; i++)
    {
        if (respostaA.charAt(i) == '0')
        {
            pA += '1';
        }
        else
        {
            pA += '0';
        }

        if (respostaB.charAt(i) == '0')
        {
            pB += '1';
        }
        else
        {
            pB += '0';
        }
    }

    for (int i = 0; i < 4; i++)
    {
        if (respostaB.charAt(i) == pA.charAt(i) && respostaB.charAt(i) == '1')
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);
} //9
void funcao_B(char b)
{
    String respostaB = fazerBina(b);
    for (int i = 0; i < 4; i++)
    {
        if (respostaB.charAt(i) == '1')
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);
} //A

void funcao_AB(char a, char b)
{
    String respostaA = fazerBina(a);
    String respostaB = fazerBina(b);

    for (int i = 0; i < 4; i++)
    {

        if (respostaB.charAt(i) == respostaA.charAt(i) && respostaB.charAt(i) == '0')
        {
        }
        else
        {
            digitalWrite(13 - i, HIGH);
        }
    }
    delay(2000);
} //B

void funcao_umL()
{
    Serial.println("Entrou c");
    for (int i = 0; i < 4; i++)
    {
        digitalWrite(13 - i, HIGH);
    }
    delay(2000);
} //C

void funcao_AoBn(char a, char b)
{
    String respostaA = fazerBina(a);
    String respostaB = fazerBina(b);
    String pB;
    for (int i = 0; i < 4; i++)
    {
        if (respostaB.charAt(i) == '0')
        {
            pB += '1';
        }
        else
        {
            pB += '0';
        }
    }

    for (int i = 0; i < 4; i++)
    {
        if (pB.charAt(i) == resposaA.charAt(i) && pB.charAt(i) == '0')
        {
        }
        else
        {
            digitalWrite(13 - i, HIGH);
        }
    }

} //D

void funcao_AoB(char a, char b)
{

    String respostaA = fazerBina(a);
    String respostaB = fazerBina(b);

    for (int i = 0; i < 4; i++)
    {
        if (respostaB.charAt(i) == resposaA.charAt(i) && respostaB.charAt(i) == '0')
        {
        }
        else
        {
            digitalWrite(13 - i, HIGH);
        }
    }

} //E

void funcao_A(char a)
{
    String respostaA = fazerBina(a);

    for (int i = 0; i < 4; i++)
    {
        if (respostaA.charAt(i) == '1')
        {
            digitalWrite(13 - i, HIGH);
        }
    }

} //F

void fazerTudo(String resultado)
{
    digitalWrite(13, LOW);
    digitalWrite(12, LOW);
    digitalWrite(11, LOW);
    digitalWrite(10, LOW);
    vetroGlobal[0] = resultado.charAt(2);
    vetroGlobal[1] = resultado.charAt(0);
    vetroGlobal[2] = resultado.charAt(1);
    Serial.println();
    Serial.print("[ ");
    for (int t = 0; t <= 5; t++)
    {
        Serial.print(vetroGlobal[t] + "\t");
    }
    Serial.print(" ]");
    Serial.println();
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
        funcao_B(resultado.charAt(1));
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

void setup()
{
    pinMode(LED_BUILTIN, OUTPUT);
    pinMode(10, OUTPUT);
    pinMode(11, OUTPUT);
    pinMode(12, OUTPUT);
    pinMode(13, OUTPUT);
    Serial.begin(9600);
    vetroGlobal[0] = "0";
    vetroGlobal[1] = "0";
    vetroGlobal[2] = "0";
    int i = 0;
    Serial.println("Quantas  Operacoes");
    while (Serial.available() == 0)
        ;
    int receber = Serial.parseInt();

    do
    {

        Serial.println("Operacao");
        while (Serial.available() == 0)
            ;

        vetroGlobal[i + 3] = Serial.readString();
        i++;

    } while (i < receber);
    Serial.println();
    Serial.print("[ ");
    for (int t = 0; t <= receber + i; t++)
    {
        Serial.print(vetroGlobal[t] + "\t");
    }
    Serial.print(" ]");
    Serial.println();

    for (int t = 3; t <= receber + i; t++)
    {
        fazerTudo(vetroGlobal[t]);
    }
}

void loop()
{
}
