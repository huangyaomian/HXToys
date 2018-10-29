package cn.hx.appium.sqlutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSetMetaData;

public class ExecutionMethod {

	/**
	 * 构造方法 //单表查询
	 * 
	 * @param String
	 *            mysql //数据库执行语句
	 * @param Object
	 *            contrast //查找的对象
	 * @throws Exception
	 */
	public boolean isInSql(String mysql, Object contrast) {
		boolean flag = false;
		Connection conn = BaseConnection.getConnection();// 获取数据库连接
		// sql执行器对象
		PreparedStatement ps = null;
		// 结果集对象
		ResultSet rs = null;// 查询出来的数据先放到rs中
		try {
			// String sql="select * from emp";
			String sql = mysql;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// 执行数据库查询的方法，放到rs中
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while (rs.next()) {// rs对象相当于一个指针，指向数据库的一横行数据
				List list = new ArrayList();
				for (int j = 1; j < rsmd.getColumnCount()+1; j++) {
					list.add(rs.getObject(j));
				}
				flag = false;
				System.out.println(list);
				if (list.contains(contrast)) {
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 重点下面代码必须写，当数据库使用后必须关闭，如果没有关闭数据库的接口有限，下次就不能连接
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}

	// 该方法负责将传递过来的SetData对象中的数据存入到数据库中
	public void insertSql(String sql) {
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);// 把写好的sql语句传递到数据库，让数据库知道我们要干什么
			int a = ps.executeUpdate();// 这个方法用于改变数据库数据，a代表改变数据库的条数
			if (a > 0) {
				System.out.println("添加成功");
			} else {
				System.out.println("添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void deleteSql(String sql) {// 删除数据库中的数据
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			int a = ps.executeUpdate();
			if (a > 0) {
				System.out.println("删除成功");
			} else {
				System.out.println("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void updateSql(String sql) {
		Connection conn = BaseConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			int a = ps.executeUpdate();
			if (a > 0) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ExecutionMethod executionMethod = new ExecutionMethod();
		boolean ar = executionMethod.isInSql("select empno from emp", null);
//		String bbString = "insert into emp (empno,ename,job) values (123456,\"小明\",\"打字\")";
//		executionMethod.insertSql(bbString); 
//		executionMethod.delete("delete from emp where empno=123");
		executionMethod.updateSql("update emp set empno=5555 where job=\"员工股\"");


	}

}
