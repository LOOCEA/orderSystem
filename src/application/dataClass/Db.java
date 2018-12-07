package application.dataClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/*
 * ��ArrayHandler��     ����ѯ����ĵ�һ�����ݣ����浽Object������
      ��ArrayListHandler     ����ѯ�Ľ����ÿһ���ȷ�װ��Object�����У�Ȼ�����ݴ���List����
      ��BeanHandler     ����ѯ����ĵ�һ�����ݣ���װ��user����
     ��BeanListHandler     ����ѯ�����ÿһ�з�װ��user����Ȼ���ٴ���List����
     ��ColumnListHandler     ����ѯ�����ָ���е����ݷ�װ��List������
     ��MapHandler     ����ѯ����ĵ�һ�����ݷ�װ��map��ϣ�key==������value==��ֵ��
     ��MapListHandler     ����ѯ�����ÿһ�з�װ��map���ϣ�key==������value==��ֵ�����ٽ�map���ϴ���List����
     ��BeanMapHandler     ����ѯ�����ÿһ�����ݣ���װ��User�����ٴ���mao�����У�key==������value==��ֵ��
     ��KeyedHandler     ����ѯ�Ľ����ÿһ�����ݣ���װ��map1��key==������value==��ֵ ����Ȼ��map1���ϣ��ж��������map2���ϣ�ֻ��һ����
     ��ScalarHandler     ��װ����count��avg��max��min��sum......������ִ�н��
 * */
public class Db {

	// ���ݿ����ӵ�ַ
	public static String URL;
	// �û���
	public static String USERNAME;
	// ����
	public static String PASSWORD;
	// mysql��������
	public static String DRIVER;

	private static ResourceBundle rb = ResourceBundle.getBundle("db-config");

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

	public static void main(String[] args) throws SQLException {
		String sql = "select * from customer";
		QueryRunner qr = new QueryRunner();

		java.util.List<Object[]> list = qr.query(Db.getConnection(), sql, new ArrayListHandler());
		for (int i = 0; i < list.size(); i++) {
			Object[] ob = list.get(i);
			for (int j = 0; j < ob.length; j++) {
				System.out.println(ob[j]);
			}
		}
		System.out.println(list);

	}

}