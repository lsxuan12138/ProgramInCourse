-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Mon Nov 29 19:15:35 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode synth_stub d:/VProject/test/test.gen/sources_1/ip/divClk_0/divClk_0_stub.vhdl
-- Design      : divClk_0
-- Purpose     : Stub declaration of top-level module interface
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity divClk_0 is
  Port ( 
    Clk : in STD_LOGIC;
    s1 : in STD_LOGIC;
    s0 : in STD_LOGIC;
    divClk : out STD_LOGIC
  );

end divClk_0;

architecture stub of divClk_0 is
attribute syn_black_box : boolean;
attribute black_box_pad_pin : string;
attribute syn_black_box of stub : architecture is true;
attribute black_box_pad_pin of stub : architecture is "Clk,s1,s0,divClk";
attribute X_CORE_INFO : string;
attribute X_CORE_INFO of stub : architecture is "divClk,Vivado 2021.1";
begin
end;
