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
 * Defines Request Constants
 * <p><i>See USB 1.1 spec section 9.4</i>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface RequestConst
{
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
}
