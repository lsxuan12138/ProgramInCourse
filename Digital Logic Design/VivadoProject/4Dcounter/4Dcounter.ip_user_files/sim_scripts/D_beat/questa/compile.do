vlib questa_lib/work
vlib questa_lib/msim

vlib questa_lib/msim/xil_defaultlib

vmap xil_defaultlib questa_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
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

