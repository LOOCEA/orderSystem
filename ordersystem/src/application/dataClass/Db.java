package application.dataClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/*
 * public Object query(Connection conn, String sql, Object[] params, ResultSetHandler rsh) throws SQLException��ִ��һ����ѯ�������������ѯ�У����������е�ÿ��Ԫ��ֵ��������Ϊ��ѯ�����û��������÷��������д��� PreparedStatement �� ResultSet �Ĵ����͹رա�
����public Object query(String sql, Object[] params, ResultSetHandler rsh) throws SQLException:���������һ�ַ���һ����Ψһ�Ĳ�ͬ�������������ݿ������ṩ���������������Ǵ��ṩ�����췽��������Դ(DataSource) ��ʹ�õ�setDataSource ���������»�� Connection��
����public Object query(Connection conn, String sql, ResultSetHandler rsh) throws SQLException : ִ��һ������Ҫ�û������Ĳ�ѯ������
����public int update(Connection conn, String sql, Object[] params) throws SQLException:����ִ��һ�����£����롢���»�ɾ����������
����public int update(Connection conn, String sql) throws SQLException������ִ��һ������Ҫ�û������ĸ��²�����
 * 
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

	public static String url = "jdbc:mysql://127.0.0.1:3306/sys?useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true ";
	public static String user = "root";
	public static String passwd = "123456";
	public static String driver = "com.mysql.cj.jdbc.Driver";

	public Db() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ʹ�þ�̬�������������
	// ����һ����ȡ���ݿ����ӵķ���
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
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
		Db db = new Db();
		java.util.List<Object[]> list = qr.query(db.getConnection(), sql, new ArrayListHandler());
		for (int i = 0; i < list.size(); i++) {
			Object[] ob = list.get(i);
			for (int j = 0; j < ob.length; j++) {
				System.out.println(ob[j]);
			}
		}
		System.out.println(list);

	}

}