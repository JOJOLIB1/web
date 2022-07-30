package bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.io.Serializable;

/**
 * @className: bean.User
 * @description:
 * @author: 江骏杰
 * @create: 2022-07-30 17:34
 */
public class User implements HttpSessionBindingListener, Serializable {

    private String username;
    private String userpwd;

    public User(String username, String userpwd) {
        this.username = username;
        this.userpwd = userpwd;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        ServletContext application = event.getSession().getServletContext();
        Object count = application.getAttribute("count");
        if (count != null) {
            int afCount = (Integer) count;
            afCount++;
            application.setAttribute("count",afCount);
        }else {
            application.setAttribute("count",1);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        ServletContext application = event.getSession().getServletContext();
        int count = (Integer)application.getAttribute("count");
        count--;
        application.setAttribute("count", count);
    }

}
