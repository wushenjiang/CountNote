package countnote.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import countnote.domain.Cost;
import countnote.service.CostService;

/**
 * Servlet implementation class AddCostServlet
 */
@WebServlet("/addCost")
public class AddCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 从表单读取参数 封装到实体类中
		Map<String, String[]> costMap = request.getParameterMap();
		Cost cost = new Cost();
		try {
			BeanUtils.populate(cost, costMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将没有设置的参数进行设置
		// cid,在数据库中中为主键
		cost.setCid(null);
		// 传递到service层
		CostService service = new CostService();
		int cflag = 0;
		try {
			cflag = service.addCost(cost);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cflag>0) {
			request.setAttribute("addinfo", "添加成功!");
			request.getRequestDispatcher("/showAllCost").forward(request, response);
		}
		else {
			request.setAttribute("addinfo", "添加失败!请重试!");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
