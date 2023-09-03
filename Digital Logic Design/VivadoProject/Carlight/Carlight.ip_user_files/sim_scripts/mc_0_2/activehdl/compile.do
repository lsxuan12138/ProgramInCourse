vlib work
vlib activehdl

vlib activehdl/xil_defaultlib

vmap xil_defaultlib activehdl/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../Carlight.gen/sources_1/ip/mc_0_2/mc.v" \
"../../../../Carlight.gen/sources_1/ip/mc_0_2/sim/mc_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

