vlib modelsim_lib/work
vlib modelsim_lib/msim

vlib modelsim_lib/msim/xil_defaultlib

vmap xil_defaultlib modelsim_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
"../../../../test2.3.gen/sources_1/ip/add_half_0/add_half.v" \
"../../../../test2.3.gen/sources_1/ip/add_half_0/sim/add_half_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

