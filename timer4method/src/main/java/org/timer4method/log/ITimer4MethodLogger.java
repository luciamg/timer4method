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
package org.timer4method.log;

public interface ITimer4MethodLogger {

	Boolean isNone();
	Boolean isDebug();
	Boolean isWarn();
	Boolean isError();
	Boolean canSendMail();
	Boolean sendAuthenticatedMail();
	Boolean writeWarning (final Long time);
	Boolean writeError (final Long time);
	Boolean writeMail (final Long time);
	String getHost();
	String getUser();
	String getPassword();
	String getFrom();
	String getTo();
	String getSubject();
	void setTimeError(Long error);
	void setTimeWarning(Long warning);
	void setTimeMail(Long mail);
}
