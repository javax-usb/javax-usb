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
 * Defines a factory interface to create Request objects
 * <p>It is suggested that the implementation of this Factory interface would recycles the
 * Request objects once they are used and the Request.recycle() method</p>
 * @author E. Michael Maximilien
 * @since 0.8.0
 * @see javax.usb.Request
 * @see javax.usb.Request#recycle
 */
public interface RequestFactory
{
	//-------------------------------------------------------------------------
	// Public methods
	//

	/**
	 * Indicates to the RequestFactory object that the Request object can be recycled
	 * That is can be reused again.
	 * @param request the Request object to recycle
	 */
	public void recycle( Request request );

	/**
	 * Indicates to the RequestFactory object that the RequestBundle object can be recycled
	 * That is can be reused again.
	 * @param requestBundle the RequestBundle object to recycle
	 */
	public void recycle( RequestBundle requestBundle );

    //-------------------------------------------------------------------------
    // Public methods - create a RequestBundle
    //

	/**
	 * @return a RequestBundle object that is used to aggregate and submit 
	 * vendor or class specific requests (standard request cannot be bundled)
	 */
	public RequestBundle createRequestBundle();

    //-------------------------------------------------------------------------
    // Public methods - create methods for Vendor Request
    //

	/**
	 * @return a Vendor Request
	 * @param bmRequestType the request type bitmap
	 * @param requestType the request type value
	 * @param wValue the word feature selector value
	 * @param wIndex the index value
	 * @param data the Data byte[]
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createVendorRequest( byte bmRequestType, byte requestType,
										short wValue, short wIndex, byte[] data ) throws RequestException;
	
	//-------------------------------------------------------------------------
    // Public methods - create methods for Class Request
    //

	/**
	 * @return a Class Request (this also includes hub class requests)
	 * @param bmRequestType the request type bitmap
	 * @param requestType the request type value
	 * @param wValue the word feature selector value
	 * @param wIndex the index value
	 * @param data the Data byte[]
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createClassRequest( byte bmRequestType, byte requestType,
							     	   short wValue, short wIndex, byte[] data ) throws RequestException;

    //-------------------------------------------------------------------------
    // Public methods - create methods for Standard Request
    //

	/**
	 * Used to disable to clear or disable a specific feature
	 * <p><i>See USB 1.1 spec section 9.4.1</i>
	 * @return a CLEAR_FEATURE Request object
	 * @param bmRequestType the request type bitmap
	 * @param wValue the word feature selector value
	 * @param wIndex Zero or Interface or Endpoint index
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createClearFeatureRequest( byte bmRequestType, short wValue, short wIndex ) throws RequestException;

	/**
	 * Returns the current device configuration value
	 * <p><i>See USB 1.1 spec section 9.4.2</i>
	 * @return a GET_CONFIGURATION Request object
	 * @param data a byte array of 1 to contain the configuration value
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createGetConfigurationRequest( byte[] data ) throws RequestException;

	/**
	 * Returns the specified descriptor if it exists
	 * <p><i>See USB 1.1 spec section 9.4.3</i>
	 * @return a GET_DESCRIPTOR Request object
	 * @param wValue the descriptor type and index
	 * @param data a byte array of the correct length to contain the descriptor data bytes
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createGetDescriptorRequest( short wValue, short wIndex, byte[] data ) throws RequestException;

	/**
	 * Returns the alternate setting for the specified interface
	 * <p><i>See USB 1.1 spec section 9.4.4</i>
	 * @return a GET_INTERFACE Request object
	 * @param wIndex the interface number
	 * @param data a byte array of size 1 to contain the alternate setting value
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createGetInterfaceRequest( short wIndex, byte[] data ) throws RequestException;

	/**
	 * Returns the status for the specified recipient
	 * <p><i>See USB 1.1 spec section 9.4.5</i>
	 * @return a GET_STATUS Request object
	 * @param bmRequestType the request type bitmap
	 * @param wIndex zero for device status request OR interface or endpoint index
	 * @param data a byte array of size 2 to contain the device, interface or endpoint status
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createGetStatusRequest( byte bmRequestType, short wIndex, byte[] data ) throws RequestException;

	/**
	 * Sets the device address for all future device accesses
	 * <p><i>See USB 1.1 spec section 9.4.6</i>
	 * @return a SET_ADDRESS Request object
	 * @param wValue the device address
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createSetAddressRequest( short wValue ) throws RequestException;

	/**
	 * Sets the device configuration
	 * <p><i>See USB 1.1 spec section 9.4.7</i>
	 * @return a SET_CONFIGURATION Request object
	 * @param wValue the configuration value
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createSetConfigurationRequest( short wValue ) throws RequestException;

	/**
	 * Update existing descriptor or add new descriptor
	 * <p><i>See USB 1.1 spec section 9.4.8</i>
	 * @return a SET_DESCRIPTOR Request object
	 * @param wValue the descriptor type and index
	 * @param wIndex the language ID if the descriptor is a String descriptor or zero
	 * @param data a byte array of the correct length to contain the descriptor data bytes
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createSetDescriptorRequest( short wValue, short wIndex, byte[] data ) throws RequestException;

	/**
	 * Sets or enable a specific feature
	 * <p><i>See USB 1.1 spec section 9.4.8</i>
	 * @return a SET_FEATURE Request object
	 * @param bmRequestType the request type bitmap
	 * @param wValue the feature selector value
	 * @param wIndex zero for device feature OR interface or endpoint feature value
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createSetFeatureRequest( byte bmRequestType, short wValue, short wIndex ) throws RequestException;

	/**
	 * Allows the host to select an alternate setting for the specified interface
 	 * <p><i>See USB 1.1 spec section 9.4.9</i>
	 * @return a SET_INTERFACE Request object
	 * @param wIndex the interface number
	 * @param wValue the alternate setting value
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createSetInterfaceRequest( short wIndex, short wValue ) throws RequestException;

	/**
	 * Sets and then report an endpoint's synchronization frame
	 * <p><i>See USB 1.1 spec section 9.4.10</i>
	 * @return a SYNCH_FRAME Request object
	 * @param wIndex the endpoint number
	 * @param data a byte array of size 2 to contain the frame number
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createSynchFrameRequest( short wIndex, byte[] data ) throws RequestException;

	//-------------------------------------------------------------------------
    // Public methods - additional create methods for Hub class request
    //

	/**
	 * Querries and returns the state of the hub
	 * @return a GET_STATE Request object 
	 * @param wIndex
	 * @param data byte array of size 1 for the port bus state
	 * @exception javax.usb.RequestException if the value to create this Request object is not valid
	 */
	public Request createGetStateRequest( short wIndex, byte[] data ) throws RequestException;
}
