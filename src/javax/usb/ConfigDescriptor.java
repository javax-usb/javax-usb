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
 * Defines a USB configuration descriptor.
 * <p>
 * This interface logically represents a USB device configuration descriptor
 * which keeps all the necessary information describing it.  Getter methods
 * are provided for all the sections of a configuration descriptor.
 * </p>
 * <p>
 * <i>See section 9.6.2 of USB 1.1 specification for details</i>
 * </p>
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface ConfigDescriptor extends Descriptor
{
    /** @return the number of interfaces supported by this configuration */
    public byte getNumInterfaces();

    /** @return this configuration value */
    public byte getConfigValue();

    /** @return this configuration description index */
    public byte getConfigIndex();

    /**
     * @return the attributes specifying this configuration's characteristics 
     * <i>NOTE: this is a byte bitmap</i>
     */
    public byte getAttributes();

    /** @return the maximum power for this configuration.  Specified in multiple of 2mA */
    public byte getMaxPower();
}
