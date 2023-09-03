// Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
// Date        : Tue Oct 26 21:53:01 2021
// Host        : MateBook14 running 64-bit major release  (build 9200)
// Command     : write_verilog -force -mode funcsim -rename_top decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix -prefix
//               decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_ jk_trigger_0_sim_netlist.v
// Design      : jk_trigger_0
// Purpose     : This verilog netlist is a functional simulation representation of the design and should not be modified
//               or synthesized. This netlist cannot be used for SDF annotated simulation.
// Device      : xc7a35tcsg324-3
// --------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_jk_trigger
   (Q,
    CLK,
    Set,
    Reset,
    J,
    K);
  output Q;
  input CLK;
  input Set;
  input Reset;
  input J;
  input K;

  wire CLK;
  wire J;
  wire K;
  wire Q;
  wire Q_C_i_1_n_0;
  wire Q_reg_C_n_0;
  wire Q_reg_LDC_i_1_n_0;
  wire Q_reg_LDC_i_2_n_0;
  wire Q_reg_LDC_n_0;
  wire Q_reg_P_n_0;
  wire Reset;
  wire Set;

  LUT5 #(
    .INIT(32'h70FF80F0)) 
    Q_C_i_1
       (.I0(Reset),
        .I1(Set),
        .I2(J),
        .I3(K),
        .I4(Q),
        .O(Q_C_i_1_n_0));
  LUT3 #(
    .INIT(8'hB8)) 
    Q_INST_0
       (.I0(Q_reg_P_n_0),
        .I1(Q_reg_LDC_n_0),
        .I2(Q_reg_C_n_0),
        .O(Q));
  FDCE Q_reg_C
       (.C(CLK),
        .CE(1'b1),
        .CLR(Q_reg_LDC_i_2_n_0),
        .D(Q_C_i_1_n_0),
        .Q(Q_reg_C_n_0));
  (* OPT_MODIFIED = "MLO" *) 
  (* XILINX_LEGACY_PRIM = "LDC" *) 
  LDCE #(
    .INIT(1'b0),
    .IS_G_INVERTED(1'b1)) 
    Q_reg_LDC
       (.CLR(Q_reg_LDC_i_2_n_0),
        .D(1'b1),
        .G(Set),
        .GE(1'b1),
        .Q(Q_reg_LDC_n_0));
  LUT1 #(
    .INIT(2'h1)) 
    Q_reg_LDC_i_1
       (.I0(Set),
        .O(Q_reg_LDC_i_1_n_0));
  LUT1 #(
    .INIT(2'h1)) 
    Q_reg_LDC_i_2
       (.I0(Reset),
        .O(Q_reg_LDC_i_2_n_0));
  (* OPT_MODIFIED = "MLO" *) 
  FDPE Q_reg_P
       (.C(CLK),
        .CE(1'b1),
        .D(Q_C_i_1_n_0),
        .PRE(Q_reg_LDC_i_1_n_0),
        .Q(Q_reg_P_n_0));
endmodule

(* CHECK_LICENSE_TYPE = "jk_trigger_0,jk_trigger,{}" *) (* DowngradeIPIdentifiedWarnings = "yes" *) (* IP_DEFINITION_SOURCE = "package_project" *) 
(* X_CORE_INFO = "jk_trigger,Vivado 2021.1" *) 
(* NotValidForBitStream *)
module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix
   (CLK,
    J,
    K,
    Set,
    Reset,
    Q);
  (* X_INTERFACE_INFO = "xilinx.com:signal:clock:1.0 CLK CLK" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME CLK, ASSOCIATED_RESET Reset, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0" *) input CLK;
  input J;
  input K;
  input Set;
  (* X_INTERFACE_INFO = "xilinx.com:signal:reset:1.0 Reset RST" *) (* X_INTERFACE_PARAMETER = "XIL_INTERFACENAME Reset, POLARITY ACTIVE_LOW, INSERT_VIP 0" *) input Reset;
  output Q;

  wire CLK;
  wire J;
  wire K;
  wire Q;
  wire Reset;
  wire Set;

  decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_jk_trigger inst
       (.CLK(CLK),
        .J(J),
        .K(K),
        .Q(Q),
        .Reset(Reset),
        .Set(Set));
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
