package com.peermagic.xmlreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import com.peermagic.conversion.UploadTally;

public class XmlToCsvConvertor {
	
	private static Workbook workbook;
	private static int rowNum;


	private final static int LLEDGER_LIST = 0;
	private final static int LEDGER_NAME = 1;

	/*public static void main(String[] args) throws Exception {
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
		//UDF:VCHLEDGERCODE.LIST
	
		
		getAndReadXml(ledgername,ledgerlist,amount,date,tallyMessage,voucher,voucherType,deemedPositive,investmentIdTag,voucherKey,staticVariable,currentCompany,masterId,voucherNumber,investmentListTag,companyCodeListTag,companyCodeTag,companyInvestmentList,
				companyInvestmentCode,bankLocationListTag,bankTransactionTag,paymentFavouringTag,bankPartyNameTag,salesTransTypeListTag,salesTransTypeTag);
	}*/

	/**
	 *
	 * Downloads a XML file, reads the substance and product values and then
	 * writes them to rows on an excel file.
	 *
	 * @throws Exception
	 */
	 public static void getAndReadXml(String xmlFileName,String ledgername,String ledgerlist ,String amount, String date, String tallyMessage,String voucher,String voucherType,String deemedPositive,String investmentIdTag,String voucherKey, String staticVariable, String currentCompany, String masterId,String voucherNumber,String investmentListTag,String companyCodeListTag,String companyCodeTag,
			 String companyInvestmentList,String companyInvestmentCode,String bankLocationListTag,String bankTransactionTag,String paymentFavouringTag,String bankPartyNameTag,String salesTransTypeListTag, String salesTransTypeTag) throws Exception {
		System.out.println("Reading Xml Data name tag alledgerlist::::"+ledgerlist+":::ledgername tag::: "+ledgername+":::date::: "+date);

		System.out.println("FIle Name.....:"+xmlFileName+".xml");
		/*File xmlFile = new File(
				"D:\\Workspaces\\xml2xls\\Sample-xml-to-excel-master\\Sample-xml-to-excel-master\\Indirect_Exp.xml");
*/
		File xmlFile = new File(xmlFileName+".xml");
		if(xmlFile.exists() && !xmlFile.isDirectory()) { 
		initXls();
		

		Sheet sheet = workbook.getSheetAt(0);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		dbFactory.setValidating(true);
		dbFactory.setNamespaceAware(true);
		
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		
		List<String> transType = new ArrayList<String>();
		transType.add("Transaction Type");
		
		List<String> transAmount = new ArrayList<String>();
		transAmount.add("Amount");
		
		List<String> Datelist = new ArrayList<String>();
		Datelist.add("Transaction Date");
		
		List<String> name = new ArrayList<String>();
		name.add("Investor Name");
		
		List<String> narration = new ArrayList<String>();
		narration.add("Narration");
		
		/*List<String> voucherNumber = new ArrayList<String>();
		voucherNumber.add("Transaction Number");*/
		
		List<String> masterIdList = new ArrayList<String>();
		masterIdList.add("MasterId");
		
		List<String> voucherKeyList = new ArrayList<String>();
		voucherKeyList.add("VoucherKey");
		
		List<String> deemedPositiveList = new ArrayList<String>();
		deemedPositiveList.add("IsDeemedPositive");

		List<String> investmentIdList = new ArrayList<String>();
		investmentIdList.add("InvestorId");

		List<String> projectList = new ArrayList<String>();
		projectList.add("Project");
		
		List<String> projectIdList = new ArrayList<String>();
		projectIdList.add("ProjectCode");
		
		List<String> investmentList = new ArrayList<String>();
		investmentList.add("Investment");
		
		List<String> voucherNumList = new ArrayList<String>();
		voucherNumList.add("Voucher Number");
		
		List<String> transactionDateList = new ArrayList<String>();
		transactionDateList.add("Bank Transaction Date");
		
		
		List<String> bankTransactiontypeList = new ArrayList<String>();
		bankTransactiontypeList.add("Bank Transaction Type");
		
		
		List<String>paymentFavouringList = new ArrayList<String>();
		paymentFavouringList.add("Bank Payment Favouring");
		
		
		List<String>bankPartyNameList = new ArrayList<String>();
		bankPartyNameList.add("Bank PartyName");
		
		List<String>bankAmountList = new ArrayList<String>();
		bankAmountList.add("Bank Amount");
		
		List<String>salesTransTypeList = new ArrayList<String>();
		salesTransTypeList.add("Saleforce Transaction Type");
		
		List<String> remarksList = new ArrayList<String>();
		remarksList.add("Remarks");
		
		NodeList tallyMsg = doc.getElementsByTagName(tallyMessage);
		
		String projectName= null;
		
		NodeList projects = doc.getElementsByTagName(staticVariable);
		for(int i =0; i<projects.getLength();i++){
			 Node node = projects.item(i);
			  if (node.getNodeType() == Node.ELEMENT_NODE) {
			        Element element = (Element) node;
			        try{
			        projectName = element.getElementsByTagName(currentCompany).item(0).getTextContent();
			        projectList.add(projectName);
			        }
			        catch(java.lang.NullPointerException e){
						
					}
			  }
		}
		
		/*NodeList investments = doc.getElementsByTagName("UDF:VCHLEDGERCODE.LIST");
		for(int i =0; i<investments.getLength();i++){
			 Node node = investments.item(i);
			  if (node.getNodeType() == Node.ELEMENT_NODE) {
			        Element element = (Element) node;
			        try{
			        investmentId = element.getElementsByTagName("UDF:VCHLEDGERCODE").item(0).getTextContent();
			        System.out.println("Investment Id........"+investmentId);
			        investmentIdList.add(investmentId);
			        }
			        catch(java.lang.NullPointerException e){
						System.out.println("The tag  is not present in the XML");
						
					}
			  }
		}*/
		
		NodeList nList = doc.getElementsByTagName(tallyMessage);
		 String date1 = null;
		for (int i = 0; i < nList.getLength(); i++) {
		    //System.out.println("Processing element " + (i+1) + "/" + nList.getLength());
		    Node node = nList.item(i);
		    if (node.getNodeType() == Node.ELEMENT_NODE) {
		        Element element1 = (Element) node;
		        try{
		        
		        	
			        
			       
		        //NodeList prods = nList.item(i).getChildNodes();
		        NodeList ladList = element1.getElementsByTagName(ledgerlist);
		        //System.out.println("ledgerList Length.......:"+ladList.getLength());
		
		        for (int j = 0; j <ladList.getLength(); j++) {
		        	//System.out.println("Processing element ledger name...." + (i+1) + "/" + prods.getLength());
		            Node prod = ladList.item(j);
		            if (prod.getNodeType() == Node.ELEMENT_NODE) {
		                Element product = (Element) prod;
		              
		                String voucherTypeName = element1.getElementsByTagName(voucherType).item(0).getTextContent();
				        //System.out.println("voucherTypeName.....:"+voucherTypeName+"Count..."+i);
				        transType.add(voucherTypeName);
				        
		                String ledgerName = product.getElementsByTagName(ledgername).item(0).getTextContent();
						name.add(ledgerName);
						
						String ledgeramount = product.getElementsByTagName(amount).item(0).getTextContent();
						transAmount.add(ledgeramount);
						//System.out.println("ledgerName........."+ledgerName);
						
						String isDeemedPositive = product.getElementsByTagName(deemedPositive).item(0).getTextContent();
						 deemedPositiveList.add(isDeemedPositive);
						 
						 String datelst =  element1.getElementsByTagName(date).item(0).getTextContent();
							//System.out.println("date List....."+datelst);
						
							String masterIdValue = element1.getElementsByTagName(masterId).item(0).getTextContent();
							masterIdList.add(masterIdValue);
							
							String voucherKeys = element1.getElementsByTagName(voucherKey).item(0).getTextContent();
							voucherKeyList.add(voucherKeys);
							
							 String companyCode = element1.getElementsByTagName(companyCodeTag).item(0).getTextContent();
	    				        projectIdList.add(companyCode);
							
							String voucherNum = element1.getElementsByTagName(voucherNumber).item(0).getTextContent();
							voucherNumList.add(voucherNum);
							
							NodeList investments = product.getElementsByTagName(investmentListTag);
							//System.out.println("investments........length:"+investments.getLength());
							if(investments.getLength()>0){
								for(int k =0; k<investments.getLength();k++){
									 Node node1 = investments.item(k);
									  if (node1.getNodeType() == Node.ELEMENT_NODE) {
										  Element elementInvst = (Element) node1;
									        try{
									       String investmentId = elementInvst.getElementsByTagName(investmentIdTag).item(0).getTextContent();
									        investmentIdList.add(investmentId);
									        
									        }
									        catch(java.lang.NullPointerException e){
												
											}
									  } 
							
						}
							}
							else{
								investmentIdList.add("");
							}
							
							
							/*NodeList companyList = element1.getElementsByTagName(companyCodeListTag);
				    		if(companyList.getLength()>0){
				    			for(int c=0; c < companyList.getLength(); c++){
				    				Node companyNode = companyList.item(c);
				    				  if (companyNode.getNodeType() == Node.ELEMENT_NODE) {
				    					  Element elementInvst = (Element) companyNode;
				    				        try{
				    				        String companyCode = elementInvst.getElementsByTagName(companyCodeTag).item(0).getTextContent();
				    				 
				    				        projectIdList.add(companyCode);
				    				       
				    				        }
				    				        catch(java.lang.NullPointerException e){
				    							
				    						}
				    			}
				    		}
				    		}
				    		else{
				    			  projectIdList.add("");
				    		}*/
					   
							NodeList salesTransactionTypeList = element1.getElementsByTagName(salesTransTypeListTag);
				    		if(salesTransactionTypeList.getLength()>0){
				    			for(int c=0; c < salesTransactionTypeList.getLength(); c++){
				    				Node salesTransNode = salesTransactionTypeList.item(c);
				    				  if (salesTransNode.getNodeType() == Node.ELEMENT_NODE) {
				    					  Element elementInvst = (Element) salesTransNode;
				    				        try{
				    				        	String salesTransType= elementInvst.getElementsByTagName(salesTransTypeTag).item(0).getTextContent();
				    				        //System.out.println("salesforce Tag.....:"+salesTransType);
				    				 
				    				        salesTransTypeList.add(salesTransType);
				    				        if(salesTransType.equals("Tax Withholding")){
												
								    			remarksList.add("Tax Withhold Return");
											}
								    		else{
								    			remarksList.add(salesTransType);
								    		}
				    				        }
				    				        catch(java.lang.NullPointerException e){
				    							
				    						}
				    			}
				    		}
				    		}
				    		else{
				    			salesTransTypeList.add("");
				    			remarksList.add("");
				    		}
				    		
				    		
				    		/*NodeList salesTransactionTypeList1 = element1.getElementsByTagName(salesTransTypeListTag);
				    		if(salesTransactionTypeList1.getLength()>0){
				    			for(int c=0; c < salesTransactionTypeList1.getLength(); c++){
				    				Node salesTransNode = salesTransactionTypeList1.item(c);
				    				  if (salesTransNode.getNodeType() == Node.ELEMENT_NODE) {
				    					  Element elementInvst = (Element) salesTransNode;
				    				        try{
				    				        	String salesTransType= elementInvst.getElementsByTagName(salesTransTypeTag).item(0).getTextContent();
				    				        	if(salesTransType.equals("Tax Withholding")){
													
									    			remarksList.add("Tax Withhold Return");
												}
									    		else{
									    			remarksList.add(salesTransType);
									    		}
				    				        }
				    				        catch(java.lang.NullPointerException e){
				    							
				    						}
				    			}
				    		}
				    		}
				    		else{
				    			remarksList.add("");
				    		}*/
							
				    		
							
				    		  String transactionDate = null;
				    		NodeList companyinvestment = element1.getElementsByTagName(companyInvestmentList);
				    		if(companyinvestment.getLength()>0){
				    			for(int n=0; n < companyinvestment.getLength(); n++){
				    				Node companyInvstNode = companyinvestment.item(n);
				    				  if (companyInvstNode.getNodeType() == Node.ELEMENT_NODE) {
				    					  Element elementInvst = (Element) companyInvstNode;
				    				        try{
				    				        	
				    				        	 String companyInvestment = elementInvst.getElementsByTagName(companyInvestmentCode).item(0).getTextContent();
				    				        	 investmentList.add(companyInvestment);
				    				       
				    				        }
				    				        catch(java.lang.NullPointerException e){
				    							
				    						}
				    			}
				    		}
				    		}
				    		else{
				    			investmentList.add("");
				    		}
				    		String bankTransactionType = null;
				    		String paymentFavouring = null;
				    		String bankPartyName = null;
				    		String bankAmount = null;
				    		NodeList bankList = product.getElementsByTagName(bankLocationListTag);
				    		for(int b=0; b < bankList.getLength(); b++){
			    				Node bankNode = bankList.item(b);
			    				  if (bankNode.getNodeType() == Node.ELEMENT_NODE) {
			    					  Element bankElement = (Element) bankNode;
			    					  //System.out.println("element value......:"+bankElement.getNodeValue());
			    					
			    						  try{
			    							  transactionDate = bankElement.getElementsByTagName(date).item(0).getTextContent();
			    				        		
				    				        	
				    				        	 bankTransactionType = bankElement.getElementsByTagName(bankTransactionTag).item(0).getTextContent();
				    				        	
				    				        	
				    				        	 paymentFavouring = bankElement.getElementsByTagName(paymentFavouringTag).item(0).getTextContent();
				    				        	 
				    				        	 bankPartyName = bankElement.getElementsByTagName(bankPartyNameTag).item(0).getTextContent();
				    				        	 
				    				        	 bankAmount = bankElement.getElementsByTagName(amount).item(0).getTextContent();
				    				        	
			    						  }
				    					  catch(java.lang.NullPointerException e){
				    							
				    						}
			    					 
			    				  }
				    		}
				    		
				    		
				    		if(bankTransactionType!=null){
				    			bankTransactiontypeList.add(bankTransactionType);
				    		}
				    		else{
				    			bankTransactiontypeList.add("");
				    		}
				    		
				    		if(paymentFavouring!=null){
				    			paymentFavouringList.add(paymentFavouring);
				    		}
				    		else{
				    			paymentFavouringList.add("");
				    		}
				    		
				    		if(bankPartyName != null){
				    			bankPartyNameList.add(bankPartyName);
				    		}
				    		else{
				    			bankPartyNameList.add("");
				    		}
							
				    		if(bankAmount != null){
				    			bankAmountList.add(bankAmount);
				    		}
				    		else{
				    			bankAmountList.add("");
				    		}
				    		
							try {
								 
								//input date format
								 SimpleDateFormat df_in = new SimpleDateFormat("yyyyMMdd");
								 
								//output date format
								 SimpleDateFormat df_output = new SimpleDateFormat("MM/dd/yyyy");
								 Date date11 = df_in.parse(datelst);
								 date1 = df_output.format(date11);
								 if(transactionDate!=null){
									 Date date22 = df_in.parse(transactionDate);
										String date2 = df_output.format(date22);
										 transactionDateList.add(date2);
								 }
								 else{
									 transactionDateList.add("");
								 }
								
							
								 } catch (Exception e) {
								
								 }
								 
									
						        Datelist.add(date1);
						       //System.out.println("formated Date....:"+Datelist);
						       //System.out.println("transaction date.....:"+transactionDateList);
						
		            }
		        }
		        
		        }
				catch(java.lang.NullPointerException e){
					
				}
		      
		       
		    }
		}

		
		for (int i = 0; i < name.size(); i++) {
			Row r = sheet.createRow(i);
			r.createCell(0).setCellValue(salesTransTypeList.get(i));
			r.createCell(1).setCellValue(transType.get(i));
			r.createCell(2).setCellValue(deemedPositiveList.get(i));
			r.createCell(3).setCellValue(investmentIdList.get(i));
			
			/*if(investmentIdList.size() > i){
				r.createCell(3).setCellValue(investmentIdList.get(i));
			} else{
				r.createCell(3).setCellValue("");
			}*/
			
			r.createCell(4).setCellValue(projectIdList.get(i));
			r.createCell(5).setCellValue(transAmount.get(i));
			r.createCell(6).setCellValue(Datelist.get(i));
			r.createCell(7).setCellValue(remarksList.get(i));
			r.createCell(8).setCellValue(masterIdList.get(i));
			//r.createCell(7).setCellValue(name.get(i));
			
			
			
			/*r.createCell(1).setCellValue(name.get(i));
			r.createCell(2).setCellValue(Datelist.get(i));
			
			
			/*if(i==0){
				r.createCell(5).setCellValue(projectList.get(0));
			} else{
				r.createCell(5).setCellValue(projectList.get(1));
			}
			
			
		
			r.createCell(7).setCellValue(masterIdList.get(i));
			r.createCell(8).setCellValue(voucherKeyList.get(i));
			r.createCell(9).setCellValue(investmentList.get(i));
			r.createCell(10).setCellValue(voucherNumList.get(i));
			//r.createCell(12).setCellValue(transactionDateList.get(i));
			//r.createCell(13).setCellValue(bankTransactiontypeList.get(i));
			
			r.createCell(12).setCellValue(bankTransactiontypeList.get(i));
			r.createCell(13).setCellValue(paymentFavouringList.get(i));
			r.createCell(14).setCellValue(bankPartyNameList.get(i));
			r.createCell(15).setCellValue(bankAmountList.get(i));
		
			/*if(bankTransactiontypeList.size() > i){
				r.createCell(13).setCellValue(bankTransactiontypeList.get(i));
			} else{
				r.createCell(13).setCellValue("");
			}
			if(paymentFavouringList.size() > i){
				r.createCell(14).setCellValue(paymentFavouringList.get(i));
			}
			else{
				r.createCell(14).setCellValue("");
			}*/
			
			
			

		}

		
		FileOutputStream fileOut = new FileOutputStream(xmlFileName+".xlsx");
		workbook.write(fileOut);
		//workbook.close();
		fileOut.close();
		
		XmlToCsvConvertor xmlConvert= new XmlToCsvConvertor();
		String filename= xmlFileName+".xlsx";
		
		try {
			xmlConvert.convertExcelToCSV(filename);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else{
			System.out.println("Please upload the file...");
			
		}

	}

	/**
	 * Initializes the POI workbook and writes the header row
	 */
	private static void initXls() {
		workbook = new XSSFWorkbook();

		CellStyle style = workbook.createCellStyle();
		Font boldFont = workbook.createFont();
		boldFont.setBold(true);
		style.setFont(boldFont);
		style.setAlignment(CellStyle.ALIGN_CENTER);

		Sheet sheet = workbook.createSheet();
		rowNum = 0;
		Row row = sheet.createRow(rowNum++);
		Cell cell = row.createCell(LLEDGER_LIST);
		cell.setCellValue("Dispacc name");
		cell.setCellStyle(style);

		Row row1 = sheet.createRow(rowNum++);
		Cell cell1 = row1.createCell(LEDGER_NAME);
		cell1.setCellValue("Dspcl dramta");
		cell1.setCellStyle(style);

	}
	
	public  void convertExcelToCSV(String fileName) 
			throws InvalidFormatException, IOException {

		BufferedWriter output = new BufferedWriter(new FileWriter(fileName.substring(0, fileName.lastIndexOf(".")) + ".csv"));
	
		//System.out.println("output....:"+output);

		InputStream is = new FileInputStream(fileName);

		Workbook wb = WorkbookFactory.create(is);

		Sheet sheet = wb.getSheetAt(0);
	
		int maxColumns = sheet.getRow(0).getLastCellNum();
	    
		for (Row row : sheet) {
			
			int minCol = 0; // row.getFirstCellNum()
			int maxCol = maxColumns; // row.getLastCellNum()

			for (int i = minCol; i < maxCol; i++) {

				Cell cell = row.getCell(i);
			
				String buf = "";
				if (i > 0) {
					buf = ",";
				}

				if (cell == null) {
					output.write(buf);
					//System.out.print(buf);
				} else {

					String v = null;

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						v = cell.getRichStringCellValue().getString();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							v = cell.getDateCellValue().toString();
						} else {
							v = String.valueOf(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						v = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						v = cell.getCellFormula();
						break;
					default:
					}

					if (v != null) {
						buf = buf + toCSV(v);
					}
					output.write(buf);
					//System.out.print(buf);
				}
			}

			output.write("\n");
			
			//System.out.println(output);
		}
		is.close();
		output.close();
		String f= fileName.substring(0, fileName.lastIndexOf(".")) + ".csv";
		//UploadTally.uploadFile(f);
		//System.out.println("file name...:"+f);
	}


	/*
	 * </strong>
	 * Escape the given value for output to a CSV file. 
	 * Assumes the value does not have a double quote wrapper.
	 * @return
	 */
	public static String toCSV(String value) {

		String v = null;
		boolean doWrap = false;

		if (value != null) {

			v = value;

			if (v.contains("\"")) {
				v = v.replace("\"", "\"\""); // escape embedded double quotes
				doWrap = true;
			}

			if (v.contains(",") || v.contains("\n")) {
				doWrap = true;
			}

			if (doWrap) {
				v = "\"" + v + "\""; // wrap with double quotes to hide the comma
			}
		}

		return v;
	}

}
