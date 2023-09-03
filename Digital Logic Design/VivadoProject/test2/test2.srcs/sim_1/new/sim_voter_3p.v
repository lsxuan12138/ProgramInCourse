`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/18 23:21:40
// Design Name: 
// Module Name: sim_voter_3p
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


module sim_voter_3p();
    reg s2,s1,s0;
    wire out;
    voter_3p v(.s2(s2),.s1(s1),.s0(s0),.out(out));
    initial
        begin
         s2=0;s1=0;s0=0;    
         end
     always #10 {s2,s1,s0}={s2,s1,s0}+1;
endmodule
