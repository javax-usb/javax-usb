package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import javax.usb.event.UsbPipeListener;

/**
 * Defines a USB pipe.
 * <p>
 * See the USB 1.1 specification sec 5.3.2 for details on USB pipes.
 * This pipe is a 'logical' pipe.  It maintains a state (open, closed, busy, etc)
 * and its 'lifetime' is from device connection to device removal.
 * UsbPipes and {@link javax.usb.Request Requests} are the only methods
 * of communication with a UsbDevice.  Each pipe is connected to a specific endpoint
 * on a device (Control-type pipes are 'special', they are connected to 2 endpoints
 * with the same number - and descriptor - with different 'direction' bits).  Data
 * flows in the direction specified by the associated endpoint (except for Control-type
 * pipes, which specifiy the direction as part of each submission).
 * <p>
 * No guarantee of synchronization is made.  If synchronization is desired it should be
 * provided by the application.  However, the implementation may use synchronization and
 * may actually be Thread-safe; but it is not guaranteed.
 * <p>
 * This pipe's configuration and interface setting must be active to use this pipe.
 * Any attempt to use a UsbPipe belonging to an inactive configuration or interface setting
 * will throw a NotActiveException.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbPipe
{
	/**
	 * Open this UsbPipe.
	 * <p>
	 * The pipe cannot be used for communication until it is open.
	 * <p>
	 * The implementation should, to whatever extent the platform allows,
	 * try to ensure the pipe is usable (not in error) before returning
	 * successfully.
	 * @throws javax.usb.UsbException if the UsbPipe could not be opened
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public void open() throws UsbException;

	/**
	 * Close this UsbPipe.
	 * <p>
	 * The Pipe can only be closed while it is {@link #isIdle() idle}
	 * or {@link #isInError() in error}, not while {@link #isBusy() busy}.
	 * To close the pipe while submissions are pending, all submissions must
	 * be {@link #abortAllSubmissions() aborted}.
	 * @throws javax.usb.UsbException if the UsbPipe could not be closed.
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public void close() throws UsbException;

	/**
	 * If this pipe is active.
	 * <p>
	 * This pipe is active only if it belongs to an
	 * {@link javax.usb.UsbConfig#isActive() active configuration} and
	 * {@link javax.usb.UsbInterface#isActive() interface setting}, otherwise it is inactive.
	 * This UsbPipe cannot be used at all if inactive, and no other state methods
	 * are valid.
	 * @return if this UsbPipe is active.
	 */
	public boolean isActive();

	/**
	 * If this pipe is open.
	 * <p>
	 * This is only valid if the UsbPipe is {@link #isActive() active}.
	 * This is true after a sucessful {@link #open() open}
	 * until a successful {@link #close() close}.
	 * @return if this UsbPipe is open.
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public boolean isOpen();

	/**
	 * If this pipe is closed.
	 * <p>
	 * This is only valid if the UsbPipe is {@link #isActive() active}.
	 * This is the inital state of the pipe.
	 * The pipe leaves this state after a successful {@link #open() open} and
	 * can only return by a successful {@link #close() close}.
	 * @return if this UsbPipe is closed.
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public boolean isClosed();

	/**
	 * If this pipe is idle.
	 * <p>
	 * This is only valid if the UsbPipe is {@link #isActive() active}.
	 * This is true after a successful {@link #open() open} call until
	 * a successful data submission through any of the various methods.
	 * After completion of all pending submissions this is true again,
	 * until a successful {@link #close() close} or more data is submitted.
	 * @return if this UsbPipe is idle.
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public boolean isIdle();

	/**
	 * If this pipe is busy.
	 * <p>
	 * This is only valid if the UsbPipe is {@link #isActive() active}.
	 * This is true after a successful {@link #open() open} and submission of data.
	 * While there are submission(s) in progress this is true.
	 * After completion of all pending submissions this is false.
	 * @return if this UsbPipe is busy.
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public boolean isBusy();

	/**
	 * If this pipe is in error.
	 * <p>
	 * This is only valid if the UsbPipe is {@link #isActive() active}.
	 * This is only true if a serious (persistent) error occurs that
	 * needs correction.  For example, if the pipe stalls, or the device is
	 * disconnected.  Nothing can be done to this pipe while in the error state,
	 * except closing the pipe.  The solution for correcting error conditions
	 * is specific to {@link #getErrorCode() the error condition}.
	 * The pipe must be {@link #close() closed} and {@link #open() re-opened}
	 * (in addition to actually correcting the error) to be able to use the
	 * pipe again.
	 * @return if this UsbPipe is in an error state.
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public boolean isInError();

	/**
	 * Get the error code associated with the error state.
	 * <p>
	 * This is only valid if the UsbPipe is {@link #isInError() in error}.
	 * This indicates the reason for the current error state.
	 * <p>
	 * If the UsbPipe is active but not in an error state, this should return 0.
	 * @return an error code indicating the reason for the current error state, or 0.
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public int getErrorCode();

	/**
	 * Get the max packet size for this pipe.
	 * <p>
	 * Maximum packet sizes are generally not used at this level
	 * in the USB stack; lower layers in the javax.usb implementation
	 * and/or the platform's USB stack may segment a submission into
	 * max-packet-sized 'packets' which are then transported on the USB 'wire'.
	 * See the USB 1.1 specification sec 5.3.2 for more details on max packet
	 * sizes and individual packets.
	 * @see javax.usb.UsbEndpoint#getMaxPacketSize()
	 * @return the max packet size of this pipe's endpoint
	 */
	public short getMaxPacketSize();

	/**
	 * Get the address of this pipe's endpoint.
	 * @see javax.usb.UsbEndpoint#getEndpointAddress()
	 * @return the address of this pipe's endpoint
	 */
	public byte getEndpointAddress();

	/**
	 * Get the pipe's type.
	 * <p>
	 * The pipe's type is determined by its assocaited endpoint.
	 * @see javax.usb.UsbEndpoint#getType()
	 * @return this pipe's type
	 */
	public byte getType();

	/**
	 * Get this pipe's UsbEndpoint.
	 * @return the UsbEndpoint associated with this UsbPipe
	 */
	public UsbEndpoint getUsbEndpoint();

	/**
	 * Get the sequence number.
	 * <p>
	 * This sequence number can be used to track submissions.
	 * <p>
	 * The number is always greater than zero, but the initial number is
	 * not specified.  If the sequence number reaches its
	 * maximum value, it will wrap to one.
	 * <p>
	 * Every submission, synchronous or asynchronous, byte[] or
	 * UsbIrp, will increment the sequence number by exactly one.
	 * Each submission is associated with the current sequence number
	 * when it is submitted; e.g. if the UsbPipe's current sequence number
	 * is 13, a submission will then be given the sequence number of 13
	 * and the UsbPipe's sequence number will be incremented to 14.
	 * <p>
	 * The sequence number is available from
	 * {@link javax.usb.UsbIrp#getSequenceNumber() UsbIrps} and
	 * {@link javax.usb.UsbPipe.SubmitResult#getSequenceNumber() SubmitResults}.
	 * @return this UsbPipe's current sequence number.
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public long getSequenceNumber();

	/**
	 * Synchonously submit a byte[] to the UsbPipe.
	 * <p>
	 * This can be used for input and output.
	 * This may only be called when the pipe is idle or busy.
	 * There is no guarantee that the javax.usb implementation or
	 * platform USB stack supports multiple (queued) submissions.
	 * This will block until the operation is completed,
	 * either sucessfully or with an error.
	 * <p>
	 * The return value will indicate the number of bytes sucessfully transferred
	 * to or from the target endpoint (depending on direction) <b>only if</b> the operation was successful.
	 * The return value will never exceed the total size of the provided buffer.
	 * Nothing is guaranteed if the operation was not sucessful except the UsbException should
	 * accurately reflect the cause of the error.
	 * <p>
	 * Short packets are accepted.  There is no way to disable short packet acceptance using this method.
	 * See the USB 1.1 specification sec 5.3.2 for details on short packets and short packet detection.
	 * <p>
<!-- This will not be true after implementing UsbPipe Policies -->
	 * The javax.usb implementation or platform's USB stack may impose minimum or maximum
	 * limits on the buffer size, which may vary according to the pipe type.
	 * <p>
	 * Note that the words 'submission' and 'submit' are used somewhat vaguely; these methods
	 * are methods to <i>submit</i> (verb) a <i>submission</i> (noun).  However, the asynchronous submission
	 * methods only wait until the 'submission' has been passed to the lower layers for actual
	 * execution over the 'wire'.  The synchronous submission methods wait until the entire
	 * submission is complete (all data has been transferred).
	 * @param data the buffer to use for this operation
	 * @return the number of bytes actually transferred in this operation
	 * @throws javax.usb.UsbException if there was an error during the submission
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public int syncSubmit( byte[] data ) throws UsbException;

	/**
	 * Asynchonously submit a byte[] to the UsbPipe.
	 * <p>
	 * This can be used for input and output.
	 * This may only be called when the pipe is idle or busy.
	 * There is no guarantee that the javax.usb implementation or
	 * platform USB stack supports multiple (queued) submissions.
	 * This will block until the operation is submitted (not completed),
	 * either sucessfully or with an error.
	 * <p>
	 * The SubmitResult.getDataLength() value will indicate the number of bytes sucessfully transferred
	 * to or from the target endpoint (depending on direction) <b>only if</b> the operation was successful.
	 * The return value will never exceed the total size of the provided buffer.
	 * Nothing is guaranteed if the operation was not sucessful except the UsbException should
	 * accurately reflect the cause of the error.
	 * <p>
	 * Short packets are accepted.  There is no way to disable short packet acceptance using this method.
	 * See the USB 1.1 specification sec 5.3.2 for details on short packets and short packet detection.
	 * <p>
<!-- This will not be true after implementing UsbPipe Policies -->
	 * The javax.usb implementation or platform's USB stack may impose minimum or maximum
	 * limits on the buffer size, which may vary according to the pipe type.
	 * <p>
	 * Note that the words 'submission' and 'submit' are used somewhat vaguely; these methods
	 * are methods to <i>submit</i> (verb) a <i>submission</i> (noun).  However, the asynchronous submission
	 * methods only wait until the 'submission' has been passed to the lower layers for actual
	 * execution over the 'wire'.  The synchronous submission methods wait until the entire
	 * submission is complete (all data has been transferred).
	 * @param data the buffer to use for this operation
	 * @return a UsbPipe.SubmitResult future object associated with this operation
	 * @throws javax.usb.UsbException if there was an error during the submission
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 * @see javax.usb.UsbPipe.SubmitResult
	 */
	public UsbPipe.SubmitResult asyncSubmit( byte[] data ) throws UsbException;

	/**
	 * Synchonously submit a UsbIrp to the UsbPipe.
	 * <p>
	 * This can be used for input and output.
	 * This may only be called when the pipe is idle or busy.
	 * There is no guarantee that the javax.usb implementation or
	 * platform USB stack supports multiple (queued) submissions.
	 * This will block until the operation is completed,
	 * either sucessfully or with an error.
	 * <p>
	 * <strong>WARNING</strong> : If automatic resubmission is being used, this will
	 * not return between resubmissions; i.e. it will only return when
	 * the UsbIrp is completed <strong>without</strong> being resubmitted,
	 * such as when it is aborted or a UsbException occurs, or after
	 * resubmit is manually turned off.
	 * <p>
	 * See javax.usb.UsbIrp for details on using UsbIrps for submissions.
	 * <p>
<!-- This will not be true after implementing UsbPipe Policies -->
	 * The javax.usb implementation or platform's USB stack may impose minimum or maximum
	 * limits on the buffer size, which may vary according to the pipe type.
	 * <p>
	 * Note that the words 'submission' and 'submit' are used somewhat vaguely; these methods
	 * are methods to <i>submit</i> (verb) a <i>submission</i> (noun).  However, the asynchronous submission
	 * methods only wait until the 'submission' has been passed to the lower layers for actual
	 * execution over the 'wire'.  The synchronous submission methods wait until the entire
	 * submission is complete (all data has been transferred).
	 * @param irp the UsbIrp for this submission
	 * @throws javax.usb.UsbException if there was an error during the submission
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public void syncSubmit( UsbIrp irp ) throws UsbException;

	/**
	 * Asynchonously submit a UsbIrp to the UsbPipe.
	 * <p>
	 * This can be used for input and output.
	 * This may only be called when the pipe is idle or busy.
	 * There is no guarantee that the javax.usb implementation or
	 * platform USB stack supports multiple (queued) submissions.
	 * This will block until the operation is submitted (not completed),
	 * either sucessfully or with an error.
	 * <p>
	 * See javax.usb.UsbIrp for details on using UsbIrps for submissions.
	 * <p>
<!-- This will not be true after implementing UsbPipe Policies -->
	 * The javax.usb implementation or platform's USB stack may impose minimum or maximum
	 * limits on the buffer size, which may vary according to the pipe type.
	 * <p>
	 * Note that the words 'submission' and 'submit' are used somewhat vaguely; these methods
	 * are methods to <i>submit</i> (verb) a <i>submission</i> (noun).  However, the asynchronous submission
	 * methods only wait until the 'submission' has been passed to the lower layers for actual
	 * execution over the 'wire'.  The synchronous submission methods wait until the entire
	 * submission is complete (all data has been transferred).
	 * @param irp the UsbIrp for this submission
	 * @throws javax.usb.UsbException if there was an error during the submission
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public void asyncSubmit( UsbIrp irp ) throws UsbException;

	/**
	 * Stop all submissions in progress
	 * <p>
	 * This will abort all submission in progress on the pipe,
	 * and block until all submissions are complete.
	 * The pipe will be idle (or in error) after execution.
	 * @throws javax.usb.NotActiveException if the config or interface setting is not active.
	 */
	public void abortAllSubmissions();

	/**
	 * Adds the listener object for UsbPipeEvents.
	 * <p>
	 * The listener will receive all events generated by this pipe.
	 * @param listener the UsbPipeListener
	 * @see javax.usb.event.UsbPipeDataEvent
	 * @see javax.usb.event.UsbPipeErrorEvent
	 */
	public void addUsbPipeListener( UsbPipeListener listener );

	/**
	 * Removes the listener object.
	 * <p>
	 * The listener will not receive further events from this pipe.
	 * @param listener the UsbPipeListener to remove
	 */
	public void removeUsbPipeListener( UsbPipeListener listener );

	//-------------------------------------------------------------------------
	// Public inner interface
	//

	/**
	 * Defines a Future object for asynchronous submit results.
	 * @author E. Michael Maximilien
	 * @author Dan Streetman
	 * @version 0.0.1 (JDK 1.1.x)
	 */
	public interface SubmitResult
	{
		/**
		 * This number represents a unique (per UsbPipe) sequential number for this submission.
		 * <p>
		 * The number is unique per specific UsbPipe.  The initial number is not specified,
		 * but the number will always be positive (greater than zero).  The number will be
		 * incremented by one (1) for each submission on a specific UsbPipe where a SubmitResult
		 * is generated.
		 * @return a unique (per UsbPipe) number for this submission.
		 */
		public long getNumber();

		/**
		 * Get the sequence number of the associated submission.
		 * <p>
		 * This is similar to {@link #getNumber() getNumber} with the exception
		 * that this is incremented for every submission, not just submissions
		 * that generate a SubmitResult.
		 * See UsbPipe's {@link javax.usb.UsbPipe#getSequenceNumber() getSequenceNumber}
		 * for details.
		 * @return the sequence number assigned to the associated submission.
		 * @see javax.usb.UsbPipe#getSequenceNumber()
		 */
		public long getSequenceNumber();

		/**
		 * Get the pipe tht generated this SubmitResult.
		 * @return the UsbPipe associated with the submission
		 */
		public UsbPipe getUsbPipe();
	
		/**
		 * Get the data from the submission assocaited with this SubmitResult.
		 * @return the data associated with the submission
		 */
		public byte[] getData();
	
		/**
		 * Get the length of the data actually transferred to/from the target endpoint.
		 * <p>
		 * This is only valid after the submission completes successfully,
		 * which may be determined by isCompleted() and isInUsbException().
		 * @return the amount of data transferred in this submission
		 */
		public int getDataLength();
	
		/**
		 * If the submission assocaited with this SubmitResult is complete.
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
