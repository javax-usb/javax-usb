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
 * UsbRuntimeException indicating an attempt was made to access or use an
 * inactive part of a UsbDevice.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class NotActiveException extends UsbRuntimeException
{
	/** Constructs a <code>NotActiveException</code> with no detail message. */
	public NotActiveException() { super(); }

	/**
	 * Constructs a <code>NotActiveException</code> with the specified 
	 * detail message. 
	 * @param msg the detail message
	 */
	public NotActiveException( String msg ) { super(msg); }

	/**
	 * Constructs a <code>NotActiveException</code> with the specified 
	 * detail message and error code.
	 * @param msg the detail message.
	 * @param err the error code.
	 */
	public NotActiveException( String msg, int err ) { super(msg,err); }

}
