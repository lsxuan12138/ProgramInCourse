vlib work
vlib riviera

vlib riviera/xil_defaultlib

vmap xil_defaultlib riviera/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../Carlight.gen/sources_1/ip/number_0_1/sources_1/new/number.v" \
"../../../../Carlight.gen/sources_1/ip/number_0_1/sim/number_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

