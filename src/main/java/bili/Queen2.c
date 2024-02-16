#include <math.h>
#include <stdio.h>

#define N 4

int q[N + 1];

int check(int j){
    int i;
    for(i = 0;i < j;i ++){
        if(q[i] == q[j] || abs(i - j) == abs(q[i] - q[j])){
            return 0;
        }
    }
    return 1;
}

void queen(int j){

    int i;
    for(i = 1;i <= N; i ++){
      q[j] = i;
      if(check(j)){
        if(j == N){
            answer = answer + 1;
            for(i = 1;i <= N; i++){
                printf("方案%d:",q[i]);
            }
         }else{
            queen(j+1);
         }
      }

    }

}

void main(){

}