`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/11/29 22:14:35
// Design Name: 
// Module Name: simnum
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


module simnum();
reg[2:0] a = 3'b001;
reg clk = 0;
always #5 clk = ~clk;
wire[7:0] dm;
wire[3:0] wm;

endmodule
