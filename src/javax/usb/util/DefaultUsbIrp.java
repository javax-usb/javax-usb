package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.util.*;

import javax.usb.*;

/**
 * UsbIrp default implementation.
 * <p>
 * This uses the defaults as defined in the {@link javax.usb.UsbIrp interface}.
 * Any of the fields may be updated if the default is not appropriate; in most cases
 * the {@link #getData() data} will be the only field that needs to be {@link #setData(byte[]) set}.
 * <p>
 * When the implementation finishes processing this (successfully or not), it must set the
 * {@link #getActualLength() actual length} via its {@link #setActualLength(int) setter}.
 * If unsuccessful, the implementation must set the
 * {@link #getUsbException() UsbException} via its {@link #setUsbException(UsbException) setter}.
 * The implementation will then set this {@link #complete() complete}.
 * @author Dan Streetman
 */
public class DefaultUsbIrp implements UsbIrp
{
	/**
	 * Constructor.
	 */
	public DefaultUsbIrp() { }

	/**
	 * Constructor.
	 * @param data The data.
	 */
	public DefaultUsbIrp(byte[] data)
	{
		setData(data);
	}

	/**
	 * Constructor.
	 * @param data The data.
	 * @param offset The offset.
	 * @param length The length.
	 * @param shortPacket The Short Packet policy.
	 */
	public DefaultUsbIrp(byte[] data, int offset, int length, boolean shortPacket)
	{
		setData(data);
		setOffset(offset);
		setLength(length);
		setAcceptShortPacket(shortPacket);
	}

	/**
	 * Get the data.
	 * @return The data.
	 */
	public byte[] getData() { return data; }

	/**
	 * Set the data.
	 * <p>
	 * If the data is null, an empty byte[] will be substituted.
	 * @param d The data.
	 */
	public void setData( byte[] d )
	{
		if (null == d)
			data = new byte[0];
		else
			data = d;
	}

	/**
	 * Get the offset.
	 * <p>
	 * The behavior is defined in {@link javax.usb.UsbIrp#getOffset() the interface}.
	 * @return The offset.
	 */
	public int getOffset() { return 0 > offset ? 0 : offset; }

	/**
	 * Set the offset.
	 * <p>
	 * The behavior is defined in {@link javax.usb.UsbIrp#setOffset(int) the interface}.
	 * @param o The offset.
	 */
	public void setOffset(int o) { offset = o; }

	/**
	 * Get the length.
	 * <p>
	 * The behavior is defined in {@link javax.usb.UsbIrp#getLength() the interface}.
	 * @return The length.
	 */
	public int getLength()
	{
		if (0 > length) {
			int dynamicLength = getData().length - getOffset();
			return 0 > dynamicLength ? 0 : dynamicLength;
		} else {
			return length;
		}
	}

	/**
	 * Set the length.
	 * <p>
	 * The behavior is defined in {@link javax.usb.UsbIrp#setLength(int) the interface}.
	 * @param l The length.
	 */
	public void setLength(int l) { length = l; }

	/**
	 * Get the actual length.
	 * <p>
	 * The behavior is defined in {@link javax.usb.UsbIrp#getActualLength() the interface}.
	 * @return The actual length.
	 */
	public int getActualLength()
	{
		if (0 > actualLength)
			return 0;
		else if (getData().length < actualLength)
			return getData().length;
		else
			return actualLength;
	}

	/**
	 * Set the actual length.
	 * @param l The actual length.
	 */
	public void setActualLength(int l) throws IllegalArgumentException { actualLength = l; }

	/**
	 * If a UsbException occurred.
	 * @return If a UsbException occurred.
	 */
	public boolean isUsbException() { return ( null != getUsbException() ); }

	/**
	 * Get the UsbException.
	 * @return The UsbException, or null.
	 */
	public UsbException getUsbException() { return usbException; }

	/**
	 * Set the UsbException.
	 * @param exception The UsbException.
	 */
	public void setUsbException( UsbException exception ) { usbException = exception; }

	/**
	 * Get the Short Packet policy.
	 * @return The Short Packet policy.
	 */
	public boolean getAcceptShortPacket() { return acceptShortPacket; }

	/**
	 * Set the Short Packet policy.
	 * @param accept The Short Packet policy.
	 */
	public void setAcceptShortPacket( boolean accept ) { acceptShortPacket = accept; }

	/**
	 * If this is complete.
	 * @return If this is complete.
	 */
	public boolean isComplete() { return complete; }

	/**
	 * Set this as complete (or not).
	 * @param c If this is complete (or not).
	 */
	public void setComplete( boolean b ) { complete = b; }

	/**
	 * Complete this submission.
	 * <p>
	 * This will:
	 * <ul>
	 * <li>{@link #setComplete(boolean) Set} this {@link #isComplete() complete}.</li>
	 * <li>Notify all {@link #waitUntilComplete() waiting Threads}.</li>
	 * </ul>
	 */
	public void complete()
	{
		setComplete(true);
		synchronized(waitLock) { waitLock.notifyAll(); }
	}

	/**
	 * Wait until {@link #isComplete() complete}.
	 * <p>
	 * This will block until this is {@link #isComplete() complete}.
	 */
	public void waitUntilComplete()
	{
		if (!isComplete()) {
			synchronized ( waitLock ) {
				while (!isComplete()) {
					try { waitLock.wait(); }
					catch ( InterruptedException iE ) { }
				}
			}
		}
	}

	/**
	 * Wait until {@link #isComplete() complete}, or the timeout has expired.
	 * <p>
	 * This will block until this is {@link #isComplete() complete},
	 * or the timeout has expired.  The timeout is ignored if it is
	 * 0 or less.
	 * @param timeout The maximum number of milliseconds to wait.
	 */
	public void waitUntilComplete( long timeout )
	{
		if (0 >= timeout) {
			waitUntilComplete();
			return;
		}

		long startTime = System.currentTimeMillis();

		if (!isComplete()) {
			synchronized ( waitLock ) {
				if (!isComplete() && ((System.currentTimeMillis() - startTime) < timeout)) {
					try { waitLock.wait( timeout ); }
					catch ( InterruptedException iE ) { }
				}
			}
		}
	}

	protected byte[] data = new byte[0];
	protected boolean complete = false;
	protected boolean acceptShortPacket = true;
	protected int offset = -1;
	protected int length = -1;
	protected int actualLength = -1;
	protected UsbException usbException = null;
	private Object waitLock = new Object();
}
