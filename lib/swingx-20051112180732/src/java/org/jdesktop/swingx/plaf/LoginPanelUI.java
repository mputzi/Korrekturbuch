/*
 * $Id: LoginPanelUI.java,v 1.1 2005/11/11 23:05:14 rbair Exp $
 *
 * Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.jdesktop.swingx.plaf;
import java.awt.Image;
import javax.swing.plaf.PanelUI;

/**
 *
 * @author rbair
 */
public abstract class LoginPanelUI extends PanelUI {
    /**
     * @return The Image to use as the banner for the JXLoginPanel. If
     * this method returns null, then no banner will be shown.
     */
    public abstract Image getBanner();
}
