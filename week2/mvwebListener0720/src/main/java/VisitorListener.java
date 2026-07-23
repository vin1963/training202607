
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;

@WebListener
public class VisitorListener implements ServletContextListener, HttpSessionListener {

    private int totalVisitors = 0;
    private int onlineUsers = 0;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("totalVisitors", 0);
        sce.getServletContext().setAttribute("onlineUsers", 0);
        System.out.println("VisitorListener 初始化完成");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("VisitorListener 關閉");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        var context = se.getSession().getServletContext();
        
        // 更新線上人數
        onlineUsers++;
        context.setAttribute("onlineUsers", onlineUsers);
        
        // 更新總訪客數
        totalVisitors++;
        context.setAttribute("totalVisitors", totalVisitors);
        
        // 記錄 Session 資訊
        System.out.println("新訪客 - Session ID: " + se.getSession().getId());
        System.out.println("線上人數：" + onlineUsers + " | 總訪客數：" + totalVisitors);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        var context = se.getSession().getServletContext();
        
        onlineUsers--;
        if (onlineUsers < 0) onlineUsers = 0;
        context.setAttribute("onlineUsers", onlineUsers);
        
        System.out.println("訪客離開 - 線上人數：" + onlineUsers);
    }
}
