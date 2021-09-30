#ifndef ARVORE_HPP
#define ARVORE_HPP

#include <iostream>
#include <string>
#include "no.hpp"
#

using namespace std;

class ArvoreBina
{

protected:
    No *raiz;
    string pesquisa(string, bool, No *);

public:
    ArvoreBina();
    string pesquisa(string, bool);
    void inserir(string exp, No *temp, bool item);
    void inserir(string, bool);
    void printArvore();
    void printArvore(No *);
    //string mostrarNatela(No *);
};

ArvoreBina::ArvoreBina()
{
    raiz = NULL;
}

/*void ArvoreBina::inserir(string exp, bool item)
{
    inserir(exp, item, raiz);
}

void ArvoreBina::inserir(string exp, No *temp, bool item)
{
    if (temp == NULL)
    {
        cout << "cheguei1" << endl;
        temp = new No(item, exp);
    }
    else
    {
        if (exp < temp->item)
        {
            cout << "cheguei2" << endl;
            inserir(exp, temp->esq);
        }
        else if(exp > temp->item)
        {
            inserir(exp, temp->dir);
        }
    }
}
*/

void ArvoreBina::printArvore(No *t)
{
    if (t != NULL)
    {
        cout << "cheguei" << endl;
        std::cout << "item==" << t->item << " Esxpre" << t->expre << endl;
        printArvore(t->esq);
        printArvore(t->dir);
    }
}

void ArvoreBina::printArvore()
{
    printArvore(raiz);
}

#endif