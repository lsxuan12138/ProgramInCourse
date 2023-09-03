//Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
//--------------------------------------------------------------------------------
//Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
//Date        : Fri Oct 29 19:03:48 2021
//Host        : MateBook14 running 64-bit major release  (build 9200)
//Command     : generate_target D_beat.bd
//Design      : D_beat
//Purpose     : IP block netlist
//--------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

(* CORE_GENERATION_INFO = "D_beat,IP_Integrator,{x_ipVendor=xilinx.com,x_ipLibrary=BlockDiagram,x_ipName=D_beat,x_ipVersion=1.00.a,x_ipLanguage=VERILOG,numBlks=5,numReposBlks=5,numNonXlnxBlks=0,numHierBlks=0,maxHierDepth=0,numSysgenBlks=0,numHlsBlks=0,numHdlrefBlks=0,numPkgbdBlks=0,bdsource=USER,synth_mode=OOC_per_IP}" *) (* HW_HANDOFF = "D_beat.hwdef" *) 
module D_beat
   (CLK,
    Rst,
    Y1,
    Y2,
    Y3,
    Y4);
  input CLK;
  input Rst;
  output Y1;
  output Y2;
  output Y3;
  output Y4;

  wire CLK_1;
  wire D_trigger_0_q;
  wire D_trigger_1_q;
  wire D_trigger_2_q;
  wire D_trigger_3_q;
  wire Rst_1;
  wire myNor_0_out;

  assign CLK_1 = CLK;
  assign Rst_1 = Rst;
  assign Y1 = D_trigger_0_q;
  assign Y2 = D_trigger_1_q;
  assign Y3 = D_trigger_2_q;
  assign Y4 = D_trigger_3_q;
  D_beat_D_trigger_0_0 D_trigger_0
       (.clk(CLK_1),
        .d(D_trigger_1_q),
        .q(D_trigger_0_q),
        .rst(Rst_1));
  D_beat_D_trigger_1_0 D_trigger_1
       (.clk(CLK_1),
        .d(D_trigger_2_q),
        .q(D_trigger_1_q),
        .rst(Rst_1));
  D_beat_D_trigger_2_0 D_trigger_2
       (.clk(CLK_1),
        .d(D_trigger_3_q),
        .q(D_trigger_2_q),
        .rst(Rst_1));
  D_beat_D_trigger_3_0 D_trigger_3
       (.clk(CLK_1),
        .d(myNor_0_out),
        .q(D_trigger_3_q),
        .rst(Rst_1));
  D_beat_myNor_0_0 myNor_0
       (.i1(D_trigger_3_q),
        .i2(D_trigger_2_q),
        .i3(D_trigger_1_q),
        .out(myNor_0_out));
endmodule
