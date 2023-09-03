`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/26 19:41:45
// Design Name: 
// Module Name: sim_4Dcounter
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


module sim_4Dcounter(

    );
    reg Clk,Shift;
    //wire[3:0] Y;
    //D4counter D4(.Y(Y),.Clk(Clk),.Shift(Shift));
    wire Y4,Y3,Y2,Y1;
    D_beat D4(.CLK(Clk),.Rst(Shift),.Y4(Y4),.Y3(Y3),.Y2(Y2),.Y1(Y1));
    initial
    begin
        Clk=0;
        Shift=1;
        #1 Shift=0;
    end
    always
    begin
        #5 Clk=~Clk;
    end
//    always
//    begin
//        #150 Shift=~Shift;
//    end
endmodule
