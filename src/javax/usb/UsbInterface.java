package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.io.*;
import java.util.*;

/**
 * Interface for a USB interface.
 * <p>
 * This object actually represents a specific 'alternate' setting for a USB interface.
 * Interfaces must have at least one alternate setting, and one and only one setting
 * is active per interface.  All settings share the same interface
 * number.  If this interface setting is not active, it cannot be claimed or released;
 * the active interface setting should be used for claiming and releasing ownership of
 * the interface; also no action may be taken on any parts of this interface
 * setting, if the setting is not active.  Any attempt to perform action on objects
 * belonging to an inactive interface setting will throw a NotActiveException.
 * @author Dan Streetman
 */
public interface UsbInterface
{
	/**
	 * Claim this interface.
	 * <p>
	 * This will only succeed if the interface is not claimed (in Java)
	 * and the native claim (if applicable) succeeds.
	 * This will attempt whatever claiming the native implementation provides,
	 * if any.
	 * <p>
	 * This must be done before opening and/or using any UsbPipes.
	 * <p>
	 * If the interface has already been claimed (in Java), this method with do nothing.
	 * @exception UsbException If the interface could not be claimed.
	 * @exception NotActiveException If this interface setting is not
	 * {@link #isActive() active}.
	 */
	public void claim() throws UsbException,NotActiveException;

	/**
	 * Release this interface.
	 * <p>
	 * This will only succeed if the interface is claimed (in Java).
	 * This will release the claim in Java and attempt to release whatever
	 * native claims were made (if any).
	 * <p>
	 * This should be done after the interface is no longer being used.
	 * All pipes must be closed before this can be released.
	 * <p>
	 * If the interface is not claimed (in Java), this method will do nothing.
	 * @exception UsbException If the interface could not be released.
	 * @exception NotActiveException If this interface setting is not
	 * {@link #isActive() active}.
	 */
	public void release() throws UsbException,NotActiveException;

	/**
	 * If this interface is claimed.
	 * <p>
	 * This will return true if claimed in Java.
	 * This may, depending on implementation, return true if
	 * claimed natively (outside of Java)
	 * <p>
	 * If this UsbInterface is not {@link #isActive() active}, this will
	 * return if the active alternate setting is active.
	 * @return If this interface is claimed (in Java).
	 */
	public boolean isClaimed();

	/**
	 * If this interface alternate setting is active.
	 * <p>
	 * The interface itself is active if and only if its parent
	 * configuration is {@link javax.usb.UsbConfig#isActive() active}.
	 * If the interface itself is not active, none of its alternate settings
	 * are active.
	 * @return if this interface alternate setting is active.
	 */
	public boolean isActive();

	/**
	 * Get the number of alternate settings for this interface.
	 * @return the number of alternate settings for this interface.
	 */
	public int getNumSettings();

	/**
	 * Get the number of the active alternate setting.
	 * @return The active setting number for this interface.
	 * @exception NotActiveException If the interface (and parent config) is not
	 * {@link #isActive() active}.
	 */
	public byte getActiveSettingNumber() throws NotActiveException;

	/**
	 * Get the active alternate setting.
	 * <p>
	 * @return The active setting for this interface.
	 * @exception NotActiveException If this interface (and parent config) is not
	 * {@link #isActive() active}.
	 */
	public UsbInterface getActiveSetting() throws NotActiveException;

	/**
	 * Get the specified alternate setting.
	 * <p>
	 * If the specified setting does not exist, this returns null.
	 * @return The specified alternate setting, or null.
	 */
	public UsbInterface getSetting( byte number );

	/**
	 * If the specified alternate setting exists.
	 * @param number The alternate setting number.
	 * @return If the alternate setting exists.
	 */
	public boolean containsSetting( byte number );

	/**
	 * Get all alternate settings for this interface.
	 * <p>
	 * This returns all alternate settings, including this one.
	 * @return All alternate settings for this interface.
	 */
	public List getSettings();

    /**
	 * Get all endpoints for this interface setting.
	 * @return All endpoints for this setting.
	 */
    public List getUsbEndpoints();

	/**
	 * Get a specific UsbEndpoint.
	 * <p>
	 * If this does not contain the specified endpoint, this returns null.
	 * @param address The address of the UsbEndpoint to get.
	 * @return The specified UsbEndpoint, or null.
	 */
	public UsbEndpoint getUsbEndpoint( byte address );

	/**
	 * If the specified UsbEndpoint is contained in this UsbInterface.
	 * @param address The endpoint address.
	 * @return If this UsbInterface contains the specified UsbEndpoint.
	 */
	public boolean containsUsbEndpoint( byte address );

    /**
	 * Get the parent UsbConfig that this UsbInterface belongs to.
	 * @return The UsbConfig that this interface belongs to.
	 */
    public UsbConfig getUsbConfig();

	/**
	 * Get the interface descriptor.
	 * <p>
	 * The descriptor may be cached.
	 * @return The interface descriptor.
	 */
	public InterfaceDescriptor getInterfaceDescriptor();

	/**
	 * Get the interface String.
	 * <p>
	 * This is a convienence method.  The String may be cached.
	 * If the device does not support strings or does not define the
	 * interface string, this returns null.
	 * @return The interface String, or null.
	 * @exception UsbException If there was an error getting the StringDescriptor.
	 * @exception UnsupportedEncodingException If the string encoding is not supported.
	 */
	public String getInterfaceString() throws UsbException,UnsupportedEncodingException;
}
