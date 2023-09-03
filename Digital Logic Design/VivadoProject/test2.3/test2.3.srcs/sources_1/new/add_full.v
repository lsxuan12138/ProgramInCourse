`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/20 21:28:47
// Design Name: 
// Module Name: add_full
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


module add_full(
    output si,
    output ci,
    input ai,
    input bi,
    input cin
    );
    wire s1;
    wire c1;
    wire c2;
    add_half_0 ADD_HALF1(.s(s1),.ci(c1),.ai(ai),.bi(bi));
    add_half_0 ADD_HALF2(.s(si),.ci(c2),.ai(cin),.bi(s1));
    or(ci,c1,c2);
endmodule
