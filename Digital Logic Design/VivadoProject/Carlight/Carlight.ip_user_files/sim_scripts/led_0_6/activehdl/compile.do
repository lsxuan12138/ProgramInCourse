vlib work
vlib activehdl

vlib activehdl/xil_defaultlib

vmap xil_defaultlib activehdl/xil_defaultlib

vlog -work xil_defaultlib  -v2k5 \
"../../../../Carlight.gen/sources_1/ip/led_0_6/src/settime.v" \
"../../../../Carlight.gen/sources_1/ip/led_0_6/src/led.v" \
"../../../../Carlight.gen/sources_1/ip/led_0_6/sim/led_0.v" \


vlog -work xil_defaultlib \
"glbl.v"

