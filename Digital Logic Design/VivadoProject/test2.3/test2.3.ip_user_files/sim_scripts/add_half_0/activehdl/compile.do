vlib work
vlib activehdl

vlib activehdl/xil_defaultlib

vmap xil_defaultlib activehdl/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../test2.3.gen/sources_1/ip/add_half_0/add_half.v" \
"../../../../test2.3.gen/sources_1/ip/add_half_0/sim/add_half_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

