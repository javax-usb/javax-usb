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
 * The data must be set either via its {@link #setData(byte[]) setter} or the constructor.
 * The {@link #getOffset() offset} defaults to 0, which is usually correct.
 * The {@link #getLength() length} must be set, as it defaults to 0, which is rarely correct.
 * The {@link #getAcceptShortPacket() defaults to true, which is usually correct.
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
	 * @return The offset.
	 */
	public int getOffset() { return offset; }

	/**
	 * Set the offset.
	 * @param o The offset.
	 * @exception IllegalArgumentException If the offset is negative.
	 */
	public void setOffset(int o) throws IllegalArgumentException
	{
		if (0 > o)
			throw new IllegalArgumentException("Offset cannot be negative.");

		offset = o;
	}

	/**
	 * Get the length.
	 * @return The length.
	 */
	public int getLength() { return length; }

	/**
	 * Set the length.
	 * @param l The length.
	 * @exception IllegalArgumentException If the length is negative.
	 */
	public void setLength(int l) throws IllegalArgumentException
	{
		if (0 > l)
			throw new IllegalArgumentException("Length cannot be negative.");

		length = l;
	}

	/**
	 * Get the actual length.
	 * @return The actual length.
	 */
	public int getActualLength() { return actualLength; }

	/**
	 * Set the actual length.
	 * @param l The actual length.
	 * @exception IllegalArgumentException If the actual length is negative.
	 */
	public void setActualLength(int l) throws IllegalArgumentException
	{
		if (0 > l)
			throw new IllegalArgumentException("Actual length cannot be negative.");

		actualLength = l;
	}

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
