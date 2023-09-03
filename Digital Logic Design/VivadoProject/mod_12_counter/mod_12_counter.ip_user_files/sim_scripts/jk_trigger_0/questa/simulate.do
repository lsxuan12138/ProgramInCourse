onbreak {quit -f}
onerror {quit -f}

vsim -lib xil_defaultlib jk_trigger_0_opt

set NumericStdNoWarnings 1
set StdArithNoWarnings 1

do {wave.do}

view wave
view structure
view signals

do {jk_trigger_0.udo}

run -all

quit -force
