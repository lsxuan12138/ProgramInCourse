`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/20 21:09:49
// Design Name: 
// Module Name: add_half
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


module add_half(
    output s,
    output ci,
    input ai,
    input bi
    );
    xor(s,ai,bi);
    and(ci,ai,bi);
endmodule
