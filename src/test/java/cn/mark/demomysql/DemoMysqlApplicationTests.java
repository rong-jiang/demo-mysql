package cn.mark.demomysql;

import cn.mark.demomysql.model.Book;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemoMysqlApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/t312?characterEncoding=UTF-8 && serverTimezone=GMT";
		String user = "root";
		String password = "123456";
		try(Connection con= DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
		) {
			String sql="select * from t_book";
			// 执行查询语句，并把结果集返回给ResultSet
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");// 可以使用字段名
				String name = rs.getString(2);// 也可以使用字段的顺序
				String hp = rs.getString("set");
				System.out.format("id=%d,name=%s,hp=%f,damage=%d %n", id, name,hp);
			}
		} catch (SQLException e) {
			System.out.println("数据库操作出错");
			e.printStackTrace();
		}

	}

	@Test
    void testPost() {
        Book book = new Book();
        book.setName("张三");
        book.setAge(10);
        book.setSetb("nan");
	    
        System.out.println("第一个集合数据："+book);
	    
        Book book1 = new Book();
        book1.setName("李四");
        book1.setAge(5);
        book1.setSetb("nan");
	
		System.out.println("第二个集合数据："+book1);

        List<Book> list = new ArrayList();
        list.add(book);
        list.add(book1);

        Map<String, Book> map = new HashMap<>();
//        map.put("data", list);

		list.forEach(lists->{
			System.out.println(lists);
			map.put("data", lists);

		});

		System.out.println("输出map数据:"+map);


    }


}
