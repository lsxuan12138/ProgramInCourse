// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Mon Nov 29 19:15:35 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode funcsim -rename_top decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix -prefix
//               decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_ divClk_0_sim_netlist.v
// Design      : divClk_0
// Purpose     : This verilog netlist is a functional simulation representation of the design and should not be modified
//               or synthesized. This netlist cannot be used for SDF annotated simulation.
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_divClk
   (Clk,
    s1,
    s0,
    divClk);
  input Clk;
  input s1;
  input s0;
  output divClk;

  wire Clk;
  wire \counter[0]_i_10_n_0 ;
  wire \counter[0]_i_11_n_0 ;
  wire \counter[0]_i_12_n_0 ;
  wire \counter[0]_i_13_n_0 ;
  wire \counter[0]_i_15_n_0 ;
  wire \counter[0]_i_16_n_0 ;
  wire \counter[0]_i_17_n_0 ;
  wire \counter[0]_i_18_n_0 ;
  wire \counter[0]_i_19_n_0 ;
  wire \counter[0]_i_1_n_0 ;
  wire \counter[0]_i_20_n_0 ;
  wire \counter[0]_i_21_n_0 ;
  wire \counter[0]_i_22_n_0 ;
  wire \counter[0]_i_24_n_0 ;
  wire \counter[0]_i_25_n_0 ;
  wire \counter[0]_i_26_n_0 ;
  wire \counter[0]_i_27_n_0 ;
  wire \counter[0]_i_28_n_0 ;
  wire \counter[0]_i_29_n_0 ;
  wire \counter[0]_i_30_n_0 ;
  wire \counter[0]_i_31_n_0 ;
  wire \counter[0]_i_32_n_0 ;
  wire \counter[0]_i_33_n_0 ;
  wire \counter[0]_i_34_n_0 ;
  wire \counter[0]_i_35_n_0 ;
  wire \counter[0]_i_36_n_0 ;
  wire \counter[0]_i_37_n_0 ;
  wire \counter[0]_i_38_n_0 ;
  wire \counter[0]_i_39_n_0 ;
  wire \counter[0]_i_4_n_0 ;
  wire \counter[0]_i_6_n_0 ;
  wire \counter[0]_i_7_n_0 ;
  wire \counter[0]_i_8_n_0 ;
  wire \counter[0]_i_9_n_0 ;
  wire [31:0]counter_reg;
  wire \counter_reg[0]_i_14_n_0 ;
  wire \counter_reg[0]_i_14_n_1 ;
  wire \counter_reg[0]_i_14_n_2 ;
  wire \counter_reg[0]_i_14_n_3 ;
  wire \counter_reg[0]_i_23_n_0 ;
  wire \counter_reg[0]_i_23_n_1 ;
  wire \counter_reg[0]_i_23_n_2 ;
  wire \counter_reg[0]_i_23_n_3 ;
  wire \counter_reg[0]_i_2_n_0 ;
  wire \counter_reg[0]_i_2_n_1 ;
  wire \counter_reg[0]_i_2_n_2 ;
  wire \counter_reg[0]_i_2_n_3 ;
  wire \counter_reg[0]_i_2_n_4 ;
  wire \counter_reg[0]_i_2_n_5 ;
  wire \counter_reg[0]_i_2_n_6 ;
  wire \counter_reg[0]_i_2_n_7 ;
  wire \counter_reg[0]_i_3_n_0 ;
  wire \counter_reg[0]_i_3_n_1 ;
  wire \counter_reg[0]_i_3_n_2 ;
  wire \counter_reg[0]_i_3_n_3 ;
  wire \counter_reg[0]_i_5_n_0 ;
  wire \counter_reg[0]_i_5_n_1 ;
  wire \counter_reg[0]_i_5_n_2 ;
  wire \counter_reg[0]_i_5_n_3 ;
  wire \counter_reg[12]_i_1_n_0 ;
  wire \counter_reg[12]_i_1_n_1 ;
  wire \counter_reg[12]_i_1_n_2 ;
  wire \counter_reg[12]_i_1_n_3 ;
  wire \counter_reg[12]_i_1_n_4 ;
  wire \counter_reg[12]_i_1_n_5 ;
  wire \counter_reg[12]_i_1_n_6 ;
  wire \counter_reg[12]_i_1_n_7 ;
  wire \counter_reg[16]_i_1_n_0 ;
  wire \counter_reg[16]_i_1_n_1 ;
  wire \counter_reg[16]_i_1_n_2 ;
  wire \counter_reg[16]_i_1_n_3 ;
  wire \counter_reg[16]_i_1_n_4 ;
  wire \counter_reg[16]_i_1_n_5 ;
  wire \counter_reg[16]_i_1_n_6 ;
  wire \counter_reg[16]_i_1_n_7 ;
  wire \counter_reg[20]_i_1_n_0 ;
  wire \counter_reg[20]_i_1_n_1 ;
  wire \counter_reg[20]_i_1_n_2 ;
  wire \counter_reg[20]_i_1_n_3 ;
  wire \counter_reg[20]_i_1_n_4 ;
  wire \counter_reg[20]_i_1_n_5 ;
  wire \counter_reg[20]_i_1_n_6 ;
  wire \counter_reg[20]_i_1_n_7 ;
  wire \counter_reg[24]_i_1_n_0 ;
  wire \counter_reg[24]_i_1_n_1 ;
  wire \counter_reg[24]_i_1_n_2 ;
  wire \counter_reg[24]_i_1_n_3 ;
  wire \counter_reg[24]_i_1_n_4 ;
  wire \counter_reg[24]_i_1_n_5 ;
  wire \counter_reg[24]_i_1_n_6 ;
  wire \counter_reg[24]_i_1_n_7 ;
  wire \counter_reg[28]_i_1_n_1 ;
  wire \counter_reg[28]_i_1_n_2 ;
  wire \counter_reg[28]_i_1_n_3 ;
  wire \counter_reg[28]_i_1_n_4 ;
  wire \counter_reg[28]_i_1_n_5 ;
  wire \counter_reg[28]_i_1_n_6 ;
  wire \counter_reg[28]_i_1_n_7 ;
  wire \counter_reg[4]_i_1_n_0 ;
  wire \counter_reg[4]_i_1_n_1 ;
  wire \counter_reg[4]_i_1_n_2 ;
  wire \counter_reg[4]_i_1_n_3 ;
  wire \counter_reg[4]_i_1_n_4 ;
  wire \counter_reg[4]_i_1_n_5 ;
  wire \counter_reg[4]_i_1_n_6 ;
  wire \counter_reg[4]_i_1_n_7 ;
  wire \counter_reg[8]_i_1_n_0 ;
  wire \counter_reg[8]_i_1_n_1 ;
  wire \counter_reg[8]_i_1_n_2 ;
  wire \counter_reg[8]_i_1_n_3 ;
  wire \counter_reg[8]_i_1_n_4 ;
  wire \counter_reg[8]_i_1_n_5 ;
  wire \counter_reg[8]_i_1_n_6 ;
  wire \counter_reg[8]_i_1_n_7 ;
  wire divClk;
  wire divClk_i_10_n_0;
  wire divClk_i_11_n_0;
  wire divClk_i_12_n_0;
  wire divClk_i_13_n_0;
  wire divClk_i_14_n_0;
  wire divClk_i_15_n_0;
  wire divClk_i_1_n_0;
  wire divClk_i_4_n_0;
  wire divClk_i_5_n_0;
  wire divClk_i_6_n_0;
  wire divClk_i_8_n_0;
  wire divClk_i_9_n_0;
  wire divClk_reg_i_2_n_1;
  wire divClk_reg_i_2_n_2;
  wire divClk_reg_i_2_n_3;
  wire divClk_reg_i_3_n_0;
  wire divClk_reg_i_3_n_1;
  wire divClk_reg_i_3_n_2;
  wire divClk_reg_i_3_n_3;
  wire divClk_reg_i_7_n_0;
  wire divClk_reg_i_7_n_1;
  wire divClk_reg_i_7_n_2;
  wire divClk_reg_i_7_n_3;
  wire s0;
  wire s1;
  wire [3:0]\NLW_counter_reg[0]_i_14_O_UNCONNECTED ;
  wire [3:0]\NLW_counter_reg[0]_i_23_O_UNCONNECTED ;
  wire [3:0]\NLW_counter_reg[0]_i_3_O_UNCONNECTED ;
  wire [3:0]\NLW_counter_reg[0]_i_5_O_UNCONNECTED ;
  wire [3:3]\NLW_counter_reg[28]_i_1_CO_UNCONNECTED ;
  wire [3:3]NLW_divClk_reg_i_2_CO_UNCONNECTED;
  wire [3:0]NLW_divClk_reg_i_2_O_UNCONNECTED;
  wire [3:0]NLW_divClk_reg_i_3_O_UNCONNECTED;
  wire [3:0]NLW_divClk_reg_i_7_O_UNCONNECTED;

  LUT2 #(
    .INIT(4'hE)) 
    \counter[0]_i_1 
       (.I0(divClk_reg_i_2_n_1),
        .I1(\counter_reg[0]_i_3_n_0 ),
        .O(\counter[0]_i_1_n_0 ));
  LUT2 #(
    .INIT(4'h1)) 
    \counter[0]_i_10 
       (.I0(counter_reg[30]),
        .I1(counter_reg[31]),
        .O(\counter[0]_i_10_n_0 ));
  LUT2 #(
    .INIT(4'h1)) 
    \counter[0]_i_11 
       (.I0(counter_reg[28]),
        .I1(counter_reg[29]),
        .O(\counter[0]_i_11_n_0 ));
  LUT2 #(
    .INIT(4'h1)) 
    \counter[0]_i_12 
       (.I0(counter_reg[26]),
        .I1(counter_reg[27]),
        .O(\counter[0]_i_12_n_0 ));
  LUT4 #(
    .INIT(16'h1241)) 
    \counter[0]_i_13 
       (.I0(counter_reg[24]),
        .I1(counter_reg[25]),
        .I2(s0),
        .I3(s1),
        .O(\counter[0]_i_13_n_0 ));
  LUT4 #(
    .INIT(16'hF3A2)) 
    \counter[0]_i_15 
       (.I0(counter_reg[22]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[23]),
        .O(\counter[0]_i_15_n_0 ));
  LUT4 #(
    .INIT(16'hC382)) 
    \counter[0]_i_16 
       (.I0(counter_reg[20]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[21]),
        .O(\counter[0]_i_16_n_0 ));
  LUT4 #(
    .INIT(16'hFBA2)) 
    \counter[0]_i_17 
       (.I0(counter_reg[18]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[19]),
        .O(\counter[0]_i_17_n_0 ));
  LUT4 #(
    .INIT(16'hFB82)) 
    \counter[0]_i_18 
       (.I0(counter_reg[16]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[17]),
        .O(\counter[0]_i_18_n_0 ));
  LUT4 #(
    .INIT(16'h1181)) 
    \counter[0]_i_19 
       (.I0(counter_reg[22]),
        .I1(counter_reg[23]),
        .I2(s0),
        .I3(s1),
        .O(\counter[0]_i_19_n_0 ));
  LUT4 #(
    .INIT(16'h1881)) 
    \counter[0]_i_20 
       (.I0(counter_reg[20]),
        .I1(counter_reg[21]),
        .I2(s0),
        .I3(s1),
        .O(\counter[0]_i_20_n_0 ));
  LUT4 #(
    .INIT(16'h0059)) 
    \counter[0]_i_21 
       (.I0(counter_reg[19]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[18]),
        .O(\counter[0]_i_21_n_0 ));
  LUT4 #(
    .INIT(16'h1241)) 
    \counter[0]_i_22 
       (.I0(counter_reg[16]),
        .I1(counter_reg[17]),
        .I2(s0),
        .I3(s1),
        .O(\counter[0]_i_22_n_0 ));
  LUT4 #(
    .INIT(16'hF3A2)) 
    \counter[0]_i_24 
       (.I0(counter_reg[14]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[15]),
        .O(\counter[0]_i_24_n_0 ));
  LUT4 #(
    .INIT(16'hE382)) 
    \counter[0]_i_25 
       (.I0(counter_reg[12]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[13]),
        .O(\counter[0]_i_25_n_0 ));
  LUT4 #(
    .INIT(16'hF3A2)) 
    \counter[0]_i_26 
       (.I0(counter_reg[10]),
        .I1(s1),
        .I2(s0),
        .I3(counter_reg[11]),
        .O(\counter[0]_i_26_n_0 ));
  LUT4 #(
    .INIT(16'hFF8A)) 
    \counter[0]_i_27 
       (.I0(counter_reg[8]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[9]),
        .O(\counter[0]_i_27_n_0 ));
  LUT4 #(
    .INIT(16'h1181)) 
    \counter[0]_i_28 
       (.I0(counter_reg[14]),
        .I1(counter_reg[15]),
        .I2(s0),
        .I3(s1),
        .O(\counter[0]_i_28_n_0 ));
  LUT4 #(
    .INIT(16'h1481)) 
    \counter[0]_i_29 
       (.I0(counter_reg[12]),
        .I1(counter_reg[13]),
        .I2(s0),
        .I3(s1),
        .O(\counter[0]_i_29_n_0 ));
  LUT4 #(
    .INIT(16'h1181)) 
    \counter[0]_i_30 
       (.I0(counter_reg[10]),
        .I1(counter_reg[11]),
        .I2(s1),
        .I3(s0),
        .O(\counter[0]_i_30_n_0 ));
  LUT4 #(
    .INIT(16'h0059)) 
    \counter[0]_i_31 
       (.I0(counter_reg[8]),
        .I1(s1),
        .I2(s0),
        .I3(counter_reg[9]),
        .O(\counter[0]_i_31_n_0 ));
  LUT4 #(
    .INIT(16'hFBA2)) 
    \counter[0]_i_32 
       (.I0(counter_reg[6]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[7]),
        .O(\counter[0]_i_32_n_0 ));
  LUT2 #(
    .INIT(4'hE)) 
    \counter[0]_i_33 
       (.I0(counter_reg[4]),
        .I1(counter_reg[5]),
        .O(\counter[0]_i_33_n_0 ));
  LUT2 #(
    .INIT(4'hE)) 
    \counter[0]_i_34 
       (.I0(counter_reg[2]),
        .I1(counter_reg[3]),
        .O(\counter[0]_i_34_n_0 ));
  LUT4 #(
    .INIT(16'hFF28)) 
    \counter[0]_i_35 
       (.I0(counter_reg[0]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[1]),
        .O(\counter[0]_i_35_n_0 ));
  LUT4 #(
    .INIT(16'h0059)) 
    \counter[0]_i_36 
       (.I0(counter_reg[7]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[6]),
        .O(\counter[0]_i_36_n_0 ));
  LUT2 #(
    .INIT(4'h1)) 
    \counter[0]_i_37 
       (.I0(counter_reg[4]),
        .I1(counter_reg[5]),
        .O(\counter[0]_i_37_n_0 ));
  LUT2 #(
    .INIT(4'h1)) 
    \counter[0]_i_38 
       (.I0(counter_reg[2]),
        .I1(counter_reg[3]),
        .O(\counter[0]_i_38_n_0 ));
  LUT4 #(
    .INIT(16'h0096)) 
    \counter[0]_i_39 
       (.I0(counter_reg[0]),
        .I1(s1),
        .I2(s0),
        .I3(counter_reg[1]),
        .O(\counter[0]_i_39_n_0 ));
  LUT1 #(
    .INIT(2'h1)) 
    \counter[0]_i_4 
       (.I0(counter_reg[0]),
        .O(\counter[0]_i_4_n_0 ));
  LUT2 #(
    .INIT(4'hE)) 
    \counter[0]_i_6 
       (.I0(counter_reg[30]),
        .I1(counter_reg[31]),
        .O(\counter[0]_i_6_n_0 ));
  LUT2 #(
    .INIT(4'hE)) 
    \counter[0]_i_7 
       (.I0(counter_reg[28]),
        .I1(counter_reg[29]),
        .O(\counter[0]_i_7_n_0 ));
  LUT2 #(
    .INIT(4'hE)) 
    \counter[0]_i_8 
       (.I0(counter_reg[26]),
        .I1(counter_reg[27]),
        .O(\counter[0]_i_8_n_0 ));
  LUT4 #(
    .INIT(16'hFB82)) 
    \counter[0]_i_9 
       (.I0(counter_reg[24]),
        .I1(s0),
        .I2(s1),
        .I3(counter_reg[25]),
        .O(\counter[0]_i_9_n_0 ));
  FDRE \counter_reg[0] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[0]_i_2_n_7 ),
        .Q(counter_reg[0]),
        .R(\counter[0]_i_1_n_0 ));
  (* COMPARATOR_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[0]_i_14 
       (.CI(\counter_reg[0]_i_23_n_0 ),
        .CO({\counter_reg[0]_i_14_n_0 ,\counter_reg[0]_i_14_n_1 ,\counter_reg[0]_i_14_n_2 ,\counter_reg[0]_i_14_n_3 }),
        .CYINIT(1'b0),
        .DI({\counter[0]_i_24_n_0 ,\counter[0]_i_25_n_0 ,\counter[0]_i_26_n_0 ,\counter[0]_i_27_n_0 }),
        .O(\NLW_counter_reg[0]_i_14_O_UNCONNECTED [3:0]),
        .S({\counter[0]_i_28_n_0 ,\counter[0]_i_29_n_0 ,\counter[0]_i_30_n_0 ,\counter[0]_i_31_n_0 }));
  (* ADDER_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[0]_i_2 
       (.CI(1'b0),
        .CO({\counter_reg[0]_i_2_n_0 ,\counter_reg[0]_i_2_n_1 ,\counter_reg[0]_i_2_n_2 ,\counter_reg[0]_i_2_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b1}),
        .O({\counter_reg[0]_i_2_n_4 ,\counter_reg[0]_i_2_n_5 ,\counter_reg[0]_i_2_n_6 ,\counter_reg[0]_i_2_n_7 }),
        .S({counter_reg[3:1],\counter[0]_i_4_n_0 }));
  (* COMPARATOR_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[0]_i_23 
       (.CI(1'b0),
        .CO({\counter_reg[0]_i_23_n_0 ,\counter_reg[0]_i_23_n_1 ,\counter_reg[0]_i_23_n_2 ,\counter_reg[0]_i_23_n_3 }),
        .CYINIT(1'b0),
        .DI({\counter[0]_i_32_n_0 ,\counter[0]_i_33_n_0 ,\counter[0]_i_34_n_0 ,\counter[0]_i_35_n_0 }),
        .O(\NLW_counter_reg[0]_i_23_O_UNCONNECTED [3:0]),
        .S({\counter[0]_i_36_n_0 ,\counter[0]_i_37_n_0 ,\counter[0]_i_38_n_0 ,\counter[0]_i_39_n_0 }));
  (* COMPARATOR_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[0]_i_3 
       (.CI(\counter_reg[0]_i_5_n_0 ),
        .CO({\counter_reg[0]_i_3_n_0 ,\counter_reg[0]_i_3_n_1 ,\counter_reg[0]_i_3_n_2 ,\counter_reg[0]_i_3_n_3 }),
        .CYINIT(1'b0),
        .DI({\counter[0]_i_6_n_0 ,\counter[0]_i_7_n_0 ,\counter[0]_i_8_n_0 ,\counter[0]_i_9_n_0 }),
        .O(\NLW_counter_reg[0]_i_3_O_UNCONNECTED [3:0]),
        .S({\counter[0]_i_10_n_0 ,\counter[0]_i_11_n_0 ,\counter[0]_i_12_n_0 ,\counter[0]_i_13_n_0 }));
  (* COMPARATOR_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[0]_i_5 
       (.CI(\counter_reg[0]_i_14_n_0 ),
        .CO({\counter_reg[0]_i_5_n_0 ,\counter_reg[0]_i_5_n_1 ,\counter_reg[0]_i_5_n_2 ,\counter_reg[0]_i_5_n_3 }),
        .CYINIT(1'b0),
        .DI({\counter[0]_i_15_n_0 ,\counter[0]_i_16_n_0 ,\counter[0]_i_17_n_0 ,\counter[0]_i_18_n_0 }),
        .O(\NLW_counter_reg[0]_i_5_O_UNCONNECTED [3:0]),
        .S({\counter[0]_i_19_n_0 ,\counter[0]_i_20_n_0 ,\counter[0]_i_21_n_0 ,\counter[0]_i_22_n_0 }));
  FDRE \counter_reg[10] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[8]_i_1_n_5 ),
        .Q(counter_reg[10]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[11] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[8]_i_1_n_4 ),
        .Q(counter_reg[11]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[12] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[12]_i_1_n_7 ),
        .Q(counter_reg[12]),
        .R(\counter[0]_i_1_n_0 ));
  (* ADDER_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[12]_i_1 
       (.CI(\counter_reg[8]_i_1_n_0 ),
        .CO({\counter_reg[12]_i_1_n_0 ,\counter_reg[12]_i_1_n_1 ,\counter_reg[12]_i_1_n_2 ,\counter_reg[12]_i_1_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O({\counter_reg[12]_i_1_n_4 ,\counter_reg[12]_i_1_n_5 ,\counter_reg[12]_i_1_n_6 ,\counter_reg[12]_i_1_n_7 }),
        .S(counter_reg[15:12]));
  FDRE \counter_reg[13] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[12]_i_1_n_6 ),
        .Q(counter_reg[13]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[14] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[12]_i_1_n_5 ),
        .Q(counter_reg[14]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[15] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[12]_i_1_n_4 ),
        .Q(counter_reg[15]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[16] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[16]_i_1_n_7 ),
        .Q(counter_reg[16]),
        .R(\counter[0]_i_1_n_0 ));
  (* ADDER_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[16]_i_1 
       (.CI(\counter_reg[12]_i_1_n_0 ),
        .CO({\counter_reg[16]_i_1_n_0 ,\counter_reg[16]_i_1_n_1 ,\counter_reg[16]_i_1_n_2 ,\counter_reg[16]_i_1_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O({\counter_reg[16]_i_1_n_4 ,\counter_reg[16]_i_1_n_5 ,\counter_reg[16]_i_1_n_6 ,\counter_reg[16]_i_1_n_7 }),
        .S(counter_reg[19:16]));
  FDRE \counter_reg[17] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[16]_i_1_n_6 ),
        .Q(counter_reg[17]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[18] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[16]_i_1_n_5 ),
        .Q(counter_reg[18]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[19] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[16]_i_1_n_4 ),
        .Q(counter_reg[19]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[1] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[0]_i_2_n_6 ),
        .Q(counter_reg[1]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[20] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[20]_i_1_n_7 ),
        .Q(counter_reg[20]),
        .R(\counter[0]_i_1_n_0 ));
  (* ADDER_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[20]_i_1 
       (.CI(\counter_reg[16]_i_1_n_0 ),
        .CO({\counter_reg[20]_i_1_n_0 ,\counter_reg[20]_i_1_n_1 ,\counter_reg[20]_i_1_n_2 ,\counter_reg[20]_i_1_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O({\counter_reg[20]_i_1_n_4 ,\counter_reg[20]_i_1_n_5 ,\counter_reg[20]_i_1_n_6 ,\counter_reg[20]_i_1_n_7 }),
        .S(counter_reg[23:20]));
  FDRE \counter_reg[21] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[20]_i_1_n_6 ),
        .Q(counter_reg[21]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[22] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[20]_i_1_n_5 ),
        .Q(counter_reg[22]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[23] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[20]_i_1_n_4 ),
        .Q(counter_reg[23]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[24] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[24]_i_1_n_7 ),
        .Q(counter_reg[24]),
        .R(\counter[0]_i_1_n_0 ));
  (* ADDER_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[24]_i_1 
       (.CI(\counter_reg[20]_i_1_n_0 ),
        .CO({\counter_reg[24]_i_1_n_0 ,\counter_reg[24]_i_1_n_1 ,\counter_reg[24]_i_1_n_2 ,\counter_reg[24]_i_1_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O({\counter_reg[24]_i_1_n_4 ,\counter_reg[24]_i_1_n_5 ,\counter_reg[24]_i_1_n_6 ,\counter_reg[24]_i_1_n_7 }),
        .S(counter_reg[27:24]));
  FDRE \counter_reg[25] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[24]_i_1_n_6 ),
        .Q(counter_reg[25]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[26] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[24]_i_1_n_5 ),
        .Q(counter_reg[26]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[27] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[24]_i_1_n_4 ),
        .Q(counter_reg[27]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[28] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[28]_i_1_n_7 ),
        .Q(counter_reg[28]),
        .R(\counter[0]_i_1_n_0 ));
  (* ADDER_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[28]_i_1 
       (.CI(\counter_reg[24]_i_1_n_0 ),
        .CO({\NLW_counter_reg[28]_i_1_CO_UNCONNECTED [3],\counter_reg[28]_i_1_n_1 ,\counter_reg[28]_i_1_n_2 ,\counter_reg[28]_i_1_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O({\counter_reg[28]_i_1_n_4 ,\counter_reg[28]_i_1_n_5 ,\counter_reg[28]_i_1_n_6 ,\counter_reg[28]_i_1_n_7 }),
        .S(counter_reg[31:28]));
  FDRE \counter_reg[29] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[28]_i_1_n_6 ),
        .Q(counter_reg[29]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[2] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[0]_i_2_n_5 ),
        .Q(counter_reg[2]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[30] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[28]_i_1_n_5 ),
        .Q(counter_reg[30]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[31] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[28]_i_1_n_4 ),
        .Q(counter_reg[31]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[3] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[0]_i_2_n_4 ),
        .Q(counter_reg[3]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[4] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[4]_i_1_n_7 ),
        .Q(counter_reg[4]),
        .R(\counter[0]_i_1_n_0 ));
  (* ADDER_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[4]_i_1 
       (.CI(\counter_reg[0]_i_2_n_0 ),
        .CO({\counter_reg[4]_i_1_n_0 ,\counter_reg[4]_i_1_n_1 ,\counter_reg[4]_i_1_n_2 ,\counter_reg[4]_i_1_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O({\counter_reg[4]_i_1_n_4 ,\counter_reg[4]_i_1_n_5 ,\counter_reg[4]_i_1_n_6 ,\counter_reg[4]_i_1_n_7 }),
        .S(counter_reg[7:4]));
  FDRE \counter_reg[5] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[4]_i_1_n_6 ),
        .Q(counter_reg[5]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[6] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[4]_i_1_n_5 ),
        .Q(counter_reg[6]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[7] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[4]_i_1_n_4 ),
        .Q(counter_reg[7]),
        .R(\counter[0]_i_1_n_0 ));
  FDRE \counter_reg[8] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[8]_i_1_n_7 ),
        .Q(counter_reg[8]),
        .R(\counter[0]_i_1_n_0 ));
  (* ADDER_THRESHOLD = "11" *) 
  CARRY4 \counter_reg[8]_i_1 
       (.CI(\counter_reg[4]_i_1_n_0 ),
        .CO({\counter_reg[8]_i_1_n_0 ,\counter_reg[8]_i_1_n_1 ,\counter_reg[8]_i_1_n_2 ,\counter_reg[8]_i_1_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O({\counter_reg[8]_i_1_n_4 ,\counter_reg[8]_i_1_n_5 ,\counter_reg[8]_i_1_n_6 ,\counter_reg[8]_i_1_n_7 }),
        .S(counter_reg[11:8]));
  FDRE \counter_reg[9] 
       (.C(Clk),
        .CE(1'b1),
        .D(\counter_reg[8]_i_1_n_6 ),
        .Q(counter_reg[9]),
        .R(\counter[0]_i_1_n_0 ));
  LUT2 #(
    .INIT(4'h6)) 
    divClk_i_1
       (.I0(divClk_reg_i_2_n_1),
        .I1(divClk),
        .O(divClk_i_1_n_0));
  LUT5 #(
    .INIT(32'h00042041)) 
    divClk_i_10
       (.I0(counter_reg[15]),
        .I1(s1),
        .I2(s0),
        .I3(counter_reg[17]),
        .I4(counter_reg[16]),
        .O(divClk_i_10_n_0));
  LUT5 #(
    .INIT(32'h20040041)) 
    divClk_i_11
       (.I0(counter_reg[12]),
        .I1(s1),
        .I2(s0),
        .I3(counter_reg[14]),
        .I4(counter_reg[13]),
        .O(divClk_i_11_n_0));
  LUT5 #(
    .INIT(32'h0000400B)) 
    divClk_i_12
       (.I0(s0),
        .I1(s1),
        .I2(counter_reg[11]),
        .I3(counter_reg[10]),
        .I4(counter_reg[9]),
        .O(divClk_i_12_n_0));
  LUT5 #(
    .INIT(32'h00000249)) 
    divClk_i_13
       (.I0(s0),
        .I1(s1),
        .I2(counter_reg[8]),
        .I3(counter_reg[7]),
        .I4(counter_reg[6]),
        .O(divClk_i_13_n_0));
  LUT3 #(
    .INIT(8'h01)) 
    divClk_i_14
       (.I0(counter_reg[5]),
        .I1(counter_reg[4]),
        .I2(counter_reg[3]),
        .O(divClk_i_14_n_0));
  LUT5 #(
    .INIT(32'h00000096)) 
    divClk_i_15
       (.I0(counter_reg[0]),
        .I1(s1),
        .I2(s0),
        .I3(counter_reg[2]),
        .I4(counter_reg[1]),
        .O(divClk_i_15_n_0));
  LUT2 #(
    .INIT(4'h1)) 
    divClk_i_4
       (.I0(counter_reg[30]),
        .I1(counter_reg[31]),
        .O(divClk_i_4_n_0));
  LUT3 #(
    .INIT(8'h01)) 
    divClk_i_5
       (.I0(counter_reg[29]),
        .I1(counter_reg[28]),
        .I2(counter_reg[27]),
        .O(divClk_i_5_n_0));
  LUT5 #(
    .INIT(32'h01001021)) 
    divClk_i_6
       (.I0(counter_reg[24]),
        .I1(counter_reg[26]),
        .I2(s1),
        .I3(s0),
        .I4(counter_reg[25]),
        .O(divClk_i_6_n_0));
  LUT5 #(
    .INIT(32'h20000049)) 
    divClk_i_8
       (.I0(counter_reg[21]),
        .I1(s1),
        .I2(s0),
        .I3(counter_reg[23]),
        .I4(counter_reg[22]),
        .O(divClk_i_8_n_0));
  LUT5 #(
    .INIT(32'h00004029)) 
    divClk_i_9
       (.I0(s1),
        .I1(s0),
        .I2(counter_reg[20]),
        .I3(counter_reg[19]),
        .I4(counter_reg[18]),
        .O(divClk_i_9_n_0));
  FDRE divClk_reg
       (.C(Clk),
        .CE(1'b1),
        .D(divClk_i_1_n_0),
        .Q(divClk),
        .R(1'b0));
  CARRY4 divClk_reg_i_2
       (.CI(divClk_reg_i_3_n_0),
        .CO({NLW_divClk_reg_i_2_CO_UNCONNECTED[3],divClk_reg_i_2_n_1,divClk_reg_i_2_n_2,divClk_reg_i_2_n_3}),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O(NLW_divClk_reg_i_2_O_UNCONNECTED[3:0]),
        .S({1'b0,divClk_i_4_n_0,divClk_i_5_n_0,divClk_i_6_n_0}));
  CARRY4 divClk_reg_i_3
       (.CI(divClk_reg_i_7_n_0),
        .CO({divClk_reg_i_3_n_0,divClk_reg_i_3_n_1,divClk_reg_i_3_n_2,divClk_reg_i_3_n_3}),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O(NLW_divClk_reg_i_3_O_UNCONNECTED[3:0]),
        .S({divClk_i_8_n_0,divClk_i_9_n_0,divClk_i_10_n_0,divClk_i_11_n_0}));
  CARRY4 divClk_reg_i_7
       (.CI(1'b0),
        .CO({divClk_reg_i_7_n_0,divClk_reg_i_7_n_1,divClk_reg_i_7_n_2,divClk_reg_i_7_n_3}),
        .CYINIT(1'b1),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O(NLW_divClk_reg_i_7_O_UNCONNECTED[3:0]),
        .S({divClk_i_12_n_0,divClk_i_13_n_0,divClk_i_14_n_0,divClk_i_15_n_0}));
endmodule

(* CHECK_LICENSE_TYPE = "divClk_0,divClk,{}" *) (* DowngradeIPIdentifiedWarnings = "yes" *) (* IP_DEFINITION_SOURCE = "package_project" *) 
(* X_CORE_INFO = "divClk,Vivado 2021.1" *) 
(* NotValidForBitStream *)
module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix
   (Clk,
    s1,
    s0,
    divClk);
  (* X_INTERFACE_INFO = "xilinx.com:signal:clock:1.0 Clk CLK" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME Clk, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0" *) input Clk;
  input s1;
  input s0;
  output divClk;

  wire Clk;
  wire divClk;
  wire s0;
  wire s1;

  decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_divClk inst
       (.Clk(Clk),
        .divClk(divClk),
        .s0(s0),
        .s1(s1));
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
