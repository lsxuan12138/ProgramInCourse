vlib questa_lib/work
vlib questa_lib/msim

vlib questa_lib/msim/xil_defaultlib

vmap xil_defaultlib questa_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
"../../../../Carlight.gen/sources_1/ip/mc_0_1/mc.v" \
"../../../../Carlight.gen/sources_1/ip/mc_0_1/sim/mc_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

