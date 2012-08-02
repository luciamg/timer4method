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
package org.timer4method.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Annotation for the methods to evaluate the time elapsed
 * @author Lucia
 *
 */
public @interface Timer4Method {
	/** Time elapsed necessary to write warning log */
	public long maxTimeWarning() default 0;
	/** Time elapsed necessary to write error log */
	public long maxTimeError() default 0;
	/** Time elapsed necessary to send email */
	public long maxTimeMail() default 0;
}
