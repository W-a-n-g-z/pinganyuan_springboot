package com.enss.ec;

import java.io.File;

import com.enss.ec.jerasure.Encoder;

public class ECTest {
	public static void main(String[] args) {
////		Encoder ec = new Encoder(6,3,8);
////		ec.encode(new File("F:\\ECTest\\method test.txt"));
//		
//		Decoder dc = new Decoder(new File("F:\\ECTest\\method test.txt"),6,3,8);
//		dc.decode(1532344);
		
		long start = System.currentTimeMillis();

		Encoder ec = new Encoder(4,2,8);
		ec.encode(new File("F:\\ECTest\\加油吧威基基-01.mp4"));

//		Decoder dc = new Decoder(new File("F:\\ECTest\\加油吧威基基-01.mp4"),4,2,8);
//		dc.decode(567677653);

//		int a = 1 << 8;
//		System.out.println(a);

		long end = System.currentTimeMillis();
		long relTime = end-start;
		System.out.println("共耗时:"+relTime+"豪秒");
		
	}
}
