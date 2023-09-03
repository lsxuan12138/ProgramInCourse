vlib work
vlib activehdl

vlib activehdl/xil_defaultlib

vmap xil_defaultlib activehdl/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../4Dcounter.gen/sources_1/ip/D_trigger_0/D_trigger.v" \
"../../../../4Dcounter.gen/sources_1/ip/D_trigger_0/sim/D_trigger_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

