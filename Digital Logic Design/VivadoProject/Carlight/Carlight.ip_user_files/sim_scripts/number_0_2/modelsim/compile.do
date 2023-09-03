vlib modelsim_lib/work
vlib modelsim_lib/msim

vlib modelsim_lib/msim/xil_defaultlib

vmap xil_defaultlib modelsim_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
"../../../../Carlight.gen/sources_1/ip/number_0_2/sources_1/new/number.v" \
"../../../../Carlight.gen/sources_1/ip/number_0_2/sim/number_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

