package javax.usb.event;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import javax.usb.*;

/**
 * Defines a class for UsbPipe error events
 * <p>
 * This will be fired for all errors occurring during a submission, including
 * but not limited to:
 * <ul>
 * <li>Device disconnected.</li>
 * <li>Endpoint/pipe stalled/halted.</li>
 * <li>{@link javax.usb.UsbPipe#abortAllSubmissions() Submission aborted}.</li>
 * <li>{@link javax.usb.UsbIrp#getAcceptShortPacket() Short packets}.</li>
 * <li>Any transient bus error which does not place the UsbPipe in an error state.</li>
 * <li>Any persistent bus error which places the UsbPipe in an error state.</li>
 * </ul>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public class UsbPipeErrorEvent extends UsbPipeEvent
{
    //-------------------------------------------------------------------------
    // Public ctor(s)
    //

    /**
     * Creates a UsbPipeErrorEvent.
     * @param usbPipe the event's source.
	 * @param sn the sequence number of the assocaited submission.
	 * @param ec the error code of the error.
     * @param uE the UsbException assocaited with this error event.
     */
    public UsbPipeErrorEvent( UsbPipe usbPipe, long sn, int ec, UsbException uE ) 
    {
        super( usbPipe );
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
