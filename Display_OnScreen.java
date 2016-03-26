import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Display_OnScreen {

	public void display(DBCursor cursor){
		try {
			while(cursor.hasNext()){
				DBObject ob = cursor.next();
				System.out.println(ob);
			}
		} finally {
			cursor.close();
		}
	}
}
