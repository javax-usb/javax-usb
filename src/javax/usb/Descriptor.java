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
 * Defines the super-interface for all USB descriptors
 * <p>
 * See section 9.5 and 9.6 (and sub-sections) of the USB 1.1 specification
 * for details on USB descriptors.
 * </p>
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface Descriptor
{
    //-------------------------------------------------------------------------
    // USB descriptor specific public method
    //

    /** @return the length of this descriptor */
    public byte getLength();

    /** @return the type of this descriptor */
    public byte getType();

	/**
	 * Get this descriptor's byte[] representation (as it would appear 'on the wire')
	 * <p><i>WARNING: any word-sized fields are LITTLE-ENDIAN!</i></p>
	 * @return this descriptor byte[] representation
	 */
	public byte[] toBytes();

    //-------------------------------------------------------------------------
    // Public methods
    //

    /**
     * Accepts a DescriptorVisitor objects
     * @param visitor the DescriptorVisitor object
     */
    public void accept( DescriptorVisitor visitor );
}
