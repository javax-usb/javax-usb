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
 * At a minimum, this can be submitted after its data is set.  If no
 * other changes are made to the UsbIrp then the submission will behave
 * exactly as if the data was submitted by
 * {@link javax.usb.UsbPipe#syncSubmit(byte[]) UsbPipe.syncSubmit(byte[] data)} or
 * {@link javax.usb.UsbPipe#asyncSubmit(byte[]) UsbPipe.asyncSubmit(byte[] data)}.
 * <p>
 * See the USB 1.1 specification sec 5.3.2 for some details on their description of Irps.
 * The Irp defined in this API has more than is mentioned in the USB 1.1 specification;
 * all extra fields or methods are guaranteed to be provided on all platforms, either
 * in the Java subsystem implementation or by the native USB implementation.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface UsbIrp
{
	/**
	 * This number represents a unique number for this UsbIrp.
	 * <p>
	 * This number will be unique for this UsbIrp.
	 * The number will not change as long as the UsbIrp
	 * is valid (not {@link #recycle() recycled}).  Nothing else
	 * is specified or gauranteed.
	 * @return a unique number for this UsbIrp.
	 */
	public long getNumber();

	/**
	 * Get the sequence number assigned to the associated submission.
	 * <p>
	 * This is assigned by the UsbPipe every time this UsbIrp is
	 * submitted.  After the UsbPipe assigns the number, it will remain constant
	 * during submission, until the next time the UsbIrp is submitted by an
	 * application.
	 * @return the sequence number of this submission.
	 */
	public long getSequenceNumber();

	/**
	 * Get the UsbPipe this has been submitted on.
	 * <p>
	 * This is only valid after the irp has been submitted,
	 * and is set (by the implementation) when the UsbIrp
	 * is submitted.
	 * @return the UsbPipe associated with the submission
	 */
	public UsbPipe getUsbPipe();

	/**
	 * Get this irp's data.
	 * <p>
	 * This must be set by the user.
	 * @return the data associated with the submission
	 */
	public byte[] getData();

	/**
	 * Set the data to use in the submission.
	 * @param data the data associated with the submission
	 */
	public void setData( byte[] data );

	/**
	 * Get the length of the data actually transferred to/from the target endpoint.
	 * <p>
	 * This is only valid after the irp has completed successfully, which may be
	 * determined with
	 * {@link #isCompleted() isCompleted} and
	 * {@link #isInUsbException() isInUsbException}.  It is set
	 * by the implementation.
	 * <p>
	 * Note that the actual amount of data transferred may be less than the
	 * size of the {@link #setData(byte[]) provided buffer}, ergo the need for this method.
	 * @return the amount of data transferred in this submission.
	 */
	public int getDataLength();

	/**
	 * If this UsbIrp is active (in progress).
	 * <p>
	 * This indicates if this UsbIrp is currently being processed and
	 * is active.  When the UsbIrp is inactive (not being processed),
	 * the owner can modify it in any way.  However, when the UsbIrp
	 * is active (being processed), the UsbIrp should not be modified in any way.
	 * The result of modifying the UsbIrp while active is undefined.
	 */
	public boolean isActive();

   /**
	 * If this submission associated with this irp is completed.
	 * <p>
	 * This is false until the UsbIrp has completed.
	 * @return if this submit is done.
	 */
	public boolean isCompleted();

	/**
	 * If a UsbException occured.
	 * <p>
	 * The implementation will set this if appropriate when the UsbIrp completes.
	 * If that UsbIrp is submitted the implementation will clear the old UsbException
	 * (and this along with it).
	 * @return if a UsbException occured during submission.
	 */
	public boolean isInUsbException();

	/**
	 * Wait (block) until this submission is completed.
	 * <p>
	 * It is guaranteed that the submission will be complete when this
	 * this method returns.
	 * <p>
	 * The implementation may or may not use synchronization on the UsbIrp.
	 */
	public void waitUntilCompleted();

	/**
	 * Wait (block) until this submission is completed.
	 * <p>
	 * This method will return when at least one of the following
	 * conditions are met:
	 * <ul>
	 * <li>The submission is complete.</li>
	 * <li>The specified amount of time has elapsed.</li>
	 * </ul>
	 * The implementation may take some additional processing time
	 * beyond the specified timeout, but should attempt to keep the
	 * additional time to a minumim.  This method will not return
	 * due to timeout until at least the specified amount of time
	 * has passed.
	 * <p>
	 * The implementation may or may not use synchronization on the UsbIrp.
	 * @param timeout number of milliseconds to wait before giving up
	 */
	public void waitUntilCompleted( long timeout );

	/**
	 * Get the UsbException that occured during submission.
	 * <p>
	 * The implementation will set this to the generate UsbException
	 * (if appropriate) when the UsbIrp completes.  If that UsbIrp
	 * is then submitted again the implementation will clear the old
	 * UsbException.
	 * @return any javax.usb.UsbException the submission may have caused.
	 */
	public UsbException getUsbException();

	/**
	 * If short packets should be accepted.
	 * <p>
	 * See the USB 1.1 specification sec 5.3.2 for details on short packets and
	 * short packet detection.
	 * If short packets are accepted (true), a short packet will be ignored.
	 * If short packets are not accepted (false), a short packet will generate
	 * an error (UsbException) and result in a failed submission (and possibly
	 * stall the pipe or halt the endpoint).
	 * <p>
	 * This is set by the application and will never be changed by the implementation.
	 * @return if a short packet during this submission should be accepted (no error).
	 */
	public boolean getAcceptShortPacket();

	/**
	 * Set if short packets should be accepted.
	 * <p>
	 * See getAcceptShortPacket() for details on short packets.
	 * <p>
	 * This is will never be called by the implementation.
	 * @param accept if a short packet during this submission should be accepted (no error).
	 */
	public void setAcceptShortPacket( boolean accept );

	/**
	 * Recycle this UsbIrp.
	 * <p>
	 * This should be called only when the UsbIrp is no longer needed.
	 * No fields or methods on this irp should be called after this method.
	 * <p>
	 * <b>This should absolutely not be called while the irp is in progress!</b>
	 */
	public void recycle();

}
