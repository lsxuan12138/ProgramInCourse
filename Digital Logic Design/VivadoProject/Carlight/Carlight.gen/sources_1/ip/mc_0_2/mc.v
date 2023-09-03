`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/11/30 22:12:14
// Design Name: 
// Module Name: mc
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


module mc(
    input clk,
    input s,
    input sdin,
    output pwm,
    output sd
    );
    //获取输出声音频率
    reg pwm;
    reg sd;
    reg[16:0] counter;
initial
begin
pwm=0;
sd=0;
counter=0;
end
    always@(posedge clk)
    begin
        if(s==1'b1)
        begin
            sd=sdin;
            if(counter == 17'd80000)
                //if(counter == 17'd8)
                    begin
                        pwm=~pwm;
                        counter<=17'd0;
                    end
                else if(counter<17'd80000)
                //else if(counter<17'd8)
                    begin
                        counter <= counter+17'd1;
                    end
                else
                    begin
                        counter <= 0;
                    end
        end
        else
        begin
            pwm=0;
            sd=0;
            counter<=0;
        end
    end
//    always@(negedge clk)
//    begin
//    case(s)
//    3'b001:
//    begin
//        sd=sdin;
//        if(counter == 17'd80000)
//                //if(counter == 17'd8)
//                    begin
//                        pwm=~pwm;
//                        counter<=17'd0;
//                    end
//                else if(counter<17'd80000)
//                //else if(counter<17'd8)
//                    begin
//                        counter <= counter+17'd1;
//                    end
//                else
//                    begin
//                        counter <= 0;
//                    end
//    end
//    default:
//    begin
//        pwm=0;
//        sd=0;
//        counter<=0;
//    end
//    endcase

endmodule
