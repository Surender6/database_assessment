import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqHandler{
	public static void insertPicture(Statement statement,Picture movie){
		String insert = "INSERT INTO Movies" + " values ('" + movie.getName() + "','" + movie.getActor()
				+ "','" + movie.getDirector() + "'," + movie.getYear() + ")";
		try{
			statement.executeUpdate(insert);
		}catch (SQLException e) {
			System.err.println("error : " + e.getMessage());
		}
	}

	public static ResultSet getMovies(Statement statement){
		try{
			return statement.executeQuery("Select * from Movies");
		}catch (SQLException e) {
			System.err.println("error : " + e.getMessage());
		}
		return null;
	}

	public static Picture fatchMovie(ResultSet resultset){
		Picture picture;
		try{
			String name = resultset.getString("name");
			String actor = resultset.getString("actor");
			String director = resultset.getString("director");
			int year = resultset.getInt("year");
			picture = new Picture(name,actor,director,year);
			return picture;
		}catch (SQLException e) {
			System.err.println("error : " + e.getMessage());
		}
		return null;
	}
	
}