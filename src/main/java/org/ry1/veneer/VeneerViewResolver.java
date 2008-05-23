/*
 * Copyright 2008 Ryan Berdeen.
 *
 * This file is part of Veneer.
 *
 * Veneer is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Veneer is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Veneer.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ry1.veneer;

import java.util.Locale;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.ryanberdeen.veneer.Configuration;
import com.ryanberdeen.veneer.VeneerSupport;

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
		VeneerView result = new VeneerView(viewName);
		result.setApplicationContext(getApplicationContext());
		result.setServletContext(getServletContext());
		return result;
	}

}
