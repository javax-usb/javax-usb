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
 * Defines an interface for all the standard USB operations to a device
 * <p>
 * This interface is used to help use the USB devices and submit request, however
 * all operations/request can be coded by creating the correct byte[] with all the 
 * appropriate values and then submitting it directly through the device's default ctrl pipe
 * or other pipes.
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface StandardOperations extends UsbOperations
{
    //-------------------------------------------------------------------------
    // Public operations methods
	// NOTE: all operations are simplified of all arguments that are not necessary or superfluous
    //

	/**
	 * Used to disable to clear or disable a specific feature
	 * <p><i>See USB 1.1 spec section 9.4.1</i>
	 * @param bmRequestType the request type bitmap
	 * @param wValue the word feature selector value
	 * @param wIndex Zero or Interface or Endpoint index
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request clearFeature( byte bmRequestType, short wValue, short wIndex ) throws RequestException;

	/**
	 * Returns the current device configuration value
	 * <p><i>See USB 1.1 spec section 9.4.2</i>
	 * @param data a byte array of 1 to contain the configuration value
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request getConfiguration( byte[] data ) throws RequestException;

	/**
	 * Returns the specified descriptor if it exists
	 * <p><i>See USB 1.1 spec section 9.4.3</i>
	 * @param wValue the descriptor type and index
	 * @param wIndex zero or the language ID for String descriptor
	 * @param data a byte array of the correct length to contain the descriptor data bytes
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request getDescriptor( short wValue, short wIndex, byte[] data ) throws RequestException;

	/**
	 * Returns the alternate setting for the specified interface
	 * <p><i>See USB 1.1 spec section 9.4.4</i>
	 * @param wIndex the interface index
	 * @param data a byte array of size 1 to contain the alternate setting value
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request getInterface( short wIndex, byte[] data ) throws RequestException;

	/**
	 * Returns the status for the specified recipient
	 * <p><i>See USB 1.1 spec section 9.4.5</i>
	 * @param bmRequestType the request type bitmap
	 * @param wIndex zero for device status request OR interface or endpoint index
	 * @param data a byte array of size 2 to contain the device, interface or endpoint status
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request getStatus( byte bmRequestType, short wIndex, byte[] data ) throws RequestException;

	/**
	 * Sets the device address for all future device accesses
	 * <p><i>See USB 1.1 spec section 9.4.6</i>
	 * @param wValue the device address
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request setAddress( short wValue ) throws RequestException;

	/**
	 * Sets the device configuration
	 * <p><i>See USB 1.1 spec section 9.4.7</i>
	 * @param wValue the configuration value
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request setConfiguration( short wValue ) throws RequestException;

	/**
	 * Update existing descriptor or add new descriptor
	 * <p><i>See USB 1.1 spec section 9.4.8</i>
	 * @param wValue the descriptor type and index
	 * @param wIndex the language ID if the descriptor is a String descriptor or zero
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request setDescriptor( short wValue, short wIndex, byte[] data ) throws RequestException;

	/**
	 * Sets or enable a specific feature
	 * <p><i>See USB 1.1 spec section 9.4.8</i>
	 * @param bmRequestType the request type bitmap
	 * @param wValue the feature selector value
	 * @param wIndex zero for device feature OR interface or endpoint feature value
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request setFeature( byte bmRequestType, short wValue, short wIndex ) throws RequestException;

	/**
	 * Allows the host to select an alternate setting for the specified interface
 	 * <p><i>See USB 1.1 spec section 9.4.9</i>
	 * @param wIndex the interface number
	 * @param wValue the alternate setting value
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request setInterface( short wIndex, short wValue ) throws RequestException;

	/**
	 * Sets and then report an endpoint's synchronization frame
	 * <p><i>See USB 1.1 spec section 9.4.10</i>
	 * @param wIndex the endpoint index
	 * @param data a byte array of size 2 to contain the frame number
	 * @return a Request object that is created for this submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public Request synchFrame( short wIndex, byte[] data ) throws RequestException;
}
