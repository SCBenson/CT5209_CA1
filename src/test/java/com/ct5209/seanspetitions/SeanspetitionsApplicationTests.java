package com.ct5209.seanspetitions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SeanspetitionsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testPetitionCreation(){
		Petition p = new Petition(1, "Test Title", "Test Description");
		assertEquals("Test Title", p.getTitle());
		assertEquals("Test Description", p.getDescription());
		assertEquals(0, p.getSignatureCount());
	}

	@Test
	void testSignPetition(){
		Petition p = new Petition(1, "Test", "Desc");
		p.addSignature(new Signature("John", "john@test.com"));
		assertEquals(1, p.getSignatureCount());
		assertEquals("John", p.getSignatures().get(0).getName());
	}

}
