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
 * Defines an interface for class request.  Defines a generic method 
 * that can be used to submit all type of vendor request.  In addition
 * the syncSubmit and asyncSubmit methods taking Request object can also be used
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface ClassOperations extends UsbOperations
{
    //-------------------------------------------------------------------------
    // Public methods
    //

	/**
	 * Used to submit any class request.  Note that the bmRequestType field bits 6..5
	 * must be set to 0x01 for Class type according to the USB 1.1. specification
	 * @param bmRequestType the request type bitmap
	 * @param requestType the specific request type
	 * @param wValue the word feature selector value
	 * @param wIndex Zero or Interface or Endpoint index
	 * @param data a byte array for the request Data
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request classRequest( byte bmRequestType, byte requestType, 
								 short wValue, short wIndex, byte[] data ) throws RequestException;
}
