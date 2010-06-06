/**
* Testing out a MySQL database connection
*
* @author 	Joshua Kehn
* https://gist.github.com/e3016d06afc3651dd966
* @date		06/06/2010
*/

//import com.mysql.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SqlTest
{
	public static void main(String [] args) throws Exception
	{
		Class.forName( "com.mysql.jdbc.Driver" ); // do this in init
		// // edit the jdbc url 
		String url = "jdbc:mysql://localhost:3306/projects";
		// Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/projects","root","123");
		Connection conn = DriverManager.getConnection(url, "root", "123");
		
		System.out.println("Connected, attempting query.");
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery( "select "+
			"count(`users`.`fav_color`) AS color_count,"+
			"`users`.`fav_color`,"+
			"`users`.`firstname`"+
		" from "+ 
			"`users` "+ 
		" where "+
			"`users`.`eye_color` = `users`.`fav_color` AND "+
			"`users`.`firstname` = 'Joshua' "+
		"group by "+
			"`users`.`fav_color` "+
		"order by "+
			"`color_count`;" );
		
		if(rs.first())
		{
			System.out.println("Query successful, listing.");

			do
			{
				System.out.print(rs.getString("color_count"));
				System.out.print("\t");
				System.out.print(rs.getString("fav_color"));
				System.out.print("\t");
				System.out.print(rs.getString("firstname"));
				System.out.println();
			}while(rs.next());
		}
		else
		{
			System.out.println("No rows found.");
		}
		
	}
}