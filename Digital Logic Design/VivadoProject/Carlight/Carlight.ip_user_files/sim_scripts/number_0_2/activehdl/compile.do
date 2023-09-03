vlib work
vlib activehdl

vlib activehdl/xil_defaultlib

vmap xil_defaultlib activehdl/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../Carlight.gen/sources_1/ip/number_0_2/sources_1/new/number.v" \
"../../../../Carlight.gen/sources_1/ip/number_0_2/sim/number_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

