package ua.pp.darknsoft.support;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Andrew on 18.01.2017.
 */
public class StaticMethod {

    public static String myRdrct(HttpServletRequest httpServletRequest) {
        String scheme = httpServletRequest.getScheme() + "://";
        String serverName = httpServletRequest.getServerName();
        String serverPort = (httpServletRequest.getServerPort() == 80) ? "" : ":" + httpServletRequest.getServerPort();
        String contextPath = httpServletRequest.getContextPath();
        String rdrct = "redirect:" + scheme + serverName + serverPort;
        return rdrct;
    }
    public static String rejectHtml(String input) {
        String output = "";
        if (input == null) return "";
        else {
            input = input.replaceAll("<", "&lt;");
            input = input.replaceAll(">", "&gt;");
            input = input.replaceAll("\'", "&rsquo;");
            output = input.replaceAll("\"", "&quot;");
            return output;
        }
    }

    public static String repairHtml(String input) {
        String output = "";
        if (input == null) return "";
        else {
            input = input.replaceAll("&lt;", "<");
            input = input.replaceAll("&gt;", ">");
            input = input.replaceAll("&rsquo;", "\'");
            output = input.replaceAll("&quot;", "\"");
            return output;
        }
    }
}
