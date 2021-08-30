#include <iostream>
#include <bits/stdc++.h>
#include <stdlib.h>
#include <string>
#include <cstring>
#include <vector>
using namespace std;

using std::cout;

/*
 
 nome primeiro paragrafo com o nome
  cnfInformation tem as informacoes como quantas entradas e quantas colunas
  valores == quantos valores 
  clonunas quantoas colunas , fazenbdo uma alocacao 
  

*/

void tokenize(std::string const &str, const char delim,
              std::vector<std::string> &out)
{
    size_t start;
    size_t end = 0;

    while ((start = str.find_first_not_of(delim, end)) != std::string::npos)
    {
        end = str.find(delim, start);
        out.push_back(str.substr(start, end - start));
    }
}

int main()
{
    clock_t start, end;
    start = clock();

    std ::string nome;
    std ::string cnfIntruction;
    getline(cin, nome);
    string *receber;
    int valores = 0;
    int colunas = 0;
    int i = 0;
    //std::cout<<nome.find("C")<<endl;
    if (nome.find("c") != std::string::npos)
    {
        std::cout << "funciona 1" << endl;
        getline(cin, cnfIntruction);
        if (cnfIntruction.find("p cnf") != std::string::npos)
        {

            std::vector<std::string> out;
            tokenize(cnfIntruction, ' ', out);
            for (auto &s : out)
            {
                // std::cout << s << std::endl;
                if (i == 2)
                {
                    valores = std::stoi(s);
                }
                else if (i == 3)
                {
                    colunas = std::stoi(s);
                }
                i++;
            }

            std::cout << "valores ====" << valores << endl;
            std::cout << "colunas ====" << colunas << endl;

            string *tamanhoT = new string[colunas];

            for (int i = 0; i < colunas + 1; i++)
            {

                getline(cin, tamanhoT[i]);

                std::cout << tamanhoT[i] << endl;
            }
        }
    }

    end = clock();
    double time_taken = double(end - start) / double(CLOCKS_PER_SEC);
    cout << "\nTempo de execucao -> " << fixed
         << time_taken << setprecision(5);
    cout << " sec " << endl;
    return 0;
}