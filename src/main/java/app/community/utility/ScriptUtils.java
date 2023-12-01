package app.community.utility;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ScriptUtils {

    public static void alertScript(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + message + "');</script>");
        out.flush();
    }

    public static void alertAndRedirectScript(HttpServletResponse response, String message, String uri) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('" + message + "'); ");
        out.println("try { window.location.replace('" + uri + "'); }");
        out.println("catch (e) { window.location = '" + uri + "'; }");
        out.println("</script>");
        out.flush();
    }

    public static void bootstrapAlertScript(HttpServletResponse response, String type, String title, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("div class=\"alert alert_\" + type");
        out.println("<div class=\"alert--icon\"><i class=\"fas fa-bell\"></i></div>");
        out.println("<div class=\"alert--content\">");
        out.println("<strong>" + title + "</strong> " + message);
        out.println("</div>");
        out.println("<div class=\"alert--close\"><i class=\"far fa-times-circle\"></i></div>");
        out.println("</div>");
        out.flush();
    }
}