Дана строка текста, состоящая из слов и чесел. Числа представленные в 8-ричной системи счисления языка Си 
перенести в начало строки, представленные в 10 с.с. перенести в её конец.
Исх. AB C10 071 21 C8 021
Результат: 071 021 AB C10 21 C8
#include <iostream>
#include "conio.h"
#include <locale>
#include <windows.h>
#include <string>
#include <windows.h>
using namespace std; 
bool is8number(char *str)
{
    bool b=false;
    /*
    for(int i=0;i<strlen(str);i++)
    {
       if((str[i]<'0')||(str[i]>'8'))
        b=false;
    }
    */
    if (str[0] == '0'){
        b=true;
    }
    return b;
}
bool is10number(char *str)
{
   bool b=true;
    for(int i=0;i<strlen(str);i++)
    {
       if((str[i]<'0')||(str[i]>'9'))
        b=false;
    }
    return b;
}
int main(int argc, char* argv[])
{
        char a[900];
        cout<<"Vvedite  stroku "<<endl;
        int count=0;
        int bukva;
        while ((bukva=getch())&&(bukva!=13))
        {
                cout<<(char)bukva;
                a[count]=(char)bukva;
                count++;
        }
        a[count]='\0';
        char **strings1 = new char* [10];
          char **strings2 = new char* [10];
            char **strings3 = new char* [10];
        int kol1=0,kol2=0,kol3=0;
        char *pstr;
        for(int i=0; i<10; i++)
        {
            strings1[i] = new char[25];
             strings2[i] = new char[25];
              strings3[i] = new char[25];
        }
        pstr=strtok(a," ");
        while (pstr!=NULL)
        {
        if(is8number(pstr))
        {
        strcpy(strings1[kol1],pstr);
        kol1++;
        }
        else if(!is8number(pstr))
        {
        strcpy(strings3[kol3],pstr);
        kol3++;
        }
        else
        {
        strcpy(strings2[kol2],pstr);
        kol2++;
        }
        pstr=strtok(NULL," ");
        }
        a[0]='\0';
        for (int i=0;i<kol1;i++)
        {
            strcat(a,strings1[i]);
            strcat(a," ");
        }
          for (int i=0;i<kol2;i++)
        {
            strcat(a,strings2[i]);
            strcat(a," ");
        }
          for (int i=0;i<kol3;i++)
        {
            strcat(a,strings3[i]);
            strcat(a," ");
        }
        cout<<endl<<a;
        getch();
        return 0;
}
