package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import javax.usb.event.*;
import javax.usb.util.UsbInfoListIterator;

/**
 * Defines a USB device.
 * <p>
 * It defines the main interface that all USB device objects should adhere too.  It 
 * represents a USB device attached to the USB host system.  It is mainly though this
 * interface that clients will be able to identify devices that they are interrested 
 * in as well as access
 * {@link javax.usb.UsbConfig UsbConfig},
 * {@link javax.usb.UsbInterface UsbInterface},
 * and {@link javax.usb.UsbEndpoint UsbEndpoint} objects.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbDevice extends UsbInfo
{
    //-------------------------------------------------------------------------
    // Public properties getter methods
    //

	/**
	 * Get the UsbPort on the 'parent' UsbHub that this device is connected to.
	 * @return the port on the parent UsbHub that this is attached to.
	 */
	public UsbPort getUsbPort();

    /**
	 * If this is a UsbHub.
	 * @return true if this is a UsbHub.
	 */
    public boolean isUsbHub();

    /**
	 * Get the manufacturer String.
	 * <p>
	 * If this device does not define a manufacturer String,
	 * null is returned.
	 * @return the manufacturer String.
	 * @see javax.usb.StringDescriptor
	 */
    public String getManufacturer();

    /**
	 * Get the serial number String.
	 * <p>
	 * If this device does not define a serial number String,
	 * null is returned.
	 * @return the serial number String.
	 * @see javax.usb.StringDescriptor
	 */
    public String getSerialNumber();

    /**
	 * Get the product String.
	 * <p>
	 * If this device does not define a product String,
	 * null is returned.
	 * @return the product String.
	 * @see javax.usb.StringDescriptor
	 */
    public String getProductString();

    /**
	 * Get the speed of the device.
	 * <p>
	 * Return the speed of the device as a String.
	 * One of the following values will be returned :
	 * <ul>
	 * <li>1.5 Mbps</li>
	 * <li>12 Mbps</li>
	 * </ul>
	 * @return the speed of this device.
	 */
    public String getSpeedString();

    /**
	 * Get the device class.
	 * @return the USB device class.
	 */
    public byte getDeviceClass();

    /**
	 * Get the device subclass.
	 * @return the USB device sub-class.
	 */
    public byte getDeviceSubClass();

    /**
	 * Get the device protocol.
	 * @return the USB device protocol.
	 */
    public byte getDeviceProtocol();

    /**
	 * Get the max packet size of this device's Default Control Pipe.
	 * @return the maximum packet size.
	 */
    public byte getMaxPacketSize();

    /**
	 * Get the number of configurations for this device.
	 * @return the number of configurations.
	 */
    public byte getNumConfigs();

    /**
	 * Get the vendor ID.
	 * <p>
	 * The vendor ID is a Binary Coded Decimal.
	 * @return the vendor ID.
	 */
    public short getVendorId();

    /**
	 * Get the product ID.
	 * <p>
	 * The product ID is a Binary Coded Decimal.
	 * @return the product ID.
	 */
    public short getProductId();

    /**
	 * Get the USB version for this device.
	 * <p>
	 * The USB version is a Binary Coded Decimal.
	 * @return the USB version for this device.
	 */
    public short getBcdUsb();

    /**
	 * Get the device version.
	 * <p>
	 * The device version is a Binary Coded Decimal.
	 * @return the device version number.
	 */
    public short getBcdDevice();

    /**
	 * Get an iteration of UsbConfigs contained by this UsbDevice.
	 * @return the UsbConfig objects contained by this UsbDevice.
	 */
    public UsbInfoListIterator getUsbConfigs();

	/**
	 * Get the specified UsbConfig.
	 * <p>
	 * Note the config numbering is 1-based, and config number 0 is
	 * reserved for the Not Configured state (see the USB 1.1 specification
	 * section 9.4.2).  Obviously, no UsbConfig exists for the Not Configured state.
	 * @return the specified UsbConfig.
	 * @throws javax.usb.UsbRuntimeException if the specified UsbConfig does not exist.
	 */
	public UsbConfig getUsbConfig( byte number );

	/**
	 * If this UsbDevice contains the specified UsbConfig.
	 * <p>
	 * Note that this will return false for the number zero, which indicates
	 * the device is in a Not Configured state.
	 * @return if the specified UsbConfig is contained in this UsbDevice.
	 */
	public boolean containsUsbConfig( byte number );

	/**
	 * Get the number of the active UsbConfig.
	 * <p>
	 * If the device is in a Not Configured state, this will return zero.
	 * @return the active config number.
	 */
	public byte getActiveUsbConfigNumber();

    /**
	 * Get the active UsbConfig.
	 * @return the active UsbConfig.
	 * @throws javax.usb.UsbRuntimeException if the device is in a Not Configured state.
	 */
    public UsbConfig getActiveUsbConfig();

	/**
	 * If this UsbDevice has reached the Configured state.
	 * <p>
	 * This returns true if the device has reached the Configured state
	 * as shown in the USB 1.1 specification table 9.1.
	 * @return if this is in the Configured state.
	 */
	public boolean isConfigured();

	/**
	 * Get the device descriptor.
	 * <p>
	 * This descriptor may be cached by the implementation.  If
	 * this is unacceptable for any reason, the descriptor
	 * should be retrieved using a
	 * {@link javax.usb.Request Request} through the
	 * {@link #getStandardOperations() StandardOperations}.
	 * @return the descriptor.
	 */
	public DeviceDescriptor getDeviceDescriptor();

	/**
	 * Get the specified string descriptor.
	 * <p>
	 * If the specified string descriptor does not exist,
	 * null is returned.
	 * <p>
	 * This descriptor may be cached by the implementation.  If
	 * this is unacceptable for any reason, the descriptor
	 * should be retrieved using a
	 * {@link javax.usb.Request Request} through the
	 * {@link #getStandardOperations() StandardOperations}.
	 * @param index the index of the string descriptor to get.
	 * @return the specified string descriptor.
	 * @throws javax.usb.UsbException if an error occurrs while getting the StringDescrpitor.
	 */
	public StringDescriptor getStringDescriptor( byte index ) throws UsbException;

	/**
	 * Get the String from the specified string descriptor.
	 * <p>
	 * If the specified string descriptor does not exist,
	 * null is returned.
	 * <p>
	 * This string may be cached by the implementation.  If
	 * this is unacceptable for any reason, the descriptor
	 * should be retrieved using a
	 * {@link javax.usb.Request Request} through the
	 * {@link #getStandardOperations() StandardOperations}.
	 * @return the String from the specified StringDescrpitor.
	 * @throws javax.usb.UsbException if an error occurred while getting the String.
	 * @see javax.usb.StringDescriptor
	 */
	public String getString( byte index ) throws UsbException;

	/**
	 * Returns a StandardOperations object that can be used to submit
	 * standard USB Request objects to this device.
	 * @return a StandardOperations object to use with this UsbDevice
	 * @see javax.usb.Request
	 * @see javax.usb.os.UsbServices#getRequestFactory
	 */
	public StandardOperations getStandardOperations();

	/**
	 * Returns a ClassOperations object that can be used to submit
	 * standard USB class Request objects to this device.
	 * @return a ClassOperations object to use with this UsbDevice
	 * @see javax.usb.Request
	 * @see javax.usb.os.UsbServices#getRequestFactory
	 */
	public ClassOperations getClassOperations();

	/**
	 * Returns a VendorOperations object that can be used to submit
	 * standard USB vendor Request objects to this device.
	 * @return a VendorOperations object to use with this UsbDevice
	 * @see javax.usb.Request
	 * @see javax.usb.os.UsbServices#getRequestFactory
	 */
	public VendorOperations getVendorOperations();

	/**
	 * Add a UsbDeviceListener to this UsbDevice.
	 * <p>
	 * The listener will receive UsbDeviceEvents.
	 * @param listener the UsbDeviceListener to add.
	 */
	public void addUsbDeviceListener( UsbDeviceListener listener );

	/**
	 * Remove a UsbDeviceListener from this UsbDevice.
	 * <p>
	 * The listener will not receive any more events from this UsbDevice.
	 * @param listener the listener to remove.
	 */
	public void removeUsbDeviceListener( UsbDeviceListener listener );

}
