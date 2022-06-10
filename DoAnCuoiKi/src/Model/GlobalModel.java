package Model;

public class GlobalModel {
	public static int globalTypeUser = - 1;
	public static String globalIDUser = "";
	
	public GlobalModel() {
	}
	
	public static void setglobalTypeUser(String type, String id) {
		if(type.equals("Quản Lí")) {
			globalTypeUser = 1;
			globalIDUser = id;
		}
		else if(type.equals("Thu Ngân")){
			globalTypeUser = 0;
			globalIDUser = id;
		}
	}
}
