package javax.usb.util;

/**
 * Copyright (c) 1999 - 2001, International Business Machines Corporation.
 * All Rights Reserved.
 *
 * This software is provided and licensed under the terms and conditions
 * of the IBM Public License.
 *
 */

import java.util.*;

import javax.usb.UsbIrp;

/**
 * Default implementation of the UsbIrpList interface using a Vector class.
 * @author Dan Streetman
 * @author E. Michael Maximilien
 * @since 0.8.0
 */
public class DefaultUsbIrpList extends Vector implements UsbIrpList
{
	/**
	 * Constructor
	 */
	public DefaultUsbIrpList() { super(); }

	/**
	 * Constructor
	 * @param list this list's contents are copied into the newly created list.
	 */
	public DefaultUsbIrpList( UsbIrpList list )
	{
		super();
		addUsbIrpList( list );
	}

	//*************************************************************************
	// Public methods

    /**
     * Adds the UsbIrp object at the specified index
     * @param index the position to add
     * @param usbIrp the UsbIrp object to add
     */
    public void addUsbIrp( int index, UsbIrp usbIrp ) { add( index, usbIrp ); }

    /**
     * Add this UsbIrp object to the end of the list
     * @param usbIrp the UsbIrp object to add
     */
    public void addUsbIrp( UsbIrp usbIrp ) { add( usbIrp ); }

    /**
     * Add all this UsbIrpList's UsbIrp objects to the end of the list
     * @param usbIrpList the UsbIrpList object to add/combine
     */
    public void addUsbIrpList( UsbIrpList usbIrpList )
    {
        UsbIrp[] usbIrpArray = usbIrpList.toUsbIrpArray();

        for ( int i=0; i<usbIrpArray.length; i++ )
            addUsbIrp( usbIrpArray[i] );
    }

    /** @return true if the list contains this UsbIrp object */
    public boolean containsUsbIrp( UsbIrp usbIrp ) { return contains( usbIrp ); }

    /**
     * @return the UsbIrp object at the specified index
     * @param index the index of the UsbIrp object to get
     */
    public UsbIrp getUsbIrp( int index ) { return (UsbIrp)get( index ); }

	/** @return a (shallow) copy of this UsbIrpList */
	public UsbIrpList copy() { return new DefaultUsbIrpList( this ); }

	/**
	 * Get a UsbIrpListIterator representing this UsbIrpList.
	 * @return a UsbIrpListIterator with this UsbIrpList's contents.
	 */
	public UsbIrpListIterator usbIrpListIterator() { return new DefaultUsbIrpList.DefaultUsbIrpListIterator( this ); }

	/**
	 * Get a UsbIrpIterator representing this UsbIrpList.
	 * @return a UsbIrpIterator with this UsbIrpList's contents.
	 */
	public UsbIrpIterator usbIrpIterator() { return usbIrpListIterator(); }

    /**
     * @return the index of the UsbIrp object passed
     * @param usbIrp the UsbIrp object
     */
    public int indexOfUsbIrp( UsbIrp usbIrp ) { return indexOf( usbIrp ); }

    /**
     * @return the UsbIrp object at the specified index
     * @param index the index of the UsbIrp to remove
     */
    public UsbIrp removeUsbIrp( int index ) { return (UsbIrp)remove( index ); }

    /**
     * @return true if the UsbIrp object specified could be removed
     * @param usbIrp the UsbIrp object to remove
     */
    public boolean removeUsbIrp( UsbIrp usbIrp ) { return remove( usbIrp ); }

    /**
     * Sets the UsbIrp object at the index specified
     * @param index the index where to set the specified UsbIrp object
     * @param usbIrp the UsbIrp object that should be set to the index specified
     * @return the previous element
     */
    public UsbIrp setUsbIrp( int index, UsbIrp usbIrp ) { return (UsbIrp)set( index, usbIrp ); }

    /** @return a array of UsbIrp objects */
    public UsbIrp[] toUsbIrpArray() { return (UsbIrp[])toArray(); }

	//*************************************************************************
	// Inner Classes

	/**
	 * Default UsbIrpListIterator implementation for a UsbIrpList.
	 */
	public class DefaultUsbIrpListIterator implements UsbIrpListIterator
	{
		/**
		 * Constructor
		 * @param list the UsbIrpList whose contents to use.
		 */
		public DefaultUsbIrpListIterator( UsbIrpList list ) { usbIrpArray = list.toUsbIrpArray(); }

		//*********************************************************************
		// Public methods

		/** @return if this iteration has a next UsbIrp */
		public boolean hasNext() { return !(usbIrpArray.length == next); }

		/** @return the next UsbIrp in this iteration */
		public synchronized UsbIrp nextUsbIrp()
		{
			if (!hasNext())
				throw new NoSuchElementException( "No next UsbIrp" );

			previous++;
			return usbIrpArray[next++];
		}

		/** @return if this iteration has a previous UsbIrp */
		public boolean hasPrevious() { return !(-1 == previous); }

		/** @return the previous UsbIrp in this iteration */
		public synchronized UsbIrp previousUsbIrp()
		{
			if (!hasPrevious())
				throw new NoSuchElementException( "No previous UsbIrp" );

			next--;
			return usbIrpArray[previous--];
		}

		/** @return the index of the next UsbIrp in this iteration */
		public int nextIndex() { return next; }

		/** @return the index of the previoius UsbIrp in this iteration */
		public int previousIndex() { return previous; }

		/**
		 * Get the UsbIrp at the specified position.
		 * @param index the index of the UsbIrp to get.
		 * @return the UsbIrp at the specified position
		 */
		public synchronized UsbIrp getUsbIrp( int index )
		{
			if (0 > index || index >= usbIrpArray.length)
				throw new NoSuchElementException( "No UsbIrp at index " + index );

			previous = index;
			next = index+1;
			return usbIrpArray[index];
		}

		/** @return the size of this iteration. */
		public int size() { return usbIrpArray.length; }

		//*********************************************************************
		// Instance variables

		private UsbIrp[] usbIrpArray = null;
		private int previous = -1;
		private int next = 0;
	}

}
