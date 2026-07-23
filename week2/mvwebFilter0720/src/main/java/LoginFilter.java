import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter("/admin/*")  // 攔截 /admin/ 下的所有請求
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        // 取得 Session
        HttpSession session = request.getSession(false);
        
        // 檢查是否已登入
        boolean isLoggedIn = (session != null && 
                              session.getAttribute("user") != null);
        
        String loginURI = request.getContextPath() + "/login";
        boolean isLoginRequest = request.getRequestURI().equals(loginURI);
        
        if (isLoggedIn || isLoginRequest) {
            // 已登入或正在登入，繼續執行
            chain.doFilter(request, response);
        } else {
            // 未登入，導向登入頁面
        	    System.out.println("No User Session and URI not valid");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {}
}
