package com.TestScripts;

public class My_Information {

	public void LaunchApplication() throws Exception
	{
		super.setUp(getBrowser());
		_register = PageFactory.initElements(_driver, RegisterPage.class);
		PropertyConfigurator.configure("config/log4j.properties");
		_register.navigateToPageAndWait();
		_driver.manage().window().maximize();
		Thread.sleep(2000);		

		TestCaseName = testName.getMethodName();
		String TestCaseLogFolderPath = logFolder.getCanonicalPath()+"\\"+ TestCaseName;
		TestCaseLogFolder = new File(TestCaseLogFolderPath);
		if (!TestCaseLogFolder.exists()) {
			TestCaseLogFolder.mkdir();
		}

		DateFormat dateFormat = new SimpleDateFormat("hh_mm_a");
		String TestCaseLogSubFolderPath = TestCaseLogFolder.getCanonicalPath()+"\\"+ dateFormat.format(new Date());
		TestCaseLogSubFolder = new File(TestCaseLogSubFolderPath);
		if (!TestCaseLogSubFolder.exists()) {
			TestCaseLogSubFolder.mkdir();
		}

		ReadAndUpdate RU = new ReadAndUpdate();
		String TestCaseLogScreenshotsFolderPath = TestCaseLogSubFolder.getCanonicalPath()+"\\Screenshots";
		File TestCaseLogScreenshotsFolder = new File(TestCaseLogScreenshotsFolderPath);
		if (!TestCaseLogScreenshotsFolder.exists()) {
			TestCaseLogScreenshotsFolder.mkdir();
		}
		RU.UpdateFunction("Global", "Epace_Login", "Epace_Login", "ScreenshotsFolderPath", TestCaseLogScreenshotsFolder.getCanonicalPath());

		TestCaselogFile = new File(TestCaseLogSubFolder.getCanonicalPath()+"\\Test_Case_"+TestCaseName+".txt");
		fout = new FileOutputStream(TestCaselogFile);
		MultiOutputStream multiOut= new MultiOutputStream(System.out, fout);
		MultiOutputStream multiErr= new MultiOutputStream(System.err, fout);

		PrintStream stdout= new PrintStream(multiOut);
		PrintStream stderr= new PrintStream(multiErr);

		System.setOut(stdout);
		System.setErr(stderr);		
	}

}
