-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Wed Oct 20 21:22:22 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode synth_stub
--               d:/VProject/test2.3/test2.3.gen/sources_1/ip/add_half_0/add_half_0_stub.vhdl
-- Design      : add_half_0
-- Purpose     : Stub declaration of top-level module interface
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity add_half_0 is
  Port ( 
    s : out STD_LOGIC;
    ci : out STD_LOGIC;
    ai : in STD_LOGIC;
    bi : in STD_LOGIC
  );

end add_half_0;

architecture stub of add_half_0 is
attribute syn_black_box : boolean;
attribute black_box_pad_pin : string;
attribute syn_black_box of stub : architecture is true;
attribute black_box_pad_pin of stub : architecture is "s,ci,ai,bi";
attribute X_CORE_INFO : string;
attribute X_CORE_INFO of stub : architecture is "add_half,Vivado 2021.1";
begin
end;
