package javax.usb;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.security.Permission;
import java.security.BasicPermission;

/**
 * Permission class for the javax.usb API.  This is used by the few API classes and 
 * a RI that implements the javax.usb API.
 * <p>NOTE that this class is final to prevent overrides</p>
 * @author E. Michael Maximilien
 * @since 0.9.0
 */
public final class JavaxUsbPermission extends BasicPermission
{
    //-------------------------------------------------------------------------
    // Ctor(s)
    //

	/**
	 * Creates a new JavaxUsbPermission with name specified
	 * @param name the permission name
	 */
	public JavaxUsbPermission( String name ) { super( name ); }

	/**
	 * Creates a new JavaxUsbPermission with name specified
	 * @param name the permission name
	 * @param actions the permission actions (not used)
	 */
	public JavaxUsbPermission( String name, String actions ) { super( name, actions ); }

    //-------------------------------------------------------------------------
    // Public methods
    //

	/**
	 * Checks if the Permission object passed is implied by this one
	 * <p>
	 * Implication rule are as follow:
	 * 1) An invalid permission is never implied
	 * 2) A permission imply itself
	 * 3) Wildcard permission imply all permissions
	 * 4) getRootHub implies getUsbServices permission
	 * 5) getUsbServices permission does not imply getUsbRootHub permission.
	 * <p><b>
	 * This last implication rule is not very important at this point 
	 * it will becaome more meaningful when we add the other permissions
	 * </b</p>
	 * @param p the Permission object
	 */
	public boolean implies( Permission p )
	{
		//<temp>
		System.out.println( this );
		System.out.println( p );
		//</temp>

		if( p == this ) return true;

		if( ( p instanceof JavaxUsbPermission ) == false )
			return false;

		if( getName().equals( p.getName() ) ) return true;

		if( getName().equals( WILDCARD_PERMISSION_NAME ) )
			return true;

		if( getName().equals( GETUSBROOTHUB_PERMISSION_NAME ) && 
			p.getName().equals( GETUSBROOTHUB_PERMISSION_NAME ) )
			return true;

		return false;
	}

    //-------------------------------------------------------------------------
    // Class constants - Permission names
    //

	/** Indicates a wildcard permission name for javax.usb (i.e. all its permission) */
	public static final String WILDCARD_PERMISSION_NAME = "*";

	/** Indicates permission to get the UsbServices object  */
	public static final String GETUSBSERVICES_PERMISSION_NAME = "getUsbServices";

	/** Indicates permission to get the UsbRootHub  */
	public static final String GETUSBROOTHUB_PERMISSION_NAME = "getUsbRootHub";

	//<THIS_IS_FOR_FINER_GRAINED_ SOLUTION>
	/**
	 * Indicates permission to submit Request to a USB device 
	 * <p></p>
	 */
	public static final String SUBMIT_REQUEST_PERMISSION_NAME = "submitRequest";

	/**
	 * Indicates permission to call getUsbPipe from an endpoint 
	 * <p></p>
	 */
	public static final String GETUSBPIPE_PERMISSION_NAME = "getUsbPipe";

	/** 
	 * Indicates permission to submit to a UsbPipe object.
	 * This implies both submitting a byte[] and a Irp to the UsbPipe object
	 */
	public static final String SUBMIT_USB_PIPE_PERMISSION_NAME = "submitUsbPipe";
	//</THIS_IS_FOR_FINER_GRAINED_ SOLUTION>

    //-------------------------------------------------------------------------
    // Class constants - Constant Permission objects
    //

	/**
	 * A javax.usb Wildcard permission object 
	 * <p>This constant can be used to avoid overhead of creation of this permission</p>
	 */
	public static final Permission WILDCARD_JAVAX_USB_PERMISSION = new JavaxUsbPermission( WILDCARD_PERMISSION_NAME );

	/**
	 * A javax.usb "getUsbServices" permission object 
	 * <p>This constant can be used to avoid overhead of creation of this permission</p>
	 */
	public static final Permission GETUSBSERVICES_JAVAX_USB_PERMISSION = new JavaxUsbPermission( GETUSBSERVICES_PERMISSION_NAME );

	/**
	 * A javax.usb "getUsbRootHub" permission object 
	 * <p>This constant can be used to avoid overhead of creation of this permission</p>
	 */
	public static final Permission GETUSBROOTHUB_JAVAX_USB_PERMISSION = new JavaxUsbPermission( GETUSBROOTHUB_PERMISSION_NAME );
}
