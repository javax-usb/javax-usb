package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

import java.util.*;

import javax.usb.*;

/**
 * Simple application to printout the current values from the UsbProperties 
 * implementing class
 * @since 0.8.0
 */
public class UsbPropertiesViewer extends Object
{
    //-------------------------------------------------------------------------
    // Public class methods
    //

    /**
     * Simple main entry point that prints out all current properties (name and value) defined
     * @param args the array for String argument
     */
    public static void main( String[] args )
    {
        UsbProperties props = UsbHostManager.getInstance().getUsbProperties();

        Enumeration propNames = props.getPropertyNames();

        System.out.println( "javax USB defined Java properties:" );
        System.out.println( "<!-- name = \"propName\" value = \"propValue\" -->" );

        while( propNames.hasMoreElements() )
        {
            String propName = (String)propNames.nextElement();
            String propValue = (String)props.getPropertyString( propName );

            System.out.println( "<name = \"" + propName + "\"" + " value = \"" + propValue + "\" />" );
        }
    }
}
