-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Tue Nov 30 21:39:35 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode funcsim
--               d:/VProject/Carlight/Carlight.gen/sources_1/ip/led_0_3/led_0_sim_netlist.vhdl
-- Design      : led_0
-- Purpose     : This VHDL netlist is a functional simulation representation of the design and should not be modified or
--               synthesized. This netlist cannot be used for SDF annotated simulation.
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity led_0_settime is
  port (
    Q : out STD_LOGIC_VECTOR ( 3 downto 0 );
    switch : in STD_LOGIC;
    down : in STD_LOGIC;
    up : in STD_LOGIC
  );
  attribute ORIG_REF_NAME : string;
  attribute ORIG_REF_NAME of led_0_settime : entity is "settime";
end led_0_settime;

architecture STRUCTURE of led_0_settime is
  signal \^q\ : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal \c1[0]_i_1_n_0\ : STD_LOGIC;
  signal \c1[1]__0_i_1_n_0\ : STD_LOGIC;
  signal \c1[1]_i_1_n_0\ : STD_LOGIC;
  signal \c1[2]__0_i_1_n_0\ : STD_LOGIC;
  signal \c1[2]_i_1_n_0\ : STD_LOGIC;
  signal \c1[3]__0_i_1_n_0\ : STD_LOGIC;
  signal \c1[3]_i_1_n_0\ : STD_LOGIC;
  signal p_0_in : STD_LOGIC;
  signal s : STD_LOGIC;
begin
  Q(3 downto 0) <= \^q\(3 downto 0);
\c1[0]_i_1\: unisim.vcomponents.LUT1
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => \^q\(0),
      O => \c1[0]_i_1_n_0\
    );
\c1[1]__0_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"6"
    )
        port map (
      I0 => \^q\(0),
      I1 => \^q\(1),
      O => \c1[1]__0_i_1_n_0\
    );
\c1[1]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"9"
    )
        port map (
      I0 => \^q\(1),
      I1 => \^q\(0),
      O => \c1[1]_i_1_n_0\
    );
\c1[2]__0_i_1\: unisim.vcomponents.LUT3
    generic map(
      INIT => X"78"
    )
        port map (
      I0 => \^q\(0),
      I1 => \^q\(1),
      I2 => \^q\(2),
      O => \c1[2]__0_i_1_n_0\
    );
\c1[2]_i_1\: unisim.vcomponents.LUT3
    generic map(
      INIT => X"E1"
    )
        port map (
      I0 => \^q\(0),
      I1 => \^q\(1),
      I2 => \^q\(2),
      O => \c1[2]_i_1_n_0\
    );
\c1[3]__0_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"7F80"
    )
        port map (
      I0 => \^q\(2),
      I1 => \^q\(1),
      I2 => \^q\(0),
      I3 => \^q\(3),
      O => \c1[3]__0_i_1_n_0\
    );
\c1[3]_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"FE01"
    )
        port map (
      I0 => \^q\(2),
      I1 => \^q\(1),
      I2 => \^q\(0),
      I3 => \^q\(3),
      O => \c1[3]_i_1_n_0\
    );
\c1_reg[0]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => down,
      CE => s,
      D => \c1[0]_i_1_n_0\,
      Q => \^q\(0),
      R => '0'
    );
\c1_reg[0]__0\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => up,
      CE => s,
      D => \c1[0]_i_1_n_0\,
      Q => \^q\(0),
      R => '0'
    );
\c1_reg[1]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '1'
    )
        port map (
      C => down,
      CE => s,
      D => \c1[1]_i_1_n_0\,
      Q => \^q\(1),
      R => '0'
    );
\c1_reg[1]__0\: unisim.vcomponents.FDRE
    generic map(
      INIT => '1'
    )
        port map (
      C => up,
      CE => s,
      D => \c1[1]__0_i_1_n_0\,
      Q => \^q\(1),
      R => '0'
    );
\c1_reg[2]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => down,
      CE => s,
      D => \c1[2]_i_1_n_0\,
      Q => \^q\(2),
      R => '0'
    );
\c1_reg[2]__0\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => up,
      CE => s,
      D => \c1[2]__0_i_1_n_0\,
      Q => \^q\(2),
      R => '0'
    );
\c1_reg[3]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '1'
    )
        port map (
      C => down,
      CE => s,
      D => \c1[3]_i_1_n_0\,
      Q => \^q\(3),
      R => '0'
    );
\c1_reg[3]__0\: unisim.vcomponents.FDRE
    generic map(
      INIT => '1'
    )
        port map (
      C => up,
      CE => s,
      D => \c1[3]__0_i_1_n_0\,
      Q => \^q\(3),
      R => '0'
    );
s_i_1: unisim.vcomponents.LUT1
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => s,
      O => p_0_in
    );
s_reg: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => switch,
      CE => '1',
      D => p_0_in,
      Q => s,
      R => '0'
    );
end STRUCTURE;
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity led_0_led is
  port (
    t1 : out STD_LOGIC_VECTOR ( 3 downto 0 );
    Q : out STD_LOGIC_VECTOR ( 7 downto 0 );
    down : in STD_LOGIC;
    up : in STD_LOGIC;
    switch : in STD_LOGIC;
    clk : in STD_LOGIC;
    a : in STD_LOGIC_VECTOR ( 2 downto 0 )
  );
  attribute ORIG_REF_NAME : string;
  attribute ORIG_REF_NAME of led_0_led : entity is "led";
end led_0_led;

architecture STRUCTURE of led_0_led is
  signal \^q\ : STD_LOGIC_VECTOR ( 7 downto 0 );
  signal \count[0]_i_2_n_0\ : STD_LOGIC;
  signal \count[3]_i_1_n_0\ : STD_LOGIC;
  signal \count[3]_i_3_n_0\ : STD_LOGIC;
  signal count_reg : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal ledtemp0 : STD_LOGIC;
  signal \ledtemp[0]_i_1_n_0\ : STD_LOGIC;
  signal \ledtemp[0]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[1]_i_1_n_0\ : STD_LOGIC;
  signal \ledtemp[1]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[1]_i_3_n_0\ : STD_LOGIC;
  signal \ledtemp[2]_i_1_n_0\ : STD_LOGIC;
  signal \ledtemp[2]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[3]_i_1_n_0\ : STD_LOGIC;
  signal \ledtemp[4]_i_1_n_0\ : STD_LOGIC;
  signal \ledtemp[5]_i_1_n_0\ : STD_LOGIC;
  signal \ledtemp[5]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[5]_i_3_n_0\ : STD_LOGIC;
  signal \ledtemp[6]_i_1_n_0\ : STD_LOGIC;
  signal \ledtemp[6]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[6]_i_3_n_0\ : STD_LOGIC;
  signal \ledtemp[6]_i_4_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_10_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_11_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_3_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_4_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_5_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_6_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_7_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_8_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_9_n_0\ : STD_LOGIC;
  signal \p_0_in__0\ : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal \^t1\ : STD_LOGIC_VECTOR ( 3 downto 0 );
  attribute SOFT_HLUTNM : string;
  attribute SOFT_HLUTNM of \ledtemp[0]_i_2\ : label is "soft_lutpair2";
  attribute SOFT_HLUTNM of \ledtemp[5]_i_2\ : label is "soft_lutpair1";
  attribute SOFT_HLUTNM of \ledtemp[6]_i_2\ : label is "soft_lutpair1";
  attribute SOFT_HLUTNM of \ledtemp[6]_i_3\ : label is "soft_lutpair0";
  attribute SOFT_HLUTNM of \ledtemp[7]_i_3\ : label is "soft_lutpair2";
  attribute SOFT_HLUTNM of \ledtemp[7]_i_6\ : label is "soft_lutpair0";
begin
  Q(7 downto 0) <= \^q\(7 downto 0);
  t1(3 downto 0) <= \^t1\(3 downto 0);
\count[0]_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"D714"
    )
        port map (
      I0 => count_reg(0),
      I1 => \^t1\(3),
      I2 => count_reg(3),
      I3 => \count[0]_i_2_n_0\,
      O => \p_0_in__0\(0)
    );
\count[0]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"2332333333332332"
    )
        port map (
      I0 => \^t1\(0),
      I1 => count_reg(0),
      I2 => \^t1\(2),
      I3 => count_reg(2),
      I4 => count_reg(1),
      I5 => \^t1\(1),
      O => \count[0]_i_2_n_0\
    );
\count[1]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00BEBE00"
    )
        port map (
      I0 => \count[3]_i_3_n_0\,
      I1 => count_reg(3),
      I2 => \^t1\(3),
      I3 => count_reg(1),
      I4 => count_reg(0),
      O => \p_0_in__0\(1)
    );
\count[2]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"00BEBE00BE00BE00"
    )
        port map (
      I0 => \count[3]_i_3_n_0\,
      I1 => count_reg(3),
      I2 => \^t1\(3),
      I3 => count_reg(2),
      I4 => count_reg(0),
      I5 => count_reg(1),
      O => \p_0_in__0\(2)
    );
\count[3]_i_1\: unisim.vcomponents.LUT3
    generic map(
      INIT => X"04"
    )
        port map (
      I0 => a(1),
      I1 => a(0),
      I2 => a(2),
      O => \count[3]_i_1_n_0\
    );
\count[3]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"328C8C8C8C8C8C8C"
    )
        port map (
      I0 => \count[3]_i_3_n_0\,
      I1 => count_reg(3),
      I2 => \^t1\(3),
      I3 => count_reg(1),
      I4 => count_reg(0),
      I5 => count_reg(2),
      O => \p_0_in__0\(3)
    );
\count[3]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"6FF6FFFFFFFF6FF6"
    )
        port map (
      I0 => \^t1\(2),
      I1 => count_reg(2),
      I2 => \^t1\(1),
      I3 => count_reg(1),
      I4 => count_reg(0),
      I5 => \^t1\(0),
      O => \count[3]_i_3_n_0\
    );
\count_reg[0]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => \count[3]_i_1_n_0\,
      D => \p_0_in__0\(0),
      Q => count_reg(0),
      R => '0'
    );
\count_reg[1]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => \count[3]_i_1_n_0\,
      D => \p_0_in__0\(1),
      Q => count_reg(1),
      R => '0'
    );
\count_reg[2]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => \count[3]_i_1_n_0\,
      D => \p_0_in__0\(2),
      Q => count_reg(2),
      R => '0'
    );
\count_reg[3]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => \count[3]_i_1_n_0\,
      D => \p_0_in__0\(3),
      Q => count_reg(3),
      R => '0'
    );
\ledtemp[0]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"88B8BBBB88B88888"
    )
        port map (
      I0 => \ledtemp[7]_i_5_n_0\,
      I1 => a(2),
      I2 => \ledtemp[0]_i_2_n_0\,
      I3 => a(0),
      I4 => a(1),
      I5 => \ledtemp[7]_i_7_n_0\,
      O => \ledtemp[0]_i_1_n_0\
    );
\ledtemp[0]_i_2\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"2"
    )
        port map (
      I0 => \^q\(1),
      I1 => \^q\(7),
      O => \ledtemp[0]_i_2_n_0\
    );
\ledtemp[1]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"B8BBB888"
    )
        port map (
      I0 => \ledtemp[6]_i_2_n_0\,
      I1 => a(2),
      I2 => \ledtemp[1]_i_2_n_0\,
      I3 => a(1),
      I4 => \ledtemp[7]_i_7_n_0\,
      O => \ledtemp[1]_i_1_n_0\
    );
\ledtemp[1]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"00000000BBB888B8"
    )
        port map (
      I0 => \^q\(0),
      I1 => a(0),
      I2 => \ledtemp[1]_i_3_n_0\,
      I3 => \^q\(6),
      I4 => \^q\(2),
      I5 => \^q\(7),
      O => \ledtemp[1]_i_2_n_0\
    );
\ledtemp[1]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"FFFF0000FFFF0001"
    )
        port map (
      I0 => \^q\(5),
      I1 => \^q\(4),
      I2 => \^q\(3),
      I3 => \^q\(0),
      I4 => \^q\(2),
      I5 => \^q\(1),
      O => \ledtemp[1]_i_3_n_0\
    );
\ledtemp[2]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"B8BBB888"
    )
        port map (
      I0 => \ledtemp[5]_i_2_n_0\,
      I1 => a(2),
      I2 => \ledtemp[2]_i_2_n_0\,
      I3 => a(1),
      I4 => \ledtemp[7]_i_7_n_0\,
      O => \ledtemp[2]_i_1_n_0\
    );
\ledtemp[2]_i_2\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"33B8"
    )
        port map (
      I0 => \^q\(1),
      I1 => a(0),
      I2 => \^q\(3),
      I3 => \^q\(7),
      O => \ledtemp[2]_i_2_n_0\
    );
\ledtemp[3]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000008A80"
    )
        port map (
      I0 => a(1),
      I1 => \^q\(2),
      I2 => a(0),
      I3 => \^q\(4),
      I4 => \^q\(7),
      I5 => a(2),
      O => \ledtemp[3]_i_1_n_0\
    );
\ledtemp[4]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000008A80"
    )
        port map (
      I0 => a(1),
      I1 => \^q\(3),
      I2 => a(0),
      I3 => \^q\(5),
      I4 => \^q\(7),
      I5 => a(2),
      O => \ledtemp[4]_i_1_n_0\
    );
\ledtemp[5]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"B8BBB888"
    )
        port map (
      I0 => \ledtemp[5]_i_2_n_0\,
      I1 => a(2),
      I2 => \ledtemp[5]_i_3_n_0\,
      I3 => a(1),
      I4 => \ledtemp[7]_i_7_n_0\,
      O => \ledtemp[5]_i_1_n_0\
    );
\ledtemp[5]_i_2\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"8888CBC8"
    )
        port map (
      I0 => a(0),
      I1 => a(1),
      I2 => \^q\(6),
      I3 => \ledtemp[7]_i_8_n_0\,
      I4 => \^q\(7),
      O => \ledtemp[5]_i_2_n_0\
    );
\ledtemp[5]_i_3\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"CCB8"
    )
        port map (
      I0 => \^q\(4),
      I1 => a(0),
      I2 => \^q\(6),
      I3 => \^q\(7),
      O => \ledtemp[5]_i_3_n_0\
    );
\ledtemp[6]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"B8BBB888"
    )
        port map (
      I0 => \ledtemp[6]_i_2_n_0\,
      I1 => a(2),
      I2 => \ledtemp[6]_i_3_n_0\,
      I3 => a(1),
      I4 => \ledtemp[7]_i_7_n_0\,
      O => \ledtemp[6]_i_1_n_0\
    );
\ledtemp[6]_i_2\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"44440300"
    )
        port map (
      I0 => a(0),
      I1 => a(1),
      I2 => \^q\(6),
      I3 => \ledtemp[7]_i_8_n_0\,
      I4 => \^q\(7),
      O => \ledtemp[6]_i_2_n_0\
    );
\ledtemp[6]_i_3\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"55100000"
    )
        port map (
      I0 => \^q\(7),
      I1 => \^q\(6),
      I2 => \ledtemp[6]_i_4_n_0\,
      I3 => \^q\(5),
      I4 => a(0),
      O => \ledtemp[6]_i_3_n_0\
    );
\ledtemp[6]_i_4\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00000001"
    )
        port map (
      I0 => \^q\(3),
      I1 => \^q\(1),
      I2 => \^q\(2),
      I3 => \^q\(0),
      I4 => \^q\(4),
      O => \ledtemp[6]_i_4_n_0\
    );
\ledtemp[7]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"EEE2FFFF"
    )
        port map (
      I0 => \ledtemp[7]_i_3_n_0\,
      I1 => a(1),
      I2 => a(0),
      I3 => \ledtemp[7]_i_4_n_0\,
      I4 => a(2),
      O => ledtemp0
    );
\ledtemp[7]_i_10\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000000004"
    )
        port map (
      I0 => \^q\(4),
      I1 => \^q\(0),
      I2 => \^q\(2),
      I3 => \^q\(1),
      I4 => \^q\(3),
      I5 => \^q\(5),
      O => \ledtemp[7]_i_10_n_0\
    );
\ledtemp[7]_i_11\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000041000041"
    )
        port map (
      I0 => \^q\(0),
      I1 => \^q\(6),
      I2 => \^q\(1),
      I3 => \^q\(5),
      I4 => \^q\(2),
      I5 => \^q\(3),
      O => \ledtemp[7]_i_11_n_0\
    );
\ledtemp[7]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"B888BBBBB8888888"
    )
        port map (
      I0 => \ledtemp[7]_i_5_n_0\,
      I1 => a(2),
      I2 => \ledtemp[7]_i_6_n_0\,
      I3 => a(0),
      I4 => a(1),
      I5 => \ledtemp[7]_i_7_n_0\,
      O => \ledtemp[7]_i_2_n_0\
    );
\ledtemp[7]_i_3\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"C00A"
    )
        port map (
      I0 => \ledtemp[7]_i_8_n_0\,
      I1 => \ledtemp[7]_i_9_n_0\,
      I2 => \^q\(7),
      I3 => \^q\(6),
      O => \ledtemp[7]_i_3_n_0\
    );
\ledtemp[7]_i_4\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0088008803BB0088"
    )
        port map (
      I0 => \ledtemp[7]_i_10_n_0\,
      I1 => \^q\(7),
      I2 => \^q\(5),
      I3 => \^q\(6),
      I4 => \ledtemp[7]_i_11_n_0\,
      I5 => \^q\(4),
      O => \ledtemp[7]_i_4_n_0\
    );
\ledtemp[7]_i_5\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"C0C0C0C0C0DFC0D0"
    )
        port map (
      I0 => \^q\(5),
      I1 => a(0),
      I2 => a(1),
      I3 => \^q\(6),
      I4 => \ledtemp[7]_i_8_n_0\,
      I5 => \^q\(7),
      O => \ledtemp[7]_i_5_n_0\
    );
\ledtemp[7]_i_6\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"2"
    )
        port map (
      I0 => \^q\(6),
      I1 => \^q\(7),
      O => \ledtemp[7]_i_6_n_0\
    );
\ledtemp[7]_i_7\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"F600"
    )
        port map (
      I0 => \^t1\(3),
      I1 => count_reg(3),
      I2 => \count[3]_i_3_n_0\,
      I3 => a(0),
      O => \ledtemp[7]_i_7_n_0\
    );
\ledtemp[7]_i_8\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000000001"
    )
        port map (
      I0 => \^q\(4),
      I1 => \^q\(0),
      I2 => \^q\(2),
      I3 => \^q\(1),
      I4 => \^q\(3),
      I5 => \^q\(5),
      O => \ledtemp[7]_i_8_n_0\
    );
\ledtemp[7]_i_9\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000400000000000"
    )
        port map (
      I0 => \^q\(4),
      I1 => \^q\(0),
      I2 => \^q\(2),
      I3 => \^q\(1),
      I4 => \^q\(3),
      I5 => \^q\(5),
      O => \ledtemp[7]_i_9_n_0\
    );
\ledtemp_reg[0]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => ledtemp0,
      D => \ledtemp[0]_i_1_n_0\,
      Q => \^q\(0),
      R => '0'
    );
\ledtemp_reg[1]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => ledtemp0,
      D => \ledtemp[1]_i_1_n_0\,
      Q => \^q\(1),
      R => '0'
    );
\ledtemp_reg[2]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => ledtemp0,
      D => \ledtemp[2]_i_1_n_0\,
      Q => \^q\(2),
      R => '0'
    );
\ledtemp_reg[3]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => ledtemp0,
      D => \ledtemp[3]_i_1_n_0\,
      Q => \^q\(3),
      R => '0'
    );
\ledtemp_reg[4]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => ledtemp0,
      D => \ledtemp[4]_i_1_n_0\,
      Q => \^q\(4),
      R => '0'
    );
\ledtemp_reg[5]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => ledtemp0,
      D => \ledtemp[5]_i_1_n_0\,
      Q => \^q\(5),
      R => '0'
    );
\ledtemp_reg[6]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => ledtemp0,
      D => \ledtemp[6]_i_1_n_0\,
      Q => \^q\(6),
      R => '0'
    );
\ledtemp_reg[7]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => ledtemp0,
      D => \ledtemp[7]_i_2_n_0\,
      Q => \^q\(7),
      R => '0'
    );
s: entity work.led_0_settime
     port map (
      Q(3 downto 0) => \^t1\(3 downto 0),
      down => down,
      switch => switch,
      up => up
    );
end STRUCTURE;
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity led_0 is
  port (
    clk : in STD_LOGIC;
    a : in STD_LOGIC_VECTOR ( 2 downto 0 );
    led : out STD_LOGIC_VECTOR ( 7 downto 0 );
    up : in STD_LOGIC;
    down : in STD_LOGIC;
    switch : in STD_LOGIC;
    t1 : out STD_LOGIC_VECTOR ( 3 downto 0 )
  );
  attribute NotValidForBitStream : boolean;
  attribute NotValidForBitStream of led_0 : entity is true;
  attribute CHECK_LICENSE_TYPE : string;
  attribute CHECK_LICENSE_TYPE of led_0 : entity is "led_0,led,{}";
  attribute DowngradeIPIdentifiedWarnings : string;
  attribute DowngradeIPIdentifiedWarnings of led_0 : entity is "yes";
  attribute IP_DEFINITION_SOURCE : string;
  attribute IP_DEFINITION_SOURCE of led_0 : entity is "package_project";
  attribute X_CORE_INFO : string;
  attribute X_CORE_INFO of led_0 : entity is "led,Vivado 2021.1";
end led_0;

architecture STRUCTURE of led_0 is
  attribute X_INTERFACE_INFO : string;
  attribute X_INTERFACE_INFO of clk : signal is "xilinx.com:signal:clock:1.0 clk CLK";
  attribute X_INTERFACE_PARAMETER : string;
  attribute X_INTERFACE_PARAMETER of clk : signal is "XIL_INTERFACENAME clk, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0";
begin
inst: entity work.led_0_led
     port map (
      Q(7 downto 0) => led(7 downto 0),
      a(2 downto 0) => a(2 downto 0),
      clk => clk,
      down => down,
      switch => switch,
      t1(3 downto 0) => t1(3 downto 0),
      up => up
    );
end STRUCTURE;
