#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
#define Data long long
#define N 1920
#define M 1080
//typedef long long Data;

int getMiTime() {
    SYSTEMTIME currentTime;
    GetSystemTime(&currentTime);

    return (3600 * currentTime.wHour + 60 * currentTime.wMinute + currentTime.wSecond) * 1000 + currentTime.wMilliseconds;
}
int main()
{
    if(sizeof(Data)!=8){
        printf("DataClass Error\n");
        exit(-1);
    }
    //生成随机数组
    Data *img = (Data *)malloc(M * N * sizeof(Data));
    srand((unsigned)time(NULL));
    for(int i = 0;i<N;i++){
        for(int j = 0;j<M;j++){
            img[i * M +j]=rand();

        }
    }

    Data *line1 =(Data*) malloc(M*sizeof(Data));
    Data *line2 =(Data*) malloc(M*sizeof(Data));

    //记录开始时间
    int t_start = getMiTime();
    for(int i = 0;i<10;i++) {
        //算出line1
        for(int j = 1;j<M-1;j++){
            line1[j] = (img[(1-1)*M+j] + img[(1+1)*M+j] + img[1*M+j-1] + img[1*M+j+1])/4;
        }
        int k;
        for(k = 2;k<N-1;k++){
            //算出line2
            for(int j = 1;j<M-1;j++){
                line2[j] = (img[(k-1)*M+j] + img[(k+1)*M+j] + img[k*M+j-1] + img[k*M+j+1])/4;
            }
            //改变原值
            for(int j = 1;j<M-1;j++){
                img[(k-1)*M+j] = line1[j];
            }
            //交换line1，line2
            Data *temp = line1;
            line1 = line2;
            line2 = temp;
        }
        //改变原值,最后一行在双重循环中未改变
        for(int j = 1;j<M-1;j++){
            img[(k-1)*M+j] = line1[j];
        }
    }

    //记录结束时间
    int t_end = getMiTime();
    printf("Using time: %d ms",(t_end-t_start)/10);
    free(img);
    free(line1);
    free(line2);
}