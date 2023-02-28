package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class Servlet extends HttpServlet{
 /**
     * this life-cycle method is invoked when this servlet is first accessed
     * by the client
     * "%JAVA_HOME%\bin\jar" -cvf a.war *
     */
    int count=0;
    public void init(ServletConfig config) {
        System.out.println("Servlet is being initialized");
    }
 
    /**
     * handles HTTP GET request
     *  WebContent\WEB-INF\classes
     * C:\Users\Yeshwanth\apache-tomcat-10.1.5-windows-x86\apache-tomcat-10.1.5\lib\servlet-api.jar
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
                count++;
        PrintWriter writer = response.getWriter();
        writer.println(
            "<html><p>Hello From Java Servlet</p><p>This Website hasbeen visited "+count+" times</p>  </html>"
        );
        writer.flush();
    }
 
    /**
     * handles HTTP POST request
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String paramWidth = request.getParameter("width");
        int width = Integer.parseInt(paramWidth);
 
        String paramHeight = request.getParameter("height");
        int height = Integer.parseInt(paramHeight);
 
        long area = width * height;
 
        PrintWriter writer = response.getWriter();
        writer.println("<html>Area of the rectangle is: " + area + "</html>");
        writer.flush();
 
    }
 
    /**
     * this life-cycle method is invoked when the application or the server
     * is shutting down
     */
    public void destroy() {
        System.out.println("Servlet is being destroyed");
    }
}