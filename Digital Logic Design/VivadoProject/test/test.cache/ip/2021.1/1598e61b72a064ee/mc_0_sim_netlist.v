// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Wed Dec  1 11:47:59 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode funcsim -rename_top decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix -prefix
//               decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_ mc_0_sim_netlist.v
// Design      : mc_0
// Purpose     : This verilog netlist is a functional simulation representation of the design and should not be modified
//               or synthesized. This netlist cannot be used for SDF annotated simulation.
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_mc
   (sd,
    pwm,
    sdin,
    s,
    clk);
  output sd;
  output pwm;
  input sdin;
  input s;
  input clk;

  wire clk;
  wire [16:0]counter;
  wire [16:1]counter0;
  wire \counter[16]_i_2_n_0 ;
  wire \counter[16]_i_4_n_0 ;
  wire [16:0]counter_0;
  wire \counter_reg[12]_i_2_n_0 ;
  wire \counter_reg[12]_i_2_n_1 ;
  wire \counter_reg[12]_i_2_n_2 ;
  wire \counter_reg[12]_i_2_n_3 ;
  wire \counter_reg[16]_i_3_n_1 ;
  wire \counter_reg[16]_i_3_n_2 ;
  wire \counter_reg[16]_i_3_n_3 ;
  wire \counter_reg[4]_i_2_n_0 ;
  wire \counter_reg[4]_i_2_n_1 ;
  wire \counter_reg[4]_i_2_n_2 ;
  wire \counter_reg[4]_i_2_n_3 ;
  wire \counter_reg[8]_i_2_n_0 ;
  wire \counter_reg[8]_i_2_n_1 ;
  wire \counter_reg[8]_i_2_n_2 ;
  wire \counter_reg[8]_i_2_n_3 ;
  wire pwm;
  wire pwm_i_1_n_0;
  wire pwm_i_2_n_0;
  wire pwm_i_3_n_0;
  wire pwm_i_4_n_0;
  wire pwm_i_5_n_0;
  wire s;
  wire sd;
  wire sd_i_1_n_0;
  wire sdin;
  wire [3:3]\NLW_counter_reg[16]_i_3_CO_UNCONNECTED ;

  (* SOFT_HLUTNM = "soft_lutpair0" *) 
  LUT2 #(
    .INIT(4'h2)) 
    \counter[0]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter[0]),
        .O(counter_0[0]));
  (* SOFT_HLUTNM = "soft_lutpair5" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[10]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[10]),
        .O(counter_0[10]));
  (* SOFT_HLUTNM = "soft_lutpair5" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[11]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[11]),
        .O(counter_0[11]));
  (* SOFT_HLUTNM = "soft_lutpair6" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[12]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[12]),
        .O(counter_0[12]));
  (* SOFT_HLUTNM = "soft_lutpair6" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[13]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[13]),
        .O(counter_0[13]));
  (* SOFT_HLUTNM = "soft_lutpair7" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[14]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[14]),
        .O(counter_0[14]));
  (* SOFT_HLUTNM = "soft_lutpair7" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[15]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[15]),
        .O(counter_0[15]));
  LUT2 #(
    .INIT(4'h8)) 
    \counter[16]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[16]),
        .O(counter_0[16]));
  LUT6 #(
    .INIT(64'h000000F7FFFFFFFF)) 
    \counter[16]_i_2 
       (.I0(counter[12]),
        .I1(counter[11]),
        .I2(\counter[16]_i_4_n_0 ),
        .I3(counter[14]),
        .I4(counter[15]),
        .I5(counter[16]),
        .O(\counter[16]_i_2_n_0 ));
  LUT5 #(
    .INIT(32'h0001FFFF)) 
    \counter[16]_i_4 
       (.I0(counter[9]),
        .I1(counter[10]),
        .I2(counter[7]),
        .I3(counter[8]),
        .I4(counter[13]),
        .O(\counter[16]_i_4_n_0 ));
  (* SOFT_HLUTNM = "soft_lutpair0" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[1]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[1]),
        .O(counter_0[1]));
  (* SOFT_HLUTNM = "soft_lutpair1" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[2]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[2]),
        .O(counter_0[2]));
  (* SOFT_HLUTNM = "soft_lutpair1" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[3]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[3]),
        .O(counter_0[3]));
  (* SOFT_HLUTNM = "soft_lutpair2" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[4]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[4]),
        .O(counter_0[4]));
  (* SOFT_HLUTNM = "soft_lutpair2" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[5]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[5]),
        .O(counter_0[5]));
  (* SOFT_HLUTNM = "soft_lutpair3" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[6]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[6]),
        .O(counter_0[6]));
  (* SOFT_HLUTNM = "soft_lutpair3" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[7]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[7]),
        .O(counter_0[7]));
  (* SOFT_HLUTNM = "soft_lutpair4" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[8]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[8]),
        .O(counter_0[8]));
  (* SOFT_HLUTNM = "soft_lutpair4" *) 
  LUT2 #(
    .INIT(4'h8)) 
    \counter[9]_i_1 
       (.I0(\counter[16]_i_2_n_0 ),
        .I1(counter0[9]),
        .O(counter_0[9]));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[0] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[0]),
        .Q(counter[0]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[10] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[10]),
        .Q(counter[10]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[11] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[11]),
        .Q(counter[11]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[12] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[12]),
        .Q(counter[12]),
        .R(pwm_i_1_n_0));
  (* ADDER_THRESHOLD = "35" *) 
  CARRY4 \counter_reg[12]_i_2 
       (.CI(\counter_reg[8]_i_2_n_0 ),
        .CO({\counter_reg[12]_i_2_n_0 ,\counter_reg[12]_i_2_n_1 ,\counter_reg[12]_i_2_n_2 ,\counter_reg[12]_i_2_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O(counter0[12:9]),
        .S(counter[12:9]));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[13] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[13]),
        .Q(counter[13]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[14] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[14]),
        .Q(counter[14]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[15] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[15]),
        .Q(counter[15]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[16] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[16]),
        .Q(counter[16]),
        .R(pwm_i_1_n_0));
  (* ADDER_THRESHOLD = "35" *) 
  CARRY4 \counter_reg[16]_i_3 
       (.CI(\counter_reg[12]_i_2_n_0 ),
        .CO({\NLW_counter_reg[16]_i_3_CO_UNCONNECTED [3],\counter_reg[16]_i_3_n_1 ,\counter_reg[16]_i_3_n_2 ,\counter_reg[16]_i_3_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O(counter0[16:13]),
        .S(counter[16:13]));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[1] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[1]),
        .Q(counter[1]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[2] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[2]),
        .Q(counter[2]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[3] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[3]),
        .Q(counter[3]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[4] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[4]),
        .Q(counter[4]),
        .R(pwm_i_1_n_0));
  (* ADDER_THRESHOLD = "35" *) 
  CARRY4 \counter_reg[4]_i_2 
       (.CI(1'b0),
        .CO({\counter_reg[4]_i_2_n_0 ,\counter_reg[4]_i_2_n_1 ,\counter_reg[4]_i_2_n_2 ,\counter_reg[4]_i_2_n_3 }),
        .CYINIT(counter[0]),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O(counter0[4:1]),
        .S(counter[4:1]));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[5] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[5]),
        .Q(counter[5]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[6] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[6]),
        .Q(counter[6]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[7] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[7]),
        .Q(counter[7]),
        .R(pwm_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[8] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[8]),
        .Q(counter[8]),
        .R(pwm_i_1_n_0));
  (* ADDER_THRESHOLD = "35" *) 
  CARRY4 \counter_reg[8]_i_2 
       (.CI(\counter_reg[4]_i_2_n_0 ),
        .CO({\counter_reg[8]_i_2_n_0 ,\counter_reg[8]_i_2_n_1 ,\counter_reg[8]_i_2_n_2 ,\counter_reg[8]_i_2_n_3 }),
        .CYINIT(1'b0),
        .DI({1'b0,1'b0,1'b0,1'b0}),
        .O(counter0[8:5]),
        .S(counter[8:5]));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    \counter_reg[9] 
       (.C(clk),
        .CE(1'b1),
        .D(counter_0[9]),
        .Q(counter[9]),
        .R(pwm_i_1_n_0));
  LUT1 #(
    .INIT(2'h1)) 
    pwm_i_1
       (.I0(s),
        .O(pwm_i_1_n_0));
  LUT4 #(
    .INIT(16'h7F80)) 
    pwm_i_2
       (.I0(pwm_i_3_n_0),
        .I1(pwm_i_4_n_0),
        .I2(pwm_i_5_n_0),
        .I3(pwm),
        .O(pwm_i_2_n_0));
  LUT6 #(
    .INIT(64'h0000000100000000)) 
    pwm_i_3
       (.I0(counter[5]),
        .I1(counter[6]),
        .I2(counter[3]),
        .I3(counter[4]),
        .I4(counter[8]),
        .I5(counter[7]),
        .O(pwm_i_3_n_0));
  LUT5 #(
    .INIT(32'h01000000)) 
    pwm_i_4
       (.I0(counter[0]),
        .I1(counter[1]),
        .I2(counter[2]),
        .I3(counter[12]),
        .I4(counter[11]),
        .O(pwm_i_4_n_0));
  LUT6 #(
    .INIT(64'h0000000200000000)) 
    pwm_i_5
       (.I0(counter[13]),
        .I1(counter[14]),
        .I2(counter[9]),
        .I3(counter[10]),
        .I4(counter[15]),
        .I5(counter[16]),
        .O(pwm_i_5_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    pwm_reg
       (.C(clk),
        .CE(1'b1),
        .D(pwm_i_2_n_0),
        .Q(pwm),
        .R(pwm_i_1_n_0));
  LUT2 #(
    .INIT(4'h8)) 
    sd_i_1
       (.I0(sdin),
        .I1(s),
        .O(sd_i_1_n_0));
  FDRE #(
    .INIT(1'b0),
    .IS_C_INVERTED(1'b1)) 
    sd_reg
       (.C(clk),
        .CE(1'b1),
        .D(sd_i_1_n_0),
        .Q(sd),
        .R(1'b0));
endmodule

(* CHECK_LICENSE_TYPE = "mc_0,mc,{}" *) (* DowngradeIPIdentifiedWarnings = "yes" *) (* IP_DEFINITION_SOURCE = "package_project" *) 
(* X_CORE_INFO = "mc,Vivado 2021.1" *) 
(* NotValidForBitStream *)
module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix
   (clk,
    s,
    sdin,
    pwm,
    sd);
  (* X_INTERFACE_INFO = "xilinx.com:signal:clock:1.0 clk CLK" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME clk, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0" *) input clk;
  input s;
  input sdin;
  output pwm;
  output sd;

  wire clk;
  wire pwm;
  wire s;
  wire sd;
  wire sdin;

  decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_mc inst
       (.clk(clk),
        .pwm(pwm),
        .s(s),
        .sd(sd),
        .sdin(sdin));
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
