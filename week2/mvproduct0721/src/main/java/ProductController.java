

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import model.*;
/**
 * Servlet implementation class ProductController
 */
@WebServlet("/products/*")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDao = new ProductDAOImpl();   
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getPathInfo();  // 取得 /product 之後的路徑，如 /list, /edit
		//response.getWriter().append("Served at: ").append(path);
		switch(path) {
		case "/list":
			List<Product> data= listProduct(request,response);			
			request.setAttribute("products", data);			
			request.getRequestDispatcher("../productlist.jsp").forward(request, response);
			break;
		case "/delete":
			deleteProduct(request,response);					
			response.sendRedirect("../products/list");
			break;
		case "/add":
			request.getRequestDispatcher("../productform.jsp").forward(request, response);
			break;
		case "/edit":
			showEditForm(request,response);
			break;
		default:
			response.getWriter().append("Invalid Path : ").append(path);
		}
	}
	void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		Optional<Product> opt= productDao.findById(Integer.parseInt(id));
		if(opt.isPresent()) {
			Product p=opt.get();
			try {
			  request.setAttribute("product", p);
			  request.getRequestDispatcher("../productedit.jsp").forward(request, response);
			  
			}catch(Exception ex) {}
		}else {
			try {
			  response.getWriter().append(id+" No Data Found");
			}catch(Exception ex) {}
		}
	}
	void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		productDao.delete(Integer.parseInt(id));
		List<Product> data=productDao.findAll();
	 	  try {
	 	     response.getWriter().append(data.toString());
	 	   }catch(Exception ex) {}
	}
    private void addProduct(HttpServletRequest request, HttpServletResponse response) {
    	   String name=request.getParameter("name");
    	   String price=request.getParameter("price");
    	   String stock=request.getParameter("stock");
    	   String category=request.getParameter("category");
    	   Product p=new Product(name,new BigDecimal(price),Integer.parseInt(stock),category);
    	   productDao.insert(p);
    	   try {
    	     response.getWriter().append("insert completed");
    	   }catch(Exception ex) {}
    }
    private List<Product> listProduct(HttpServletRequest request, HttpServletResponse response) {
    	       List<Product> list= productDao.findAll();
    	       return list;
    }
    void updateProduct(HttpServletRequest request, HttpServletResponse response) {
    	   String id=request.getParameter("id");
    	   String name=request.getParameter("name");
 	   String price=request.getParameter("price");
 	   String stock=request.getParameter("stock");
 	   String category=request.getParameter("category");
 	   Product p=new Product(name,new BigDecimal(price),Integer.parseInt(stock),category);
 	   p.setId(Integer.parseInt(id));
 	   productDao.update(p);
 	   List<Product> data=productDao.findAll();
 	  try {
 	     response.getWriter().append(data.toString());
 	   }catch(Exception ex) {}
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getPathInfo();  // 取得 /product 之後的路徑，如 /list, /edit
		//response.getWriter().append("Served at: ").append(path);
		switch(path) {
		case "/add":
			addProduct(request,response);
			List<Product> data= listProduct(request,response);			
			request.setAttribute("products", data);			
			request.getRequestDispatcher("../productlist.jsp").forward(request, response);
			break;
		case "/update":
			updateProduct(request,response);				
			request.setAttribute("products", listProduct(request,response));			
			request.getRequestDispatcher("../productlist.jsp").forward(request, response);
			break;
		default:
			response.getWriter().append("Invalid Path : ").append(path);
		}
	}

}
