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
 * Indicates data was successfully transferred over the UsbPipe.
 * <p>
 * This event will be fired to all listeners for all data that is transferred over the pipe.
 * <p>
 * Since multiple listeners are possible and some may choose to modify the data or UsbIrp directly,
 * a unique copy of the data is provided for each listener, but the UsbIrp is the same that was
 * originally submitted.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public class UsbPipeDataEvent extends UsbPipeEvent
{
	/**
	 * Constructor.
	 * @param source The UsbPipe.
	 * @param irp The UsbIrp, or null if no UsbIrp was involved.
	 * @param data The data.
	 * @param offset The offset into the data.
	 * @param length The amount of data transferred.
	 * @exception IllegalArgumentException If the offset, length or data is invalid.
	 */
	public UsbPipeDataEvent( UsbPipe source, UsbIrp irp, byte[] data, int offset, int length ) throws IllegalArgumentException
	{
		super(source);
		usbIrp = irp;
		if (0 > offset)
			throw new IllegalArgumentException("Offset cannot be negative");
		if (0 > length)
			throw new IllegalArgumentException("Length cannot be negative");
		if ((offset+length) > data.length)
			throw new IllegalArgumentException("Offset + length cannot be larger than data.length");
		this.data = new byte[length];
		System.arraycopy(data, offset, this.data, 0, length);
	}

	/**
	 * Get the UsbIrp.
	 * <p>
	 * If no UsbIrp was involved, this returns null.
	 * @return The UsbIrp, or null.
	 */
	public UsbIrp getUsbIrp() { return usbIrp; }

	/**
	 * Get the data.
	 * <p>
	 * This is a copy of the original buffer used.
	 * The size is the exact amount of data actually transferred.
	 * @return The transferred data.
	 */
	public byte[] getData() { return data; }

	private UsbIrp usbIrp = null;
	private byte[] data = null;

}
