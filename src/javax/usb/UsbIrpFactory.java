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
 * Defines a Factory for creating UsbIrp objects.
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbIrpFactory
{
	/**
	 * Create a UsbIrp
	 * @return a UsbIrp
	 */
	public UsbIrp createUsbIrp();

}
