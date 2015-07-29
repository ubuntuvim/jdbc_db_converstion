package test.com.ubuntuvim.utils;


import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ubuntuvim.utils.DBConnUtils;

public class DBConnUtilsTest {

	private static DBConnUtils dbConnUtil = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dbConnUtil = new DBConnUtils();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dbConnUtil = null;
	}

	@Test
	public void testGetConntion() {
		assertNotNull("对象实例化失败了！！", dbConnUtil);
		assertNotNull("链接数据库失败了！！", DBConnUtils.getConnection());
	}

}
