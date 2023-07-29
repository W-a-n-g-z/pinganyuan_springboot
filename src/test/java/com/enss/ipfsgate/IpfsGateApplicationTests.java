package com.enss.ipfsgate;

import com.enss.ipfsgate.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IpfsGateApplicationTests {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Test
	void contextLoads() {
	}

}
