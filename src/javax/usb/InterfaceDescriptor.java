package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

/**
 * Defines the interface for USB interface descriptors
 * <p>
 * This interface logically represents a USB interface descriptor
 * which keeps all the necessary information describing it.  Getter methods
 * are provided for all the sections of a interface descriptor.
 * </p>
 * <p>
 * <i>See section 9.6.3 of USB 1.1 specification for details</i>
 * </p>
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface InterfaceDescriptor extends Descriptor
{
    /** @return the interface number */
    public byte getInterfaceNumber();

    /** @return the alternate setting for this interface */
    public byte getAlternateSetting();

    /** @return the number of endpoints used by this interface */
    public byte getNumEndpoints();

    /** @return the interface class code */
    public byte getInterfaceClass();

    /** @return the interface subclass code */
    public byte getInterfaceSubClass();

    /** @return the interface protocol code */
    public byte getInterfaceProtocol();

    /** @return the interface StringDescriptor index code */
    public byte getInterfaceIndex();
}
