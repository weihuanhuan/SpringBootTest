package filter;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by JasonFitch on 12/6/2018.
 */
public class BESLogFilter implements Filter {

    private AtomicLong atomicLong = new AtomicLong();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(atomicLong.get() + ": " + "BESLogFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String servletContextName = request.getServletContext().getServletContextName();
        System.out.println(servletContextName);
        System.out.println(atomicLong.addAndGet(1) + ": " + "BESLogFilter doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println(atomicLong.get() + ": " + "BESLogFilter destroy");
    }
}
