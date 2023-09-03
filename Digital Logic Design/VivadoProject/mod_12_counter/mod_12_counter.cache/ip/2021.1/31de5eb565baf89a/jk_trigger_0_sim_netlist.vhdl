-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Tue Oct 26 21:53:01 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode funcsim -rename_top decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix -prefix
--               decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_ jk_trigger_0_sim_netlist.vhdl
-- Design      : jk_trigger_0
-- Purpose     : This VHDL netlist is a functional simulation representation of the design and should not be modified or
--               synthesized. This netlist cannot be used for SDF annotated simulation.
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_jk_trigger is
  port (
    Q : out STD_LOGIC;
    CLK : in STD_LOGIC;
    Set : in STD_LOGIC;
    Reset : in STD_LOGIC;
    J : in STD_LOGIC;
    K : in STD_LOGIC
  );
end decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_jk_trigger;

architecture STRUCTURE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_jk_trigger is
  signal \^q\ : STD_LOGIC;
  signal Q_C_i_1_n_0 : STD_LOGIC;
  signal Q_reg_C_n_0 : STD_LOGIC;
  signal Q_reg_LDC_i_1_n_0 : STD_LOGIC;
  signal Q_reg_LDC_i_2_n_0 : STD_LOGIC;
  signal Q_reg_LDC_n_0 : STD_LOGIC;
  signal Q_reg_P_n_0 : STD_LOGIC;
  attribute OPT_MODIFIED : string;
  attribute OPT_MODIFIED of Q_reg_LDC : label is "MLO";
  attribute XILINX_LEGACY_PRIM : string;
  attribute XILINX_LEGACY_PRIM of Q_reg_LDC : label is "LDC";
  attribute OPT_MODIFIED of Q_reg_P : label is "MLO";
begin
  Q <= \^q\;
Q_C_i_1: unisim.vcomponents.LUT5
    generic map(
      INIT => X"70FF80F0"
    )
        port map (
      I0 => Reset,
      I1 => Set,
      I2 => J,
      I3 => K,
      I4 => \^q\,
      O => Q_C_i_1_n_0
    );
Q_INST_0: unisim.vcomponents.LUT3
    generic map(
      INIT => X"B8"
    )
        port map (
      I0 => Q_reg_P_n_0,
      I1 => Q_reg_LDC_n_0,
      I2 => Q_reg_C_n_0,
      O => \^q\
    );
Q_reg_C: unisim.vcomponents.FDCE
     port map (
      C => CLK,
      CE => '1',
      CLR => Q_reg_LDC_i_2_n_0,
      D => Q_C_i_1_n_0,
      Q => Q_reg_C_n_0
    );
Q_reg_LDC: unisim.vcomponents.LDCE
    generic map(
      INIT => '0',
      IS_G_INVERTED => '1'
    )
        port map (
      CLR => Q_reg_LDC_i_2_n_0,
      D => '1',
      G => Set,
      GE => '1',
      Q => Q_reg_LDC_n_0
    );
Q_reg_LDC_i_1: unisim.vcomponents.LUT1
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => Set,
      O => Q_reg_LDC_i_1_n_0
    );
Q_reg_LDC_i_2: unisim.vcomponents.LUT1
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => Reset,
      O => Q_reg_LDC_i_2_n_0
    );
Q_reg_P: unisim.vcomponents.FDPE
     port map (
      C => CLK,
      CE => '1',
      D => Q_C_i_1_n_0,
      PRE => Q_reg_LDC_i_1_n_0,
      Q => Q_reg_P_n_0
    );
end STRUCTURE;
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix is
  port (
    CLK : in STD_LOGIC;
    J : in STD_LOGIC;
    K : in STD_LOGIC;
    Set : in STD_LOGIC;
    Reset : in STD_LOGIC;
    Q : out STD_LOGIC
  );
  attribute NotValidForBitStream : boolean;
  attribute NotValidForBitStream of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is true;
  attribute CHECK_LICENSE_TYPE : string;
  attribute CHECK_LICENSE_TYPE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "jk_trigger_0,jk_trigger,{}";
  attribute DowngradeIPIdentifiedWarnings : string;
  attribute DowngradeIPIdentifiedWarnings of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "yes";
  attribute IP_DEFINITION_SOURCE : string;
  attribute IP_DEFINITION_SOURCE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "package_project";
  attribute X_CORE_INFO : string;
  attribute X_CORE_INFO of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "jk_trigger,Vivado 2021.1";
end decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix;

architecture STRUCTURE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix is
  attribute X_INTERFACE_INFO : string;
  attribute X_INTERFACE_INFO of CLK : signal is "xilinx.com:signal:clock:1.0 CLK CLK";
  attribute X_INTERFACE_PARAMETER : string;
  attribute X_INTERFACE_PARAMETER of CLK : signal is "XIL_INTERFACENAME CLK, ASSOCIATED_RESET Reset, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0";
  attribute X_INTERFACE_INFO of Reset : signal is "xilinx.com:signal:reset:1.0 Reset RST";
  attribute X_INTERFACE_PARAMETER of Reset : signal is "XIL_INTERFACENAME Reset, POLARITY ACTIVE_LOW, INSERT_VIP 0";
begin
inst: entity work.decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_jk_trigger
     port map (
      CLK => CLK,
      J => J,
      K => K,
      Q => Q,
      Reset => Reset,
      Set => Set
    );
end STRUCTURE;
