package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import java.awt.*;
import javax.swing.*;

import javax.usb.*;

/**
 * Helper class to perform some operation on UsbHub
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class UsbHubUtility extends Object
{
    /** Make ctor private to prevent construction */
    private UsbHubUtility() {}

    //-------------------------------------------------------------------------
    // Public methods
    //

    /**
     * @return an iteration of all the UsbHub attached to the hub passed searchin in a BFS manner
     * @param usbHub the top UsbHub object
     */
    public static UsbInfoIterator bfsUsbHubs( UsbHub usbHub )
    {
        UsbHubFilterV visitor = new UsbHubFilterV();

        usbHub.accept( visitor );

        return visitor.getUsbHubs();
    }

    /**
     * @return an iteration of all the UsbDevice attached to the hub passed searchin in a BFS manner
     * @param usbHub the top UsbHub object
     */
    public static UsbInfoIterator bfsUsbDevices( UsbHub usbHub )
    {
        UsbDeviceFilterV visitor = new UsbDeviceFilterV();

        usbHub.accept( visitor );

        return visitor.getUsbDevices();
    }

    //-------------------------------------------------------------------------
    // Static Inner classes
    //

    /**
     * Visitor class to filter out all UsbHub attached to a UsbHub
     * @author E. Michael Maximilien
     */
    public static class UsbHubFilterV extends DefaultUsbInfoV
    {
        //---------------------------------------------------------------------
        // Ctor(s)
        //

        /** Default no-arg ctor */
        public UsbHubFilterV() {}

        //---------------------------------------------------------------------
        // Public methods
        //

        /** @return an iteration of UsbHub objects */
        public UsbInfoListIterator getUsbHubs() { return usbInfoList.usbInfoListIterator(); }

        //---------------------------------------------------------------------
        // Visitor methods
        //
        
        /**
         * Visits a UsbHub by adding it to the list of UsbHub and 
         * recursively visiting all its devices
         * @param usbInfo the UsbInfo object
         */
        public void visitUsbHub( UsbInfo usbInfo )
        {
            usbInfoList.addUsbInfo( usbInfo );

            UsbInfoIterator iterator = ( (UsbHub)usbInfo ).getAttachedUsbDevices();

            while( iterator.hasNext() )
                iterator.nextUsbInfo().accept( this );
        }

        //---------------------------------------------------------------------
        // Private instance variables
        //

        private UsbInfoList usbInfoList = new DefaultUsbInfoList();
    }

    /**
     * Visitor class to filter out all UsbDevice attached to a UsbHub
     * @author E. Michael Maximilien
     */
    public static class UsbDeviceFilterV extends DefaultUsbInfoV
    {
        //---------------------------------------------------------------------
        // Ctor(s)
        //

        /** Default no-arg ctor */
        public UsbDeviceFilterV() {}

        //---------------------------------------------------------------------
        // Public methods
        //

        /** @return an iteration of UsbHub objects */
        public UsbInfoListIterator getUsbDevices() { return usbInfoList.usbInfoListIterator(); }

        //---------------------------------------------------------------------
        // Visitor methods
        //
        
        /**
         * Default method to visit a UsbDevice
         * @param usbInfo the UsbInfo object
         */
        public void visitUsbDevice( UsbInfo usbInfo ) { usbInfoList.addUsbInfo( usbInfo ); }

        /**
         * Visits a UsbHub by adding it to the list of UsbHub and 
         * recursively visiting all its devices
         * @param usbInfo the UsbInfo object
         */
        public void visitUsbHub( UsbInfo usbInfo )
        {
            UsbInfoIterator iterator = ( (UsbHub)usbInfo ).getAttachedUsbDevices();

            while( iterator.hasNext() )
                iterator.nextUsbInfo().accept( this );
        }

        //---------------------------------------------------------------------
        // Private instance variables
        //

        private UsbInfoList usbInfoList = new DefaultUsbInfoList();
    }


}
