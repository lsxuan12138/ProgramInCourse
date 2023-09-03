-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Tue Nov 30 21:25:11 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode synth_stub
--               d:/VProject/Carlight/Carlight.gen/sources_1/ip/number_0_1/number_0_stub.vhdl
-- Design      : number_0
-- Purpose     : Stub declaration of top-level module interface
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity number_0 is
  Port ( 
    s : in STD_LOGIC;
    t : in STD_LOGIC_VECTOR ( 3 downto 0 );
    clk : in STD_LOGIC;
    a : in STD_LOGIC_VECTOR ( 2 downto 0 );
    dm1 : out STD_LOGIC_VECTOR ( 7 downto 0 );
    wm1 : out STD_LOGIC_VECTOR ( 3 downto 0 )
  );

end number_0;

architecture stub of number_0 is
attribute syn_black_box : boolean;
attribute black_box_pad_pin : string;
attribute syn_black_box of stub : architecture is true;
attribute black_box_pad_pin of stub : architecture is "s,t[3:0],clk,a[2:0],dm1[7:0],wm1[3:0]";
attribute X_CORE_INFO : string;
attribute X_CORE_INFO of stub : architecture is "number,Vivado 2021.1";
begin
end;
