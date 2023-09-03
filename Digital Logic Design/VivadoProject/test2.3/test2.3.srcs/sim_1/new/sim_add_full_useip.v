`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/20 21:40:08
// Design Name: 
// Module Name: sim_add_full_useip
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


module sim_add_full_useip();
    reg ai,bi,cin;
    wire si,ci;
    add_full ADD(.si(si),.ci(ci),.ai(ai),.bi(bi),.cin(cin));
    initial
        begin
            ai=0;bi=0;cin=0;
        end
    always #10 {ai,bi,cin}={ai,bi,cin}+1;
endmodule
