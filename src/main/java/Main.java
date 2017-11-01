import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

public class Main extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("request: " + req.getHeader("X-Request-ID"));
//    try {
//      Thread.sleep(25000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    resp.getWriter().print("Hello from Java! Charset: " + Charset.defaultCharset());
  }
}
