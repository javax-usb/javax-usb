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
 * Interface for a USB device descriptor.
 * <p>
 * See the USB 1.1 specification section 9.6.1.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public interface DeviceDescriptor extends Descriptor
{
    /**
	 * Get the device class.
	 * @return The device class.
	 */
    public byte getDeviceClass();

	/**
	 * Get the device subclass.
	 * @return The device subclass.
	 */
    public byte getDeviceSubClass();

    /**
	 * Get the device protocol.
	 * @return The device protocol.
	 */
    public byte getDeviceProtocol();

    /**
	 * Get the maximum packet size for the Default Control Pipe.
	 * @return The maximum packet size for the Default Control Pipe.
	 */
    public byte getMaxPacketSize();

    /**
	 * Get the device's vendor ID.
	 * @return The device's vendor ID.
	 */
    public short getVendorId();

    /**
	 * Get the device's product ID.
	 * @return The device's product ID.
	 */
    public short getProductId();

    /**
	 * Get the Device level in Binary Coded Decimal.
	 * @return The device level in BCD.
	 */
    public short getBcdDevice();

    /**
	 * Get the USB Binary Coded Decimal.
	 * <p>
	 * USB specification leve supported by this device, in BCD.
	 * @return USB BCD for this device.
	 */
    public short getBcdUsb();

    /**
	 * Get the manufacturer string index.
	 * <p>
	 * If this is 0, the device has no manufacturer string.
	 * @return The manufacturer string index.
	 */
    public byte getManufacturerIndex();

    /**
	 * Get the product string index.
	 * <p>
	 * If this is 0, the device has no product string.
	 * @return The product string index.
	 */
    public byte getProductIndex();

    /**
	 * Get the serial number string index.
	 * <p>
	 * If this is 0, the device has no serial number string.
	 * @return The serial number string index.
	 */
    public byte getSerialNumberIndex();

    /**
	 * Get the number of configurations for the device.
	 * @return The number of configurations for this device.
	 */
    public byte getNumConfigs();
}
