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
 * Interface for a USB endpoint.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public interface UsbEndpoint
{
    /**
	 * Get the parent UsbInterface that this UsbEndpoint belongs to.
	 * @return The parent interface.
	 */
    public UsbInterface getUsbInterface();

	/**
	 * Get the Descriptor for this UsbEndpoint.
	 * <p>
	 * The descriptor may be cached.
	 * @return The descriptor for this UsbEndpoint.
	 */
	public EndpointDescriptor getEndpointDescriptor();

    /**
	 * Get the UsbPipe for this UsbEndpoint.
	 * <p>
	 * This is the only method of communication to this endpoint.
	 * @return This UsbEndpoint's UsbPipe.
	 */
    public UsbPipe getUsbPipe();
}
