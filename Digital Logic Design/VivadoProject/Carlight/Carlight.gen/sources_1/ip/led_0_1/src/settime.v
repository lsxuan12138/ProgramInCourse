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
    output [3:0] c
    );
 reg[3:0] c1 = 0;
 always @(posedge a)c1=c1+1;
 always @(posedge b)c1=c1-1;
 assign c = c1;
endmodule
