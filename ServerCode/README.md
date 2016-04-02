# Central CORBA server
This server takes part in a project that tries to emulate Microsoft's Messenger. This server is written in C++ and uses  
CORBA to communicate with the client, implemented in java. In this document I'll post some interesting points of the project, as well as tricky ones.

## Resources
	
* [OmniOrb](http://omniorb.sourceforge.net/) 
* [FreeMySqlHosting](http://www.freemysqlhosting.net/) 
* [Visual Studio 2015](https://www.visualstudio.com/es-es/downloads/download-visual-studio-vs.aspx) 

## Overview
The main purpose of this server is to communicate with a MySQL database and connect the different clients between them.


## Remote functions