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
import java.util.Vector;

import javax.usb.os.UsbServices;
import javax.usb.util.*;

/**
 * Event class for UsbServices
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public class UsbServicesEvent extends UsbEvent
{
    //-------------------------------------------------------------------------
    // Public ctor(s)
    //

	/**
     * Creates a UsbServicesEvent with source and UsbDevice involved
	 * @param source the event's source
	 * @param usbDevices the usbDevices involved
	 */
	public UsbServicesEvent( UsbServices services, UsbInfoList devices )
	{
		super( services );
		usbDevices = devices;
	}

    //-------------------------------------------------------------------------
    // Public methods
    //

    /**
	 * Get the UsbServices object that fired this.
	 * @return the UsbServices that caused this event.
	 **/
    public UsbServices getUsbServices() { return (UsbServices)getSource(); }

	/**
	 * Get the UsbInfoList of UsbDevices involved in this event.
	 * @return the UsbDevices involved in this event.
	 */
	public UsbInfoList getUsbDevices() { return usbDevices; }

    //-------------------------------------------------------------------------
    // Instance variables
    //

	private UsbInfoList usbDevices = null;
}
