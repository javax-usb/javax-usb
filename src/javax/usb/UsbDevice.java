package javax.usb;

/*
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.io.*;
import java.util.*;

import javax.usb.event.*;

/**
 * Interface for a USB device.
 * <p>
 * The submission methods contained in this UsbDevice operate on the device's Default Control Pipe.
 * The device does not have to be {@link #isConfigured() configured} to use the Default Control Pipe.
 * <p>
 * The implementation is not required to be Thread-safe.  If a Thread-safe UsbDevice
 * is required, use a {@link com.ibm.jusb.util.UsbUtil#synchronizedUsbDevice(UsbDevice) synchronizedUsbDevice}.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public interface UsbDevice
{
	/**
	 * Get the UsbPort on the parent UsbHub that this device is connected to.
	 * @return The port on the parent UsbHub that this is attached to.
	 */
	public UsbPort getParentUsbPort();

    /**
	 * If this is a UsbHub.
	 * @return true if this is a UsbHub.
	 */
    public boolean isUsbHub();

    /**
	 * Get the manufacturer String.
	 * <p>
	 * This is a convienence method, which uses
	 * {@link #getString(byte) getString}.
	 * @return The manufacturer String, or null.
	 * @exception UsbException If there was an error getting the StringDescriptor.
	 * @exception UnsupportedEncodingException If the string encoding is not supported.
	 */
    public String getManufacturerString() throws UsbException,UnsupportedEncodingException;

    /**
	 * Get the serial number String.
	 * <p>
	 * This is a convienence method, which uses
	 * {@link #getString(byte) getString}.
	 * @return The serial number String, or null.
	 * @exception UsbException If there was an error getting the StringDescriptor.
	 * @exception UnsupportedEncodingException If the string encoding is not supported.
	 */
    public String getSerialNumberString() throws UsbException,UnsupportedEncodingException;

    /**
	 * Get the product String.
	 * <p>
	 * This is a convienence method, which uses
	 * {@link #getString(byte) getString}.
	 * @return The product String, or null.
	 * @exception UsbException If there was an error getting the StringDescriptor.
	 * @exception UnsupportedEncodingException If the string encoding is not supported.
	 */
    public String getProductString() throws UsbException,UnsupportedEncodingException;

    /**
	 * Get the speed of the device.
	 * <p>
	 * The speed will be one of:
	 * <ul>
	 * <li>{@link javax.usb.UsbConst#DEVICE_SPEED_UNKNOWN UsbConst.DEVICE_SPEED_UNKNOWN}</li>
	 * <li>{@link javax.usb.UsbConst#DEVICE_SPEED_LOW UsbConst.DEVICE_SPEED_LOW}</li>
	 * <li>{@link javax.usb.UsbConst#DEVICE_SPEED_FULL UsbConst.DEVICE_SPEED_FULL}</li>
	 * </ul>
	 * @return The speed of this device.
	 */
    public Object getSpeed();

    /**
	 * Get all UsbConfigs for this device.
	 * <p>
	 * The List is unmodifiable.
	 * @return All UsbConfigs for this device.
	 */
    public List getUsbConfigs();

	/**
	 * Get the specified UsbConfig.
	 * <p>
	 * If the specified UsbConfig does not exist, null is returned.
	 * Config number 0 is reserved for the Not Configured state (see the USB 1.1 specification
	 * section 9.4.2).  Obviously, no UsbConfig exists for the Not Configured state.
	 * @return The specified UsbConfig, or null.
	 */
	public UsbConfig getUsbConfig( byte number );

	/**
	 * If this UsbDevice contains the specified UsbConfig.
	 * <p>
	 * This will return false for zero (the Not Configured state).
	 * @return If the specified UsbConfig is contained in this UsbDevice.
	 */
	public boolean containsUsbConfig( byte number );

	/**
	 * Get the number of the active UsbConfig.
	 * <p>
	 * If the device is in a Not Configured state, this will return zero.
	 * @return The active config number.
	 */
	public byte getActiveUsbConfigNumber();

    /**
	 * Get the active UsbConfig.
	 * <p>
	 * If this device is Not Configured, this returns null.
	 * @return The active UsbConfig, or null.
	 */
    public UsbConfig getActiveUsbConfig();

	/**
	 * If this UsbDevice is configured.
	 * <p>
	 * This returns true if the device is in the configured state
	 * as shown in the USB 1.1 specification table 9.1.
	 * @return If this is in the Configured state.
	 */
	public boolean isConfigured();

	/**
	 * Get the device descriptor.
	 * <p>
	 * The descriptor may be cached.
	 * @return The device descriptor.
	 */
	public DeviceDescriptor getDeviceDescriptor();

	/**
	 * Get the specified string descriptor.
	 * <p>
	 * This is a convienence method.  The StringDescriptor may be cached.
	 * If the device does not support strings or does not define the
	 * specified string descriptor, this returns null.
	 * @param index The index of the string descriptor to get.
	 * @return The specified string descriptor.
	 * @exception UsbException If an error occurred while getting the string descriptor.
	 */
	public StringDescriptor getStringDescriptor( byte index ) throws UsbException;

	/**
	 * Get the String from the specified string descriptor.
	 * <p>
	 * This is a convienence method, which uses
	 * {@link #getStringDescriptor(byte) getStringDescriptor()}.
	 * {@link javax.usb.StringDescriptor#getString() getString()}.
	 * @param index The index of the string to get.
	 * @return The specified String.
	 * @exception UsbException If an error occurred while getting the String.
	 * @exception UnsupportedEncodingException If the string encoding is not supported.
	 */
	public String getString( byte index ) throws UsbException,UnsupportedEncodingException;

	/**
	 * Submit a ControlUsbIrp synchronously to the Default Control Pipe.
	 * @param irp The ControlUsbIrp.
	 * @exception UsbException If an error occurrs.
	 */
	public void syncSubmit( ControlUsbIrp irp ) throws UsbException;

	/**
	 * Submit a ControlUsbIrp asynchronously to the Default Control Pipe.
	 * @param irp The ControlUsbIrp.
	 * @exception UsbException If an error occurrs.
	 */
	public void asyncSubmit( ControlUsbIrp irp ) throws UsbException;

	/**
	 * Submit a List of ControlUsbIrps synchronously to the Default Control Pipe.
	 * <p>
	 * All ControlUsbIrps are guaranteed to be atomically (with respect to other clients
	 * of this API) submitted to the Default Control Pipe.  Atomicity on a native level
	 * is implementation-dependent.
	 * @param list The List of ControlUsbIrps.
	 * @exception UsbException If an error occurrs.
	 */
	public void syncSubmit( List list ) throws UsbException;

	/**
	 * Submit a List of ControlUsbIrps asynchronously to the Default Control Pipe.
	 * <p>
	 * All ControlUsbIrps are guaranteed to be atomically (with respect to other clients
	 * of this API) submitted to the Default Control Pipe.  Atomicity on a native level
	 * is implementation-dependent.
	 * @param list The List of ControlUsbIrps.
	 * @exception UsbException If an error occurrs.
	 */
	public void asyncSubmit( List list ) throws UsbException;

	/**
	 * Create a ControlUsbIrp.
	 * <p>
	 * This creates a ControlUsbIrp that may be optimized for use on
	 * this UsbDevice.  Using this UsbIrp instead of a
	 * {@link javax.usb.util.DefaultControlUsbIrp DefaultControlUsbIrp}
	 * may increase performance or decrease memory requirements.
	 * <p>
	 * The UsbDevice cannot require this ControlUsbIrp to be used, all submit
	 * methods <i>must</i> accept any ControlUsbIrp implementation.
	 * @param bmRequestType The bmRequestType.
	 * @param bRequest The bRequest.
	 * @param wValue The wValue.
	 * @param wIndex The wIndex.
	 * @return A ControlUsbIrp ready for use.
	 */
	public ControlUsbIrp createControlUsbIrp(byte bmRequestType, byte bRequest, short wValue, short wIndex);

	/**
	 * Add a UsbDeviceListener to this UsbDevice.
	 * @param listener The UsbDeviceListener to add.
	 */
	public void addUsbDeviceListener( UsbDeviceListener listener );

	/**
	 * Remove a UsbDeviceListener from this UsbDevice.
	 * @param listener The listener to remove.
	 */
	public void removeUsbDeviceListener( UsbDeviceListener listener );

}
