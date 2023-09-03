// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Mon Nov 29 19:15:35 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode synth_stub d:/VProject/test/test.gen/sources_1/ip/divClk_0/divClk_0_stub.v
// Design      : divClk_0
// Purpose     : Stub declaration of top-level module interface
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------

// This empty module with port declaration file causes synthesis tools to infer a black box for IP.
// The synthesis directives are for Synopsys Synplify support to prevent IO buffer insertion.
// Please paste the declaration into a Verilog source file or add the file as an additional source.
(* X_CORE_INFO = "divClk,Vivado 2021.1" *)
module divClk_0(Clk, s1, s0, divClk)
/* synthesis syn_black_box black_box_pad_pin="Clk,s1,s0,divClk" */;
  input Clk;
  input s1;
  input s0;
  output divClk;
endmodule
