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
import java.security.*;

/**
 * Entry point for javax.usb.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 */
public class UsbHostManager
{
	private UsbHostManager() { }

	/** 
	 * Get the UsbServices implementation.
	 * @return the UsbServices for this USB host 
	 * @exception UsbException If the is an error creating the UsbSerivces implementation.
	 * @exception SecurityException If the caller does not have permission to access javax.usb.
	 */
	public static synchronized UsbServices getUsbServices() throws UsbException,SecurityException
	{
		if (null == usbServices)
			usbServices = createUsbServices();

		return usbServices;
	}

	private static UsbServices createUsbServices() throws UsbException,SecurityException
	{
		setupProperties();

		String className = System.getProperty(JAVAX_USB_USBSERVICES_PROPERTY);

		if (null == className)
			throw new UsbException(USBSERVICES_PROPERTY_NOT_DEFINED());

		try {
			return (UsbServices)Class.forName(className).newInstance();
		} catch ( ClassNotFoundException cnfE ) {
			throw new UsbException(USBSERVICES_CLASSNOTFOUNDEXCEPTION(className)+" : "+cnfE.getMessage());
		} catch ( ExceptionInInitializerError eiiE ) {
			throw new UsbException(USBSERVICES_EXCEPTIONININITIALIZERERROR(className)+" : "+eiiE.getMessage());
		} catch ( InstantiationException iE ) {
			throw new UsbException(USBSERVICES_INSTANTIATIONEXCEPTION(className)+" : "+iE.getMessage());
		} catch ( IllegalAccessException iaE ) {
			throw new UsbException(USBSERVICES_ILLEGALACCESSEXCEPTION(className)+" : "+iaE.getMessage());
		} catch ( ClassCastException ccE ) {
			throw new UsbException(USBSERVICES_CLASSCASTEXCEPTION(className)+" : "+ccE.getMessage());
		}
	}

	private static void setupProperties() throws UsbException,SecurityException
	{
		Properties p = new Properties();
		Properties sysP = System.getProperties();
		InputStream i = ClassLoader.getSystemResourceAsStream(JAVAX_USB_PROPERTIES_FILE);
		if (null == i)
			throw new UsbException("Properties file " + JAVAX_USB_PROPERTIES_FILE + " not found");
		try {
			p.load(i);
		} catch ( IOException ioE ) {
			throw new UsbException("IOException while reading properties file " + JAVAX_USB_PROPERTIES_FILE + " : " + ioE.getMessage());
		}
		try { i.close(); }
		catch ( IOException ioE ) { /* FIXME - log inability to close */ }

		Enumeration e = p.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String)e.nextElement();
			if (!sysP.containsKey(key))
				sysP.setProperty(key, p.getProperty(key));
		}
	}

	/**
	 * @throws java.lang.SecurityException if the "getUsbServices" javax.usb security
	 * permission is not granted to the calling code base
	 */
	private void checkPermission() throws SecurityException
	{
		AccessController.checkPermission( JavaxUsbPermission.GETUSBSERVICES_JAVAX_USB_PERMISSION );
	}

	private static final String USBSERVICES_PROPERTY_NOT_DEFINED()
	{ return "The property " + JAVAX_USB_USBSERVICES_PROPERTY + " is not defined as the implementation class of UsbServices"; }
	private static final String USBSERVICES_CLASSNOTFOUNDEXCEPTION(String c)
	{ return "The UsbServices implementation class "+c+" was found found"; }
	private static final String USBSERVICES_EXCEPTIONININITIALIZERERROR(String c)
	{ return "an Exception occurred during initialization of the UsbServices Class "+c; }
	private static final String USBSERVICES_INSTANTIATIONEXCEPTION(String c)
	{ return "An Exception occurred during instantiation of the UsbServices implementation "+c; }
	private static final String USBSERVICES_ILLEGALACCESSEXCEPTION(String c)
	{ return "An IllegalAccessException occurred while creating the UsbServices implementation "+c; }
	private static final String USBSERVICES_CLASSCASTEXCEPTION(String c)
	{ return "The class "+c+" does not implement UsbServices"; }

	public static final String JAVAX_USB_PROPERTIES_FILE = "javax.usb.properties";
	public static final String JAVAX_USB_USBSERVICES_PROPERTY = "javax.usb.services";

	private static UsbServices usbServices = null;
}
