package javax.usb.test;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the Common Public License:
 * http://oss.software.ibm.com/developerworks/opensource/license-cpl.html
 */

import java.util.*;

/**
 * Utility class to create JUnit TestCase and TestSuite
 * @author E. Michael Maximilien
 */
public class JUnitUtility extends Object
{
	//-------------------------------------------------------------------------
	// Ctor(s)
	//

	/** Make ctor private to prevent construction */
	private JUnitUtility() {}

	//-------------------------------------------------------------------------
	// Public class methods
	//

	/**
	 * @return a Vector with contents of the Enumeration 
	 * @param enum the Enumeration object
	 */
	public static Vector createVector( Enumeration enum )
	{
		Vector v = new Vector();

		while( enum.hasMoreElements() )
			v.addElement( enum.nextElement() );

		return v;
	}

	/**
	 * @return a Vector with contents of the Object[] 
	 * @param array an Object[]
	 */
	public static Vector createVector( Object[] array )
	{
		Vector v = new Vector();

		for( int i = 0; i < array.length; ++ i)
			v.addElement( array[ i ] );

		return v;
	}

	/**
	 * @return true if the 2 Enumeration have the same Objects in same order
	 * @param v1 the first Vector
	 * @param v2 the second Vector
	 */
	public static boolean isIdentical( Vector v1, Vector v2 )
	{
		return isIdentical( v1.elements(), v2.elements() );
	}

	/**
	 * @return true if the 2 Enumeration have the same Objects in same order 
	 * @param enum1 the first Enumeration
	 * @param enum2 the second Enumeration
	 */
	public static boolean isIdentical( Enumeration enum1, Enumeration enum2 )
	{
		if( enum1 == null || enum2 == null )
			return false;

		if( enum1.hasMoreElements() && ( enum2.hasMoreElements() == false ) )
			return false;

		if( enum2.hasMoreElements() && ( enum1.hasMoreElements() == false ) )
			return false;

		while( enum1.hasMoreElements() )
		{
			Object enum1Obj = enum1.nextElement();

			if( enum2.hasMoreElements() == false )
				return false;

			Object enum2Obj = enum2.nextElement();

			if( !enum1Obj.equals( enum2Obj ) )
				return false;
		}

		if( enum2.hasMoreElements() )
			return false;

		return true;
	}

	/**
	 * @return true if the two arguments have the same elements (don't need to be in same order)
	 * @param array an Object[]
	 * @param vector a Vector 
	 */
	public static boolean isIdentical( Object[] array, Vector vector )
	{
		return isIdentical( createVector( array ), vector );
	}

	/**
	 * @return true if the two arguments have the same elements (don't need to be in same order)
	 * @param array an Object[]
	 * @param enum an Enumeration of Objects 
	 */
	public static boolean isIdentical( Object[] array, Enumeration enum )
	{
		return isIdentical( createVector( array ).elements(), enum );
	}

	/**
	 * @return true if the two arguments have the same elements (don't need to be in same order)
	 * @param array an Object[]
	 * @param vector a Vector 
	 */
	public static boolean isEquals( Object[] array, Vector vector )
	{
		return isEquals( createVector( array ), vector );
	}

	/**
	 * @return true if the two arguments have the same elements (don't need to be in same order)
	 * @param array an Object[]
	 * @param vector a Vector 
	 */
	public static boolean isEquals( Object[] array, Enumeration enum )
	{
		return isEquals( createVector( array ).elements(), enum );
	}

	/**
	 * @return true if the 2 Enumeration have the same Objects (could be in different order)
	 * @param enum1 the first Enumeration
	 * @param enum2 the second Enumeration
	 */
	public static boolean isEquals( Enumeration enum1, Enumeration enum2 )
	{
		if( enum1 == null || enum2 == null )
			return false;

		return isEquals( createVector( enum1 ), createVector( enum2 ) );
	}			  

	/**
	 * @return true if the 2 Vector have the same Objects (could be in different order)
	 * @param v1 the first Vector
	 * @param v2 the second Vector
	 */
	public static boolean isEquals( Vector v1, Vector v2 )
	{
		if( v1 == null || v2 == null )
			return false;

		Vector v1Clone = (Vector)v1.clone();
		Vector v2Clone = (Vector)v2.clone();

		if( v1Clone.size() != v2Clone.size() )
			return false;

		for( int i = 0; i < v1Clone.size(); ++i )
		{
			Object v1CloneObj = v1Clone.elementAt( i );

			boolean found = false;
			Object v2CloneObj = null;

			for( int j = 0; j < v2Clone.size(); ++j )
			{
				v2CloneObj = v2Clone.elementAt( j );

				if( v1CloneObj.equals( v2CloneObj ) )
				{
					found = true;
					break;
				}
			}

			if( found )
				v2Clone.removeElement( v2CloneObj );
			else
				return false;
		}

		return ( v2Clone.size() == 0 ? true : false );
	}
}
