vlib questa_lib/work
vlib questa_lib/msim

vlib questa_lib/msim/xil_defaultlib

vmap xil_defaultlib questa_lib/msim/xil_defaultlib

vlog -work xil_defaultlib  -incr -mfcu \
"../../../../Carlight.gen/sources_1/ip/led_0_1/src/settime.v" \
"../../../../Carlight.gen/sources_1/ip/led_0_1/src/led.v" \
"../../../../Carlight.gen/sources_1/ip/led_0_1/sim/led_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

