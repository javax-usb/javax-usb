package javax.usb.event;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.util.*;

import javax.usb.*;

/**
 * Class for USB pipe events.
 * @author E. Michael Maximilien
 */
public class UsbPipeEvent extends EventObject
{
	/**
	 * Constructor.
	 * @param source The source UsbPipe.
	 */
	public UsbPipeEvent( UsbPipe source ) { super(source); }

	/**
	 * Get the UsbPipe.
	 * @return The associated UsbPipe.
	 */
	public UsbPipe getUsbPipe() { return (UsbPipe)getSource(); }

}
