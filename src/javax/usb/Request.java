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
 * @author Dan Streetman
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
	public byte getRequestCode();

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

	/**
	 * Get the length of valid data for this request.
	 * <p>
	 * NOTE: this length is the length of data actually sent/received to/from
	 * the device, which is always less than or equal to the length of
	 * the byte[] buffer.  Only this number of bytes in the buffer are
	 * valid (and only after the request is complete).
	 * @return the length of vaid data for this request.
	 */
	public int getDataLength();

	/**
	 * Set the length of valid data for this request.
	 * <p>
	 * The implementation will call this with the valid data length.
	 * @param length The length of the valid data.
	 */
	public void setDataLength(int length);

	/** @return the data byte[] for this request */
	public byte[] getDataBytes();

	/** 
	 * Sets the Data array for this Request object
	 * @param data the byte[] data value
	 */
	public void setDataBytes( byte[] data );

	/**
	 * Get the UsbException that occurred during this request.
	 * <p>
	 * This is null if no exception occurred.
	 * @return The UsbException.
	 */
	public UsbException getUsbException();

	/**
	 * Set the UsbException the occurred.
	 * <p>
	 * This will be called by the implementation to set the UsbException.
	 * @param usbException The UsbException.
	 */
	public void setUsbException( UsbException usbException );

	/**
	 * If a UsbException occurred.
	 * @return If a UsbException occurred.
	 */
	public boolean isUsbException();

	/**
	 * If this is completed.
	 * @return If this is completed.
	 */
	public boolean isCompleted();

	/**
	 * Set this as completed (or not).
	 * <p>
	 * The implementation will call this when this is completed.
	 * @param completed If this is completed.
	 */
	public void setCompleted( boolean completed );

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
