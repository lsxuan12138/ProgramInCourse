vlib modelsim_lib/work
vlib modelsim_lib/msim

vlib modelsim_lib/msim/xil_defaultlib

vmap xil_defaultlib modelsim_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
"../../../../Carlight.gen/sources_1/ip/led_0_1/src/settime.v" \
"../../../../Carlight.gen/sources_1/ip/led_0_1/src/led.v" \
"../../../../Carlight.gen/sources_1/ip/led_0_1/sim/led_0.v" \


vlog -work xil_defaultlib \
"glbl.v"
