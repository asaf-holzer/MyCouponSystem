import com.asafh.creatTables.DBManeger;

public class CloseTables {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("start closing");
		 DBManeger.dropAllTables();	
		
		 com.asafh.utils.ConnectionPool.getInstance().closeAllConnection();
		System.out.println("end closing");
		
	}

}
