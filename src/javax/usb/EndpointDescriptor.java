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
 * Interface for USB endpoint descriptor.
 * <p>
 * See the USB 1.1 specification section 9.6.4.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public interface EndpointDescriptor extends Descriptor
{
    /**
	 * Get the address for this endpoint.
	 * <p>
	 * The most significant bit in the address is the
	 * {@link #getDirection() direction} of data flow
	 * (see the USB 1.1 specification table 9.10).
	 * The lower nibble (4 bits) of the address is the
	 * endpoint number (see the USB 1.1 specification table 9.10).
	 * @return The address of this endpoint.
	 */
    public byte getEndpointAddress();

    /**
	 * Get the direction of this endpoint.
	 * <p>
	 * The direction is either in (device-to-host) or out (host-to-device),
	 * meaning the endpoint is either a source or a sink, respectively.
	 * Control-type endpoints are bidirectional, but their address
	 * must specifiy a direction, since it's binary (two-state, instead
	 * of three-state; in, out, and bidirectional).  The USB 1.1 specification
	 * instructs in table 9.10 that the direction should be ignored for Control endpoints.
     * @return The direction of this endpoint.
     * @see javax.usb.UsbInfoConst#ENDPOINT_DIRECTION_IN
     * @see javax.usb.UsbInfoConst#ENDPOINT_DIRECTION_OUT
     */
    public byte getDirection();

    /**
	 * Get this endpoint's attributes.
	 * <p>
	 * See the USB 1.1 specification table 9.10 for details
	 * on endpoint attributes.
	 * @return The attribute of this endpoint.
	 */
    public byte getAttributes();

    /**
	 * Get the type of this endpoint.
	 * <p>
	 * See the USB 1.1 spcification table 9.10 for
	 * details on endpoint types.
	 * @return The endpoint's type.
	 * @see javax.usb.UsbInfoConst#ENDPOINT_TYPE_CONTROL
	 * @see javax.usb.UsbInfoConst#ENDPOINT_TYPE_BULK
	 * @see javax.usb.UsbInfoConst#ENDPOINT_TYPE_INT
	 * @see javax.usb.UsbInfoConst#ENDPOINT_TYPE_ISOC
	 */
    public byte getType();

    /**
	 * Get the max packet size for this endpoint.
	 * <p>
	 * See the USB 1.1 specification table 9.10 for
	 * details on endpoint max packet sizes,
	 * and section 5.3.2 for details on IRPs
	 * and their segmentation into max-packet-sized packets.
	 * @return The max packet size for this endpoint.
	 */
    public short getMaxPacketSize();

    /**
	 * Get the interval for this endpoint.
	 * <p>
	 * See the USB 1.1 specification table 9.10 for
	 * details on endpoint intervals.
	 * @return The endpoint interval.
	 */
    public byte getInterval();

}
