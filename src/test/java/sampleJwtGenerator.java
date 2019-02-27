

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;



/**
* @author kumari.meena 27-Feb-2019
*
*/

            /**
                * @param args
                * @author Created by kumari.meena 27-Feb-2019
                * @throws URISyntaxException
                * @throws JobProgressException
                * @throws JSONException
                * @throws IOException
                * @throws IllegalStateException
                 */

             
             class OutputParam
             {
            	 String FinalAPIURL;
            	 String JWTToken;
            	 OutputParam(String FA, String JT)
            	 {
            		 FinalAPIURL = FA;
            		 JWTToken = JT;
            	 }
             }
             public class sampleJwtGenerator
             {
            	 public static String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";// Replace Zephyr BaseUrl with the <ZAPI_CLOUD_URL> shared with ZAPI Cloud Installation
                 public static String accessKey = "NzM2YjQyOWItOGRjYi0zNjVkLWJlMzYtODM4MjE2YTQ1ODBmIHNoZWV0aGFsLmQxMSBVU0VSX0RFRkFVTFRfTkFNRQ";// zephyr accessKey , we can get from Addons >> zapi section
                 public static String secretKey = "swVWLyRRIo8ui37gwAifW6dWeWmoR6U_MyRup_r_79Q";// zephyr secretKey , we can get from Addons >> zapi section
                 public static String userName = "sheethal.d11@wipro.com"; // Jira UserName
                 public static String jwt;
                 public static String FinalAPI;
                 static URI uri = null;
            	 static OutputParam getOutputParam(String MethodName, String URLId, String ProjectID, String IssueId, String ExecutionID) throws URISyntaxException
            	 {
            		 ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName).build();
                     JwtGenerator jwtGenerator = client.getJwtGenerator();
                     // API to which the JWT token has to be generated
    
                     if (MethodName.equals("GET")) 
                     {
                  	   String createCycleUri = zephyrBaseUrl + URLId +"issueId=" +IssueId + "&projectId=" + ProjectID;
                  	   System.out.println("cycleuri : " +createCycleUri);
                  	   URI uri = new URI(createCycleUri);
                  	   int expirationInSec = 15811200;
                  	   System.out.println("uri : " +uri);
                  	   jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);
                  	   System.out.println("jwt : " +jwt);
                  	   FinalAPI = uri.toString();
                     }

                     else if (MethodName.equals("PUT"))
                     {
                  	   String createCycleUri = zephyrBaseUrl + URLId + ExecutionID ;
                  	   System.out.println("cycleuri : " +createCycleUri);
                  	   URI uri = new URI(createCycleUri);
                  	   int expirationInSec = 15811200;
                  	   System.out.println("uri" +uri);
                  	   jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec); 
                  	   System.out.println("jwt : " +jwt);
                  	   FinalAPI = uri.toString();
                     }
                     
              	   	 //System.out.println("FINAL API : " +FinalAPI);
                	 String delims = "[ ]+";
                	 String[] tokens = jwt.split(delims);
                	 String jwtToken = tokens[1];
                	 //System.out.println("Token : " +jwtToken);
                     return new OutputParam(FinalAPI,jwtToken);
            	 }
             
             public static void main(String[] args) throws URISyntaxException, IllegalStateException, IOException
             {
            	 ArrayList<String> alist=new ArrayList<String>();

                 // Newly Added 

                 System.out.println("Program Arguments:");
                 for (String arg : args) 
                 {
              	   System.out.println("\t" + arg);
              	   alist.add(arg);
                 }  
                 Constant.setApiMethod(alist.get(0));
                 Constant.setURL(alist.get(1));
                 Constant.setProjectID(alist.get(2));
                 Constant.setIssueID(alist.get(3));
                 Constant.setExecutionID(alist.get(4));
                 String MethodName = Constant.ApiMethod;
                 String URLId = Constant.URL;
                 String ProjectID = Constant.ProjectID;
                 String IssueId = Constant.IssueID;
                 String ExecutionID = Constant.ExecutionID;
            	   
            	 OutputParam Rst = getOutputParam(MethodName,URLId,ProjectID,IssueId,ExecutionID);
            	 Constant.setIssueID("");
            	 Constant.setProjectID("");
            	 Constant.setExecutionID("");
            	 
            	 System.out.println("FINAL API : " +Rst.FinalAPIURL);
            	 System.out.println("Token : " +Rst.JWTToken);
              }
             }


