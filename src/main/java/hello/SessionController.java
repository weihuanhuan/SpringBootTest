package hello;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by JasonFitch on 2/15/2019.
 */
@Controller
public class SessionController {


    private final  Logger logger = Logger.getLogger(this.getClass().getName());


    private void sessionOperations(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.getAttribute("");
        session.setAttribute("", "");
        session.removeAttribute("");
    }

    @RequestMapping("/session")
    public ModelAndView doSessionGet(HttpServletRequest request) throws IOException {
        System.out.println("-------------doSessionGet------------------");

        infoPrint(request);
        ModelAndView modelAndView = new ModelAndView("session");
        return modelAndView;
    }

    @PostMapping("/session")
    public String doSessionPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("-------------doSessionPost------------------");
        String attributeName = request.getParameter("attributeName");
        String attributeValue = request.getParameter("attributeValue");
        if (!(isBlank(attributeName) || isBlank(attributeValue))) {
            request.getSession().setAttribute(attributeName, attributeValue);
        }

        infoPrint(request);
        return "session";
    }

    @RequestMapping("/sessionRedirect")
    public void doSessionRedirect(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        System.out.println("-------------doSessionRedirect------------------");

        infoPrint(request);
        resp.sendRedirect(request.getContextPath() + "/session");
    }

    private void infoPrint(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (session.isNew()) {
            System.out.println("Is New ID:" + session.getId());
        } else {
            System.out.println("No New ID:" + session.getId());
        }

        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            String value = (String) session.getAttribute(key);
            System.out.println("Session Attribute:" + key + "=" + value);
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("SESSION")) {
                    System.out.println("SESSION Cookie:" + cookie.getName() + "=" + cookie.getValue());
                    break;
                }
            }
        }

        System.out.println();
    }

    private boolean isBlank(String string) {
        return (null == string || string.isEmpty());
    }

//服务端session失效
//-------------doSessionGet------------------
//    Is New ID:22459bfb-6a7f-4c48-9170-f79026e86cac
//第一次（无数据）：服务器产生新session v1，初次浏览器没有携带cookie信息，服务器响应时首次写下cookie v1，待下次浏览器访问时使用
//-------------doSessionPost------------------
//    No New ID:22459bfb-6a7f-4c48-9170-f79026e86cac
//    Session Attribute:test001=test001
//    SESSION Cookie:SESSION=MjI0NTliZmItNmE3Zi00YzQ4LTkxNzAtZjc5MDI2ZTg2Y2Fj
//第二次（未过期）：服务器使用旧session v1，浏览器首次使用之前cookie v1，
//-------------doSessionGet------------------
//    Is New ID:c1655952-510d-45a8-9ab1-01eeece07fcf
//    SESSION Cookie:SESSION=MjI0NTliZmItNmE3Zi00YzQ4LTkxNzAtZjc5MDI2ZTg2Y2Fj
//第三次（已过期）：服务器产生新session v2，浏览器仍然使用上次cookie v1，由于session过期服务器响应时写下新的cookie v2
//-------------doSessionPost------------------
//    Is New ID:5fd5e65a-85ea-43d8-8207-83eba06c7440
//    Session Attribute:session3=session3
//    SESSION Cookie:SESSION=YzE2NTU5NTItNTEwZC00NWE4LTlhYjEtMDFlZWVjZTA3ZmNm
//第四次（已过期）：服务器产生新session v3，浏览器仍然使用上次cookie v2，由于session过期服务器响应时写下新的cookie v3

//浏览器cookie失效
//-------------doSessionGet------------------
//    Is New ID:46a5c310-cad9-4de9-9186-13904ebdc299
//第一次（无数据）：服务器产生新session v1，初次浏览器没有携带cookie信息，服务器响应时首次写下cookie v1，待下次浏览器访问时使用
//-------------doSessionGet------------------
//    No New ID:46a5c310-cad9-4de9-9186-13904ebdc299
//    SESSION Cookie:SESSION=NDZhNWMzMTAtY2FkOS00ZGU5LTkxODYtMTM5MDRlYmRjMjk5
//第二次（未过期）：服务器使用旧session v1，浏览器首次使用之前cookie v1，
//-------------doSessionGet------------------
//    Is New ID:788a5943-c43c-4e3f-a962-08300b4dbcad
//    SESSION Cookie:SESSION=NDZhNWMzMTAtY2FkOS00ZGU5LTkxODYtMTM5MDRlYmRjMjk5Modified
//第三次（修改浏览器cookie ID）：这里修改产生了新的cookie 改，浏览器使用修改了的cookie 改，
//      服务器根据cookie 改，法找到匹配的session，所以认为这是一个新的会话，产生了新的session v2，同时在响应时写下新的cookie v2
//-------------doSessionGet------------------
//    No New ID:ba0f9129-9639-4559-a6af-29ba261cd9e5
//    SESSION Cookie:SESSION=YmEwZjkxMjktOTYzOS00NTU5LWE2YWYtMjliYTI2MWNkOWU
//第四次（未过期）:服务器使用旧的session v2，浏览器使用上次新的cookie v2
}
