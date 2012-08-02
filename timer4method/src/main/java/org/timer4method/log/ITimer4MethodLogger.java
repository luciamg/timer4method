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

/**
 * Interface with the methods for the Timer4MethodLogger
 * @author Lucia
 *
 */
public interface ITimer4MethodLogger {

	/**
	 * Indicates if no log must be show
	 * @return	True if no log must be show, false in other case
	 */
	Boolean isNone();
	/**
	 * Indicates if the log must be show in debug level. In that case, all logs will be show (debug,
	 * warning and error), but no mail will be send.
	 * @return True if debug level is enabled, false in other case
	 */
	Boolean isDebug();
	/**
	 * Indicates if the log must be show in warning level. In that case warning and errors log will be show
	 * but no mail will be send.
	 * @return True if warning level is enabled, false in other case
	 */
	Boolean isWarn();
	/**
	 * Indicates if the log must be show in error level. In that case only errors log will be show
	 * and no mail will be send.
	 * @return True if error level is enabled, false in other case
	 */
	Boolean isError();
	/**
	 * Indicates if the timer4method have all the necessary information to send an email.
	 * @return True if all the information had been given, false in other case
	 */
	Boolean canSendMail();
	/**
	 * Indicates if the timer4method have all the necessary information to send an authenticated mail.
	 * @return True if all the information had been given, false in other case
	 */
	Boolean sendAuthenticatedMail();
	/**
	 * Indicates if the warning log must be written (depending on the elapsed time of the method, and the maximun
	 * warning time) 
	 * @param time	Elapsed time of the method
	 * @return	True if the warning log must be written, false in other case
	 */
	Boolean writeWarning (final Long time);
	/**
	 * Indicates if the error log must be written (depending on the elapsed time of the method, and the maximun
	 * error time) 
	 * @param time	Elapsed time of the method
	 * @return	True if the error log must be written, false in other case
	 */
	Boolean writeError (final Long time);
	/**
	 * Indicates if the mail log must be written and send email (depending on the elapsed time of the method, and the maximun
	 * mail time) 
	 * @param time	Elapsed time of the method
	 * @return	True if the mail log must be written and the email must be send, false in other case
	 */
	Boolean writeMail (final Long time);
	/**
	 * Get the host name to send an email
	 * @return
	 */
	String getHost();
	/**
	 * Get the port to send an email
	 * @return
	 */
	String getPort();
	/**
	 * Get the username to send an authenticated email
	 * @return
	 */
	String getUser();
	/**
	 * Get the password to send an authenticated email
	 * @return
	 */
	String getPassword();
	/**
	 * Get the 'from' value to send an email
	 * @return
	 */
	String getFrom();
	/**
	 * Get the 'To' value to send an email
	 * @return
	 */
	String getTo();
	/**
	 * Get the subject of the email
	 * @return
	 */
	String getSubject();
	/**
	 * Change the maximun error time (if the time passed is null or zero, the value won't be changed)
	 * @param error	New value for the maximun error time
	 */
	void setTimeError(Long error);
	/**
	 * Change the maximun warning time (if the time passed is null or zero, the value won't be changed)
	 * @param error	New value for the maximun warning time
	 */
	void setTimeWarning(Long warning);
	/**
	 * Change the maximun mail time (if the time passed is null or zero, the value won't be changed)
	 * @param error	New value for the maximun mail time
	 */
	void setTimeMail(Long mail);
}
