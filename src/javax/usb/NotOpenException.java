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
 * Exception indicating an operation was attempted on a
 * {@link javax.usb.UsbPipe#isOpen() closed} UsbPipe.
 * @author Dan Streetman
 */
public class NotOpenException extends RuntimeException
{
	/**
	 * Constructor.
	 */
	public NotOpenException() { super(); }

	/**
	 * Constructor.
	 * @param s The detail message.
	 */
	public NotOpenException(String s) { super(s); }

}
