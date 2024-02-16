#include <math.h>
#include <stdio.h>

#define N = 4 // 物品数量
#define W = 5 // 背包容量


int max(int a,int b){
    return a > b ? a : b;
}

int main(){
    int v[] = {0,2,3,4,6};
    int w[] = {0,1,2,3,4};

    int f[N + 1][W + 1] = {};

    int i,j;
    for(i = 1;i < N + 1;i ++){
           for(j = 1;j < W + 1;j ++){
                if(j >= w[i]){
                    f[i][j] = max( f[i-1][j] , f[i-1][j - w[i]] + v[i])
                }else{
                    f[i][j] = f[i-1][j];
                }
           }
    }


}