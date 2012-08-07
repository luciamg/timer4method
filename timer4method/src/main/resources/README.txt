Timer4Method is a library to control the elapsed times of the methods on a spring application.
Configuration:
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
		<!-- Ignora las excepciones por no encontrar una referencia, nos permite usar mas de un PlaceholderConfigurer -->
		<property name="ignoreUnresolvablePlaceholders" value="false" />
	</bean>
	
- Add the timer4method.properties file to the classpath (there is an example of that file on the test directory).
- Add the @Timer4Method annotation (with the attributes you want) to the method you want to control.

If you have any question or detect a bug, please send an email to luciamanescau@yahoo.es	