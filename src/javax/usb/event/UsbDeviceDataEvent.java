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
 * Indicates data was successfully transferred over the Default Control Pipe.
 * <p>
 * This event will be fired on all successful transfers of data over the DCP.
 * <p>
 * Since multiple listeners are possible and some may choose to modify the data or UsbIrp directly,
 * a unique copy of the data is provided for each listener, but the UsbIrp is the same that was
 * originally submitted.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public class UsbDeviceDataEvent extends UsbDeviceEvent
{
	/**
	 * Constructor.
	 * @param source The UsbDevice.
	 * @param irp The ControlUsbIrp.
	 * @param data The data.
	 * @param offset The offset.
	 * @param length The amount of data transferred.
	 * @exception IllegalArgumentException If the offset, length or data is invalid.
	 */
	public UsbDeviceDataEvent( UsbDevice source, ControlUsbIrp irp, byte[] data, int offset, int length ) throws IllegalArgumentException
	{
		super(source);
		controlUsbIrp = irp;
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
	 * Get the ControlUsbIrp.
	 * @return The ControlUsbIrp.
	 */
	public ControlUsbIrp getControlUsbIrp() { return controlUsbIrp; }

	/**
	 * Get the data.
	 * <p>
	 * This is a copy of the original buffer used.
	 * The size is the exact amount of data actually transferred.
	 * @return The transferred data.
	 */
	public byte[] getData() { return data; }

	private ControlUsbIrp controlUsbIrp = null;
	private byte[] data = null;

}
