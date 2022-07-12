package adapter;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * @className: PACKAGE_NAME.GenericServlet
 * @description: 适配器
 * @author: 江骏杰
 * @create: 2022-07-11 21:43
 */
public abstract class GenericServlet implements Servlet {
    private ServletConfig config;
    @Override
    public final void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
        init();
    }

    public void init() {

    }
    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
    /**
     * 适配器设计模式
     * 模板设计模式
     * 封装机制
     * 最常用的是抽象方法
     */
}
