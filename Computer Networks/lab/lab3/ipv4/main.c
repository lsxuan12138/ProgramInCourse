/*
* THIS FILE IS FOR IP TEST
*/
#include <string.h>
// system support
//todo
int STUD_IP_TEST_CHECKSUM_ERROR =1;//IP 校验和出错
int STUD_IP_TEST_TTL_ERROR =2;//TTL 值出错
int STUD_IP_TEST_VERSION_ERROR =3;//IP 版本号错
int STUD_IP_TEST_HEADLEN_ERROR =4;//头部长度错
int STUD_IP_TEST_DESTINATION_ERROR =5;//目的地址错
//#include "sysInclude.h"
typedef unsigned char byte;

extern void ip_DiscardPkt(char* pBuffer,int type);

extern void ip_SendtoLower(char*pBuffer,int length);

extern void ip_SendtoUp(char *pBuffer,int length);

extern unsigned int getIpv4Address();
unsigned int getCheckSum(char *head);
// implemented by students

int stud_ip_recv(char *pBuffer,unsigned short length)
{
    byte tempByte = pBuffer[0];
    byte version = (tempByte&0xf0)>>4;
    byte headLength = tempByte & 0x0f;
    byte TTL = pBuffer[8];
    unsigned int destAddr = (pBuffer[16]<<24)+(pBuffer[17]<<16)+(pBuffer[18]<<8)+pBuffer[19];
    unsigned int checksum = (pBuffer[10]<<8)+pBuffer[11];
    if((version^0x04)!=0x00) {
        ip_DiscardPkt(pBuffer, STUD_IP_TEST_VERSION_ERROR);
        goto error;
    }
    if(!(headLength>=0x5&&headLength<=0xc)){
        ip_DiscardPkt(pBuffer,STUD_IP_TEST_HEADLEN_ERROR);
        goto error;
    }
    if(TTL <= 0x0){
        ip_DiscardPkt(pBuffer,STUD_IP_TEST_TTL_ERROR);
        goto error;
    }
    if(getIpv4Address()!=destAddr){
        ip_DiscardPkt(pBuffer,STUD_IP_TEST_DESTINATION_ERROR);
        goto error;
    }
    if(checksum!=0){
        ip_DiscardPkt(pBuffer,STUD_IP_TEST_CHECKSUM_ERROR);
        goto error;
    }
    ip_SendtoUp(pBuffer+headLength,length-headLength);
    return 0;
    error:
    return 1;
}

int stud_ip_Upsend(char *pBuffer,unsigned short len,unsigned int srcAddr,
                   unsigned int dstAddr,byte protocol,byte ttl)
{
    unsigned short length=len+20;
    byte temp[length];
    memset(temp,0,length);
    temp[0]=0x45;
    temp[2]=(length&0xff00)>>8;
    temp[3]=length&0xff;
    temp[4]=0;
    temp[5]=0;
    temp[6]=0;
    temp[7]=0;
    temp[8]=ttl;
    temp[9]=protocol;
    temp[10]=0;
    temp[11]=0;
    temp[12]=(srcAddr&0xff000000)>>24;
    temp[13]=(srcAddr&0xff0000)>>16;
    temp[14]=(srcAddr&0xff00)>>8;
    temp[15]=(srcAddr&0xff);
    temp[16]=(dstAddr&0xff000000)>>24;
    temp[17]=(dstAddr&0xff0000)>>16;
    temp[18]=(dstAddr&0xff00)>>8;
    temp[19]=(dstAddr&0xff);

    unsigned int checksum = getCheckSum(temp);
    temp[10] = (checksum&0xff00)>>8;
    temp[11]=checksum&0xff;

    char buffer[65535];
    memcpy(buffer, (char *)temp, 24);
    memcpy(buffer+20, pBuffer, strlen(pBuffer)+1);

    ip_SendtoLower(buffer,length);
    return 0;
}
unsigned int getCheckSum(char *head)
{
    unsigned char *p = (unsigned char*)head;
    unsigned int checksum = 0;
    for(int i = 0; i < 19; i+=2)
    {
        checksum += (p[i]<<8) + (p[i+1]);
    }
    checksum = (checksum >> 16) + (checksum & 0xffff);
    return (~checksum) & 0xffff;
}

