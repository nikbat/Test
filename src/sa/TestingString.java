package sa;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestingString {

	@Test
	public void test() {
		StringArray1 sa = new StringArray1();
		assertTrue(sa.stringInteger("1234") == 1234);
	}

}
