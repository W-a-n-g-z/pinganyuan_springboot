package com.enss.ec.tests.performance;

import static com.enss.ec.utils.FileUtils.cleanDir;
import static com.enss.ec.utils.FileUtils.createRandomContentFile;
import static org.junit.Assert.*;

import java.io.File;

import com.enss.ec.utils.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BasePerfTest {


	static File testDir = new File("performanceTest");
	protected File getFile(String fileName) {
		return new File(testDir.getAbsolutePath() + File.separator + fileName);
	}
	
	protected void cleanAndCreateFile(File f, long size){
		FileUtils.cleanDir(testDir);
		assertTrue(FileUtils.createRandomContentFile(f, size));
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		if (!testDir.isDirectory()) {
			testDir.mkdir();
		}

		FileUtils.cleanDir(testDir);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		FileUtils.cleanDir(testDir, true);
		if (testDir.isDirectory()) {
			testDir.delete();
		}
	}

	@After
	public void tearDown() {
		FileUtils.cleanDir(testDir);
	}
}
