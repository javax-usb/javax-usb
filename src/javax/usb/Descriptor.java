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
 * Interface for a USB descriptor.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public interface Descriptor
{
    /**
	 * Get the length of this descriptor.
	 * @return The length of this descriptor.
	 */
    public byte getLength();

    /**
	 * Get the type of this descriptor.
	 * @return The type of this descriptor.
	 */
    public byte getType();

}
