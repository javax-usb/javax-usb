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
 * Defines a set of general USB constants and error bases.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbConst
{

	//*************************************************************************
	// USB error bases

	public static final int ERR_BASE                  =  10;
	public static final int USB_SERVICES_BASE_ERR     = 100;
	public static final int USB_INFO_BASE_ERR         = 200;
	public static final int USB_PIPE_BASE_ERR         = 300;
	public static final int REQUEST_BASE_ERR          = 400;
	public static final int DESCRIPTOR_BASE_ERR       = 500;

	//*************************************************************************
	// USB generic errors

	/** An attempt was made to use a device which has been removed. */
	public static final int USB_ERR_DEVICE_REMOVED                = -( ERR_BASE + 1);

}
