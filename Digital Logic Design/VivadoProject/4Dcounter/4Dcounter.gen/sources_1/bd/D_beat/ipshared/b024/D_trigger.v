`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/26 19:13:50
// Design Name: 
// Module Name: D_trigger
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////



module D_trigger(clk,d,rst,q);
    input d;
    input clk;
    input rst;
    output q;

    reg q;

    always @ (posedge clk,posedge rst)
    begin
        if(rst)//�첽��λ�źţ���ʱ�����޹أ�ֻҪ��λ�Ǹߵ�ƽ�ͻḴλ
            q <=0;
        else
            q <= d;//��������Ч��ʱ�򣬰�d����q
    end    

endmodule

