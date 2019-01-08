package com.peermagic.conversion;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.peermagic.xmlreader.XmlToCsvConvertor;

@Controller
@EnableScheduling
public class Sample {

	 @RequestMapping(value="/", method= RequestMethod.GET)
	 public String shcedular() {
		 System.out.println("running scheduling ........");
	      return "index";
	   }
	  
	 @RequestMapping(value="/tally", method= RequestMethod.GET)
	 @Scheduled(cron = "0 32 14 * * ?")
	 public void Convert(){
		 System.out.println("running scheduling 1 ........");
		 //XmlToCsvConvertor convert= new XmlToCsvConvertor();
			String ledgername="LEDGERNAME";
			String ledgerlist="ALLLEDGERENTRIES.LIST";
			String amount= "AMOUNT";
			String tallyMessage = "TALLYMESSAGE";
			String date = "DATE";
			String voucher = "VOUCHER";
			String voucherType = "VOUCHERTYPENAME";
			//String narration="NARRATION";
			String voucherNumber ="VOUCHERNUMBER";
			String voucherKey = "VOUCHERKEY";
			String deemedPositive= "ISDEEMEDPOSITIVE";
			String investmentIdTag = "UDF:VCHLEDGERCODE";
			//String project = "SVCURRENTCOMPANY";
			String investmentListTag = "UDF:VCHLEDGERCODE.LIST";
			String staticVariable = "STATICVARIABLES";
			String currentCompany = "SVCURRENTCOMPANY";
			String masterId = "MASTERID";
			String companyCodeListTag = "UDF:EICOMPANYCODE.LIST";
			String companyCodeTag = "EICOMPANYCODE";
			String companyInvestmentList= "UDF:EIINVESTMENTCODE.LIST";
			String companyInvestmentCode ="UDF:EIINVESTMENTCODE";
			String bankLocationListTag ="BANKALLOCATIONS.LIST";
			String bankTransactionTag = "TRANSACTIONTYPE";
			String paymentFavouringTag ="PAYMENTFAVOURING";
			String bankPartyNameTag="BANKPARTYNAME";
			String salesTransTypeListTag= "UDF:MAGTRANSACTIONTYPE.LIST";
			String salesTransTypeTag="UDF:MAGTRANSACTIONTYPE";
			System.out.println("hi spring....");
			 Date today = new Date();
				LocalDate loc = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int month = loc.getMonthValue();
				int year = loc.getYear();
				int newDate = loc.getDayOfMonth();
				
				 String f1= null;
				 List <String> listA = new ArrayList();
				 /*for(File f : new File("//122.176.78.238/D/Magic Tally Data").listFiles()){
		             System.out.println("File Name........:"+f.getName());
		             listA.add(f.getName()); 
				 }*/
				 
				   /* String domain="all";
				    String userName="New";
				    String password="1234";
				    String remoteFilePath="//122.176.78.238/D/Magic Tally Data/";
				    
				    UserAuthenticator auth=new StaticUserAuthenticator(domain, userName, password);
				    FileSystemOptions opts=new FileSystemOptions();
				    DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);


				    FileObject fo=VFS.getManager().resolveFile(remoteFilePath,opts);*/


				
		       //String xmlFileName ="//122.176.78.238/D:/Magic Tally Data/Transaction-Demo MAGIC FREEDOM LLC_"+getDay(newDate)+"-"+getDate(month)+"-"+year+"";
		       //String xmlFileName1 ="//122.176.78.238/D:/Magic Tally Data/Transaction-Demo MAGIC FREEDOM LLC_"+getDay(newDate)+"-"+getDate(month)+"-"+year+""; 
		      
		     String xmlFileName1 = "D:/XML files/08_01_2019/Transaction-MAGICRAR LLC_"+getDay(newDate)+"-"+getDate(month)+"-"+year+"";
		     String xmlFileName2 = "D:/XML files/08_01_2019/Transaction-MAGIC QUANT MEZZ LLC_"+getDay(newDate)+"-"+getDate(month)+"-"+year+"";
		     String xmlFileName3 = "D:/XML files/08_01_2019/Transaction-MAGIC QUANT PREF LLC_"+getDay(newDate)+"-"+getDate(month)+"-"+year+"";
		     String xmlFileName4 = "D:/XML files/08_01_2019/Transaction-MAGIC SHERATON LLC_"+getDay(newDate)+"-"+getDate(month)+"-"+year+"";
		     String xmlFileName5 = "D:/XML files/08_01_2019/Transaction-MAGIC SUNWOOD LLC_"+getDay(newDate)+"-"+getDate(month)+"-"+year+"";
		     String xmlFileName6 = "D:/XML files/08_01_2019/Transaction-MAGIC THETARAY LLC_"+getDay(newDate)+"-"+getDate(month)+"-"+year+"";
		     String xmlFileName7 = "D:/XML files/08_01_2019/Transaction-MAGIC UNION LLC_"+getDay(newDate)+"-"+getDate(month)+"-"+year+"";
		
	
		    
		      //System.out.println("Common xml file.........:"+xmlFileName);
			try {
				XmlToCsvConvertor.getAndReadXml(xmlFileName1,ledgername,ledgerlist,amount,date,tallyMessage,voucher,voucherType,deemedPositive,investmentIdTag,voucherKey,staticVariable,currentCompany,masterId,voucherNumber,investmentListTag,companyCodeListTag,companyCodeTag,companyInvestmentList,
						companyInvestmentCode,bankLocationListTag,bankTransactionTag,paymentFavouringTag,bankPartyNameTag,salesTransTypeListTag,salesTransTypeTag);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			
			try {
				XmlToCsvConvertor.getAndReadXml(xmlFileName2,ledgername,ledgerlist,amount,date,tallyMessage,voucher,voucherType,deemedPositive,investmentIdTag,voucherKey,staticVariable,currentCompany,masterId,voucherNumber,investmentListTag,companyCodeListTag,companyCodeTag,companyInvestmentList,
						companyInvestmentCode,bankLocationListTag,bankTransactionTag,paymentFavouringTag,bankPartyNameTag,salesTransTypeListTag,salesTransTypeTag);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			
			try {
				XmlToCsvConvertor.getAndReadXml(xmlFileName3,ledgername,ledgerlist,amount,date,tallyMessage,voucher,voucherType,deemedPositive,investmentIdTag,voucherKey,staticVariable,currentCompany,masterId,voucherNumber,investmentListTag,companyCodeListTag,companyCodeTag,companyInvestmentList,
						companyInvestmentCode,bankLocationListTag,bankTransactionTag,paymentFavouringTag,bankPartyNameTag,salesTransTypeListTag,salesTransTypeTag);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			
			try {
				XmlToCsvConvertor.getAndReadXml(xmlFileName4,ledgername,ledgerlist,amount,date,tallyMessage,voucher,voucherType,deemedPositive,investmentIdTag,voucherKey,staticVariable,currentCompany,masterId,voucherNumber,investmentListTag,companyCodeListTag,companyCodeTag,companyInvestmentList,
						companyInvestmentCode,bankLocationListTag,bankTransactionTag,paymentFavouringTag,bankPartyNameTag,salesTransTypeListTag,salesTransTypeTag);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			
			try {
				XmlToCsvConvertor.getAndReadXml(xmlFileName5,ledgername,ledgerlist,amount,date,tallyMessage,voucher,voucherType,deemedPositive,investmentIdTag,voucherKey,staticVariable,currentCompany,masterId,voucherNumber,investmentListTag,companyCodeListTag,companyCodeTag,companyInvestmentList,
						companyInvestmentCode,bankLocationListTag,bankTransactionTag,paymentFavouringTag,bankPartyNameTag,salesTransTypeListTag,salesTransTypeTag);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			
			try {
				XmlToCsvConvertor.getAndReadXml(xmlFileName6,ledgername,ledgerlist,amount,date,tallyMessage,voucher,voucherType,deemedPositive,investmentIdTag,voucherKey,staticVariable,currentCompany,masterId,voucherNumber,investmentListTag,companyCodeListTag,companyCodeTag,companyInvestmentList,
						companyInvestmentCode,bankLocationListTag,bankTransactionTag,paymentFavouringTag,bankPartyNameTag,salesTransTypeListTag,salesTransTypeTag);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			
			try {
				XmlToCsvConvertor.getAndReadXml(xmlFileName7,ledgername,ledgerlist,amount,date,tallyMessage,voucher,voucherType,deemedPositive,investmentIdTag,voucherKey,staticVariable,currentCompany,masterId,voucherNumber,investmentListTag,companyCodeListTag,companyCodeTag,companyInvestmentList,
						companyInvestmentCode,bankLocationListTag,bankTransactionTag,paymentFavouringTag,bankPartyNameTag,salesTransTypeListTag,salesTransTypeTag);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			
			
	 }
	 private static String getDate(int month){

			if(month <10){
				return "0"+month;
			} else{
				return String.valueOf(month);

			}
		}
		private static String getDay(int day){

			if(day < 10){
				return "0"+day;
				
			} else{
				return String.valueOf(day);

			}
			
		}
}
