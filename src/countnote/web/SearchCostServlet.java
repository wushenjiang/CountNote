package countnote.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import countnote.domain.Condition;
import countnote.domain.Cost;
import countnote.domain.PageBean;
import countnote.service.CostService;

/**
 * Servlet implementation class SearchCostServlet
 */
@WebServlet("/searchCost")
public class SearchCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		//获取查询条件和查询内容
		String select = request.getParameter("select");
		String selectVal = request.getParameter("selectVal");
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr==null) {
			currentPageStr ="1";
		}
		int currentPage= Integer.parseInt(currentPageStr);
		//认为每页显示9条
		int currentCount = 9;
		List<Cost> costList = null;
		//封装成bean对象(实体类对象)
		Condition condition = new Condition();
		condition.setSelect(select);
		condition.setSelectVal(selectVal);
		//将实体传递给service层
		CostService service = new CostService();
		Double sum = 0.0;
		try {
			 costList = service.searchCost(condition);
			 sum=service.searchCostSum(condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("costList",costList);
		request.setAttribute("sum", sum);
		request.getRequestDispatcher("/begin2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
