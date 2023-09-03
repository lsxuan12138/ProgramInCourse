-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Wed Dec  1 17:29:14 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode funcsim
--               d:/VProject/Carlight/Carlight.gen/sources_1/ip/led_0_6/led_0_sim_netlist.vhdl
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
    D : out STD_LOGIC_VECTOR ( 5 downto 0 );
    a_0_sp_1 : out STD_LOGIC;
    \a[0]_0\ : out STD_LOGIC;
    \a[0]_1\ : out STD_LOGIC;
    \a[0]_2\ : out STD_LOGIC;
    switch : in STD_LOGIC;
    \ledtemp_reg[6]\ : in STD_LOGIC;
    a : in STD_LOGIC_VECTOR ( 2 downto 0 );
    \ledtemp_reg[6]_0\ : in STD_LOGIC;
    \ledtemp_reg[1]\ : in STD_LOGIC;
    \count_reg[2]\ : in STD_LOGIC;
    \count_reg[2]_0\ : in STD_LOGIC;
    \count_reg[2]_1\ : in STD_LOGIC;
    \count_reg[2]_2\ : in STD_LOGIC;
    \count_reg[0]\ : in STD_LOGIC;
    \count_reg[1]\ : in STD_LOGIC;
    \ledtemp_reg[0]\ : in STD_LOGIC;
    \ledtemp_reg[0]_0\ : in STD_LOGIC;
    \ledtemp_reg[2]\ : in STD_LOGIC;
    \ledtemp_reg[7]\ : in STD_LOGIC_VECTOR ( 0 to 0 );
    \ledtemp_reg[7]_0\ : in STD_LOGIC;
    up : in STD_LOGIC
  );
  attribute ORIG_REF_NAME : string;
  attribute ORIG_REF_NAME of led_0_settime : entity is "settime";
end led_0_settime;

architecture STRUCTURE of led_0_settime is
  signal \^q\ : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal a_0_sn_1 : STD_LOGIC;
  signal \count[0]_i_2_n_0\ : STD_LOGIC;
  signal \count[1]_i_3_n_0\ : STD_LOGIC;
  signal \count[2]_i_3_n_0\ : STD_LOGIC;
  signal \count[3]_i_2_n_0\ : STD_LOGIC;
  signal \count[3]_i_3_n_0\ : STD_LOGIC;
  signal \count[3]_i_4_n_0\ : STD_LOGIC;
  signal \count[3]_i_5_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_3_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_4_n_0\ : STD_LOGIC;
  signal p_0_in : STD_LOGIC;
  signal \p_0_in__0\ : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal s : STD_LOGIC;
  attribute SOFT_HLUTNM : string;
  attribute SOFT_HLUTNM of \c1[0]_i_1\ : label is "soft_lutpair2";
  attribute SOFT_HLUTNM of \c1[1]_i_1\ : label is "soft_lutpair2";
  attribute SOFT_HLUTNM of \c1[2]_i_1\ : label is "soft_lutpair0";
  attribute SOFT_HLUTNM of \c1[3]_i_1\ : label is "soft_lutpair0";
  attribute SOFT_HLUTNM of \count[3]_i_4\ : label is "soft_lutpair1";
  attribute SOFT_HLUTNM of \count[3]_i_5\ : label is "soft_lutpair1";
begin
  Q(3 downto 0) <= \^q\(3 downto 0);
  a_0_sp_1 <= a_0_sn_1;
\c1[0]_i_1\: unisim.vcomponents.LUT1
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => \^q\(0),
      O => \p_0_in__0\(0)
    );
\c1[1]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"6"
    )
        port map (
      I0 => \^q\(1),
      I1 => \^q\(0),
      O => \p_0_in__0\(1)
    );
\c1[2]_i_1\: unisim.vcomponents.LUT3
    generic map(
      INIT => X"6A"
    )
        port map (
      I0 => \^q\(2),
      I1 => \^q\(0),
      I2 => \^q\(1),
      O => \p_0_in__0\(2)
    );
\c1[3]_i_1\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"6AAA"
    )
        port map (
      I0 => \^q\(3),
      I1 => \^q\(0),
      I2 => \^q\(1),
      I3 => \^q\(2),
      O => \p_0_in__0\(3)
    );
\c1_reg[0]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => up,
      CE => s,
      D => \p_0_in__0\(0),
      Q => \^q\(0),
      R => '0'
    );
\c1_reg[1]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '1'
    )
        port map (
      C => up,
      CE => s,
      D => \p_0_in__0\(1),
      Q => \^q\(1),
      R => '0'
    );
\c1_reg[2]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => up,
      CE => s,
      D => \p_0_in__0\(2),
      Q => \^q\(2),
      R => '0'
    );
\c1_reg[3]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '1'
    )
        port map (
      C => up,
      CE => s,
      D => \p_0_in__0\(3),
      Q => \^q\(3),
      R => '0'
    );
\count[0]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"000000008A2A0208"
    )
        port map (
      I0 => a(0),
      I1 => \count_reg[2]\,
      I2 => \count_reg[0]\,
      I3 => \^q\(3),
      I4 => \count[0]_i_2_n_0\,
      I5 => a(2),
      O => \a[0]_2\
    );
\count[0]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"9F0F060F0F9F0F06"
    )
        port map (
      I0 => \count_reg[2]_1\,
      I1 => \^q\(2),
      I2 => \count_reg[0]\,
      I3 => \count_reg[2]_2\,
      I4 => \^q\(0),
      I5 => \^q\(1),
      O => \count[0]_i_2_n_0\
    );
\count[1]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"00000000A8A22080"
    )
        port map (
      I0 => a(0),
      I1 => \count_reg[2]\,
      I2 => \count_reg[1]\,
      I3 => \^q\(3),
      I4 => \count[1]_i_3_n_0\,
      I5 => a(2),
      O => \a[0]_1\
    );
\count[1]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"9000FFFFF6FF0000"
    )
        port map (
      I0 => \count_reg[2]_1\,
      I1 => \^q\(2),
      I2 => \^q\(1),
      I3 => \^q\(0),
      I4 => \count_reg[0]\,
      I5 => \count_reg[2]_2\,
      O => \count[1]_i_3_n_0\
    );
\count[2]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"00000000A8A22080"
    )
        port map (
      I0 => a(0),
      I1 => \count_reg[2]\,
      I2 => \count_reg[2]_0\,
      I3 => \^q\(3),
      I4 => \count[2]_i_3_n_0\,
      I5 => a(2),
      O => a_0_sn_1
    );
\count[2]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"CA5A5A5AAAAAAAAA"
    )
        port map (
      I0 => \count_reg[2]_1\,
      I1 => \^q\(2),
      I2 => \count_reg[2]_2\,
      I3 => \^q\(0),
      I4 => \^q\(1),
      I5 => \count_reg[0]\,
      O => \count[2]_i_3_n_0\
    );
\count[3]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00008A80"
    )
        port map (
      I0 => a(0),
      I1 => \count[3]_i_2_n_0\,
      I2 => \count_reg[2]\,
      I3 => \count[3]_i_3_n_0\,
      I4 => a(2),
      O => \a[0]_0\
    );
\count[3]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"80FFFFFFFFFFFFFF"
    )
        port map (
      I0 => \^q\(2),
      I1 => \count[3]_i_4_n_0\,
      I2 => \^q\(3),
      I3 => \count_reg[2]_2\,
      I4 => \count_reg[0]\,
      I5 => \count_reg[2]_1\,
      O => \count[3]_i_2_n_0\
    );
\count[3]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"C0C080C000000000"
    )
        port map (
      I0 => \^q\(3),
      I1 => \count_reg[0]\,
      I2 => \count_reg[2]_2\,
      I3 => \^q\(2),
      I4 => \count[3]_i_5_n_0\,
      I5 => \count_reg[2]_1\,
      O => \count[3]_i_3_n_0\
    );
\count[3]_i_4\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"8"
    )
        port map (
      I0 => \^q\(1),
      I1 => \^q\(0),
      O => \count[3]_i_4_n_0\
    );
\count[3]_i_5\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"7"
    )
        port map (
      I0 => \^q\(0),
      I1 => \^q\(1),
      O => \count[3]_i_5_n_0\
    );
\ledtemp[0]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"CCB8FFFFCCB80000"
    )
        port map (
      I0 => \ledtemp_reg[0]\,
      I1 => a(2),
      I2 => \ledtemp_reg[0]_0\,
      I3 => a(0),
      I4 => a(1),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(0)
    );
\ledtemp[1]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"2320FFFF23200000"
    )
        port map (
      I0 => \ledtemp_reg[6]\,
      I1 => a(0),
      I2 => a(2),
      I3 => \ledtemp_reg[1]\,
      I4 => a(1),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(1)
    );
\ledtemp[2]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"88B8FFFF88B80000"
    )
        port map (
      I0 => \ledtemp_reg[2]\,
      I1 => a(2),
      I2 => \ledtemp_reg[0]\,
      I3 => a(0),
      I4 => a(1),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(2)
    );
\ledtemp[5]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"B888FFFFB8880000"
    )
        port map (
      I0 => \ledtemp_reg[2]\,
      I1 => a(2),
      I2 => \ledtemp_reg[0]\,
      I3 => a(0),
      I4 => a(1),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(3)
    );
\ledtemp[6]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"2C20FFFF2C200000"
    )
        port map (
      I0 => \ledtemp_reg[6]\,
      I1 => a(0),
      I2 => a(2),
      I3 => \ledtemp_reg[6]_0\,
      I4 => a(1),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(4)
    );
\ledtemp[7]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"EA20FFFFEA200000"
    )
        port map (
      I0 => a(2),
      I1 => \ledtemp_reg[7]\(0),
      I2 => \ledtemp_reg[7]_0\,
      I3 => a(0),
      I4 => a(1),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(5)
    );
\ledtemp[7]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"BB8BBBB888888888"
    )
        port map (
      I0 => \ledtemp_reg[0]\,
      I1 => a(2),
      I2 => \^q\(3),
      I3 => \ledtemp[7]_i_4_n_0\,
      I4 => \count_reg[2]\,
      I5 => a(0),
      O => \ledtemp[7]_i_3_n_0\
    );
\ledtemp[7]_i_4\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"7FDFF7FDBFEFFBFE"
    )
        port map (
      I0 => \count_reg[2]_1\,
      I1 => \^q\(1),
      I2 => \^q\(0),
      I3 => \count_reg[2]_2\,
      I4 => \count_reg[0]\,
      I5 => \^q\(2),
      O => \ledtemp[7]_i_4_n_0\
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
    Q : out STD_LOGIC_VECTOR ( 5 downto 0 );
    a : in STD_LOGIC_VECTOR ( 2 downto 0 );
    clk : in STD_LOGIC;
    switch : in STD_LOGIC;
    up : in STD_LOGIC
  );
  attribute ORIG_REF_NAME : string;
  attribute ORIG_REF_NAME of led_0_led : entity is "led";
end led_0_led;

architecture STRUCTURE of led_0_led is
  signal \^q\ : STD_LOGIC_VECTOR ( 5 downto 0 );
  signal \count[1]_i_2_n_0\ : STD_LOGIC;
  signal \count[2]_i_2_n_0\ : STD_LOGIC;
  signal \count_reg_n_0_[0]\ : STD_LOGIC;
  signal \count_reg_n_0_[1]\ : STD_LOGIC;
  signal \count_reg_n_0_[2]\ : STD_LOGIC;
  signal \count_reg_n_0_[3]\ : STD_LOGIC;
  signal \ledtemp[0]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[1]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[5]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[5]_i_3_n_0\ : STD_LOGIC;
  signal \ledtemp[5]_i_4_n_0\ : STD_LOGIC;
  signal \ledtemp[6]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[6]_i_3_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_2_n_0\ : STD_LOGIC;
  signal s_n_10 : STD_LOGIC;
  signal s_n_11 : STD_LOGIC;
  signal s_n_12 : STD_LOGIC;
  signal s_n_13 : STD_LOGIC;
  signal s_n_4 : STD_LOGIC;
  signal s_n_5 : STD_LOGIC;
  signal s_n_6 : STD_LOGIC;
  signal s_n_7 : STD_LOGIC;
  signal s_n_8 : STD_LOGIC;
  signal s_n_9 : STD_LOGIC;
  attribute SOFT_HLUTNM : string;
  attribute SOFT_HLUTNM of \count[1]_i_2\ : label is "soft_lutpair4";
  attribute SOFT_HLUTNM of \count[2]_i_2\ : label is "soft_lutpair4";
  attribute SOFT_HLUTNM of \ledtemp[5]_i_4\ : label is "soft_lutpair3";
  attribute SOFT_HLUTNM of \ledtemp[7]_i_2\ : label is "soft_lutpair3";
begin
  Q(5 downto 0) <= \^q\(5 downto 0);
\count[1]_i_2\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"6"
    )
        port map (
      I0 => \count_reg_n_0_[0]\,
      I1 => \count_reg_n_0_[1]\,
      O => \count[1]_i_2_n_0\
    );
\count[2]_i_2\: unisim.vcomponents.LUT3
    generic map(
      INIT => X"6A"
    )
        port map (
      I0 => \count_reg_n_0_[2]\,
      I1 => \count_reg_n_0_[0]\,
      I2 => \count_reg_n_0_[1]\,
      O => \count[2]_i_2_n_0\
    );
\count_reg[0]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_13,
      Q => \count_reg_n_0_[0]\,
      R => a(1)
    );
\count_reg[1]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_12,
      Q => \count_reg_n_0_[1]\,
      R => a(1)
    );
\count_reg[2]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_10,
      Q => \count_reg_n_0_[2]\,
      R => a(1)
    );
\count_reg[3]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_11,
      Q => \count_reg_n_0_[3]\,
      R => a(1)
    );
\ledtemp[0]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000000010"
    )
        port map (
      I0 => \^q\(5),
      I1 => \^q\(2),
      I2 => \^q\(1),
      I3 => \^q\(0),
      I4 => \^q\(3),
      I5 => \^q\(4),
      O => \ledtemp[0]_i_2_n_0\
    );
\ledtemp[1]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000000004"
    )
        port map (
      I0 => \^q\(5),
      I1 => \^q\(2),
      I2 => \^q\(0),
      I3 => \^q\(1),
      I4 => \^q\(3),
      I5 => \^q\(4),
      O => \ledtemp[1]_i_2_n_0\
    );
\ledtemp[5]_i_2\: unisim.vcomponents.LUT3
    generic map(
      INIT => X"F8"
    )
        port map (
      I0 => \^q\(4),
      I1 => \ledtemp[5]_i_4_n_0\,
      I2 => a(0),
      O => \ledtemp[5]_i_2_n_0\
    );
\ledtemp[5]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000000001"
    )
        port map (
      I0 => \^q\(5),
      I1 => \^q\(2),
      I2 => \^q\(0),
      I3 => \^q\(1),
      I4 => \^q\(3),
      I5 => \^q\(4),
      O => \ledtemp[5]_i_3_n_0\
    );
\ledtemp[5]_i_4\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00000010"
    )
        port map (
      I0 => \^q\(3),
      I1 => \^q\(0),
      I2 => \^q\(1),
      I3 => \^q\(2),
      I4 => \^q\(5),
      O => \ledtemp[5]_i_4_n_0\
    );
\ledtemp[6]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000000020"
    )
        port map (
      I0 => \^q\(5),
      I1 => \^q\(2),
      I2 => \^q\(0),
      I3 => \^q\(1),
      I4 => \^q\(3),
      I5 => \^q\(4),
      O => \ledtemp[6]_i_2_n_0\
    );
\ledtemp[6]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000010000"
    )
        port map (
      I0 => \^q\(5),
      I1 => \^q\(2),
      I2 => \^q\(0),
      I3 => \^q\(1),
      I4 => \^q\(3),
      I5 => \^q\(4),
      O => \ledtemp[6]_i_3_n_0\
    );
\ledtemp[7]_i_2\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00000001"
    )
        port map (
      I0 => \^q\(3),
      I1 => \^q\(1),
      I2 => \^q\(0),
      I3 => \^q\(2),
      I4 => \^q\(5),
      O => \ledtemp[7]_i_2_n_0\
    );
\ledtemp_reg[0]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_9,
      Q => \^q\(0),
      R => '0'
    );
\ledtemp_reg[1]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_8,
      Q => \^q\(1),
      R => '0'
    );
\ledtemp_reg[2]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_7,
      Q => \^q\(2),
      R => '0'
    );
\ledtemp_reg[5]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_6,
      Q => \^q\(3),
      R => '0'
    );
\ledtemp_reg[6]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_5,
      Q => \^q\(4),
      R => '0'
    );
\ledtemp_reg[7]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_4,
      Q => \^q\(5),
      R => '0'
    );
s: entity work.led_0_settime
     port map (
      D(5) => s_n_4,
      D(4) => s_n_5,
      D(3) => s_n_6,
      D(2) => s_n_7,
      D(1) => s_n_8,
      D(0) => s_n_9,
      Q(3 downto 0) => t1(3 downto 0),
      a(2 downto 0) => a(2 downto 0),
      \a[0]_0\ => s_n_11,
      \a[0]_1\ => s_n_12,
      \a[0]_2\ => s_n_13,
      a_0_sp_1 => s_n_10,
      \count_reg[0]\ => \count_reg_n_0_[0]\,
      \count_reg[1]\ => \count[1]_i_2_n_0\,
      \count_reg[2]\ => \count_reg_n_0_[3]\,
      \count_reg[2]_0\ => \count[2]_i_2_n_0\,
      \count_reg[2]_1\ => \count_reg_n_0_[2]\,
      \count_reg[2]_2\ => \count_reg_n_0_[1]\,
      \ledtemp_reg[0]\ => \ledtemp[5]_i_3_n_0\,
      \ledtemp_reg[0]_0\ => \ledtemp[0]_i_2_n_0\,
      \ledtemp_reg[1]\ => \ledtemp[1]_i_2_n_0\,
      \ledtemp_reg[2]\ => \ledtemp[5]_i_2_n_0\,
      \ledtemp_reg[6]\ => \ledtemp[6]_i_2_n_0\,
      \ledtemp_reg[6]_0\ => \ledtemp[6]_i_3_n_0\,
      \ledtemp_reg[7]\(0) => \^q\(4),
      \ledtemp_reg[7]_0\ => \ledtemp[7]_i_2_n_0\,
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
  signal \<const0>\ : STD_LOGIC;
  signal \^led\ : STD_LOGIC_VECTOR ( 7 downto 0 );
  attribute X_INTERFACE_INFO : string;
  attribute X_INTERFACE_INFO of clk : signal is "xilinx.com:signal:clock:1.0 clk CLK";
  attribute X_INTERFACE_PARAMETER : string;
  attribute X_INTERFACE_PARAMETER of clk : signal is "XIL_INTERFACENAME clk, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0";
begin
  led(7 downto 5) <= \^led\(7 downto 5);
  led(4) <= \<const0>\;
  led(3) <= \<const0>\;
  led(2 downto 0) <= \^led\(2 downto 0);
GND: unisim.vcomponents.GND
     port map (
      G => \<const0>\
    );
inst: entity work.led_0_led
     port map (
      Q(5 downto 3) => \^led\(7 downto 5),
      Q(2 downto 0) => \^led\(2 downto 0),
      a(2 downto 0) => a(2 downto 0),
      clk => clk,
      switch => switch,
      t1(3 downto 0) => t1(3 downto 0),
      up => up
    );
end STRUCTURE;
