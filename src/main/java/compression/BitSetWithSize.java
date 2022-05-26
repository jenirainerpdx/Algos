package compression;

import java.util.BitSet;

public class BitSetWithSize  extends BitSet {

	private int numBits;

	/**
	 * Creates a bit set whose initial size is large enough to explicitly
	 * represent bits with indices in the range {@code 0} through
	 * {@code nbits-1}. All bits are initially {@code false}.
	 *
	 * @param numBits the initial size of the bit set
	 * @throws NegativeArraySizeException if the specified initial size
	 *                                    is negative
	 */
	public BitSetWithSize(int numBits) {
		super(numBits);
		this.numBits = numBits;
	}

	public int getNumBits() {
		return numBits;
	}

	public void setNumBits(int numBits) {
		this.numBits = numBits;
	}
}
