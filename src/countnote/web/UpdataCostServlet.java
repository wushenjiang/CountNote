package countnote.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
 * Servlet implementation class UpdataCostServlet
 */
@WebServlet("/updateCost")
public class UpdataCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdataCostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取cid
		request.setCharacterEncoding("UTF-8");
		String cid = request.getParameter("cid");
		Map<String, String[]> dcostMap = request.getParameterMap();
		Cost cost =new Cost();
		try {
			BeanUtils.populate(cost, dcostMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cost.setCid(cid);
		//传递到service层
		CostService service = new CostService();
		int uFlag= 0;
		try {
			uFlag =service.updateCost(cost);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(uFlag>0) {
			request.setAttribute("uinfo", "修改成功");
			request.getRequestDispatcher("/showAllCost").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
