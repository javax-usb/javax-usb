package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import java.util.Enumeration;

/**
 * Defines an interface for a bundle of UsbPipe objects.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbPipeBundle
{
    /**
	 * Get the UsbPipe with the specified UsbEndpoint address.
     * @param epAddress the address of the UsbEndpoint.
     * @return the UsbPipe belonging to the UsbEndpoint with the specified address.
	 * @throws javax.usb.UsbRuntimeException if a pipe with the specified address is not conatined in this.
     */
    public UsbPipe getUsbPipe( byte epAddress );

	/**
	 * If this contains a UsbPipe with the specified endpoint address.
	 * @return if this contains a UsbPipe with the specified address.
	 */
	public boolean containsUsbPipe( byte epAddress );

    /**
	 * Get the number of UsbPipes in the bundle.
	 * @return the current size of this bundle.
	 */
    public int size();

    /**
	 * If this bundle is empty.
	 * @return true if this bundle is empty.
	 */
    public boolean isEmpty();

    /**
	 * Get an Enumeration of the UsbPipes.
	 * @return an Enumeration of UsbPipe objects in this bundle.
	 */
    public Enumeration elements();
}
