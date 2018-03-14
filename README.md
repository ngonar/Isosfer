# Isosfer
ISO 8583 Message Simulator for Development Purposes
\n\n

Start creating your ISO Simulation Server with :\n\n
\n
1. Deploy folder.\n
add content like 01_idpel_sample.xml as many as possible.\n
this works as your idpel dummy.\n
\n
%_timedelay => to determine delay time between processing code (DF03)\n
%_xx => x is the Data field number. you can customize this by your self.\n
\n
2. Modify the iso_simulator.ServerApplicationListener file.\n
edit as necessary.\n
the default configuration in on 1987 - ASCII channel packager format.\n
if you wish to use 2003 packager then you dont need the processing code.\n
\n
Be creative to create your own simulation server.\n
\n
Salam Olahraga,\n
IS
