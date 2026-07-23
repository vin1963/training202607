

import java.util.List;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextDemoListener
 *
 */
@WebListener
public class Context2DemoListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Context2DemoListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	      System.out.println("=== Context2DemoListener Web 應用程式啟動 ===");
         List<String> names=List.of("Alan","Bread","Cindy","Dean","Eddie");
         // 取得 ServletContext
         sce.getServletContext().setAttribute("names", names);
         
       
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
