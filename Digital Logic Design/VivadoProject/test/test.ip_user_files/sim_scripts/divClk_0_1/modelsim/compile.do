vlib modelsim_lib/work
vlib modelsim_lib/msim

vlib modelsim_lib/msim/xil_defaultlib

vmap xil_defaultlib modelsim_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
"../../../../test.gen/sources_1/ip/divClk_0_1/sources_1/new/divClk.v" \
"../../../../test.gen/sources_1/ip/divClk_0_1/sim/divClk_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

