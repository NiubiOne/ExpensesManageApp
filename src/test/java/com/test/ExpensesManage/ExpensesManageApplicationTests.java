package com.test.ExpensesManage;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExpensesManageApplicationTests {

	@Autowired
	private ExpensesController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}



}
