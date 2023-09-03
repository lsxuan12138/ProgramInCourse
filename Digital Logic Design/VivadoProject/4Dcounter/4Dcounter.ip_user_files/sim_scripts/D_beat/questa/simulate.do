onbreak {quit -f}
onerror {quit -f}

vsim -lib xil_defaultlib D_beat_opt

set NumericStdNoWarnings 1
set StdArithNoWarnings 1

do {wave.do}

view wave
view structure
view signals

do {D_beat.udo}

run -all

quit -force
