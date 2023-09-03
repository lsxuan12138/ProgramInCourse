`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/24 21:14:50
// Design Name: 
// Module Name: jk_trigger
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


module jk_trigger(
    input wire CLK,
    input wire J,
    input wire K,
    input wire Set,
    input wire Reset,
    output Q
    );
    reg Q;
    always@(posedge CLK or negedge Set or negedge Reset)
    begin
       if(!Reset)
        begin
            Q<=1'b0;
        end
        else if(!Set)
        begin
            Q<=1'b1;
        end
        else
        begin
            case({J,K})
                2'b00:Q<=Q;
                2'b01:Q<=0;
                2'b10:Q<=1;
                default:Q=~Q;
            endcase
        end
    end
endmodule
