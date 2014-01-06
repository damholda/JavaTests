package com.pats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportTestResults {

	public static final String LABEL = "label";
	public static final String ELAPSED = "elapsed";
	public static final String FAIL = "fail";
	public static final String PASS = "pass";

	private String label;
	private String elapsed;
	private int fail;
	private int pass;
	private String reportAddress;
	private String user;
	private String environment;
	private String exchange;
	private String commodity;
	private String productClass;
	private String esaOrdersVersion;
	private String esaPricesVersion;
	private String recipients;
	private List<ReportTestResults> subResults;
	private boolean isTerminated;
	
	/**
	 * Creates ReportTestResults object without reading report file, therefore with
	 * null label and elapsed and no pass or fails.
	 */
	public ReportTestResults() {
	}

	/**
	 * Creates ReportTestResult object and populates it with values from given report file.
	 * @param reportFilePath full String path to report file
	 * @throws IOException if report file does not exists
	 */
	public ReportTestResults(String reportFilePath) throws IOException {
		File reportFile = new File(reportFilePath);
		readReportFile(reportFile);
	}

	private void readReportFile(File reportFile) throws IOException {
		FileReader fileReader = new FileReader( reportFile );
		try(BufferedReader bufReader = new BufferedReader( fileReader )){
			String currLine = null;
			while ( (currLine = bufReader.readLine()) != null ) {
				if ( currLine.indexOf("window.output[\"stats\"]") != -1 ) {
					//start pos is the first '{' after  'Critical Tests', end pos is the first '}' after start pos
					int startPosition = currLine.indexOf( "{" , currLine.indexOf("Critical Tests") ) + 1;
					int endPosition = currLine.indexOf( "}" , startPosition );

					String resultStr = currLine.substring( startPosition, endPosition );

					setFields( resultStr );
					break;
				}
			}
		}
	}

	private void setFields(String resultStr) {
		String[] resultArr = resultStr.replaceAll("\"", "").split(",");

		for ( String str : resultArr ) {
			String key =  str.substring( 0, str.indexOf(":") );
			String value = str.substring( str.indexOf(":") + 1 );

			switch ( key ) {
			case ReportTestResults.LABEL :
				setLabel( value );
				break;
			case ReportTestResults.PASS :
				setPass( Integer.parseInt(value) );
				break;
			case ReportTestResults.FAIL :
				setFail( Integer.parseInt(value) );
				break;
			case ReportTestResults.ELAPSED :
				setElapsed( value );
				break;
			default :
				break;
			}
		}
	}
	
	public List<ReportTestResults> getTestResults() {
		return subResults;
	}

	public void addTestResult(ReportTestResults result) {
		if (subResults == null) {
			subResults = new ArrayList<>();
		}
		subResults.add(result);
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getElapsed() {
		return elapsed;
	}
	public void setElapsed(String elapsed) {
		this.elapsed = elapsed;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public String getReportAddress() {
		return reportAddress;
	}
	public void setReportAddress(String reportAddress) {
		this.reportAddress = reportAddress.replace(' ', '_');
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getEnvironment() {
		return environment;
	}
	
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	public String getExchange() {
		return exchange;
	}
	
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getCommodity() {
		return commodity;
	}
	
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getProductClass() {
		return productClass;
	}
	
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public String getEsaOrdersVersion() {
		return esaOrdersVersion;
	}

	public void setEsaOrdersVersion(String esaOrderVersion) {
		this.esaOrdersVersion = esaOrderVersion;
	}

	public String getEsaPricesVersion() {
		return esaPricesVersion;
	}

	public void setEsaPricesVersion(String esaPricesVersion) {
		this.esaPricesVersion = esaPricesVersion;
	}

	public String getRecipients() {
		return recipients;
	}
	
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}
}