`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/12/01 21:36:00
// Design Name: 
// Module Name: sim_div
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


module sim_div(

    );
    wire out;
    reg clk,s1,s0;
    divClk div(.Clk(clk),.s1(s1),.s0(s0),.divClk(out));
    initial
    begin
        clk=0;s1=0;s0=0;
    end
    always #1 clk=~clk;
    always
    begin
        #100 {s1,s0}={s1,s0}+1;
    end
endmodule
