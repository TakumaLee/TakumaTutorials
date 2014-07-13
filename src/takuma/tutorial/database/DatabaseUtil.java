package takuma.tutorial.database;

import java.sql.SQLException;

import android.util.Log;

public class DatabaseUtil {
    private DatabaseUtil() {
    	
    }
    
    public static void throwAndroidSQLException(String tag, SQLException e) {
    	if (e != null) {
    		String error = "" + e.getMessage();
            Log.e(tag, error, e);
            throw new android.database.SQLException(error);
    	} 
    }
}