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

import junit.framework.*;

import javax.usb.Descriptor;
import javax.usb.UsbInfo;
import javax.usb.UsbInfoVisitor;
import javax.usb.test.JUnitUtility;

/**
 * A JUnit TestCase for the UsbInfoList and UsbInfoListIterator interfaces
 * and implementing classes
 * @author E. Michael Maximilien
 */
public class UsbInfoListTestCase extends TestCase
{
	//-------------------------------------------------------------------------
	// Ctor(s)
	//

	public UsbInfoListTestCase( String name ) { super( name ); }

	//-------------------------------------------------------------------------
	// Protected overridden methods
	//

	protected void setUp() 
	{
		list = new DefaultUsbInfoList();
		vector = new Vector();

		usbInfo0 = new TestUsbInfo( "UsbInfo0" );
		usbInfo1 = new TestUsbInfo( "UsbInfo1" );
		usbInfo2 = new TestUsbInfo( "UsbInfo2" );
		usbInfo3 = new TestUsbInfo( "UsbInfo3" );

		list.addUsbInfo( usbInfo0 );
		list.addUsbInfo( usbInfo1 );
		list.addUsbInfo( usbInfo2 );
		list.addUsbInfo( usbInfo3 );

		vector.addElement( usbInfo0 );
		vector.addElement( usbInfo1 );
		vector.addElement( usbInfo2 );
		vector.addElement( usbInfo3 );
	}

	protected void tearDown() 
	{ 
		list = null;
		vector = null;

		usbInfo0 = null;
		usbInfo1 = null;
		usbInfo2 = null;
		usbInfo3 = null;
	}

	protected Vector createVector( UsbInfoIterator iterator )
	{
		Vector v = new Vector();
		while( iterator.hasNext() )
			v.addElement( iterator.nextUsbInfo() );

		return v;
	}

	//-------------------------------------------------------------------------
	// Public testXyz() methods
	//

	/** Tests various aspects of the UsbInfoList interface */
	public void testUsbInfoList()
	{
		//Verify the UsbInfoList.contains(UsbInfo) method
		assertTrue( "List should contain element with name UsbInfo0", list.contains( usbInfo0 ) );
		assertTrue( "List should contain element with name UsbInfo3", list.contains( usbInfo3 ) );
		assertTrue( "List should contain element with name UsbInfo2", list.contains( usbInfo3 ) );

		//Verify the UsbInfoList.size() method
		assertTrue( "List should contain 4 elements", list.size() == 4 );

		//Verify the UsbInfoList.indexOf( UsbInfo ) method
		assertTrue( "Index of element by name UsbInfo0 should be 0", list.indexOf( usbInfo0 ) == 0 );
		assertTrue( "Index of element by name UsbInfo0 should be 3", list.indexOf( usbInfo3 ) == 3 );
		assertTrue( "Index of element by name UsbInfo0 should be 1", list.indexOf( usbInfo1 ) == 1 );

		//Verify the UsbInfoList.isEmpty() method
		assertTrue( "List should not be empty", list.isEmpty() == false );

		//Verify the UsbInfoList.toUsbInfoArray() method
		UsbInfo[] array = list.toUsbInfoArray();
		assertTrue( "UsbInfo[] should be identical to elements enumeration", JUnitUtility.isIdentical( array, vector ) );
		assertTrue( "UsbInfo[] should be equal to elements enumeration", JUnitUtility.isEquals( array, vector ) );

		//Verify the UsbInfoList.addUsbInfo( UsbInfo ) method
		UsbInfo usbInfo4 = new TestUsbInfo( "UsbInfo4" );
		list.addUsbInfo( usbInfo4 );
		array = list.toUsbInfoArray();
		assertTrue( "Added usbInfo4 so list should not be identical to elements enumeration", JUnitUtility.isIdentical( array, vector ) == false );
		assertTrue( "Added usbInfo4 so list should not be equal to elements enumeration", JUnitUtility.isEquals( array, vector ) == false );

		//Verify the UsbInfoList.removeUsbInfo( UsbInfo ) method
		list.removeUsbInfo( usbInfo4 );
		array = list.toUsbInfoArray();
		assertTrue( "Removed usbInfo4 so list should be identical to elements enumeration", JUnitUtility.isIdentical( array, vector ) );
		assertTrue( "Added usbInfo4 so list should be equal to elements enumeration", JUnitUtility.isEquals( array, vector ) );

		//Verify the UsbInfoList.addUsbInfo( int, UsbInfo ) method
		UsbInfo usbInfo = new TestUsbInfo( "UsbInfo" );
		list.addUsbInfo( 0, usbInfo );
		array = list.toUsbInfoArray();
		assertTrue( "Added usbInfo so list should not be identical to elements enumeration", JUnitUtility.isIdentical( array, vector ) == false );
		assertTrue( "Added usbInfo so list should not be equal to elements enumeration", JUnitUtility.isEquals( array, vector ) == false );

		//Verify the UsbInfoList.removeUsbInfo( int ) method
		list.removeUsbInfo( 0 );
		array = list.toUsbInfoArray();
		assertTrue( "Removed usbInfo so list should be identical to elements enumeration", JUnitUtility.isIdentical( array, vector ) );
		assertTrue( "Added usbInfo so list should be equal to elements enumeration", JUnitUtility.isEquals( array, vector ) );

		//Verify the UsbInfoList.addUsbInfo( int, UsbInfo ) method
		usbInfo = new TestUsbInfo( "UsbInfo" );
		list.addUsbInfo( 3, usbInfo );
		array = list.toUsbInfoArray();
		assertTrue( "Added usbInfo so list should not be identical to elements enumeration", JUnitUtility.isIdentical( array, vector ) == false );
		assertTrue( "Added usbInfo so list should not be equal to elements enumeration", JUnitUtility.isEquals( array, vector ) == false );

		//Verify the UsbInfoList.removeUsbInfo( int ) method
		list.removeUsbInfo( 3 );
		array = list.toUsbInfoArray();
		assertTrue( "Removed usbInfo so list should be identical to elements enumeration", JUnitUtility.isIdentical( array, vector ) );
		assertTrue( "Added usbInfo so list should be equal to elements enumeration", JUnitUtility.isEquals( array, vector ) );

		//Verify the UsbInfoLsit.getUsbInfo( int ) method
		usbInfo = list.getUsbInfo( 3 );
		assertTrue( "list.getUsbInfo( 3 ).equals( usbInfo3 ) == true", list.getUsbInfo( 3 ).equals( usbInfo3 ) == true );

		try
		{
			usbInfo = list.getUsbInfo( 5 );
			assertTrue( "Expected ArrayIndexOutOfBoundsException here...", false );
		}
		catch( ArrayIndexOutOfBoundsException e )
		{
			assertTrue( "Corectly got ArrayIndexOutOfBoundsException", true );
		}

		//Verify that UsbInfoList.equals( Object ) method
		UsbInfoList list2 = new DefaultUsbInfoList();

		list2.addUsbInfo( usbInfo0 );
		list2.addUsbInfo( usbInfo1 );
		list2.addUsbInfo( usbInfo2 );
		list2.addUsbInfo( usbInfo3 );

		assertTrue( "list.equals( null ) == false", list.equals( null ) == false );
		assertTrue( "list.equals( list ) == true", list.equals( list ) == true );
		assertTrue( "list.equals( list2 ) == true", list.equals( list ) == true );

		list.removeUsbInfo( 0 );

		assertTrue( "Modified list2 thus: list.equals( list2 ) == false", list.equals( list2 ) == false );

		//Verify UsbInfoList.clear() method
		list.clear();
		assertTrue( "Cleared the list thus size == 0", list.size() == 0 );
		assertTrue( "Cleared the list thus list iEmpty() == true", list.isEmpty() == true );
	}

	/** Tests various aspects of the UsbInfoListIterator */
	public void testUsbInfoListIterator()
	{
		//Verify the UsbInfoList.usbInfoIterator() method
		UsbInfoIterator iterator = list.usbInfoIterator();
		Vector v = createVector( iterator );

        assertTrue( "Iterator contents should be identical to list contents...", JUnitUtility.isIdentical( vector, v ) );
		assertTrue( "Iterator contents should be equal to list contents...", JUnitUtility.isEquals( v, vector ) );
		assertTrue( "iterator.hasNext() == false", iterator.hasNext() == false );

		//Verify the UsbInfoList.hasNext() method
		iterator = list.usbInfoIterator();
		assertTrue( "iterator.hasNext() == true", iterator.hasNext() == true );

		//Verify the UsbInfoList.hasNext() method
		iterator = list.usbInfoIterator();

		UsbInfo usbInfo = new TestUsbInfo( "UsbInfo" );
		list.addUsbInfo( usbInfo );

		UsbInfoIterator iterator2 = list.usbInfoIterator();
		      
		assertTrue( "iterator contents should be different then iterator2 since element was added", 
				JUnitUtility.isEquals( createVector( iterator ), createVector( iterator2 ) ) == false );
	}

	//-------------------------------------------------------------------------
	// Inner classes
	//

	private class TestUsbInfo extends Object implements UsbInfo
	{
		//---------------------------------------------------------------------
		// Ctor(s)
		//

		public TestUsbInfo( String s ) { name = s; }

		//---------------------------------------------------------------------
		// Public methods
		//

		public boolean equals( Object obj )
		{
			if( obj == null )
				return false;

			if( !( obj instanceof UsbInfoListTestCase.TestUsbInfo ) )
				return false;

			return ( getName().equals( ( (UsbInfo)obj ).getName() ) );
		}

		/** @return name of this USB device */
		public String getName() { return name; }
		/** @return this object's descriptor */
		public Descriptor getDescriptor() { return descriptor; }
		/**
		 * Visitor.accept method
		 * @param visitor the UsbInfoVisitor visiting this UsbInfo
		 */
		public void accept( UsbInfoVisitor visitor ) {}

		//---------------------------------------------------------------------
		// Instance variables
		//

		private String name = "";
		private Descriptor descriptor = null;
	}

	//-------------------------------------------------------------------------
	// Instance variables
	//

	private UsbInfoList list = null;
	private Vector vector = null;

	private UsbInfo usbInfo0 = null;
	private UsbInfo usbInfo1 = null;
	private UsbInfo usbInfo2 = null;
	private UsbInfo usbInfo3 = null;
}
