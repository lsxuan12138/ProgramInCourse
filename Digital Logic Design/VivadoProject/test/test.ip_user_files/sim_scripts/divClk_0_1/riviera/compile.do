vlib work
vlib riviera

vlib riviera/xil_defaultlib

vmap xil_defaultlib riviera/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../test.gen/sources_1/ip/divClk_0_1/sources_1/new/divClk.v" \
"../../../../test.gen/sources_1/ip/divClk_0_1/sim/divClk_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

