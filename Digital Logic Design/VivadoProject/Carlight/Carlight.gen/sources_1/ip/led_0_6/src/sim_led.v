`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/11/29 19:19:32
// Design Name: 
// Module Name: sim_led
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


module sim_led();
    reg[2:0]a=3'b011;
    reg clk = 0;
    reg up=0;
    reg s=0;
    always #5 clk = ~clk;
    always #20 up = ~up;
    initial
    begin
    #100 s=1;
    #1 s=0;
    #100 s=1;
    #99 a=3'b001;
    end
    wire[7:0] deng;
    wire [3:0] t;
    led  le(clk,a,deng,up,s,t);
endmodule
