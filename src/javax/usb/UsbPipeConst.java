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
 * Defines an interface for all UsbPipe constants.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbPipeConst
{

	//*************************************************************************
	// UsbPipe constants

    public static final byte PIPE_DIRECTION_OUT    = 0x00;
    public static final byte PIPE_DIRECTION_IN     = (byte)0x80;

    public static final byte PIPE_TYPE_CONTROL     = 0x00;
    public static final byte PIPE_TYPE_ISOC        = 0x01;
    public static final byte PIPE_TYPE_BULK        = 0x02;
    public static final byte PIPE_TYPE_INT         = 0x03;

    public static final byte PIPE_DIRECTION_MASK   = (byte)0x80;
    public static final byte PIPE_NUMBER_MASK      = 0x7f;
    public static final byte PIPE_TYPE_MASK        = 0x03;

	//*************************************************************************
	// UsbPipe error base

	public static final int ERR_BASE = UsbConst.USB_PIPE_BASE_ERR;

	//*************************************************************************
	// UsbPipe errors

	/** An attempt was made to use a UsbPipe on an unclaimed interface. */
	public static final int USB_PIPE_ERR_NOT_CLAIMED               = -( ERR_BASE + 1);

	/** Permission to use a UsbPipe on a claimed interface was denied. */
	public static final int USB_PIPE_ERR_CLAIMED_ACCESS_DENIED     = -( ERR_BASE + 2);

	/** An attempt to use an unopened UsbPipe was made. */
	public static final int USB_PIPE_ERR_NOT_OPEN                  = -( ERR_BASE + 3);

	/** An attempt to use an inactive UsbPipe was made. */
	public static final int USB_PIPE_ERR_INACTIVE_PIPE             = -( ERR_BASE + 4);

	/** The UsbPipe could not be opened by the implementation. */
	public static final int USB_PIPE_ERR_IMP_OPEN_FAILED           = -( ERR_BASE + 5);

	/** The UsbPipe could not be used due to bandwidth limitations. */
	public static final int USB_PIPE_ERR_NO_BANDWIDTH              = -( ERR_BASE + 6);

	/** The format, contents, size, or other aspect of the data submission was invlaid. */
	public static final int USB_PIPE_ERR_INVALID                   = -( ERR_BASE + 7);

	/** There was insufficient memory to complete the data transfer. */
	public static final int USB_PIPE_ERR_NO_MEMORY                 = -( ERR_BASE + 8);

	/** The submission was aborted. */
	public static final int USB_PIPE_ERR_ABORTED                   = -( ERR_BASE + 9);

	/** The implementation temporarily could not handle the submission. */
	public static final int USB_PIPE_ERR_TEMPORARY_FAILURE         = -( ERR_BASE + 10);

	/** A short packet was detected, in a submission that did not allow short packets. */
	public static final int USB_PIPE_ERR_SHORT_PACKET              = -( ERR_BASE + 11);

	/** The submission was interrupted. */
	public static final int USB_PIPE_ERR_INTERRUPTED               = -( ERR_BASE + 12);

	/** An unknown I/O error occurred diuring submission. */
	public static final int USB_PIPE_ERR_IO                        = -( ERR_BASE + 13);

	/** The UsbPipe is stalled/halted. */
	public static final int USB_PIPE_ERR_HALTED                    = -( ERR_BASE + 14);

	/** The submission timed out. */
	public static final int USB_PIPE_ERR_TIMEOUT                   = -( ERR_BASE + 15);

	/** The submission was/is already in progress. */
	public static final int USB_PIPE_ERR_IN_PROGRESS               = -( ERR_BASE + 16);

	/** An unknown implementation or platform error occurred. */
	public static final int USB_PIPE_ERR_UNKNOWN                   = -( ERR_BASE + 17);

}
