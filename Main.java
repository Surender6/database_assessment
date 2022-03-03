import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Main{
	private static String databasePath = "jdbc:sqlite:MoviesDatabase.db";

	public static void main(String[] args){
		Connection connect = null;

		Picture Bheemlanayak = new Picture("Bheemlanayak","Pawan Kalyan"," Saagar K Chandra",2022);
    Picture vakeelsaab = new Picture("vakeelsaab","Pawan kalyan","venu sriram",2021);
    Picture khushi = new Picture("khushi"," Pawan kalyan"," sj surya",2001);
    Picture agnathavasi = new Picture("agnathavasi ","Pawan kalyan ","trivikram",2018);
		
		try {
			connect = DriverManager.getConnection(databasePath);
			Statement statement = connect.createStatement();
			statement.setQueryTimeout(30);

			statement.executeUpdate("drop table if exists Movies");
			statement.executeUpdate("create table Movies (name string, actor string, director string,year integer)");
			
			SqHandler.insertPicture(statement,Bheemlanayak);
			SqHandler.insertPicture(statement,vakeelsaab);
			SqHandler.insertPicture(statement,khushi);
			SqHandler.insertPicture(statement,agnathavasi);

			ResultSet resultset = SqHandler.getMovies(statement);
      System.out.println("");
			System.out.println("PictureName	|	PictureActor	|	PictureDirector	|	PictureYear	");
			System.out.println("=======================================================================");
			if(resultset != null){
				while(resultset.next()){
					Picture picture = SqHandler.fatchMovie(resultset);
					picture.display();
				}
			}
			
		} catch (SQLException e) {
			System.err.println("error : " + e.getMessage());
		}
	}

	
}