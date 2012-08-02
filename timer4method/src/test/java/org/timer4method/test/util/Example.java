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
package org.timer4method.test.util;

import org.springframework.stereotype.Component;
import org.timer4method.annotations.Timer4Method;

@Component
public class Example {

	@Timer4Method()
	public void showOnlyDebug() {		
	}
	
	@Timer4Method(maxTimeWarning=10l)
	public void showDebugAndWarning() {
		final long start = System.currentTimeMillis();
		long elapsed = 0;
		while (elapsed < 15l) {
			elapsed = System.currentTimeMillis() - start;
		}
	}
	
	@Timer4Method(maxTimeWarning=10l, maxTimeError=20l)
	public void showDebugWarningAndError() {
		final long start = System.currentTimeMillis();
		long elapsed = 0;
		while (elapsed < 30l) {
			elapsed = System.currentTimeMillis() - start;
		}
	}
	
	@Timer4Method(maxTimeWarning=10l, maxTimeError=20l, maxTimeMail=30l)
	public void showDebugWarningErrorAndSendEmail() {
		final long start = System.currentTimeMillis();
		long elapsed = 0;
		while (elapsed < 40l) {
			elapsed = System.currentTimeMillis() - start;
		}
	}
}
