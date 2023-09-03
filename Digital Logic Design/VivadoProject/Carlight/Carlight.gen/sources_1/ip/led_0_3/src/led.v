`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/11/29 18:23:50
// Design Name: 
// Module Name: led
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


module led(clk,a,led,up,down,switch,t1);
    input clk;
    input[2:0] a;
    input up;
    input down;
    input switch;
    output [7:0] led;
    output [3:0] t1;
    reg [7:0] ledtemp = 8'b00000000; // led初值
    assign led = ledtemp;
    reg[3:0] count = 0;
    wire[3:0] t1;
    settime s(up,down,switch,t1);
    always @(posedge clk)
    begin
        case(a)
        3'b000: ledtemp = 8'b00000000;
        3'b001: 
            begin
                if(count!=t1)
                begin
                ledtemp = 8'b11100111;
                count = count + 1;
                end
                else
                begin
                count = 0;
                ledtemp = 8'b00000000;
                end
            end
        3'b010:
            begin
                if(ledtemp==0)
                ledtemp = 8'b00000100;
                if ( ledtemp[7] == 1)
                ledtemp = 8'b00000100; //实现循环移位
                else
                ledtemp = ledtemp >> 1; //左移1位
            end
        3'b011:
            begin 
                    if(ledtemp==8'b00000000)ledtemp = 8'b00100000;
                    if ( ledtemp[7] == 1)
                    ledtemp = 8'b00100000; //实现循环移位
                    else
                    ledtemp = ledtemp << 1; //左移1位
            end    
        3'b100:   
            begin
                if(ledtemp == 8'b00000000)ledtemp = 8'b11100111;
                else if(ledtemp == 8'b11100111)ledtemp = 8'b00000000;
            end
         3'b101:   
            begin
                if(ledtemp == 8'b00000000)ledtemp = 8'b11100111;
                else if(ledtemp == 8'b11100111)ledtemp = 8'b00000000;
            end
        3'b110:
            begin
                case(ledtemp)
                8'b00000000:ledtemp = 8'b10000001;
                8'b10000001:ledtemp = 8'b01000010;
                8'b01000010:ledtemp = 8'b00100100;
                8'b00100100:ledtemp = 8'b00000000;
                endcase
            end
        3'b111:ledtemp = 8'b10100101;
        endcase
    end
endmodule
