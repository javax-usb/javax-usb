package javax.usb;

/*
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import javax.usb.util.Recyclable;

/**
 * Defines the basic Request interface
 * Use the RequestFactory from the HostManager to create Request objects
 * <p>
 * <i>NOTE: all getter methods are derived from the USB 1.1 spec definition for the 
 * different standard requests.  See chapter 9.
 * </i>
 * @author E. Michael Maximilien
 * @since 0.8.0
 * @version 1.0.0
 * @see javax.usb.UsbHostManager#getUsbServices
 * @see javax.usb.os.UsbServices#getRequestFactory
 */
public interface Request extends Recyclable
{
    //-------------------------------------------------------------------------
    // Public property methods
	// NOTE: all getter methods come from the USB 1.1 spec definition for the 
	// different standard requests.  See chapter 9.
    //

	/** @return the bmRequestType bitmap byte for this Request */
	public byte getRequestType();

	/** @return the Request code byte for this request */
	public byte getRequest();

	/** @return the wValue for this request */
	public short getValue();

	/** 
	 * Sets the wValue for this Request object
	 * @param wValue the short value
	 * @throws javax.usb.UsbRuntimeException if data supplied is invalid for 
	 * the Request wValue
	 */
	public void setValue( short wValue ) throws UsbRuntimeException;

	/** @return the wIndex for this request */
	public short getIndex();

	/** 
	 * Sets the wIdex for this Request object
	 * @param wIndex the short index
	 * @throws javax.usb.UsbRuntimeException if data supplied is invalid for 
	 * the Request wIndex
	 */
	public void setIndex( short wIndex ) throws UsbRuntimeException;

	/**
	 * @return the length of the data for this request 
	 * <p><i>NOTE: this length is calculated from the Data property length
	 * (i.e. this is not the total request length)</i>
	 */
	public short getLength();

	/** @return the data byte[] for this request */
	public byte[] getData();

	/** 
	 * Sets the Data array for this Request object
	 * @param data the byte[] data value
	 * @throws javax.usb.UsbRuntimeException if data supplied is invalid for 
	 * the Request Data
	 */
	public void setData( byte[] data ) throws UsbRuntimeException;

	//-------------------------------------------------------------------------
	// Other public methods
	//

	/**
	 * @return a formated byte[] representing this Request object
	 * @exception javax.usb.RequestException if this Request object is invalid
	 */
	public byte[] toBytes() throws RequestException;

	/**
	 * Explicitly tell this Request object that it can be recycled.  
	 * Clients call this method to tell the RequestFactory that this request 
	 * can be recycled
	 * <p>
	 * <i>NOTE: the RequestFactory may or may not choose to acknowledge that the 
	 * Request object is available.  Typically the RequestFactory will add the 
	 * recycled Request object to its available pool.</i>
	 * @see javax.usb.RequestFactory
	 */
	public void recycle();
}
