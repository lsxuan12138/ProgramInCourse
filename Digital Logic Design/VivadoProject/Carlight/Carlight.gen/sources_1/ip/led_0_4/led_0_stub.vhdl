-- Copyright 1986-2021 Xilinx, Inc. All Rights Reserved.
-- --------------------------------------------------------------------------------
-- Tool Version: Vivado v.2021.1 (win64) Build 3247384 Thu Jun 10 19:36:33 MDT 2021
-- Date        : Wed Dec  1 16:30:34 2021
-- Host        : MateBook14 running 64-bit major release  (build 9200)
-- Command     : write_vhdl -force -mode synth_stub d:/VProject/Carlight/Carlight.gen/sources_1/ip/led_0_4/led_0_stub.vhdl
-- Design      : led_0
-- Purpose     : Stub declaration of top-level module interface
-- Device      : xc7a35tcsg324-3
-- --------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity led_0 is
  Port ( 
    clk : in STD_LOGIC;
    a : in STD_LOGIC_VECTOR ( 2 downto 0 );
    led : out STD_LOGIC_VECTOR ( 7 downto 0 );
    up : in STD_LOGIC;
    down : in STD_LOGIC;
    switch : in STD_LOGIC;
    t1 : out STD_LOGIC_VECTOR ( 3 downto 0 )
  );

end led_0;

architecture stub of led_0 is
attribute syn_black_box : boolean;
attribute black_box_pad_pin : string;
attribute syn_black_box of stub : architecture is true;
attribute black_box_pad_pin of stub : architecture is "clk,a[2:0],led[7:0],up,down,switch,t1[3:0]";
attribute X_CORE_INFO : string;
attribute X_CORE_INFO of stub : architecture is "led,Vivado 2021.1";
begin
end;
