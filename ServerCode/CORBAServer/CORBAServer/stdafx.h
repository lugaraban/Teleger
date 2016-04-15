// stdafx.h : include file for standard system include files,
// or project specific include files that are used frequently, but
// are changed infrequently
//

#pragma once

#include "targetver.h"

#include <stdio.h>
#include <tchar.h>
#include <iostream>
#include "../../../bin/teleger.hh"
#include <vector>
#include <mutex>


static struct serverSideUser {
	const char * id;
	const char * name;
	const char * image;
};

// TODO: reference additional headers your program requires here
