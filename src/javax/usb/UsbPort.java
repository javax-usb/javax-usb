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
 * Defines a USB port.
 * <p>
 * USB ports belong to a USB hub.  In this API, they represent
 * downstream ports.  Upstream and (non-declared) internal ports are
 * not represented in this API.  Internal ports that are reported
 * as downstream ports by the USB hub (in the USB hub descriptor)
 * are represented.
 * See the USB 1.1 specification sec 11.16 for details on USB hub ports.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbPort extends UsbInfo
{
    /**
	 * Get the number of this port.
	 * <p>
	 * According to the USB 1.1 specification, port numbers are
	 * 1-based, not 0-based.
	 * See the USB 1.1 specification table 11.8 offset 7.
	 * Since the number is 1-based, the first port on a hub
	 * has port number 1.  There is a maximum of 255 ports
	 * on a single hub (so the maximum port number is also 255).
	 * Note that the port number is one greater
	 * than its index in its 'parent' UsbHub's getUsbPorts() list.
	 * Also, the port number is actually an <strong>unsigned</strong>
	 * byte; however Java does not provide unsigned bytes, so
	 * any port number above 127 will appear negative.
	 * To convert this byte into an unsigned integer you can use
	 * {@link javax.usb.util.UsbUtil#unsignedInt(byte) UsbUtil}.
	 * @return the number of this port.
	 * @see javax.usb.util.UsbUtil#unsignedInt(byte)
	 */
    public byte getPortNumber();

    /**
	 * Get the 'parent' UsbHub.
	 * @return USB hub that this port belongs to
	 */
    public UsbHub getUsbHub();

    /**
	 * Get the UsbDevice attached to this UsbPort.
	 * <p>
	 * This returns the attached UsbDevice, or null if
	 * no UsbDevice is attached.
	 * @return the attached UsbDevice to this port
	 */
    public UsbDevice getUsbDevice();

    /** @return true if a device is attached to this port and false otherwise */
    public boolean isUsbDeviceAttached();
}
