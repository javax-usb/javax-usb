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
 * USB constants.
 * @author Dan Streetman
 */
public interface UsbConst
{
	//-------------------------------------------------------------------------
	// USB configuration constants
	//

    public static final byte CONFIG_POWERED_MASK                        = (byte)0x60;

    public static final byte CONFIG_SELF_POWERED                        = (byte)0x40;
    public static final byte CONFIG_REMOTE_WAKEUP                       = (byte)0x20;

	//-------------------------------------------------------------------------
	// USB endpoints constants
	//

    public static final byte ENDPOINT_NUMBER_MASK                       = (byte)0x0f;

    public static final byte ENDPOINT_DIRECTION_MASK                    = (byte)0x80;

    public static final byte ENDPOINT_DIRECTION_OUT                     = (byte)0x00;
    public static final byte ENDPOINT_DIRECTION_IN                      = (byte)0x80;

    public static final byte ENDPOINT_TYPE_MASK                         = (byte)0x03;

    public static final byte ENDPOINT_TYPE_CONTROL                      = (byte)0x00;
    public static final byte ENDPOINT_TYPE_ISOC                         = (byte)0x01;
    public static final byte ENDPOINT_TYPE_BULK                         = (byte)0x02;
    public static final byte ENDPOINT_TYPE_INT                          = (byte)0x03;

	public static final byte ENDPOINT_SYNCHRONIZATION_TYPE_MASK		    = (byte)0x0c;

	public static final byte ENDPOINT_SYNCHRONIZATION_TYPE_NONE		    = (byte)0x00;
	public static final byte ENDPOINT_SYNCHRONIZATION_TYPE_ASYNCHRONOUS = (byte)0x04;
	public static final byte ENDPOINT_SYNCHRONIZATION_TYPE_ADAPTIVE	    = (byte)0x08;
	public static final byte ENDPOINT_SYNCHRONIZATION_TYPE_SYNCHRONOUS  = (byte)0x0c;

	public static final byte ENDPOINT_USAGE_TYPE_MASK				    = (byte)0x30;

	public static final byte ENDPOINT_USAGE_TYPE_DATA				    = (byte)0x00;
	public static final byte ENDPOINT_USAGE_TYPE_FEEDBACK			    = (byte)0x10;
	public static final byte ENDPOINT_USAGE_TYPE_IMPLICIT_FEEDBACK_DATA = (byte)0x20;
	public static final byte ENDPOINT_USAGE_TYPE_RESERVED			    = (byte)0x30;

	//-------------------------------------------------------------------------
	// USB info base error

	public static final int ERR_BASE = UsbConst.USB_INFO_BASE_ERR;

	//-------------------------------------------------------------------------
	// USB interface errors

	/** An attempt was made to claim an interface that is already claimed. */
	public static final int USB_INFO_ERR_INTERFACE_CLAIMED              = -( ERR_BASE + 1 );

	/** The implementation could not natively claim the interface. */
	public static final int USB_INFO_ERR_NATIVE_CLAIM_FAILED            = -( ERR_BASE + 2 );

	/** An attempt was made to release an interface that is not claimed. */
	public static final int USB_INFO_ERR_INTERFACE_NOT_CLAIMED          = -( ERR_BASE + 3 );

	/** The implementation could not natively release the interface. */
	public static final int USB_INFO_ERR_NATIVE_RELEASE_FAILED          = -( ERR_BASE + 4 );

	/** An attempt was made to access or use an unconfigured device. */
	public static final int USB_INFO_ERR_NOT_CONFIGURED                 = -( ERR_BASE + 5 );

	/** An attempt was made to access or use an inactive configuration. */
	public static final int USB_INFO_ERR_INACTIVE_CONFIGURATION         = -( ERR_BASE + 6 );

	/** An attempt was made to access or use an inactive interface setting. */
	public static final int USB_INFO_ERR_INACTIVE_INTERFACE_SETTING     = -( ERR_BASE + 7 );


	//-------------------------------------------------------------------------
	// Request bmRequestType values
	//

	/** Utility bit mask for finding Request Type direction */
	public static final byte REQUESTTYPE_DIRECTION_MASK      = (byte)0x80;

	/**
	 * Indicates an OUT request i.e. a Host-to-device request
	 * <p><i>See USB 1.1 section 9.3 table 9-2</i>
	 */
	public static final byte REQUESTTYPE_DIRECTION_OUT       = (byte)0x00;

	/**
	 * Indicates an IN request i.e. a Device-to-host request
	 * <p><i>See USB 1.1 section 9.3 table 9-2
	 */
	public static final byte REQUESTTYPE_DIRECTION_IN        = (byte)0x80;

	/** Utility bit mask for finding Request Type type */
	public static final byte REQUESTTYPE_TYPE_MASK           = (byte)0x60;

	/**
	 * Indicates a Standard Request type
	 * <p><i>See USB 1.1 section 9.3 table 9-2</i>
	 */
	public static final byte REQUESTTYPE_TYPE_STANDARD       = (byte)0x00;

	/**
	 * Indicates a Class Request type
	 * <p><i>See USB 1.1 section 9.3 table 9-2</i>
	 */
	public static final byte REQUESTTYPE_TYPE_CLASS          = (byte)0x20;

	/**
	 * Indicates a Vendor Request type
	 * <p><i>See USB 1.1 section 9.3 table 9-2</i>
	 */
	public static final byte REQUESTTYPE_TYPE_VENDOR         = (byte)0x40;

	/** Utility bit mask for finding Request Type recipient */
	public static final byte REQUESTTYPE_RECIPIENT_MASK      = (byte)0x1f;

	/**
	 * Indicates the Device as the recipient
	 * <p><i>See USB 1.1 section 9.3 table 9-2</i>
	 */
	public static final byte REQUESTTYPE_RECIPIENT_DEVICE  	 = (byte)0x00;

	/**
	 * Indicates the Interface as the recipient
	 * <p><i>See USB 1.1 section 9.3 table 9-2</i>
	 */
	public static final byte REQUESTTYPE_RECIPIENT_INTERFACE = (byte)0x01;

	/**
	 * Indicates the Endpoint as the recipient
	 * <p><i>See USB 1.1 section 9.3 table 9-2</i>
	 */
	public static final byte REQUESTTYPE_RECIPIENT_ENDPOINT  = (byte)0x02;

	/**
	 * Indicates Other as the recipient
	 * <p><i>See USB 1.1 section 9.3 table 9-2</i>
	 */
	public static final byte REQUESTTYPE_RECIPIENT_OTHER     = (byte)0x03;

	//-------------------------------------------------------------------------
	// Standard Request codes
	//

	/**
	 * GET_STATUS standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_GET_STATUS				 = (byte)0x00;

	/**
	 * CLEAR_FEATURE standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_CLEAR_FEATURE           = (byte)0x01;

	/**
	 * GET_STATE request code
	 * <p><i>See USB 1.1</i>
	 */
	public static final byte REQUEST_GET_STATE           	= (byte)0x02;

	/**
	 * SET_FEATURE standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_SET_FEATURE			 = (byte)0x03;

	/**
	 * SET_ADDRESS standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_SET_ADDRESS			 = (byte)0x05;

	/**
	 * GET_DESCRIPTOR standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_GET_DESCRIPTOR			 = (byte)0x06;

	/**
	 * SET_DESCRIPTOR standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_SET_DESCRIPTOR			 = (byte)0x07;

	/**
	 * GET_CONFIGURATION standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_GET_CONFIGURATION		 = (byte)0x08;

	/**
	 * SET_CONFIGURATION standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_SET_CONFIGURATION		 = (byte)0x09;

	/**
	 * GET_INTERFACE standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_GET_INTERFACE			 = (byte)0x0a;

	/**
	 * SET_INTERFACE standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_SET_INTERFACE			 = (byte)0x0b;

	/**
	 * SYNCH_FRAME standard request code
	 * <p><i>See USB 1.1 section 9.4 table 9-4</i>
	 */
	public static final byte REQUEST_SYNCH_FRAME			 = (byte)0x0c;


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

	//-------------------------------------------------------------------------
    // USB spec "Device Class" defined USB device classes
	//

    public static final byte DEVICE_CLASS_RESERVED                      = (byte)0x00;
    public static final byte DEVICE_CLASS_AUDIO                         = (byte)0x01;
    public static final byte DEVICE_CLASS_COMMUNICATIONS                = (byte)0x02;
    public static final byte DEVICE_CLASS_HUMAN_INTERFACE               = (byte)0x03;
    public static final byte DEVICE_CLASS_MONITOR                       = (byte)0x04;
    public static final byte DEVICE_CLASS_PHYSICAL_INTERFACE            = (byte)0x05;
    public static final byte DEVICE_CLASS_POWER                         = (byte)0x06;    
    public static final byte DEVICE_CLASS_PRINTER                       = (byte)0x07;
    public static final byte DEVICE_CLASS_STORAGE                       = (byte)0x08;
    public static final byte DEVICE_CLASS_HUB                           = (byte)0x09;
    
    public static final byte DEVICE_CLASS_VENDOR_SPECIFIC               = (byte)0xff;

    //-------------------------------------------------------------------------
    // USB spec "Descriptor Type" constants
	//

    public static final byte DESCRIPTOR_TYPE_DEVICE                     = (byte)0x01;
    public static final byte DESCRIPTOR_TYPE_CONFIG                     = (byte)0x02;
    public static final byte DESCRIPTOR_TYPE_STRING                     = (byte)0x03;
    public static final byte DESCRIPTOR_TYPE_INTERFACE                  = (byte)0x04;
    public static final byte DESCRIPTOR_TYPE_ENDPOINT                   = (byte)0x05;

	//-------------------------------------------------------------------------
    // USB 1.1 descriptor minimum lengths
	//

    public static final byte DESCRIPTOR_MIN_LENGTH                      = (byte)0x02;
    public static final byte DESCRIPTOR_MIN_LENGTH_DEVICE               = (byte)0x12;
    public static final byte DESCRIPTOR_MIN_LENGTH_CONFIG               = (byte)0x09;
    public static final byte DESCRIPTOR_MIN_LENGTH_INTERFACE            = (byte)0x09;
    public static final byte DESCRIPTOR_MIN_LENGTH_ENDPOINT             = (byte)0x07;
    public static final byte DESCRIPTOR_MIN_LENGTH_STRING               = (byte)0x02;
}
