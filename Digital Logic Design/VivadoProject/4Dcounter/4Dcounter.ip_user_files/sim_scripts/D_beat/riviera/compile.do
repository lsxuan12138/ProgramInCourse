vlib work
vlib riviera

vlib riviera/xil_defaultlib

vmap xil_defaultlib riviera/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../bd/D_beat/ipshared/b024/D_trigger.v" \
"../../../bd/D_beat/ip/D_beat_D_trigger_0_0/sim/D_beat_D_trigger_0_0.v" \
"../../../bd/D_beat/ip/D_beat_D_trigger_1_0/sim/D_beat_D_trigger_1_0.v" \
"../../../bd/D_beat/ip/D_beat_D_trigger_2_0/sim/D_beat_D_trigger_2_0.v" \
"../../../bd/D_beat/ip/D_beat_D_trigger_3_0/sim/D_beat_D_trigger_3_0.v" \
"../../../bd/D_beat/ipshared/997f/myNor.v" \
"../../../bd/D_beat/ip/D_beat_myNor_0_0/sim/D_beat_myNor_0_0.v" \
"../../../bd/D_beat/sim/D_beat.v" \


vlog -work xil_defaultlib \
"glbl.v"

