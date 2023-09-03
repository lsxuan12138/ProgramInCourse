// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Tue Nov 30 21:15:25 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode funcsim
//               d:/VProject/Carlight/Carlight.gen/sources_1/ip/number_0/number_0_sim_netlist.v
// Design      : number_0
// Purpose     : This verilog netlist is a functional simulation representation of the design and should not be modified
//               or synthesized. This netlist cannot be used for SDF annotated simulation.
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

(* CHECK_LICENSE_TYPE = "number_0,number,{}" *) (* DowngradeIPIdentifiedWarnings = "yes" *) (* IP_DEFINITION_SOURCE = "package_project" *) 
(* X_CORE_INFO = "number,Vivado 2021.1" *) 
(* NotValidForBitStream *)
module number_0
   (s,
    t,
    clk,
    a,
    dm1,
    wm1);
  input s;
  input [3:0]t;
  (* X_INTERFACE_INFO = "xilinx.com:signal:clock:1.0 clk CLK" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME clk, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0" *) input clk;
  input [2:0]a;
  output [7:0]dm1;
  output [3:0]wm1;

  wire [2:0]a;
  wire clk;
  wire [7:0]dm1;
  wire s;
  wire [3:0]t;
  wire [3:0]wm1;

  (* maxcnt = "25000" *) 
  number_0_number inst
       (.a(a),
        .clk(clk),
        .dm1(dm1),
        .s(s),
        .t(t),
        .wm1(wm1));
endmodule

(* ORIG_REF_NAME = "number" *) (* maxcnt = "25000" *) 
module number_0_number
   (s,
    t,
    clk,
    a,
    dm1,
    wm1);
  input s;
  input [3:0]t;
  input clk;
  input [2:0]a;
  output [7:0]dm1;
  output [3:0]wm1;

  wire [2:0]a;
  wire clk;
  wire [14:1]data0;
  wire \disp_bit[0]_i_1_n_0 ;
  wire \disp_bit[1]_i_1_n_0 ;
  wire [1:0]disp_bit__0;
  wire [0:0]div_cnt;
  wire \div_cnt[0]_i_2_n_0 ;
  wire \div_cnt[0]_i_3_n_0 ;
  wire \div_cnt[0]_i_4_n_0 ;
  wire \div_cnt_reg[12]_i_1_n_0 ;
  wire \div_cnt_reg[12]_i_1_n_1 ;
  wire \div_cnt_reg[12]_i_1_n_2 ;
  wire \div_cnt_reg[12]_i_1_n_3 ;
  wire \div_cnt_reg[14]_i_2_n_3 ;
  wire \div_cnt_reg[4]_i_1_n_0 ;
  wire \div_cnt_reg[4]_i_1_n_1 ;
  wire \div_cnt_reg[4]_i_1_n_2 ;
  wire \div_cnt_reg[4]_i_1_n_3 ;
  wire \div_cnt_reg[8]_i_1_n_0 ;
  wire \div_cnt_reg[8]_i_1_n_1 ;
  wire \div_cnt_reg[8]_i_1_n_2 ;
  wire \div_cnt_reg[8]_i_1_n_3 ;
  wire \div_cnt_reg_n_0_[0] ;
  wire \div_cnt_reg_n_0_[10] ;
  wire \div_cnt_reg_n_0_[11] ;
  wire \div_cnt_reg_n_0_[12] ;
  wire \div_cnt_reg_n_0_[13] ;
  wire \div_cnt_reg_n_0_[14] ;
  wire \div_cnt_reg_n_0_[1] ;
  wire \div_cnt_reg_n_0_[2] ;
  wire \div_cnt_reg_n_0_[3] ;
  wire \div_cnt_reg_n_0_[4] ;
  wire \div_cnt_reg_n_0_[5] ;
  wire \div_cnt_reg_n_0_[6] ;
  wire \div_cnt_reg_n_0_[7] ;
  wire \div_cnt_reg_n_0_[8] ;
  wire \div_cnt_reg_n_0_[9] ;
  wire divclk;
  wire divclk_i_1_n_0;
  wire divclk_reg_n_0;
  wire [7:0]dm1;
  wire \dm1_reg[0]_i_1_n_0 ;
  wire \dm1_reg[1]_i_1_n_0 ;
  wire \dm1_reg[1]_i_2_n_0 ;
  wire \dm1_reg[1]_i_3_n_0 ;
  wire \dm1_reg[2]_i_1_n_0 ;
  wire \dm1_reg[2]_i_2_n_0 ;
  wire \dm1_reg[2]_i_3_n_0 ;
  wire \dm1_reg[3]_i_1_n_0 ;
  wire \dm1_reg[3]_i_2_n_0 ;
  wire \dm1_reg[3]_i_3_n_0 ;
  wire \dm1_reg[3]_i_4_n_0 ;
  wire \dm1_reg[4]_i_1_n_0 ;
  wire \dm1_reg[4]_i_2_n_0 ;
  wire \dm1_reg[4]_i_3_n_0 ;
  wire \dm1_reg[5]_i_1_n_0 ;
  wire \dm1_reg[5]_i_2_n_0 ;
  wire \dm1_reg[5]_i_3_n_0 ;
  wire \dm1_reg[6]_i_1_n_0 ;
  wire \dm1_reg[6]_i_2_n_0 ;
  wire \dm1_reg[6]_i_3_n_0 ;
  wire \dm1_reg[6]_i_4_n_0 ;
  wire \dm1_reg[7]_i_1_n_0 ;
  wire \dm1_reg[7]_i_2_n_0 ;
  wire \dm1_reg[7]_i_3_n_0 ;
  wire \dm1_reg[7]_i_4_n_0 ;
  wire \dm1_reg[7]_i_5_n_0 ;
  wire \dm1_reg[7]_i_6_n_0 ;
  wire flag;
  wire flag_i_1_n_0;
  wire s;
  wire [3:0]t;
  wire [3:0]wm1;
  wire \wm1[0]_i_1_n_0 ;
  wire \wm1[1]_i_1_n_0 ;
  wire \wm1[2]_i_1_n_0 ;
  wire \wm1[3]_i_1_n_0 ;
  wire [3:1]\NLW_div_cnt_reg[14]_i_2_CO_UNCONNECTED ;
  wire [3:2]\NLW_div_cnt_reg[14]_i_2_O_UNCONNECTED ;

  (* SOFT_HLUTNM = "soft_lutpair10" *) 
  LUT1 #(
    .INIT(2'h1)) 
    \disp_bit[0]_i_1 
       (.I0(disp_bit__0[0]),
        .O(\disp_bit[0]_i_1_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair9" *) 
  LUT2 #(
    .INIT(4'h6)) 
    \disp_bit[1]_i_1 
       (.I0(disp_bit__0[1]),
        .I1(disp_bit__0[0]),
        .O(\disp_bit[1]_i_1_n_0 ));
  FDRE #(
    .INIT(1'b0)) 
    \disp_bit_reg[0] 
       (.C(divclk_reg_n_0),
        .CE(1'b1),
        .D(\disp_bit[0]_i_1_n_0 ),
        .Q(disp_bit__0[0]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \disp_bit_reg[1] 
       (.C(divclk_reg_n_0),
        .CE(1'b1),
        .D(\disp_bit[1]_i_1_n_0 ),
        .Q(disp_bit__0[1]),
        .R(1'b0));
  (* SOFT_HLUTNM = "soft_lutpair7" *) 
  LUT2 #(
    .INIT(4'h2)) 
    \div_cnt[0]_i_1 
       (.I0(\div_cnt[0]_i_2_n_0 ),
        .I1(\div_cnt_reg_n_0_[0] ),
        .O(div_cnt));
  LUT6 #(
    .INIT(64'hFFFFFFFFFFFFFBFF)) 
    \div_cnt[0]_i_2 
       (.I0(\div_cnt[0]_i_3_n_0 ),
        .I1(\div_cnt_reg_n_0_[3] ),
        .I2(\div_cnt_reg_n_0_[4] ),
        .I3(\div_cnt_reg_n_0_[5] ),
        .I4(\div_cnt_reg_n_0_[6] ),
        .I5(\div_cnt[0]_i_4_n_0 ),
        .O(\div_cnt[0]_i_2_n_0 ));
  LUT4 #(
    .INIT(16'hFFF7)) 
    \div_cnt[0]_i_3 
       (.I0(\div_cnt_reg_n_0_[8] ),
        .I1(\div_cnt_reg_n_0_[7] ),
        .I2(\div_cnt_reg_n_0_[10] ),
        .I3(\div_cnt_reg_n_0_[9] ),
        .O(\div_cnt[0]_i_3_n_0 ));
  LUT6 #(
    .INIT(64'hFFFFFFFFFFFFFFF7)) 
    \div_cnt[0]_i_4 
       (.I0(\div_cnt_reg_n_0_[13] ),
        .I1(\div_cnt_reg_n_0_[14] ),
        .I2(\div_cnt_reg_n_0_[11] ),
        .I3(\div_cnt_reg_n_0_[12] ),
        .I4(\div_cnt_reg_n_0_[2] ),
        .I5(\div_cnt_reg_n_0_[1] ),
        .O(\div_cnt[0]_i_4_n_0 ));
  LUT2 #(
    .INIT(4'h1)) 
    \div_cnt[14]_i_1 
       (.I0(\div_cnt_reg_n_0_[0] ),
        .I1(\div_cnt[0]_i_2_n_0 ),
        .O(divclk));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[0] 
       (.C(clk),
        .CE(1'b1),
        .D(div_cnt),
        .Q(\div_cnt_reg_n_0_[0] ),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[10] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[10]),
        .Q(\div_cnt_reg_n_0_[10] ),
        .R(divclk));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[11] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[11]),
        .Q(\div_cnt_reg_n_0_[11] ),
        .R(divclk));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[12] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[12]),
        .Q(\div_cnt_reg_n_0_[12] ),
        .R(divclk));
  (* ADDER_THRESHOLD = "35" *) 
  CARRY4 \div_cnt_reg[12]_i_1 
       (.CI(\div_cnt_reg[8]_i_1_n_0 ),
        .CO({\div_cnt_reg[12]_i_1_n_0 ,\div_cnt_reg[12]_i_1_n_1 ,\div_cnt_reg[12]_i_1_n_2 ,\div_cnt_reg[12]_i_1_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O(data0[12:9]),
        .S({\div_cnt_reg_n_0_[12] ,\div_cnt_reg_n_0_[11] ,\div_cnt_reg_n_0_[10] ,\div_cnt_reg_n_0_[9] }));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[13] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[13]),
        .Q(\div_cnt_reg_n_0_[13] ),
        .R(divclk));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[14] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[14]),
        .Q(\div_cnt_reg_n_0_[14] ),
        .R(divclk));
  (* ADDER_THRESHOLD = "35" *) 
  CARRY4 \div_cnt_reg[14]_i_2 
       (.CI(\div_cnt_reg[12]_i_1_n_0 ),
        .CO({\NLW_div_cnt_reg[14]_i_2_CO_UNCONNECTED [3:1],\div_cnt_reg[14]_i_2_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O({\NLW_div_cnt_reg[14]_i_2_O_UNCONNECTED [3:2],data0[14:13]}),
        .S({1'b0,1'b0,\div_cnt_reg_n_0_[14] ,\div_cnt_reg_n_0_[13] }));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[1] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[1]),
        .Q(\div_cnt_reg_n_0_[1] ),
        .R(divclk));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[2] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[2]),
        .Q(\div_cnt_reg_n_0_[2] ),
        .R(divclk));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[3] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[3]),
        .Q(\div_cnt_reg_n_0_[3] ),
        .R(divclk));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[4] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[4]),
        .Q(\div_cnt_reg_n_0_[4] ),
        .R(divclk));
  (* ADDER_THRESHOLD = "35" *) 
  CARRY4 \div_cnt_reg[4]_i_1 
       (.CI(1'b0),
        .CO({\div_cnt_reg[4]_i_1_n_0 ,\div_cnt_reg[4]_i_1_n_1 ,\div_cnt_reg[4]_i_1_n_2 ,\div_cnt_reg[4]_i_1_n_3 }),
        .CYINIT(\div_cnt_reg_n_0_[0] ),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O(data0[4:1]),
        .S({\div_cnt_reg_n_0_[4] ,\div_cnt_reg_n_0_[3] ,\div_cnt_reg_n_0_[2] ,\div_cnt_reg_n_0_[1] }));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[5] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[5]),
        .Q(\div_cnt_reg_n_0_[5] ),
        .R(divclk));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[6] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[6]),
        .Q(\div_cnt_reg_n_0_[6] ),
        .R(divclk));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[7] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[7]),
        .Q(\div_cnt_reg_n_0_[7] ),
        .R(divclk));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[8] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[8]),
        .Q(\div_cnt_reg_n_0_[8] ),
        .R(divclk));
  (* ADDER_THRESHOLD = "35" *) 
  CARRY4 \div_cnt_reg[8]_i_1 
       (.CI(\div_cnt_reg[4]_i_1_n_0 ),
        .CO({\div_cnt_reg[8]_i_1_n_0 ,\div_cnt_reg[8]_i_1_n_1 ,\div_cnt_reg[8]_i_1_n_2 ,\div_cnt_reg[8]_i_1_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O(data0[8:5]),
        .S({\div_cnt_reg_n_0_[8] ,\div_cnt_reg_n_0_[7] ,\div_cnt_reg_n_0_[6] ,\div_cnt_reg_n_0_[5] }));
  FDRE #(
    .INIT(1'b0)) 
    \div_cnt_reg[9] 
       (.C(clk),
        .CE(1'b1),
        .D(data0[9]),
        .Q(\div_cnt_reg_n_0_[9] ),
        .R(divclk));
  (* SOFT_HLUTNM = "soft_lutpair7" *) 
  LUT3 #(
    .INIT(8'hE1)) 
    divclk_i_1
       (.I0(\div_cnt_reg_n_0_[0] ),
        .I1(\div_cnt[0]_i_2_n_0 ),
        .I2(divclk_reg_n_0),
        .O(divclk_i_1_n_0));
  FDRE #(
    .INIT(1'b0)) 
    divclk_reg
       (.C(clk),
        .CE(1'b1),
        .D(divclk_i_1_n_0),
        .Q(divclk_reg_n_0),
        .R(1'b0));
  (* XILINX_LEGACY_PRIM = "LD" *) 
  LDCE #(
    .INIT(1'b0)) 
    \dm1_reg[0] 
       (.CLR(1'b0),
        .D(\dm1_reg[0]_i_1_n_0 ),
        .G(\dm1_reg[7]_i_2_n_0 ),
        .GE(1'b1),
        .Q(dm1[0]));
  (* SOFT_HLUTNM = "soft_lutpair1" *) 
  LUT5 #(
    .INIT(32'h00020000)) 
    \dm1_reg[0]_i_1 
       (.I0(flag),
        .I1(wm1[1]),
        .I2(wm1[2]),
        .I3(wm1[0]),
        .I4(wm1[3]),
        .O(\dm1_reg[0]_i_1_n_0 ));
  (* XILINX_LEGACY_PRIM = "LD" *) 
  LDCE #(
    .INIT(1'b0)) 
    \dm1_reg[1] 
       (.CLR(1'b0),
        .D(\dm1_reg[1]_i_1_n_0 ),
        .G(\dm1_reg[7]_i_2_n_0 ),
        .GE(1'b1),
        .Q(dm1[1]));
  LUT6 #(
    .INIT(64'hB888BBBBB8888888)) 
    \dm1_reg[1]_i_1 
       (.I0(\dm1_reg[1]_i_2_n_0 ),
        .I1(\dm1_reg[7]_i_3_n_0 ),
        .I2(flag),
        .I3(t[0]),
        .I4(wm1[2]),
        .I5(\dm1_reg[1]_i_3_n_0 ),
        .O(\dm1_reg[1]_i_1_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair4" *) 
  LUT3 #(
    .INIT(8'hB2)) 
    \dm1_reg[1]_i_2 
       (.I0(a[0]),
        .I1(a[1]),
        .I2(a[2]),
        .O(\dm1_reg[1]_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h0000FFFF7C007C00)) 
    \dm1_reg[1]_i_3 
       (.I0(t[1]),
        .I1(t[2]),
        .I2(t[3]),
        .I3(flag),
        .I4(\dm1_reg[5]_i_3_n_0 ),
        .I5(wm1[1]),
        .O(\dm1_reg[1]_i_3_n_0 ));
  (* XILINX_LEGACY_PRIM = "LD" *) 
  LDCE #(
    .INIT(1'b0)) 
    \dm1_reg[2] 
       (.CLR(1'b0),
        .D(\dm1_reg[2]_i_1_n_0 ),
        .G(\dm1_reg[7]_i_2_n_0 ),
        .GE(1'b1),
        .Q(dm1[2]));
  (* SOFT_HLUTNM = "soft_lutpair0" *) 
  LUT5 #(
    .INIT(32'hAAAA8A02)) 
    \dm1_reg[2]_i_1 
       (.I0(\dm1_reg[3]_i_2_n_0 ),
        .I1(wm1[2]),
        .I2(\dm1_reg[2]_i_2_n_0 ),
        .I3(flag),
        .I4(\dm1_reg[7]_i_3_n_0 ),
        .O(\dm1_reg[2]_i_1_n_0 ));
  LUT6 #(
    .INIT(64'hFBFFF3FB00000000)) 
    \dm1_reg[2]_i_2 
       (.I0(t[1]),
        .I1(flag),
        .I2(wm1[1]),
        .I3(t[3]),
        .I4(t[2]),
        .I5(\dm1_reg[2]_i_3_n_0 ),
        .O(\dm1_reg[2]_i_2_n_0 ));
  LUT3 #(
    .INIT(8'h1F)) 
    \dm1_reg[2]_i_3 
       (.I0(a[2]),
        .I1(a[0]),
        .I2(wm1[1]),
        .O(\dm1_reg[2]_i_3_n_0 ));
  (* XILINX_LEGACY_PRIM = "LD" *) 
  LDCE #(
    .INIT(1'b0)) 
    \dm1_reg[3] 
       (.CLR(1'b0),
        .D(\dm1_reg[3]_i_1_n_0 ),
        .G(\dm1_reg[7]_i_2_n_0 ),
        .GE(1'b1),
        .Q(dm1[3]));
  LUT6 #(
    .INIT(64'hAA02AA8AAA02AA02)) 
    \dm1_reg[3]_i_1 
       (.I0(\dm1_reg[3]_i_2_n_0 ),
        .I1(wm1[2]),
        .I2(\dm1_reg[3]_i_3_n_0 ),
        .I3(\dm1_reg[7]_i_3_n_0 ),
        .I4(t[0]),
        .I5(flag),
        .O(\dm1_reg[3]_i_1_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair4" *) 
  LUT4 #(
    .INIT(16'hBFFB)) 
    \dm1_reg[3]_i_2 
       (.I0(a[2]),
        .I1(\dm1_reg[7]_i_3_n_0 ),
        .I2(a[0]),
        .I3(a[1]),
        .O(\dm1_reg[3]_i_2_n_0 ));
  LUT6 #(
    .INIT(64'h1111F0FF1111FFFF)) 
    \dm1_reg[3]_i_3 
       (.I0(a[0]),
        .I1(a[2]),
        .I2(t[1]),
        .I3(flag),
        .I4(wm1[1]),
        .I5(\dm1_reg[3]_i_4_n_0 ),
        .O(\dm1_reg[3]_i_3_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair3" *) 
  LUT2 #(
    .INIT(4'hB)) 
    \dm1_reg[3]_i_4 
       (.I0(t[2]),
        .I1(t[3]),
        .O(\dm1_reg[3]_i_4_n_0 ));
  (* XILINX_LEGACY_PRIM = "LD" *) 
  LDCE #(
    .INIT(1'b0)) 
    \dm1_reg[4] 
       (.CLR(1'b0),
        .D(\dm1_reg[4]_i_1_n_0 ),
        .G(\dm1_reg[7]_i_2_n_0 ),
        .GE(1'b1),
        .Q(dm1[4]));
  LUT6 #(
    .INIT(64'h66666666FFF0F0F0)) 
    \dm1_reg[4]_i_1 
       (.I0(a[0]),
        .I1(a[1]),
        .I2(\dm1_reg[4]_i_2_n_0 ),
        .I3(flag),
        .I4(wm1[2]),
        .I5(\dm1_reg[7]_i_3_n_0 ),
        .O(\dm1_reg[4]_i_1_n_0 ));
  LUT6 #(
    .INIT(64'h1540000055555555)) 
    \dm1_reg[4]_i_2 
       (.I0(wm1[2]),
        .I1(a[1]),
        .I2(a[2]),
        .I3(a[0]),
        .I4(wm1[1]),
        .I5(\dm1_reg[4]_i_3_n_0 ),
        .O(\dm1_reg[4]_i_2_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair3" *) 
  LUT5 #(
    .INIT(32'hFDDFDDFD)) 
    \dm1_reg[4]_i_3 
       (.I0(flag),
        .I1(wm1[1]),
        .I2(t[3]),
        .I3(t[2]),
        .I4(t[1]),
        .O(\dm1_reg[4]_i_3_n_0 ));
  (* XILINX_LEGACY_PRIM = "LD" *) 
  LDCE #(
    .INIT(1'b0)) 
    \dm1_reg[5] 
       (.CLR(1'b0),
        .D(\dm1_reg[5]_i_1_n_0 ),
        .G(\dm1_reg[7]_i_2_n_0 ),
        .GE(1'b1),
        .Q(dm1[5]));
  LUT5 #(
    .INIT(32'hFF00B1B1)) 
    \dm1_reg[5]_i_1 
       (.I0(wm1[2]),
        .I1(\dm1_reg[5]_i_2_n_0 ),
        .I2(flag),
        .I3(\dm1_reg[5]_i_3_n_0 ),
        .I4(\dm1_reg[7]_i_3_n_0 ),
        .O(\dm1_reg[5]_i_1_n_0 ));
  LUT6 #(
    .INIT(64'h88888B88BBBBBBBB)) 
    \dm1_reg[5]_i_2 
       (.I0(\dm1_reg[1]_i_2_n_0 ),
        .I1(wm1[1]),
        .I2(t[1]),
        .I3(t[2]),
        .I4(t[3]),
        .I5(flag),
        .O(\dm1_reg[5]_i_2_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair6" *) 
  LUT3 #(
    .INIT(8'h45)) 
    \dm1_reg[5]_i_3 
       (.I0(a[2]),
        .I1(a[1]),
        .I2(a[0]),
        .O(\dm1_reg[5]_i_3_n_0 ));
  (* XILINX_LEGACY_PRIM = "LD" *) 
  LDCE #(
    .INIT(1'b0)) 
    \dm1_reg[6] 
       (.CLR(1'b0),
        .D(\dm1_reg[6]_i_1_n_0 ),
        .G(\dm1_reg[7]_i_2_n_0 ),
        .GE(1'b1),
        .Q(dm1[6]));
  LUT6 #(
    .INIT(64'h7577000075775577)) 
    \dm1_reg[6]_i_1 
       (.I0(\dm1_reg[6]_i_2_n_0 ),
        .I1(wm1[2]),
        .I2(wm1[1]),
        .I3(\dm1_reg[6]_i_3_n_0 ),
        .I4(\dm1_reg[6]_i_4_n_0 ),
        .I5(\dm1_reg[7]_i_3_n_0 ),
        .O(\dm1_reg[6]_i_1_n_0 ));
  LUT6 #(
    .INIT(64'h0100010106060606)) 
    \dm1_reg[6]_i_2 
       (.I0(wm1[1]),
        .I1(wm1[3]),
        .I2(wm1[0]),
        .I3(t[0]),
        .I4(flag),
        .I5(wm1[2]),
        .O(\dm1_reg[6]_i_2_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair2" *) 
  LUT5 #(
    .INIT(32'hDDFDFDDD)) 
    \dm1_reg[6]_i_3 
       (.I0(flag),
        .I1(wm1[1]),
        .I2(t[3]),
        .I3(t[2]),
        .I4(t[1]),
        .O(\dm1_reg[6]_i_3_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair6" *) 
  LUT3 #(
    .INIT(8'h35)) 
    \dm1_reg[6]_i_4 
       (.I0(a[0]),
        .I1(a[2]),
        .I2(a[1]),
        .O(\dm1_reg[6]_i_4_n_0 ));
  (* XILINX_LEGACY_PRIM = "LD" *) 
  LDCE #(
    .INIT(1'b0)) 
    \dm1_reg[7] 
       (.CLR(1'b0),
        .D(\dm1_reg[7]_i_1_n_0 ),
        .G(\dm1_reg[7]_i_2_n_0 ),
        .GE(1'b1),
        .Q(dm1[7]));
  LUT6 #(
    .INIT(64'hFF6F0000FF6FFF6F)) 
    \dm1_reg[7]_i_1 
       (.I0(a[1]),
        .I1(a[0]),
        .I2(\dm1_reg[7]_i_3_n_0 ),
        .I3(a[2]),
        .I4(\dm1_reg[7]_i_4_n_0 ),
        .I5(\dm1_reg[7]_i_5_n_0 ),
        .O(\dm1_reg[7]_i_1_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair5" *) 
  LUT4 #(
    .INIT(16'h0116)) 
    \dm1_reg[7]_i_2 
       (.I0(wm1[0]),
        .I1(wm1[1]),
        .I2(wm1[3]),
        .I3(wm1[2]),
        .O(\dm1_reg[7]_i_2_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair5" *) 
  LUT4 #(
    .INIT(16'hFEEB)) 
    \dm1_reg[7]_i_3 
       (.I0(wm1[0]),
        .I1(wm1[2]),
        .I2(wm1[3]),
        .I3(wm1[1]),
        .O(\dm1_reg[7]_i_3_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair1" *) 
  LUT5 #(
    .INIT(32'hFFF9FEF9)) 
    \dm1_reg[7]_i_4 
       (.I0(wm1[1]),
        .I1(wm1[3]),
        .I2(wm1[0]),
        .I3(wm1[2]),
        .I4(flag),
        .O(\dm1_reg[7]_i_4_n_0 ));
  LUT6 #(
    .INIT(64'hAFEFAFFFAAAAAAAA)) 
    \dm1_reg[7]_i_5 
       (.I0(wm1[2]),
        .I1(a[1]),
        .I2(wm1[1]),
        .I3(a[0]),
        .I4(a[2]),
        .I5(\dm1_reg[7]_i_6_n_0 ),
        .O(\dm1_reg[7]_i_5_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair2" *) 
  LUT5 #(
    .INIT(32'hFFFF14FF)) 
    \dm1_reg[7]_i_6 
       (.I0(t[2]),
        .I1(t[3]),
        .I2(t[1]),
        .I3(flag),
        .I4(wm1[1]),
        .O(\dm1_reg[7]_i_6_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair0" *) 
  LUT1 #(
    .INIT(2'h1)) 
    flag_i_1
       (.I0(flag),
        .O(flag_i_1_n_0));
  FDRE #(
    .INIT(1'b0)) 
    flag_reg
       (.C(s),
        .CE(1'b1),
        .D(flag_i_1_n_0),
        .Q(flag),
        .R(1'b0));
  (* SOFT_HLUTNM = "soft_lutpair8" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \wm1[0]_i_1 
       (.I0(disp_bit__0[0]),
        .I1(disp_bit__0[1]),
        .O(\wm1[0]_i_1_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair9" *) 
  LUT2 #(
    .INIT(4'h1)) 
    \wm1[1]_i_1 
       (.I0(disp_bit__0[0]),
        .I1(disp_bit__0[1]),
        .O(\wm1[1]_i_1_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair10" *) 
  LUT2 #(
    .INIT(4'h2)) 
    \wm1[2]_i_1 
       (.I0(disp_bit__0[0]),
        .I1(disp_bit__0[1]),
        .O(\wm1[2]_i_1_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair8" *) 
  LUT2 #(
    .INIT(4'h4)) 
    \wm1[3]_i_1 
       (.I0(disp_bit__0[0]),
        .I1(disp_bit__0[1]),
        .O(\wm1[3]_i_1_n_0 ));
  FDRE #(
    .INIT(1'b0)) 
    \wm1_reg[0] 
       (.C(divclk_reg_n_0),
        .CE(1'b1),
        .D(\wm1[0]_i_1_n_0 ),
        .Q(wm1[0]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \wm1_reg[1] 
       (.C(divclk_reg_n_0),
        .CE(1'b1),
        .D(\wm1[1]_i_1_n_0 ),
        .Q(wm1[1]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b1)) 
    \wm1_reg[2] 
       (.C(divclk_reg_n_0),
        .CE(1'b1),
        .D(\wm1[2]_i_1_n_0 ),
        .Q(wm1[2]),
        .R(1'b0));
  FDRE #(
    .INIT(1'b0)) 
    \wm1_reg[3] 
       (.C(divclk_reg_n_0),
        .CE(1'b1),
        .D(\wm1[3]_i_1_n_0 ),
        .Q(wm1[3]),
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
