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
 * Defines a USB string descriptor
 * <p>
 * This interface logically represents a USB string descriptor
 * which keeps all the necessary information describing it.  Getter methods
 * are provided for all the sections of a string descriptor.
 * <p>
 * For all methods which convert a byte[] (from the physical device) into a Java String,
 * the implementation will use "UTF-16LE" encoding for the conversion;
 * i.e., new String( byteArray, "UTF-16LE" ).  All byte[] requested from the device will use
 * the device's default lang-id (first 2 bytes of String Descriptor 0).
 * <p>
 * <i>See section 9.6.5 of USB 1.1 specification for details.</i>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface StringDescriptor extends Descriptor
{
	/**
	 * Return the UTF-16LE encoded String from this Descriptor.
	 * <p>
	 * For information about Unicode see
	 * <a href="http://www.unicode.org/">the Unicode website</a>.
	 * @return the string for this descriptor.
	 */
    public String getString();
}
