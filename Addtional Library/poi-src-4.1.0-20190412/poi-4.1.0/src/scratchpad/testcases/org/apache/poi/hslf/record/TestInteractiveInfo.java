
/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */



package org.apache.poi.hslf.record;


import java.io.ByteArrayOutputStream;

import junit.framework.TestCase;

/**
 * Tests that InteractiveInfoAtom works properly.
 *
 * @author Nick Burch (nick at torchbox dot com)
 */
public class TestInteractiveInfo extends TestCase {
	// From a real file
	private final byte[] data_a = new byte[] {
		0x0F, 00, 0xF2-256, 0x0F, 0x18, 00, 00, 00,
		00, 00, 0xF3-256, 0x0F, 0x10, 00, 00, 00,
		00, 00, 00, 00, 01, 00, 00, 00,
		04, 00, 00, 00, 8, 00, 00, 00
	};

	public void testRecordType() {
		InteractiveInfo ii = new InteractiveInfo(data_a, 0, data_a.length);
		assertEquals(4082, ii.getRecordType());
	}

	public void testGetChildDetails() {
		InteractiveInfo ii = new InteractiveInfo(data_a, 0, data_a.length);
		InteractiveInfoAtom ia = ii.getInteractiveInfoAtom();

		assertEquals(1, ia.getHyperlinkID());
	}

	public void testWrite() throws Exception {
		InteractiveInfo ii = new InteractiveInfo(data_a, 0, data_a.length);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ii.writeOut(baos);
		byte[] b = baos.toByteArray();

		assertEquals(data_a.length, b.length);
		for(int i=0; i<data_a.length; i++) {
			assertEquals(data_a[i],b[i]);
		}
	}

	// Create A from scratch
	public void testCreate() throws Exception {
		InteractiveInfo ii = new InteractiveInfo();
		InteractiveInfoAtom ia = ii.getInteractiveInfoAtom();

		// Set values
		ia.setHyperlinkID(1);
		ia.setSoundRef(0);
		ia.setAction((byte)4);
		ia.setHyperlinkType((byte)8);

		// Check it's now the same as a
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ii.writeOut(baos);
		byte[] b = baos.toByteArray();

		assertEquals(data_a.length, b.length);
		for(int i=0; i<data_a.length; i++) {
			assertEquals(data_a[i],b[i]);
		}
   }
}
