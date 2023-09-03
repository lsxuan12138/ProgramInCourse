vlib work
vlib riviera

vlib riviera/xil_defaultlib

vmap xil_defaultlib riviera/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../mod_12_counter.gen/sources_1/ip/jk_trigger_0/sources_1/new/jk_trigger.v" \
"../../../../mod_12_counter.gen/sources_1/ip/jk_trigger_0/sim/jk_trigger_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

