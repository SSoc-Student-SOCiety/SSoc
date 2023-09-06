package gwangju.ssafy.backend.global.utils;

import java.util.Random;

public class AuthCodeGenerator {
	private static final int CERT_CODE_LENGTH = 8;
	private static final char[] CHARACTER_TABLE = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
		'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
		'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

	public static String generate() {
		Random random = new Random(System.currentTimeMillis());

		StringBuffer buf = new StringBuffer();

		for(int i = 0; i < CERT_CODE_LENGTH; i++) {
			buf.append(CHARACTER_TABLE[random.nextInt(CHARACTER_TABLE.length)]);
		}

		return buf.toString();
	}

}
