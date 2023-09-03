// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Tue Nov 30 21:39:35 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode funcsim
//               d:/VProject/Carlight/Carlight.gen/sources_1/ip/led_0_3/led_0_sim_netlist.v
// Design      : led_0
// Purpose     : This verilog netlist is a functional simulation representation of the design and should not be modified
//               or synthesized. This netlist cannot be used for SDF annotated simulation.
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

(* CHECK_LICENSE_TYPE = "led_0,led,{}" *) (* DowngradeIPIdentifiedWarnings = "yes" *) (* IP_DEFINITION_SOURCE = "package_project" *) 
(* X_CORE_INFO = "led,Vivado 2021.1" *) 
(* NotValidForBitStream *)
module led_0
   (clk,
    a,
    led,
    up,
    down,
    switch,
    t1);
  (* X_INTERFACE_INFO = "xilinx.com:signal:clock:1.0 clk CLK" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME clk, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0" *) input clk;
  input [2:0]a;
  output [7:0]led;
  input up;
  input down;
  input switch;
  output [3:0]t1;

  wire [2:0]a;
  wire clk;
  wire down;
  wire [7:0]led;
  wire switch;
  wire [3:0]t1;
  wire up;

  led_0_led inst
       (.Q(led),
        .a(a),
        .clk(clk),
        .down(down),
        .switch(switch),
        .t1(t1),
        .up(up));
endmodule

(* ORIG_REF_NAME = "led" *) 
module led_0_led
   (t1,
    Q,
    down,
    up,
    switch,
    clk,
    a);
  output [3:0]t1;
  output [7:0]Q;
  input down;
  input up;
  input switch;
  input clk;
  input [2:0]a;

  wire [7:0]Q;
  wire [2:0]a;
  wire clk;
  wire \count[0]_i_2_n_0 ;
  wire \count[3]_i_1_n_0 ;
  wire \count[3]_i_3_n_0 ;
  wire [3:0]count_reg;
  wire down;
  wire ledtemp0;
  wire \ledtemp[0]_i_1_n_0 ;
  wire \ledtemp[0]_i_2_n_0 ;
  wire \ledtemp[1]_i_1_n_0 ;
  wire \ledtemp[1]_i_2_n_0 ;
  wire \ledtemp[1]_i_3_n_0 ;
  wire \ledtemp[2]_i_1_n_0 ;
  wire \ledtemp[2]_i_2_n_0 ;
  wire \ledtemp[3]_i_1_n_0 ;
  wire \ledtemp[4]_i_1_n_0 ;
  wire \ledtemp[5]_i_1_n_0 ;
  wire \ledtemp[5]_i_2_n_0 ;
  wire \ledtemp[5]_i_3_n_0 ;
  wire \ledtemp[6]_i_1_n_0 ;
  wire \ledtemp[6]_i_2_n_0 ;
  wire \ledtemp[6]_i_3_n_0 ;
  wire \ledtemp[6]_i_4_n_0 ;
  wire \ledtemp[7]_i_10_n_0 ;
  wire \ledtemp[7]_i_11_n_0 ;
  wire \ledtemp[7]_i_2_n_0 ;
  wire \ledtemp[7]_i_3_n_0 ;
  wire \ledtemp[7]_i_4_n_0 ;
  wire \ledtemp[7]_i_5_n_0 ;
  wire \ledtemp[7]_i_6_n_0 ;
  wire \ledtemp[7]_i_7_n_0 ;
  wire \ledtemp[7]_i_8_n_0 ;
  wire \ledtemp[7]_i_9_n_0 ;
  wire [3:0]p_0_in__0;
  wire switch;
  wire [3:0]t1;
  wire up;

  LUT4 #(
    .INIT(16'hD714)) 
    \count[0]_i_1 
       (.I0(count_reg[0]),
        .I1(t1[3]),
        .I2(count_reg[3]),
        .I3(\count[0]_i_2_n_0 ),
        .O(p_0_in__0[0]));
  LUT6 #(
    .INIT(64'h2332333333332332)) 
    \count[0]_i_2 
       (.I0(t1[0]),
        .I1(count_reg[0]),
        .I2(t1[2]),
        .I3(count_reg[2]),
        .I4(count_reg[1]),
        .I5(t1[1]),
        .O(\count[0]_i_2_n_0 ));
  LUT5 #(
    .INIT(32'h00BEBE00)) 
    \count[1]_i_1 
       (.I0(\count[3]_i_3_n_0 ),
        .I1(count_reg[3]),
        .I2(t1[3]),
        .I3(count_reg[1]),
        .I4(count_reg[0]),
        .O(p_0_in__0[1]));
  LUT6 #(
    .INIT(64'h00BEBE00BE00BE00)) 
    \count[2]_i_1 
       (.I0(\count[3]_i_3_n_0 ),
        .I1(count_reg[3]),
        .I2(t1[3]),
        .I3(count_reg[2]),
        .I4(count_reg[0]),
        .I5(count_reg[1]),
        .O(p_0_in__0[2]));
  LUT3 #(
    .INIT(8'h04)) 
    \count[3]_i_1 
       (.I0(a[1]),
        .I1(a[0]),
        .I2(a[2]),
        .O(\count[3]_i_1_n_0 ));
  LUT6 #(
    .INIT(64'h328C8C8C8C8C8C8C)) 
    \count[3]_i_2 
       (.I0(\count[3]_i_3_n_0 ),
        .I1(count_reg[3]),
        .I2(t1[3]),
        .I3(count_reg[1]),
        .I4(count_reg[0]),
        .I5(count_reg[2]),
        .O(p_0_in__0[3]));
  LUT6 #(
    .INIT(64'h6FF6FFFFFFFF6FF6)) 
    \count[3]_i_3 
       (.I0(t1[2]),
        .I1(count_reg[2]),
        .I2(t1[1]),
        .I3(count_reg[1]),
        .I4(count_reg[0]),
        .I5(t1[0]),
        .O(\count[3]_i_3_n_0 ));
  FDRE #(
    .INIT(1'b0)) 
    \count_reg[0] 
       (.C(clk),
        .CE(\count[3]_i_1_n_0 ),
        .D(p_0_in__0[0]),
        .Q(count_reg[0]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \count_reg[1] 
       (.C(clk),
        .CE(\count[3]_i_1_n_0 ),
        .D(p_0_in__0[1]),
        .Q(count_reg[1]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \count_reg[2] 
       (.C(clk),
        .CE(\count[3]_i_1_n_0 ),
        .D(p_0_in__0[2]),
        .Q(count_reg[2]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \count_reg[3] 
       (.C(clk),
        .CE(\count[3]_i_1_n_0 ),
        .D(p_0_in__0[3]),
        .Q(count_reg[3]),
        .R(1'b0));
  LUT6 #(
    .INIT(64'h88B8BBBB88B88888)) 
    \ledtemp[0]_i_1 
       (.I0(\ledtemp[7]_i_5_n_0 ),
        .I1(a[2]),
        .I2(\ledtemp[0]_i_2_n_0 ),
        .I3(a[0]),
        .I4(a[1]),
        .I5(\ledtemp[7]_i_7_n_0 ),
        .O(\ledtemp[0]_i_1_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair2" *) 
  LUT2 #(
    .INIT(4'h2)) 
    \ledtemp[0]_i_2 
       (.I0(Q[1]),
        .I1(Q[7]),
        .O(\ledtemp[0]_i_2_n_0 ));
  LUT5 #(
    .INIT(32'hB8BBB888)) 
    \ledtemp[1]_i_1 
       (.I0(\ledtemp[6]_i_2_n_0 ),
        .I1(a[2]),
        .I2(\ledtemp[1]_i_2_n_0 ),
        .I3(a[1]),
        .I4(\ledtemp[7]_i_7_n_0 ),
        .O(\ledtemp[1]_i_1_n_0 ));
  LUT6 #(
    .INIT(64'h00000000BBB888B8)) 
    \ledtemp[1]_i_2 
       (.I0(Q[0]),
        .I1(a[0]),
        .I2(\ledtemp[1]_i_3_n_0 ),
        .I3(Q[6]),
        .I4(Q[2]),
        .I5(Q[7]),
        .O(\ledtemp[1]_i_2_n_0 ));
  LUT6 #(
    .INIT(64'hFFFF0000FFFF0001)) 
    \ledtemp[1]_i_3 
       (.I0(Q[5]),
        .I1(Q[4]),
        .I2(Q[3]),
        .I3(Q[0]),
        .I4(Q[2]),
        .I5(Q[1]),
        .O(\ledtemp[1]_i_3_n_0 ));
  LUT5 #(
    .INIT(32'hB8BBB888)) 
    \ledtemp[2]_i_1 
       (.I0(\ledtemp[5]_i_2_n_0 ),
        .I1(a[2]),
        .I2(\ledtemp[2]_i_2_n_0 ),
        .I3(a[1]),
        .I4(\ledtemp[7]_i_7_n_0 ),
        .O(\ledtemp[2]_i_1_n_0 ));
  LUT4 #(
    .INIT(16'h33B8)) 
    \ledtemp[2]_i_2 
       (.I0(Q[1]),
        .I1(a[0]),
        .I2(Q[3]),
        .I3(Q[7]),
        .O(\ledtemp[2]_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h0000000000008A80)) 
    \ledtemp[3]_i_1 
       (.I0(a[1]),
        .I1(Q[2]),
        .I2(a[0]),
        .I3(Q[4]),
        .I4(Q[7]),
        .I5(a[2]),
        .O(\ledtemp[3]_i_1_n_0 ));
  LUT6 #(
    .INIT(64'h0000000000008A80)) 
    \ledtemp[4]_i_1 
       (.I0(a[1]),
        .I1(Q[3]),
        .I2(a[0]),
        .I3(Q[5]),
        .I4(Q[7]),
        .I5(a[2]),
        .O(\ledtemp[4]_i_1_n_0 ));
  LUT5 #(
    .INIT(32'hB8BBB888)) 
    \ledtemp[5]_i_1 
       (.I0(\ledtemp[5]_i_2_n_0 ),
        .I1(a[2]),
        .I2(\ledtemp[5]_i_3_n_0 ),
        .I3(a[1]),
        .I4(\ledtemp[7]_i_7_n_0 ),
        .O(\ledtemp[5]_i_1_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair1" *) 
  LUT5 #(
    .INIT(32'h8888CBC8)) 
    \ledtemp[5]_i_2 
       (.I0(a[0]),
        .I1(a[1]),
        .I2(Q[6]),
        .I3(\ledtemp[7]_i_8_n_0 ),
        .I4(Q[7]),
        .O(\ledtemp[5]_i_2_n_0 ));
  LUT4 #(
    .INIT(16'hCCB8)) 
    \ledtemp[5]_i_3 
       (.I0(Q[4]),
        .I1(a[0]),
        .I2(Q[6]),
        .I3(Q[7]),
        .O(\ledtemp[5]_i_3_n_0 ));
  LUT5 #(
    .INIT(32'hB8BBB888)) 
    \ledtemp[6]_i_1 
       (.I0(\ledtemp[6]_i_2_n_0 ),
        .I1(a[2]),
        .I2(\ledtemp[6]_i_3_n_0 ),
        .I3(a[1]),
        .I4(\ledtemp[7]_i_7_n_0 ),
        .O(\ledtemp[6]_i_1_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair1" *) 
  LUT5 #(
    .INIT(32'h44440300)) 
    \ledtemp[6]_i_2 
       (.I0(a[0]),
        .I1(a[1]),
        .I2(Q[6]),
        .I3(\ledtemp[7]_i_8_n_0 ),
        .I4(Q[7]),
        .O(\ledtemp[6]_i_2_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair0" *) 
  LUT5 #(
    .INIT(32'h55100000)) 
    \ledtemp[6]_i_3 
       (.I0(Q[7]),
        .I1(Q[6]),
        .I2(\ledtemp[6]_i_4_n_0 ),
        .I3(Q[5]),
        .I4(a[0]),
        .O(\ledtemp[6]_i_3_n_0 ));
  LUT5 #(
    .INIT(32'h00000001)) 
    \ledtemp[6]_i_4 
       (.I0(Q[3]),
        .I1(Q[1]),
        .I2(Q[2]),
        .I3(Q[0]),
        .I4(Q[4]),
        .O(\ledtemp[6]_i_4_n_0 ));
  LUT5 #(
    .INIT(32'hEEE2FFFF)) 
    \ledtemp[7]_i_1 
       (.I0(\ledtemp[7]_i_3_n_0 ),
        .I1(a[1]),
        .I2(a[0]),
        .I3(\ledtemp[7]_i_4_n_0 ),
        .I4(a[2]),
        .O(ledtemp0));
  LUT6 #(
    .INIT(64'h0000000000000004)) 
    \ledtemp[7]_i_10 
       (.I0(Q[4]),
        .I1(Q[0]),
        .I2(Q[2]),
        .I3(Q[1]),
        .I4(Q[3]),
        .I5(Q[5]),
        .O(\ledtemp[7]_i_10_n_0 ));
  LUT6 #(
    .INIT(64'h0000000041000041)) 
    \ledtemp[7]_i_11 
       (.I0(Q[0]),
        .I1(Q[6]),
        .I2(Q[1]),
        .I3(Q[5]),
        .I4(Q[2]),
        .I5(Q[3]),
        .O(\ledtemp[7]_i_11_n_0 ));
  LUT6 #(
    .INIT(64'hB888BBBBB8888888)) 
    \ledtemp[7]_i_2 
       (.I0(\ledtemp[7]_i_5_n_0 ),
        .I1(a[2]),
        .I2(\ledtemp[7]_i_6_n_0 ),
        .I3(a[0]),
        .I4(a[1]),
        .I5(\ledtemp[7]_i_7_n_0 ),
        .O(\ledtemp[7]_i_2_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair2" *) 
  LUT4 #(
    .INIT(16'hC00A)) 
    \ledtemp[7]_i_3 
       (.I0(\ledtemp[7]_i_8_n_0 ),
        .I1(\ledtemp[7]_i_9_n_0 ),
        .I2(Q[7]),
        .I3(Q[6]),
        .O(\ledtemp[7]_i_3_n_0 ));
  LUT6 #(
    .INIT(64'h0088008803BB0088)) 
    \ledtemp[7]_i_4 
       (.I0(\ledtemp[7]_i_10_n_0 ),
        .I1(Q[7]),
        .I2(Q[5]),
        .I3(Q[6]),
        .I4(\ledtemp[7]_i_11_n_0 ),
        .I5(Q[4]),
        .O(\ledtemp[7]_i_4_n_0 ));
  LUT6 #(
    .INIT(64'hC0C0C0C0C0DFC0D0)) 
    \ledtemp[7]_i_5 
       (.I0(Q[5]),
        .I1(a[0]),
        .I2(a[1]),
        .I3(Q[6]),
        .I4(\ledtemp[7]_i_8_n_0 ),
        .I5(Q[7]),
        .O(\ledtemp[7]_i_5_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair0" *) 
  LUT2 #(
    .INIT(4'h2)) 
    \ledtemp[7]_i_6 
       (.I0(Q[6]),
        .I1(Q[7]),
        .O(\ledtemp[7]_i_6_n_0 ));
  LUT4 #(
    .INIT(16'hF600)) 
    \ledtemp[7]_i_7 
       (.I0(t1[3]),
        .I1(count_reg[3]),
        .I2(\count[3]_i_3_n_0 ),
        .I3(a[0]),
        .O(\ledtemp[7]_i_7_n_0 ));
  LUT6 #(
    .INIT(64'h0000000000000001)) 
    \ledtemp[7]_i_8 
       (.I0(Q[4]),
        .I1(Q[0]),
        .I2(Q[2]),
        .I3(Q[1]),
        .I4(Q[3]),
        .I5(Q[5]),
        .O(\ledtemp[7]_i_8_n_0 ));
  LUT6 #(
    .INIT(64'h0000400000000000)) 
    \ledtemp[7]_i_9 
       (.I0(Q[4]),
        .I1(Q[0]),
        .I2(Q[2]),
        .I3(Q[1]),
        .I4(Q[3]),
        .I5(Q[5]),
        .O(\ledtemp[7]_i_9_n_0 ));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[0] 
       (.C(clk),
        .CE(ledtemp0),
        .D(\ledtemp[0]_i_1_n_0 ),
        .Q(Q[0]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[1] 
       (.C(clk),
        .CE(ledtemp0),
        .D(\ledtemp[1]_i_1_n_0 ),
        .Q(Q[1]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[2] 
       (.C(clk),
        .CE(ledtemp0),
        .D(\ledtemp[2]_i_1_n_0 ),
        .Q(Q[2]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[3] 
       (.C(clk),
        .CE(ledtemp0),
        .D(\ledtemp[3]_i_1_n_0 ),
        .Q(Q[3]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[4] 
       (.C(clk),
        .CE(ledtemp0),
        .D(\ledtemp[4]_i_1_n_0 ),
        .Q(Q[4]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[5] 
       (.C(clk),
        .CE(ledtemp0),
        .D(\ledtemp[5]_i_1_n_0 ),
        .Q(Q[5]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[6] 
       (.C(clk),
        .CE(ledtemp0),
        .D(\ledtemp[6]_i_1_n_0 ),
        .Q(Q[6]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[7] 
       (.C(clk),
        .CE(ledtemp0),
        .D(\ledtemp[7]_i_2_n_0 ),
        .Q(Q[7]),
        .R(1'b0));
  led_0_settime s
       (.Q(t1),
        .down(down),
        .switch(switch),
        .up(up));
endmodule

(* ORIG_REF_NAME = "settime" *) 
module led_0_settime
   (Q,
    switch,
    down,
    up);
  output [3:0]Q;
  input switch;
  input down;
  input up;

  wire [3:0]Q;
  wire \c1[0]_i_1_n_0 ;
  wire \c1[1]__0_i_1_n_0 ;
  wire \c1[1]_i_1_n_0 ;
  wire \c1[2]__0_i_1_n_0 ;
  wire \c1[2]_i_1_n_0 ;
  wire \c1[3]__0_i_1_n_0 ;
  wire \c1[3]_i_1_n_0 ;
  wire down;
  wire p_0_in;
  wire s;
  wire switch;
  wire up;

  LUT1 #(
    .INIT(2'h1)) 
    \c1[0]_i_1 
       (.I0(Q[0]),
        .O(\c1[0]_i_1_n_0 ));
  LUT2 #(
    .INIT(4'h6)) 
    \c1[1]__0_i_1 
       (.I0(Q[0]),
        .I1(Q[1]),
        .O(\c1[1]__0_i_1_n_0 ));
  LUT2 #(
    .INIT(4'h9)) 
    \c1[1]_i_1 
       (.I0(Q[1]),
        .I1(Q[0]),
        .O(\c1[1]_i_1_n_0 ));
  LUT3 #(
    .INIT(8'h78)) 
    \c1[2]__0_i_1 
       (.I0(Q[0]),
        .I1(Q[1]),
        .I2(Q[2]),
        .O(\c1[2]__0_i_1_n_0 ));
  LUT3 #(
    .INIT(8'hE1)) 
    \c1[2]_i_1 
       (.I0(Q[0]),
        .I1(Q[1]),
        .I2(Q[2]),
        .O(\c1[2]_i_1_n_0 ));
  LUT4 #(
    .INIT(16'h7F80)) 
    \c1[3]__0_i_1 
       (.I0(Q[2]),
        .I1(Q[1]),
        .I2(Q[0]),
        .I3(Q[3]),
        .O(\c1[3]__0_i_1_n_0 ));
  LUT4 #(
    .INIT(16'hFE01)) 
    \c1[3]_i_1 
       (.I0(Q[2]),
        .I1(Q[1]),
        .I2(Q[0]),
        .I3(Q[3]),
        .O(\c1[3]_i_1_n_0 ));
  FDRE #(
    .INIT(1'b0)) 
    \c1_reg[0] 
       (.C(down),
        .CE(s),
        .D(\c1[0]_i_1_n_0 ),
        .Q(Q[0]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \c1_reg[0]__0 
       (.C(up),
        .CE(s),
        .D(\c1[0]_i_1_n_0 ),
        .Q(Q[0]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b1)) 
    \c1_reg[1] 
       (.C(down),
        .CE(s),
        .D(\c1[1]_i_1_n_0 ),
        .Q(Q[1]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b1)) 
    \c1_reg[1]__0 
       (.C(up),
        .CE(s),
        .D(\c1[1]__0_i_1_n_0 ),
        .Q(Q[1]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \c1_reg[2] 
       (.C(down),
        .CE(s),
        .D(\c1[2]_i_1_n_0 ),
        .Q(Q[2]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \c1_reg[2]__0 
       (.C(up),
        .CE(s),
        .D(\c1[2]__0_i_1_n_0 ),
        .Q(Q[2]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b1)) 
    \c1_reg[3] 
       (.C(down),
        .CE(s),
        .D(\c1[3]_i_1_n_0 ),
        .Q(Q[3]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b1)) 
    \c1_reg[3]__0 
       (.C(up),
        .CE(s),
        .D(\c1[3]__0_i_1_n_0 ),
        .Q(Q[3]),
        .R(1'b0));
  LUT1 #(
    .INIT(2'h1)) 
    s_i_1
       (.I0(s),
        .O(p_0_in));
  FDRE #(
    .INIT(1'b0)) 
    s_reg
       (.C(switch),
        .CE(1'b1),
        .D(p_0_in),
        .Q(s),
        .R(1'b0));
endmodule
`ifndef GLBL
`define GLBL
`timescale  1 ps / 1 ps

module glbl ();

    parameter ROC_WIDTH = 100000;
    parameter TOC_WIDTH = 0;
    parameter GRES_WIDTH = 10000;
    parameter GRES_START = 10000;

//--------   STARTUP Globals --------------
    wire GSR;
    wire GTS;
    wire GWE;
    wire PRLD;
    wire GRESTORE;
    tri1 p_up_tmp;
    tri (weak1, strong0) PLL_LOCKG = p_up_tmp;

    wire PROGB_GLBL;
    wire CCLKO_GLBL;
    wire FCSBO_GLBL;
    wire [3:0] DO_GLBL;
    wire [3:0] DI_GLBL;
   
    reg GSR_int;
    reg GTS_int;
    reg PRLD_int;
    reg GRESTORE_int;

//--------   JTAG Globals --------------
    wire JTAG_TDO_GLBL;
    wire JTAG_TCK_GLBL;
    wire JTAG_TDI_GLBL;
    wire JTAG_TMS_GLBL;
    wire JTAG_TRST_GLBL;

    reg JTAG_CAPTURE_GLBL;
    reg JTAG_RESET_GLBL;
    reg JTAG_SHIFT_GLBL;
    reg JTAG_UPDATE_GLBL;
    reg JTAG_RUNTEST_GLBL;

    reg JTAG_SEL1_GLBL = 0;
    reg JTAG_SEL2_GLBL = 0 ;
    reg JTAG_SEL3_GLBL = 0;
    reg JTAG_SEL4_GLBL = 0;

    reg JTAG_USER_TDO1_GLBL = 1'bz;
    reg JTAG_USER_TDO2_GLBL = 1'bz;
    reg JTAG_USER_TDO3_GLBL = 1'bz;
    reg JTAG_USER_TDO4_GLBL = 1'bz;

    assign (strong1, weak0) GSR = GSR_int;
    assign (strong1, weak0) GTS = GTS_int;
    assign (weak1, weak0) PRLD = PRLD_int;
    assign (strong1, weak0) GRESTORE = GRESTORE_int;

    initial begin
	GSR_int = 1'b1;
	PRLD_int = 1'b1;
	#(ROC_WIDTH)
	GSR_int = 1'b0;
	PRLD_int = 1'b0;
    end

    initial begin
	GTS_int = 1'b1;
	#(TOC_WIDTH)
	GTS_int = 1'b0;
    end

    initial begin 
	GRESTORE_int = 1'b0;
	#(GRES_START);
	GRESTORE_int = 1'b1;
	#(GRES_WIDTH);
	GRESTORE_int = 1'b0;
    end

endmodule
`endif
