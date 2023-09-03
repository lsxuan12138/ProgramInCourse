vlib questa_lib/work
vlib questa_lib/msim

vlib questa_lib/msim/xil_defaultlib

vmap xil_defaultlib questa_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
"../../../../Carlight.gen/sources_1/ip/number_0_1/sources_1/new/number.v" \
"../../../../Carlight.gen/sources_1/ip/number_0_1/sim/number_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

