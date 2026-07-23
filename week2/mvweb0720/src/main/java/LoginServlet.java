

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import model.User;
import java.util.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 模擬資料庫
    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("admin", new User(1, "admin", "管理員", "admin"));
        users.put("tom", new User(2, "tom", "Tom", "user"));
        users.put("jerry", new User(3, "jerry", "Jerry", "user"));
    }
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // 檢查是否已登入
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        
        // 顯示登入頁面
        request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		 String username = request.getParameter("username");
	     String password = request.getParameter("password");
	        
	        // 驗證帳號密碼
	        User user = users.get(username);
	        if (user != null && "1234".equals(password)) {
	            // 登入成功，建立 Session
	            HttpSession session = request.getSession(true);
	            session.setAttribute("user", user);
	            session.setAttribute("loginTime", new java.util.Date());
	            session.setAttribute("id", session.getId());
	            // 設定 Session 超時時間（30分鐘）
	            session.setMaxInactiveInterval(30 * 60);
	            System.out.println("sessionid="+session.getId());
	            // 導向首頁
	            response.sendRedirect(request.getContextPath() + "/home");
	        } else {
	            // 登入失敗
	            request.setAttribute("error", "帳號或密碼錯誤");
	            request.getRequestDispatcher("/login.jsp").forward(request, response);
	        }
	}

}
