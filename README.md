# Isosfer
ISO 8583 Message Simulator for Development Purposes
--

<br />
Start creating your ISO Simulation Server with :<br />
<br />
1. Deploy folder.<br />
add content like 01_idpel_sample.xml as many as possible.<br />
this works as your idpel dummy.<br />
<br />
%_timedelay => to determine delay time between processing code (DF03)<br />
%_xx => x is the Data field number. you can customize this by your self.<br />
<br />
<br />
2. Modify the iso_simulator.ServerApplicationListener file.<br />
edit as necessary.<br />
the default configuration in on 1987 - ASCII channel packager format.<br />
if you wish to use 2003 packager then you dont need the processing code.<br />
<br />
Be creative to create your own simulation server.<br />
<br />
--
Salam Olahraga,<br />
IS
--
