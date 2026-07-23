

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SessionDemoServlet
 */
@WebServlet("/session-demo")
public class SessionDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionDemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  // 1. 取得 Session（不存在則建立）
        HttpSession session = request.getSession(true);
        
        // 2. 取得 Session ID
        String sessionId = session.getId();
        
        // 3. 設定屬性
        session.setAttribute("username", "Tom");
        session.setAttribute("loginTime", new java.util.Date());
        
        // 4. 取得屬性
        String username = (String) session.getAttribute("username");
        
        // 5. 移除屬性
        session.removeAttribute("loginTime");
        
        // 6. 取得/設定超時時間（秒）
        int maxInactive = session.getMaxInactiveInterval();
        session.setMaxInactiveInterval(60 * 30);  // 30 分鐘
        
        // 7. 判斷是否為新 Session
        boolean isNew = session.isNew();
        
        // 8. 取得建立時間
        long createTime = session.getCreationTime();
        
        // 9. 取得最後存取時間
        long lastAccess = session.getLastAccessedTime();
        //session.invalidate();
        // 輸出結果
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h2>Session Demo</h2>");
        out.println("<p>Session ID: " + sessionId + "</p>");
        out.println("<p>使用者名稱: " + username + "</p>");
        out.println("<p>是否新 Session: " + isNew + "</p>");
        out.println("<p>超時時間: " + maxInactive + " 秒</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
