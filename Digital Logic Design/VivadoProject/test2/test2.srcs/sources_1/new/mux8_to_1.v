`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/18 22:24:02
// Design Name: 
// Module Name: mux8_to_1
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


module mux8_to_1(out,i0,i1,i2,i3,i4,i5,i6,i7,s2,s1,s0);
    output out;
    input i0,i1,i2,i3,i4,i5,i6,i7;
    input s2,s1,s0;
    reg out ;
    always @(s2 or s1 or s0 or i0 or i1 or i2 or i3 or i4 or i5 or i6 or i7)
    begin
        case({s2,s1,s0})
        3'b000:out = i0;
        3'b001:out = i1;
        3'b010:out = i2;
        3'b011:out = i3;
        3'b100:out = i4;
        3'b101:out = i5;
        3'b110:out = i6;
        3'b111:out = i7;
        endcase
    end
endmodule
