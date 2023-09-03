-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Wed Dec  1 01:17:09 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode synth_stub -rename_top mc_0 -prefix
--               mc_0_ mc_0_stub.vhdl
-- Design      : mc_0
-- Purpose     : Stub declaration of top-level module interface
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity mc_0 is
  Port ( 
    clk : in STD_LOGIC;
    s : in STD_LOGIC;
    sdin : in STD_LOGIC;
    pwm : out STD_LOGIC;
    sd : out STD_LOGIC
  );

end mc_0;

architecture stub of mc_0 is
attribute syn_black_box : boolean;
attribute black_box_pad_pin : string;
attribute syn_black_box of stub : architecture is true;
attribute black_box_pad_pin of stub : architecture is "clk,s,sdin,pwm,sd";
attribute X_CORE_INFO : string;
attribute X_CORE_INFO of stub : architecture is "mc,Vivado 2021.1";
begin
end;
