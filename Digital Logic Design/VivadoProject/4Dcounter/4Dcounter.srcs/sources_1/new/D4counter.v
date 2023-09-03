`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/26 19:24:45
// Design Name: 
// Module Name: D4counter
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


module D4counter(
    output wire[3:0] Y,
    input wire Clk,
    input wire Shift
    );
    wire si,y1,y2,y3,y4;
    nor(si,y2,y3,y4);
    D_trigger_0 D1(.d(y2),.clk(Clk),.rst(Shift),.q(y1));
    D_trigger_0 D2(.d(y3),.clk(Clk),.rst(Shift),.q(y2));
    D_trigger_0 D3(.d(y4),.clk(Clk),.rst(Shift),.q(y3));
    D_trigger_0 D4(.d(si),.clk(Clk),.rst(Shift),.q(y4));
    assign Y={y4,y3,y2,y1};
endmodule
