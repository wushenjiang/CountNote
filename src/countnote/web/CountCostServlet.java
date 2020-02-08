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
import countnote.service.CostService;

/**
 * Servlet implementation class CountCostServlet
 */
@WebServlet("/countCost")
public class CountCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountCostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取前端传来的时间数据
		String date1 = request.getParameter("date1");
		String date2 = request.getParameter("date2");
		//传递到service层
		CostService service = new CostService();
		Double sum = null;
		List<Cost> countCostList = null;
		 try {
			sum =service.countCostSum(date1,date2);
			countCostList = service.countCostArray(date1,date2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 request.setAttribute("sum", sum);
		 request.setAttribute("countCostList", countCostList);
		 request.getRequestDispatcher("/sum.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
