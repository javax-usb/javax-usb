package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

/**
 * Defines a Control-type USB Irp (I/O Request Packet).
 * <p>
 * This is identical to a UsbIrp, except this also contains the Control-specific
 * setup packet information.
 * @author Dan Streetman
 */
public interface ControlUsbIrp extends UsbIrp
{
	/**
	 * Get the bmRequestType.
	 * @return The bmRequestType.
	 */
	public byte bmRequestType();

	/**
	 * Get the bRequest.
	 * @return The bRequest.
	 */
	public byte bRequest();

	/**
	 * Get the wValue.
	 * @return The wValue.
	 */
	public short wValue();

	/**
	 * Get the wIndex.
	 * @return The wIndex.
	 */
	public short wIndex();

}
