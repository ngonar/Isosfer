# Isosfer
ISO 8583 Message Simulator for Development Purposes


Start creating your ISO Simulation Server with :

1. Deploy folder.
add content like 01_idpel_sample.xml as many as possible.
this works as your idpel dummy.

%_timedelay => to determine delay time between processing code (DF03)
%_xx => x is the Data field number. you can customize this by your self.

2. Modify the iso_simulator.ServerApplicationListener file.
edit as necessary.
the default configuration in on 1987 - ASCII channel packager format.
if you wish to use 2003 packager then you dont need the processing code.

Be creative to create your own simulation server.

Salam Olahraga,
IS
