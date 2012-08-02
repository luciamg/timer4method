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
package org.timer4method.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.timer4method.exceptions.Timer4MethodException;
import org.timer4method.log.ITimer4MethodLogger;

/**
 * Class to send the information email when the elapsed time of a method is bigger than the maximun mail time
 * @author Lucia
 *
 */
public class Timer4MethodMail {
	
	public static final void sendMail(final Long elapsedTime, final String methodName, final ITimer4MethodLogger timer4methodConfiguration) 
			throws Timer4MethodException {
		try {
			Properties props = new Properties();
		    props.put("mail.smtp.host", timer4methodConfiguration.getHost());
		    Authenticator auth = null;
		    if (timer4methodConfiguration.sendAuthenticatedMail()) {
		    	auth = new SMTPAuthenticator(timer4methodConfiguration.getUser(), timer4methodConfiguration.getPassword());
		    	props.put("mail.smtp.auth", "true");
		    }
		    
		    Session session = Session.getDefaultInstance(props, auth);
	
		    // create a message
		    Message msg = new MimeMessage(session);
	
		    // set the from and to address
		    InternetAddress addressFrom;
			
				addressFrom = new InternetAddress(timer4methodConfiguration.getFrom());		
		    msg.setFrom(addressFrom);
	
		    InternetAddress[] addressTo = new InternetAddress[1];
		    addressTo[0] = new InternetAddress(timer4methodConfiguration.getTo());	    
		    msg.setRecipients(Message.RecipientType.TO, addressTo);
	
		    // Setting the Subject and Content Type
		    msg.setSubject(timer4methodConfiguration.getSubject());
		    String message = "LogTime [EMAIL]: " + methodName + " elapsed " + elapsedTime + " miliseconds. ";
		    msg.setContent(message, "text/plain");
		    Transport.send(msg);
		} catch (AddressException e) {
			throw new Timer4MethodException ("Can't send the email");
		} catch (MessagingException e) {
			throw new Timer4MethodException ("Can't send the email");
		}
	}

}
