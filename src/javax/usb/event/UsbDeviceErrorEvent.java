package javax.usb.event;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import javax.usb.*;

/**
 * Defines a class for UsbDevice error events
 * <p>
 * This will be fired for all errors occurring during a submission of Request and 
 * RequestBundle.
 * </p>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public class UsbDeviceErrorEvent extends UsbDeviceEvent
{
    //-------------------------------------------------------------------------
    // Public ctor(s)
    //

    /**
     * Creates a UsbDeviceErrorEvent.
     * @param usbDevice the event's source.
	 * @param sn the sequence number of the assocaited submission.
	 * @param ec the error code of the error.
     * @param uE the UsbException assocaited with this error event.
     */
    public UsbDeviceErrorEvent( UsbDevice usbDevice, long sn, int ec, UsbException uE ) 
    {
        super( usbDevice );
		sequenceNumber = sn;
		errorCode = ec;
        usbException = uE;
    }

    //-------------------------------------------------------------------------
    // Public methods
    //

	/**
	 * Get the UsbException that caused or is associated with this event.
	 * @return the UsbException associated with this error event
	 */
	public UsbException getUsbException() { return usbException; }

    /**
	 * @return the error code for this error event
	 */
    public int getErrorCode() { return errorCode; }

	/**
	 * Get the sequence number of the submission associated with this event
	 * @return the associated submission's sequence number
	 */
	public long getSequenceNumber() { return sequenceNumber; }

    //-------------------------------------------------------------------------
    // Instance variables
    //

	private UsbException usbException = null;
	private int errorCode = 0;
	private long sequenceNumber = 0;
}
