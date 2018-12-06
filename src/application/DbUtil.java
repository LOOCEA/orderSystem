package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DbUtil {

	// ���ݿ����ӵ�ַ
	public static String URL = "jdbc:mysql://127.0.0.1:3306/ordersystemby(yyandlzh)?useSSL=false&serverTimezone=GMT";
	// �û���
	public static String USERNAME = "root";
	// ����
	public static String PASSWORD = "123456";
	// mysql��������
	public static String DRIVER = "com.mysql.cj.jdbc.Driver";

	private static ResourceBundle rb = ResourceBundle.getBundle("com.util.db.db-config");

	// ʹ�þ�̬�������������
	static {
		URL = rb.getString("jdbc.url");
		USERNAME = rb.getString("jdbc.username");
		PASSWORD = rb.getString("jdbc.password");
		DRIVER = rb.getString("jdbc.driver");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// ����һ����ȡ���ݿ����ӵķ���
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡ����ʧ��");
		}
		return conn;
	}

	// �ر����ݿ�����
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (stat != null)
				stat.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DbUtil db = new DbUtil();

	}

}