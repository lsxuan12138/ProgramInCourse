`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/11/29 22:58:29
// Design Name: 
// Module Name: settime
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


module settime(
    input a,
    input b,
    input switch,
    output [3:0] c
    );
    reg s = 0;
 reg[3:0] c1 = 10;
 always @(posedge switch)s=~s;
 always @(posedge a)
 begin
    if(s==1)c1=c1+1;
 end
 always @(posedge b)
 begin
    if(s==1)c1=c1-1;
 end
 assign c = c1;
endmodule
