/**
 * Copyright 2014 Juliana Louback
 * Copyright (C) 2009 The Libphonenumber Authors
 * This file is part of DialAssist.

    DialAssist is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    DialAssist is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with DialAssist.  If not, see <http://www.gnu.org/licenses/>.
 */
package main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@WebServlet("/validationservlet/*")
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ValidationServlet() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	String text = "";
    	String number = request.getParameter("number");
    	String country = request.getParameter("code");
    	PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    	try {
    		PhoneNumber phoneNumber;
    		if(number.startsWith("+")) {
    			phoneNumber = phoneUtil.parse(number,"");
    		}
    		else {
    			phoneNumber = phoneUtil.parse(number, country);
    		}
    		  if(phoneUtil.isValidNumber(phoneNumber)) {
    			  text = phoneUtil.format(phoneNumber, PhoneNumberFormat.E164);
    		  }else {
    			  text = "error";
    		  }
    		} catch (NumberParseException e) {
    		  System.err.println("NumberParseException was thrown: " + e.toString());
    		  text = "error";
    		}
        response.setContentType("text/plain");  
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(text);  
        
    }

}
