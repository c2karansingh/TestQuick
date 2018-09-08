package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Database;

public class LoginDao 
{
	private final int NO_SUCH_USER = -1; //
	private final int WRONG_PASSWORD = 0; //
	private final int USER_VALIDATED = 1;//
	
	public int returnError(String username,String password)
	{
		Connection connect = Database.getConnection();
		try 
		{
			Statement getUser = connect.createStatement();
			ResultSet user = getUser.executeQuery("Select username from user_pass where username='"+username+"';");
			if(!user.first())
			{
				
				return NO_SUCH_USER;
			}
	
			
			Statement getPassword = connect.createStatement();
			ResultSet truePassword = getPassword.executeQuery("Select 1 from user_pass where username='"+user.getString(1)+"' and password='"+password+"';");
			if(!truePassword.first())
			{
				
				return WRONG_PASSWORD;
			}
			return USER_VALIDATED;
		
		
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return NO_SUCH_USER;

	}

	
	
}
