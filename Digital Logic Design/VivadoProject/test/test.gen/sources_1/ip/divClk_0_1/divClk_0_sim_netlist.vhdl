-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Mon Nov 29 19:15:35 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode funcsim -rename_top divClk_0 -prefix
--               divClk_0_ divClk_0_sim_netlist.vhdl
-- Design      : divClk_0
-- Purpose     : This VHDL netlist is a functional simulation representation of the design and should not be modified or
--               synthesized. This netlist cannot be used for SDF annotated simulation.
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity divClk_0_divClk is
  port (
    Clk : in STD_LOGIC;
    s1 : in STD_LOGIC;
    s0 : in STD_LOGIC;
    divClk : out STD_LOGIC
  );
end divClk_0_divClk;

architecture STRUCTURE of divClk_0_divClk is
  signal \counter[0]_i_10_n_0\ : STD_LOGIC;
  signal \counter[0]_i_11_n_0\ : STD_LOGIC;
  signal \counter[0]_i_12_n_0\ : STD_LOGIC;
  signal \counter[0]_i_13_n_0\ : STD_LOGIC;
  signal \counter[0]_i_15_n_0\ : STD_LOGIC;
  signal \counter[0]_i_16_n_0\ : STD_LOGIC;
  signal \counter[0]_i_17_n_0\ : STD_LOGIC;
  signal \counter[0]_i_18_n_0\ : STD_LOGIC;
  signal \counter[0]_i_19_n_0\ : STD_LOGIC;
  signal \counter[0]_i_1_n_0\ : STD_LOGIC;
  signal \counter[0]_i_20_n_0\ : STD_LOGIC;
  signal \counter[0]_i_21_n_0\ : STD_LOGIC;
  signal \counter[0]_i_22_n_0\ : STD_LOGIC;
  signal \counter[0]_i_24_n_0\ : STD_LOGIC;
  signal \counter[0]_i_25_n_0\ : STD_LOGIC;
  signal \counter[0]_i_26_n_0\ : STD_LOGIC;
  signal \counter[0]_i_27_n_0\ : STD_LOGIC;
  signal \counter[0]_i_28_n_0\ : STD_LOGIC;
  signal \counter[0]_i_29_n_0\ : STD_LOGIC;
  signal \counter[0]_i_30_n_0\ : STD_LOGIC;
  signal \counter[0]_i_31_n_0\ : STD_LOGIC;
  signal \counter[0]_i_32_n_0\ : STD_LOGIC;
  signal \counter[0]_i_33_n_0\ : STD_LOGIC;
  signal \counter[0]_i_34_n_0\ : STD_LOGIC;
  signal \counter[0]_i_35_n_0\ : STD_LOGIC;
  signal \counter[0]_i_36_n_0\ : STD_LOGIC;
  signal \counter[0]_i_37_n_0\ : STD_LOGIC;
  signal \counter[0]_i_38_n_0\ : STD_LOGIC;
  signal \counter[0]_i_39_n_0\ : STD_LOGIC;
  signal \counter[0]_i_4_n_0\ : STD_LOGIC;
  signal \counter[0]_i_6_n_0\ : STD_LOGIC;
  signal \counter[0]_i_7_n_0\ : STD_LOGIC;
  signal \counter[0]_i_8_n_0\ : STD_LOGIC;
  signal \counter[0]_i_9_n_0\ : STD_LOGIC;
  signal counter_reg : STD_LOGIC_VECTOR ( 31 downto 0 );
  signal \counter_reg[0]_i_14_n_0\ : STD_LOGIC;
  signal \counter_reg[0]_i_14_n_1\ : STD_LOGIC;
  signal \counter_reg[0]_i_14_n_2\ : STD_LOGIC;
  signal \counter_reg[0]_i_14_n_3\ : STD_LOGIC;
  signal \counter_reg[0]_i_23_n_0\ : STD_LOGIC;
  signal \counter_reg[0]_i_23_n_1\ : STD_LOGIC;
  signal \counter_reg[0]_i_23_n_2\ : STD_LOGIC;
  signal \counter_reg[0]_i_23_n_3\ : STD_LOGIC;
  signal \counter_reg[0]_i_2_n_0\ : STD_LOGIC;
  signal \counter_reg[0]_i_2_n_1\ : STD_LOGIC;
  signal \counter_reg[0]_i_2_n_2\ : STD_LOGIC;
  signal \counter_reg[0]_i_2_n_3\ : STD_LOGIC;
  signal \counter_reg[0]_i_2_n_4\ : STD_LOGIC;
  signal \counter_reg[0]_i_2_n_5\ : STD_LOGIC;
  signal \counter_reg[0]_i_2_n_6\ : STD_LOGIC;
  signal \counter_reg[0]_i_2_n_7\ : STD_LOGIC;
  signal \counter_reg[0]_i_3_n_0\ : STD_LOGIC;
  signal \counter_reg[0]_i_3_n_1\ : STD_LOGIC;
  signal \counter_reg[0]_i_3_n_2\ : STD_LOGIC;
  signal \counter_reg[0]_i_3_n_3\ : STD_LOGIC;
  signal \counter_reg[0]_i_5_n_0\ : STD_LOGIC;
  signal \counter_reg[0]_i_5_n_1\ : STD_LOGIC;
  signal \counter_reg[0]_i_5_n_2\ : STD_LOGIC;
  signal \counter_reg[0]_i_5_n_3\ : STD_LOGIC;
  signal \counter_reg[12]_i_1_n_0\ : STD_LOGIC;
  signal \counter_reg[12]_i_1_n_1\ : STD_LOGIC;
  signal \counter_reg[12]_i_1_n_2\ : STD_LOGIC;
  signal \counter_reg[12]_i_1_n_3\ : STD_LOGIC;
  signal \counter_reg[12]_i_1_n_4\ : STD_LOGIC;
  signal \counter_reg[12]_i_1_n_5\ : STD_LOGIC;
  signal \counter_reg[12]_i_1_n_6\ : STD_LOGIC;
  signal \counter_reg[12]_i_1_n_7\ : STD_LOGIC;
  signal \counter_reg[16]_i_1_n_0\ : STD_LOGIC;
  signal \counter_reg[16]_i_1_n_1\ : STD_LOGIC;
  signal \counter_reg[16]_i_1_n_2\ : STD_LOGIC;
  signal \counter_reg[16]_i_1_n_3\ : STD_LOGIC;
  signal \counter_reg[16]_i_1_n_4\ : STD_LOGIC;
  signal \counter_reg[16]_i_1_n_5\ : STD_LOGIC;
  signal \counter_reg[16]_i_1_n_6\ : STD_LOGIC;
  signal \counter_reg[16]_i_1_n_7\ : STD_LOGIC;
  signal \counter_reg[20]_i_1_n_0\ : STD_LOGIC;
  signal \counter_reg[20]_i_1_n_1\ : STD_LOGIC;
  signal \counter_reg[20]_i_1_n_2\ : STD_LOGIC;
  signal \counter_reg[20]_i_1_n_3\ : STD_LOGIC;
  signal \counter_reg[20]_i_1_n_4\ : STD_LOGIC;
  signal \counter_reg[20]_i_1_n_5\ : STD_LOGIC;
  signal \counter_reg[20]_i_1_n_6\ : STD_LOGIC;
  signal \counter_reg[20]_i_1_n_7\ : STD_LOGIC;
  signal \counter_reg[24]_i_1_n_0\ : STD_LOGIC;
  signal \counter_reg[24]_i_1_n_1\ : STD_LOGIC;
  signal \counter_reg[24]_i_1_n_2\ : STD_LOGIC;
  signal \counter_reg[24]_i_1_n_3\ : STD_LOGIC;
  signal \counter_reg[24]_i_1_n_4\ : STD_LOGIC;
  signal \counter_reg[24]_i_1_n_5\ : STD_LOGIC;
  signal \counter_reg[24]_i_1_n_6\ : STD_LOGIC;
  signal \counter_reg[24]_i_1_n_7\ : STD_LOGIC;
  signal \counter_reg[28]_i_1_n_1\ : STD_LOGIC;
  signal \counter_reg[28]_i_1_n_2\ : STD_LOGIC;
  signal \counter_reg[28]_i_1_n_3\ : STD_LOGIC;
  signal \counter_reg[28]_i_1_n_4\ : STD_LOGIC;
  signal \counter_reg[28]_i_1_n_5\ : STD_LOGIC;
  signal \counter_reg[28]_i_1_n_6\ : STD_LOGIC;
  signal \counter_reg[28]_i_1_n_7\ : STD_LOGIC;
  signal \counter_reg[4]_i_1_n_0\ : STD_LOGIC;
  signal \counter_reg[4]_i_1_n_1\ : STD_LOGIC;
  signal \counter_reg[4]_i_1_n_2\ : STD_LOGIC;
  signal \counter_reg[4]_i_1_n_3\ : STD_LOGIC;
  signal \counter_reg[4]_i_1_n_4\ : STD_LOGIC;
  signal \counter_reg[4]_i_1_n_5\ : STD_LOGIC;
  signal \counter_reg[4]_i_1_n_6\ : STD_LOGIC;
  signal \counter_reg[4]_i_1_n_7\ : STD_LOGIC;
  signal \counter_reg[8]_i_1_n_0\ : STD_LOGIC;
  signal \counter_reg[8]_i_1_n_1\ : STD_LOGIC;
  signal \counter_reg[8]_i_1_n_2\ : STD_LOGIC;
  signal \counter_reg[8]_i_1_n_3\ : STD_LOGIC;
  signal \counter_reg[8]_i_1_n_4\ : STD_LOGIC;
  signal \counter_reg[8]_i_1_n_5\ : STD_LOGIC;
  signal \counter_reg[8]_i_1_n_6\ : STD_LOGIC;
  signal \counter_reg[8]_i_1_n_7\ : STD_LOGIC;
  signal \^divclk\ : STD_LOGIC;
  signal divClk_i_10_n_0 : STD_LOGIC;
  signal divClk_i_11_n_0 : STD_LOGIC;
  signal divClk_i_12_n_0 : STD_LOGIC;
  signal divClk_i_13_n_0 : STD_LOGIC;
  signal divClk_i_14_n_0 : STD_LOGIC;
  signal divClk_i_15_n_0 : STD_LOGIC;
  signal divClk_i_1_n_0 : STD_LOGIC;
  signal divClk_i_4_n_0 : STD_LOGIC;
  signal divClk_i_5_n_0 : STD_LOGIC;
  signal divClk_i_6_n_0 : STD_LOGIC;
  signal divClk_i_8_n_0 : STD_LOGIC;
  signal divClk_i_9_n_0 : STD_LOGIC;
  signal divClk_reg_i_2_n_1 : STD_LOGIC;
  signal divClk_reg_i_2_n_2 : STD_LOGIC;
  signal divClk_reg_i_2_n_3 : STD_LOGIC;
  signal divClk_reg_i_3_n_0 : STD_LOGIC;
  signal divClk_reg_i_3_n_1 : STD_LOGIC;
  signal divClk_reg_i_3_n_2 : STD_LOGIC;
  signal divClk_reg_i_3_n_3 : STD_LOGIC;
  signal divClk_reg_i_7_n_0 : STD_LOGIC;
  signal divClk_reg_i_7_n_1 : STD_LOGIC;
  signal divClk_reg_i_7_n_2 : STD_LOGIC;
  signal divClk_reg_i_7_n_3 : STD_LOGIC;
  signal \NLW_counter_reg[0]_i_14_O_UNCONNECTED\ : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal \NLW_counter_reg[0]_i_23_O_UNCONNECTED\ : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal \NLW_counter_reg[0]_i_3_O_UNCONNECTED\ : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal \NLW_counter_reg[0]_i_5_O_UNCONNECTED\ : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal \NLW_counter_reg[28]_i_1_CO_UNCONNECTED\ : STD_LOGIC_VECTOR ( 3 to 3 );
  signal NLW_divClk_reg_i_2_CO_UNCONNECTED : STD_LOGIC_VECTOR ( 3 to 3 );
  signal NLW_divClk_reg_i_2_O_UNCONNECTED : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal NLW_divClk_reg_i_3_O_UNCONNECTED : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal NLW_divClk_reg_i_7_O_UNCONNECTED : STD_LOGIC_VECTOR ( 3 downto 0 );
  attribute COMPARATOR_THRESHOLD : integer;
  attribute COMPARATOR_THRESHOLD of \counter_reg[0]_i_14\ : label is 11;
  attribute ADDER_THRESHOLD : integer;
  attribute ADDER_THRESHOLD of \counter_reg[0]_i_2\ : label is 11;
  attribute COMPARATOR_THRESHOLD of \counter_reg[0]_i_23\ : label is 11;
  attribute COMPARATOR_THRESHOLD of \counter_reg[0]_i_3\ : label is 11;
  attribute COMPARATOR_THRESHOLD of \counter_reg[0]_i_5\ : label is 11;
  attribute ADDER_THRESHOLD of \counter_reg[12]_i_1\ : label is 11;
  attribute ADDER_THRESHOLD of \counter_reg[16]_i_1\ : label is 11;
  attribute ADDER_THRESHOLD of \counter_reg[20]_i_1\ : label is 11;
  attribute ADDER_THRESHOLD of \counter_reg[24]_i_1\ : label is 11;
  attribute ADDER_THRESHOLD of \counter_reg[28]_i_1\ : label is 11;
  attribute ADDER_THRESHOLD of \counter_reg[4]_i_1\ : label is 11;
  attribute ADDER_THRESHOLD of \counter_reg[8]_i_1\ : label is 11;
begin
  divClk <= \^divclk\;
\counter[0]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"E"
    )
        port map (
      I0 => divClk_reg_i_2_n_1,
      I1 => \counter_reg[0]_i_3_n_0\,
      O => \counter[0]_i_1_n_0\
    );
\counter[0]_i_10\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => counter_reg(30),
      I1 => counter_reg(31),
      O => \counter[0]_i_10_n_0\
    );
\counter[0]_i_11\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => counter_reg(28),
      I1 => counter_reg(29),
      O => \counter[0]_i_11_n_0\
    );
\counter[0]_i_12\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => counter_reg(26),
      I1 => counter_reg(27),
      O => \counter[0]_i_12_n_0\
    );
\counter[0]_i_13\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"1241"
    )
        port map (
      I0 => counter_reg(24),
      I1 => counter_reg(25),
      I2 => s0,
      I3 => s1,
      O => \counter[0]_i_13_n_0\
    );
\counter[0]_i_15\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"F3A2"
    )
        port map (
      I0 => counter_reg(22),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(23),
      O => \counter[0]_i_15_n_0\
    );
\counter[0]_i_16\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"C382"
    )
        port map (
      I0 => counter_reg(20),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(21),
      O => \counter[0]_i_16_n_0\
    );
\counter[0]_i_17\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"FBA2"
    )
        port map (
      I0 => counter_reg(18),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(19),
      O => \counter[0]_i_17_n_0\
    );
\counter[0]_i_18\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"FB82"
    )
        port map (
      I0 => counter_reg(16),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(17),
      O => \counter[0]_i_18_n_0\
    );
\counter[0]_i_19\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"1181"
    )
        port map (
      I0 => counter_reg(22),
      I1 => counter_reg(23),
      I2 => s0,
      I3 => s1,
      O => \counter[0]_i_19_n_0\
    );
\counter[0]_i_20\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"1881"
    )
        port map (
      I0 => counter_reg(20),
      I1 => counter_reg(21),
      I2 => s0,
      I3 => s1,
      O => \counter[0]_i_20_n_0\
    );
\counter[0]_i_21\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"0059"
    )
        port map (
      I0 => counter_reg(19),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(18),
      O => \counter[0]_i_21_n_0\
    );
\counter[0]_i_22\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"1241"
    )
        port map (
      I0 => counter_reg(16),
      I1 => counter_reg(17),
      I2 => s0,
      I3 => s1,
      O => \counter[0]_i_22_n_0\
    );
\counter[0]_i_24\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"F3A2"
    )
        port map (
      I0 => counter_reg(14),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(15),
      O => \counter[0]_i_24_n_0\
    );
\counter[0]_i_25\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"E382"
    )
        port map (
      I0 => counter_reg(12),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(13),
      O => \counter[0]_i_25_n_0\
    );
\counter[0]_i_26\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"F3A2"
    )
        port map (
      I0 => counter_reg(10),
      I1 => s1,
      I2 => s0,
      I3 => counter_reg(11),
      O => \counter[0]_i_26_n_0\
    );
\counter[0]_i_27\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"FF8A"
    )
        port map (
      I0 => counter_reg(8),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(9),
      O => \counter[0]_i_27_n_0\
    );
\counter[0]_i_28\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"1181"
    )
        port map (
      I0 => counter_reg(14),
      I1 => counter_reg(15),
      I2 => s0,
      I3 => s1,
      O => \counter[0]_i_28_n_0\
    );
\counter[0]_i_29\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"1481"
    )
        port map (
      I0 => counter_reg(12),
      I1 => counter_reg(13),
      I2 => s0,
      I3 => s1,
      O => \counter[0]_i_29_n_0\
    );
\counter[0]_i_30\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"1181"
    )
        port map (
      I0 => counter_reg(10),
      I1 => counter_reg(11),
      I2 => s1,
      I3 => s0,
      O => \counter[0]_i_30_n_0\
    );
\counter[0]_i_31\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"0059"
    )
        port map (
      I0 => counter_reg(8),
      I1 => s1,
      I2 => s0,
      I3 => counter_reg(9),
      O => \counter[0]_i_31_n_0\
    );
\counter[0]_i_32\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"FBA2"
    )
        port map (
      I0 => counter_reg(6),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(7),
      O => \counter[0]_i_32_n_0\
    );
\counter[0]_i_33\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"E"
    )
        port map (
      I0 => counter_reg(4),
      I1 => counter_reg(5),
      O => \counter[0]_i_33_n_0\
    );
\counter[0]_i_34\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"E"
    )
        port map (
      I0 => counter_reg(2),
      I1 => counter_reg(3),
      O => \counter[0]_i_34_n_0\
    );
\counter[0]_i_35\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"FF28"
    )
        port map (
      I0 => counter_reg(0),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(1),
      O => \counter[0]_i_35_n_0\
    );
\counter[0]_i_36\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"0059"
    )
        port map (
      I0 => counter_reg(7),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(6),
      O => \counter[0]_i_36_n_0\
    );
\counter[0]_i_37\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => counter_reg(4),
      I1 => counter_reg(5),
      O => \counter[0]_i_37_n_0\
    );
\counter[0]_i_38\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => counter_reg(2),
      I1 => counter_reg(3),
      O => \counter[0]_i_38_n_0\
    );
\counter[0]_i_39\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"0096"
    )
        port map (
      I0 => counter_reg(0),
      I1 => s1,
      I2 => s0,
      I3 => counter_reg(1),
      O => \counter[0]_i_39_n_0\
    );
\counter[0]_i_4\: unisim.vcomponents.LUT1
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => counter_reg(0),
      O => \counter[0]_i_4_n_0\
    );
\counter[0]_i_6\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"E"
    )
        port map (
      I0 => counter_reg(30),
      I1 => counter_reg(31),
      O => \counter[0]_i_6_n_0\
    );
\counter[0]_i_7\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"E"
    )
        port map (
      I0 => counter_reg(28),
      I1 => counter_reg(29),
      O => \counter[0]_i_7_n_0\
    );
\counter[0]_i_8\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"E"
    )
        port map (
      I0 => counter_reg(26),
      I1 => counter_reg(27),
      O => \counter[0]_i_8_n_0\
    );
\counter[0]_i_9\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"FB82"
    )
        port map (
      I0 => counter_reg(24),
      I1 => s0,
      I2 => s1,
      I3 => counter_reg(25),
      O => \counter[0]_i_9_n_0\
    );
\counter_reg[0]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[0]_i_2_n_7\,
      Q => counter_reg(0),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[0]_i_14\: unisim.vcomponents.CARRY4
     port map (
      CI => \counter_reg[0]_i_23_n_0\,
      CO(3) => \counter_reg[0]_i_14_n_0\,
      CO(2) => \counter_reg[0]_i_14_n_1\,
      CO(1) => \counter_reg[0]_i_14_n_2\,
      CO(0) => \counter_reg[0]_i_14_n_3\,
      CYINIT => '0',
      DI(3) => \counter[0]_i_24_n_0\,
      DI(2) => \counter[0]_i_25_n_0\,
      DI(1) => \counter[0]_i_26_n_0\,
      DI(0) => \counter[0]_i_27_n_0\,
      O(3 downto 0) => \NLW_counter_reg[0]_i_14_O_UNCONNECTED\(3 downto 0),
      S(3) => \counter[0]_i_28_n_0\,
      S(2) => \counter[0]_i_29_n_0\,
      S(1) => \counter[0]_i_30_n_0\,
      S(0) => \counter[0]_i_31_n_0\
    );
\counter_reg[0]_i_2\: unisim.vcomponents.CARRY4
     port map (
      CI => '0',
      CO(3) => \counter_reg[0]_i_2_n_0\,
      CO(2) => \counter_reg[0]_i_2_n_1\,
      CO(1) => \counter_reg[0]_i_2_n_2\,
      CO(0) => \counter_reg[0]_i_2_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0001",
      O(3) => \counter_reg[0]_i_2_n_4\,
      O(2) => \counter_reg[0]_i_2_n_5\,
      O(1) => \counter_reg[0]_i_2_n_6\,
      O(0) => \counter_reg[0]_i_2_n_7\,
      S(3 downto 1) => counter_reg(3 downto 1),
      S(0) => \counter[0]_i_4_n_0\
    );
\counter_reg[0]_i_23\: unisim.vcomponents.CARRY4
     port map (
      CI => '0',
      CO(3) => \counter_reg[0]_i_23_n_0\,
      CO(2) => \counter_reg[0]_i_23_n_1\,
      CO(1) => \counter_reg[0]_i_23_n_2\,
      CO(0) => \counter_reg[0]_i_23_n_3\,
      CYINIT => '0',
      DI(3) => \counter[0]_i_32_n_0\,
      DI(2) => \counter[0]_i_33_n_0\,
      DI(1) => \counter[0]_i_34_n_0\,
      DI(0) => \counter[0]_i_35_n_0\,
      O(3 downto 0) => \NLW_counter_reg[0]_i_23_O_UNCONNECTED\(3 downto 0),
      S(3) => \counter[0]_i_36_n_0\,
      S(2) => \counter[0]_i_37_n_0\,
      S(1) => \counter[0]_i_38_n_0\,
      S(0) => \counter[0]_i_39_n_0\
    );
\counter_reg[0]_i_3\: unisim.vcomponents.CARRY4
     port map (
      CI => \counter_reg[0]_i_5_n_0\,
      CO(3) => \counter_reg[0]_i_3_n_0\,
      CO(2) => \counter_reg[0]_i_3_n_1\,
      CO(1) => \counter_reg[0]_i_3_n_2\,
      CO(0) => \counter_reg[0]_i_3_n_3\,
      CYINIT => '0',
      DI(3) => \counter[0]_i_6_n_0\,
      DI(2) => \counter[0]_i_7_n_0\,
      DI(1) => \counter[0]_i_8_n_0\,
      DI(0) => \counter[0]_i_9_n_0\,
      O(3 downto 0) => \NLW_counter_reg[0]_i_3_O_UNCONNECTED\(3 downto 0),
      S(3) => \counter[0]_i_10_n_0\,
      S(2) => \counter[0]_i_11_n_0\,
      S(1) => \counter[0]_i_12_n_0\,
      S(0) => \counter[0]_i_13_n_0\
    );
\counter_reg[0]_i_5\: unisim.vcomponents.CARRY4
     port map (
      CI => \counter_reg[0]_i_14_n_0\,
      CO(3) => \counter_reg[0]_i_5_n_0\,
      CO(2) => \counter_reg[0]_i_5_n_1\,
      CO(1) => \counter_reg[0]_i_5_n_2\,
      CO(0) => \counter_reg[0]_i_5_n_3\,
      CYINIT => '0',
      DI(3) => \counter[0]_i_15_n_0\,
      DI(2) => \counter[0]_i_16_n_0\,
      DI(1) => \counter[0]_i_17_n_0\,
      DI(0) => \counter[0]_i_18_n_0\,
      O(3 downto 0) => \NLW_counter_reg[0]_i_5_O_UNCONNECTED\(3 downto 0),
      S(3) => \counter[0]_i_19_n_0\,
      S(2) => \counter[0]_i_20_n_0\,
      S(1) => \counter[0]_i_21_n_0\,
      S(0) => \counter[0]_i_22_n_0\
    );
\counter_reg[10]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[8]_i_1_n_5\,
      Q => counter_reg(10),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[11]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[8]_i_1_n_4\,
      Q => counter_reg(11),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[12]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[12]_i_1_n_7\,
      Q => counter_reg(12),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[12]_i_1\: unisim.vcomponents.CARRY4
     port map (
      CI => \counter_reg[8]_i_1_n_0\,
      CO(3) => \counter_reg[12]_i_1_n_0\,
      CO(2) => \counter_reg[12]_i_1_n_1\,
      CO(1) => \counter_reg[12]_i_1_n_2\,
      CO(0) => \counter_reg[12]_i_1_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3) => \counter_reg[12]_i_1_n_4\,
      O(2) => \counter_reg[12]_i_1_n_5\,
      O(1) => \counter_reg[12]_i_1_n_6\,
      O(0) => \counter_reg[12]_i_1_n_7\,
      S(3 downto 0) => counter_reg(15 downto 12)
    );
\counter_reg[13]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[12]_i_1_n_6\,
      Q => counter_reg(13),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[14]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[12]_i_1_n_5\,
      Q => counter_reg(14),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[15]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[12]_i_1_n_4\,
      Q => counter_reg(15),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[16]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[16]_i_1_n_7\,
      Q => counter_reg(16),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[16]_i_1\: unisim.vcomponents.CARRY4
     port map (
      CI => \counter_reg[12]_i_1_n_0\,
      CO(3) => \counter_reg[16]_i_1_n_0\,
      CO(2) => \counter_reg[16]_i_1_n_1\,
      CO(1) => \counter_reg[16]_i_1_n_2\,
      CO(0) => \counter_reg[16]_i_1_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3) => \counter_reg[16]_i_1_n_4\,
      O(2) => \counter_reg[16]_i_1_n_5\,
      O(1) => \counter_reg[16]_i_1_n_6\,
      O(0) => \counter_reg[16]_i_1_n_7\,
      S(3 downto 0) => counter_reg(19 downto 16)
    );
\counter_reg[17]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[16]_i_1_n_6\,
      Q => counter_reg(17),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[18]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[16]_i_1_n_5\,
      Q => counter_reg(18),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[19]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[16]_i_1_n_4\,
      Q => counter_reg(19),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[1]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[0]_i_2_n_6\,
      Q => counter_reg(1),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[20]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[20]_i_1_n_7\,
      Q => counter_reg(20),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[20]_i_1\: unisim.vcomponents.CARRY4
     port map (
      CI => \counter_reg[16]_i_1_n_0\,
      CO(3) => \counter_reg[20]_i_1_n_0\,
      CO(2) => \counter_reg[20]_i_1_n_1\,
      CO(1) => \counter_reg[20]_i_1_n_2\,
      CO(0) => \counter_reg[20]_i_1_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3) => \counter_reg[20]_i_1_n_4\,
      O(2) => \counter_reg[20]_i_1_n_5\,
      O(1) => \counter_reg[20]_i_1_n_6\,
      O(0) => \counter_reg[20]_i_1_n_7\,
      S(3 downto 0) => counter_reg(23 downto 20)
    );
\counter_reg[21]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[20]_i_1_n_6\,
      Q => counter_reg(21),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[22]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[20]_i_1_n_5\,
      Q => counter_reg(22),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[23]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[20]_i_1_n_4\,
      Q => counter_reg(23),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[24]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[24]_i_1_n_7\,
      Q => counter_reg(24),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[24]_i_1\: unisim.vcomponents.CARRY4
     port map (
      CI => \counter_reg[20]_i_1_n_0\,
      CO(3) => \counter_reg[24]_i_1_n_0\,
      CO(2) => \counter_reg[24]_i_1_n_1\,
      CO(1) => \counter_reg[24]_i_1_n_2\,
      CO(0) => \counter_reg[24]_i_1_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3) => \counter_reg[24]_i_1_n_4\,
      O(2) => \counter_reg[24]_i_1_n_5\,
      O(1) => \counter_reg[24]_i_1_n_6\,
      O(0) => \counter_reg[24]_i_1_n_7\,
      S(3 downto 0) => counter_reg(27 downto 24)
    );
\counter_reg[25]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[24]_i_1_n_6\,
      Q => counter_reg(25),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[26]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[24]_i_1_n_5\,
      Q => counter_reg(26),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[27]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[24]_i_1_n_4\,
      Q => counter_reg(27),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[28]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[28]_i_1_n_7\,
      Q => counter_reg(28),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[28]_i_1\: unisim.vcomponents.CARRY4
     port map (
      CI => \counter_reg[24]_i_1_n_0\,
      CO(3) => \NLW_counter_reg[28]_i_1_CO_UNCONNECTED\(3),
      CO(2) => \counter_reg[28]_i_1_n_1\,
      CO(1) => \counter_reg[28]_i_1_n_2\,
      CO(0) => \counter_reg[28]_i_1_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3) => \counter_reg[28]_i_1_n_4\,
      O(2) => \counter_reg[28]_i_1_n_5\,
      O(1) => \counter_reg[28]_i_1_n_6\,
      O(0) => \counter_reg[28]_i_1_n_7\,
      S(3 downto 0) => counter_reg(31 downto 28)
    );
\counter_reg[29]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[28]_i_1_n_6\,
      Q => counter_reg(29),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[2]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[0]_i_2_n_5\,
      Q => counter_reg(2),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[30]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[28]_i_1_n_5\,
      Q => counter_reg(30),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[31]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[28]_i_1_n_4\,
      Q => counter_reg(31),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[3]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[0]_i_2_n_4\,
      Q => counter_reg(3),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[4]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[4]_i_1_n_7\,
      Q => counter_reg(4),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[4]_i_1\: unisim.vcomponents.CARRY4
     port map (
      CI => \counter_reg[0]_i_2_n_0\,
      CO(3) => \counter_reg[4]_i_1_n_0\,
      CO(2) => \counter_reg[4]_i_1_n_1\,
      CO(1) => \counter_reg[4]_i_1_n_2\,
      CO(0) => \counter_reg[4]_i_1_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3) => \counter_reg[4]_i_1_n_4\,
      O(2) => \counter_reg[4]_i_1_n_5\,
      O(1) => \counter_reg[4]_i_1_n_6\,
      O(0) => \counter_reg[4]_i_1_n_7\,
      S(3 downto 0) => counter_reg(7 downto 4)
    );
\counter_reg[5]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[4]_i_1_n_6\,
      Q => counter_reg(5),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[6]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[4]_i_1_n_5\,
      Q => counter_reg(6),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[7]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[4]_i_1_n_4\,
      Q => counter_reg(7),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[8]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[8]_i_1_n_7\,
      Q => counter_reg(8),
      R => \counter[0]_i_1_n_0\
    );
\counter_reg[8]_i_1\: unisim.vcomponents.CARRY4
     port map (
      CI => \counter_reg[4]_i_1_n_0\,
      CO(3) => \counter_reg[8]_i_1_n_0\,
      CO(2) => \counter_reg[8]_i_1_n_1\,
      CO(1) => \counter_reg[8]_i_1_n_2\,
      CO(0) => \counter_reg[8]_i_1_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3) => \counter_reg[8]_i_1_n_4\,
      O(2) => \counter_reg[8]_i_1_n_5\,
      O(1) => \counter_reg[8]_i_1_n_6\,
      O(0) => \counter_reg[8]_i_1_n_7\,
      S(3 downto 0) => counter_reg(11 downto 8)
    );
\counter_reg[9]\: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => \counter_reg[8]_i_1_n_6\,
      Q => counter_reg(9),
      R => \counter[0]_i_1_n_0\
    );
divClk_i_1: unisim.vcomponents.LUT2
    generic map(
      INIT => X"6"
    )
        port map (
      I0 => divClk_reg_i_2_n_1,
      I1 => \^divclk\,
      O => divClk_i_1_n_0
    );
divClk_i_10: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00042041"
    )
        port map (
      I0 => counter_reg(15),
      I1 => s1,
      I2 => s0,
      I3 => counter_reg(17),
      I4 => counter_reg(16),
      O => divClk_i_10_n_0
    );
divClk_i_11: unisim.vcomponents.LUT5
    generic map(
      INIT => X"20040041"
    )
        port map (
      I0 => counter_reg(12),
      I1 => s1,
      I2 => s0,
      I3 => counter_reg(14),
      I4 => counter_reg(13),
      O => divClk_i_11_n_0
    );
divClk_i_12: unisim.vcomponents.LUT5
    generic map(
      INIT => X"0000400B"
    )
        port map (
      I0 => s0,
      I1 => s1,
      I2 => counter_reg(11),
      I3 => counter_reg(10),
      I4 => counter_reg(9),
      O => divClk_i_12_n_0
    );
divClk_i_13: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00000249"
    )
        port map (
      I0 => s0,
      I1 => s1,
      I2 => counter_reg(8),
      I3 => counter_reg(7),
      I4 => counter_reg(6),
      O => divClk_i_13_n_0
    );
divClk_i_14: unisim.vcomponents.LUT3
    generic map(
      INIT => X"01"
    )
        port map (
      I0 => counter_reg(5),
      I1 => counter_reg(4),
      I2 => counter_reg(3),
      O => divClk_i_14_n_0
    );
divClk_i_15: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00000096"
    )
        port map (
      I0 => counter_reg(0),
      I1 => s1,
      I2 => s0,
      I3 => counter_reg(2),
      I4 => counter_reg(1),
      O => divClk_i_15_n_0
    );
divClk_i_4: unisim.vcomponents.LUT2
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => counter_reg(30),
      I1 => counter_reg(31),
      O => divClk_i_4_n_0
    );
divClk_i_5: unisim.vcomponents.LUT3
    generic map(
      INIT => X"01"
    )
        port map (
      I0 => counter_reg(29),
      I1 => counter_reg(28),
      I2 => counter_reg(27),
      O => divClk_i_5_n_0
    );
divClk_i_6: unisim.vcomponents.LUT5
    generic map(
      INIT => X"01001021"
    )
        port map (
      I0 => counter_reg(24),
      I1 => counter_reg(26),
      I2 => s1,
      I3 => s0,
      I4 => counter_reg(25),
      O => divClk_i_6_n_0
    );
divClk_i_8: unisim.vcomponents.LUT5
    generic map(
      INIT => X"20000049"
    )
        port map (
      I0 => counter_reg(21),
      I1 => s1,
      I2 => s0,
      I3 => counter_reg(23),
      I4 => counter_reg(22),
      O => divClk_i_8_n_0
    );
divClk_i_9: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00004029"
    )
        port map (
      I0 => s1,
      I1 => s0,
      I2 => counter_reg(20),
      I3 => counter_reg(19),
      I4 => counter_reg(18),
      O => divClk_i_9_n_0
    );
divClk_reg: unisim.vcomponents.FDRE
     port map (
      C => Clk,
      CE => '1',
      D => divClk_i_1_n_0,
      Q => \^divclk\,
      R => '0'
    );
divClk_reg_i_2: unisim.vcomponents.CARRY4
     port map (
      CI => divClk_reg_i_3_n_0,
      CO(3) => NLW_divClk_reg_i_2_CO_UNCONNECTED(3),
      CO(2) => divClk_reg_i_2_n_1,
      CO(1) => divClk_reg_i_2_n_2,
      CO(0) => divClk_reg_i_2_n_3,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3 downto 0) => NLW_divClk_reg_i_2_O_UNCONNECTED(3 downto 0),
      S(3) => '0',
      S(2) => divClk_i_4_n_0,
      S(1) => divClk_i_5_n_0,
      S(0) => divClk_i_6_n_0
    );
divClk_reg_i_3: unisim.vcomponents.CARRY4
     port map (
      CI => divClk_reg_i_7_n_0,
      CO(3) => divClk_reg_i_3_n_0,
      CO(2) => divClk_reg_i_3_n_1,
      CO(1) => divClk_reg_i_3_n_2,
      CO(0) => divClk_reg_i_3_n_3,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3 downto 0) => NLW_divClk_reg_i_3_O_UNCONNECTED(3 downto 0),
      S(3) => divClk_i_8_n_0,
      S(2) => divClk_i_9_n_0,
      S(1) => divClk_i_10_n_0,
      S(0) => divClk_i_11_n_0
    );
divClk_reg_i_7: unisim.vcomponents.CARRY4
     port map (
      CI => '0',
      CO(3) => divClk_reg_i_7_n_0,
      CO(2) => divClk_reg_i_7_n_1,
      CO(1) => divClk_reg_i_7_n_2,
      CO(0) => divClk_reg_i_7_n_3,
      CYINIT => '1',
      DI(3 downto 0) => B"0000",
      O(3 downto 0) => NLW_divClk_reg_i_7_O_UNCONNECTED(3 downto 0),
      S(3) => divClk_i_12_n_0,
      S(2) => divClk_i_13_n_0,
      S(1) => divClk_i_14_n_0,
      S(0) => divClk_i_15_n_0
    );
end STRUCTURE;
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity divClk_0 is
  port (
    Clk : in STD_LOGIC;
    s1 : in STD_LOGIC;
    s0 : in STD_LOGIC;
    divClk : out STD_LOGIC
  );
  attribute NotValidForBitStream : boolean;
  attribute NotValidForBitStream of divClk_0 : entity is true;
  attribute CHECK_LICENSE_TYPE : string;
  attribute CHECK_LICENSE_TYPE of divClk_0 : entity is "divClk_0,divClk,{}";
  attribute DowngradeIPIdentifiedWarnings : string;
  attribute DowngradeIPIdentifiedWarnings of divClk_0 : entity is "yes";
  attribute IP_DEFINITION_SOURCE : string;
  attribute IP_DEFINITION_SOURCE of divClk_0 : entity is "package_project";
  attribute X_CORE_INFO : string;
  attribute X_CORE_INFO of divClk_0 : entity is "divClk,Vivado 2021.1";
end divClk_0;

architecture STRUCTURE of divClk_0 is
  attribute X_INTERFACE_INFO : string;
  attribute X_INTERFACE_INFO of Clk : signal is "xilinx.com:signal:clock:1.0 Clk CLK";
  attribute X_INTERFACE_PARAMETER : string;
  attribute X_INTERFACE_PARAMETER of Clk : signal is "XIL_INTERFACENAME Clk, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0";
begin
inst: entity work.divClk_0_divClk
     port map (
      Clk => Clk,
      divClk => divClk,
      s0 => s0,
      s1 => s1
    );
end STRUCTURE;
