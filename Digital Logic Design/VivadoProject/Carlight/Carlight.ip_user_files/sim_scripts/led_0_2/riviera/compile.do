vlib work
vlib riviera

vlib riviera/xil_defaultlib

vmap xil_defaultlib riviera/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../Carlight.gen/sources_1/ip/led_0_2/src/settime.v" \
"../../../../Carlight.gen/sources_1/ip/led_0_2/src/led.v" \
"../../../../Carlight.gen/sources_1/ip/led_0_2/sim/led_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

