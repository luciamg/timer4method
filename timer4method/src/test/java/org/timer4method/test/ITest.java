package org.timer4method.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


public interface ITest {

	/**
	 * Autowired to set the Spring application context.
	 *
	 */
	@Autowired
	public abstract void setContext(final ApplicationContext context);

	public ApplicationContext getContext();

}