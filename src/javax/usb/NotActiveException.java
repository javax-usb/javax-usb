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
 * Exception indicating an operation was attempted on an inactive part of a device.
 * <p>
 * This indicates either a configuration is
 * {@link javax.usb.UsbConfig#isActive() not active} or an interface setting is
 * {@link javax.usb.UsbInterface#isActive() not active}.
 * @author Dan Streetman
 */
public class NotActiveException extends RuntimeException
{
	/**
	 * Constructor.
	 */
	public NotActiveException() { super(); }

	/**
	 * Constructor.
	 * @param s The detail message.
	 */
	public NotActiveException(String s) { super(s); }
}
