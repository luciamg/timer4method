/**
 * Copyright 2012 - 2017 Lucía Manescau
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.timer4method.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.timer4method.test.util.Example;

public class Timer4MethodTest extends TestImpl {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Autowired
	private Example example;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

	@Test
	public void testShowOnlyDebug() {
		example.showOnlyDebug();
		String output = outContent.toString();
		boolean haveMsg = output.contains("DEBUG Timer4Method");
		Assert.assertTrue(haveMsg);
	}
	
	@Test
	public void testShowDebugAndWarning() {
		example.showDebugAndWarning();
		String output = outContent.toString();
		boolean haveMsg = output.contains("DEBUG Timer4Method") && output.contains("WARNING Timer4Method");
		Assert.assertTrue(haveMsg);
	}
	
	@Test
	public void testShowDebugWarningAndError() {
		example.showDebugWarningAndError();
		String output = outContent.toString();
		boolean haveMsg = output.contains("DEBUG Timer4Method") && output.contains("WARNING Timer4Method") && output.contains("ERROR Timer4Method");
		Assert.assertTrue(haveMsg);
	}
	
	@Test
	public void testShowDebugWarningErrorAndSendEmail() {
		example.showDebugWarningErrorAndSendEmail();
		String output = outContent.toString();
		boolean haveMsg = output.contains("DEBUG Timer4Method") && output.contains("WARNING Timer4Method") && output.contains("ERROR Timer4Method") && output.contains("ERROR-MAIL Timer4Method");
		Assert.assertTrue(haveMsg);		
	}
}
