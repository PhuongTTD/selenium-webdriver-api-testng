package javaForTest;

public class Topic02_Get_Project_Path {

	public static void main(String[] args) {
		String project_location = System.getProperty("user.dir");
		System.out.print(project_location);
	}

}
