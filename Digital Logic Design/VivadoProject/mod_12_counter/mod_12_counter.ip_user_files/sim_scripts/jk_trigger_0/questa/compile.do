vlib questa_lib/work
vlib questa_lib/msim

vlib questa_lib/msim/xil_defaultlib

vmap xil_defaultlib questa_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
"../../../../mod_12_counter.gen/sources_1/ip/jk_trigger_0/sources_1/new/jk_trigger.v" \
"../../../../mod_12_counter.gen/sources_1/ip/jk_trigger_0/sim/jk_trigger_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

