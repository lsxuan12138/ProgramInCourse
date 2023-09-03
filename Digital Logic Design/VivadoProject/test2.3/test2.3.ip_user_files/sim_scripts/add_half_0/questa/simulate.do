onbreak {quit -f}
onerror {quit -f}

vsim -lib xil_defaultlib add_half_0_opt

set NumericStdNoWarnings 1
set StdArithNoWarnings 1

do {wave.do}

view wave
view structure
view signals

do {add_half_0.udo}

run -all

quit -force
