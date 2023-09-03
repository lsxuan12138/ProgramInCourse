`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/11/17 17:49:58
// Design Name: 
// Module Name: test_mc
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


module test_mc(
    input clk,
    input s3,
    input s2,
    input s1,
    output AUDIO_PWM,
    output AUDIO_SD
    );
    
    reg clks1,clks0,mcs;
    wire outClk;
    wire[2:0] in;
    assign in = {s3,s2,s1};
    divClk_0 divClk(.Clk(clk),.s1(clks1),.s0(clks0),.divClk(outClk));
    mc_0 mc(.clk(clk),.s(mcs),.sdin(outClk),.pwm(AUDIO_PWM),.sd(AUDIO_SD));
    always@(s3,s2,s1)
    begin
        case(in)
        3'b000:
        begin
        {clks1,clks0}=2'b00; 
        mcs=0;
        end
        3'b001:begin{clks1,clks0}=2'b01;mcs=1;end
        
        3'b010:begin{clks1,clks0}=2'b10;mcs=0;end
        3'b011:begin{clks1,clks0}=2'b10;mcs=0;end
        3'b100:begin{clks1,clks0}=2'b10;mcs=0;end
        3'b101:begin{clks1,clks0}=2'b10;mcs=0;end
        3'b110:begin{clks1,clks0}=2'b10;mcs=0;end
        default:begin{clks1,clks0}=2'b11;mcs=0;end
        endcase
    end
endmodule
