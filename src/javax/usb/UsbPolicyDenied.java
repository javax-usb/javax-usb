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
 * Exception indicating a policy prevented an action.
 * @author Dan Streetman
 */
public class UsbPolicyDenied extends UsbException
{
	/**
	 * Constructor.
	 */
	public UsbPolicyDenied() { super(); }

	/**
	 * Constructor.
	 * @param s The detail message.
	 */
	public UsbPolicyDenied(String s) { super(s); }
}
