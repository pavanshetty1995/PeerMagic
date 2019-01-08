/*package com.peermagic.conversion;
import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Attachment;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.enterprise.sobject.Tally_CSV__c;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class UploadTally {
static final String USERNAME = "shruti@peermagic.com.dev1";
static final String PASSWORD = "salesforce123";
  static EnterpriseConnection connection;
  
  
  public static void uploadFile(String fileName) {

	  System.out.println("Upload File.......");
    ConnectorConfig config = new ConnectorConfig();
    config.setUsername(USERNAME);
    config.setPassword(PASSWORD);

    //config.setTraceMessage(true);

    try {
      connection = Connector.newConnection(config);
      // display some current settings
      System.out.println("Auth EndPoint: "+config.getAuthEndpoint());
      System.out.println("Service EndPoint: "+config.getServiceEndpoint());
      System.out.println("Username: "+config.getUsername());
      System.out.println("SessionId: "+config.getSessionId());
     
        createAttachment(fileName);

    } catch (ConnectionException e1) {
        e1.printStackTrace();
    }  

  }
  public static void createAttachment(String filename){
	  try {
	 System.out.println("Attach csv file");
        File f = new File(filename);
        InputStream is = new FileInputStream(f);
        byte[] inbuff = new byte[(int)f.length()];        
        is.read(inbuff);

        Attachment attach = new Attachment();

        Tally_CSV__c tallyCsv= new Tally_CSV__c();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    	LocalDate localDate = LocalDate.now();
    	String recordName= dtf.format(localDate);
        tallyCsv.setName(recordName);
        Calendar c = Calendar.getInstance();
        tallyCsv.setCreated_Date__c(c);
        
        attach.setBody(inbuff);
        attach.setName("Tally.csv");
        attach.setIsPrivate(false);
        
        try {
        	
        	//SaveResult[] saveResults=connection.create(csv);
        	SaveResult[] saveResults = connection.create(new SObject[] { tallyCsv });
			System.out.println("Results......:"+saveResults.length);
			 for (int i = 0; i < saveResults.length; i++) {
				 String[] result = new String[1];
				if (saveResults[i].isSuccess()) {
		            System.out.println("Successfully created tally ID: "
		                  + saveResults[i].getId());
		            result[i] = saveResults[i].getId();
		            attach.setParentId(result[i]);
		            SaveResult[] saveFile = connection.create(new SObject[] { attach });
		         } else {
		            System.out.println("Error: could not create tally "
		                  + "for array element " + i + ".");
		            System.out.println("   The error reported was: "
		                  + saveResults[i].getErrors()[0].getMessage() + "\n");
		            result[i] = saveResults[i].getId();
		         }
		      }
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    } catch (FileNotFoundException fnf) {
        System.out.println("File Not Found: " +fnf.getMessage());

    } catch (IOException io) {
        System.out.println("IO: " +io.getMessage());            
    }
  }
}
*/