-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Wed Oct 20 21:22:22 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode funcsim
--               d:/VProject/test2.3/test2.3.gen/sources_1/ip/add_half_0/add_half_0_sim_netlist.vhdl
-- Design      : add_half_0
-- Purpose     : This VHDL netlist is a functional simulation representation of the design and should not be modified or
--               synthesized. This netlist cannot be used for SDF annotated simulation.
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
library UNISIM;
use UNISIM.VCOMPONENTS.ALL;
entity add_half_0 is
  port (
    s : out STD_LOGIC;
    ci : out STD_LOGIC;
    ai : in STD_LOGIC;
    bi : in STD_LOGIC
  );
  attribute NotValidForBitStream : boolean;
  attribute NotValidForBitStream of add_half_0 : entity is true;
  attribute CHECK_LICENSE_TYPE : string;
  attribute CHECK_LICENSE_TYPE of add_half_0 : entity is "add_half_0,add_half,{}";
  attribute DowngradeIPIdentifiedWarnings : string;
  attribute DowngradeIPIdentifiedWarnings of add_half_0 : entity is "yes";
  attribute IP_DEFINITION_SOURCE : string;
  attribute IP_DEFINITION_SOURCE of add_half_0 : entity is "package_project";
  attribute X_CORE_INFO : string;
  attribute X_CORE_INFO of add_half_0 : entity is "add_half,Vivado 2021.1";
end add_half_0;

architecture STRUCTURE of add_half_0 is
begin
ci_INST_0: unisim.vcomponents.LUT2
    generic map(
      INIT => X"8"
    )
        port map (
      I0 => ai,
      I1 => bi,
      O => ci
    );
s_INST_0: unisim.vcomponents.LUT2
    generic map(
      INIT => X"6"
    )
        port map (
      I0 => bi,
      I1 => ai,
      O => s
    );
end STRUCTURE;
