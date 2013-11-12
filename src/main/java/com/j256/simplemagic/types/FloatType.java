package com.j256.simplemagic.types;

import com.j256.simplemagic.endian.EndianConverter;
import com.j256.simplemagic.endian.EndianType;
import com.j256.simplemagic.entries.MagicFormatter;
import com.j256.simplemagic.entries.MagicMatcher;

/**
 * A 32-bit single precision IEEE floating point number in this machine's native byte order.
 * 
 * @author graywatson
 */
public class FloatType implements MagicMatcher {

	private final EndianConverter endianConverter;

	public FloatType(EndianType endianType) {
		this.endianConverter = endianType.getConverter();
	}

	public Object convertTestString(String typeStr, String testStr, int offset) {
		try {
			return Float.parseFloat(testStr);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Could not parse float from: " + testStr);
		}
	}

	public Object extractValueFromBytes(int offset, byte[] bytes) {
		Long val = endianConverter.convertNumber(offset, bytes, 4);
		if (val == null) {
			return null;
		} else {
			return Float.intBitsToFloat(val.intValue());
		}
	}

	public Object isMatch(Object testValue, Long andValue, boolean unsignedType, Object extractedValue, int offset,
			byte[] bytes) {
		// not sure how to do the & here
		if (testValue.equals(extractedValue)) {
			return extractedValue;
		} else {
			return null;
		}
	}

	public void renderValue(StringBuilder sb, Object extractedValue, MagicFormatter formatter) {
		formatter.format(sb, extractedValue);
	}

	public Byte getStartingByte(Object testValue) {
		return null;
	}
}
