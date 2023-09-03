//Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
//--------------------------------------------------------------------------------
//Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
//Date        : Fri Oct 29 19:03:48 2021
//Host        : MateBook14 running 64-bit major release  (build 9200)
//Command     : generate_target D_beat_wrapper.bd
//Design      : D_beat_wrapper
//Purpose     : IP block netlist
//--------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module D_beat_wrapper
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

  wire CLK;
  wire Rst;
  wire Y1;
  wire Y2;
  wire Y3;
  wire Y4;

  D_beat D_beat_i
       (.CLK(CLK),
        .Rst(Rst),
        .Y1(Y1),
        .Y2(Y2),
        .Y3(Y3),
        .Y4(Y4));
endmodule
