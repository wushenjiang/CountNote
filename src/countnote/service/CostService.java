package countnote.service;

import java.sql.SQLException;
import java.util.List;

import countnote.dao.CostDao;
import countnote.domain.Condition;
import countnote.domain.Cost;
import countnote.domain.PageBean;
import sun.print.resources.serviceui;

public class CostService {
	//展示所有消费
	public List<Cost> showAllCost() throws SQLException {
		//直接调用dao层方法
		CostDao dao = new CostDao();
		return dao.showAllCost();
		
	}
	//新增消费
	public int addCost(Cost cost) throws SQLException {
		
		//调用dao层方法
		CostDao dao = new CostDao();
		int cflag =dao.addCost(cost);
		return cflag;
		
	}
	//删除消费项
	public int delCost(String cid) throws SQLException {
		
		//调用dao层方法
		CostDao dao = new CostDao();
		return dao.delCost(cid);
	}
	//更新消费项
	public int updateCost(Cost cost) throws SQLException {
		CostDao dao = new CostDao();
		return dao.updateCost(cost);
	}
	//计算某时间段内的总和
	public Double countCostSum(String date1, String date2) throws SQLException {
		CostDao dao = new CostDao();
		return dao.countCostSum(date1,date2);
		
	}
	//获得某时间段内的所有数据
	public List<Cost> countCostArray(String date1, String date2) throws SQLException {
		CostDao dao = new CostDao();
		return dao.countCostArray(date1,date2);
		
	}
	//条件查询后的总金额
	public Double searchCostSum(Condition condition) throws SQLException {
		CostDao dao = new CostDao();
		return dao.searchCostSum(condition);
	}
	//所有数据的总金额
	public Double sumCost() throws SQLException {
		CostDao dao = new CostDao();
		return dao.sumCost();
	}
	//分页
	public PageBean findPageBean(int currentPage,int currentCount) throws SQLException {
		CostDao dao = new CostDao();
		//目的:就是想办法封装一个PageBean并返回
		PageBean pageBean = new PageBean();
		//currentPage
		pageBean.setCurrentPage(currentPage);
		//currentCount
		pageBean.setCurrentCount(currentCount);
		//totalCount
		int totalCount = dao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		//totalPage
		/*
		 * 总条数 	当前页显示的条数			总页数
		 * 10		4						3
		 * 11		4						3
		 * 12		4						3
		 * 13		4						4
		 * 公式:总页数=Math.ceil(总条数/当前显示的条数)
		 */
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		//costList
		/*
		 * 页数与limit起始索引的关系
		 * 每页四条
		 * 页数 起始索引 每页显示条数
		 *  1   0 		4
		 *  2   4		4
		 *  3   8		4
		 *  索引index = (当前页数-1)*每页显示的条数
		 */
		 int index = (currentPage-1)*currentCount;
		List<Cost> costList = dao.findCostListForPageBean(index,currentCount);
		pageBean.setCostList(costList);
		return pageBean;
	}
	//条件查询记账记录
	/*public PageBean<Cost> searchCost(Condition condition, int currentPage, int currentCount) throws SQLException {
		CostDao dao = new CostDao();
		//目的:就是想办法封装一个PageBean并返回
		PageBean pageBean = new PageBean();
		//currentPage
		pageBean.setCurrentPage(currentPage);
		//currentCount
		pageBean.setCurrentCount(currentCount);
		//totalCount
		int totalCount = dao.getSearchTotalCount(condition);
		pageBean.setTotalCount(totalCount);
		//totalPage
		//公式:总页数=Math.ceil(总条数/当前显示的条数)
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		//costList
		//索引index = (当前页数-1)*每页显示的条数
		int index = (currentPage-1)*currentCount;
		List<Cost> costList = dao.searchCostListForPageBean(condition,index,currentCount);
		pageBean.setCostList(costList);
		return pageBean;
	}*/
	public List<Cost> searchCost(Condition condition) throws SQLException {
		CostDao dao = new CostDao();
		return dao.searchCost(condition);
	}
	
	
}
