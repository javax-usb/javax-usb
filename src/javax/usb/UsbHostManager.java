package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import javax.usb.util.*;

import javax.usb.os.*;
import javax.usb.event.*;

/**
 * Manager class that is the "Facade" to the javax.usb API.  This class is final as 
 * it should not be subclassed.  The only ctor is private to avoid accidental construction.
 * <p>
 * <i>NOTE: this is a Singleton class that gives access to topology, UsbServices, ...</i>
 * <p>
 * See the GoF (Design Pattern book) page 127 for details about usage and applicability 
 * of the Singleton pattern.
 * </p>
 * </p>
 * @author E. Michael Maximilien
 * @since 0.8.0
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
    public UsbProperties getUsbProperties() 
    { 
        if( usbProperties == null )
        {
            usbProperties = new DefaultProperties();
            usbProperties.loadProperties();

            initTracer();
        }

        return usbProperties;
    }

    /** 
     * @return the UsbServices for this USB host 
     * @exception javax.usb.UsbException if something goes wrong accessing or creating
     * the UsbServices object for this platform
     */
    public UsbServices getUsbServices() throws UsbException
    {
        return UsbServicesUtility.getUsbServices();
    }

    //-------------------------------------------------------------------------
    // Private methods
    //

    /** Initialize the Tracer looking at the UsbProperties file */
    private void initTracer()
    {
        if( usbProperties.isPropertyDefined( UsbProperties.JUSB_TRACING_PROP_NAME ) )
        {
            String tracingPropValue = usbProperties.getPropertyString( UsbProperties.JUSB_TRACING_PROP_NAME );

            if( tracingPropValue.equalsIgnoreCase( UsbProperties.JUSB_TRACING_ON_PROP_VALUE ) ||
                tracingPropValue.equalsIgnoreCase( UsbProperties.JUSB_TRACING_TRUE_PROP_VALUE ) )
                Tracer.getInstance().setOn( true );
        }
    }

    //-------------------------------------------------------------------------
    // Instance variables
    //

    private UsbProperties usbProperties = null;

    //-------------------------------------------------------------------------
    // Class variables
    //

    private static UsbHostManager instance = null;
}
