// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Wed Dec  1 16:30:12 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode synth_stub
//               d:/VProject/Carlight/Carlight.gen/sources_1/ip/number_0_2/number_0_stub.v
// Design      : number_0
// Purpose     : Stub declaration of top-level module interface
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------

// This empty module with port declaration file causes synthesis tools to infer a black box for IP.
// The synthesis directives are for Synopsys Synplify support to prevent IO buffer insertion.
// Please paste the declaration into a Verilog source file or add the file as an additional source.
(* X_CORE_INFO = "number,Vivado 2021.1" *)
module number_0(s, t, clk, a, dm1, wm1)
/* synthesis syn_black_box black_box_pad_pin="s,t[3:0],clk,a[2:0],dm1[7:0],wm1[3:0]" */;
  input s;
  input [3:0]t;
  input clk;
  input [2:0]a;
  output [7:0]dm1;
  output [3:0]wm1;
endmodule
