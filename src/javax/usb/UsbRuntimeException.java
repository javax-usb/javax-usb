package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

/**
 * Runtime Exceptions for the javax.usb API.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public class UsbRuntimeException extends RuntimeException
{
	/** Constructs a <code>UsbRuntimeException</code> with no detail message. */
	public UsbRuntimeException() { super(); }

	/**
	 * Constructs a <code>UsbRuntimeException</code> with the specified 
	 * detail message. 
	 * @param msg the detail message.
	 */
	public UsbRuntimeException( String msg ) { super(msg); }

	/**
	 * Constructs a <code>UsbRuntimeException</code> with the specified 
	 * detail message and error code.
	 * @param msg the detail message.
	 * @param err the error code.
	 */
	public UsbRuntimeException( String msg, int err )
	{
		super(msg);
		errorCode = err;
	}

    //-------------------------------------------------------------------------
    // Public methods
    //

    /** @return the error code for this exception */
    public int getErrorCode() { return errorCode; }

    //-------------------------------------------------------------------------
    // Instance variables
    //

    private int errorCode = -1;
}
