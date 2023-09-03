// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Tue Nov 30 21:39:35 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode synth_stub d:/VProject/Carlight/Carlight.gen/sources_1/ip/led_0_3/led_0_stub.v
// Design      : led_0
// Purpose     : Stub declaration of top-level module interface
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------

// This empty module with port declaration file causes synthesis tools to infer a black box for IP.
// The synthesis directives are for Synopsys Synplify support to prevent IO buffer insertion.
// Please paste the declaration into a Verilog source file or add the file as an additional source.
(* X_CORE_INFO = "led,Vivado 2021.1" *)
module led_0(clk, a, led, up, down, switch, t1)
/* synthesis syn_black_box black_box_pad_pin="clk,a[2:0],led[7:0],up,down,switch,t1[3:0]" */;
  input clk;
  input [2:0]a;
  output [7:0]led;
  input up;
  input down;
  input switch;
  output [3:0]t1;
endmodule
