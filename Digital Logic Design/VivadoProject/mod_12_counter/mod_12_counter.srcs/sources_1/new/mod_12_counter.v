`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/26 21:51:49
// Design Name: 
// Module Name: mod_12_counter
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

//j4=q3q2q1
//k4=q2q1
//j3=(~q4)q2q1
//k3=q2q1
//j2=q1
//k2=q1
//j1=1
//k1=1
module mod_12_counter(
    input wire CLK,
    input wire Set,
    input wire Rst,
    output wire Z,
    output wire[3:0] out
    );
    
    wire q4,q3,q2,q1;
    wire l1,l2,l3;
    and(l1,q3,q2,q1);
    and(l2,q2,q1);
    and(l3,~q4,q2,q1);
    jk_trigger_0 JK4(.CLK(CLK),.J(l1),.K(l2),.Set(Set),.Reset(Rst),.Q(q4));
    jk_trigger_0 JK3(.CLK(CLK),.J(l3),.K(l2),.Set(Set),.Reset(Rst),.Q(q3));
    jk_trigger_0 JK2(.CLK(CLK),.J(q1),.K(q1),.Set(Set),.Reset(Rst),.Q(q2));
    jk_trigger_0 JK1(.CLK(CLK),.J(1),.K(1),.Set(Set),.Reset(Rst),.Q(q1));
    and(Z,q4,q2,q1);
    assign out={q4,q3,q2,q1};
endmodule
