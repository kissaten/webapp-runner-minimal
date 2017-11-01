import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Joe Kutner on 10/19/17.
 *         Twitter: @codefinger
 */
public class DrainFilter implements Filter {

  private AtomicInteger activeConnections = new AtomicInteger(0);

  public void init(FilterConfig filterConfig) throws ServletException { }

  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain
                      ) throws IOException, ServletException {
    activeConnections.incrementAndGet();
    filterChain.doFilter(servletRequest, servletResponse);
    activeConnections.decrementAndGet();
  }

  public void destroy() {
    while (activeConnections.get() > 0) {
      LockSupport.parkNanos(activeConnections, 1);
    }
  }
}
