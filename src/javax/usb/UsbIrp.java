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
 * Before submitting this, several steps must be taken:
 * <ul>
 * <li>The {@link #getData() data} must be {@link #setData(byte[]) set}.</li>
 * <li>The {@link #getLength() data length} must be {@link #setLength(int) set}.</li>
 * <li>The {@link #getOffset() data offset}, if non-zero, must be {@link #setOffset(int) set}.</li>
 * <li>The {@link #isComplete() complete state} must be false; how this is set (or reset) is implementation-dependent.</li>
 * </ul>
 * The implementation will set the {@link #getActualLength() data length}.
 * <p>
 * See the USB 1.1 specification section 5.3.2 for details on USB IRPs.
 * The IRP defined in this API has more than is mentioned in the USB 1.1 specification;
 * all extra fields or methods are guaranteed to be provided on all platforms, either
 * in the Java subsystem implementation or by the native USB implementation.
 * @author Dan Streetman
 */
public interface UsbIrp
{
	/**
	 * Get the data.
	 * @return The data.
	 */
	public byte[] getData();

	/**
	 * Get the starting offset of the data.
	 * <p>
	 * This indicates the starting byte in the {@link #getData() data}.
	 * @return The offset to use.
	 */
	public int getOffset();

	/**
	 * The amount of data to transfer.
	 * <p>
	 * This should be set to the amount of data to transfer.
	 * @return The amount of data to transfer.
	 */
	public int getLength();

	/**
	 * The amount of data that was transferred.
	 * <p>
	 * The implementation will set this to the amount of data
	 * actually transferred.
	 * @return The amount of data that was transferred.
	 */
	public int getActualLength();

	/**
	 * Set the data.
	 * @param data The data.
	 */
	public void setData(byte[] data);

	/**
	 * Set the offset.
	 * @param offset The offset.
	 */
	public void setOffset(int offset);

	/**
	 * Set the amount of data to transfer.
	 * @param length The amount of data to transfer.
	 */
	public void setLength(int length);

	/**
	 * Set the amount of data that was transferred.
	 * <p>
	 * The implementation will set this to the amount of data
	 * actually transferred.  The implementation <b>must</b> set this
	 * before calling {@link complete() complete}, regardless of
	 * whether the submission was successful or not.
	 * @param length The amount of data that was transferred.
	 */
	public void setActualLength(int length);

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
	 * The implementation <b>must</b> {@link #setLength(int) set the length},
	 * even if the submission was unsuccessful, before calling this.
	 * The implementation will {@link #setUsbException(UsbException) set the UsbException},
	 * if appropriate, before calling this.
	 * After calling this {@link #isComplete() isComplete} will return true.
	 */
	public void complete();

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
	 * UsbIrp for use on a Control pipe.
	 * <p>
	 * This contains additional Setup parameters, which Control transfers require.
	 */
	public static interface ControlUsbIrp extends UsbIrp
	{
		/**
		 * Get the bmRequestType.
		 * @return The bmRequestType.
		 */
		public byte getRequestType();

		/**
		 * Get the bRequest.
		 * @return The bRequest.
		 */
		public byte getRequest();

		/**
		 * Get the wValue.
		 * @return The wValue.
		 */
		public short getValue();

		/**
		 * Get the wIndex.
		 * @return The wIndex.
		 */
		public short getIndex();

		/**
		 * Set the bmRequestType.
		 * @param bmRequestTyp The bmRequestType.
		 */
		public void setRequestType(byte bmRequestType);

		/**
		 * Set the bRequest.
		 * @param bRequest The bRequest.
		 */
		public void setRequest(byte bRequest);

		/**
		 * Set the wValue.
		 * @param wValue The wValue.
		 */
		public void setValue(short wValue);

		/** 
		 * Set the wIndex.
		 * @param wIndex The wIndex.
		 */
		public void setIndex(short wIndex);
	}
}
