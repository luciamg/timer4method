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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.timer4method.enums.Timer4MethodLevels;

/**
 * Implementation of the timer4MethodLogger. That class reads the global configuration from the timer4method.properties on the classpath, and
 * controls when show the logs and send the email.
 * @author Lucia
 *
 */
class Timer4MethodLoggerImpl implements ITimer4MethodLogger {
	
	private static final Log logger = LogFactory.getLog(Timer4MethodLoggerImpl.class);
	/** Level of the log (NONE, DEBUG, WARN, ERROR) */
	private String logLevel = "";	
	/** Maximun elapsed time to show warning log */
	private Long timeWarning;
	/** Maximun elapsed time to show error log */
	private Long timeError;
	/** Maximun elapsed time to send email */
	private Long timeMail;
	/** Host used to send email */
	private String host;
	/** Port used to send email */
	private String port;
	/** User used to send email */
	private String user;
	/** Password used to send email */
	private String password;
	/** From used to send email */
	private String from;
	/** To used to send email */
	private String to;
	/** Subject used to send email */
	private String subject;

	/**
	 * Constructor. Read the global configuration from the timer4method.properties
	 */
	public Timer4MethodLoggerImpl() {		
		Properties props = new Properties();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(Timer4MethodLogConstants.TIME4METHOD_PROPERTIES_FILE);
		if (inputStream != null) {
			try {
				props.load(inputStream);			

				logLevel = props.getProperty(Timer4MethodLogConstants.TIMER4METHOD_LOG_LEVEL);
				host = props.getProperty(Timer4MethodLogConstants.LOG4METHOD_EMAIL_SMTPHOST);
				port = props.getProperty(Timer4MethodLogConstants.LOG4METHOD_EMAIL_SMTPPORT);
				user = props.getProperty(Timer4MethodLogConstants.LOG4METHOD_EMAIL_SMTPUSERNAME);
				password = props.getProperty(Timer4MethodLogConstants.LOG4METHOD_EMAIL_SMTPPASSWORD);
				from = props.getProperty(Timer4MethodLogConstants.LOG4METHOD_EMAIL_FROM);
				to = props.getProperty(Timer4MethodLogConstants.LOG4METHOD_EMAIL_TO);
				subject = props.getProperty(Timer4MethodLogConstants.LOG4METHOD_EMAIL_SUBJECT);
				try {
					timeWarning = Long.parseLong(props.getProperty(Timer4MethodLogConstants.MAX_TIME_WARNING));
				} catch (NumberFormatException en) {
					en.printStackTrace();
				}
				try {
					timeError = Long.parseLong(props.getProperty(Timer4MethodLogConstants.MAX_TIME_ERROR));
				} catch (NumberFormatException en) {
					en.printStackTrace();
				}
				try {
					timeMail = Long.parseLong(props.getProperty(Timer4MethodLogConstants.MAX_TIME_MAIL));
				} catch (NumberFormatException en) {
					en.printStackTrace();
				}
			
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Impossible to read properties file");
			}
		} else {
			logger.debug("No properties file exists");
		}
	}
	
	@Override
	public Boolean isNone() {
		if (!StringUtils.isEmpty(logLevel) && logLevel.equals(Timer4MethodLevels.NONE.name())) {
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean isDebug() {
		return logLevel.equals(Timer4MethodLevels.DEBUG.name());
	}
	
	@Override
	public Boolean isWarn() {
		return logLevel.equals(Timer4MethodLevels.DEBUG.name()) || logLevel.equals(Timer4MethodLevels.WARN.name());
	}
	
	@Override
	public Boolean isError() {
		return logLevel.equals(Timer4MethodLevels.DEBUG.name()) || logLevel.equals(Timer4MethodLevels.WARN.name()) || logLevel.equals(Timer4MethodLevels.ERROR.name());
	}
	
	@Override
	public Boolean canSendMail() {		
		if (!StringUtils.isEmpty(host) && !StringUtils.isEmpty(from) && !StringUtils.isEmpty(to) && !StringUtils.isEmpty(subject)) {
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean sendAuthenticatedMail() {
		if (!StringUtils.isEmpty(password) && !StringUtils.isEmpty(user)) {
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean writeWarning (final Long time) {
		if (timeWarning != null && timeWarning.compareTo(time) < 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean writeError (final Long time) {
		if (timeError != null && timeError.compareTo(time) < 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean writeMail (final Long time) {
		if (timeMail != null && timeMail.compareTo(time) < 0) {
			return true;
		}
		return false;
	}	

	@Override
	public String getHost() {
		return host;
	}
	
	@Override
	public String getPort() {
		return port;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getFrom() {
		return from;
	}

	@Override
	public String getTo() {
		return to;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public void setTimeError(Long error) {
		if (error != null && error.compareTo(0l) != 0) {
			timeError = error;
		}		
	}

	@Override
	public void setTimeWarning(Long warning) {
		if (warning != null && warning.compareTo(0l) != 0) {
			timeWarning = warning;
		}
	}

	@Override
	public void setTimeMail(Long mail) {
		if (mail != null && mail.compareTo(0l) != 0) {
			timeMail = mail;
		}
	}
	
	
}
