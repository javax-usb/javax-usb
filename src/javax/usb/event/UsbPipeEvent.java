package javax.usb.event;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.util.EventObject;

import javax.usb.UsbPipe;

/**
 * Super class for all USB Pipe events
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class UsbPipeEvent extends UsbEvent
{
    /**
     * Creates an event with the UsbPipe that generated it.
     * @param source the event's source.
     */
    public UsbPipeEvent( UsbPipe source ) { super( source ); }

    //-------------------------------------------------------------------------
    // Public methods
    //

    /** @return the UsbPipe that generated this event */
    public UsbPipe getUsbPipe() { return (UsbPipe)getSource(); }

}
