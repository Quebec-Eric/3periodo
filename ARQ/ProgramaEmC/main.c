#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void pegarEntrada();
char *tirarZeroFinal(char *linha);
void colocarNoLocal();

int main()
{

    pegarEntrada();

    return 0;
}

void pegarEntrada()
{
    char entrada[20];
    fgets(entrada, 20, stdin);
    char *parte;
    int i = 0;
    int valores = 0;
    int clausulas = 0;
    parte = strtok(entrada, " ");

    while (parte != NULL)
    {
        if (i == 2)
        {
            valores = atof(parte);
        }
        else if (i == 3)
        {
            clausulas = atof(parte);
        }
        i++;
        parte = strtok(NULL, " ");
    }

    char *linhas[clausulas];
    for (int i = 0; i < clausulas; i++)
    {
        linhas[i] = (char *)calloc(30, sizeof(char));
    }

    for (int passarLinhas = 0; passarLinhas < clausulas; passarLinhas++)
    {
        fgets(linhas[passarLinhas], 30, stdin);
        linhas[passarLinhas] = tirarZeroFinal(linhas[passarLinhas]);
    }

    for (int passarLinhas = 0; passarLinhas < clausulas; passarLinhas++)
    {
        printf("%s\n", linhas[passarLinhas]);
    }

    colocarNoLocal(linhas, clausulas);
}

char *tirarZeroFinal(char *linha)
{
    int i = 0;
    char tt[] = " 0 ";
    int local = strcspn(linha, "\0");
    char *linhas = (char *)calloc(30, sizeof(char));
    for (int i = 0; i < local - 2; i++)
    {
        linhas[i] = linha[i];
    }
    return linhas;
}

int cancel(char *linhas)
{

    return 0;
}

void colocarNoLocal(char **linhas, int tamanho2)
{
    char *resposta[tamanho2];

    int z = 0;
    for (int i = 0; i < tamanho2 - 1; i++)
    {
        if (linhas[i][0] == linhas[i + 1][0])
        {
            for (int t = 0; t < sizeof(linhas[i]) - 1; t++)
            {
                resposta[z] += linhas[i][t];
            }
        }
        else
        {

            printf("\n");
            z++;
        }
    }

    for (int i = 0; i < z; i++)
    {
        printf("%s\n", resposta[i]);
    }
}
