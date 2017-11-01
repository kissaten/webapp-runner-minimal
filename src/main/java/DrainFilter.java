import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

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
    // maybe we can block on this going to zero?
    // i think there is a semaphore thing that can have many lock holders
    while (activeConnections.get() > 0) {
      try { Thread.sleep(1000); } catch (InterruptedException e) { throw new RuntimeException(e);
      }
    }
  }
}
