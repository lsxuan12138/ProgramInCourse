vlib work
vlib riviera

vlib riviera/xil_defaultlib

vmap xil_defaultlib riviera/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../4Dcounter.gen/sources_1/ip/D_trigger_0/D_trigger.v" \
"../../../../4Dcounter.gen/sources_1/ip/D_trigger_0/sim/D_trigger_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

