import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Main extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String var = "NONE";
    try {
      var = (String) new InitialContext().lookup("java:comp/env/environment");
    } catch (NamingException e) {
      e.printStackTrace();
    }
    resp.getWriter().print("environment: " + var);
  }
}
