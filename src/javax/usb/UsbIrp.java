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
 * <li>The {@link #getData() data} must be {@link #setData(byte[]) set}.</li>
 * <li>The {@link #getOffset() data offset}, may be {@link #setOffset(int) set}; the default is 0.</li>
 * <li>The {@link #getLength() data length} may be {@link #setLength(int) set}; the default is the full data length.</li>
 * <li>The {@link #getAcceptShortPacket() Short Packet policy} may be {@link #setAcceptShortPacket(boolean) set}; the default is true.</li>
 * <li>The {@link #getUsbException() UsbException} must be null (and {@link #isUsbException() isUsbException} must be false).</li>
 * <li>The {@link #isComplete() complete state} must be false.</li>
 * </ul>
 * Any UsbIrp implementation must behave as specified in this interface's documentation, including the specified defaults.
 * Note that {@link #setData(byte[]) setData()} not only sets the data, but also
 * {@link #setLength(int) sets the length} to the length of the new data buffer.  This behavior allows
 * the default case, where the offset is 0 and the length is the full data buffer, to work correctly.
 * Only the data must be set, the offset and length are set to correct values.  If the offset and/or length
 * should be non-standard values, then they must be set <i>after</i> the data is set.
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
	 * This defaults to an empty byte[].  This will never return null.
	 * @return The data.
	 */
	public byte[] getData();

	/**
	 * Get the starting offset of the data.
	 * <p>
	 * This indicates the starting byte in the {@link #getData() data}.
	 * <p>
	 * This defaults to no offset (0).  This will never be negative.
	 * @return The offset to use.
	 */
	public int getOffset();

	/**
	 * The amount of data to transfer.
	 * <p>
	 * This should be set to the amount of data to transfer.
	 * The default is the full length of the data buffer.
	 * This will never be negative.
	 * <p>
	 * This will be automatically reset every time the data is
	 * {@link #setData(byte[]) set}.
	 * @return The amount of data to transfer.
	 */
	public int getLength();

	/**
	 * The amount of data that was transferred.
	 * @return The amount of data that was transferred.
	 */
	public int getActualLength();

	/**
	 * Set the data.
	 * <p>
	 * <i>Note that the {@link #getLength() length} will be reset
	 * when this is called</i>.  This will {@link #setLength(int) set the length}
	 * to {@link #getData() getData()}.length.
	 * @param data The data.
	 * @exception IllegalArgumentException If the data is null.
	 */
	public void setData(byte[] data);

	/**
	 * Set the offset.
	 * @param offset The offset.
	 * @exception IllegalArgumentException If the offset is negative.
	 */
	public void setOffset(int offset);

	/**
	 * Set the amount of data to transfer.
	 * <p>
	 * This must be set <i>after</i> the data is {@link #setData(byte[]) set}.
	 * @param length The amount of data to transfer.
	 * @exception IllegalArgumentException If the length is negative.
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
	 * @exception IllegalArgumentException If the length is negative.
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
	 * an UsbException.  The default is true.
	 * @return If short packects should be accepted.
	 */
	public boolean getAcceptShortPacket();

	/**
	 * Set if short packets should be accepted.
	 * <p>
	 * This should be set by the application.
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
	 * The implementation will {@link #setActualLength(int) set the actual length},
	 * even if the submission was unsuccessful, before calling this.
	 * The implementation will {@link #setUsbException(UsbException) set the UsbException},
	 * if appropriate, before calling this.
	 * <p>
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
