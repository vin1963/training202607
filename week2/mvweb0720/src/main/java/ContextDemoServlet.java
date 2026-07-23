

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ContextDemoServlet
 */
@WebServlet("/context-demo")
public class ContextDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContextDemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = super.getServletContext();
		 // 1. 設定/取得全域屬性
        context.setAttribute("globalVar", "全域變數");
        String value = (String) context.getAttribute("globalVar");
        
		// 2. 取得初始化參數
        String appName = context.getInitParameter("appName");
        
        // 3. 取得虛擬路徑對應的真實路徑
        String realPath = context.getRealPath("/WEB-INF");
        
        // 4. 取得 MIME 類型
        String mimeType = context.getMimeType("mypicture.jpg");
        
        // 5. 取得版本資訊
        int majorVersion = context.getMajorVersion();
        int minorVersion = context.getMinorVersion();
        
        // 6. 記錄日誌        
        context.log("這是一則日誌訊息");
        
        // 輸出結果
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h2>ServletContext Demo</h2>");
        out.println("<p>應用程式名稱：" + appName + "</p>");
        out.println("<p>全域變數：" + value + "</p>");
        out.println("<p>真實路徑：" + realPath + "</p>");
        out.println("<p>MIME 類型：" + mimeType + "</p>");
        out.println("<p>Servlet 版本：" + majorVersion + "." + minorVersion + "</p>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
