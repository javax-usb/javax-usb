package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

/**
 * Defines a USB Irp (I/O Request Packet).
 * <p>
 * Some USB communication requires addiitonal metadata that describes how the actual
 * data should be handled when being transferred.  This UsbIrp encapsulates the
 * actual data buffer, as well as other metadata that gives the user more
 * control and knowledge over how the data is handled.
 * <p>
 * Before submitting this, at least some of these (depending on UsbIrp implementation) must be performed:
 * <ul>
 * <li>The {@link #getData() data} must be {@link #setData(byte[]) set}; the default is an empty byte[].</li>
 * <li>The {@link #getOffset() data offset}, may be {@link #setOffset(int) set}; the default is no offset.</li>
 * <li>The {@link #getLength() data length} may be {@link #setLength(int) set}; the default is the full data length (see {@link #getLength() getLength()} for details).</li>
 * <li>The {@link #getAcceptShortPacket() Short Packet policy} may be {@link #setAcceptShortPacket(boolean) set}; the default is true.</li>
 * <li>The {@link #getUsbException() UsbException} must be null (and {@link #isUsbException() isUsbException} must be false).</li>
 * <li>The {@link #isComplete() complete state} must be false.</li>
 * </ul>
 * Any UsbIrp implementation must behave as specified in this interface's documentation, including the specified defaults.
 * <p>
 * The javax.usb implementation will set the {@link #getActualLength() data length} and, if unsuccessful, the
 * {@link #getUsbException() UsbException}, after processing.  Finally, it will call {@link #complete() complete}.
 * <p>
 * See the USB 1.1 specification section 5.3.2 for details on USB IRPs.
 * The IRP defined in this API has more than is mentioned in the USB 1.1 specification.
 * @author Dan Streetman
 */
public interface UsbIrp
{
	/**
	 * Get the data.
	 * <p>
	 * This defaults to an empty byte[].  This will never return null,
	 * if there is no data it will return an empty byte[].
	 * @return The data.
	 */
	public byte[] getData();

	/**
	 * Get the starting offset of the data.
	 * <p>
	 * This indicates the starting byte in the {@link #getData() data}.
	 * <p>
	 * This defaults to no offset (0).
	 * @return The offset to use.
	 */
	public int getOffset();

	/**
	 * The amount of data to transfer.
	 * <p>
	 * This should be set to the amount of data to transfer.
	 * <p>
	 * This defaults to returning a dynamic value based on the current
	 * {@link #getOffset() offset} and {@link #getData() data}.length;
	 * specifically, this will return
	 * <code>getData().length - getOffset()</code>.  This default behavior
	 * allows for the most common case, where the length should be all data in the buffer,
	 * to be correct without extra work.  Note that if data.length - offset is negative,
	 * this will return 0; in no case will this ever return a negative number.
	 * <p>
	 * If the length has been {@link #setLength(int) set} to a non-negative number, this will
	 * return that regardless of the current offset or data length.
	 * @return The amount of data to transfer.
	 */
	public int getLength();

	/**
	 * The amount of data that was transferred.
	 * <p>
	 * This will never return a negative number, nor greater than
	 * {@link #getData() getData()}.length.
	 * @return The amount of data that was transferred.
	 */
	public int getActualLength();

	/**
	 * Set the data.
	 * <p>
	 * A null parameter is converted to an empty byte[].
	 * @param data The data.
	 */
	public void setData(byte[] data);

	/**
	 * Set the offset.
	 * <p>
	 * A negative value is converted to 0.
	 * @param offset The offset.
	 */
	public void setOffset(int offset);

	/**
	 * Set the amount of data to transfer.
	 * <p>
	 * If the value is 0 or greater, that specific value is used.
	 * A negative value causes {@link #getLength() getLength()} to resume
	 * its default behavior.
	 * @param length The amount of data to transfer.
	 */
	public void setLength(int length);

	/**
	 * Set the amount of data that was transferred.
	 * <p>
	 * The implementation will set this to the amount of data
	 * actually transferred.  The implementation will set this
	 * before calling {@link #complete() complete}, regardless of
	 * whether the submission was successful or not.
	 * @param length The amount of data that was transferred.
	 */
	public void setActualLength(int length);

	/**
	 * If a UsbException occured.
	 * @return If a UsbException occurred.
	 */
	public boolean isUsbException();

	/**
	 * Get the UsbException.
	 * <p>
	 * If no UsbException occurred, this returns null.
	 * @return The UsbException, or null.
	 */
	public UsbException getUsbException();

	/**
	 * Set the UsbException.
	 * @param usbException The UsbException.
	 */
	public void setUsbException(UsbException usbException);

	/**
	 * If short packets should be accepted.
	 * <p>
	 * See the USB 1.1 specification sec 5.3.2 for details on short packets and
	 * short packet detection.
	 * If short packets are accepted (true), a short packet indicates the end of data.
	 * If short packets are not accepted (false), a short packet will generate
	 * an UsbException.
	 * <p>
	 * This is set by the application and will never be changed by the implementation.
	 * @return If short packects should be accepted.
	 */
	public boolean getAcceptShortPacket();

	/**
	 * Set if short packets should be accepted.
	 * @param accept If short packets should be accepted.
	 */
	public void setAcceptShortPacket( boolean accept );

	/**
	 * If this has completed.
	 * <p>
	 * This must be false before use.
	 * @return If the has completed.
	 */
	public boolean isComplete();

	/**
	 * Set this as complete.
	 * <p>
	 * This is the last method the implementation calls; it indicates the UsbIrp has completed.  
	 * The implementation will set the {@link #setActualLength(int) actual length},
	 * even if the submission was unsuccessful, before calling this.
	 * The implementation will {@link #setUsbException(UsbException) set the UsbException},
	 * if appropriate, before calling this.
	 * After calling this {@link #isComplete() isComplete} will return true.
	 */
	public void complete();

	/**
	 * Wait until {@link #isComplete() complete}.
	 * <p>
	 * This will block until this is {@link #isComplete() complete}.
	 */
	public void waitUntilComplete();

	/**
	 * Wait until {@link #isComplete() complete}, or the timeout has expired.
	 * <p>
	 * This will block until this is {@link #isComplete() complete},
	 * or the timeout has expired.  The timeout is ignored if it is
	 * 0 or less, i.e. this will behave as the {@link #waitUntilComplete() no-timeout method}.
	 * @param timeout The maximum number of milliseconds to wait.
	 */
	public void waitUntilComplete( long timeout );
}
