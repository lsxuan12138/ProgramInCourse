
#include <stdio.h>
#include <string.h>

#define N 6
#define N1 20

int main()
{
    char str[N1], min[N1], max[N1];
    int i;

    printf("������6���ַ�����\n");
    gets(min);  //�����1������С

    strcpy(max, min);//������󴮵�ֵҲΪmin

    //ѭ��������������������С�Ĵ������Ĵ��Ƚ�
    for (i = 2; i <= N; i++)
    {
        gets(str);
        if (strcmp(str, min) < 0)
            strcpy(min, str);
        if (strcmp(str, max) > 0)
            strcpy(max, str);
    }

    printf("The max string is: %s\n", max);
    printf("The min string is: %s\n", min);

    return 0;
}
