package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.util.*;
import java.io.*;

import javax.usb.UsbException;

/**
 * Default implementation of the JposProperties interface
 * Loads/looks for the javax USB properties from the System properties and
 * from the javax/usb/res/jusb.properties files
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class DefaultProperties extends Object implements UsbProperties 
{
    //-------------------------------------------------------------------------
    // Public methods
    //

    /** Loads the current set of properties from the file */
    public void loadProperties()
    {
        loaded = false;
        lastException = null;

        usbProperties = findProperties( UsbProperties.JUSB_PROPERTIES_FILENAME );
    }

    /** @return true if the UsbProperties is loaded */
    public boolean isLoaded() { return loaded; }

    /** @return the last exception if any after the last load */
    public Exception getLastException() { return lastException; }

    /** 
     * @return the String property value for the String prop name passed
     * @param propName the String property name
     */
    public String getPropertyString( String propName )
    {
        String propValue = "";

        if( System.getProperties().containsKey( propName ) )
            propValue = System.getProperties().getProperty( propName );
        else
        if( usbProperties != null )
            propValue = usbProperties.getProperty( propName );

        return propValue;
    }

    /** 
     * @return true if the property is defined
     * <b>NOTE:</b> looks in the System properties first then the local resource
     * @param propName the property name to check
     */
    public boolean isPropertyDefined( String propName )
    {
        if( System.getProperties().containsKey( propName ) )
            return true;

        if( usbProperties != null )
        {
            Enumeration keys = usbProperties.keys();

            while( keys.hasMoreElements() )
            {
                String key = (String)keys.nextElement();

                if( key.equals( propName ) )
                    return true;
            }
        }

        return false;
    }

    /** @return an Enumeration of the property name strings in the local resource */
    public Enumeration getPropertyNames() 
    {
        Enumeration enum = null;

        if( getProperties() == null )
            enum = ( new Vector() ).elements();
        else
            enum = getProperties().keys(); 

        return enum;
    }

    //-------------------------------------------------------------------------
    // Private methods
    //

    /** @return the Properties with the USB properties.  Loads and creates it if necessary */
    private Properties getProperties()
    {
        if( usbProperties == null )
            loadProperties();

        return usbProperties;
    }

    /**
     * @return a Properties object loaded with the properties file passed
     * Looks for the properties file in the current set JAR or Zip files
     * @param propFileName the properties file name
     */
    private Properties findProperties( String propFileName )
    {
        Properties properties = new Properties();
        final String resName = propFileName;
        InputStream is = 
        (InputStream)java.security.AccessController.doPrivileged(   new java.security.PrivilegedAction() 
                                                                    {
                                                                        public Object run() 
                                                                        { return ClassLoader.getSystemResourceAsStream( resName ); }
                                                                    }
                                                                );
        
        if( is != null ) 
        {
            is = new BufferedInputStream( is );

            try 
            {
                properties.load( is );
                loaded = true;
            }
            catch( Exception e ) 
            {
                loaded = false;
                lastException = e;
            }
            finally 
            {
                try{ is.close(); }
                catch( Exception e ) {}
            }
        }
        else
        {
            loaded = false;
            System.err.println( propFileName + " file not found" );
        }

        return properties;
    }

    //-------------------------------------------------------------------------
    // Private instance variables
    //

    private Properties usbProperties = null;

    private boolean loaded = false;
    private Exception lastException = null;
}
