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
 * Defines a USB endpoint descriptor
 * <p>
 * This interface logically represents a USB endpoint descriptor
 * which keeps all the necessary information describing it.  Getter methods
 * are provided for all the sections of a endpoint descriptor.
 * </p>
 * <p>
 * <i>See section 9.6.4 of USB 1.1 specification for details</i>
 * </p>
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface EndpointDescriptor extends Descriptor
{
    /** @return the address of the endpoint on the USB device described by this descriptor */
    public byte getEndpointAddress();

    /** @return this endpoint's attributes info */
    public byte getAttributes();

    /** @return the maximum packet size this endpoint is capable of sending or receiving */
    public short getMaxPacketSize();

    /** 
	 * <p><i>
	 * NOTE: ignored for bulk and control endpoints.  Must be 1 for isochronous 
	 * and 1-255 for Interrupt endpoints
	 * </i></p>
     * @return the interval for polling endpoint for data transfers (in ms) 
     */
    public byte getInterval();
}
