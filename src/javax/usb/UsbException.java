package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

/**
 * Defines the superclass of all exceptions for the javax.usb API
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class UsbException extends Exception
{
    //-------------------------------------------------------------------------
    // Public ctor
    //

    /** Constructs a UsbException with no detail message. */
	public UsbException() { super(); }

    /**
     * Constructs a UsbException with the specified detail message.
     * 1-arg ctor
     * @param msg the exception's message
     */
    public UsbException( String msg ) { super( msg ); }

    /**
     * Constructs a UsbException with the specified detail message and original Exception.
     * @param msg the exception's message
	 * @param origException the original exception
     */
    public UsbException( String msg, Exception origException ) 
	{ 
		super( msg ); 
		originalException = origException;
	}

    /**
     * Constructs a UsbException with the specified detail message and error code.
     * @param msg the exception's message
     * @param eCode the exception's error code
     */
    public UsbException( String msg, int eCode ) 
    { 
        super( msg ); 
        errorCode = eCode;
    }

    /**
     * Constructs a UsbException with the specified detail message, original Exception, and error code.
     * @param msg the exception's message
	 * @param origException the original exception
     * @param eCode the exception's error code
     */
    public UsbException( String msg, Exception origException, int eCode ) 
	{ 
		super( msg ); 
		originalException = origException;
        errorCode = eCode;
	}

    //-------------------------------------------------------------------------
    // Public methods
    //

    /** @return the error code for this exception */
    public int getErrorCode() { return errorCode; }

	/** @return the original exception that caused this exception */
	public Exception getOriginalException() { return originalException; }

    //-------------------------------------------------------------------------
    // Instance variables
    //

    private int errorCode = -1;
	private Exception originalException = null;
}
