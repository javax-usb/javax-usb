package javax.usb;

/*
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import javax.usb.event.*;

/**
 * Interface for a USB device.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public interface UsbDevice
{
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
	 * This is a convienence method.  The String may be cached.
	 * If the device does not support strings or does not define the
	 * manufacturer string, this returns null.
	 * @return The manufacturer String, or null.
	 * @exception UsbException If there was an error getting the StringDescriptor.
	 */
    public String getManufacturer() throws UsbException;

    /**
	 * Get the serial number String.
	 * <p>
	 * This is a convienence method.  The String may be cached.
	 * If the device does not support strings or does not define the
	 * serial number string, this returns null.
	 * @return The serial number String, or null.
	 * @exception UsbException If there was an error getting the StringDescriptor.
	 */
    public String getSerialNumber();

    /**
	 * Get the product String.
	 * <p>
	 * This is a convienence method.  The String may be cached.
	 * If the device does not support strings or does not define the
	 * product string, this returns null.
	 * @return The product String, or null.
	 * @exception UsbException If there was an error getting the StringDescriptor.
	 */
    public String getProductString();

    /**
	 * Get the speed of the device.
	 * <p>
	 * The speed will be one of:
	 * <ul>
	 * <li>{@link javax.usb.UsbConst#SPEED_UNKNOWN UsbConst.SPEED_UNKNOWN}</li>
	 * <li>{@link javax.usb.UsbConst#SPEED_LOW UsbConst.SPEED_LOW}</li>
	 * <li>{@link javax.usb.UsbConst#SPEED_FULL UsbConst.SPEED_FULL}</li>
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
	 * Note that this will return false for the number zero, which indicates
	 * the device is in a Not Configured state.
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
	 * @return the active UsbConfig.
//FIXME - create NotConfiguredException?
	 * @throws RuntimeException if the device is in a Not Configured state.
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
	 * This is a convienence method.  The String may be cached.
	 * If the device does not support strings or does not define the
	 * specified string, this returns null.
	 * @param index The index of the string to get.
	 * @return The specified String.
	 * @exception UsbException If an error occurred while getting the String.
	 */
	public String getString( byte index ) throws UsbException;

	/**
	 * Submit a ControlUsbIrp synchronously to the Default Control Pipe.
	 * @param irp The ControlUsbIrp.
	 * @exception UsbException If an error occurrs.
	 */
	public void syncSubmit( UsbIrp.ControlUsbIrp irp ) throws UsbException;

	/**
	 * Submit a ControlUsbIrp asynchronously to the Default Control Pipe.
	 * @param irp The ControlUsbIrp.
	 * @exception UsbException If an error occurrs.
	 */
	public void asyncSubmit( UsbIrp.ControlUsbIrp irp ) throws UsbException;

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
