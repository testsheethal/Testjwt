

public class Constant {
	//public static String ProjectID  = null;
	public static String ExecutionID;
	public static String URL;
	public static String ProjectID;
	public static String IssueID;
	public static String ApiMethod;
	
	public static String getApiMethod(){
		return ApiMethod;	
	}
	
	public static void setApiMethod(String apiMethod) {
		ApiMethod = apiMethod;
	}
	public static String getIssueID(){
		return IssueID;	
	}
	
	public static void setIssueID(String issueID) {
		IssueID = issueID;
	}
	public static String getProjectID() {
		return ProjectID;
	}
	public static void setProjectID(String projectID) {
		ProjectID = projectID;
	}
	public static String getExecutionID() {
		return ExecutionID;
	}
public static void setExecutionID(String executionID) {
		ExecutionID = executionID;
		
		
	}
	public static String getURL(String URL) {
		return URL;
	}
	public static String setURL(String uRL) {
		URL = uRL;
		return URL;
	}
	

}
