Description

Timer4Method is a library to control the elapsed times of the methods on a spring application.

Configuration:

- Add to the pom.xml:
	<repositories>	    
	    <repository>
	        <id>luciamg-public-releases</id>
	        <url>https://raw.github.com/luciamg/luciamg/master/releases</url>
	    </repository>
	</repositories>

	<dependencies>
		<!-- Timer4Method -->
		<dependency>
			<groupId>timer4method</groupId>
			<artifactId>timer4method</artifactId>
			<version>0.2</version>
        	</dependency>        
		.......
	</dependencies>
	
- Add to the context.xml:
        	
	<!-- Activa @AspectJ support -->
	<aop:aspectj-autoproxy/>

	<bean id="encryptor" class="org.jasypt.encryption.pbe.StandardPBEStringEncryptor">
		<property name="algorithm" value="PBEWithMD5AndDES" />
		<property name="password" value="PfKOeRYPinqn7kb" />
	</bean>

	<bean id="timer4Method" class="org.timer4method.aspects.Timer4MethodAspect"/>

		<bean id="propertyConfigurer" class="org.jasypt.spring.properties.EncryptablePropertyPlaceholderConfigurer">
		<constructor-arg ref="encryptor" />
		<property name="ignoreUnresolvablePlaceholders" value="false" />
	</bean>
	
- Add the timer4method.properties file to the classpath (there is an example of that file on the test directory). Properties available:
	* timer4method.log.level : Log level. It could be NONE, DEBUG, WARN or ERROR
	* timer4method.time.warning : Global elapsed time needed to show a warning message
	* timer4method.time.error : Global elapsed time needed to show an error message
	* timer4method.time.main : Global elapsed time needed to send an email with the error message
	* timer4method.appender.email.*** : Configuration for the emails
	
- Add to the method you want to control the @Timer4Method annotation with the attributes you want: maxTimeWarning (elapsed time needed to 
	show a warning message only for that method), 	maxTimeError (elapsed time needed to show an error message only for that method) and/or 
	maxTimeMail (elapsed time needed to send an email with 	the error message only for that method).

If you have any question or detect a bug, please send an email to luciamanescau@yahoo.es	