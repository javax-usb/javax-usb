package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

/**
 * Defines super interface for all the USB operations (standard, class and vendor) to a device
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface UsbOperations
{
    //-------------------------------------------------------------------------
    // Public request methods
    //

	/**
	 * Performs a synchronous operation by submitting the Request object
	 * @param request the Request object that is used for this submit
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public void syncSubmit( Request request ) throws RequestException;

	/**
	 * Performs a synchronous operation by submitting all the Request objects in the bundle.
	 * No other request submission can be overlapped.  This means that the Request object in the
	 * bundle are guaranteed to be sent w/o interruption.
	 * @param requestBundle the RequestBundle object that is used for this submit
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public void syncSubmit( RequestBundle requestBundle ) throws RequestException;

	/**
	 * Performs a asynchronous operation by submitting the Request object
	 * @param request the Request object that is used for this submit
	 * @return a UsbOperations.SubmitResult object used to track the submission
	 * @exception javax.usb.RequestException if something goes wrong submitting the request for this operation
	 */
	public UsbOperations.SubmitResult asyncSubmit( Request request ) throws RequestException;

	//-------------------------------------------------------------------------
	// Inner interfaces
	//

	/**
	 * Defines a SubmitResult interface used as a future object for async submission
	 * <i>NOTE: this SubmitResult is modeled and is similar to the UsbPipe.SubmitResult interface</i>
	 * @see javax.usb.UsbPipe.SubmitResult
	 * @author E. Michael Maximilien
	 * @author Daniel D. Streetman
	 * @version 1.0.0
	 */
	public interface SubmitResult
	{
		/**
		 * This number represents a unique (per UsbDevice) sequential number for this submission.
		 * <p>
		 * The number is unique per specific UsbDevice.  The initial number is not specified,
		 * but the number will always be positive (greater than zero).  The number will be
		 * incremented by one (1) for each submission on a specific UsbPipe where a SubmitResult
		 * is generated.
		 * @return a unique (per UsbDevice) number for this submission.
		 */
		public long getNumber();

		/**
		 * Get the Request whose usbmission generated this SubmitResult.
		 * @return the Request associated with the submission
		 */
		public Request getRequest();
	
		/**
		 * Get the data from the submission associated with this SubmitResult.
		 * @return the data associated with the submission
		 */
		public byte[] getData();
	
		/**
		 * Get the length of the data actually transferred via the Request
		 * <p>
		 * This is only valid after the submission completes successfully,
		 * which may be determined by isCompleted() and isInUsbException().
		 * @return the amount of data transferred in this submission
		 */
		public int getDataLength();
	
		/**
		 * If the submission associated with this SubmitResult is complete.
		 * @return true if this submit is done
		 */
		public boolean isCompleted();
	
		/**
		 * Wait (block) until this submission is completed.
		 * <p>
		 * It is guaranteed that the submission will be complete when this
		 * this method returns.
		 * <p>
		 * The implementation may or may not use synchronization on the SubmitResult.
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
		 * The implementation may or may not use synchronization on the SubmitResult.
		 * @param timeout the number of milliseconds to wait before giving up
		 */
		public void waitUntilCompleted( long timeout );
	
		/**
		 * Get the UsbException that occured during submission.
		 * @return any javax.usb.UsbException the submission may have caused
		 */
		public UsbException getUsbException();
	
		/**
		 * If a UsbException occured.
		 * @return true if this SubmitResult has a UsbException
		 */
		public boolean isInUsbException();

		/**
		 * Recycle this SubmitResult.
		 * <p>
		 * This should be called when the SubmitResult is no longer needed.
		 * No fields or methods on this SubmitResult should be called after this method.
		 */
		public void recycle();
	}
}
