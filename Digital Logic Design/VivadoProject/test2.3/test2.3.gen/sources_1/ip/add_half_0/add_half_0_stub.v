// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Wed Oct 20 21:22:22 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode synth_stub
//               d:/VProject/test2.3/test2.3.gen/sources_1/ip/add_half_0/add_half_0_stub.v
// Design      : add_half_0
// Purpose     : Stub declaration of top-level module interface
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------

// This empty module with port declaration file causes synthesis tools to infer a black box for IP.
// The synthesis directives are for Synopsys Synplify support to prevent IO buffer insertion.
// Please paste the declaration into a Verilog source file or add the file as an additional source.
(* X_CORE_INFO = "add_half,Vivado 2021.1" *)
module add_half_0(s, ci, ai, bi)
/* synthesis syn_black_box black_box_pad_pin="s,ci,ai,bi" */;
  output s;
  output ci;
  input ai;
  input bi;
endmodule
