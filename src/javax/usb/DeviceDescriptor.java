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
 * Defines a USB device descriptor
 * <p>
 * This interface logically represents a USB device descriptor
 * which keeps all the necessary information describing it.  Getter methods
 * are provided for all the sections of a device descriptor.
 * </p>
 * <p>
 * <i>See section 9.6.1 of USB 1.1 specification for details</i>
 * </p>
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public interface DeviceDescriptor extends Descriptor
{
    //-------------------------------------------------------------------------
    // Public USB device descriptor specific methods
    //

    /**
	 * <p>
	 * Binary coded decimal release number of the USB specification
	 * supported by this device (i.e. 2.10 is 0x210)
	 * </p>
	 * @return a binary coded decimal for the level of USB supported by this spec 
	 */
    public short getBcdUsb();

    /**
	 * <p>
	 * USB class code for this device.
	 * The Device Classes are specified by the USB standard and the value
	 * of this field is between 1 and 0xFE (see USB specification section 9.6.1)
	 * </p> 
	 * @return the USB device class for this descriptor 
	 */
    public byte getDeviceClass();

	/**
	 * <p>
	 * USB sub-class code for this device.
	 * The Device SubClasses are specified by the USB standard .
	 * <p><i>
	 * According to the USB 1.1 specification (section 9.6.1) if the DeviceClass property
	 * is reset to 0 then this field is also reset to 0.  Also if the DeviceClass is not
	 * set to 0xFF then all values are reserved for assignment by the USB.
	 * </i></p>
	 * </p>
	 * 
	 * @return the USB device subclass for this descriptor
	 * @see javax.usb.DeviceDescriptor#getDeviceClass
	 */
    public byte getDeviceSubClass();

    /**
	 * <p>
	 * Protocol code for this device.
	 * The protocol codes are defined by the USB specification.  It may be set to
	 * 0 if does not use class-specified protocols on a device basis.  If this field
	 * is set to 0xFF then the device is using a vendors-specific protocol on a device
	 * basis.
	 * </p>
	 * @return the device protocol for this descriptor 
	 */
    public byte getDeviceProtocol();

    /**
	 * <p>
	 * The max packet size for the endpoint 0 of this device.  Valid values are
	 * one of: 8, 16, 32 or 64.
	 * </p>
	 * @return the maximum packet size for this descriptor 
	 */
    public byte getMaxPacketSize();

    /**
	 * <p>
	 * The vendor ID for a device is a unique number for the vendor of this 
	 * device assigned by the <a href="http://www.usb.org">main USB website</a>
	 * </p>
	 * @return the vendor ID for this descriptor 
	 */
    public short getVendorId();

    /**
	 * <p>
	 * The product ID assigned by the manufacturer of the device.
	 * </p>
	 * @return the product ID for this descriptor 
	 */
    public short getProductId();

    /**
	 * <p>
	 * The device release number in binary coded decimal (i.e.version 1.0.0 of a device
	 * is coded as 0x0100)
	 * </p>
	 * @return a binary coded decimal of the device release number 
	 */
    public short getBcdDevice();

    /**
	 * @return the index of StringDescriptor describing the manufacturer 
	 */
    public byte getManufacturerIndex();

    /**
	 * @return the index of StringDescriptor describing the product 
	 */
    public byte getProductIndex();

    /**
	 * @return the index of StringDescriptor describing the serial number 
	 */
    public byte getSerialNumberIndex();

    /**
	 * @return the number of possible configurations that the device supports 
	 */
    public byte getNumConfigs();
}
