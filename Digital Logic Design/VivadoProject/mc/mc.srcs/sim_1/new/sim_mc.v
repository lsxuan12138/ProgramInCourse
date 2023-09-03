`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/12/01 00:39:18
// Design Name: 
// Module Name: sim_mc
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


module sim_mc(

    );
    reg[2:0] s;
    reg clk ,sdin;
    wire pwm,sd;
    mc mc0(.clk(clk),.s(s),.sdin(sdin),.pwm(pwm),.sd(sd));
    initial
    begin
        sdin=0;
        clk=0;
        s=2'b001;
        #100 s=2'b011;
    end
    always
    begin
        # 5 clk=~clk;
    end
    always
    begin
        # 12 sdin=~sdin;
    end
endmodule
