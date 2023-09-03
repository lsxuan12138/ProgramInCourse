`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/26 20:40:00
// Design Name: 
// Module Name: sim_jk
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


module sim_jk();
    reg CLK,J,K,Set,Reset;
    wire Q;
    jk_trigger JK(.Q(Q),.CLK(CLK),.J(J),.K(K),.Set(Set),.Reset(Reset));
    initial
    begin
        Set=1;Reset=1;
        CLK=0;
    end
    always
    begin
        #5 CLK=~CLK;
    end
    always
    begin
        #16 {J,K}=2'b00;
        #16 {J,K}=2'b01;
        #16 {J,K}=2'b10;
        #16 {J,K}=2'b11;
    end
    always
    begin
        #50 Reset=0; 
        #50 Set=0;Reset=1; 
        #50 Set=1;
    end
endmodule
