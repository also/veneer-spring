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

package com.ryanberdeen.veneer;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.View;

import com.ryanberdeen.veneer.RenderContext;
import com.ryanberdeen.veneer.VeneerSupport;

public class VeneerView extends WebApplicationObjectSupport implements View {
	
	private String name;
	
	public VeneerView(String name) {
		this.name = name;
	}
	
	public String getContentType() {
		return null;
	}
	
	public String getPath() {
		return name;
	}

	@SuppressWarnings("unchecked")
	public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (model != null) {
			for (Map.Entry<String, Object> modelEntry : ((Map<String, Object>) model).entrySet()) {
				request.setAttribute(modelEntry.getKey(), modelEntry.getValue());
			}
		}

		RenderContext renderContext = VeneerSupport.getContext(getServletContext(), request);
		String value = renderContext.renderMain(response, name);
		
		if (value != null) {
			response.getWriter().write(value);
		}
	}

}
  