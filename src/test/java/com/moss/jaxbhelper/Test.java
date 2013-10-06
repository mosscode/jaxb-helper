/**
 * Copyright (C) 2013, Moss Computing Inc.
 *
 * This file is part of jaxb-helper.
 *
 * jaxb-helper is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * jaxb-helper is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jaxb-helper; see the file COPYING.  If not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under
 * terms of your choice, provided that you also meet, for each linked
 * independent module, the terms and conditions of the license of that
 * module.  An independent module is a module which is not derived from
 * or based on this library.  If you modify this library, you may extend
 * this exception to your version of the library, but you are not
 * obligated to do so.  If you do not wish to do so, delete this
 * exception statement from your version.
 */
package com.moss.jaxbhelper;

import javax.xml.bind.JAXBContext;

import com.moss.jaxbhelper.JAXBHelper;

import junit.framework.TestCase;

public class Test extends TestCase {
	public void testExecute() throws Exception {
		JAXBContext context = JAXBContext.newInstance(Root.class);
		JAXBHelper helper = new JAXBHelper(context);
		
		Root root = new Root();
		for(int x=0;x<10;x++){
			root.add(new Child("Child " + x, x * 2));
		}
		
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
"<root>\n"+
"    <children name=\"Child 0\" age=\"0\"/>\n"+
"    <children name=\"Child 1\" age=\"2\"/>\n"+
"    <children name=\"Child 2\" age=\"4\"/>\n"+
"    <children name=\"Child 3\" age=\"6\"/>\n"+
"    <children name=\"Child 4\" age=\"8\"/>\n"+
"    <children name=\"Child 5\" age=\"10\"/>\n"+
"    <children name=\"Child 6\" age=\"12\"/>\n"+
"    <children name=\"Child 7\" age=\"14\"/>\n"+
"    <children name=\"Child 8\" age=\"16\"/>\n"+
"    <children name=\"Child 9\" age=\"18\"/>\n"+
"</root>\n";
		String xml = helper.writeToXmlString(root);
		assertEquals(expected, xml);
		assertEquals(expected, helper.writeToXmlString(helper.readFromXmlString(xml)));
	}
}
