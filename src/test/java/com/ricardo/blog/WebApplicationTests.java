package com.ricardo.blog;

import com.ricardo.blog.util.Const;
import com.ricardo.blog.util.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {

	@Test
	void contextLoads() {
		// 加密
		String encrypt = PasswordUtil.encrypt("123456", Const.PASSWORD, PasswordUtil.getStaticSalt());
		System.out.println(encrypt);

		//解密
		String decrypt = PasswordUtil.decrypt(encrypt, Const.PASSWORD, PasswordUtil.getStaticSalt());
		System.out.println(decrypt);
	}

	public static void main(String[] args) {
		// 加密
		String encrypt = PasswordUtil.encrypt("123456", Const.PASSWORD, PasswordUtil.getStaticSalt());
		System.out.println(encrypt);

		//解密
		String decrypt = PasswordUtil.decrypt(encrypt, Const.PASSWORD, PasswordUtil.getStaticSalt());
		System.out.println(decrypt);
	}

}
