package javax.usb.event;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import javax.usb.UsbPipe;

/**
 * Defines a UsbPipeEvent that indicates a successfully completed data transmission.
 * <p>
 * This event will be fired on all successful transfers of data:
 * <ul>
 * <li>{@link javax.usb.UsbPipe#syncSubmit( byte[] data ) synchronous byte[] submission}.</li>
 * <li>{@link javax.usb.UsbPipe#asyncSubmit( byte[] data ) asynchronous byte[] submission}.</li>
 * <li>{@link javax.usb.UsbPipe#syncSubmit( UsbIrp irp ) synchronous UsbIrp (and UsbCompositeIrp) submission}
 * if the {@link javax.usb.UsbIrp.EventCommand EventCommand} chooses to.</li>
 * <li>{@link javax.usb.UsbPipe#asyncSubmit( UsbIrp irp ) asynchronous UsbIrp (and UsbCompositeIrp) submission}
 * if the {@link javax.usb.UsbIrp.EventCommand EventCommand} chooses to.</li>
 * </ul>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public class UsbPipeDataEvent extends UsbPipeEvent
{
    /**
	 * Constructor.
     * @param usbPipe the event's source.
	 * @param sn the sequence number of the associated submission.
     * @param data the byte[] data.
	 * @param dataLength the number of valid bytes in the data array.
     */
    public UsbPipeDataEvent( UsbPipe usbPipe, long sn, byte[] ba, int dl ) 
    {
        super( usbPipe );
		sequenceNumber = sn;
        data = ba;
		dataLength = dl;
    }

    //-------------------------------------------------------------------------
    // Public methods
    //

    /**
	 * Get the data associated with this successful data transfer.
	 * <p>
	 * This byte[] is the original byte[] submitted on the <code>UsbPipe</code>
	 * (not a copy), so the <code>data.length</code> may be longer than the
	 * number of bytes actually transferred in the operation.  See
	 * {@link #getDataLength()} for the valid number of bytes in this byte[].
	 * @return the byte[] data for this Data event
	 */
    public byte[] getData() { return data; }

    /**
	 * Get the number of valid bytes in the associated data.
	 * <p>
	 * This indicates how many bytes were actually transferred in
	 * the operation associated with this event.  Only this many bytes
	 * of the associated data are valid.  This will always be zero or greater
	 * (never negative).
	 * @return the number of valid bytes of data for this Data event.
	 */
    public int getDataLength() { return dataLength; }

	/**
	 * Get the sequence number of the assocaited submission.
	 * @return the sequence number of the assocaited submission.
	 */
	public long getSequenceNumber() { return sequenceNumber; }

    //-------------------------------------------------------------------------
    // Instance variables
    //

    private byte[] data = null;
	private int dataLength = -1;
	private long sequenceNumber = 0;
}
