`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2021/10/23 13:57:41
// Design Name: 
// Module Name: sim_led
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

module sim_led(
);

reg clk_i, rst_n_i; // the type  of input is generally reg
wire [7:0] led_o; // the type  of output is generally wire

//cite the module 
FlowLed flow_led_u(
    .clk(clk_i),
    .reset(rst_n_i),
    .led(led_o)
);

//initial the module
initial begin
    clk_i = 0;
    rst_n_i = 0;
    #10 
    rst_n_i =1;
end

always #100 clk_i = ~clk_i;  // period is 2ps to  fastly simulate 

endmodule
