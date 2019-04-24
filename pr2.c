Задание: разработать схему алгоритма и программу на языке C составляющую таблицу значений функции при изменяющимися параметре  A и аргументе  X, переменной b.
Изменение параметра и аргумента: 
Аргумент X принимает M значений

Параметра A изменяется от значения An, N раз с шагом da

Уравнение:	
Переменная b равна корню уравнения 
2*ln(x)-1(1/x)=0
Функция: Y=(a*b*sin^2(x))/sqrt(x)

Код программы:
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <conio.h>
#define EPS 1e-6                   // точность нахождения корня уравнения;
#define TEN 10                      

float equation(float x) { return 2*log(x)-(1/x); }

float root(float l, float r, float (*eq)(float)){
    float c;
    while( (r - l) > EPS ) {
       c = ( l + r ) / 2;                 // вычисляем середину отрезка;
       if( eq(c) * eq(r) < 0 )      // узнаем, в какой из частей;
           l = c;                             // находится искомый корень;
       else
           r = c;
    }
    return l;
}
int main()
{

    int n=5, m=3;
    float Xn=0;
    float An=1, Ak=3, da=0.8;
    float fun;
    int Arr[]={1, 2, 4, 5, 6, 7, 8, 9, 10, 11};

    float x, a = An, b = root(0, 10, equation);

    printf("Dlya zadannoi tochnosti %f  na intervale (0, 10) koren uravnenya: %f\n", EPS, b);
    printf("Provedem proverku:\nPodstavim %f v uravnenie.\nOtvet: %f\n",b, equation(b));

    int i=0, j=0;
    for (i=0;i<n;i++) {
        printf("\nA = %f\n",a);                  //выводим на экран параметр А

          for (j=0;j<m;j++){
           printf("X= %f",x);                            //выводим на экран аргумент Х

             x=Arr[j];

             fun=(a*b*pow(sin(x),2))/pow(x,1/2);

             printf("       Result:%f\n",fun);        //выводим на экран результат

        }
        a = a + da;
    }
   getch();
    return 0;
}
