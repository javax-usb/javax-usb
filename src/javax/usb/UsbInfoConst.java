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
 * Defines a set of constants used by all USB info objects
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbInfoConst
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

}
