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

/**
 * Super class for all USB event
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class UsbEvent extends EventObject
{
    /**
     * 1-arg ctor takes the event source
     * @param source the event's source
     */
    public UsbEvent( Object source ) { super( source ); }
}
