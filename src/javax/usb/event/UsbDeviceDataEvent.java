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
	 * @param length The amount of data transferred.
	 * @exception IllegalArgumentException If the length is less than 0 or greater than data.length.
	 */
	public UsbDeviceDataEvent( UsbDevice source, ControlUsbIrp irp, byte[] data, int length ) throws IllegalArgumentException
	{
		super(source);
		controlUsbIrp = irp;
		if (0 > length || length > data.length)
			throw new IllegalArgumentException("Illegal data length " + length + "; length must be between 0 and data.length");
		this.data = new byte[length];
		System.arraycopy(data, 0, this.data, 0, length);
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
