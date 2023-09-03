`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/23 13:51:57
// Design Name: 
// Module Name: FlowLed
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


 //
 //��ˮ�ƣ��˸������ε���
// Module Name: led_test
 //

module FlowLed(
    clk, //ʱ��
    reset, //��λ
    led //���״̬��
);
input clk;
input reset;
output [7:0] led;

reg [7:0] led;
reg [31:0] counter; //������
reg [3:0] state; //״̬����

//������
always @(posedge clk or negedge reset)
    begin
        if(!reset) 
            begin
                counter <= 32'd0;
            end
        else if ( counter == 32'd900_000_000) 
            begin
                counter <= 32'd0;
            end
        else 
            begin
                counter <= counter + 32'd1;
            end
        end

/* 
always @(posedge clk or negedge reset)
    begin
        if(!reset) 
            begin
                led <= 8'b1111_1111;
            end
        else if( counter < 32'd100_000_000 )
            led <= 8'b1111_1110;
        else if( counter >= 32'd100_000_000 && counter < 32'd200_000_000 )
            led <= 8'b1111_1101;
        else if( counter >= 32'd200_000_000 && counter < 32'd300_000_000 )
            led <= 8'b1111_1011;
        else if( counter >= 32'd300_000_000 && counter < 32'd400_000_000 )
            led <= 8'b1111_0111;
        else if( counter >= 32'd400_000_000 && counter < 32'd500_000_000 )
            led <= 8'b1110_1111;
        else if( counter >= 32'd500_000_000 && counter < 32'd600_000_000 )
            led <= 8'b1101_1111;
        else if( counter >= 32'd600_000_000 && counter < 32'd700_000_000 )
            led <= 8'b1011_1111;
        else if( counter >= 32'd700_000_000 && counter < 32'd800_000_000 )
            led <= 8'b0111_1111;
        else if( counter >= 32'd800_000_000 && counter < 32'd900_000_000 )
            led <= 8'b1111_1111;
        else
        begin
            counter<=32'd000_000_000;
            led <= 8'b1111_1110;
        end
    end
    */
 //״̬��ת��ͨ���������ļ���ֵ����
always @(posedge clk or negedge reset)
    begin
        if(!reset) 
            begin
                state <= 8'd0;
            end
        else if( counter < 32'd100_000_000 )
            state <= 8'd0;
        else if( counter >= 32'd100_000_000 && counter < 32'd200_000_000 )
            state <= 8'd1;
        else if( counter >= 32'd200_000_000 && counter < 32'd300_000_000 )
            state <= 8'd2;
        else if( counter >= 32'd300_000_000 && counter < 32'd400_000_000 )
            state <= 8'd3;
        else if( counter >= 32'd400_000_000 && counter < 32'd500_000_000 )
            state <= 8'd4;
        else if( counter >= 32'd500_000_000 && counter < 32'd600_000_000 )
            state <= 8'd5;
        else if( counter >= 32'd600_000_000 && counter < 32'd700_000_000 )
            state <= 8'd6;
        else if( counter >= 32'd700_000_000 && counter < 32'd800_000_000 )
            state <= 8'd7;
        else if( counter >= 32'd800_000_000 && counter < 32'd900_000_000 )
            state <= 8'd8;
        
    end
    
 //led״̬����
always @(posedge clk or negedge reset)
    begin
        if(!reset) 
            begin
                led <= 8'b1111_1111;
            end
        else 
            begin
                case(state)
                8'd0:led <= 8'b1111_1110; //led�͵�ƽʱ������Ĭ��״̬Ϊ��
                8'd1:led <= 8'b1111_1101; //����Ϊ���ε�������
                8'd2:led <= 8'b1111_1011;
                8'd3:led <= 8'b1111_0111;
                8'd4:led <= 8'b1110_1111; 
                8'd5:led <= 8'b1101_1111; 
                8'd6:led <= 8'b1011_1111;
                8'd7:led <= 8'b0111_1111;
                8'd8:led <= 8'b1111_1111;
                default:led <= 8'b1111_1111;
                endcase
            end
    end

endmodule


