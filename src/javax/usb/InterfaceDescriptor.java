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
 * Interface for a USB interface descriptor.
 * <p>
 * See the USB 1.1 specification section 9.6.3.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public interface InterfaceDescriptor extends Descriptor
{
    /**
	 * Get the interface number.
	 * @return The interface number.
	 */
    public byte getInterfaceNumber();

    /**
	 * Get the alternate setting number.
	 * @return The alternate setting number.
	 */
    public byte getAlternateSetting();

    /**
	 * Get the number of endpoints for this interface setting.
	 * @return The number of endpoints for this interface setting.
	 */
    public byte getNumEndpoints();

    /**
	 * Get the interface class.
	 * @return The interface class.
	 */
    public byte getInterfaceClass();

    /**
	 * Get the interface subclass.
	 * @return The interface subclass.
	 */
    public byte getInterfaceSubClass();

    /**
	 * Get the interface protocol.
	 * @return The interface protocol.
	 */
    public byte getInterfaceProtocol();

    /**
	 * Get the interface string index.
	 * <p>
	 * If this is 0, the interface does not have an interface string.
	 * @return The interface string index.
	 */
    public byte getInterfaceIndex();
}
