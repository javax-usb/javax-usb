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
 * Defines a USB device endpoint.
 * <p>
 * See the USB 1.1 specification sec 9.6.4 and sec 5.3.1
 * for more information on USB device endpoints.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbEndpoint extends UsbInfo
{
    /**
	 * Get the address for this endpoint.
	 * <p>
	 * The number is actually an <i>unsigned byte</i>.  However, since
	 * Java does not provide unsigned numbers, all addresses with
	 * a direction of in (MSb is set) will appear negative.  You
	 * can use {@link javax.usb.util.UsbUtil#unsignedInt(byte) UsbUtil}
	 * to convert to an unsigned integer.
	 * <p>
	 * Note that the MSb represents the 'direction' of data flow
	 * to/from this endpoint (data source/sink), except for Control-type
	 * endpoints which are bidirectional.  According to the USB 1.1
	 * specification table 9.10, the MSb of the endpoint address should
	 * 'be ignored for Control type endpoints', so the value of the MSb
	 * is therefore not specified by this API and should be ignored.
	 * Masking the endpoint address with
	 * {@link javax.usb.UsbInfoConst#ENDPOINT_NUMBER_MASK ENDPOINT_NUMBER_MASK}
	 * will give the endpoint 'number' (see USB 1.1 specification table 9.10), which
	 * consists of the lower nibble of the endpoint address byte.
	 * @return the address of this endpoint.
	 * @see #getDirection()
	 */
    public byte getEndpointAddress();

    /**
	 * Get the direction of this endpoint.
	 * <p>
	 * The direction is either in (device-to-host) or out (host-to-device),
	 * meaning the endpoint is either a source or a sink, respectively.
	 * Note that Control-type endpoints are bidirectional, but their address
	 * must specifiy a direction, since it's binary (two-state, instead
	 * of three-state; in, out, and bidirectional).  The USB 1.1 specification
	 * instructs in table 9.10 that the direction is 'ignored for Control endpoints'.
     * @return direction of this endpoint
     * @see javax.usb.UsbInfoConst#ENDPOINT_DIRECTION_IN
     * @see javax.usb.UsbInfoConst#ENDPOINT_DIRECTION_OUT
     */
    public byte getDirection();

    /**
	 * Get this endpoint's attributes.
	 * <p>
	 * See the USB 1.1 specification table 9.10 for details
	 * on endpoint attributes.
	 * @return the attribute of this endpoint
	 */
    public byte getAttributes();

    /**
	 * Get the type of this endpoint.
	 * <p>
	 * See the USB 1.1 spcification table 9.10 for
	 * details on endpoint types.
	 * @return this endpoint's type
	 * @see javax.usb.UsbInfoConst#ENDPOINT_TYPE_CONTROL
	 * @see javax.usb.UsbInfoConst#ENDPOINT_TYPE_BULK
	 * @see javax.usb.UsbInfoConst#ENDPOINT_TYPE_INT
	 * @see javax.usb.UsbInfoConst#ENDPOINT_TYPE_ISOC
	 */
    public byte getType();

    /**
	 * Get the max packet size for this endpoint.
	 * <p>
	 * See the USB specification table 9.10 for
	 * details on endpoint max packet sizes.  Note that
	 * the endpoint max packet size does not correspond to
	 * the max sized submissions allowed, since lower
	 * layers of this API and/or the platform's javax.usb
	 * implementation and/or USB implementation will/may segment
	 * submissions into max-packet-sized packets.
	 * See the USB 1.1 specification sec 5.3.2 for details on IRPs
	 * and their segmentation into max-packet-sized packets.
	 * @return the max packet size required for this endpoint
	 */
    public short getMaxPacketSize();

    /**
	 * Get the interval for this endpoint.
	 * <p>
	 * Note that this value is mostly unimportant to users of this
	 * API.  It is used by the Host Controller (driver) to
	 * interact with different type endpoints in different ways.
	 * See the USB 1.1 specification table 9.10 for
	 * details on endpoint intervals.
	 * @return this endpoint interval
	 */
    public byte getInterval();

	/**
	 * Get the 'parent' UsbDevice that this UsbEndpoint belongs to.
	 * @return the UsbDevice associated with this endpoint
	 */
	public UsbDevice getUsbDevice();

    /**
	 * Get the 'parent' UsbInterface that this UsbEndpoint belongs to.
	 * @return the UsbInterface associated with this endpoint
	 */
    public UsbInterface getUsbInterface();

	/**
	 * Get the Descriptor for this UsbEndpoint.
	 * <p>
	 * See the USB 1.1 specification sec 9.6.4 and table 9.10 for details on
	 * endpoints and their associated descriptors.  All methods in this Class
	 * that refer to endpoint descriptor fields/methods will agree.
	 * For example, <code>getndpointAddress() == getEndpointDescriptor().getEndpointAddress()</code>.
	 * <p>
	 * This descriptor may be cached by the implementation.  If
	 * this is unacceptable for any reason, the descriptor
	 * should be retrieved using a
	 * {@link javax.usb.Request Request} through the
	 * {@link javax.usb.StandardOperations StandardOperations}.
	 * @return the descriptor for this UsbEndpoint.
	 */
	public EndpointDescriptor getEndpointDescriptor();

    /**
	 * Get the UsbPipe attached to this UsbEndpoint.
	 * <p>
	 * This is the only method of communication to this endpoint.
	 * The UsbPipe object represents a 'logical' pipe to the endpoint.
	 * The type of the endpoint determines the direction of data flow
	 * through the pipe.
	 * @return This UsbEndpoint's UsbPipe.
	 */
    public UsbPipe getUsbPipe();
}
