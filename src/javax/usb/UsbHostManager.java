package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import javax.usb.util.*;

import javax.usb.os.*;
import javax.usb.event.*;

import java.security.AccessController;

/**
 * Manager class that is the "Facade" to the javax.usb API.  This class is final as 
 * it should not be subclassed.  The only ctor is private to avoid accidental construction.
 * <p>
 * <i>NOTE: this is a Singleton class that gives access to topology, UsbServices, ...</i>
 * <p>
 * See the GoF (Design Pattern book) page 127 for details about usage and applicability 
 * of the Singleton pattern.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 */
public final class UsbHostManager extends Object
{
    //-------------------------------------------------------------------------
    // Ctor(s)
    //

    /** Make ctor private to avoid construction */
    private UsbHostManager() {}

    //-------------------------------------------------------------------------
    // Public class methods
    //

    /** 
	 * @return the only instance of the UsbHostManager creating it if necessary
	 * @throws java.lang.SecurityException if current client not configured to access javax.usb
	 */
    public synchronized static UsbHostManager getInstance() throws SecurityException
    {
        if( instance == null )
            instance = new UsbHostManager();
        
        return instance;
    }

    //-------------------------------------------------------------------------
    // Public methods
    //

    /** @return the loaded UsbProperties for this host */
    public synchronized UsbProperties getUsbProperties() 
    { 
        if( usbProperties == null )
        {
            usbProperties = new DefaultProperties();
            usbProperties.loadProperties();
        }

        return usbProperties;
    }

    /** 
     * @return the UsbServices for this USB host 
     * @exception javax.usb.UsbException if something goes wrong accessing or creating
     * the UsbServices object for this platform
	 * @throws java.lang.SecurityException if the "getUsbServices" javax.usb security
	 * permission is not granted to the calling code base
     */
    public synchronized UsbServices getUsbServices() throws UsbException
    {
		//<TEMP>
		//checkPermission();
		//</TEMP>

        return UsbServicesUtility.getUsbServices();
    }

    //-------------------------------------------------------------------------
    // Private methods
    //

	/**
	 * 
	 * @throws java.lang.SecurityException if the "getUsbServices" javax.usb security
	 * permission is not granted to the calling code base
	 */
	private void checkPermission() throws SecurityException
	{
		AccessController.checkPermission( JavaxUsbPermission.GETUSBSERVICES_JAVAX_USB_PERMISSION );
	}

    /**
     * Creates the correct UsbServices for this host and platform
     * @return a UsbServices instance
     * @exception jcomm.usb.UsbException if the current platform is not supported
     */
    private UsbServices createUsbServices() throws UsbException
    {
        if (!getUsbProperties().isPropertyDefined( UsbProperties.JUSB_OS_SERVICES_PROP_NAME ))
            throw new UsbException( "The " + UsbProperties.JUSB_OS_SERVICES_PROP_NAME + " property must be defined as the classname of the implementation." );

        String className = getUsbProperties().getPropertyString( UsbProperties.JUSB_OS_SERVICES_PROP_NAME );

        try {
            return (UsbServices)(Class.forName(className)).newInstance();
        } catch( ClassNotFoundException cnfe ) {
			throw new UsbException( "Could not find UsbServices class = " + className );
        } catch( ClassCastException cce ) {
			throw new UsbException( "The UsbServices class: " + className + " does not implement the javax.usb.os.UsbServices interface" );
        } catch( Exception e ) {
			throw new UsbException( "Error instantiation UsbServices class name: " + className + ", Exception.message = " + e.getMessage() );
		}
    }

    //-------------------------------------------------------------------------
    // Instance variables
    //

    private UsbProperties usbProperties = null;
	private UsbServices usbServices = null;

    //-------------------------------------------------------------------------
    // Class variables
    //

    private static UsbHostManager instance = null;
}
