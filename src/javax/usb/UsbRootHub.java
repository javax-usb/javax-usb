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
 * Defines a USB Root Hub.
 * <p>
 * USB root hubs are a special hub at the top of the topology tree.
 * The USB 1.1 specification mentions root hubs in sec 5.2.3,
 * where it states that 'the host includes an embedded hub called
 * the root hub'.  The implication of this seems to be that the
 * (hardware) Host Controller device is the root hub, since
 * the Host Controller device 'emulates' a USB hub, and in
 * systems with only one physical Host Controller device, its
 * emulated hub is in effect the root hub.  However when
 * multiple Host Controller devices are considered, there are
 * two (2) options that were considered:
 * <ol>
 * <li>Have an array or list of the available topology trees,
 * with each physical Host Controller's emulated root hub as
 * the UsbRootHub of that particular topology tree.  This
 * configuration could be compared to the MS-DOS/Windows decision
 * to assign drive letters to different physical drives (partitions).
 * </li>
 * <li>Have a 'virtual' root hub, which is completely virtual (not
 * associated with any physical device) and is created and managed
 * solely by the javax.usb implementation.  This configuration could
 * be compared to the UNIX descision to put all physical drives
 * on 'mount points' under a single 'root' (/) directory filesystem.
 * </li>
 * </ol>
 * The second configuration is what is used in this API.  The implementation
 * is responsible for creating a single UsbRootHub which is completely
 * virtual (and available through the UsbServices object).  Every
 * UsbHub attached to this virtual UsbRootHub corresponds to a
 * physical Host Controller's emulated hub.  I.e., the first level of
 * UsbDevices under the virtual UsbRootHub are all UsbHubs corresponding
 * to a particular Host Controller on the system.  Note that since
 * the UsbRootHub is a virtual hub, the number of ports is not
 * posible to specify; so all that is guaranteed is the number of ports
 * is at least equal to the number of UsbHubs attached to the UsbRootHub.
 * The number of ports on the virtual UsbRootHub may change if UsbHubs
 * are attached or detached (e.g., if a Host Controller is physically
 * hot-removed from the system or hot-plugged, or if its driver is
 * dynamically loaded, or for any other reason a top-level Host Controller's
 * hub is attached/detached).  This API specification suggests that the
 * number of ports for the UsbRootHub equal the number of directly
 * attached UsbHubs.
 * <p>
 * The major deciding factors are listed here to show why the decision
 * to use the second option was made.
 * <ul>
 * <li>The first configuration results in having to maintain
 * a list of different and completely unconnected device topologies.
 * This means a search for a particular device must be performed on
 * all the device topologies.
 * </li>
 * <li>Since a UsbHub already has a list of UsbDevices, and
 * a UsbHub <i>is</i> a UsbDevice, introducing a new, different
 * list (as in option 1) is not a desirable action, since it
 * introduces extra unnecessary steps in performing actions, like
 * searching.
 * <li>As an example, a recursive search for a certain device
 * in the first configuration involves getting the first UsbRootHub,
 * getting all its attached UsbDevices, and checking each device;
 * any of those devices which are UsbHubs can be also searched recursively.
 * Then, the entire operation must be performed on the next UsbRootHub,
 * and this is repeated for all the UsbRootHubs in the array/list.
 * In the second configuration, the virtual UsbRootHub is recursively
 * searched in a single operation.
 * <li>The device model hierarchy was intentionally unified by making
 * all the Interfaces extends UsbInfo.  This way every part of the
 * device model structure can be collected into a UsbInfoList or
 * handled as a UsbInfo.  The first configuration breaks this
 * since a list (which could be a UsbInfoList) is required as part
 * of the device model structure (the top level).
 * <li>Having multiple UsbRootHubs is not a desirable 'feature',
 * which results in also having multiple topology trees.
 * The second configuration allows for a <i>single</i> 'true' UsbRootHub,
 * which really is at the 'root' of a <i>single</i> topology tree.
 * </li>
 * </ul>
 * @author E. Michael Maximilien
 * @author Dan Streetman (extremely long JavaDOC narration :-)
 * @since 0.8.0
 */
public interface UsbRootHub extends UsbHub {}
