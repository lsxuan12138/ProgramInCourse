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
        if(rst)//异步复位信号，跟时钟沿无关，只要复位是高电平就会复位
            q <=0;
        else
            q <= d;//上升沿有效的时候，把d捕获到q
    end    

endmodule

