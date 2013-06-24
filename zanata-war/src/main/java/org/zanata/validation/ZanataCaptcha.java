/*
 * Copyright 2013, Red Hat, Inc. and individual contributors as indicated by the
 * @author tags. See the copyright.txt file in the distribution for a full
 * listing of individual contributors.
 * 
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */
package org.zanata.validation;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.captcha.Captcha;

/**
 * @author Alex Eng <a href="mailto:aeng@redhat.com">aeng@redhat.com</a>
 * 
 * Extends of org.jboss.seam.captcha.Captcha to use org.zanata.validation.CaptchaResponse and bypass org.jboss.seam.captcha.CaptchaResponse annotation
 *
 */
@Name("org.jboss.seam.captcha.captcha")
@Scope(ScopeType.SESSION)
public class ZanataCaptcha extends Captcha
{
   private static final long serialVersionUID = 1L;

   @CaptchaResponse
   public String getCaptchaResponse()
   {
      return super.getResponse();
   }

   public void setCaptchaResponse(String input)
   {
      super.setResponse(input);
   }
}