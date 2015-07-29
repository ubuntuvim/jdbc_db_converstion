# jdbc_db_converstion
使用注解和反射实现通用性的 jdbc操作数据库之最简单的 jdbc 操作
JDBC是什么?
JDBC API是一个Java API，可以访问任何类型表列数据，特别是存储在关系数据库中的数据。JDBC代表Java数据库连接。
JDBC通常的使用步骤有7步：
•	加载JDBC驱动程序；
•	使用数据库的用户名和密码以及 URL 获取链接数据的 connection
•	创建一个Statement或者是PreparedStatement（建议使用，放 SQL 注入）
•	执行 SQL
•	处理结果
•	关闭数据库链接释放资源
