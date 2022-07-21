package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.io.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Customer Management</title>\n");
      out.write("        <link href=\"cool.jsp\" rel=\"stylesheet\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Customers</h1>\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <th>First Name</th>\n");
      out.write("                <th>Last Name</th>\n");
      out.write("                <th>Rewards Member?</th>\n");
      out.write("                <th>Rewards Points</th>\n");
      out.write("            </tr>\n");
      out.write("            ");

            Process process = Runtime.getRuntime().exec("java -jar customer-management-app-1.0-SNAPSHOT.jar export all customers.csv");
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            File file = new File("customers.csv");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                boolean isRewardsMember = false;
                if (!parts[9].equals("")) {
                    isRewardsMember = Boolean.parseBoolean(parts[9]);
                }
                String isRewardsMemberString = isRewardsMember ? "Yes" : "No";
                long rewardsPoints = 0;
                if (parts.length == 11 && !parts[10].equals("")) {
                    rewardsPoints = Long.parseLong(parts[10]);
                }
                
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print( parts[0] );
      out.write("</td><td>");
      out.print( parts[1] );
      out.write("</td><td>");
      out.print( isRewardsMemberString );
      out.write("</td><td>");
      out.print( rewardsPoints );
      out.write("</td>\n");
      out.write("                </tr>\n");
      out.write("                ");

                line = bufferedReader.readLine();
            }
            
      out.write("\n");
      out.write("        </table>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
