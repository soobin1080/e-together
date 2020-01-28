import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MariaDBTransaction1 {

//	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/together?useUniCode=yes&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";

	static final String USERNAME = "together";
	static final String PASSWORD = "together1!";

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatementInsert = null;
		PreparedStatement preparedStatementUpdate = null;

		String insertTableSQL = "INSERT IGNORE INTO product_test" + "(pro_name,price,img) VALUES" + "(?,?,?)";

		try {
//			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("MariaDB 연결.");

			connection.setAutoCommit(false);
			Document doc = null;

			for (int i = 1; i <= 15; i++) {

				String url = "http://emart.ssg.com/category/listCategoryItem.ssg?dispCtgId=0006110153&page="
						+ Integer.toString(i);
				try {
					doc = Jsoup.connect(url).get();
				} catch (IOException e) {
					e.printStackTrace();
				}

				Elements element = doc.select("div.tmpl_itemlist");
				Iterator<Element> ie1 = element.select("em.tx_ko").iterator();
				Iterator<Element> ie2 = element.select("em.ssg_price").iterator();
				Iterator<Element> ie3 = element.select("img").iterator();
				preparedStatementInsert = connection.prepareStatement(insertTableSQL);

				while (ie2.hasNext()) {
					String pro_name = ie1.next().text();
					String price = ie2.next().text();
					String img = ie3.next().attr("src").toString();
					System.out.println(pro_name + " " + price + " " + img + "\t");
					preparedStatementInsert.setString(1, pro_name);
					preparedStatementInsert.setString(2, price);
					preparedStatementInsert.setString(3, img);

					preparedStatementInsert.executeUpdate();
//				preparedStatementInsert.clearParameters();
				}

				// preparedStatementInsert = connection.prepareStatement(insertTableSQL);

				// preparedStatementUpdate.executeUpdate();
				connection.commit();
				System.out.println("트랜잭션 정상처리");

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
//			connection.rollback();
		} finally {
			if (preparedStatementInsert != null)
				preparedStatementInsert.close();
			if (preparedStatementUpdate != null)
				preparedStatementUpdate.close();
			if (connection != null)
				connection.close();
		}
		System.out.println("MariaDB 연결종료.");
	}

}