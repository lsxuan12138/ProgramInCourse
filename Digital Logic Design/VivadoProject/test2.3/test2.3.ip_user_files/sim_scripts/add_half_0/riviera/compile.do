vlib work
vlib riviera

vlib riviera/xil_defaultlib

vmap xil_defaultlib riviera/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../test2.3.gen/sources_1/ip/add_half_0/add_half.v" \
"../../../../test2.3.gen/sources_1/ip/add_half_0/sim/add_half_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

