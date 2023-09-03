`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/11/29 19:59:34
// Design Name: 
// Module Name: number
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

`define zerop 8'b11111101
`define onep 8'b01100001
`define twop 8'b11011011
`define threep 8'b11110011
`define fourp 8'b01100111
`define fivep 8'b10110111
`define sixp 8'b10111111
`define sevenp 8'b11100001
`define eightp 8'b11111111;
`define ninep 8'b11110111
`define zero 8'b11111100
`define five 8'b10110110
module number(s,t,clk,a,dm1,wm1 );
    input clk;
   input [2:0] a; 
   input[3:0] t;
   input s;
   output [7:0] dm1;
   output [3:0] wm1;
   reg [7:0] dm1 = 0;  
   reg [3:0] wm1 = 4'b0100; 
   reg [14:0] div_cnt = 0; 
   reg divclk = 0;
   reg[1:0] disp_bit = 0;
   reg flag = 0;
   always @(posedge s)flag = ~flag;
parameter  maxcnt=25000;
   always @(posedge clk )
   begin
       if(div_cnt  == maxcnt)  
              begin
                   divclk  = ~divclk;
                   div_cnt = 0;
              end
      else    
                begin 
                   div_cnt = div_cnt +1'b1;     
               end
      end
    always @(posedge divclk )
       begin            
            if ( disp_bit >= 3) 
                      disp_bit = 0; 
          else
                     disp_bit = disp_bit + 1; 
          case (disp_bit)
              2'b00:wm1 = 4'b0001;   
             2'b01:wm1 = 4'b0010; 
             2'b10: wm1=4'b0100;
             2'b11:wm1=4'b1000;
              default: wm1 = 0;
              endcase
        end
    always @(wm1)
        begin
            if(wm1==4'b0001)
                begin
                case(a)
                3'b000:dm1=8'h60;
                3'b001:dm1=8'h9e;
                3'b010:dm1=8'hfc;
                3'b011:dm1=8'h60;
                3'b100:dm1=8'hce;
                3'b101:dm1=8'h9e;
                3'b110:dm1=8'h9c;
                3'b111:dm1=8'h8e;
                endcase
                end
             if(wm1==4'b0010)
                begin
                case(a)
                3'b000:dm1=8'h60;
                3'b001:dm1=8'h9e;
                3'b010:dm1=8'h60;
                3'b011:dm1=8'hfc;
                3'b100:dm1=8'hce;
                3'b101:dm1=8'h9e;
                3'b110:dm1=8'h3e;
                3'b111:dm1=8'h8e;
                endcase
                end

       if(wm1==4'b0100)
            if(flag==1)
                    begin
                    if(t[0]==0)dm1 = `zero;
                    else if(t[0]==1)dm1=`five;
                    end
             else dm1=0;
        if(wm1==4'b1000)
             if(flag==1)
                    begin
                    case(t>>1)
                    0:dm1=`zerop;
                    1:dm1=`onep;
                    2:dm1=`twop;
                    3:dm1=`threep;
                    4:dm1=`fourp;
                    5:dm1=`fivep;
                    6:dm1=`sixp;
                    7:dm1=`sevenp;
                    default:dm1=0;
                    endcase
                    end
             else dm1=0;
         end
endmodule
