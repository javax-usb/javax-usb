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
 * Defines an interface for hub class request.
 * <p>
 * Similar to the StandardOperations interface, hub class request are
 * used to define methods to facilitate there usage via instances of HubClassOperations.
 * HubClassOperations are not created directly but instead accessed using a UsbHub
 * object.  The object returned is also directly associated with the UsbHub that
 * returned it.
 * <p><i>NOTE: if the UsbHub associated with the HubClassOperations is no longer valid (e.g.
 * disconnected from the bus) then all operations on this object will fail</i></p>
 * </p>
 * 
 * @author E. Michael Maximilien
 * @since 0.8.0
 * @see javax.usb.UsbHub#getClassOperations
 * @see javax.usb.StandardOperations
 */
public interface HubClassOperations extends ClassOperations
{
    //-------------------------------------------------------------------------
    // Public methods
    //

	/** @return the UsbHub associated with this HubClassOperations object */
	public UsbHub getUsbHub();

	/**
	 * Used to disable to clear or disable a specific feature
	 * @param bmRequestType the request type bitmap
	 * @param wValue the feature value selector
	 * @param wIndex the port number (1 based)
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request clearFeature( byte bmRequestType, short wValue, short wIndex ) throws RequestException;

	/**
	 * Returns the state of the hub
	 * @param wIndex the port number (1 based)
	 * @param data byte array of size 1 for the port bus state
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request getState( short wIndex, byte[] data ) throws RequestException;

	/**
	 * Returns the specified descriptor if it exists
	 * @param wValue the descriptor type and index
	 * @param wIndex zero or the language ID for String descriptor
	 * @param data a byte array of the correct length to contain the descriptor data bytes
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request getDescriptor( short wValue, short wIndex, byte[] data ) throws RequestException;

	/**
	 * Returns the status for the specified recipient
	 * @param bmRequestType the request type bitmap
	 * @param wIndex 0 for GetHubStatus and the port number for GetPortStatus
	 * @param data a byte[] of size 4 to contain the hub status
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request getStatus( byte bmRequestType, short wIndex, byte[] data ) throws RequestException;

	/**
	 * Update existing descriptor or add new descriptor
	 * @param wValue the descriptor type and index
	 * @param wIndex the language ID if the descriptor is a String descriptor or zero
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request setDescriptor( short wValue, short wIndex, byte[] data ) throws RequestException;

	/**
	 * Sets or enable a specific feature
	 * @param bmRequestType the request type bitmap
	 * @param wValue the feature selector value
	 * @param wIndex zero or port number (1 based)
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request setFeature( byte bmRequestType, short wValue, short wIndex ) throws RequestException;
}
