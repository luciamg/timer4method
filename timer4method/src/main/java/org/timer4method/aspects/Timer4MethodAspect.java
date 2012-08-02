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
package org.timer4method.aspects;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.timer4method.annotations.Timer4Method;
import org.timer4method.exceptions.Timer4MethodException;
import org.timer4method.log.ITimer4MethodLogger;
import org.timer4method.log.Timer4MethodLogFactory;
import org.timer4method.mail.Timer4MethodMail;

@Aspect
public class Timer4MethodAspect {
	private static final Log log = LogFactory.getLog(Timer4MethodAspect.class);
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
	private ITimer4MethodLogger timer4MethodLogger;
	
	public Timer4MethodAspect() {
		timer4MethodLogger = Timer4MethodLogFactory.getTimer4MethodLogger();
	}


	@Around(value="@annotation(timer4Method)", argNames="timer4Method")
	public Object profile(final ProceedingJoinPoint pjp, final Timer4Method timer4Method) throws Throwable {
		initTime4MethodAttrs(timer4Method);
		final long start = System.currentTimeMillis();
		final Object output = pjp.proceed();
		final long elapsedTime = System.currentTimeMillis() - start;
		writeLog(elapsedTime, pjp.toLongString());
		return output;
	}
	
	private void initTime4MethodAttrs (final Timer4Method timer4Method) {
		timer4MethodLogger.setTimeWarning(timer4Method.maxTimeWarning());
		timer4MethodLogger.setTimeError(timer4Method.maxTimeError());		
		timer4MethodLogger.setTimeMail(timer4Method.maxTimeMail());
	}
	
	private void writeLog (final Long elapsedTime, final String methodName) {		
		if (!timer4MethodLogger.isNone()) {
			if (timer4MethodLogger.isDebug()) {
				write("DEBUG", methodName, elapsedTime);
			}
			if (timer4MethodLogger.isWarn() && timer4MethodLogger.writeWarning(elapsedTime)) {
				write("WARNING", methodName, elapsedTime);
			}
			if (timer4MethodLogger.isError() && timer4MethodLogger.writeError(elapsedTime)) {
				write("ERROR", methodName, elapsedTime);
			}
			if (timer4MethodLogger.writeMail(elapsedTime)) {
				write("ERROR-MAIL", methodName, elapsedTime);
				if (timer4MethodLogger.canSendMail()) {
					try {
						Timer4MethodMail.sendMail(elapsedTime, methodName, timer4MethodLogger);
					} catch (final Timer4MethodException e) {
						log.error(e);
					}
				}
			}
		}
	}
	
	private void write (final String type, final String methodName, final Long elapsedTime) {
		System.out.println(type + ": [" + sdf.format(new Date()) + " Timer4Method] " + methodName + " in " + elapsedTime + " miliseconds. ");
	}
	
}
