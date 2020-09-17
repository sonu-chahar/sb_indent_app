/*
 * Copyright 2005 Joe Walker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.directwebremoting.hibernate;

import java.beans.PropertyDescriptor;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.directwebremoting.ConversionException;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.extend.Property;
import org.directwebremoting.extend.PropertyDescriptorProperty;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

/**
 * A {@link Property} that catches Hibernate exceptions. This is useful for
 * Hibernate 2 where lazy loading results in an exception and you are unable to
 * detect and prevent this.
 * 
 * @author Joe Walker [joe at getahead dot ltd dot uk]
 */
public class H4PropertyDescriptorProperty extends PropertyDescriptorProperty {

	protected final Logger log = LogManager.getLogger(this.getClass());

	/**
	 * Simple constructor
	 * 
	 * @param descriptor The PropertyDescriptor that we are proxying to
	 */
	public H4PropertyDescriptorProperty(PropertyDescriptor descriptor) {
		super(descriptor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.directwebremoting.impl.PropertyDescriptorProperty#getValue(java.lang.
	 * Object)
	 */
	@Override
	public Object getValue(Object bean) {
		try {
			if (!(bean instanceof HibernateProxy)) {
				return descriptor.getReadMethod().invoke(bean);
			} else {
				if (Hibernate.isPropertyInitialized(bean, descriptor.getName())) {
					return descriptor.getReadMethod().invoke(bean);
				} else {
					HibernateProxy proxy = (HibernateProxy) bean;
					LazyInitializer initializer = proxy.getHibernateLazyInitializer();
					SessionImplementor implementor = initializer.getSession();
					if (implementor.isOpen()) {
						return descriptor.getReadMethod().invoke(bean);
					}
					return updateGivenBeanIfSessionAvailable(bean);
				}
			}
		} catch (Exception ex) {
			throw new ConversionException(bean.getClass(), ex);
		}
	}

	private Object updateGivenBeanIfSessionAvailable(Object bean) {
		ServletContext context = WebContextFactory.get().getServletContext();
		try (Session session = H4SessionAjaxFilter.getCurrentSession(context);) {
			if (session != null) {
				session.update(bean);
				return descriptor.getReadMethod().invoke(bean);
			}
		} catch (Exception e) {
			log.debug(e.getStackTrace());
			throw new ConversionException(bean.getClass(), e);
		}
		return null;
	}

	@Override
	public int hashCode() {
		return descriptor.getReadMethod().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		H4PropertyDescriptorProperty that = (H4PropertyDescriptorProperty) obj;

		return this.descriptor.getReadMethod().equals(that.descriptor.getReadMethod());
	}
}