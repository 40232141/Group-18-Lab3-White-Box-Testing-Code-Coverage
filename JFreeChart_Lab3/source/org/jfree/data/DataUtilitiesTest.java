package org.jfree.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import junit.framework.TestCase;

public class DataUtilitiesTest extends TestCase {

	private Values2D posValues2D;
	private Values2D negValues2D;
	private Values2D empValues2D;

	private double[] posArrayDoubleValues;
	private double[] posArrayDoubleFalseValues;
	private double[] negArrayDoubleValues;
	private double[] negArrayDoubleFalseValues;
	private Number[] posArrayNumberValues;
	private Number[] negArrayNumberValues;

	private double[][] posArrayDoubleValues2D;
	private double[][] posArrayDoubleFalseValues2D;
	private double[][] negArrayDoubleValues2D;
	private double[][] negArrayDoubleFalseValues2D;
	private Number[][] posArrayNumberValues2D;
	private Number[][] negArrayNumberValues2D;

	private KeyedValues posTestKeyedValues;
	private KeyedValues negTestKeyedValues;

	@Before
	public void setUp() throws Exception {

		DefaultKeyedValues2D posTestValues = new DefaultKeyedValues2D();
		posTestValues.addValue(1, 0, 0);
		posTestValues.addValue(4, 1, 0);
		posTestValues.addValue(3, 0, 1);
		posTestValues.addValue(6, 1, 1);

		posValues2D = posTestValues;
		
		DefaultKeyedValues2D negTestValues = new DefaultKeyedValues2D();
		negTestValues.addValue(-1, 0, 0);
		negTestValues.addValue(-4, 1, 0);
		negTestValues.addValue(-3, 0, 1);
		negTestValues.addValue(-6, 1, 1);

		negValues2D = negTestValues;

		posArrayDoubleValues = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0 };
		posArrayDoubleFalseValues = new double[] { 5.0, 4.0, 3.0, 2.0, 1.0 };
		negArrayDoubleValues = new double[] { -1.0, -2.0, -3.0, -4.0, -5.0 };
		negArrayDoubleFalseValues = new double[] { -5.0, -4.0, -3.0, -2.0, -1.0 };

		posArrayDoubleValues2D = new double[][] { { 1.0, 2.0, 3.0, 4.0, 5.0 }, { 5.0, 4.0, 3.0, 2.0, 1.0 } };
		posArrayDoubleFalseValues2D = new double[][] { { 5.0, 4.0, 3.0, 2.0, 1.0 }, { 1.0, 2.0, 3.0, 4.0, 5.0 } };
		negArrayDoubleValues2D = new double[][] { { -1.0, -2.0, -3.0, -4.0, -5.0 }, { -5.0, -4.0, -3.0, -2.0, -1.0 } };
		negArrayDoubleFalseValues2D = new double[][] { { -5.0, -4.0, -3.0, -2.0, -1.0 }, { -1.0, -2.0, -3.0, -4.0, -5.0 } };

		DefaultKeyedValues posTestDefaultKeyedValues = new DefaultKeyedValues();
		posTestDefaultKeyedValues.addValue("0", 5);
		posTestDefaultKeyedValues.addValue("1", 9);
		posTestDefaultKeyedValues.addValue("2", 2);

		posTestKeyedValues = posTestDefaultKeyedValues;
		
		DefaultKeyedValues negTestDefaultKeyedValues = new DefaultKeyedValues();
		negTestDefaultKeyedValues.addValue("0", -5);
		negTestDefaultKeyedValues.addValue("1", -9);
		negTestDefaultKeyedValues.addValue("2", -2);

		negTestKeyedValues = negTestDefaultKeyedValues;
		
		empValues2D = null;
	}

	@After
	public void tearDown() throws Exception {

		posValues2D = null;
		negValues2D = null;
		posArrayDoubleValues = null;
		posArrayDoubleValues2D = null;
		posArrayDoubleFalseValues = null;
		posArrayDoubleFalseValues2D = null;
		negArrayDoubleValues = null;
		negArrayDoubleValues2D = null;
		negArrayDoubleFalseValues = null;
		negArrayDoubleFalseValues2D = null;
		posTestKeyedValues = null;
		negTestKeyedValues = null;

	}

	// Returns exception because of null value
	@Test
	public void testNullDataColumnTotal() {

		try {

			DataUtilities.calculateColumnTotal(null, 0);
			fail("No exception thrown-Expected outcome was: a thrown exception of type: InvalidParameterException");

		} catch (Exception e) {

			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	@Test
	public void testEmptyDataAndColumnTotalTrue() {
		assertEquals("Wrong sum returned. It should be null", null, DataUtilities.calculateColumnTotal(empValues2D, 0));
	}
	
	
	// Checks number of positive or negative values added together in column 0. total to 5 or return assert message
	@Test
	public void testValidDataAndColumnTotalTrue() {
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(posValues2D, 0));
		assertEquals("Wrong sum returned. It should be -5.0", -5.0, DataUtilities.calculateColumnTotal(negValues2D, 0));

	}
	
	// Checks number of positive or negative values added together in column 1. total to 3 or return assert message
	@Test
	public void testValidDataAndColumnTotalFalse() {
		assertEquals("Wrong sum returned. It should be 3.0", 1.0, DataUtilities.calculateColumnTotal(posValues2D, 1));
		assertEquals("Wrong sum returned. It should be -3.0", -1.0, DataUtilities.calculateColumnTotal(negValues2D, 1));

	}

	// Returns exception because of null value
	@Test
	public void testNullDataRowTotal() {

		try {

			DataUtilities.calculateRowTotal(null, 0);
			fail("No exception thrown-Expected outcome was: a thrown exception of type: InvalidParameterException");

		} catch (Exception e) {

			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	@Test
	public void testEmptyDataAndRowTotal() {
		assertEquals("Wrong sum returned. It should be null", null, DataUtilities.calculateRowTotal(empValues2D, 0));
	}

	// Checks number of positive or negative values added together in row 0. total to 1 or return assert message
	@Test
	public void testValidDataAndRowTotalTrue() {
		assertEquals("Wrong sum returned. It should be 1.0", 1.0, DataUtilities.calculateRowTotal(posValues2D, 0));
		assertEquals("Wrong sum returned. It should be 1.0", -1.0, DataUtilities.calculateRowTotal(negValues2D, 0));
	}
	
	// Checks number of positive or negative values added together in row 1. total to 4 or return assert message
	@Test
	public void testValidDataAndRowTotalFalse() {
		assertEquals("Wrong sum returned. It should be 4.0", 3.0, DataUtilities.calculateRowTotal(posValues2D, 1));
		assertEquals("Wrong sum returned. It should be -4.0", -3.0, DataUtilities.calculateRowTotal(negValues2D, 1));
	}

	// Returns exception because of null value
	@Test
	public void testNullNumberArrayData() {

		try {

			DataUtilities.createNumberArray(null);
			fail("No exception thrown-Expected outcome was: a thrown exception of type: InvalidParameterException");

		} catch (Exception e) {

			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	@Test
	public void testEmptyNumberArrayData() {
		assertTrue(true);
	}
	

	// checks values within created number array are the same as the data values provided
	@Test
	public void testValidNumberArrayDataTrue() {

		posArrayNumberValues = DataUtilities.createNumberArray(posArrayDoubleValues);
		negArrayNumberValues = DataUtilities.createNumberArray(negArrayDoubleValues);

		for (int i = 0; i < posArrayNumberValues.length - 1; i++) {
			assertEquals("Mismatch at Position " + i, posArrayNumberValues[i], posArrayDoubleValues[i]);
			assertEquals("Mismatch at Position " + i, negArrayNumberValues[i], negArrayDoubleValues[i]);
		}
	}
	
	// checks values within created number array are the same as the data values provided
	@Test
	public void testValidNumberArrayDataFalse() {

		posArrayNumberValues = DataUtilities.createNumberArray(posArrayDoubleValues);
		negArrayNumberValues = DataUtilities.createNumberArray(negArrayDoubleValues);

		for (int i = 0; i < posArrayNumberValues.length - 1; i++) {
			assertEquals("Mismatch at Position " + i, posArrayNumberValues[i], posArrayDoubleFalseValues[i]);
			assertEquals("Mismatch at Position " + i, negArrayNumberValues[i], negArrayDoubleFalseValues[i]);
		}
	}

	// Returns exception because of null value
	@Test
	public void testNullNumberArrayData2D() {

		try {

			DataUtilities.createNumberArray2D(null);
			fail("No exception thrown-Expected outcome was: a thrown exception of type: InvalidParameterException");

		} catch (Exception e) {

			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}
	
	@Test
	public void testEmptyNumberArrayData2D() {
		assertTrue(true);
	}

	// checks values within created 2D number array are the same as the data values provided
	@Test
	public void testValidNumberArrayData2DTrue() {
		posArrayNumberValues2D = DataUtilities.createNumberArray2D(posArrayDoubleValues2D);
		negArrayNumberValues2D = DataUtilities.createNumberArray2D(negArrayDoubleValues2D);

		for (int i = 0; i < posArrayNumberValues2D.length - 1; i++) {
			assertEquals("Mismatch at Position " + i, posArrayNumberValues2D[i][i], posArrayDoubleValues2D[i][i]);
			assertEquals("Mismatch at Position " + i, negArrayNumberValues2D[i][i], negArrayDoubleValues2D[i][i]);
		}
	}
	
	// checks values within created 2D number array are the same as the data values provided
	@Test
	public void testValidNumberArrayData2DFalse() {
		posArrayNumberValues2D = DataUtilities.createNumberArray2D(posArrayDoubleValues2D);
		negArrayNumberValues2D = DataUtilities.createNumberArray2D(negArrayDoubleValues2D);

		for (int i = 0; i < posArrayNumberValues2D.length - 1; i++) {
			assertEquals("Mismatch at Position " + i, posArrayNumberValues2D[i][i], posArrayDoubleFalseValues2D[i][i]);
			assertEquals("Mismatch at Position " + i, negArrayNumberValues2D[i][i], negArrayDoubleFalseValues2D[i][i]);
		}
	}

	// Returns exception because of null value
	@Test
	public void testNullCumulativePercentages() {

		try {

			DataUtilities.getCumulativePercentages(null);
			fail("No exception thrown-Expected outcome was: a thrown exception of type: InvalidParameterException");

		} catch (Exception e) {

			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}
	}

	// Checks percentage value is correct with the values provided.
	@Test
	public void testValidCumulativePercentagesTrue() {
		double posPercentageValue1 = (double) DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(0);
		assertEquals("Wrong percentage returned. It should be 0.4", posPercentageValue1,
				DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(0));

		double posPercentageValue2 = (double) DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(1);
		assertEquals("Wrong percentage returned. It should be 1.2", posPercentageValue2,
				DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(1));

		double posPercentageValue3 = (double) DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(2);
		assertEquals("Wrong percentage returned. It should be 1.4", posPercentageValue3,
				DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(2));
		
		double negPercentageValue1 = (double) DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(0);
		assertEquals("Wrong percentage returned. It should be -0.4", negPercentageValue1,
				DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(0));

		double negPercentageValue2 = (double) DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(1);
		assertEquals("Wrong percentage returned. It should be -1.2", negPercentageValue2,
				DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(1));

		double negPercentageValue3 = (double) DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(2);
		assertEquals("Wrong percentage returned. It should be -1.4", negPercentageValue3,
				DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(2));
	}
	
	// Checks percentage value is correct with the values provided.
	@Test
	public void testValidCumulativePercentagesFalse() {
		double posPercentageValue1 = (double) DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(0);
		assertEquals("Wrong percentage returned. It should be 0.4", posPercentageValue1,
				DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(1));

		double posPercentageValue2 = (double) DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(1);
		assertEquals("Wrong percentage returned. It should be 1.2", posPercentageValue2,
				DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(2));

		double posPercentageValue3 = (double) DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(2);
		assertEquals("Wrong percentage returned. It should be 1.4", posPercentageValue3,
				DataUtilities.getCumulativePercentages(posTestKeyedValues).getValue(0));
		
		double negPercentageValue1 = (double) DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(0);
		assertEquals("Wrong percentage returned. It should be -0.4", negPercentageValue1,
				DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(1));

		double negPercentageValue2 = (double) DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(1);
		assertEquals("Wrong percentage returned. It should be -1.2", negPercentageValue2,
				DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(2));

		double negPercentageValue3 = (double) DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(2);
		assertEquals("Wrong percentage returned. It should be -1.4", negPercentageValue3,
				DataUtilities.getCumulativePercentages(negTestKeyedValues).getValue(0));
	}
}
