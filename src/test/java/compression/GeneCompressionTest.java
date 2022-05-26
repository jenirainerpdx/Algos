package compression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneCompressionTest {

	@Test
	public void testSimpleCase() {
		final String original = "ACGT";
		GeneCompression compressed = new GeneCompression(original);
		final String decompressed = compressed.decompress();
		assertEquals(original, decompressed);
	}
}