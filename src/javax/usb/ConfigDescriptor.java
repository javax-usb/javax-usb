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
 * Interface for a USB configuration descriptor.
 * <p>
 * See the USB 1.1 specification section 9.6.2.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public interface ConfigDescriptor extends Descriptor
{
	/**
	 * Get the total length of this descriptor.
	 * @return The total length of this Descriptor.
	 */
	public short getTotalLength();

    /**
	 * Get the number of interfaces for the configuration.
	 * @return The number of interfaces for this configuration.
	 */
    public byte getNumInterfaces();

    /**
	 * Get the value (number) of this configuration.
	 * @return The value (number) of this configuration.
	 */
    public byte getConfigValue();

    /**
	 * Get the configuration string index.
	 * <p>
	 * If this is 0, the configuration does not have a configuration string.
	 * @return The configuration string index.
	 */
    public byte getConfigIndex();

    /**
	 * Get the configuration attributes bitmap.
     * @return The configuration attributes bitmap.
     */
    public byte getAttributes();

    /**
	 * Get the maximum power this configuration requires.
	 * <p>
	 * This is specified in units of 2mA.  For example,
	 * a value of 50 indicates 100mA.
	 * @return The maximum power for this configuration.
	 */
    public byte getMaxPower();
}
