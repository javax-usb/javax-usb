package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import javax.usb.util.UsbIrpList;

/**
 * Defines a Usb Composite Irp (I/O Request Packet).
 * <p>
 * This object represents a composite UsbIrp; it is composed of individual
 * UsbIrps, which are used in the submission.  The UsbIrp methods that this
 * object inherits affect all its UsbIrps.  
 * <p>
 * The advantages of using a UsbCompositeIrp instead of submiting individual
 * UsbIrps (seperately) are listed:
 * <ul>
 * <li>The list of UsbIrps are handled contiguously; no other submissions
 * (including any automatic resubmissions) will be 'inserted' in
 * between any UsbIrps belonging to a UsbCompositeIrp.</li>
 * <li>Management of the list of UsbIrps is easier; the UsbIrps are contained
 * in one list, and the UsbCompositeIrp provides methods to check
 * the status and data of the UsbIrps.</li>
 * </ul>
 * <p>
 * Behavior differences, extentions, and clarifications are listed here:
 * <ul>
 * <li>Sequence Number
 *   <ul>
 *   <li>The UsbCompositeIrp's sequence number is assigned by the UsbPipe when submitted.
 *   All individual UsbIrps also receive unique sequence numbers at the same time.</li>
 *   </li>
 *   </ul>
 * </li>
 * <li>Data
 *   <ul>
 *   <li>The data contained by this UsbCompositeIrp is actually the aggregation of
 *   the data contained in each individual UsbIrp contained in the {@link #getUsbIrps() list}.
 *   The {@link #getData() getData()} method returns a copy of all of the UsbIrp's data.
 *   It is not backed by the actual data, and a new copy is generated for each call.
 *   Calls may therefore be expensive.  The operation of copying proceeds in this manner:
 *     <ol>
 *     <li>The total size of data buffer needed is calculated by summing each
 *     {@link #getData() getData()}.length.  A new byte[] is created with this size.</li>
 *     <li>Starting with the first UsbIrp, data is copied from its {@link #getData() data buffer}
 *     of a length equal to its {@link #getDataLength() data length}.</li>
 *     <li>The operation is repeated for each UsbIrp in the {@link #getUsbIrps() list}.</li>
 *     </ol>
 *   Note that the resulting byte[] may not be completely filled with valid data, if one or more
 *   individual UsbIrps' {@link #getDataLength() data length} is less than their buffer size.</li>
 *   <li>If the UsbCompositeIrp is active, the returned data does not necessarily represent all
 *   the UsbIrp's data.  The method will return all the aggregated UsbIrps' data when the
 *   UsbCompositeIrp is complete.</li>
 *   <li>The {@link #getDataLength() data length} for the UsbCompositeIrp represents the
 *   aggregated length of all individual UsbIrps' data lengths.  It is calculated by adding
 *   all UsbIrp's {@link #getDataLength() data lengths}, starting with the first UsbIrp.</li>
 *   <li>The {@link #setData(byte[]) setData(byte[] data)} method will cause all individual
 *   UsbIrps' data buffers to be replaced with a new byte[] buffer, of the same size and contents,
 *   as the specified buffer.  The {@link #setData(byte[]) setData()} method should not be
 *   called when the UsbCompositeIrp is active.</li>
 *   </ul>
 * </li>
 * <li>Active/Completion
 *   <ul>
 *   <li>While the UsbCompositeIrp is {@link #isActive() active}, no part of it should
 *   be modified, including the {@link #getUsbIrps() UsbIrp list}; i.e., no UsbIrps
 *   should be added/removed/etc to/from the UsbIrp list while the UsbCompositeIrp is active.</li>
 *   <li>The UsbCompositeIrp itself does not reach a 'complete' state until all
 *   the UsbIrps it contains have concluded their current data transfer; thus the
 *   {@link #isCompleted() isCompleted()} method applies to
 *   the UsbCompositeIrp as a whole, as should be expected.</li>
 *   <li>The individual UsbIrps will complete every submission, i.e. any waiters on
 *   an individual UsbIrp's {@link javax.usb.UsbIrp#waitUntilCompleted() waitUntilCompleted}
 *   method will be notified after that individual UsbIrp completes during the initial submission,
 *   and every subsequent resubmission (if any).  All individual UsbIrps will be
 *   {@link #isActive() inactive} and {@link #isCompleted() completed} after they complete, until
 *   the UsbCompositeIrp resubmits itself (if appropriate).</li>
 *   </ul>
 * </li>
 * <li>Errors/Exceptions
 *   <ul>
 *   <li>If an error or exception occurs during processing of a UsbIrp, the UsbCompositeIrp's
 *   {@link javax.usb.UsbCompositeIrp.CompositeErrorCommand CompositeErrorCommand}
 *   is called.  This command determines if the UsbCompositeIrp continues submission
 *   of the rest of the UsbIrps.  See its documentation for more details.</li>
 *   <li>Once all individual UsbIrps in the list have concluded their data transmission,
 *   or completed with an error, the final state of the UsbCompositeIrp can depend on
 *   the following conditions:
 *     <ul>
 *     <li>If all UsbIrps completed successfully, the UsbCompositeIrp has completed
 *     successfully.</li>
 *     <li>If any of the UsbIrps completed with an error, the UsbCompositeIrp's state
 *     depends on the execution of the
 *     {@link javax.usb.UsbCompositeIrp.CompositeErrorCommand CompositeErrorCommand}.
 *     If the CompositeErrorCommand allowed UsbIrp submission to continue in all
 *     error cases, the UsbCompositeIrp has completed successfully.  If the
 *     CompositeErrorCommand stopped submission (returned false), then the
 *     remaining UsbIrps (if any) will have been aborted, and the UsbCompositeIrp
 *     is in an error condition.  The specific error condition (UsbException) will
 *     be whatever error condition caused the individual UsbIrp to fail, when
 *     the CompositeErrorCommand stopped submission.</li>
 *     <li>Note that even if all UsbIrps failed, as long as the CompositeErrorCommand
 *     allows submission to continue, the UsbCompositeIrp will complete without errors.
 *     The individual UsbIrps will retain their error status, until resubmission
 *     begins (if appropriate).</li>
 *     </ul>
 *   <li>If the UsbCompositeIrp completes with an error and {@link #getResubmit() resubmission}
 *   is enabled, the
 *   {@link javax.usb.UsbIrp.ResubmitErrorCommand ResubmitErrorCommand} is called.</li>
 *   <li>If the UsbCompositeIrp completes without an error (sucessfully) and
 *   {@link #getResubmit() resubmission} is enabled, the
 *   {@link javax.usb.UsbIrp.ResubmitDataCommand ResubmitDataCommand} is executed.</li>
 *   </ul>
 * </li>
 * <li>Resubmission
 *   <ul>
 *   <li>Each individual UsbIrp's resubmission is ignored.  The UsbIrps
 *   do not resubmit themselves; they only resubmit as part of the composite.
 *   All resubmission functions of the individual UsbIrp, such as calling
 *   {@link javax.usb.UsbIrp.ResubmitDataCommand ResubmitDataCommand} and
 *   {@link javax.usb.UsbIrp.ResubmitErrorCommand ResubmitErrorCommand}, are
 *   never executed.  The UsbCompositeIrp's are executed (when appropriate),
 *   and those may call each individual UsbIrp's commands if desired
 *   (but they will not unless replaced by the user).</li>
 *   <li>The UsbCompositeIrp's {@link #getResubmit() resubmission} applies to
 *   the UsbCompositeIrp as a whole; when all individual UsbIrps have concluded,
 *   the UsbCompositeIrp resubmits itself if resubmission is enabled.
 *   The individual UsbIrps and the UsbCompositeIrp itself will all complete
 *   if the UsbCompositeIrp's resubmission is disabled.</li>
 *   <li>The allowed actions during execution of the UsbCompositeIrp's
 *   {@link javax.usb.UsbIrp.ResubmitDataCommand ResubmitDataCommand}
 *   are the same as for a UsbIrp, additionally modifying individual UsbIrps in the
 *   {@link #getUsbIrps() list}, and/or adding or removing UsbIrps from the list,
 *   is allowed.  The buffer returned by the UsbCompositeIrp's ResubmitDataCommand,
 *   if not null, is used to populate the individual UsbIrp's buffer via the
 *   UsbCompositeIrp's {@link #setData(byte[]) setData()} command.  If null is returned
 *   the individual UsbIrps retain their previous buffers.  The default is to return null.</li>
 *   <li>The allowed actions during execution of the UsbCompositeIrp's
 *   {@link javax.usb.UsbIrp.ResubmitErrorCommand ResubmitErrorCommand}
 *   are the same as for a UsbIrp, additionally modifying individual UsbIrps in the
 *   {@link #getUsbIrps() list}, and/or adding or removing UsbIrps from the list,
 *   is allowed.  The return value of the command is used in the same manner as a
 *   UsbIrp, it determines if the UsbCompositeIrp resubmits itself or completes.</li>
 *   </ul>
 * </li>
 * <li>Short Packets
 *   <ul>
 *   <li>The {@link #setAcceptShortPacket(boolean) setAcceptShortPacket()} method
 *   sets all UsbIrps in the list to the specified policy.</li>
 *   <li>The {@link #getAcceptShortPacket() getAcceptShortPacket()} method
 *   returns true only if all all UsbIrps are set to accept short packets.  If
 *   one or more UsbIrps are set to not accept short packets the method returns false.
 *   Note that this means the return value is a somewhat 'fuzzy' boolean.</li>
 *   </ul>
 * </li>
 * <li>Recycling
 *   <ul>
 *   <li>When the {@link #recycle() recycle()} method is called, the UsbCompositeIrp
 *   and all UsbIrps it contains are recycled.  If any of the UsbIrps should not
 *   be recycled, they must be removed from the {@link #getUsbIrps() list} first.</li>
 *   </ul>
 * </li>
 * <li>Events
 *   <ul>
 *   <li>The {@link javax.usb.UsbIrp.EventCommand EventCommand} for each individual
 *   UsbIrp will be called at some point after their completion.  The implementation
 *   should call the command as soon as possible after completion.  The commands
 *   must be called in the order the UsbIrps are in the {@link #getUsbIrps() list}.</li>
 *   <li>The {@link javax.usb.UsbIrp.EventCommand EventCommand} for the UsbCompositeIrp
 *   will be called at the normal time to determine if an event should be fired for the
 *   UsbCompositeIrp as a whole; however, note that while the default for a UsbIrp
 *   is to fire an event, the default for a UsbCompositeIrp is to <i>not</i>
 *   fire an event.  This avoids the (possibly expensive) call to the UsbCompositeIrp's
 *   {@link #getData() getData()} by default, and avoids the redundacy of firing events
 *   for each individual UsbIrp and the UsbCompositeIrp.</li>
 *   </ul>
 * </li>
 * </ul>
 * @author Dan Streetman
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface UsbCompositeIrp extends UsbIrp
{
	//*************************************************************************
	// Public methods

	/**
	 * Get the UsbIrpList of UsbIrps contained in this UsbCompositeIrp.
	 * <p>
	 * Since this list belongs to the UsbCompositeIrp, an application can only
	 * modify it with the normal add/remove/etc methods; it cannot be replaced.
	 * To populate this list with a UsbInfoList owned by the application,
	 * you can use {@link javax.usb.util.UsbInfoList#addUsbInfoList(UsbInfoList) addUsbInfoList}
	 * (preceeded by {@link javax.usb.util.UsbInfoList#clear() clear} if necessary).
	 * <p>
	 * Note that just as all other parts of UsbCompositeIrps and UsbIrps, no part of this
	 * list should be modified (including adding or removing UsbIrps) while the UsbCompositeIrp
	 * is {@link #isActive() active}.
	 * @return the list of UsbIrps.
	 */
	public UsbIrpList getUsbIrps();

	/**
	 * Get the CompositeErrorCommand currently in use.
	 * @return the CompositeErrorCommand in use.
	 */
	public UsbCompositeIrp.CompositeErrorCommand getCompositeErrorCommand();

	/**
	 * Set the CompositeErrorCommand currently in use.
	 * @param command the CompositeErrorCommand to use.
	 */
	public void setCompositeErrorCommand( UsbCompositeIrp.CompositeErrorCommand command );

	//*************************************************************************
	// Inner interfaces

	public interface CompositeErrorCommand
	{
		/**
		 * If submission of the individual UsbIrps should continue.
		 * <p>
		 * This is executed when a individual UsbIrp encounters an error.
		 * No actions may be taken on any active UsbIrps in the list, nor
		 * on the UsbCompositeIrp.  The list of UsbIrps should not be modified
		 * by removing or adding any UsbIrps.  The return value of this
		 * indicates whether the remaining UsbIrps (if any) should be
		 * executed or aborted.  A return of true continues execution normally,
		 * a return value of false aborts the remaining active UsbIrps.  Note
		 * that if the implementation handles multiple UsbIrps in this
		 * composite simultaneously, there may be UsbIrps later in the list
		 * that have transferred their data but have not yet completed.
		 * Those UsbIrps that have transferred their data will complete
		 * regardless of the return value; however any UsbIrps that have
		 * not transferred their data will be aborted (if the return value is false).
		 * <p>
		 * The default is to not allow continuation of UsbIrp submission, i.e., return false.
		 * @param composite the UsbCompositeIrp.
		 * @param irp the UsbIrp that encountered an error.
		 * @return if the UsbCompositeIrp should continue to execute subsequent UsbIrps.
		 */
		public boolean continueSubmissions( UsbCompositeIrp composite, UsbIrp irp );
	}

}
