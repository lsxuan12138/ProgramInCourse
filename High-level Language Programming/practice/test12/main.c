int ifMove(struct Tetris *tetris)
{
 	if(a[tetris->x][tetris->y]!=0)//当中心方块位置上有图案时，返回值为0，即不可移动
 	{
  		return 0;
 	}
 	else
 	{
  		if( //当为田字方块且除中心方块位置外，其他"■"字方块位置上无图案时，说明这个位置能够放下田字方块，可以移动到这个位置，返回值为1，即可移动
			//比如田字方块，它的中心方块是左下角的■，如果它的上，右，右上的位置为空，则这个位置就可以放一个田字方块；如果有一个位置上不为空，都放不下一个田
   		( tetris->flag==1  && ( a[tetris->x][tetris->y-1]==0   &&
    	a[tetris->x+2][tetris->y-1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   		//或为直线方块且除中心方块位置外，其他"■"字方块位置上无图案时，返回值为1，即可移动
   		( tetris->flag==2  && ( a[tetris->x-2][tetris->y]==0   &&
    	a[tetris->x+2][tetris->y]==0 && a[tetris->x+4][tetris->y]==0 ) )   ||
   		( tetris->flag==3  && ( a[tetris->x][tetris->y-1]==0   &&
    	a[tetris->x][tetris->y-2]==0 && a[tetris->x][tetris->y+1]==0 ) )   ||
   		( tetris->flag==4  && ( a[tetris->x-2][tetris->y]==0   &&
    	a[tetris->x+2][tetris->y]==0 && a[tetris->x][tetris->y+1]==0 ) )   ||
   		( tetris->flag==5  && ( a[tetris->x][tetris->y-1]==0   &&
   		 a[tetris->x][tetris->y+1]==0 && a[tetris->x-2][tetris->y]==0 ) )   ||
   		( tetris->flag==6  && ( a[tetris->x][tetris->y-1]==0   &&
    	a[tetris->x-2][tetris->y]==0 && a[tetris->x+2][tetris->y]==0 ) )   ||
   		( tetris->flag==7  && ( a[tetris->x][tetris->y-1]==0   &&
    	a[tetris->x][tetris->y+1]==0 && a[tetris->x+2][tetris->y]==0 ) )   ||
   		( tetris->flag==8  && ( a[tetris->x][tetris->y+1]==0   &&
    	a[tetris->x-2][tetris->y]==0 && a[tetris->x+2][tetris->y+1]==0 ) ) ||
   		( tetris->flag==9  && ( a[tetris->x][tetris->y-1]==0   &&
    	a[tetris->x-2][tetris->y]==0 && a[tetris->x-2][tetris->y+1]==0 ) ) ||
   		( tetris->flag==10 && ( a[tetris->x][tetris->y-1]==0   &&
    	a[tetris->x-2][tetris->y-1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   		( tetris->flag==11 && ( a[tetris->x][tetris->y+1]==0   &&
    	a[tetris->x-2][tetris->y-1]==0 && a[tetris->x-2][tetris->y]==0 ) ) ||
   		( tetris->flag==12 && ( a[tetris->x][tetris->y-1]==0   &&
    	a[tetris->x][tetris->y+1]==0 && a[tetris->x-2][tetris->y-1]==0 ) ) ||
   		( tetris->flag==15 && ( a[tetris->x-2][tetris->y]==0   &&
    	a[tetris->x-2][tetris->y+1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   		( tetris->flag==14 && ( a[tetris->x][tetris->y-1]==0   &&
    	a[tetris->x][tetris->y+1]==0 && a[tetris->x+2][tetris->y+1]==0 ) ) ||
   		( tetris->flag==13 && ( a[tetris->x-2][tetris->y]==0   &&
    	a[tetris->x+2][tetris->y-1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   		( tetris->flag==16 && ( a[tetris->x][tetris->y+1]==0   &&
    	a[tetris->x][tetris->y-1]==0 && a[tetris->x+2][tetris->y-1]==0 ) ) ||
   		( tetris->flag==19 && ( a[tetris->x-2][tetris->y]==0   &&
    	a[tetris->x-2][tetris->y-1]==0 && a[tetris->x+2][tetris->y]==0 ) ) ||
   		( tetris->flag==18 && ( a[tetris->x][tetris->y-1]==0   &&
    	a[tetris->x][tetris->y+1]==0 && a[tetris->x-2][tetris->y+1]==0 ) ) ||
   		( tetris->flag==17 && ( a[tetris->x-2][tetris->y]==0   &&
    	a[tetris->x+2][tetris->y+1]==0 && a[tetris->x+2][tetris->y]==0 ) ) )
   		{
    		return 1;
   		}
	}
 	return 0;
}
void MakeTetris(struct Tetris *tetris)
{
 	a[tetris->x][tetris->y]=b[0];    //中心方块位置的图形状态
 	switch(tetris->flag)      //共7大类，19种类型
 	{
  		case 1:         /*田字方块 ■■
		  			               ■■  */
   		{
			color(10);
    		a[tetris->x][tetris->y-1]=b[1];
    		a[tetris->x+2][tetris->y-1]=b[2];
    		a[tetris->x+2][tetris->y]=b[3];
   			break;
   		}
  		case 2:         /*直线方块 ■■■■*/
   		{
		   	color(13);
    		a[tetris->x-2][tetris->y]=b[1];
    		a[tetris->x+2][tetris->y]=b[2];
    		a[tetris->x+4][tetris->y]=b[3];
    		break;
   		}
  		case 3:         /*直线方块 ■
		  			               ■
								   ■
								   ■  */
   		{
		   	color(13);
    		a[tetris->x][tetris->y-1]=b[1];
    		a[tetris->x][tetris->y-2]=b[2];
    		a[tetris->x][tetris->y+1]=b[3];
    		break;
   		}
  		case 4:         /*T字方块 ■■■
		  			                ■  */
   		{
		   	color(11);
    		a[tetris->x-2][tetris->y]=b[1];
    		a[tetris->x+2][tetris->y]=b[2];
    		a[tetris->x][tetris->y+1]=b[3];
    		break;
   		}
  		case 5:         /* 顺时针90°T字方块   ■
		  			                         ■■
									           ■*/
   		{
		   	color(11);
    		a[tetris->x][tetris->y-1]=b[1];
    		a[tetris->x][tetris->y+1]=b[2];
    		a[tetris->x-2][tetris->y]=b[3];
    		break;
   		}
  		case 6:         /* 顺时针180°T字方块     ■
		  			                            ■■■*/
   		{
		   	color(11);
    		a[tetris->x][tetris->y-1]=b[1];
    		a[tetris->x-2][tetris->y]=b[2];
    		a[tetris->x+2][tetris->y]=b[3];
    		break;
   		}
  		case 7:         /* 顺时针270°T字方块    ■
		  			                            ■■
									            ■  */
   		{
		   	color(11);
    		a[tetris->x][tetris->y-1]=b[1];
    		a[tetris->x][tetris->y+1]=b[2];
    		a[tetris->x+2][tetris->y]=b[3];
    		break;
   		}
  		case 8:         /* Z字方块   ■■
		  			                   ■■*/
   		{
		   	color(14);
    		a[tetris->x][tetris->y+1]=b[1];
    		a[tetris->x-2][tetris->y]=b[2];
    		a[tetris->x+2][tetris->y+1]=b[3];
    		break;
   		}
  		case 9:         /* 顺时针Z字方块     ■
		  			                        ■■
									        ■  */
   		{
		   	color(14);
    		a[tetris->x][tetris->y-1]=b[1];
    		a[tetris->x-2][tetris->y]=b[2];
    		a[tetris->x-2][tetris->y+1]=b[3];
    		break;
   		}
  		case 10:        /* 反转Z字方块     ■■
		  			                      ■■  */
   		{
		   	color(14);
    		a[tetris->x][tetris->y-1]=b[1];
    		a[tetris->x-2][tetris->y-1]=b[2];
    		a[tetris->x+2][tetris->y]=b[3];
    		break;
   		}
  		case 11:        /* 顺时针Z字方块    ■
		  			                        ■■
                                             ■  */
   		{
		   	color(14);
    		a[tetris->x][tetris->y+1]=b[1];
    		a[tetris->x-2][tetris->y-1]=b[2];
    		a[tetris->x-2][tetris->y]=b[3];
    		break;
   		}
  		case 12:        /* 7字方块     ■■
		  			                    ■
									    ■  */
   		{
		   	color(12);
    		a[tetris->x][tetris->y-1]=b[1];
    		a[tetris->x][tetris->y+1]=b[2];
    		a[tetris->x-2][tetris->y-1]=b[3];
    		break;
   		}
  		case 13:        /* 顺时针90°7字方块       ■
		  			                            ■■■*/
   		{
		   	color(12);
    		a[tetris->x-2][tetris->y]=b[1];
    		a[tetris->x+2][tetris->y-1]=b[2];
    		a[tetris->x+2][tetris->y]=b[3];
    		break;
   		}
  		case 14:        /* 顺时针180°7字方块       ■
		  			                               ■
									               ■■  */
   		{
		   	color(12);
    		a[tetris->x][tetris->y-1]=b[1];
   		 	a[tetris->x][tetris->y+1]=b[2];
    		a[tetris->x+2][tetris->y+1]=b[3];
    		break;
   		}
  		case 15:        /* 顺时针270°7字方块    ■■■
		  			                             ■    */
   		{
		   	color(12);
    		a[tetris->x-2][tetris->y]=b[1];
    		a[tetris->x-2][tetris->y+1]=b[2];
    		a[tetris->x+2][tetris->y]=b[3];
    		break;
   		}
  		case 16:        /* 反转7字方块    ■■
		  			                      ■
										  ■    */
   		{
		   	color(12);
    		a[tetris->x][tetris->y+1]=b[1];
    		a[tetris->x][tetris->y-1]=b[2];
    		a[tetris->x+2][tetris->y-1]=b[3];
    		break;
   		}
  		case 17:        /* 顺时针转90°7字方块    ■■■
		  			                                  ■*/
   		{
		   	color(12);
    		a[tetris->x-2][tetris->y]=b[1];
    		a[tetris->x+2][tetris->y+1]=b[2];
    		a[tetris->x+2][tetris->y]=b[3];
    		break;
   		}
  		case 18:        /* 顺时针转180°7字方块    ■
                                                  ■
									             ■■    */
   		{
		   	color(12);
    		a[tetris->x][tetris->y-1]=b[1];
    		a[tetris->x][tetris->y+1]=b[2];
    		a[tetris->x-2][tetris->y+1]=b[3];
    		break;
   		}
  		case 19:        /* 顺指针转270°7字方块    ■
                                                  ■■■*/
  		{
		  	color(12);
    		a[tetris->x-2][tetris->y]=b[1];
    		a[tetris->x-2][tetris->y-1]=b[2];
    		a[tetris->x+2][tetris->y]=b[3];
    		break;
  	 	}
 	}
}
if(a[i][j]==1&&j>FrameY){                //如果有方块
                gotoxy(i,j);
                printf("■");

            }
