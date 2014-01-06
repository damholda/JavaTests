//package com.pats;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.Callable;
//
//import com.patsystems.robotframework.patslibrary.RobotFileUtils;
//import com.patsystems.robotframework.patslibrary.RobotUtils;
//
//public class BatchRun implements Callable<ReportTestResults>{	
////	private final String ALL = "ALL";
//	private final String ROBOT_HOME = "ROBOT_FRAMEWORK";
//	private final String JAVA_HOME = "JAVA_HOME";
//	private final String JYTHON_HOME = "JYTHON_HOME";
//	private final String PYTHON_HOME = "PYTHON_HOME";
//	private Map<String, String> systemVars;
//
//	private String smtp;
//	private String sender;
//	
//	private BatchConfigData configData;
//	private final BatchConfig batchConfig;
//	private Process currentRobotProcess;
//	private boolean isTerminated;
//		
//	public BatchRun(BatchConfig batchConfig, BatchConfigData batchConfigData) {
//		this.batchConfig = batchConfig;
//		this.configData = batchConfigData;
//	}	
//	
//	/**
//	 * Method to start batch in new thread.
//	 */
//	@Override
//	public ReportTestResults call() throws BatchException {
//		ReportTestResults report = null;
//		if (batchConfig!=null) {
//			report = executeBatch();
//		}
//		return report;
//	}
//	
//	public static void main(String[] args) {
//		
//		if (args == null || (args.length != 1 && args.length != 4)) {
//			System.out.println("usage: BatchRun configDir [exchange commodity productClass]");
//			System.out.println("  configDir     the path to the config directory which should contain");
//			System.out.println("                the BatchMode sub directory, for example:");
//			System.out.println("                'c:\\work\\RobotFramework\\QAScripts\\FunctionalTests\\JAPI\\ESA\\CONFIG\\'");
//			System.out.println("  optional: [exchange commodity productClass]");  
//			System.out.println("                exchange - is the exchange to be tested");
//			System.out.println("                commodity - is the commodity to be tested (or can be 'ALL')");
//			System.out.println("                productClass - productClass to be tested, ie FUTURE, OPTIONCALL... (or can be 'ALL')");
//			System.out.println("");
//		}
//		
//		String configPath = null;
//		
//		// validate config path
//		if (args!=null && args.length >= 1) {
//			configPath = args[0];
//			try {
//				validateConfigPath(configPath);
//			} catch (BatchException e) {
//				System.out.println(e.getMessage());
//				return;
//			}
//		}
//		
//		BatchConfigData configData = null;
//		try {
//			if (configPath==null) {
//				configPath = RobotUtils.loadSystemConfig().get(RobotUtils.CONFIG_DIR);
//			}
//			configData = new BatchConfigData(configPath + "\\Config\\BatchMode\\");
//		} catch (IOException | BatchException e) {
//			e.printStackTrace();
//		}
//
//		BatchConfig config = configData.getConfig();
//		if (args.length == 4){
//			config.setExchange(args[1]);
//			config.setCurrentCommodity(args[2]);
//			config.setCurrentProductClass(args[3]);
//		}
//		config.setIncludeTagsList(configData.produceIncludeTags(config));
//		config.setExcludeTagsList(configData.produceExcludeTags(config));
//		
//		try {
//			new BatchRun(config, configData).executeBatch();
//		} catch(BatchException e){
//			System.out.println(e.getMessage());
//		}
//	}
//
//	private static void validateConfigPath(String path) throws BatchException {
//		File file = new File(path);
//		if (!file.isDirectory()) {
//			throw new BatchException("config path: " + path + " does not point to a valid directory");
//		}
//		boolean batchModeSubDirExists = false;
//		for (File subFile : file.listFiles()) {
//			if (subFile.isDirectory() && subFile.getName().toLowerCase().equals("batchmode")) {
//				batchModeSubDirExists = true;
//			}
//		}
//		if (!batchModeSubDirExists) {
//			throw new BatchException("config path: " + path + " does not contain 'BatchMode' sub directory");
//		}
//	}
//	
//	/**
//	 * 
//	 * @param config
//	 * @return Network links to batch report and log files
//	 * @throws BatchException
//	 */
//	public ReportTestResults startBatch() throws BatchException {
//		ReportTestResults reportTestResults = new ReportTestResults();
//		
//		BatchConfig config = batchConfig;
////		FileExchange exchange = configData.getExchangeTagsMapping(config.getExchange());
////		List<String> commodityList = configData.getCommodities(config.getExchange(), config.getBuildVersion());
//		List<String> commodities = config.getCommodities();
//		List<String> productClasses = config.getProductClasses();
//		
//		reportTestResults.setUser(config.getUser());
//		reportTestResults.setEnvironment(config.getEnvironment());
//		reportTestResults.setExchange(config.getExchange());
//		reportTestResults.setCommodity(commodities.toString());
//		reportTestResults.setProductClass(productClasses.toString());
//		reportTestResults.setEsaOrdersVersion(config.getEsaOrdersVersion());
//		reportTestResults.setEsaPricesVersion(config.getEsaPricesVersion());
//		reportTestResults.setRecipients(config.getRecipients());
//
//		if (setAndValidateEnvironmentVariables()){
//			for (String commodity : commodities){
//				if(reportTestResults.isTerminated()) {
//					break;
//				}
//				
//				for (String productClass : productClasses){
//					try {
//						config.setCurrentCommodity(commodity);
//						config.setCurrentProductClass(productClass);
//						reportTestResults.addTestResult(startRobot(config));
//					} catch (IOException e) {
//						throw new BatchException(e.getMessage());
//					}
//					if(reportTestResults.getTestResults().get(0).isTerminated()) {
//						reportTestResults.setTerminated(true);
//						break;
//					}
//				}
//			}
//		} else {
//			reportTestResults.setTerminated(true);
//		}
//		return reportTestResults;
//	}
//
//	/**
//	 * Starts robotframework bat file with given arguments
//	 * @param config 
//	 * @param exchange
//	 * @param commodity
//	 * @param productClass
//	 * @return Network link to log and report files
//	 * @throws BatchException 
//	 */
//	private ReportTestResults startRobot(BatchConfig config) throws IOException {
//		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd-HHmmss");
//		String time = simple.format(new Date());		
//		
//		StringBuilder logsFilePathBuilder = new StringBuilder();
//		logsFilePathBuilder.append(configData.getLogsPath()).append("\\")
//			.append(config.getExchange()).append("\\");
//		String ordersVer = config.getEsaOrdersVersion();
//		if (ordersVer!=null){
//			logsFilePathBuilder.append("Orders-"+ordersVer);
//		}
//		String pricesVer = config.getEsaPricesVersion();
//		if (pricesVer!=null){
//			if (ordersVer!=null){
//				logsFilePathBuilder.append("-");
//			}
//			logsFilePathBuilder.append("Prices-"+pricesVer);
//		}
//		logsFilePathBuilder.append("\\");
//		logsFilePathBuilder.append(config.getCurrentCommodity()).append("-")
//			.append(config.getCurrentProductClass()).append("-")
//			.append(time).append("\\");
//		
//		String logsFullPath = logsFilePathBuilder.toString();
//		
//		String argumentFilePath = buildArgumentFile(config, logsFullPath);
//		String variableFilePath = buildVariableFile(config);
//		
//		ProcessBuilder processBuilder = new ProcessBuilder(
//				systemVars.get(JAVA_HOME)+"\\bin\\java",
////				"-Xmx1024m", "-Xss1024k", "-XX:MaxPermSize=128M",
//				"-Dcom.patsystems.api.prismmanager=com.patsystems.api.prism.ClientPrismManager",
//				"-Dcom.patsystems.api.bepexchange=com.patsystems.core.bep.ClientBEPExchange",
//				"-Dlog4j.configuration=file:\""+systemVars.get(ROBOT_HOME)+"\\Config\\log4j.properties\"",
//				"-Dpython.home=\""+systemVars.get(JYTHON_HOME)+"\"",
//				"-Dpython.executable=\""+systemVars.get(JYTHON_HOME)+"\\bin\\jython.bat\"",
//				"-classpath", "\""+systemVars.get(JYTHON_HOME)+"\\jython.jar\"", 
//				"org.python.util.jython", "\""+systemVars.get(PYTHON_HOME)+"\\Lib\\site-packages\\robot\\run.py\"", 
//				"-P", "\""+systemVars.get(ROBOT_HOME)+"\\Lib\\*.jar\"",
//				"-client",
//				"--argumentfile", argumentFilePath,
//				"--variablefile", variableFilePath, 
//				configData.getScriptDirPath()+config.getBuildVersion());
//		
//		StringBuilder stringPath = new StringBuilder();
//		stringPath.append(BatchConfigData.CURRENTLY_RUNNING_SCRIPTS_DIR)
//			.append(config.getExchange()).append("-")
//			.append(config.getCurrentCommodity()).append("-")
//			.append(config.getCurrentProductClass()).append("-")
//			.append(time).append(".log");
//
//		Files.createDirectories(Paths.get(BatchConfigData.CURRENTLY_RUNNING_SCRIPTS_DIR));
//		File runningScript = new File(stringPath.toString());		
//
//		System.out.println("====== ROBOT ======");
//		System.out.println("Running: "+processBuilder.command());
//		currentRobotProcess = processBuilder.start();
//
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter(runningScript));
//				BufferedReader reader =  new BufferedReader(new InputStreamReader(
//						currentRobotProcess.getInputStream()))){		
//
//			System.out.println("====== START ======");			
//			String line = reader.readLine();
//			while (line!=null && !line.trim().equals("--EOF--")) {
//				System.out.println(line);
//				writer.write(line);
//				writer.newLine();
//				writer.flush();
//				line = reader.readLine();
//		    }
//			System.out.println("======= END =======");
//		}
//
//		Path scriptsBackupDir = Files.createDirectories(Paths.get(
//				BatchConfigData.CURRENTLY_RUNNING_SCRIPTS_DIR+"..\\RanScriptsBackup"));
//		Files.move(Paths.get(runningScript.getAbsolutePath()), 
//				Paths.get(scriptsBackupDir+"\\"+runningScript.getName()));
//
//		// copy over stas, pdd and console logs to the test run dir
//		Path sourceDir = Files.createDirectories(Paths.get(RobotUtils.ROBOT_PATH+"Logs\\"));
//		Path targetDir = Files.createDirectories(Paths.get(logsFilePathBuilder.toString()));
//		moveLogFiles(sourceDir, targetDir);			
//
//		logsFilePathBuilder.append("report.html");
//		
//		ReportTestResults reportTestResult;
//		// get report run result
//		if(isTerminated) {
//			reportTestResult = new ReportTestResults();
//		} else {
//			reportTestResult = new ReportTestResults(logsFilePathBuilder.toString());
//			// remove statistics by tag in report.html and rename it.
//			ReportFormatter.formatReportFile(logsFilePathBuilder.toString(), config, config.getBuildVersion());
//		}
//		reportTestResult.setCommodity(config.getCurrentCommodity());
//		reportTestResult.setProductClass(config.getCurrentProductClass());
//
//		String address =  configData.getNetworkAddress()+logsFilePathBuilder.substring(2)+RobotUtils.LINE_SEPARATOR;
//		reportTestResult.setReportAddress( address );
//		reportTestResult.setTerminated(isTerminated);
//		
//		return reportTestResult;
//	}
//	
//	public void terminateBatchRun() {
//		isTerminated = true;
//		if(currentRobotProcess!=null) {
//			currentRobotProcess.destroy();
//		}
//	}
//	
//	/**
//	 * Starts batch run and sends email when finished.
//	 * @throws BatchException
//	 */
//	public ReportTestResults executeBatch() throws BatchException {
//		ReportTestResults reportTestResults = startBatch();//Run robot and create report
//		String networkLogsPath = "";
//		if (configData != null){
//			networkLogsPath = configData.getNetworkAddress()+configData.getLogsPath().substring(2);
//		}
//		if (smtp == null || sender == null) {
//			Map<String, String> systemConfig;
//			try {
//				systemConfig = RobotUtils.loadSystemConfig();
//			} catch (IOException e) {
//				throw new BatchException(e);
//			}
//			smtp = systemConfig.get("smtp");
//			sender = systemConfig.get("sender");
//		}
//		BatchEmail email = new BatchEmail(smtp, sender, networkLogsPath);
//		email.createAndSendEmail(reportTestResults);
//		return reportTestResults;
//	}
//	
//	private boolean setAndValidateEnvironmentVariables() {
//		boolean isValid = true;
//		
//		System.out.println("== Environment Variable Settings Check ==");
//		if (!validateAndSetVariable(ROBOT_HOME)){
//			isValid = false;
//		}
//		if (!validateAndSetVariable(JAVA_HOME, true)) {
//			isValid = false;
//		}
//		if (!validateAndSetVariable(JYTHON_HOME)) {
//			isValid = false;
//		}
//		if (!validateAndSetVariable(PYTHON_HOME)) {
//			isValid = false;
//		}
//		if (!isValid) {
//			isTerminated = true;
//		}
//		return isValid;
//	}
//
//	private boolean validateAndSetVariable(String variable, boolean... ignoreSpaceCheck) {
//		boolean isValid = false;
//		String variablePath = System.getenv().get(variable);
//		System.out.println(variable+" = "+variablePath);
//		isValid = variablePath!=null; 
//		if(ignoreSpaceCheck.length!=1 || !ignoreSpaceCheck[0]) {
//			isValid = !variablePath.contains(" ");
//		}
//		if(!isValid) {
//			System.out.println("Invalid "+variable+" environment variable. Correct before starting robot.");
//		}else {
//			if(systemVars==null) {
//				systemVars = new HashMap<>();
//			}
//			systemVars.put(variable, variablePath);
//		}
//		return isValid;
//	}
//
//	/**
//	 * Creates new argument file for robot. Takes user specified values, 
//	 * however product code and commodity needs to be valid ID, not "ALL".
//	 * @param config config class with default user input
//	 * @param exchange name of exchange to be used in this script 
//	 * @param commodity valid name of commodity to be used in this script iteration 
//	 * @param productClass valid name of product class to be used in this script iteration
//	 * @return
//	 */
//	private String buildArgumentFile(BatchConfig config, String logsFilePath) {
//		String argumentFilePath = RobotUtils.ROBOT_PATH+"argfile.txt";
//		StringBuilder output = new StringBuilder();
//		
//		for (String tag : configData.produceExcludeTags(config)) { //config.getExcludeTagsList()) {
//			output.append("--exclude=").append(tag).append(RobotUtils.LINE_SEPARATOR);
//		}
//		for (String tag : configData.produceIncludeTags(config)) { //config.getIncludeTagsList()) {
//			output.append("--include=").append(tag).append(RobotUtils.LINE_SEPARATOR);
//		}
//		
//		//set new path for log and report
//		output.append("--log").append(RobotUtils.LINE_SEPARATOR)
//			.append(logsFilePath).append("log.html").append(RobotUtils.LINE_SEPARATOR);
//		output.append("--report").append(RobotUtils.LINE_SEPARATOR)
//			.append(logsFilePath).append("report.html").append(RobotUtils.LINE_SEPARATOR);
//		output.append("--output").append(RobotUtils.LINE_SEPARATOR)
//			.append(logsFilePath).append("output.xml").append(RobotUtils.LINE_SEPARATOR);
//		return RobotFileUtils.writeToFile(argumentFilePath, output.toString());
//	}
//
//	/**
//	 * Creates new variable file for robot.
//	 * @param config
//	 * @param commodity
//	 * @param productClass
//	 * @return
//	 */
//	private String buildVariableFile(BatchConfig config) {
//		String filePath = RobotUtils.ROBOT_PATH+"varfile.py";
//
//		StringBuilder out = new StringBuilder();
//
//		out.append("ExchangeInTest1=\"").append(config.getExchange())
//			.append("\"").append(RobotUtils.LINE_SEPARATOR);
//		out.append("ProductClass1=\"").append(config.getProductClasses())
//			.append("\"").append(RobotUtils.LINE_SEPARATOR);
//		out.append("CommodityInTest1=\"").append(config.getCommodities())
//			.append("\"").append(RobotUtils.LINE_SEPARATOR);
//		out.append("EnvironmentInTest=\"").append(config.getEnvironment())
//			.append("\"").append(RobotUtils.LINE_SEPARATOR);
//		out.append("UserInTest=\"").append(config.getUser())
//			.append("\"").append(RobotUtils.LINE_SEPARATOR);
//		out.append("IgnoreAvgPrice=\"").append(config.isIgnoreAvgPrice())
//			.append("\"").append(RobotUtils.LINE_SEPARATOR);
//		
//		out.append("EsaInTest=\"BOTH\"").append(RobotUtils.LINE_SEPARATOR);
//		
//		
//		if (config.getStrategyType()!=null){
//			out.append("StrategyTypeInTest1=\"").append(config.getStrategyType())
//				.append("\"").append(RobotUtils.LINE_SEPARATOR);
//		}
//		if (config.getEsaOrdersVersion()!=null){
//			out.append("OrdersEsaInTest=\"").append(config.getEsaOrdersVersion())
//				.append("\"").append(RobotUtils.LINE_SEPARATOR);
//		}
//		if (config.getEsaPricesVersion()!=null){ 
//			out.append("PricesEsaInTest=\"").append(config.getEsaPricesVersion())
//				.append("\"").append(RobotUtils.LINE_SEPARATOR);
//		}
//
//		return RobotFileUtils.writeToFile(filePath, out.toString());
//	}
//	
//	/**
//	 * Moves (copies then deletes) any .log files from the specified source
//	 * dir to the specified target dir
//	 * 
//	 * @param sourceDir
//	 * @param targetDir
//	 * @throws IOException
//	 */
//	private void moveLogFiles(Path sourceDir, Path targetDir) throws IOException {
//		File[] files = sourceDir.toFile().listFiles(new FilenameFilter() {
//		    @Override
//		    public boolean accept(File dir, String name) {
//		        return name.endsWith(".log");
//		    }
//		});
//		for (File file : files) {
//			File targetFile = new File(targetDir +"\\"+ file.getName());
//			if (file.renameTo(targetFile)){
//				System.out.println("File '"+file.getName()+"' moved successfully.");
//			}else{
//				System.out.println("Failed to move file: "+file.getAbsolutePath());
//			}
//		}	
//	}
//}
