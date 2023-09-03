#include <stdio.h>
#include <stdlib.h>
#include <sys/timeb.h>
#include <unistd.h>
#define Data long long
#define N 1920
#define M 1080
long long getSystemTime(){
    struct timeb t;
    ftime(&t);
    return 1000 *t.time +t.millitm;
}
     
int main()
{
    if(sizeof(Data)!=8){
        printf("DataClass Error\n");
        exit(-1);
    }
    //生成随机数组
    Data *img = (Data *)malloc(M * N * sizeof(Data));
    //srand((unsigned)time(NULL));
    for(int i = 0;i<N;i++){
        for(int j = 0;j<M;j++){
            img[i * M +j]=rand();

        }
    }

    Data *line1 =(Data*) malloc(M*sizeof(Data));
    Data *line2 =(Data*) malloc(M*sizeof(Data));

    //记录开始时间
    //int t_start = getMiTime();
    long long start=getSystemTime();
    int n;
    for(int i = 0;i<10;i++) {
        //算出line1
        for(int j = 1;j<M-1;j++){
            n=1*M+j;
            line1[j] = ((img[n-M] + img[n+M]) + (img[n-1] + img[n+1]))>>2;
        }
        int k;
        for(k = 2;k<N-1;k++){
            //算出line2
            for(int j = 1;j<M-1;j+=4){
                n = k * M +j;
                line2[j] = ((img[n-M] + img[n+M]) + (img[n-1] + img[n+1]))>>2;
                line2[j+1] = ((img[n-M+1] + img[n+M+1]) + (img[n] + img[n+2]))>>2;
                line2[j+2] = ((img[n-M+2] + img[n+M+2]) + (img[n+1] + img[n+3]))>>2;
                line2[j+3] = ((img[n-M+3] + img[n+M+3]) + (img[n+2] + img[n+4]))>>2;
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
    //int t_end = getMiTime();
    long long end=getSystemTime();
    printf("Using time: %lld ms\n",(end-start)/10);
    free(img);
    free(line1);
    free(line2);
}