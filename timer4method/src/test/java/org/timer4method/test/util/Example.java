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

	@Timer4Method(maxTimeWarning=10l)
	public void methodExample() {
		for (int i = 0; i < 3000; i++) {
			String hello = "Hello";
		}
	}
}
