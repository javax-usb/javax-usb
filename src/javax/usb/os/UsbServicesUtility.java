package javax.usb.os;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import javax.usb.*;
import javax.usb.util.*;
import javax.usb.UsbException;

/**
 * Defines a utility class for creating UsbServices objects
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public class UsbServicesUtility extends Object
{
    //-------------------------------------------------------------------------
    // Ctor(s)
    //

    /** Prevents construction */
    private UsbServicesUtility() {}

    //-------------------------------------------------------------------------
    // Class methods
    //

    /**
     * @return the only UsbServices for this host platform
     * NOTE: lazily creates the correct instance for the correct platform
     * @exception jcomm.usb.UsbException if the current platform is not supported
     */
    public static UsbServices getUsbServices() throws UsbException
    {
        if( usbServices == null )
            usbServices = createUsbServices();

        return usbServices;
    }

    //-------------------------------------------------------------------------
    // Private methods
    //

    /**
     * Creates the correct UsbServices for this host and platform
     * @return a UsbServices instance
     * @exception jcomm.usb.UsbException if the current platform is not supported
     */
    private static UsbServices createUsbServices() throws UsbException
    {
        UsbServices usbServices = null;

        UsbProperties usbProperties = UsbHostManager.getInstance().getUsbProperties();

        if (!usbProperties.isPropertyDefined( UsbProperties.JUSB_OS_SERVICES_PROP_NAME ))
            throw new UsbException( "The " + UsbProperties.JUSB_OS_SERVICES_PROP_NAME +
                                    " property must be defined as the classname of the implementation." );

        String osServicesClassName = usbProperties.getPropertyString( UsbProperties.JUSB_OS_SERVICES_PROP_NAME );

        usbServices = createUsbServicesFromClassName( osServicesClassName );

        return usbServices;
    }

    /**
     * @return a new UsbServices instance creating it by calling the no-arg ctor of the class passed
     * @param className the class implementing the UsbServices interface who has a no-arg ctor
     * @exception jcomm.usb.UsbException if something goes wrong while creating the UsbServices
     */
    private static UsbServices createUsbServicesFromClassName( String className ) throws UsbException
    {
        try
        {
            Class usbServicesClass = Class.forName( className ); 

            UsbServices usbServices = (UsbServices)usbServicesClass.newInstance();

            return usbServices;
        }
        catch( ClassNotFoundException cnfe )
        { throw new UsbException( "Could not find UsbServices class = " + className ); }
        catch( ClassCastException cce )
        { throw new UsbException( "The UsbServices class: " + className + 
                                  " does not implement the jcomm.usb.os.UsbServices interface" ); }
        catch( Exception e ) 
        { throw new UsbException( "Error instantiation UsbServices class name: " + className +
                                  ", Exception.message = " + e.getMessage() ); }
    }

    //-------------------------------------------------------------------------
    // Class variables
    //

    private static UsbServices usbServices = null;
}
