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
 * Before submitting this, the data must be {@link #setData(byte[]) set}.
 * <p>
 * See the USB 1.1 specification section 5.3.2 for details on USB IRPs.
 * The IRP defined in this API has more than is mentioned in the USB 1.1 specification;
 * all extra fields or methods are guaranteed to be provided on all platforms, either
 * in the Java subsystem implementation or by the native USB implementation.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public interface UsbIrp
{
	/**
	 * Get the data.
	 * @return The data.
	 */
	public byte[] getData();

	/**
	 * Set the data.
	 * @param data The data.
	 */
	public void setData( byte[] data );

	/**
	 * Get the amount of data actually transferred.
	 * <p>
	 * This is only valid after the irp has {@link #isComplete() completed}.
	 * <p>
	 * The actual amount of data transferred may be less than the
	 * size of the {@link #getData() provided buffer}.
	 * @return The amount of data transferred.
	 */
	public int getDataLength();

	/**
	 * Set the data length.
	 * @param length The data length.
	 */
	public void setDataLength(int length);

   /**
	 * If this has completed.
	 * @return If the has completed.
	 */
	public boolean isComplete();

	/**
	 * Set this as completed.
	 * @param completed If this is completed.
	 */
	public void setComplete(boolean complete);

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
	public interface ControlUsbIrp extends UsbIrp
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
		 * Get the wLength.
		 * <p>
		 * This returns the length of the {@link #getData() data}.
		 * @return The wLength.
		 */
		public short getLength();

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
