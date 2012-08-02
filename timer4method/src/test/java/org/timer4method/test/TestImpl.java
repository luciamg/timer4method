package org.timer4method.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.context.request.SessionScope;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners( {
	DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class })
	@ContextConfiguration(locations = {
			"file:./src/test/resources/applicationContext-test.xml"
	})
	public  class TestImpl implements ITest {

	/**
	 * Contexto de aplicaciones Spring.
	 *
	 */
	private ApplicationContext context;

	@Autowired
	public void setContext(final ApplicationContext context) {
		this.context = context;
		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("session", new SessionScope());
		((DefaultListableBeanFactory) context.getAutowireCapableBeanFactory()).registerScope("request", new RequestScope());
	}

	public ApplicationContext getContext() {
		return context;
	}

	
	protected MockHttpServletResponse getMockHttpServletResponse() {
		return new MockHttpServletResponse();
	}
}