package filter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.apache.tomcat.websocket.server.WsFilter;

/**
 * Created by JasonFitch on 12/5/2018.
 */
@WebFilter(urlPatterns = "/*", filterName = "BESWsFilter")
public class BESWsFilter extends WsFilter {

    private AtomicLong atomicLong = new AtomicLong();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(atomicLong.get() + ": " + "BESWsFilter init");
        super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String servletContextName = request.getServletContext().getServletContextName();
        System.out.println(servletContextName);
        System.out.println(atomicLong.addAndGet(1) + ": " + "BESWsFilter doFilter");
        super.doFilter(request, response, chain);
    }
}
