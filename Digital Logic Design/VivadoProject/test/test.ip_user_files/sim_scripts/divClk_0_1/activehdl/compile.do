vlib work
vlib activehdl

vlib activehdl/xil_defaultlib

vmap xil_defaultlib activehdl/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../test.gen/sources_1/ip/divClk_0_1/sources_1/new/divClk.v" \
"../../../../test.gen/sources_1/ip/divClk_0_1/sim/divClk_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

