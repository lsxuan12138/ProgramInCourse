vlib questa_lib/work
vlib questa_lib/msim

vlib questa_lib/msim/xil_defaultlib

vmap xil_defaultlib questa_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
"../../../../test.gen/sources_1/ip/divClk_0_1/sources_1/new/divClk.v" \
"../../../../test.gen/sources_1/ip/divClk_0_1/sim/divClk_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

