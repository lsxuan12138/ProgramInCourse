`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/11/19 18:48:23
// Design Name: 
// Module Name: CenterController
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


module CenterController(
    input clk,
    input s3,
    input s2,
    input s1,
    input switch,
    input up,
    //input down,
    output[7:0] led,
    output AUDIO_PWM,
    output AUDIO_SD,
    output[3:0] wm1,
    output[7:0] dm1 
    );
    reg clks1,clks0,mcs;
    wire outClk;
    wire[3:0] t;
    divClk_0 divClk(.Clk(clk),.s1(clks1),.s0(clks0),.divClk(outClk));
    mc_0 mc(.clk(clk),.s(mcs),.sdin(outClk),.pwm(AUDIO_PWM),.sd(AUDIO_SD));
    led_0 led(.clk(outClk),.a({s3,s2,s1}),.up(up),.switch(switch),.led(led),.t1(t));
    number_0 number(.clk(clk),.a({s3,s2,s1}),.t(t),.s(switch),.dm1(dm1),.wm1(wm1));
    always@(s3,s2,s1)
    begin
        case({s3,s2,s1})
        3'b000:begin{clks1,clks0}=2'b00;mcs=0;end
        3'b001:begin{clks1,clks0}=2'b01;mcs=1;end
        3'b010:begin{clks1,clks0}=2'b01;mcs=0;end
        3'b011:begin{clks1,clks0}=2'b01;mcs=0;end
        3'b100:begin{clks1,clks0}=2'b01;mcs=0;end
        3'b101:begin{clks1,clks0}=2'b10;mcs=0;end
        3'b110:begin{clks1,clks0}=2'b10;mcs=0;end
        default:begin{clks1,clks0}=2'b11;mcs=0;end
        endcase
    end
endmodule
