package compression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnbreakableEncryptionTest {


	private static final String TEST_STRING = "This is my test string.";

	@Test
	public void testEncryptSimpleThing(){
		KeyPair testKeyPair = UnbreakableEncryption.encrypt(TEST_STRING);
		String actual = UnbreakableEncryption.decrypt(testKeyPair);
		assertEquals(TEST_STRING, actual);
	}

}