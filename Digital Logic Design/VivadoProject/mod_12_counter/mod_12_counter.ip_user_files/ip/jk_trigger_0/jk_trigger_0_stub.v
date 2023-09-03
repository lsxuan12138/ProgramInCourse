// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Tue Oct 26 21:53:01 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode synth_stub
//               d:/VProject/mod_12_counter/mod_12_counter.gen/sources_1/ip/jk_trigger_0/jk_trigger_0_stub.v
// Design      : jk_trigger_0
// Purpose     : Stub declaration of top-level module interface
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------

// This empty module with port declaration file causes synthesis tools to infer a black box for IP.
// The synthesis directives are for Synopsys Synplify support to prevent IO buffer insertion.
// Please paste the declaration into a Verilog source file or add the file as an additional source.
(* X_CORE_INFO = "jk_trigger,Vivado 2021.1" *)
module jk_trigger_0(CLK, J, K, Set, Reset, Q)
/* synthesis syn_black_box black_box_pad_pin="CLK,J,K,Set,Reset,Q" */;
  input CLK;
  input J;
  input K;
  input Set;
  input Reset;
  output Q;
endmodule
