package javax.usb.event;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import java.util.EventObject;

import javax.usb.UsbDevice;

/**
 * Super class for all USB device event
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class UsbDeviceEvent extends UsbEvent
{
    //-------------------------------------------------------------------------
    // Public ctor(s)
    //

    /**
     * Creates a UsbDeviceEvent with source
     * @param source the event's source
     */
    public UsbDeviceEvent( UsbDevice source ) { super( source ); }

    //-------------------------------------------------------------------------
    // Public methods
    //

    /** @return the UsbDevice that caused this event */
    public UsbDevice getUsbDevice() { return (UsbDevice)getSource(); }

}
