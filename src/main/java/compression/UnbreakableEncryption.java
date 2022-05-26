package compression;

import java.util.Random;

public class UnbreakableEncryption {
	private static byte[] randomKey (int length) {
		byte[] dummy = new byte[length];
		Random random = new Random();
		random.nextBytes(dummy);
		return dummy;
	}

	/**
	 * does it matter if we put dummy or encrypted as first?
	 * @param original - String to be encrypted.
	 * @return KeyPair including dummy and encrypted keys.
	 */
	public static KeyPair encrypt(String original) {
		byte[] originalBytes = original.getBytes();
		byte[] dummyKey = randomKey(originalBytes.length);
		byte[] encryptedKey = new byte[originalBytes.length];
		for (int i = 0; i < originalBytes.length; i++) {
			encryptedKey[i] = (byte) (originalBytes[i] ^ dummyKey[i]);
		}
		return new KeyPair(dummyKey, encryptedKey);
	}

	public static String decrypt(KeyPair keyPair) {
		byte[] decrypted = new byte[keyPair.key1.length];
		for (int i = 0; i < keyPair.key1.length; i++) {
			decrypted[i] = (byte) (keyPair.key1[i] ^ keyPair.key2[i]);
		}
		return new String(decrypted);
	}
}
