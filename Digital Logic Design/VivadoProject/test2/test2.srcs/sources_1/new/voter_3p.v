`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/18 22:50:43
// Design Name: 
// Module Name: voter_3p
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


module voter_3p(
    input s0,
    input s1,
    input s2,
    output out
    );
    
    mux8_to_1 MUX(.out(out),.i0(0),.i1(0),.i2(0),.i3(1),.i4(0),.i5(1),.i6(1),.i7(1),.s2(s2),.s1(s1),.s0(s0));
endmodule
