`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/11/29 18:36:08
// Design Name: 
// Module Name: divClk
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
//case:s1,s0
//00 原样输出 即100MHz
//01 输出2Hz
//10 输出 5Hz
module divClk(
    input Clk,
    input s1,
    input s0,
    output divClk
    );
    reg divClk;
    reg[31:0] parm;
    reg[31:0] counter;
    always@({s1,s0})
    begin
        case({s1,s0})
            2'b00:parm <= 1;
            2'b01:parm <=31'd50_000_000;
            2'b10:parm <=31'd20_000_000;
            default:parm<= 1;
        endcase
    end
    always@(posedge Clk)
        if(counter==parm)
        begin
            divClk=~divClk;
            counter=0;
        end
        else if(counter>parm)
        begin
            counter=0;
        end
        else
        begin
            counter=counter+1;
        end
    begin
    end
endmodule
