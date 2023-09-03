// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Wed Dec  1 17:29:14 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode funcsim -rename_top decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix -prefix
//               decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_ led_0_sim_netlist.v
// Design      : led_0
// Purpose     : This verilog netlist is a functional simulation representation of the design and should not be modified
//               or synthesized. This netlist cannot be used for SDF annotated simulation.
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_led
   (t1,
    Q,
    a,
    clk,
    switch,
    up);
  output [3:0]t1;
  output [5:0]Q;
  input [2:0]a;
  input clk;
  input switch;
  input up;

  wire [5:0]Q;
  wire [2:0]a;
  wire clk;
  wire \count[1]_i_2_n_0 ;
  wire \count[2]_i_2_n_0 ;
  wire \count_reg_n_0_[0] ;
  wire \count_reg_n_0_[1] ;
  wire \count_reg_n_0_[2] ;
  wire \count_reg_n_0_[3] ;
  wire \ledtemp[0]_i_2_n_0 ;
  wire \ledtemp[1]_i_2_n_0 ;
  wire \ledtemp[5]_i_2_n_0 ;
  wire \ledtemp[5]_i_3_n_0 ;
  wire \ledtemp[5]_i_4_n_0 ;
  wire \ledtemp[6]_i_2_n_0 ;
  wire \ledtemp[6]_i_3_n_0 ;
  wire \ledtemp[7]_i_2_n_0 ;
  wire s_n_10;
  wire s_n_11;
  wire s_n_12;
  wire s_n_13;
  wire s_n_4;
  wire s_n_5;
  wire s_n_6;
  wire s_n_7;
  wire s_n_8;
  wire s_n_9;
  wire switch;
  wire [3:0]t1;
  wire up;

  (* SOFT_HLUTNM = "soft_lutpair4" *) 
  LUT2 #(
    .INIT(4'h6)) 
    \count[1]_i_2 
       (.I0(\count_reg_n_0_[0] ),
        .I1(\count_reg_n_0_[1] ),
        .O(\count[1]_i_2_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair4" *) 
  LUT3 #(
    .INIT(8'h6A)) 
    \count[2]_i_2 
       (.I0(\count_reg_n_0_[2] ),
        .I1(\count_reg_n_0_[0] ),
        .I2(\count_reg_n_0_[1] ),
        .O(\count[2]_i_2_n_0 ));
  FDRE #(
    .INIT(1'b0)) 
    \count_reg[0] 
       (.C(clk),
        .CE(1'b1),
        .D(s_n_13),
        .Q(\count_reg_n_0_[0] ),
        .R(a[1]));
  FDRE #(
    .INIT(1'b0)) 
    \count_reg[1] 
       (.C(clk),
        .CE(1'b1),
        .D(s_n_12),
        .Q(\count_reg_n_0_[1] ),
        .R(a[1]));
  FDRE #(
    .INIT(1'b0)) 
    \count_reg[2] 
       (.C(clk),
        .CE(1'b1),
        .D(s_n_10),
        .Q(\count_reg_n_0_[2] ),
        .R(a[1]));
  FDRE #(
    .INIT(1'b0)) 
    \count_reg[3] 
       (.C(clk),
        .CE(1'b1),
        .D(s_n_11),
        .Q(\count_reg_n_0_[3] ),
        .R(a[1]));
  LUT6 #(
    .INIT(64'h0000000000000010)) 
    \ledtemp[0]_i_2 
       (.I0(Q[5]),
        .I1(Q[2]),
        .I2(Q[1]),
        .I3(Q[0]),
        .I4(Q[3]),
        .I5(Q[4]),
        .O(\ledtemp[0]_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h0000000000000004)) 
    \ledtemp[1]_i_2 
       (.I0(Q[5]),
        .I1(Q[2]),
        .I2(Q[0]),
        .I3(Q[1]),
        .I4(Q[3]),
        .I5(Q[4]),
        .O(\ledtemp[1]_i_2_n_0 ));
  LUT3 #(
    .INIT(8'hF8)) 
    \ledtemp[5]_i_2 
       (.I0(Q[4]),
        .I1(\ledtemp[5]_i_4_n_0 ),
        .I2(a[0]),
        .O(\ledtemp[5]_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h0000000000000001)) 
    \ledtemp[5]_i_3 
       (.I0(Q[5]),
        .I1(Q[2]),
        .I2(Q[0]),
        .I3(Q[1]),
        .I4(Q[3]),
        .I5(Q[4]),
        .O(\ledtemp[5]_i_3_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair3" *) 
  LUT5 #(
    .INIT(32'h00000010)) 
    \ledtemp[5]_i_4 
       (.I0(Q[3]),
        .I1(Q[0]),
        .I2(Q[1]),
        .I3(Q[2]),
        .I4(Q[5]),
        .O(\ledtemp[5]_i_4_n_0 ));
  LUT6 #(
    .INIT(64'h0000000000000020)) 
    \ledtemp[6]_i_2 
       (.I0(Q[5]),
        .I1(Q[2]),
        .I2(Q[0]),
        .I3(Q[1]),
        .I4(Q[3]),
        .I5(Q[4]),
        .O(\ledtemp[6]_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h0000000000010000)) 
    \ledtemp[6]_i_3 
       (.I0(Q[5]),
        .I1(Q[2]),
        .I2(Q[0]),
        .I3(Q[1]),
        .I4(Q[3]),
        .I5(Q[4]),
        .O(\ledtemp[6]_i_3_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair3" *) 
  LUT5 #(
    .INIT(32'h00000001)) 
    \ledtemp[7]_i_2 
       (.I0(Q[3]),
        .I1(Q[1]),
        .I2(Q[0]),
        .I3(Q[2]),
        .I4(Q[5]),
        .O(\ledtemp[7]_i_2_n_0 ));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[0] 
       (.C(clk),
        .CE(1'b1),
        .D(s_n_9),
        .Q(Q[0]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[1] 
       (.C(clk),
        .CE(1'b1),
        .D(s_n_8),
        .Q(Q[1]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[2] 
       (.C(clk),
        .CE(1'b1),
        .D(s_n_7),
        .Q(Q[2]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[5] 
       (.C(clk),
        .CE(1'b1),
        .D(s_n_6),
        .Q(Q[3]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[6] 
       (.C(clk),
        .CE(1'b1),
        .D(s_n_5),
        .Q(Q[4]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \ledtemp_reg[7] 
       (.C(clk),
        .CE(1'b1),
        .D(s_n_4),
        .Q(Q[5]),
        .R(1'b0));
  decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_settime s
       (.D({s_n_4,s_n_5,s_n_6,s_n_7,s_n_8,s_n_9}),
        .Q(t1),
        .a(a),
        .\a[0]_0 (s_n_11),
        .\a[0]_1 (s_n_12),
        .\a[0]_2 (s_n_13),
        .a_0_sp_1(s_n_10),
        .\count_reg[0] (\count_reg_n_0_[0] ),
        .\count_reg[1] (\count[1]_i_2_n_0 ),
        .\count_reg[2] (\count_reg_n_0_[3] ),
        .\count_reg[2]_0 (\count[2]_i_2_n_0 ),
        .\count_reg[2]_1 (\count_reg_n_0_[2] ),
        .\count_reg[2]_2 (\count_reg_n_0_[1] ),
        .\ledtemp_reg[0] (\ledtemp[5]_i_3_n_0 ),
        .\ledtemp_reg[0]_0 (\ledtemp[0]_i_2_n_0 ),
        .\ledtemp_reg[1] (\ledtemp[1]_i_2_n_0 ),
        .\ledtemp_reg[2] (\ledtemp[5]_i_2_n_0 ),
        .\ledtemp_reg[6] (\ledtemp[6]_i_2_n_0 ),
        .\ledtemp_reg[6]_0 (\ledtemp[6]_i_3_n_0 ),
        .\ledtemp_reg[7] (Q[4]),
        .\ledtemp_reg[7]_0 (\ledtemp[7]_i_2_n_0 ),
        .switch(switch),
        .up(up));
endmodule

(* CHECK_LICENSE_TYPE = "led_0,led,{}" *) (* DowngradeIPIdentifiedWarnings = "yes" *) (* IP_DEFINITION_SOURCE = "package_project" *) 
(* X_CORE_INFO = "led,Vivado 2021.1" *) 
(* NotValidForBitStream *)
module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix
   (clk,
    a,
    led,
    up,
    switch,
    t1);
  (* X_INTERFACE_INFO = "xilinx.com:signal:clock:1.0 clk CLK" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME clk, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0" *) input clk;
  input [2:0]a;
  output [7:0]led;
  input up;
  input switch;
  output [3:0]t1;

  wire \<const0> ;
  wire [2:0]a;
  wire clk;
  wire [7:0]\^led ;
  wire switch;
  wire [3:0]t1;
  wire up;

  assign led[7:5] = \^led [7:5];
  assign led[4] = \<const0> ;
  assign led[3] = \<const0> ;
  assign led[2:0] = \^led [2:0];
  GND GND
       (.G(\<const0> ));
  decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_led inst
       (.Q({\^led [7:5],\^led [2:0]}),
        .a(a),
        .clk(clk),
        .switch(switch),
        .t1(t1),
        .up(up));
endmodule

module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_settime
   (Q,
    D,
    a_0_sp_1,
    \a[0]_0 ,
    \a[0]_1 ,
    \a[0]_2 ,
    switch,
    \ledtemp_reg[6] ,
    a,
    \ledtemp_reg[6]_0 ,
    \ledtemp_reg[1] ,
    \count_reg[2] ,
    \count_reg[2]_0 ,
    \count_reg[2]_1 ,
    \count_reg[2]_2 ,
    \count_reg[0] ,
    \count_reg[1] ,
    \ledtemp_reg[0] ,
    \ledtemp_reg[0]_0 ,
    \ledtemp_reg[2] ,
    \ledtemp_reg[7] ,
    \ledtemp_reg[7]_0 ,
    up);
  output [3:0]Q;
  output [5:0]D;
  output a_0_sp_1;
  output \a[0]_0 ;
  output \a[0]_1 ;
  output \a[0]_2 ;
  input switch;
  input \ledtemp_reg[6] ;
  input [2:0]a;
  input \ledtemp_reg[6]_0 ;
  input \ledtemp_reg[1] ;
  input \count_reg[2] ;
  input \count_reg[2]_0 ;
  input \count_reg[2]_1 ;
  input \count_reg[2]_2 ;
  input \count_reg[0] ;
  input \count_reg[1] ;
  input \ledtemp_reg[0] ;
  input \ledtemp_reg[0]_0 ;
  input \ledtemp_reg[2] ;
  input [0:0]\ledtemp_reg[7] ;
  input \ledtemp_reg[7]_0 ;
  input up;

  wire [5:0]D;
  wire [3:0]Q;
  wire [2:0]a;
  wire \a[0]_0 ;
  wire \a[0]_1 ;
  wire \a[0]_2 ;
  wire a_0_sn_1;
  wire \count[0]_i_2_n_0 ;
  wire \count[1]_i_3_n_0 ;
  wire \count[2]_i_3_n_0 ;
  wire \count[3]_i_2_n_0 ;
  wire \count[3]_i_3_n_0 ;
  wire \count[3]_i_4_n_0 ;
  wire \count[3]_i_5_n_0 ;
  wire \count_reg[0] ;
  wire \count_reg[1] ;
  wire \count_reg[2] ;
  wire \count_reg[2]_0 ;
  wire \count_reg[2]_1 ;
  wire \count_reg[2]_2 ;
  wire \ledtemp[7]_i_3_n_0 ;
  wire \ledtemp[7]_i_4_n_0 ;
  wire \ledtemp_reg[0] ;
  wire \ledtemp_reg[0]_0 ;
  wire \ledtemp_reg[1] ;
  wire \ledtemp_reg[2] ;
  wire \ledtemp_reg[6] ;
  wire \ledtemp_reg[6]_0 ;
  wire [0:0]\ledtemp_reg[7] ;
  wire \ledtemp_reg[7]_0 ;
  wire p_0_in;
  wire [3:0]p_0_in__0;
  wire s;
  wire switch;
  wire up;

  assign a_0_sp_1 = a_0_sn_1;
  (* SOFT_HLUTNM = "soft_lutpair2" *) 
  LUT1 #(
    .INIT(2'h1)) 
    \c1[0]_i_1 
       (.I0(Q[0]),
        .O(p_0_in__0[0]));
  (* SOFT_HLUTNM = "soft_lutpair2" *) 
  LUT2 #(
    .INIT(4'h6)) 
    \c1[1]_i_1 
       (.I0(Q[1]),
        .I1(Q[0]),
        .O(p_0_in__0[1]));
  (* SOFT_HLUTNM = "soft_lutpair0" *) 
  LUT3 #(
    .INIT(8'h6A)) 
    \c1[2]_i_1 
       (.I0(Q[2]),
        .I1(Q[0]),
        .I2(Q[1]),
        .O(p_0_in__0[2]));
  (* SOFT_HLUTNM = "soft_lutpair0" *) 
  LUT4 #(
    .INIT(16'h6AAA)) 
    \c1[3]_i_1 
       (.I0(Q[3]),
        .I1(Q[0]),
        .I2(Q[1]),
        .I3(Q[2]),
        .O(p_0_in__0[3]));
  FDRE #(
    .INIT(1'b0)) 
    \c1_reg[0] 
       (.C(up),
        .CE(s),
        .D(p_0_in__0[0]),
        .Q(Q[0]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b1)) 
    \c1_reg[1] 
       (.C(up),
        .CE(s),
        .D(p_0_in__0[1]),
        .Q(Q[1]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \c1_reg[2] 
       (.C(up),
        .CE(s),
        .D(p_0_in__0[2]),
        .Q(Q[2]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b1)) 
    \c1_reg[3] 
       (.C(up),
        .CE(s),
        .D(p_0_in__0[3]),
        .Q(Q[3]),
        .R(1'b0));
  LUT6 #(
    .INIT(64'h000000008A2A0208)) 
    \count[0]_i_1 
       (.I0(a[0]),
        .I1(\count_reg[2] ),
        .I2(\count_reg[0] ),
        .I3(Q[3]),
        .I4(\count[0]_i_2_n_0 ),
        .I5(a[2]),
        .O(\a[0]_2 ));
  LUT6 #(
    .INIT(64'h9F0F060F0F9F0F06)) 
    \count[0]_i_2 
       (.I0(\count_reg[2]_1 ),
        .I1(Q[2]),
        .I2(\count_reg[0] ),
        .I3(\count_reg[2]_2 ),
        .I4(Q[0]),
        .I5(Q[1]),
        .O(\count[0]_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h00000000A8A22080)) 
    \count[1]_i_1 
       (.I0(a[0]),
        .I1(\count_reg[2] ),
        .I2(\count_reg[1] ),
        .I3(Q[3]),
        .I4(\count[1]_i_3_n_0 ),
        .I5(a[2]),
        .O(\a[0]_1 ));
  LUT6 #(
    .INIT(64'h9000FFFFF6FF0000)) 
    \count[1]_i_3 
       (.I0(\count_reg[2]_1 ),
        .I1(Q[2]),
        .I2(Q[1]),
        .I3(Q[0]),
        .I4(\count_reg[0] ),
        .I5(\count_reg[2]_2 ),
        .O(\count[1]_i_3_n_0 ));
  LUT6 #(
    .INIT(64'h00000000A8A22080)) 
    \count[2]_i_1 
       (.I0(a[0]),
        .I1(\count_reg[2] ),
        .I2(\count_reg[2]_0 ),
        .I3(Q[3]),
        .I4(\count[2]_i_3_n_0 ),
        .I5(a[2]),
        .O(a_0_sn_1));
  LUT6 #(
    .INIT(64'hCA5A5A5AAAAAAAAA)) 
    \count[2]_i_3 
       (.I0(\count_reg[2]_1 ),
        .I1(Q[2]),
        .I2(\count_reg[2]_2 ),
        .I3(Q[0]),
        .I4(Q[1]),
        .I5(\count_reg[0] ),
        .O(\count[2]_i_3_n_0 ));
  LUT5 #(
    .INIT(32'h00008A80)) 
    \count[3]_i_1 
       (.I0(a[0]),
        .I1(\count[3]_i_2_n_0 ),
        .I2(\count_reg[2] ),
        .I3(\count[3]_i_3_n_0 ),
        .I4(a[2]),
        .O(\a[0]_0 ));
  LUT6 #(
    .INIT(64'h80FFFFFFFFFFFFFF)) 
    \count[3]_i_2 
       (.I0(Q[2]),
        .I1(\count[3]_i_4_n_0 ),
        .I2(Q[3]),
        .I3(\count_reg[2]_2 ),
        .I4(\count_reg[0] ),
        .I5(\count_reg[2]_1 ),
        .O(\count[3]_i_2_n_0 ));
  LUT6 #(
    .INIT(64'hC0C080C000000000)) 
    \count[3]_i_3 
       (.I0(Q[3]),
        .I1(\count_reg[0] ),
        .I2(\count_reg[2]_2 ),
        .I3(Q[2]),
        .I4(\count[3]_i_5_n_0 ),
        .I5(\count_reg[2]_1 ),
        .O(\count[3]_i_3_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair1" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \count[3]_i_4 
       (.I0(Q[1]),
        .I1(Q[0]),
        .O(\count[3]_i_4_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair1" *) 
  LUT2 #(
    .INIT(4'h7)) 
    \count[3]_i_5 
       (.I0(Q[0]),
        .I1(Q[1]),
        .O(\count[3]_i_5_n_0 ));
  LUT6 #(
    .INIT(64'hCCB8FFFFCCB80000)) 
    \ledtemp[0]_i_1 
       (.I0(\ledtemp_reg[0] ),
        .I1(a[2]),
        .I2(\ledtemp_reg[0]_0 ),
        .I3(a[0]),
        .I4(a[1]),
        .I5(\ledtemp[7]_i_3_n_0 ),
        .O(D[0]));
  LUT6 #(
    .INIT(64'h2320FFFF23200000)) 
    \ledtemp[1]_i_1 
       (.I0(\ledtemp_reg[6] ),
        .I1(a[0]),
        .I2(a[2]),
        .I3(\ledtemp_reg[1] ),
        .I4(a[1]),
        .I5(\ledtemp[7]_i_3_n_0 ),
        .O(D[1]));
  LUT6 #(
    .INIT(64'h88B8FFFF88B80000)) 
    \ledtemp[2]_i_1 
       (.I0(\ledtemp_reg[2] ),
        .I1(a[2]),
        .I2(\ledtemp_reg[0] ),
        .I3(a[0]),
        .I4(a[1]),
        .I5(\ledtemp[7]_i_3_n_0 ),
        .O(D[2]));
  LUT6 #(
    .INIT(64'hB888FFFFB8880000)) 
    \ledtemp[5]_i_1 
       (.I0(\ledtemp_reg[2] ),
        .I1(a[2]),
        .I2(\ledtemp_reg[0] ),
        .I3(a[0]),
        .I4(a[1]),
        .I5(\ledtemp[7]_i_3_n_0 ),
        .O(D[3]));
  LUT6 #(
    .INIT(64'h2C20FFFF2C200000)) 
    \ledtemp[6]_i_1 
       (.I0(\ledtemp_reg[6] ),
        .I1(a[0]),
        .I2(a[2]),
        .I3(\ledtemp_reg[6]_0 ),
        .I4(a[1]),
        .I5(\ledtemp[7]_i_3_n_0 ),
        .O(D[4]));
  LUT6 #(
    .INIT(64'hEA20FFFFEA200000)) 
    \ledtemp[7]_i_1 
       (.I0(a[2]),
        .I1(\ledtemp_reg[7] ),
        .I2(\ledtemp_reg[7]_0 ),
        .I3(a[0]),
        .I4(a[1]),
        .I5(\ledtemp[7]_i_3_n_0 ),
        .O(D[5]));
  LUT6 #(
    .INIT(64'hBB8BBBB888888888)) 
    \ledtemp[7]_i_3 
       (.I0(\ledtemp_reg[0] ),
        .I1(a[2]),
        .I2(Q[3]),
        .I3(\ledtemp[7]_i_4_n_0 ),
        .I4(\count_reg[2] ),
        .I5(a[0]),
        .O(\ledtemp[7]_i_3_n_0 ));
  LUT6 #(
    .INIT(64'h7FDFF7FDBFEFFBFE)) 
    \ledtemp[7]_i_4 
       (.I0(\count_reg[2]_1 ),
        .I1(Q[1]),
        .I2(Q[0]),
        .I3(\count_reg[2]_2 ),
        .I4(\count_reg[0] ),
        .I5(Q[2]),
        .O(\ledtemp[7]_i_4_n_0 ));
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
