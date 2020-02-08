package countnote.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import countnote.domain.Cost;
import countnote.domain.PageBean;
import countnote.service.CostService;

/**
 * Servlet implementation class ShowAllCostServlet
 */
@WebServlet("/showAllCost")
public class ShowAllCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllCostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//直接传递参数到service层
		CostService service = new CostService();
		//模拟当前是第一页
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr==null) {
			currentPageStr ="1";
		}
		int currentPage= Integer.parseInt(currentPageStr);
		//认为每页显示9条
		int currentCount = 9;
		PageBean<Cost> pageBean = null;
		Double sum = 0.0;
		try {
			pageBean = service.findPageBean(currentPage,currentCount);
			sum=service.sumCost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("sum", sum);
		request.getRequestDispatcher("/begin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
