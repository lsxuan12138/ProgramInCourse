-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Tue Oct 26 19:27:07 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode synth_stub
--               d:/VProject/4Dcounter/4Dcounter.gen/sources_1/ip/D_trigger_0/D_trigger_0_stub.vhdl
-- Design      : D_trigger_0
-- Purpose     : Stub declaration of top-level module interface
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity D_trigger_0 is
  Port ( 
    clk : in STD_LOGIC;
    d : in STD_LOGIC;
    rst : in STD_LOGIC;
    q : out STD_LOGIC
  );

end D_trigger_0;

architecture stub of D_trigger_0 is
attribute syn_black_box : boolean;
attribute black_box_pad_pin : string;
attribute syn_black_box of stub : architecture is true;
attribute black_box_pad_pin of stub : architecture is "clk,d,rst,q";
attribute X_CORE_INFO : string;
attribute X_CORE_INFO of stub : architecture is "D_trigger,Vivado 2021.1";
begin
end;
