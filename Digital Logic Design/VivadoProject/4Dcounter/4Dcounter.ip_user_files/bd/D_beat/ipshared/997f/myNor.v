`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/29 18:53:00
// Design Name: 
// Module Name: myNor
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


module myNor(
    output wire out,
    input wire i1,
    input wire i2,
    input wire i3
    );
    nor(out,i1,i2,i3);
endmodule
