package com.enss.ec.tests.performance;

import static com.enss.ec.utils.FileUtils.*;

import java.io.File;

import org.junit.Test;

import com.enss.ec.jerasure.Decoder;
import com.enss.ec.jerasure.Encoder;

public class PerformanceTest extends BasePerfTest {


	int k = 3, m = 1, w = 8;
	Long[] files = {
//		100 * KB, 
//		500 * KB,  
//		1 * MB,
		10 * MB,
		100 * MB,
//		1 * GB,
	};


	@Test
	public void test() {
		long t1,t2;
		for(int i = 0; i < 10; i++){
			for(long size: files){
				String fileName = size + ".test";
				System.out.println("testing " + fileName);
				File f = getFile(fileName);
				cleanAndCreateFile(f, size);
				Encoder enc = new Encoder(k, m, w);
				Decoder dec = new Decoder(f, k, m, w);
				
				t1 = System.currentTimeMillis();
				enc.encode(f);
				t2 = System.currentTimeMillis();
				
				System.out.println("Encoding: " + (t2 - t1));
				
				
				t1 = System.currentTimeMillis();
				dec.decode(size);
				t2 = System.currentTimeMillis();
				
				System.out.println("Decoding: " + (t2 - t1));
				
			}
			System.out.println("\n\n");
		}
	}

}
