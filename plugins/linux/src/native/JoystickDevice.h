/**
 * Copyright (C) 2003 Jeremy Booth (jeremy@newdawnsoftware.com)
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer. Redistributions in binary 
 * form must reproduce the above copyright notice, this list of conditions and 
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. 
 * The name of the author may not be used to endorse or promote products derived
 * from this software without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO 
 * EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE
 */

#ifndef JoystickDevice_h
#define JoystickDevice_h

#include <stdint.h>
#include <linux/input.h>
#include "eventInterfaceTypes.h"
#include "Device.h"

class JoystickDevice : public Device {

  private:
    int fd;
    int inited;
    char *name;
    int numButtons;
    int *absAxesData;
    uint8_t *buttonData;
    int numAbsAxes;

  public:
    JoystickDevice(char *deviceFilename);
    int getNumberRelAxes();
    int getNumberAbsAxes();
    int getNumberButtons();
    const char *getName();
    int getBusType();
    int getVendorID();
    int getProductID();
    int getVersion();
    void getSupportedRelAxes(int supportedAxis[]);
    void getSupportedAbsAxes(int supportedAxis[]);
    void getSupportedButtons(int supportedButtons[]);
    int poll();
    void getPolledData(int relAxesData[], int absAxesData[], int buttonData[]);
    int getAbsAxisMinimum(int axisNumber);
    int getAbsAxisMaximum(int axisNumber);
    int getAbsAxisFuzz(int axisNumber);
    int isValidDevice();

};

#endif //eventInterface_eventDevice_h