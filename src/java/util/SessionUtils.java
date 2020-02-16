/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
    
    private static SessionUtils sessionUtil = null;

    public static SessionUtils getInstance() {
        if (sessionUtil == null) {
            sessionUtil = new SessionUtils();
        }
        return sessionUtil;
    }

    public void putValue(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    public void remove(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }
}
