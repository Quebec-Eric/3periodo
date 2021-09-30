#ifndef NO_HPP
#define NO_HPP

#include <iostream>
#include <string>

using namespace std;

class No
{
protected:
public:
    string expre;
    bool item;
    No *esq;
    No *dir;
    No(bool item, string expre);
    ~No();
};

    No::No(bool item, string expre)
    {
        this->item = item;
        this->expre = expre;
        this->dir = NULL;
        this->esq = NULL;
    }

#endif