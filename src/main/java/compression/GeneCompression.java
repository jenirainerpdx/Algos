package compression;

import java.util.Locale;

public class GeneCompression {
	private BitSetWithSize bitSet;

	public GeneCompression(String gene) {
		try {
			compress(gene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void compress(String gene) throws Exception {
		String upperGene = gene.toUpperCase(Locale.ROOT);
		int length = gene.length();
		bitSet = new BitSetWithSize(length * 2);
		for (int i = 0; i < length; i++) {
			final int firstLocation = 2 * i;
			final int secondLocation = 2 * i + 1;
			switch (upperGene.charAt(i)) {
				case 'A': // set next two bits as 00
					bitSet.set(firstLocation, false);
					bitSet.set(secondLocation, false);
					break;
				case 'C': // set next two bits as 01
					bitSet.set(firstLocation, false);
					bitSet.set(secondLocation, true);
					break;
				case 'G': // set bits as 10
					bitSet.set(firstLocation, true);
					bitSet.set(secondLocation, false);
					break;
				case 'T': // set bits as 11
					bitSet.set(firstLocation, true);
					bitSet.set(secondLocation, true);
					break;
				default:
					throw new Exception("String contained invalid character:  " + upperGene.charAt(i));
			}
		}
	}

	public String decompress() {
		if (bitSet == null) {
			return "";
		}
		StringBuilder builder = new StringBuilder(bitSet.getNumBits() / 2);

		for (int i = 0; i < bitSet.getNumBits(); i += 2) {
			final int firstBit = (bitSet.get(i)) ? 1 : 0;
			final int secondBit = (bitSet.get(i + 1) ? 1 : 0);
			final int lastBits = firstBit << 1 | secondBit;
			// when firstBit is shifted, the space left behind is replaced with 00.
			// then we use the OR operator:  using OR with 0 will always result in value of what you are oring with.
			// so that is same as concat, basically?
			switch (lastBits) {
				case 0b00:
					builder.append('A');
					break;
				case 0b01:
					builder.append('C');
					break;
				case 0b10:
					builder.append('G');
					break;
				case 0b11:
					builder.append('T');
					break;
			}
		}
		return builder.toString();
	}
}
