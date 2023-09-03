`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/23 21:40:17
// Design Name: 
// Module Name: sim_8led_sv
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


module sim_8led_sv(
    );
    wire led[7:0];
    reg clk,reset;
    FlowLed Fled(.clk(clk),.reset(reset),.led(led));
    initial
    begin
        clk=0;
        reset=0;
        #10 reset=1;
    end
    always #0.001 clk=~clk;
endmodule
