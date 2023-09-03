-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Wed Dec  1 16:56:10 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode funcsim -rename_top decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix -prefix
--               decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_ led_0_sim_netlist.vhdl
-- Design      : led_0
-- Purpose     : This VHDL netlist is a functional simulation representation of the design and should not be modified or
--               synthesized. This netlist cannot be used for SDF annotated simulation.
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_settime is
  port (
    Q : out STD_LOGIC_VECTOR ( 3 downto 0 );
    a_0_sp_1 : out STD_LOGIC;
    \a[0]_0\ : out STD_LOGIC;
    \a[0]_1\ : out STD_LOGIC;
    \a[0]_2\ : out STD_LOGIC;
    D : out STD_LOGIC_VECTOR ( 5 downto 0 );
    switch : in STD_LOGIC;
    a : in STD_LOGIC_VECTOR ( 2 downto 0 );
    \count_reg[2]\ : in STD_LOGIC;
    \count_reg[2]_0\ : in STD_LOGIC;
    \count_reg[2]_1\ : in STD_LOGIC;
    \count_reg[2]_2\ : in STD_LOGIC;
    \count_reg[0]\ : in STD_LOGIC;
    \count_reg[1]\ : in STD_LOGIC;
    \ledtemp_reg[0]\ : in STD_LOGIC;
    \ledtemp_reg[0]_0\ : in STD_LOGIC;
    \ledtemp_reg[1]\ : in STD_LOGIC;
    \ledtemp_reg[1]_0\ : in STD_LOGIC;
    \ledtemp_reg[2]\ : in STD_LOGIC;
    \ledtemp_reg[2]_0\ : in STD_LOGIC;
    \ledtemp_reg[5]\ : in STD_LOGIC;
    \ledtemp_reg[6]\ : in STD_LOGIC;
    up : in STD_LOGIC
  );
end decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_settime;

architecture STRUCTURE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_settime is
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
  signal \ledtemp[7]_i_5_n_0\ : STD_LOGIC;
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
      INIT => X"FFA0CFCFFFA0C0C0"
    )
        port map (
      I0 => a(0),
      I1 => \ledtemp_reg[0]\,
      I2 => a(1),
      I3 => \ledtemp_reg[0]_0\,
      I4 => a(2),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(0)
    );
\ledtemp[1]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"AFA0CFCFAFA0C0C0"
    )
        port map (
      I0 => \ledtemp_reg[1]\,
      I1 => \ledtemp_reg[1]_0\,
      I2 => a(1),
      I3 => \ledtemp_reg[0]_0\,
      I4 => a(2),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(1)
    );
\ledtemp[2]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"AFA0CFCFAFA0C0C0"
    )
        port map (
      I0 => \ledtemp_reg[2]\,
      I1 => \ledtemp_reg[2]_0\,
      I2 => a(1),
      I3 => \ledtemp_reg[0]_0\,
      I4 => a(2),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(2)
    );
\ledtemp[5]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"AFA0CFCFAFA0C0C0"
    )
        port map (
      I0 => \ledtemp_reg[2]\,
      I1 => \ledtemp_reg[5]\,
      I2 => a(1),
      I3 => \ledtemp_reg[0]_0\,
      I4 => a(2),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(3)
    );
\ledtemp[6]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"AFA0CFCFAFA0C0C0"
    )
        port map (
      I0 => \ledtemp_reg[1]\,
      I1 => \ledtemp_reg[6]\,
      I2 => a(1),
      I3 => \ledtemp_reg[0]_0\,
      I4 => a(2),
      I5 => \ledtemp[7]_i_3_n_0\,
      O => D(4)
    );
\ledtemp[7]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"F833F800"
    )
        port map (
      I0 => a(0),
      I1 => a(1),
      I2 => \ledtemp_reg[0]_0\,
      I3 => a(2),
      I4 => \ledtemp[7]_i_3_n_0\,
      O => D(5)
    );
\ledtemp[7]_i_3\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"DE00"
    )
        port map (
      I0 => \^q\(3),
      I1 => \ledtemp[7]_i_5_n_0\,
      I2 => \count_reg[2]\,
      I3 => a(0),
      O => \ledtemp[7]_i_3_n_0\
    );
\ledtemp[7]_i_5\: unisim.vcomponents.LUT6
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
      O => \ledtemp[7]_i_5_n_0\
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
entity decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_led is
  port (
    t1 : out STD_LOGIC_VECTOR ( 3 downto 0 );
    Q : out STD_LOGIC_VECTOR ( 5 downto 0 );
    \ledtemp_reg[4]_0\ : out STD_LOGIC;
    \ledtemp_reg[3]_0\ : out STD_LOGIC;
    a : in STD_LOGIC_VECTOR ( 2 downto 0 );
    clk : in STD_LOGIC;
    switch : in STD_LOGIC;
    up : in STD_LOGIC
  );
end decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_led;

architecture STRUCTURE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_led is
  signal \^q\ : STD_LOGIC_VECTOR ( 5 downto 0 );
  signal \count[1]_i_2_n_0\ : STD_LOGIC;
  signal \count[2]_i_2_n_0\ : STD_LOGIC;
  signal \count_reg_n_0_[0]\ : STD_LOGIC;
  signal \count_reg_n_0_[1]\ : STD_LOGIC;
  signal \count_reg_n_0_[2]\ : STD_LOGIC;
  signal \count_reg_n_0_[3]\ : STD_LOGIC;
  signal \ledtemp[0]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[1]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[1]_i_3_n_0\ : STD_LOGIC;
  signal \ledtemp[2]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[3]_i_1_n_0\ : STD_LOGIC;
  signal \ledtemp[3]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[4]_i_1_n_0\ : STD_LOGIC;
  signal \ledtemp[4]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[5]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[5]_i_3_n_0\ : STD_LOGIC;
  signal \ledtemp[6]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[6]_i_3_n_0\ : STD_LOGIC;
  signal \ledtemp[6]_i_4_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_2_n_0\ : STD_LOGIC;
  signal \ledtemp[7]_i_4_n_0\ : STD_LOGIC;
  signal \^ledtemp_reg[3]_0\ : STD_LOGIC;
  signal \^ledtemp_reg[4]_0\ : STD_LOGIC;
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
  attribute SOFT_HLUTNM of \count[1]_i_2\ : label is "soft_lutpair5";
  attribute SOFT_HLUTNM of \count[2]_i_2\ : label is "soft_lutpair5";
  attribute SOFT_HLUTNM of \ledtemp[3]_i_1\ : label is "soft_lutpair6";
  attribute SOFT_HLUTNM of \ledtemp[4]_i_1\ : label is "soft_lutpair6";
  attribute SOFT_HLUTNM of \ledtemp[5]_i_3\ : label is "soft_lutpair3";
  attribute SOFT_HLUTNM of \ledtemp[6]_i_3\ : label is "soft_lutpair3";
  attribute SOFT_HLUTNM of \ledtemp[6]_i_4\ : label is "soft_lutpair4";
  attribute SOFT_HLUTNM of \ledtemp[7]_i_4\ : label is "soft_lutpair4";
begin
  Q(5 downto 0) <= \^q\(5 downto 0);
  \ledtemp_reg[3]_0\ <= \^ledtemp_reg[3]_0\;
  \ledtemp_reg[4]_0\ <= \^ledtemp_reg[4]_0\;
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
      D => s_n_7,
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
      D => s_n_6,
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
      D => s_n_4,
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
      D => s_n_5,
      Q => \count_reg_n_0_[3]\,
      R => a(1)
    );
\ledtemp[0]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000001000"
    )
        port map (
      I0 => \^q\(4),
      I1 => \^q\(5),
      I2 => \ledtemp[7]_i_4_n_0\,
      I3 => \^q\(1),
      I4 => \^q\(3),
      I5 => a(0),
      O => \ledtemp[0]_i_2_n_0\
    );
\ledtemp[1]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0004FFFF00040000"
    )
        port map (
      I0 => \^q\(3),
      I1 => \^q\(0),
      I2 => \^q\(5),
      I3 => \^q\(4),
      I4 => a(0),
      I5 => \ledtemp[1]_i_3_n_0\,
      O => \ledtemp[1]_i_2_n_0\
    );
\ledtemp[1]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"FFFFFFFFFFFFFFFE"
    )
        port map (
      I0 => \^q\(3),
      I1 => \^ledtemp_reg[4]_0\,
      I2 => \^ledtemp_reg[3]_0\,
      I3 => \^q\(2),
      I4 => \^q\(5),
      I5 => \^q\(4),
      O => \ledtemp[1]_i_3_n_0\
    );
\ledtemp[2]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000000B08"
    )
        port map (
      I0 => \^q\(1),
      I1 => a(0),
      I2 => \^q\(3),
      I3 => \ledtemp[6]_i_4_n_0\,
      I4 => \^q\(5),
      I5 => \^q\(4),
      O => \ledtemp[2]_i_2_n_0\
    );
\ledtemp[3]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"8"
    )
        port map (
      I0 => \ledtemp[3]_i_2_n_0\,
      I1 => a(1),
      O => \ledtemp[3]_i_1_n_0\
    );
\ledtemp[3]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000000020"
    )
        port map (
      I0 => a(0),
      I1 => \^q\(3),
      I2 => \^q\(2),
      I3 => \^q\(5),
      I4 => \^q\(4),
      I5 => a(2),
      O => \ledtemp[3]_i_2_n_0\
    );
\ledtemp[4]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"8"
    )
        port map (
      I0 => \ledtemp[4]_i_2_n_0\,
      I1 => a(1),
      O => \ledtemp[4]_i_1_n_0\
    );
\ledtemp[4]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000000020"
    )
        port map (
      I0 => a(0),
      I1 => \^q\(3),
      I2 => \^ledtemp_reg[3]_0\,
      I3 => \^q\(5),
      I4 => \^q\(4),
      I5 => a(2),
      O => \ledtemp[4]_i_2_n_0\
    );
\ledtemp[5]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"FFFFFFFF00002000"
    )
        port map (
      I0 => \^q\(4),
      I1 => \^q\(5),
      I2 => \ledtemp[7]_i_4_n_0\,
      I3 => \^q\(1),
      I4 => \^q\(3),
      I5 => a(0),
      O => \ledtemp[5]_i_2_n_0\
    );
\ledtemp[5]_i_3\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00100000"
    )
        port map (
      I0 => \^q\(4),
      I1 => \^q\(5),
      I2 => \^ledtemp_reg[4]_0\,
      I3 => \^q\(3),
      I4 => a(0),
      O => \ledtemp[5]_i_3_n_0\
    );
\ledtemp[6]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000000000000040"
    )
        port map (
      I0 => \^q\(4),
      I1 => \^q\(5),
      I2 => \ledtemp[6]_i_4_n_0\,
      I3 => \^q\(1),
      I4 => \^q\(3),
      I5 => a(0),
      O => \ledtemp[6]_i_2_n_0\
    );
\ledtemp[6]_i_3\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"FE00"
    )
        port map (
      I0 => \^q\(4),
      I1 => \^q\(5),
      I2 => \^q\(3),
      I3 => a(0),
      O => \ledtemp[6]_i_3_n_0\
    );
\ledtemp[6]_i_4\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"0004"
    )
        port map (
      I0 => \^q\(2),
      I1 => \^q\(0),
      I2 => \^ledtemp_reg[3]_0\,
      I3 => \^ledtemp_reg[4]_0\,
      O => \ledtemp[6]_i_4_n_0\
    );
\ledtemp[7]_i_2\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00000010"
    )
        port map (
      I0 => \^q\(3),
      I1 => \^q\(1),
      I2 => \ledtemp[7]_i_4_n_0\,
      I3 => \^q\(5),
      I4 => \^q\(4),
      O => \ledtemp[7]_i_2_n_0\
    );
\ledtemp[7]_i_4\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"0001"
    )
        port map (
      I0 => \^q\(2),
      I1 => \^q\(0),
      I2 => \^ledtemp_reg[3]_0\,
      I3 => \^ledtemp_reg[4]_0\,
      O => \ledtemp[7]_i_4_n_0\
    );
\ledtemp_reg[0]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_13,
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
      D => s_n_12,
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
      D => s_n_11,
      Q => \^q\(2),
      R => '0'
    );
\ledtemp_reg[3]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => \ledtemp[3]_i_1_n_0\,
      Q => \^ledtemp_reg[3]_0\,
      R => '0'
    );
\ledtemp_reg[4]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => \ledtemp[4]_i_1_n_0\,
      Q => \^ledtemp_reg[4]_0\,
      R => '0'
    );
\ledtemp_reg[5]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => s_n_10,
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
      D => s_n_9,
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
      D => s_n_8,
      Q => \^q\(5),
      R => '0'
    );
s: entity work.decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_settime
     port map (
      D(5) => s_n_8,
      D(4) => s_n_9,
      D(3) => s_n_10,
      D(2) => s_n_11,
      D(1) => s_n_12,
      D(0) => s_n_13,
      Q(3 downto 0) => t1(3 downto 0),
      a(2 downto 0) => a(2 downto 0),
      \a[0]_0\ => s_n_5,
      \a[0]_1\ => s_n_6,
      \a[0]_2\ => s_n_7,
      a_0_sp_1 => s_n_4,
      \count_reg[0]\ => \count_reg_n_0_[0]\,
      \count_reg[1]\ => \count[1]_i_2_n_0\,
      \count_reg[2]\ => \count_reg_n_0_[3]\,
      \count_reg[2]_0\ => \count[2]_i_2_n_0\,
      \count_reg[2]_1\ => \count_reg_n_0_[2]\,
      \count_reg[2]_2\ => \count_reg_n_0_[1]\,
      \ledtemp_reg[0]\ => \ledtemp[0]_i_2_n_0\,
      \ledtemp_reg[0]_0\ => \ledtemp[7]_i_2_n_0\,
      \ledtemp_reg[1]\ => \ledtemp[6]_i_2_n_0\,
      \ledtemp_reg[1]_0\ => \ledtemp[1]_i_2_n_0\,
      \ledtemp_reg[2]\ => \ledtemp[5]_i_2_n_0\,
      \ledtemp_reg[2]_0\ => \ledtemp[2]_i_2_n_0\,
      \ledtemp_reg[5]\ => \ledtemp[5]_i_3_n_0\,
      \ledtemp_reg[6]\ => \ledtemp[6]_i_3_n_0\,
      switch => switch,
      up => up
    );
end STRUCTURE;
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix is
  port (
    clk : in STD_LOGIC;
    a : in STD_LOGIC_VECTOR ( 2 downto 0 );
    led : out STD_LOGIC_VECTOR ( 7 downto 0 );
    up : in STD_LOGIC;
    switch : in STD_LOGIC;
    t1 : out STD_LOGIC_VECTOR ( 3 downto 0 )
  );
  attribute NotValidForBitStream : boolean;
  attribute NotValidForBitStream of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is true;
  attribute CHECK_LICENSE_TYPE : string;
  attribute CHECK_LICENSE_TYPE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "led_0,led,{}";
  attribute DowngradeIPIdentifiedWarnings : string;
  attribute DowngradeIPIdentifiedWarnings of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "yes";
  attribute IP_DEFINITION_SOURCE : string;
  attribute IP_DEFINITION_SOURCE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "package_project";
  attribute X_CORE_INFO : string;
  attribute X_CORE_INFO of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix : entity is "led,Vivado 2021.1";
end decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix;

architecture STRUCTURE of decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix is
  attribute X_INTERFACE_INFO : string;
  attribute X_INTERFACE_INFO of clk : signal is "xilinx.com:signal:clock:1.0 clk CLK";
  attribute X_INTERFACE_PARAMETER : string;
  attribute X_INTERFACE_PARAMETER of clk : signal is "XIL_INTERFACENAME clk, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0";
begin
inst: entity work.decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_led
     port map (
      Q(5 downto 3) => led(7 downto 5),
      Q(2 downto 0) => led(2 downto 0),
      a(2 downto 0) => a(2 downto 0),
      clk => clk,
      \ledtemp_reg[3]_0\ => led(3),
      \ledtemp_reg[4]_0\ => led(4),
      switch => switch,
      t1(3 downto 0) => t1(3 downto 0),
      up => up
    );
end STRUCTURE;
