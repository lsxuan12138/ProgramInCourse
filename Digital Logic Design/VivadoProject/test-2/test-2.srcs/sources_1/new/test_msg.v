`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/11/19 20:02:54
// Design Name: 
// Module Name: test_msg
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


module test_smg(
    output num,
    output dm,
    input clk
    );
    reg[7:0] num;
    reg[3:0] dm;
    reg [14:0] div_cnt = 0;
    reg divclk = 0;
    reg [14:0] div_cnt2 = 0;
    reg divclk2 = 0;
    
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
      always @(posedge divclk)
   begin
       if(div_cnt2  == 1000)  
              begin
                   divclk2  = ~divclk2;
                   div_cnt2 = 0;
              end
      else    
                begin 
                   div_cnt2 = div_cnt2 +1'b1;     
               end
      end
//      always@(posedge divclk)
//      begin
        
        
//        //num=num+1;
//      end
      always@(posedge divclk2)
      begin
        case(dm)
            4'b0000:dm=4'b0001;
            4'b0001:dm=4'b0010;
            4'b0010:dm=4'b0100;
            4'b0100:dm=4'b1000;
            default:dm=4'b0001;
        endcase
        num=num+1;
      end
endmodule

