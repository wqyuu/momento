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

void queen(){

    int i;
    for(i = 1;i <= N; i ++){
      q[i] = 0;
    }
    int answer = 0;

    int j = 1; // 皇后序号/行序号
    while(j >= 1){
        q[j] = q[j] + 1; // 放到棋盘下个位置
        while(q[j] <= N && !check(q[j])){
            q[j] = q[j] + 1;
        }
        // 超过棋盘未找到合适位置，将上一步回溯
        if(q[j] > N){
            q[j] = 0;
            j --;
        }else{
            if(j == N){
                 answer = answer + 1;
                 for(i = 1;i <= N; i++){
                     printf("方案%d:",q[i]);
                 }
            }else{
                 // 找到合适位置，走下一步
                 j = j + 1;
            }

        }
    }

}