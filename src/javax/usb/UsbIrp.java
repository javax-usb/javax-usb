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
	 * submitted.  This is <b>not</b> changed for automatic resubmissions.
	 * After the UsbPipe assigns the number, it will remain constant
	 * during submission, even if the UsbIrp resubmits itself,
	 * until the next time the UsbIrp is submitted by an
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
	 * <p>
	 * See
	 * {@link javax.usb.UsbIrp.ResubmitDataCommand#getResubmitData(UsbIrp) ResubmitDataCommand}
	 * and
	 * {@link javax.usb.UsbIrp.ResubmitErrorCommand#continueResubmission(UsbIrp) ResubmitErrorCommand}
	 * for certain exceptions to this.
	 * <p>
	 * This is false when the UsbIrp is initially created, and set to true by the implementation
	 * when submitted; then set to false again (by the implementation) when the UsbIrp completes.
	 */
	public boolean isActive();

   /**
	 * If this submission associated with this irp is completed.
	 * <p>
	 * Note that is false until the UsbIrp has completed, including any
	 * {@link #getResubmit() resubmissions}.  This is set by the implementation.
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
	 * <p>
	 * <strong>WARNING</strong> : If automatic resubmission is being used, this will
	 * not return between resubmissions; i.e. it will only return when
	 * the UsbIrp is completed <strong>without</strong> being resubmitted,
	 * such as when it is aborted or a UsbException occurs, or after
	 * resubmit is manually turned off.
	 * @see #setResubmit( boolean resubmit )
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
	 * <p>
	 * <strong>WARNING</strong> : If automatic resubmission is being used, this will
	 * not return between resubmissions; i.e. it will only return when
	 * the UsbIrp is completed <strong>without</strong> being resubmitted,
	 * such as when it is aborted or a UsbException occurs, or after
	 * resubmit is manually turned off.  Also, the timeout value
	 * applies collectively to all (re)submissions; i.e. the timeout
	 * applies to the total time taken by the submission and subsequent
	 * resubmissions, not just to the time taken by each individual submission.
	 * @param timeout number of milliseconds to wait before giving up
	 * @see #setResubmit( boolean resubmit )
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
	 * If this irp will be automatically resubmitted when completed.
	 * <p>
	 * This indicates to the javax.usb implementation that this irp
	 * should be resubmitted automatically when completed.
	 * No notification will be done to Threads waiting on any of the
	 * waitUntilCompleted() methods.  Events will occur normally, if
	 * indicated by the {@link javax.usb.UsbIrp.EventCommand EventCommand}.
	 * <p>
	 * This flag defaults to off (false), and must be managed by the application.
	 * The implementation will never set this to true; however under some
	 * circumstances it may set this to false (and discontinue resubmission).
	 * This may be changed with setResubmit().
	 * @return if this should be automatically resubmitted
	 */
	public boolean getResubmit();

	/**
	 * Turn on or off automatic resubmission.
	 * <p>
	 * <strong>WARNING</strong> : Turning this on affects waitUntilCompleted().
	 * See getResult() for details on what this flag does.
	 * <p>
	 * This flag defaults to off (false).
	 * @param resubmit if this should be automatically resubmitted
	 * @see #waitUntilCompleted()
	 * @see #getResubmit()
	 */
	public void setResubmit( boolean resubmit );

	/**
	 * Get the ResubmitDataCommand currently in use.
	 * <p>
	 * This will never return null; if an application attempts to
	 * {@link #setResubmitDataCommand(UsbIrp.ResubmitDataCommand) setResubmitDataCommand(null)},
	 * the implementation should then return its default command (which performs the default
	 * ResubmitDataCommand action).
	 * @return the ResubmitDataCommand in use.
	 */
	public UsbIrp.ResubmitDataCommand getResubmitDataCommand();

	/**
	 * Set the ResubmitDataCommand to use.
	 * <p>
	 * The default action is to copy
	 * the data from the old byte[] into
	 * a new byte[] of the same size (this may be
	 * accomplished by a 'default' ResubmitDataCommand generated
	 * by the implementation).
	 * <p>
	 * ResubmitDataCommand.getResubmitData()
	 * will be called between completion of one
	 * UsbIrp and it resubmission, and the byte[] returned by
	 * will be used for the resubmission's byte[].
	 * <p>
	 * Setting this to null will not cause
	 * {@link #getResubmitDataCommand() getResubmitDataCommand()} to
	 * return null, however it does guarantee that the implementation
	 * will revert to the default behavior for this command.  The implementation
	 * should then return its default command for calls to
	 * {@link #getResubmitDataCommand() getResbumitDataCommand}.
	 * @param command the new ResubmitDataCommand to use.
	 */
	public void setResubmitDataCommand( UsbIrp.ResubmitDataCommand command );

	/**
	 * Get the ResubmitErrorCommand currently in use.
	 * <p>
	 * This will never return null; if an application attempts to
	 * {@link #setResubmitErrorCommand(UsbIrp.ResubmitErrorCommand) setResubmitErrorCommand(null)},
	 * the implementation should then return its default command (which performs the default
	 * ResubmitErrorCommand action).
	 * @return the ResubmitErrorCommand in use.
	 */
	public UsbIrp.ResubmitErrorCommand getResubmitErrorCommand();

	/**
	 * Set the ResubmitErrorCommand to use.
	 * <p>
	 * The default action is to stop resubmission on any error,
	 * without any special handling.
	 * <p>
	 * Setting this to null will not cause
	 * {@link #getResubmitErrorCommand() getResubmitErrorCommand()} to
	 * return null, however it does guarantee that the implementation
	 * will revert to the default behavior for this command.  The implementation
	 * should then return its default command for calls to
	 * {@link #getResubmitErrorCommand() getResbumitErrorCommand}.
	 * @param command the new ResubmitErrorCommand to use.
	 */
	public void setResubmitErrorCommand( UsbIrp.ResubmitErrorCommand command );

	/**
	 * Get the EventCommand currently in use.
	 * <p>
	 * The default action for this command is to fire events.
	 * <p>
	 * This will never return null; if an application attempts to
	 * {@link #setEventCommand(UsbIrp.EventCommand) setEventCommand(null)},
	 * the implementation should then return its default command (which performs the default
	 * EventCommand action).
	 * @return the EventCommand currently in use.
	 */
	public UsbIrp.EventCommand getEventCommand();

	/**
	 * Set the EventCommand currently in use.
	 * <p>
	 * Setting this to null will not cause
	 * {@link #getEventCommand() getEventCommand()} to
	 * return null, however it does guarantee that the implementation
	 * will revert to the default behavior for this command.  The implementation
	 * should then return its default command for calls to
	 * {@link #getEventCommand() getEventCommand}.
	 * @param the EventCommand to use.
	 */
	public void setEventCommand( UsbIrp.EventCommand command );

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

	//*************************************************************************
	// Inner interfaces

	public interface ResubmitDataCommand
	{
		/**
		 * Get the data to use in the next resubmission.
		 * <p>
		 * This is called to get a data buffer to use as the data in
		 * the (re)submission.  This indicates that the UsbIrp has
		 * completed successfully, and its resubmission policy is enabled.
		 * The UsbIrp is now ready to resubmit, and is requesting a new
		 * data buffer.  The same data buffer may be used (if desired),
		 * but the byte[] data buffer is accessible via the
		 * {@link javax.usb.event.UsbPipeDataEvent#getData() UsbPipeDataEvent}
		 * that is fired, and re-using that buffer will change its contents;
		 * this may lead to undesirable results if the application wishes to
		 * process the buffer provided by the UsbPipeDataEvent.  The default
		 * for the ResubmitDataCommand is to create a new byte[] data buffer
		 * of the same size as the original, and copy the contents of the
		 * previous buffer into the new buffer.
		 * <p>
		 * While the ResubmitDataCommand is executing, the UsbIrp is still
		 * {@link javax.usb.UsbIrp#isActive() active},
		 * but some setters may be called:
		 * <ul>
		 * <li>The data (accessible via
		 * {@link javax.usb.UsbIrp#getData() getData()}) may be modified,
		 * and the data buffer may be changed by calling
		 * {@link javax.usb.UsbIrp#setData(byte[]) setData(byte[] data)}.</li>
		 * <li>The resubmission policy may be changed by calling
		 * {@link javax.usb.UsbIrp#setResubmit(boolean) setResubmit(boolean resubmit)},
		 * however note that disabling resubmission will cause the UsbIrp to
		 * immediately stop, and the byte[] data buffer returned by this method
		 * will not be submitted.</li>
		 * <li>The
		 * {@link javax.usb.UsbIrp.ResubmitDataCommand ResubmitDataCommand} and/or
		 * {@link javax.usb.UsbIrp.ResubmitErrorCommand ResubmitErrorCommand}
		 * may be changed by calling their setters; if changed, the new
		 * commands will be used by the next resubmission.</li>
		 * </ul>
		 * <p>
		 * Note that while this is processing, other UsbIrps may complete
		 * and possibly resubmit themselves.  There is no guarantee
		 * that UsbIrps will resubmit themselves sequentially.
		 * @param irp the UsbIrp that is about to be resubmitted.
		 * @return a data buffer to use in the resubmission.
		 */
		public byte[] getResubmitData( UsbIrp irp );
	}

	public interface ResubmitErrorCommand
	{
		/**
		 * If resubmission should continue after receiving an error.
		 * <p>
		 * This is called after an error occurs during submission and resubmission
		 * is enabled.  The return value will indicate whether resubmission should
		 * continue.  A UsbPipeErrorEvent is generated in any case, and the
		 * return value of this only indicates whether to leave resubmission enabled
		 * or not.  If this returns false, resubmission is set to false, the
		 * ResubmitDataCommand is not called, and the submission completes (with
		 * the appropriate UsbException).
		 * <p>
		 * The provided UsbIrp will indicate the error via
		 * {@link javax.usb.UsbIrp#getUsbException() its UsbException}.
		 * @param irp the UsbIrp that is about to be resubmitted.
		 * @return if resubmission should continue.
		 */
		public boolean continueResubmission( UsbIrp irp );
	}

	public interface EventCommand
	{
		/**
		 * If this UsbIrp should generate an event.
		 * <p>
		 * This is called to determine if an event should be
		 * fired.  If the UsbIrp is in error, a UsbPipeErrorEvent
		 * will be fired if this returns true; if the UsbIrp
		 * is not in error, a UsbPipeDataEvent will be fired
		 * if this returns true.  If this returns false,
		 * no event will be fired.
		 * <p>
		 * The default is to fire an event.
		 * @param irp the UsbIrp that just completed.
		 * @return if an event should be fired.
		 */
		public boolean shouldFireEvent( UsbIrp irp );
	}

}
