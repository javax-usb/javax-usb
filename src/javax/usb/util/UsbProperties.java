package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import java.util.Enumeration;

import javax.usb.UsbException;

/**
 * Sample interface that contains constants for all the UsbProperties (names, default
 * values, etc...) and some methods that the implementing property class must define.
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface UsbProperties 
{
    //-------------------------------------------------------------------------
    // Public methods
    //

    /** Loads the jusb.properties file from the "javax/usb/res" directory */
    public void loadProperties();

    /** @return true if the UsbProperties is loaded */
    public boolean isLoaded();

    /** @return the last exception if any after the last load */
    public Exception getLastException();

    /**
     * @return the String property by name specified looking in System then the "jusb.properties" resource bundle
     * @param propName the property name to search for
     */
    public String getPropertyString( String propName );

    /**
     * @return true if this property is defined in the Java System properties or in
     * the jusb.properties resource file
     * @param propName the property name to look for
     */
    public boolean isPropertyDefined( String propName );

    /** @return an enumeration of properties names defined  */
    public Enumeration getPropertyNames();

    //-------------------------------------------------------------------------
    // Public constants (non-properties name)
    //

	public static final String SLASH = System.getProperty( "file.separator" );

    /** Constant defining the resource name for the "jusb.properties" file */
    public static final String JUSB_PROPERTIES_FILENAME = "javax"+SLASH+"usb"+SLASH+"res"+SLASH+"jusb.properties";

    //-------------------------------------------------------------------------
    // Public constants (properties name)
    //

    public static final String JUSB_TRACING_PROP_NAME = "javax.usb.tracing";

    public static final String JUSB_STATE_MACHINE_FACTORY_PROP_NAME = "javax.usb.util.stateMachineFactory";

    public static final String JUSB_OS_SERVICES_PROP_NAME = "javax.usb.os.services";

	public static final String JUSB_HOME_PROP_NAME = "javax.usb.home";

    //-------------------------------------------------------------------------
    // Public constants (properties value)
    //

    public static final String JUSB_TRACING_ON_PROP_VALUE = "ON";
    public static final String JUSB_TRACING_TRUE_PROP_VALUE = "TRUE";
}
