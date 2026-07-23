

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextDemoListener
 *
 */
@WebListener
public class ContextDemoListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextDemoListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	      System.out.println("=== Web 應用程式啟動 ===");
         
         // 取得 ServletContext
         sce.getServletContext().setAttribute("appStart", new java.util.Date());
         
         // 載入設定檔
         String appName = sce.getServletContext().getInitParameter("appName");
         System.out.println("應用程式名稱：" + appName);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
