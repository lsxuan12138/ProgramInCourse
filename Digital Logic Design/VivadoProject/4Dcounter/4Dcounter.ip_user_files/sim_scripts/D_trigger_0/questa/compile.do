vlib questa_lib/work
vlib questa_lib/msim

vlib questa_lib/msim/xil_defaultlib

vmap xil_defaultlib questa_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
"../../../../4Dcounter.gen/sources_1/ip/D_trigger_0/D_trigger.v" \
"../../../../4Dcounter.gen/sources_1/ip/D_trigger_0/sim/D_trigger_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

