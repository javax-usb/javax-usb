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
 * Interface for a USB string descriptor.
 * <p>
 * To convert from byte[] to String, the implementation should
 * use the first available of these encodings:
 * <ul>
 * <li>UnicodeLittleUnmarked</li>
 * <li>UnicodeLittle</li>
 * <li>UTF-16LE</li>
 * <li>ASCII (after conversion from 16 bit to 8 bit)</li>
 * </ul>
 * <p>
 * See the USB 1.1 specification section 9.6.5.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 */
public interface StringDescriptor extends Descriptor
{
	/**
	 * Get the String.
	 * <p>
	 * For information about Unicode see
	 * <a href="http://www.unicode.org/">the Unicode website</a>.
	 * @return The String for this descriptor.
	 */
    public String getString();
}
