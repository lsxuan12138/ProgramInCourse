`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/26 22:03:55
// Design Name: 
// Module Name: sim_mod12
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


module sim_mod12();
    reg CLK,Set,Rst;
    wire[3:0] out;
    wire Z;
    mod_12_counter Mod12(.CLK(CLK),.Set(Set),.Rst(Rst),.Z(Z),.out(out));
    initial
    begin
        CLK=0;
        Set=1;
        Rst=0;
    end
    always
    begin
        #5 CLK=~CLK;
    end
    always
    begin
        #2 {Set,Rst}=2'b11;
        #150 {Set,Rst}=2'b10;
        #35 {Set,Rst}=2'b01;
        #35 {Set,Rst}=2'b11;
    end
endmodule
