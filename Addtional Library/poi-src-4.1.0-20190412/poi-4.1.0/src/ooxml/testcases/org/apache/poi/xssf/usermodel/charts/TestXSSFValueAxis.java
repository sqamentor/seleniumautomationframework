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

package org.apache.poi.xssf.usermodel.charts;

import org.apache.poi.xddf.usermodel.chart.AxisCrossBetween;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import junit.framework.TestCase;

public final class TestXSSFValueAxis extends TestCase {

	public void testAccessMethods() throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		XSSFDrawing drawing = sheet.createDrawingPatriarch();
		XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 1, 1, 10, 30);
		XSSFChart chart = drawing.createChart(anchor);
		XDDFValueAxis axis = chart.createValueAxis(AxisPosition.BOTTOM);

		axis.setCrossBetween(AxisCrossBetween.MIDPOINT_CATEGORY);
		assertEquals(axis.getCrossBetween(), AxisCrossBetween.MIDPOINT_CATEGORY);

		axis.setCrosses(AxisCrosses.AUTO_ZERO);
		assertEquals(axis.getCrosses(), AxisCrosses.AUTO_ZERO);

		assertEquals(chart.getAxes().size(), 1);

		wb.close();
	}
}
