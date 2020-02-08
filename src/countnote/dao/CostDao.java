package countnote.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import countnote.domain.Condition;
import countnote.domain.Cost;
import countnote.utils.DataSourceUtils;
import net.sf.json.util.NewBeanInstanceStrategy;

public class CostDao {
	//从数据库取出所有数据
	public List<Cost> showAllCost() throws SQLException {
	
		//操作数据库
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from costdata";
		List<Cost> costList = runner.query(sql,new BeanListHandler<Cost>(Cost.class));
		return costList;
	}
	//向数据库添加数据
	public int addCost(Cost cost) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into costdata values (?,?,?,?)";
		int cflag = runner.update(sql,cost.getCid(),cost.getCdate(),cost.getCtype(),cost.getCcost());
		return cflag;
	}
	//从数据库删除数据
	public int delCost(String cid) throws SQLException {
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from costdata where cid = ?";
		return runner.update(sql,cid);
	}
	//更新数据
	public int updateCost(Cost cost) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="update costdata set cdate=?,ctype=?,ccost=? where cid =?";
		int uFlag = runner.update(sql,cost.getCdate(),cost.getCtype(),cost.getCcost(),cost.getCid());
		return uFlag;
	}
	//计算总和
	public Double countCostSum(String date1, String date2) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select sum(ccost) from costdata where cdate>=? and cdate<=?";
		return (Double) runner.query(sql, new ScalarHandler(1),date1,date2);
	}
	//求出某时间段内的数据
	public List<Cost> countCostArray(String date1, String date2) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from costdata where cdate>=? and cdate<=?";
		List<Cost> countCostList = runner.query(sql, new BeanListHandler<Cost>(Cost.class), date1,date2);
		return countCostList;
	}
	//条件查询
	public List<Cost> searchCost(Condition condition) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String select = condition.getSelect();
		String selectVal = condition.getSelectVal();
		List<Cost> costList = null;
		List<String> list = new ArrayList<String>();
		String sql = "select * from costdata where 1=1";
		if(select!=null){
			if(select.equals("消费种类")){
				sql+=" and ctype like ?";
				list.add("%"+selectVal+"%");
			}
			if(select.equals("消费时间")) {
				sql+=" and cdate like ?";
				list.add("%"+selectVal+"%");
			}
		}
		costList = runner.query(sql, new BeanListHandler<Cost>(Cost.class),list.toArray());
		return costList;
	}
	//获取条件查询后的总金额
	public Double searchCostSum(Condition condition) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String select = condition.getSelect();
		String selectVal = condition.getSelectVal();
		String sql = "select sum(ccost) from costdata where 1=1";
		List<String> list = new ArrayList<String>();
		Double sum = 0.0;
		if(select!=null){
			if(select.equals("消费种类")){
				sql+=" and ctype like ?";
				list.add("%"+selectVal+"%");
			}
			if(select.equals("消费时间")) {
				sql+=" and cdate like ?";
				list.add("%"+selectVal+"%");
			}
		}
		sum= (Double) runner.query(sql, new ScalarHandler(1),list.toArray());
		return sum;
	}
	//获得全部消费金额
	public Double sumCost() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select sum(ccost) from costdata";
		return (Double) runner.query(sql, new ScalarHandler(1));
	}
	//获得全部的商品的条数
	public int getTotalCount() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select count(*) from costdata";
		Long query =(Long) runner.query(sql, new ScalarHandler(1));
		return query.intValue();
	}
	//获得分页的商品数据
	public List<Cost> findCostListForPageBean(int index,int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from costdata limit ?,?";
		return runner.query(sql, new BeanListHandler<Cost>(Cost.class),index,currentCount);
	}
	//获得查询后的分页商品数据
	/*public List<Cost> searchCostListForPageBean(Condition condition, int index, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String select = condition.getSelect();
		String selectVal = condition.getSelectVal();
		List<Cost> costList = null;
		List<String> list = new ArrayList<String>();
		 StringBuffer sql = new StringBuffer("select * from costdata where 1=1");
		if(select!=null){
			if(select.equals("消费种类")){
				sql.append(" and ctype like ?");
				list.add("'%"+selectVal+"%'");
			}
			if(select.equals("消费时间")) {
				sql.append(" and cdate like ?");
				list.add("'%"+selectVal+"%");
			}
		}
		sql.append(" limit ?,?");
		System.out.println(sql);
		costList = runner.query(sql.toString(), new BeanListHandler<Cost>(Cost.class),list.toArray(),index,currentCount);
		return costList;
	}*/
	//取得查询后结果的总页数
	public int getSearchTotalCount(Condition condition) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select count(*) from costdata where 1=1";
		String select = condition.getSelect();
		String selectVal = condition.getSelectVal();
		List<String> list = new ArrayList<String>();
		if(select!=null){
			if(select.equals("消费种类")){
				sql+=" and ctype like ?";
				list.add("%"+selectVal+"%");
			}
			if(select.equals("消费时间")) {
				sql+=" and cdate like ?";
				list.add("%"+selectVal+"%");
			}
		}
		Long query =(Long) runner.query(sql, new ScalarHandler(1),list.toArray());
		return query.intValue();
	}

}
