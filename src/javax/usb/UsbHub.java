package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import javax.usb.util.UsbInfoListIterator;

/**
 * Defines a USB hub.
 * <p>
 * USB hubs are USB devices that implement the USB hub specification.
 * See the USB 1.1 specification Chapter 11 for the USB hub specification.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbHub extends UsbDevice
{
    /**
	 * Get the number of (downstream) ports this hub has.
	 * <p>
	 * This is only the number of ports on the hub, not
	 * all ports are necessarily enabled, available, usable, or
	 * in some cases physically present.  This only represents
	 * the number of downstream ports the hub claims to have.
	 * Note that all hubs have exactly one upstream port, which
	 * allows it to connect to the system (or another upstream hub).
	 * There is also a internal port which is generally only used
	 * by the hub itself.  See the USB 1.1 specification sec 11.4
	 * for details on the internal port, sec 11.5 for details on the
	 * downstream ports, and sec 11.6 for details on the upstream port.
	 * @return the number of (downstream) ports for this hub
	 */
    public byte getNumberOfPorts();

    /**
	 * Get an iteration of the ports this hub has.
	 * <p>
	 * See getUsbPort() for important details on port numbering.
	 * All UsbInfo objects in the returned iteration will implement UsbPort.
	 * @return an iteration of UsbPort objects this hub has
	 * @see #getUsbPort( byte number )
	 */
    public UsbInfoListIterator getUsbPorts();

	/**
	 * Get a specific UsbPort by port number.
	 * <p>
	 * This gets the UsbPort with the specified number.
	 * Note that the USB 1.1. specification implies (states?)
	 * that port numbers start with 1, not 0.  For example,
	 * see the USB 1.1 specification table 11.8 offset 7,
	 * where the port numbers start at 1, and 0 is not a valid
	 * port number.  Therefore,
	 * <code>getUsbPort(number) == (UsbPort)getUsbPorts().getUsbInfo(number-1)</code>.
	 * Additionally, the returned UsbPort's getNumber() method will return
	 * the specified number.
	 * @param number the number of the port to get
	 * @see #getUsbPorts()
	 */
	public UsbPort getUsbPort( byte number );

    /**
	 * Get an iterator of attached UsbDevices.
	 * <p>
	 * All UsbInfo objects in the returned iterator will implement UsbDevice.
	 * @return the iterator of devices currently attached to this hub
	 */
    public UsbInfoListIterator getAttachedUsbDevices();

    /**
	 * Returns true if this is a UsbRootHub.
	 * <p>
	 * See javax.usb.UsbRootHub for details on UsbRootHubs.
	 * @return true if this is the root hub
	 * @see javax.usb.UsbRootHub
	 */
    public boolean isUsbRootHub();

	/**
	 * Returns a HubClassOperations object that can be used to submit
	 * standard USB hub class Request objects to this hub.
	 * @return a HubClassOperations object to use with this UsbHub
	 * @see javax.usb.Request
	 * @see javax.usb.os.UsbServices#getRequestFactory
	 */
	public HubClassOperations getHubClassOperations();
}
