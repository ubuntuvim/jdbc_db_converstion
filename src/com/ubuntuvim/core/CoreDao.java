package com.ubuntuvim.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import org.junit.Test;

import com.ubuntuvim.utils.DBConnUtils;

/**
 * 使用要求实体类（model）中的属性名一定要和数据库中的字段名一定要严格相同（包括大小写）,
 * 
 * @author ubuntuvim
 * @email 1527254027@qq.com
 * @datatime 2015-7-23 下午11:21:14
 * @param <T>
 */
public class CoreDao {

	// 定义数据库的链接
	private Connection conn;
	// 定义sql语句的执行对象
	private PreparedStatement pstmt;
	// 定义查询返回的结果集合
	private ResultSet resultSet;
	
	private int commId = 35;

	
	//  新增数据
	@Test
	public void add() {
		conn = DBConnUtils.getConnection();
		String sql = " insert into user(id, username, birth) values(?, ?, ?)";
		try {
			//  预编译 SQL 语句
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commId);  //为了方便测试直接指定了 id，实际使用中通常不需要指定 id，默认设置为自增类型即可
			pstmt.setString(2, "新增测试");  //1表示 SQL 语句中的第一个问号
			pstmt.setDate(3, new Date(System.currentTimeMillis()));
			//  执行SQL
			int i = pstmt.executeUpdate();
			// 提交事务
			conn.commit();
			if (i > 0)
				System.out.println(" 新增成功...");
		} catch (SQLException e) {
			e.printStackTrace();
			// 出错回滚事务
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {  //关闭数据库连接释放资源
			try {
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != pstmt) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//  测试查询
	public void list() {
		
		conn = DBConnUtils.getConnection();
		String sql = "select * from user";
		try {
			//  预编译 SQL 语句
			pstmt = conn.prepareStatement(sql);
			//  执行查询
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				
				System.out.println("id = " + resultSet.getInt("id") + ", username = " + resultSet.getString("username") 
						+ ", birth = " + resultSet.getDate("birth") 
						+ ", detail_time = " + resultSet.getObject("detail_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {  //关闭数据库连接释放资源
			try {
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != pstmt) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//  测试更新
	public void update() {
		conn = DBConnUtils.getConnection();
		String sql = " update user set username = ? where id = ?";
		try {
			//  预编译 SQL 语句
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "update_test"); //
			pstmt.setInt(2, commId);  //
			//  执行更新
			int i = pstmt.executeUpdate();
			if (i > 0)
				System.out.println(" 更新成功...");
			
			//记得要提交
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//  如果更新出错，执行回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {  //关闭数据库连接释放资源
			try {
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != pstmt) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//  测试删除，删除的代码与更新的代码基本是一样的，
	//  只是 SQL 的不同而已，所以可以把更新和删除合并为一个方法，
	//  会在后续的文章实现合并
	public void del() {
		conn = DBConnUtils.getConnection();
		String sql = "  delete from user where id = ?";
		try {
			//  预编译 SQL 语句
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commId);  //删除 id 为35的数据
			//  执行更新
			int i = pstmt.executeUpdate();
			if (i > 0)
				System.out.println(" 删除成功...");
			
			//记得要提交
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//  如果更新出错，执行回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {  //关闭数据库连接释放资源
			try {
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != pstmt) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
	
	public static void main(String[] args) {
		new CoreDao().list();
		new CoreDao().add();
		new CoreDao().list();
		new CoreDao().update();
		new CoreDao().list();
		new CoreDao().del();
		new CoreDao().list();
	}
}
