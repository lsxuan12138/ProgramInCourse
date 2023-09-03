-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Tue Oct 26 21:53:01 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode synth_stub
--               d:/VProject/mod_12_counter/mod_12_counter.gen/sources_1/ip/jk_trigger_0/jk_trigger_0_stub.vhdl
-- Design      : jk_trigger_0
-- Purpose     : Stub declaration of top-level module interface
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity jk_trigger_0 is
  Port ( 
    CLK : in STD_LOGIC;
    J : in STD_LOGIC;
    K : in STD_LOGIC;
    Set : in STD_LOGIC;
    Reset : in STD_LOGIC;
    Q : out STD_LOGIC
  );

end jk_trigger_0;

architecture stub of jk_trigger_0 is
attribute syn_black_box : boolean;
attribute black_box_pad_pin : string;
attribute syn_black_box of stub : architecture is true;
attribute black_box_pad_pin of stub : architecture is "CLK,J,K,Set,Reset,Q";
attribute X_CORE_INFO : string;
attribute X_CORE_INFO of stub : architecture is "jk_trigger,Vivado 2021.1";
begin
end;
