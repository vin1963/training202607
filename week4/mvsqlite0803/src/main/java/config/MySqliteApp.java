package config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;



@OpenAPIDefinition(
	     info = @Info(
	        title = "Bookstore SQLite API",
	        version = "1.0.0",
	        description = "書籍管理 REST API（JAX-RS + JPA + SQLite）。所有 API 統一回應格式："
	            + "成功 {\"success\":true,\"data\":...}，失敗 {\"success\":false,\"error\":\"訊息\"}。",
	        contact = @Contact(name = "Bookstore Team")
	        ),
	     servers = {
	             @Server(url = "/mvsqlite0803", description = "本機 mvsqlite0803 部署路徑（context 為 /mvsqlite0803）")
	     },
	     tags = {
	             @Tag(name = "書籍", description = "書籍 CRUD 操作")
	    }
	)
@ApplicationPath("/api")
public class MySqliteApp extends Application{

}
