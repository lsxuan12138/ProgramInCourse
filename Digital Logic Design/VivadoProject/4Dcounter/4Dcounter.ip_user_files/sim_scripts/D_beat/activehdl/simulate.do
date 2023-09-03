onbreak {quit -force}
onerror {quit -force}

asim +access +r +m+D_beat -L xil_defaultlib -L unisims_ver -L unimacro_ver -L secureip -O5 xil_defaultlib.D_beat xil_defaultlib.glbl

set NumericStdNoWarnings 1
set StdArithNoWarnings 1

do {wave.do}

view wave
view structure

do {D_beat.udo}

run -all

endsim

quit -force
