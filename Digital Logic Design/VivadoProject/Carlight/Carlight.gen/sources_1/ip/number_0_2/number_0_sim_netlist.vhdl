-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Wed Dec  1 16:30:12 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode funcsim
--               d:/VProject/Carlight/Carlight.gen/sources_1/ip/number_0_2/number_0_sim_netlist.vhdl
-- Design      : number_0
-- Purpose     : This VHDL netlist is a functional simulation representation of the design and should not be modified or
--               synthesized. This netlist cannot be used for SDF annotated simulation.
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity number_0_number is
  port (
    Q : out STD_LOGIC_VECTOR ( 3 downto 0 );
    dm1 : out STD_LOGIC_VECTOR ( 7 downto 0 );
    t : in STD_LOGIC_VECTOR ( 3 downto 0 );
    a : in STD_LOGIC_VECTOR ( 2 downto 0 );
    clk : in STD_LOGIC;
    s : in STD_LOGIC
  );
  attribute ORIG_REF_NAME : string;
  attribute ORIG_REF_NAME of number_0_number : entity is "number";
end number_0_number;

architecture STRUCTURE of number_0_number is
  signal \^q\ : STD_LOGIC_VECTOR ( 3 downto 0 );
  signal data0 : STD_LOGIC_VECTOR ( 14 downto 1 );
  signal disp_bit : STD_LOGIC_VECTOR ( 1 downto 0 );
  signal \disp_bit[0]_i_1_n_0\ : STD_LOGIC;
  signal \disp_bit[1]_i_1_n_0\ : STD_LOGIC;
  signal div_cnt : STD_LOGIC_VECTOR ( 14 downto 0 );
  signal \div_cnt0_carry__0_n_0\ : STD_LOGIC;
  signal \div_cnt0_carry__0_n_1\ : STD_LOGIC;
  signal \div_cnt0_carry__0_n_2\ : STD_LOGIC;
  signal \div_cnt0_carry__0_n_3\ : STD_LOGIC;
  signal \div_cnt0_carry__1_n_0\ : STD_LOGIC;
  signal \div_cnt0_carry__1_n_1\ : STD_LOGIC;
  signal \div_cnt0_carry__1_n_2\ : STD_LOGIC;
  signal \div_cnt0_carry__1_n_3\ : STD_LOGIC;
  signal \div_cnt0_carry__2_n_3\ : STD_LOGIC;
  signal div_cnt0_carry_n_0 : STD_LOGIC;
  signal div_cnt0_carry_n_1 : STD_LOGIC;
  signal div_cnt0_carry_n_2 : STD_LOGIC;
  signal div_cnt0_carry_n_3 : STD_LOGIC;
  signal \div_cnt[0]_i_2_n_0\ : STD_LOGIC;
  signal \div_cnt[0]_i_3_n_0\ : STD_LOGIC;
  signal \div_cnt[0]_i_4_n_0\ : STD_LOGIC;
  signal div_cnt_0 : STD_LOGIC_VECTOR ( 0 to 0 );
  signal divclk : STD_LOGIC;
  signal divclk_1 : STD_LOGIC;
  signal divclk_i_1_n_0 : STD_LOGIC;
  signal \dm1_reg[0]_i_1_n_0\ : STD_LOGIC;
  signal \dm1_reg[1]_i_1_n_0\ : STD_LOGIC;
  signal \dm1_reg[1]_i_2_n_0\ : STD_LOGIC;
  signal \dm1_reg[1]_i_3_n_0\ : STD_LOGIC;
  signal \dm1_reg[2]_i_1_n_0\ : STD_LOGIC;
  signal \dm1_reg[2]_i_2_n_0\ : STD_LOGIC;
  signal \dm1_reg[2]_i_3_n_0\ : STD_LOGIC;
  signal \dm1_reg[3]_i_1_n_0\ : STD_LOGIC;
  signal \dm1_reg[3]_i_2_n_0\ : STD_LOGIC;
  signal \dm1_reg[3]_i_3_n_0\ : STD_LOGIC;
  signal \dm1_reg[3]_i_4_n_0\ : STD_LOGIC;
  signal \dm1_reg[4]_i_1_n_0\ : STD_LOGIC;
  signal \dm1_reg[4]_i_2_n_0\ : STD_LOGIC;
  signal \dm1_reg[4]_i_3_n_0\ : STD_LOGIC;
  signal \dm1_reg[5]_i_1_n_0\ : STD_LOGIC;
  signal \dm1_reg[5]_i_2_n_0\ : STD_LOGIC;
  signal \dm1_reg[5]_i_3_n_0\ : STD_LOGIC;
  signal \dm1_reg[6]_i_1_n_0\ : STD_LOGIC;
  signal \dm1_reg[6]_i_2_n_0\ : STD_LOGIC;
  signal \dm1_reg[6]_i_3_n_0\ : STD_LOGIC;
  signal \dm1_reg[6]_i_4_n_0\ : STD_LOGIC;
  signal \dm1_reg[7]_i_1_n_0\ : STD_LOGIC;
  signal \dm1_reg[7]_i_2_n_0\ : STD_LOGIC;
  signal \dm1_reg[7]_i_3_n_0\ : STD_LOGIC;
  signal \dm1_reg[7]_i_4_n_0\ : STD_LOGIC;
  signal \dm1_reg[7]_i_5_n_0\ : STD_LOGIC;
  signal \dm1_reg[7]_i_6_n_0\ : STD_LOGIC;
  signal flag : STD_LOGIC;
  signal flag_i_1_n_0 : STD_LOGIC;
  signal \wm1[0]_i_1_n_0\ : STD_LOGIC;
  signal \wm1[1]_i_1_n_0\ : STD_LOGIC;
  signal \wm1[2]_i_1_n_0\ : STD_LOGIC;
  signal \wm1[3]_i_1_n_0\ : STD_LOGIC;
  signal \NLW_div_cnt0_carry__2_CO_UNCONNECTED\ : STD_LOGIC_VECTOR ( 3 downto 1 );
  signal \NLW_div_cnt0_carry__2_O_UNCONNECTED\ : STD_LOGIC_VECTOR ( 3 downto 2 );
  attribute SOFT_HLUTNM : string;
  attribute SOFT_HLUTNM of \disp_bit[0]_i_1\ : label is "soft_lutpair10";
  attribute SOFT_HLUTNM of \disp_bit[1]_i_1\ : label is "soft_lutpair10";
  attribute ADDER_THRESHOLD : integer;
  attribute ADDER_THRESHOLD of div_cnt0_carry : label is 35;
  attribute ADDER_THRESHOLD of \div_cnt0_carry__0\ : label is 35;
  attribute ADDER_THRESHOLD of \div_cnt0_carry__1\ : label is 35;
  attribute ADDER_THRESHOLD of \div_cnt0_carry__2\ : label is 35;
  attribute SOFT_HLUTNM of \div_cnt[0]_i_1\ : label is "soft_lutpair7";
  attribute SOFT_HLUTNM of divclk_i_1 : label is "soft_lutpair7";
  attribute XILINX_LEGACY_PRIM : string;
  attribute XILINX_LEGACY_PRIM of \dm1_reg[0]\ : label is "LD";
  attribute SOFT_HLUTNM of \dm1_reg[0]_i_1\ : label is "soft_lutpair1";
  attribute XILINX_LEGACY_PRIM of \dm1_reg[1]\ : label is "LD";
  attribute SOFT_HLUTNM of \dm1_reg[1]_i_2\ : label is "soft_lutpair4";
  attribute XILINX_LEGACY_PRIM of \dm1_reg[2]\ : label is "LD";
  attribute SOFT_HLUTNM of \dm1_reg[2]_i_1\ : label is "soft_lutpair2";
  attribute SOFT_HLUTNM of \dm1_reg[2]_i_3\ : label is "soft_lutpair6";
  attribute XILINX_LEGACY_PRIM of \dm1_reg[3]\ : label is "LD";
  attribute SOFT_HLUTNM of \dm1_reg[3]_i_2\ : label is "soft_lutpair4";
  attribute SOFT_HLUTNM of \dm1_reg[3]_i_4\ : label is "soft_lutpair3";
  attribute XILINX_LEGACY_PRIM of \dm1_reg[4]\ : label is "LD";
  attribute SOFT_HLUTNM of \dm1_reg[4]_i_3\ : label is "soft_lutpair0";
  attribute XILINX_LEGACY_PRIM of \dm1_reg[5]\ : label is "LD";
  attribute XILINX_LEGACY_PRIM of \dm1_reg[6]\ : label is "LD";
  attribute SOFT_HLUTNM of \dm1_reg[6]_i_3\ : label is "soft_lutpair0";
  attribute SOFT_HLUTNM of \dm1_reg[6]_i_4\ : label is "soft_lutpair6";
  attribute XILINX_LEGACY_PRIM of \dm1_reg[7]\ : label is "LD";
  attribute SOFT_HLUTNM of \dm1_reg[7]_i_2\ : label is "soft_lutpair5";
  attribute SOFT_HLUTNM of \dm1_reg[7]_i_3\ : label is "soft_lutpair5";
  attribute SOFT_HLUTNM of \dm1_reg[7]_i_4\ : label is "soft_lutpair1";
  attribute SOFT_HLUTNM of \dm1_reg[7]_i_6\ : label is "soft_lutpair3";
  attribute SOFT_HLUTNM of flag_i_1 : label is "soft_lutpair2";
  attribute SOFT_HLUTNM of \wm1[0]_i_1\ : label is "soft_lutpair9";
  attribute SOFT_HLUTNM of \wm1[1]_i_1\ : label is "soft_lutpair8";
  attribute SOFT_HLUTNM of \wm1[2]_i_1\ : label is "soft_lutpair8";
  attribute SOFT_HLUTNM of \wm1[3]_i_1\ : label is "soft_lutpair9";
begin
  Q(3 downto 0) <= \^q\(3 downto 0);
\disp_bit[0]_i_1\: unisim.vcomponents.LUT1
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => disp_bit(0),
      O => \disp_bit[0]_i_1_n_0\
    );
\disp_bit[1]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"6"
    )
        port map (
      I0 => disp_bit(1),
      I1 => disp_bit(0),
      O => \disp_bit[1]_i_1_n_0\
    );
\disp_bit_reg[0]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => divclk,
      CE => '1',
      D => \disp_bit[0]_i_1_n_0\,
      Q => disp_bit(0),
      R => '0'
    );
\disp_bit_reg[1]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => divclk,
      CE => '1',
      D => \disp_bit[1]_i_1_n_0\,
      Q => disp_bit(1),
      R => '0'
    );
div_cnt0_carry: unisim.vcomponents.CARRY4
     port map (
      CI => '0',
      CO(3) => div_cnt0_carry_n_0,
      CO(2) => div_cnt0_carry_n_1,
      CO(1) => div_cnt0_carry_n_2,
      CO(0) => div_cnt0_carry_n_3,
      CYINIT => div_cnt(0),
      DI(3 downto 0) => B"0000",
      O(3 downto 0) => data0(4 downto 1),
      S(3 downto 0) => div_cnt(4 downto 1)
    );
\div_cnt0_carry__0\: unisim.vcomponents.CARRY4
     port map (
      CI => div_cnt0_carry_n_0,
      CO(3) => \div_cnt0_carry__0_n_0\,
      CO(2) => \div_cnt0_carry__0_n_1\,
      CO(1) => \div_cnt0_carry__0_n_2\,
      CO(0) => \div_cnt0_carry__0_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3 downto 0) => data0(8 downto 5),
      S(3 downto 0) => div_cnt(8 downto 5)
    );
\div_cnt0_carry__1\: unisim.vcomponents.CARRY4
     port map (
      CI => \div_cnt0_carry__0_n_0\,
      CO(3) => \div_cnt0_carry__1_n_0\,
      CO(2) => \div_cnt0_carry__1_n_1\,
      CO(1) => \div_cnt0_carry__1_n_2\,
      CO(0) => \div_cnt0_carry__1_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3 downto 0) => data0(12 downto 9),
      S(3 downto 0) => div_cnt(12 downto 9)
    );
\div_cnt0_carry__2\: unisim.vcomponents.CARRY4
     port map (
      CI => \div_cnt0_carry__1_n_0\,
      CO(3 downto 1) => \NLW_div_cnt0_carry__2_CO_UNCONNECTED\(3 downto 1),
      CO(0) => \div_cnt0_carry__2_n_3\,
      CYINIT => '0',
      DI(3 downto 0) => B"0000",
      O(3 downto 2) => \NLW_div_cnt0_carry__2_O_UNCONNECTED\(3 downto 2),
      O(1 downto 0) => data0(14 downto 13),
      S(3 downto 2) => B"00",
      S(1 downto 0) => div_cnt(14 downto 13)
    );
\div_cnt[0]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"2"
    )
        port map (
      I0 => \div_cnt[0]_i_2_n_0\,
      I1 => div_cnt(0),
      O => div_cnt_0(0)
    );
\div_cnt[0]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"FFFFFFFFFFFFFBFF"
    )
        port map (
      I0 => \div_cnt[0]_i_3_n_0\,
      I1 => div_cnt(3),
      I2 => div_cnt(4),
      I3 => div_cnt(5),
      I4 => div_cnt(6),
      I5 => \div_cnt[0]_i_4_n_0\,
      O => \div_cnt[0]_i_2_n_0\
    );
\div_cnt[0]_i_3\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"FFF7"
    )
        port map (
      I0 => div_cnt(8),
      I1 => div_cnt(7),
      I2 => div_cnt(10),
      I3 => div_cnt(9),
      O => \div_cnt[0]_i_3_n_0\
    );
\div_cnt[0]_i_4\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"FFFFFFFFFFFFFFF7"
    )
        port map (
      I0 => div_cnt(13),
      I1 => div_cnt(14),
      I2 => div_cnt(11),
      I3 => div_cnt(12),
      I4 => div_cnt(2),
      I5 => div_cnt(1),
      O => \div_cnt[0]_i_4_n_0\
    );
\div_cnt[14]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => div_cnt(0),
      I1 => \div_cnt[0]_i_2_n_0\,
      O => divclk_1
    );
\div_cnt_reg[0]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => div_cnt_0(0),
      Q => div_cnt(0),
      R => '0'
    );
\div_cnt_reg[10]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(10),
      Q => div_cnt(10),
      R => divclk_1
    );
\div_cnt_reg[11]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(11),
      Q => div_cnt(11),
      R => divclk_1
    );
\div_cnt_reg[12]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(12),
      Q => div_cnt(12),
      R => divclk_1
    );
\div_cnt_reg[13]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(13),
      Q => div_cnt(13),
      R => divclk_1
    );
\div_cnt_reg[14]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(14),
      Q => div_cnt(14),
      R => divclk_1
    );
\div_cnt_reg[1]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(1),
      Q => div_cnt(1),
      R => divclk_1
    );
\div_cnt_reg[2]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(2),
      Q => div_cnt(2),
      R => divclk_1
    );
\div_cnt_reg[3]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(3),
      Q => div_cnt(3),
      R => divclk_1
    );
\div_cnt_reg[4]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(4),
      Q => div_cnt(4),
      R => divclk_1
    );
\div_cnt_reg[5]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(5),
      Q => div_cnt(5),
      R => divclk_1
    );
\div_cnt_reg[6]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(6),
      Q => div_cnt(6),
      R => divclk_1
    );
\div_cnt_reg[7]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(7),
      Q => div_cnt(7),
      R => divclk_1
    );
\div_cnt_reg[8]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(8),
      Q => div_cnt(8),
      R => divclk_1
    );
\div_cnt_reg[9]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => data0(9),
      Q => div_cnt(9),
      R => divclk_1
    );
divclk_i_1: unisim.vcomponents.LUT3
    generic map(
      INIT => X"E1"
    )
        port map (
      I0 => div_cnt(0),
      I1 => \div_cnt[0]_i_2_n_0\,
      I2 => divclk,
      O => divclk_i_1_n_0
    );
divclk_reg: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => clk,
      CE => '1',
      D => divclk_i_1_n_0,
      Q => divclk,
      R => '0'
    );
\dm1_reg[0]\: unisim.vcomponents.LDCE
    generic map(
      INIT => '0'
    )
        port map (
      CLR => '0',
      D => \dm1_reg[0]_i_1_n_0\,
      G => \dm1_reg[7]_i_2_n_0\,
      GE => '1',
      Q => dm1(0)
    );
\dm1_reg[0]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"00020000"
    )
        port map (
      I0 => flag,
      I1 => \^q\(1),
      I2 => \^q\(2),
      I3 => \^q\(0),
      I4 => \^q\(3),
      O => \dm1_reg[0]_i_1_n_0\
    );
\dm1_reg[1]\: unisim.vcomponents.LDCE
    generic map(
      INIT => '0'
    )
        port map (
      CLR => '0',
      D => \dm1_reg[1]_i_1_n_0\,
      G => \dm1_reg[7]_i_2_n_0\,
      GE => '1',
      Q => dm1(1)
    );
\dm1_reg[1]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"B888BBBBB8888888"
    )
        port map (
      I0 => \dm1_reg[1]_i_2_n_0\,
      I1 => \dm1_reg[7]_i_3_n_0\,
      I2 => flag,
      I3 => t(0),
      I4 => \^q\(2),
      I5 => \dm1_reg[1]_i_3_n_0\,
      O => \dm1_reg[1]_i_1_n_0\
    );
\dm1_reg[1]_i_2\: unisim.vcomponents.LUT3
    generic map(
      INIT => X"B2"
    )
        port map (
      I0 => a(0),
      I1 => a(1),
      I2 => a(2),
      O => \dm1_reg[1]_i_2_n_0\
    );
\dm1_reg[1]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0000FFFF7C007C00"
    )
        port map (
      I0 => t(1),
      I1 => t(2),
      I2 => t(3),
      I3 => flag,
      I4 => \dm1_reg[5]_i_3_n_0\,
      I5 => \^q\(1),
      O => \dm1_reg[1]_i_3_n_0\
    );
\dm1_reg[2]\: unisim.vcomponents.LDCE
    generic map(
      INIT => '0'
    )
        port map (
      CLR => '0',
      D => \dm1_reg[2]_i_1_n_0\,
      G => \dm1_reg[7]_i_2_n_0\,
      GE => '1',
      Q => dm1(2)
    );
\dm1_reg[2]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"AAAA8A02"
    )
        port map (
      I0 => \dm1_reg[3]_i_2_n_0\,
      I1 => \^q\(2),
      I2 => \dm1_reg[2]_i_2_n_0\,
      I3 => flag,
      I4 => \dm1_reg[7]_i_3_n_0\,
      O => \dm1_reg[2]_i_1_n_0\
    );
\dm1_reg[2]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"FBFFF3FB00000000"
    )
        port map (
      I0 => t(1),
      I1 => flag,
      I2 => \^q\(1),
      I3 => t(3),
      I4 => t(2),
      I5 => \dm1_reg[2]_i_3_n_0\,
      O => \dm1_reg[2]_i_2_n_0\
    );
\dm1_reg[2]_i_3\: unisim.vcomponents.LUT3
    generic map(
      INIT => X"1F"
    )
        port map (
      I0 => a(2),
      I1 => a(0),
      I2 => \^q\(1),
      O => \dm1_reg[2]_i_3_n_0\
    );
\dm1_reg[3]\: unisim.vcomponents.LDCE
    generic map(
      INIT => '0'
    )
        port map (
      CLR => '0',
      D => \dm1_reg[3]_i_1_n_0\,
      G => \dm1_reg[7]_i_2_n_0\,
      GE => '1',
      Q => dm1(3)
    );
\dm1_reg[3]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"AA02AA8AAA02AA02"
    )
        port map (
      I0 => \dm1_reg[3]_i_2_n_0\,
      I1 => \^q\(2),
      I2 => \dm1_reg[3]_i_3_n_0\,
      I3 => \dm1_reg[7]_i_3_n_0\,
      I4 => t(0),
      I5 => flag,
      O => \dm1_reg[3]_i_1_n_0\
    );
\dm1_reg[3]_i_2\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"BFFB"
    )
        port map (
      I0 => a(2),
      I1 => \dm1_reg[7]_i_3_n_0\,
      I2 => a(0),
      I3 => a(1),
      O => \dm1_reg[3]_i_2_n_0\
    );
\dm1_reg[3]_i_3\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"1111F0FF1111FFFF"
    )
        port map (
      I0 => a(0),
      I1 => a(2),
      I2 => t(1),
      I3 => flag,
      I4 => \^q\(1),
      I5 => \dm1_reg[3]_i_4_n_0\,
      O => \dm1_reg[3]_i_3_n_0\
    );
\dm1_reg[3]_i_4\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"B"
    )
        port map (
      I0 => t(2),
      I1 => t(3),
      O => \dm1_reg[3]_i_4_n_0\
    );
\dm1_reg[4]\: unisim.vcomponents.LDCE
    generic map(
      INIT => '0'
    )
        port map (
      CLR => '0',
      D => \dm1_reg[4]_i_1_n_0\,
      G => \dm1_reg[7]_i_2_n_0\,
      GE => '1',
      Q => dm1(4)
    );
\dm1_reg[4]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"66666666FFF0F0F0"
    )
        port map (
      I0 => a(0),
      I1 => a(1),
      I2 => \dm1_reg[4]_i_2_n_0\,
      I3 => flag,
      I4 => \^q\(2),
      I5 => \dm1_reg[7]_i_3_n_0\,
      O => \dm1_reg[4]_i_1_n_0\
    );
\dm1_reg[4]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"1540000055555555"
    )
        port map (
      I0 => \^q\(2),
      I1 => a(1),
      I2 => a(2),
      I3 => a(0),
      I4 => \^q\(1),
      I5 => \dm1_reg[4]_i_3_n_0\,
      O => \dm1_reg[4]_i_2_n_0\
    );
\dm1_reg[4]_i_3\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"FDDFDDFD"
    )
        port map (
      I0 => flag,
      I1 => \^q\(1),
      I2 => t(3),
      I3 => t(2),
      I4 => t(1),
      O => \dm1_reg[4]_i_3_n_0\
    );
\dm1_reg[5]\: unisim.vcomponents.LDCE
    generic map(
      INIT => '0'
    )
        port map (
      CLR => '0',
      D => \dm1_reg[5]_i_1_n_0\,
      G => \dm1_reg[7]_i_2_n_0\,
      GE => '1',
      Q => dm1(5)
    );
\dm1_reg[5]_i_1\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"FF00B1B1"
    )
        port map (
      I0 => \^q\(2),
      I1 => \dm1_reg[5]_i_2_n_0\,
      I2 => flag,
      I3 => \dm1_reg[5]_i_3_n_0\,
      I4 => \dm1_reg[7]_i_3_n_0\,
      O => \dm1_reg[5]_i_1_n_0\
    );
\dm1_reg[5]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"88888B88BBBBBBBB"
    )
        port map (
      I0 => \dm1_reg[1]_i_2_n_0\,
      I1 => \^q\(1),
      I2 => t(1),
      I3 => t(2),
      I4 => t(3),
      I5 => flag,
      O => \dm1_reg[5]_i_2_n_0\
    );
\dm1_reg[5]_i_3\: unisim.vcomponents.LUT3
    generic map(
      INIT => X"45"
    )
        port map (
      I0 => a(2),
      I1 => a(1),
      I2 => a(0),
      O => \dm1_reg[5]_i_3_n_0\
    );
\dm1_reg[6]\: unisim.vcomponents.LDCE
    generic map(
      INIT => '0'
    )
        port map (
      CLR => '0',
      D => \dm1_reg[6]_i_1_n_0\,
      G => \dm1_reg[7]_i_2_n_0\,
      GE => '1',
      Q => dm1(6)
    );
\dm1_reg[6]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"7577000075775577"
    )
        port map (
      I0 => \dm1_reg[6]_i_2_n_0\,
      I1 => \^q\(2),
      I2 => \^q\(1),
      I3 => \dm1_reg[6]_i_3_n_0\,
      I4 => \dm1_reg[6]_i_4_n_0\,
      I5 => \dm1_reg[7]_i_3_n_0\,
      O => \dm1_reg[6]_i_1_n_0\
    );
\dm1_reg[6]_i_2\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"0100010106060606"
    )
        port map (
      I0 => \^q\(1),
      I1 => \^q\(3),
      I2 => \^q\(0),
      I3 => t(0),
      I4 => flag,
      I5 => \^q\(2),
      O => \dm1_reg[6]_i_2_n_0\
    );
\dm1_reg[6]_i_3\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"DDFDFDDD"
    )
        port map (
      I0 => flag,
      I1 => \^q\(1),
      I2 => t(3),
      I3 => t(2),
      I4 => t(1),
      O => \dm1_reg[6]_i_3_n_0\
    );
\dm1_reg[6]_i_4\: unisim.vcomponents.LUT3
    generic map(
      INIT => X"35"
    )
        port map (
      I0 => a(0),
      I1 => a(2),
      I2 => a(1),
      O => \dm1_reg[6]_i_4_n_0\
    );
\dm1_reg[7]\: unisim.vcomponents.LDCE
    generic map(
      INIT => '0'
    )
        port map (
      CLR => '0',
      D => \dm1_reg[7]_i_1_n_0\,
      G => \dm1_reg[7]_i_2_n_0\,
      GE => '1',
      Q => dm1(7)
    );
\dm1_reg[7]_i_1\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"FF6F0000FF6FFF6F"
    )
        port map (
      I0 => a(1),
      I1 => a(0),
      I2 => \dm1_reg[7]_i_3_n_0\,
      I3 => a(2),
      I4 => \dm1_reg[7]_i_4_n_0\,
      I5 => \dm1_reg[7]_i_5_n_0\,
      O => \dm1_reg[7]_i_1_n_0\
    );
\dm1_reg[7]_i_2\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"0116"
    )
        port map (
      I0 => \^q\(0),
      I1 => \^q\(1),
      I2 => \^q\(3),
      I3 => \^q\(2),
      O => \dm1_reg[7]_i_2_n_0\
    );
\dm1_reg[7]_i_3\: unisim.vcomponents.LUT4
    generic map(
      INIT => X"FEEB"
    )
        port map (
      I0 => \^q\(0),
      I1 => \^q\(2),
      I2 => \^q\(3),
      I3 => \^q\(1),
      O => \dm1_reg[7]_i_3_n_0\
    );
\dm1_reg[7]_i_4\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"FFF9FEF9"
    )
        port map (
      I0 => \^q\(1),
      I1 => \^q\(3),
      I2 => \^q\(0),
      I3 => \^q\(2),
      I4 => flag,
      O => \dm1_reg[7]_i_4_n_0\
    );
\dm1_reg[7]_i_5\: unisim.vcomponents.LUT6
    generic map(
      INIT => X"AFEFAFFFAAAAAAAA"
    )
        port map (
      I0 => \^q\(2),
      I1 => a(1),
      I2 => \^q\(1),
      I3 => a(0),
      I4 => a(2),
      I5 => \dm1_reg[7]_i_6_n_0\,
      O => \dm1_reg[7]_i_5_n_0\
    );
\dm1_reg[7]_i_6\: unisim.vcomponents.LUT5
    generic map(
      INIT => X"FFFF14FF"
    )
        port map (
      I0 => t(2),
      I1 => t(3),
      I2 => t(1),
      I3 => flag,
      I4 => \^q\(1),
      O => \dm1_reg[7]_i_6_n_0\
    );
flag_i_1: unisim.vcomponents.LUT1
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => flag,
      O => flag_i_1_n_0
    );
flag_reg: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => s,
      CE => '1',
      D => flag_i_1_n_0,
      Q => flag,
      R => '0'
    );
\wm1[0]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"8"
    )
        port map (
      I0 => disp_bit(0),
      I1 => disp_bit(1),
      O => \wm1[0]_i_1_n_0\
    );
\wm1[1]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"1"
    )
        port map (
      I0 => disp_bit(0),
      I1 => disp_bit(1),
      O => \wm1[1]_i_1_n_0\
    );
\wm1[2]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"2"
    )
        port map (
      I0 => disp_bit(0),
      I1 => disp_bit(1),
      O => \wm1[2]_i_1_n_0\
    );
\wm1[3]_i_1\: unisim.vcomponents.LUT2
    generic map(
      INIT => X"4"
    )
        port map (
      I0 => disp_bit(0),
      I1 => disp_bit(1),
      O => \wm1[3]_i_1_n_0\
    );
\wm1_reg[0]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => divclk,
      CE => '1',
      D => \wm1[0]_i_1_n_0\,
      Q => \^q\(0),
      R => '0'
    );
\wm1_reg[1]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => divclk,
      CE => '1',
      D => \wm1[1]_i_1_n_0\,
      Q => \^q\(1),
      R => '0'
    );
\wm1_reg[2]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '1'
    )
        port map (
      C => divclk,
      CE => '1',
      D => \wm1[2]_i_1_n_0\,
      Q => \^q\(2),
      R => '0'
    );
\wm1_reg[3]\: unisim.vcomponents.FDRE
    generic map(
      INIT => '0'
    )
        port map (
      C => divclk,
      CE => '1',
      D => \wm1[3]_i_1_n_0\,
      Q => \^q\(3),
      R => '0'
    );
end STRUCTURE;
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity number_0 is
  port (
    s : in STD_LOGIC;
    t : in STD_LOGIC_VECTOR ( 3 downto 0 );
    clk : in STD_LOGIC;
    a : in STD_LOGIC_VECTOR ( 2 downto 0 );
    dm1 : out STD_LOGIC_VECTOR ( 7 downto 0 );
    wm1 : out STD_LOGIC_VECTOR ( 3 downto 0 )
  );
  attribute NotValidForBitStream : boolean;
  attribute NotValidForBitStream of number_0 : entity is true;
  attribute CHECK_LICENSE_TYPE : string;
  attribute CHECK_LICENSE_TYPE of number_0 : entity is "number_0,number,{}";
  attribute DowngradeIPIdentifiedWarnings : string;
  attribute DowngradeIPIdentifiedWarnings of number_0 : entity is "yes";
  attribute IP_DEFINITION_SOURCE : string;
  attribute IP_DEFINITION_SOURCE of number_0 : entity is "package_project";
  attribute X_CORE_INFO : string;
  attribute X_CORE_INFO of number_0 : entity is "number,Vivado 2021.1";
end number_0;

architecture STRUCTURE of number_0 is
  attribute X_INTERFACE_INFO : string;
  attribute X_INTERFACE_INFO of clk : signal is "xilinx.com:signal:clock:1.0 clk CLK";
  attribute X_INTERFACE_PARAMETER : string;
  attribute X_INTERFACE_PARAMETER of clk : signal is "XIL_INTERFACENAME clk, FREQ_HZ 100000000, FREQ_TOLERANCE_HZ 0, PHASE 0.0, INSERT_VIP 0";
begin
inst: entity work.number_0_number
     port map (
      Q(3 downto 0) => wm1(3 downto 0),
      a(2 downto 0) => a(2 downto 0),
      clk => clk,
      dm1(7 downto 0) => dm1(7 downto 0),
      s => s,
      t(3 downto 0) => t(3 downto 0)
    );
end STRUCTURE;
