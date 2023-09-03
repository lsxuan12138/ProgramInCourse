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
    int n;
	 //分块
    int q=M/4;
    for(int i = 0;i<10;i++) {
        for(int p =1;p<=M;p+=q){
            //算出line1
            for(int j = p;j<M-1&&j<(p+q);j++){
                n=1*M+j;
                line1[j] = ((img[n-M] + img[n+M]) + (img[n-1] + img[n+1]))>>2;
            }
            int k;
            for(k = 2;k<N-1;k++){
                //算出line2
                for(int j = p;j<M-1&&j<(p+q);j++){
                    n = k * M +j;
                    line2[j] = ((img[n-M] + img[n+M]) + (img[n-1] + img[n+1]))>>2;
                }
                //改变原值
                for(int j = p;j<M-1&&j<(p+q);j++){
                    img[(k-1)*M+j] = line1[j];
                }
                //交换line1，line2
                Data *temp = line1;
                line1 = line2;
                line2 = temp;
            }
            //改变原值,最后一行在双重循环中未改变
            for(int j = p;j<M-1&&j<(p+M/3);j++){
                img[(k-1)*M+j] = line1[j];
            }

        }

    }

    //记录结束时间
    int t_end = getMiTime();
    printf("Using time: %d ms",(t_end-t_start)/10);
    free(img);
    free(line1);
    free(line2);
}
