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
 * Defines an exception thrown when using Request objects
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class RequestException extends UsbException
{
    //-------------------------------------------------------------------------
    // Public ctor
    //

    /**
     * Creates a RequestException with message passed
     * @param msg the exception's message
     */
    public RequestException( String msg ) { super( msg ); }

    /**
     * Creates a RequestException with message and original exception causing this one
     * @param msg the exception's message
	 * @param origException the original exception
     */
    public RequestException( String msg, Exception origException ) 
	{ super( msg, origException ); }

    /**
     * Creates a RequestException message and error code integer
     * @param msg the exception's message
     * @param eCode the exception's error code
     */
    public RequestException( String msg, int eCode ) 
    { super( msg, eCode ); }
}
