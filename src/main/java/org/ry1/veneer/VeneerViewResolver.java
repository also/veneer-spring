/* $Id$ */

package org.ry1.veneer;

import java.util.Locale;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class VeneerViewResolver extends WebApplicationObjectSupport implements ViewResolver, Ordered, InitializingBean {
	private int order;
	private Configuration configuration = new Configuration();
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public void setPrefix(String prefix) {
		configuration.setPrefix(prefix);
	}
	
	public void setSuffix(String suffix) {
		configuration.setSuffix(suffix);
	}
	
	public void setDefaultTemplate(String defaultTemplate) {
		configuration.setDefaultTemplateName(defaultTemplate);
	}
	
	public void afterPropertiesSet() throws Exception {
		VeneerSupport.setConfiguration(getServletContext(), configuration);
	}
	
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		VeneerView result = new VeneerView(configuration.getPrefix() + viewName + configuration.getSuffix(), configuration.getDefaultTemplateName());
		result.setApplicationContext(getApplicationContext());
		result.setServletContext(getServletContext());
		return result;
	}

}
