import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

@WebListener
public class RequestCounterListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // 記錄請求開始時間
        sre.getServletRequest().setAttribute("startTime", System.currentTimeMillis());
        
        String uri = ((jakarta.servlet.http.HttpServletRequest) sre.getServletRequest()).getRequestURI();
        System.out.println("請求進入：" + uri);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // 計算處理時間
        Long startTime = (Long) sre.getServletRequest().getAttribute("startTime");
        if (startTime != null) {
            long duration = System.currentTimeMillis() - startTime;
            String uri = ((jakarta.servlet.http.HttpServletRequest) sre.getServletRequest()).getRequestURI();
            System.out.println("請求離開：" + uri + " | 處理時間：" + duration + "ms");
        }
    }
}