package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License.
 *
 */

/**
 * Defines USB descriptors constants
 * <p>
 * See section 9.5 and 9.6 (and sub-sections) of the USB 1.1 specification
 * for details on USB descriptors.
 * </p>
 * @author E. Michael Maximilien
 * @author Dan Streetman
 * @since 0.8.0
 */
public interface DescriptorConst
{
	//-------------------------------------------------------------------------
    // USB spec "Device Class" defined USB device classes
	//

    public static final byte DEVICE_CLASS_RESERVED                      = (byte)0x00;
    public static final byte DEVICE_CLASS_AUDIO                         = (byte)0x01;
    public static final byte DEVICE_CLASS_COMMUNICATIONS                = (byte)0x02;
    public static final byte DEVICE_CLASS_HUMAN_INTERFACE               = (byte)0x03;
    public static final byte DEVICE_CLASS_MONITOR                       = (byte)0x04;
    public static final byte DEVICE_CLASS_PHYSICAL_INTERFACE            = (byte)0x05;
    public static final byte DEVICE_CLASS_POWER                         = (byte)0x06;    
    public static final byte DEVICE_CLASS_PRINTER                       = (byte)0x07;
    public static final byte DEVICE_CLASS_STORAGE                       = (byte)0x08;
    public static final byte DEVICE_CLASS_HUB                           = (byte)0x09;
    
    public static final byte DEVICE_CLASS_VENDOR_SPECIFIC               = (byte)0xff;

    //-------------------------------------------------------------------------
    // USB spec "Descriptor Type" constants
	//

    public static final byte DESCRIPTOR_TYPE_DEVICE                     = (byte)0x01;
    public static final byte DESCRIPTOR_TYPE_CONFIG                     = (byte)0x02;
    public static final byte DESCRIPTOR_TYPE_STRING                     = (byte)0x03;
    public static final byte DESCRIPTOR_TYPE_INTERFACE                  = (byte)0x04;
    public static final byte DESCRIPTOR_TYPE_ENDPOINT                   = (byte)0x05;

	//-------------------------------------------------------------------------
    // USB 1.1 descriptor minimum lengths
	//

    public static final byte DESCRIPTOR_MIN_LENGTH                      = (byte)0x02;
    public static final byte DESCRIPTOR_MIN_LENGTH_DEVICE               = (byte)0x12;
    public static final byte DESCRIPTOR_MIN_LENGTH_CONFIG               = (byte)0x09;
    public static final byte DESCRIPTOR_MIN_LENGTH_INTERFACE            = (byte)0x09;
    public static final byte DESCRIPTOR_MIN_LENGTH_ENDPOINT             = (byte)0x07;
    public static final byte DESCRIPTOR_MIN_LENGTH_STRING               = (byte)0x02;
}                                   					  		
