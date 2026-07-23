
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        
        // 記錄請求資訊
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
        LocalDateTime now = LocalDateTime.now();
        
        System.out.println("[" + now + "] " + method + " " + uri + " from " + ip);
        
        long startTime = System.currentTimeMillis();
        
        // 繼續執行
        chain.doFilter(req, res);
        
        // 記錄處理時間
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("[" + now + "] " + uri + " 完成 | " + duration + "ms");
    }

    @Override
    public void destroy() {}
}
