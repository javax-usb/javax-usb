package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.text.*;

import javax.usb.*;
import javax.usb.os.*;

/**
 * Visitor class to initialize that implements a toString() method for
 * UsbInfo object
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public class UsbInfoToStringV extends DefaultUsbInfoV
{
    /** Default ctor */
    public UsbInfoToStringV() { }

    //-------------------------------------------------------------------------
    // Private methods
    //

    /**
     * @return the String value or <empty> if the string is "" and 
     * <null> if the string is null
     */
    private String translateString( String s )
    {
        if( s == null )
            return "<null>";
        else
        if( s.equals( "" ) )
            return "<empty>";
        else
            return s;
    }

    //-------------------------------------------------------------------------
    // Public methods
    //

    /** @return the TAB size that will be used */
    public int getTabSize() { return tabString.length(); }

    /**
     * Sets the current TAB size that will be used 
     * @param tabSize the int parameter
     */
    public void setTabSize( int tabSize ) 
    {
        tabString = "";

        for( int i = 0; i < tabSize; ++i )
            tabString += " ";
    }

    /** @return a String representation of the UsbInfo object visited */
    public String getString() { return sb.toString(); }

    //-------------------------------------------------------------------------
    // Public visitXyz visitor methods
    //

    /**
     * Default method to visit a UsbDevice
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbDevice( UsbInfo usbInfo ) 
    {
        UsbDevice usbDevice = (UsbDevice)usbInfo;

        sb.delete( 0, sb.length() );
        
        sb.append( "Product description         = " + translateString( usbDevice.getProductString() ) + NEWLINE );
        sb.append( "Manufacturer                = " + translateString( usbDevice.getManufacturer() ) + NEWLINE );
        sb.append( "Serial Number               = " + translateString( usbDevice.getSerialNumber() ) + NEWLINE );
        sb.append( "Speed                       = " + translateString( usbDevice.getSpeedString() ) + NEWLINE );

        sb.append( "Endpoint 0 max packet size  = " + UsbUtil.unsignedInt( usbDevice.getMaxPacketSize() ) + " bytes" + NEWLINE );
        sb.append( "Number of configurations    = " + UsbUtil.unsignedInt( usbDevice.getNumConfigs() ) + NEWLINE );
        sb.append( "Active configuration        = " + UsbUtil.unsignedInt( usbDevice.getActiveUsbConfigNumber() ) + NEWLINE );
		sb.append( "Is configured               = " + usbDevice.isConfigured() + NEWLINE );
        sb.append( "Product ID                  = 0x" + UsbUtil.toHexString( usbDevice.getProductId() ) + NEWLINE );
        sb.append( "Vendor ID                   = 0x" + UsbUtil.toHexString( usbDevice.getVendorId() ) + NEWLINE );
        sb.append( "Device class                = 0x" + UsbUtil.toHexString( usbDevice.getDeviceClass() ) + NEWLINE );
        sb.append( "Device subclass             = 0x" + UsbUtil.toHexString( usbDevice.getDeviceSubClass() ) + NEWLINE );
        sb.append( "Device protocol             = 0x" + UsbUtil.toHexString( usbDevice.getDeviceProtocol() ) + NEWLINE );
        sb.append( "BCD USB                     = 0x" + UsbUtil.toHexString( usbDevice.getBcdUsb() ) + NEWLINE );
        sb.append( "BCD Device                  = 0x" + UsbUtil.toHexString( usbDevice.getBcdDevice() ) );
    }

    /**
     * Default method to visit a UsbHub
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbHub( UsbInfo usbInfo ) 
    {
        UsbHub usbHub = (UsbHub)usbInfo;

		visitUsbDevice( usbInfo );

        sb.insert( 0, "Number of ports             = " + usbHub.getNumberOfPorts() + NEWLINE );
        sb.insert( 0, "Is a root hub?              = " + usbHub.isUsbRootHub() + NEWLINE );
    }

    /**
     * Default method to visit a UsbPort
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbPort( UsbInfo usbInfo ) 
    {
        UsbPort usbPort = (UsbPort)usbInfo;

        sb.delete( 0, sb.length() );

        sb.append( "Port number " + usbPort.getPortNumber() );
        sb.append( " " + ( usbPort.getUsbDevice() == null ? "is empty" : "is in use." ) );
    }

    /**
     * Default method to visit a UsbConfig
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbConfig( UsbInfo usbInfo ) 
    {
        UsbConfig config = (UsbConfig)usbInfo;

        sb.delete( 0, sb.length() );

        sb.append( tabString + "Config Number        = " + UsbUtil.unsignedInt( config.getConfigNumber() ) + NEWLINE );
		sb.append( tabString + "Is active            = " + config.isActive() + NEWLINE );
        sb.append( tabString + "Attributes           = 0x" + UsbUtil.toHexString( config.getAttributes() ) + NEWLINE );
        sb.append( tabString + "Max power            = " + ( 2 * UsbUtil.unsignedInt( config.getMaxPower() ) ) + " mA" + NEWLINE );
        sb.append( tabString + "Number of interfaces = " + UsbUtil.unsignedInt( config.getNumInterfaces() ) );
    }

    /**
     * Default method to visit a UsbInterface
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbInterface( UsbInfo usbInfo ) 
    {
        UsbInterface usbInterface = (UsbInterface)usbInfo;

        sb.delete( 0, sb.length() );

        sb.append( tabString + "Interface number    = " + UsbUtil.unsignedInt( usbInterface.getInterfaceNumber() ) + NEWLINE );
		sb.append( tabString + "Is active           = " + usbInterface.getUsbConfig().isActive() + NEWLINE );
        sb.append( tabString + "Alternate setting   = " + UsbUtil.unsignedInt( usbInterface.getAlternateSettingNumber() ) + NEWLINE );
		sb.append( tabString + "Is setting active   = " + usbInterface.isActive() + NEWLINE );
		if ( usbInterface.getUsbConfig().isActive() )
			sb.append( tabString + "Active Alt setting  = " + UsbUtil.unsignedInt( usbInterface.getActiveAlternateSettingNumber() ) + NEWLINE );
        sb.append( tabString + "Interface class     = 0x" + UsbUtil.toHexString( usbInterface.getInterfaceClass() ) + NEWLINE );
        sb.append( tabString + "Interface sub-class = 0x" + UsbUtil.toHexString( usbInterface.getInterfaceSubClass() ) + NEWLINE );
        sb.append( tabString + "Interface protocol  = 0x" + UsbUtil.toHexString( usbInterface.getInterfaceProtocol() ) + NEWLINE );
        sb.append( tabString + "Number of endpoints = " + UsbUtil.unsignedInt( usbInterface.getNumEndpoints() ) );
    }

    /**
     * Default method to visit a UsbEndpoint
     * @param usbInfo the UsbInfo object
     */
    public void visitUsbEndpoint( UsbInfo usbInfo ) 
    {                        
        UsbEndpoint endpoint = (UsbEndpoint)usbInfo;

        sb.delete( 0, sb.length() );

        sb.append( tabString + "Address         = 0x" + UsbUtil.toHexString( endpoint.getEndpointAddress() ) + NEWLINE );
        sb.append( tabString + "Direction       = " + UsbUtil.getEndpointDirectionString( endpoint.getEndpointAddress(), endpoint.getType() ) + NEWLINE );
        sb.append( tabString + "Attributes      = 0x" + UsbUtil.toHexString( endpoint.getAttributes() ) + NEWLINE );
        sb.append( tabString + "Interval        = " + UsbUtil.unsignedInt( endpoint.getInterval() ) + " ms" + NEWLINE );
        sb.append( tabString + "Max packet size = " + UsbUtil.unsignedInt( endpoint.getMaxPacketSize() ) + " bytes" + NEWLINE );
        sb.append( tabString + "Type            = 0x" + UsbUtil.toHexString( endpoint.getType() ) + " (" + UsbUtil.getEndpointTypeString( endpoint.getType() ) + ")" );
    }

    //-------------------------------------------------------------------------
    // Instance variables
    //

    private StringBuffer sb = new StringBuffer();

    private String tabString = TAB;

    //-------------------------------------------------------------------------
    // Class constants
    //

    public static final String TAB = "    ";
	public static final String NEWLINE = "\n";
}
