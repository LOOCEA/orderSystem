package application;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class ConnUtil {
	// ����������ݿ���Ҫ�Ķ���
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://127.0.0.1:3306/ordersystemby(yyandlzh)?useSSL=false&serverTimezone=GMT";
	String user = "root";
	String passwd = "123456";
	String driver = "com.mysql.cj.jdbc.Driver";

	// �ر����ݿ���Դ
	public void close() {
		// �ر�
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (ct != null)
				ct.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// д��һ������Ҫע��ķ���
	public ResultSet queryExecute(String sql) {
		try {
			// 1.��������
			Class.forName(driver);
			// 2.�õ�����
			ct = DriverManager.getConnection(url, user, passwd);
			// 3.����ps
			ps = ct.prepareStatement(sql);

			rs = ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ

		}
		return rs;
	}

	// ģ����ѯ���ݿ�Ĳ���
	public ResultSet queryLikeExecute(String sql, String[] paras) {
		try {
			// 1.��������
			Class.forName(driver);
			// 2.�õ�����
			ct = DriverManager.getConnection(url, user, passwd);
			// 3.����ps
			ps = ct.prepareStatement(sql);
			// ��ps�ģ���ֵ
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, "%" + paras[i] + "%");
			}

			rs = ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ

		}
		return rs;
	}

	// ��ѯ���ݿ�Ĳ���
	public ResultSet queryExecute(String sql, String[] paras) {
		try {
			// 1.��������
			Class.forName(driver);
			// 2.�õ�����
			ct = DriverManager.getConnection(url, user, passwd);
			// 3.����ps
			ps = ct.prepareStatement(sql);
			// ��ps�ģ���ֵ
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}

			rs = ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ

		}
		return rs;
	}

	// ����ɾ�ĺ���һ��
	public boolean updExecute(String sql, String[] paras) {
		boolean b = true;

		try {
			// 1.��������
			Class.forName(driver);
			// 2.�õ�����
			ct = DriverManager.getConnection(url, user, passwd);
			// 3.����ps
			ps = ct.prepareStatement(sql);
			// ��ps�ģ���ֵ
			for (int i = 0; i < paras.length; i++) {
				ps.setString(i + 1, paras[i]);
			}
			// 4.ִ�в���
			if (ps.executeUpdate() != 1) {
				b = false;
			}

		} catch (Exception e) {
			b = false;
			e.printStackTrace();
		} finally {
			this.close();
		}
		return b;
	}

	public static void main(String[] args) throws UnsupportedEncodingException, SQLException {
		ConnUtil conn = new ConnUtil();
		String sql = "select ? from ? where username=?";
		String[] p = new String[3];
		p[0] = "username";
		p[1] = "username";
		p[2] = "xhy";
		ResultSet rs = conn.queryExecute(sql, p);
		rs.next();
		System.out.println(rs.getString(1));
	}
}