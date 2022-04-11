package org.jfree.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import org.jfree.data.Range;
import junit.framework.TestCase;

public class RangeTest extends TestCase {
	
	private Range rangeObjectUnderTest;
	private Range rangeObjectForContainsAndConstrainTest;
	
	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTest = new Range(-1, 1);
		rangeObjectForContainsAndConstrainTest = new Range(-10, 10);
	}

	@After
	protected void tearDown() throws Exception {
		
	}

	@Test
	public void testCentralValueShouldBeZero() {
		assertEquals("The central value of -1 and 1 should be 0", 
				0, rangeObjectUnderTest.getCentralValue(), 0.00000001d);
	}
	
	//Intersects tests
	@Test
	public void testIntersectsWithValueExactlyTheRange() {
		assertTrue("These values are exactly the range and should be intersecting", 
				rangeObjectForContainsAndConstrainTest.intersects(-10, 10));
	}
	
	@Test
	public void testIntersectsWithValueLowerAndMiddleOfTheRange() {
		assertTrue("These values are the lower boundary and middle of the range and should be intersecting", 
				rangeObjectForContainsAndConstrainTest.intersects(-10, 0));
	}
	
	@Test
	public void testIntersectsWithValueMiddleAndUpperOfTheRange() {
		assertTrue("These values are the middle and the upper boundary of the range and should be intersecting", 
				rangeObjectForContainsAndConstrainTest.intersects(0, 10));
	}
	
	@Test
	public void testIntersectsWithValueBelowAndBelowOfTheRange() {
		assertFalse("These values are both below the range and should not be intersecting", 
				rangeObjectForContainsAndConstrainTest.intersects(-20, -15));
	}
	
	@Test
	public void testIntersectsWithValueAboveAndAboveOfTheRange() {
		assertFalse("These values are both above the range and should not be intersecting", 
				rangeObjectForContainsAndConstrainTest.intersects(15, 20));
	}
	
	@Test
	public void testIntersectsWithValueBelowAndMiddleOfTheRange() {
		assertTrue("These values are below and in the middle of the range and should be intersecting", 
				rangeObjectForContainsAndConstrainTest.intersects(-20, 0));
	}
	
	@Test
	public void testIntersectsWithValueMiddleAndAboveOfTheRange() {
		assertTrue("These values are below and in the middle of the range and should be intersecting", 
				rangeObjectForContainsAndConstrainTest.intersects(0, 20));
	}
	
	@Test
	public void testIntersectsWithValueBelowAndAboveOfTheRange() {
		assertTrue("These values are below and above the range and should be intersecting", 
				rangeObjectForContainsAndConstrainTest.intersects(-20, 20));
	}
	
	@Test
	public void testIntersectsWithValuesBothMiddleOfTheRange() {
		assertTrue("These values are in the middle of the range and should be intersecting", 
				rangeObjectForContainsAndConstrainTest.intersects(0, 0));
	}
	
	@Test
	public void testIntersectsWithUpperHigherThanLower() {
		assertFalse("These values are asking about an invalid range and should not be intersecting", 
				rangeObjectForContainsAndConstrainTest.intersects(10, 0));
	}
	
	//Contains tests
	@Test
	public void testContainsValueBelowTheRange() {
		assertFalse("This value should be below the range and therefore not contained by the range", 
				rangeObjectForContainsAndConstrainTest.contains(-100));
	}
	
	@Test
	public void testContainsValueOnTheLowerBoundary() {
		assertTrue("This value should be on the lower boundary of the range and therefore presumed to be contained by the range", 
				rangeObjectForContainsAndConstrainTest.contains(-10));
	}
	
	@Test
	public void testContainsValueInTheMiddleOfTheRange() {
		assertTrue("This value should be in the middle of the range and therefore contained by the range", 
				rangeObjectForContainsAndConstrainTest.contains(0.9));
	}
	
	@Test
	public void testContainsValueOnTheUpperBoundary() {
		assertTrue("This value should be on the upper boundary of the range and therefore presumed to be contained by the range", 
				rangeObjectForContainsAndConstrainTest.contains(10));
	}
	
	@Test
	public void testContainsValueAboveTheRange() {
		assertFalse("This value should be above the range and therefore not contained by the range", 
				rangeObjectForContainsAndConstrainTest.contains(100));
	}
	
	//revise
	//@Test
	//public void testContainsPassInNull() {
		//assertFalse("This value should be below the range and therefore not contained by the range", 
				//rangeObjectForContainsAndConstrainTest.contains(-100));
	//}
	
	//getUpperBound tests
	@Test
	public void testGetUpperBoundWhereBothUpperAndLowerBoundariesAreNegative() {
		Range r1 = new Range(-100, -50);
		assertEquals("The expected upper bound is -50", -50, r1.getUpperBound());
	}
		
	@Test
	public void testGetUpperBoundWhereBothUpperAndLowerBoundariesArePositive() {
		Range r1 = new Range(50, 100);
		assertEquals("The expected upper bound is 100", 100, r1.getUpperBound());
	}
		
	@Test
	public void testGetUpperBoundWhereOneValueIsNegativeAndOneIsPositive() {
		Range r1 = new Range(-50, 50);
		assertEquals("The expected upper bound is 50", 50, r1.getUpperBound());
	}
		
	@Test
	public void testGetUpperBoundWhereBothUpperAndLowerBoundariesAreTheSameAndPositive() {
		Range r1 = new Range(-100, -100);
		assertEquals("The expected upper bound is -100", -100.0, r1.getUpperBound());
	}
		
	@Test
	public void testGetUpperBoundWhereBothUpperAndLowerBoundariesAreTheSameAndNegative() {
		Range r1 = new Range(100, 100);
		assertEquals("The expected upper bound is 100", 100.0, r1.getUpperBound());
	}
	
	//shift tests
	@Test
	public void testShiftNullRange() {

		try {

			Range r1 = null;
			Range.shift(r1, 5, false);
			fail("InvalidParameterException should be thrown as we cannot multiply non existent boundaries");

		} catch (Exception e) {

			assertTrue("Incorrect exception type thrown", e.getClass().equals(NullPointerException.class));
		}
	}
	
	@Test
	public void testShiftByPositiveDeltaAndTrue() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(0, 200);
		assertEquals("Should shift by 100", r2, Range.shift(r1, 100, true));
	}
	
	@Test
	public void testShiftByNegativeDeltaAndTrue() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-200, 0);
		assertEquals("Should shift by 100", r2, Range.shift(r1, -100, true));
	}
	
	@Test
	public void testShiftByPositiveDeltaOverLowerThresholdAndTrue() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(50, 250);
		assertEquals("Should shift by 150", r2, Range.shift(r1, 150, true));
	}
	
	@Test
	public void testShiftByPositiveDeltaOverLowerThresholdAndFalse() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(0, 250);
		assertEquals("Should shift by 150 for upper and up to 0 for lower", r2, Range.shift(r1, 150, false));
	}
	
	public void testShiftByPositiveDeltaAndFalse() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(0, 200);
		assertEquals("Should shift by 100", r2, Range.shift(r1, 100, false));
	}
	
	public void testShiftByNegativeDeltaAndFalse() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-200, 0);
		assertEquals("Should shift by 100", r2, Range.shift(r1, -100, false));
	}
	
	@Test
	public void testShiftByNegativeDeltaOverUpperThresholdAndTrue() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-250, -50);
		assertEquals("Should shift by 150", r2, Range.shift(r1, -150, true));
	}
	
	@Test
	public void testShiftByNegativeDeltaOverUpperThresholdAndFalse() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-250, 0);
		assertEquals("Should shift by 150 for lower and up to 0 for upper", r2, Range.shift(r1, -150, false));
	}
	
	@Test
	public void testShiftBy0DeltaAndFalse() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-100, 100);
		assertEquals("Should not shift", r2, Range.shift(r1, 0, false));
	}
	
	@Test
	public void testShiftBy0DeltaAndTrue() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-100, 100);
		assertEquals("Should not shift", r2, Range.shift(r1, 0, true));
	}
	
	
	@Test
	public void testShiftBy0DeltaAndFalseFor0Range() {
		Range r1 = new Range(0, 0);
		Range r2 = new Range(100, 100);
		assertEquals("Should shift by 100", r2, Range.shift(r1, 100, false));
	}
	
	@Test
	public void testShiftBy0DeltaAndTrueFor0Range() {
		Range r1 = new Range(0, 0);
		Range r2 = new Range(100, 100);
		assertEquals("Should shift by 100", r2, Range.shift(r1, 100, true));
	}
	
	@Test
	public void testShiftBy0DeltaAndNoThirdParamForRange0() {
		Range r1 = new Range(0, 0);
		Range r2 = new Range(100, 100);
		assertEquals("Should shift by 100", r2, Range.shift(r1, 100));
	}
	
	//expand tests
	@Test
	public void testExpandNullRange() {

		try {

			Range r1 = null;
			Range.expand(r1, 0.25, 0.25);
			fail("IllegalArgumentException should be thrown as we cannot multiply non existent boundaries");

		} catch (Exception e) {

			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testExpandBy0LowerAnd0Upper() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-100, 100);
		assertEquals("0% of a number is just 0", r2, Range.expand(r1, 0, 0));
	}
	
	@Test
	public void testExpandBy0LowerAndPositiveUpper() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-100, 300);
		assertEquals("0% of a number is just 0 and 100 + 200 = 300", r2, Range.expand(r1, 0, 1));
	}
	
	@Test
	public void testExpandByPositiveLowerAnd0Upper() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-300, 100);
		assertEquals("0% of a number is just 0 and -100 + -200 = -300", r2, Range.expand(r1, 1, 0));
	}
	
	@Test
	public void testExpandByPositiveLowerAndPositiveUpper() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-300, 300);
		assertEquals("100 + 200 = 300 and -100 + -200 = -300", r2, Range.expand(r1, 1, 1));
	}
	
	@Test
	public void testExpandByNegativeLowerAndNegativeUpperTooBig() {
		try {
			Range r1 = new Range(-100, 100);
			Range.expand(r1, -0.25, -1);
			fail("We are multiplying by negatives so this would make lower higher than upper");

		} catch (Exception e) {

			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testExpandByNegativeLowerAndNegativeUpper() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-50, 50);
		assertEquals("both should decrease by 25%", r2, Range.expand(r1, -0.25, -0.25));
	}
	
	@Test
	public void testExpandByNegativeLowerAndPositiveUpper() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-50, 300);
		assertEquals("lower should decrease by 25% and upper increase by 100%", r2, Range.expand(r1, -0.25, 1));
	}
	
	@Test
	public void testExpandByPositiveLowerAndNegativeUpper() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-300, 50);
		assertEquals("lower should increase by 100% and upper decrease by 25%", r2, Range.expand(r1, 1, -0.25));
	}
	
	
	//expandToInclude tests
	@Test
	public void testExpandToIncludeNullRange() {
		Range r1 = null;
		Range r2 = new Range(-100, -100);
		assertEquals("The expected range is just the value", r2, Range.expandToInclude(r1, -100));
	}
	
	@Test
	public void testExpandToIncludeUpperBoundary() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-100, 100);
		assertEquals("The expected range is the same", r2, Range.expandToInclude(r1, 100));
	}
	
	@Test
	public void testExpandToIncludeLowerBoundary() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-100, 100);
		assertEquals("The expected range is the same", r2, Range.expandToInclude(r1, -100));
	}
	
	@Test
	public void testExpandToIncludeBelowRange() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-200, 100);
		assertEquals("The expected range is expanded to -200", r2, Range.expandToInclude(r1, -200));
	}
	
	@Test
	public void testExpandToIncludeAboveRange() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-100, 200);
		assertEquals("The expected range is expanded to 200", r2, Range.expandToInclude(r1, 200));
	}
	
	@Test
	public void testExpandToIncludeMiddleOfRange() {
		Range r1 = new Range(-100, 100);
		Range r2 = new Range(-100, 100);
		assertEquals("The expected range is the same", r2, Range.expandToInclude(r1, 0));
	}
	
	
	
	//getCentralValue tests
	@Test
	public void testGetCentralValueWhereBothUpperAndLowerBoundariesAreNegative() {
		Range r1 = new Range(-100, -50);
		assertEquals("The expected central value is -75", -75.0, r1.getCentralValue());
	}
				
	@Test
	public void testGetCentralValueWhereBothUpperAndLowerBoundariesArePositive() {
		Range r1 = new Range(50, 100);
		assertEquals("The expected central value is 75", 75.0, r1.getCentralValue());
	}
				
	@Test
	public void testGetCentralValueWhereOneValueIsNegativeAndOneIsPositive() {
		Range r1 = new Range(-50, 50);
		assertEquals("The expected central value is 0", 0.0, r1.getCentralValue());
	}
				
	@Test
	public void testGetCentralValueWhereBothUpperAndLowerBoundariesAreTheSameAndNegative() {
		Range r1 = new Range(-100, -100);
		assertEquals("The expected central value is -100", -100.0, r1.getCentralValue());
	}
				
	@Test
	public void testGetCentralValueWhereBothUpperAndLowerBoundariesAreTheSameAndPositive() {
		Range r1 = new Range(100, 100);
		assertEquals("The expected central value is 100", 100.0, r1.getCentralValue());
	}		

	//Constrain tests
	@Test
	public void testConstrainValueBelowTheRange() {
		assertEquals("This value should be below the range and therefore the lower boundary should be returned", 
				-10.0, rangeObjectForContainsAndConstrainTest.constrain(-100));
	}
		
	@Test
	public void testConstrainValueOnTheLowerBoundary() {
		assertEquals("This value should be on the lower boundary of the range and therefore this value should be returned", 
				-10.0, rangeObjectForContainsAndConstrainTest.constrain(-10));
	}
		
	@Test
	public void testConstrainValueInTheMiddleOfTheRange() {
		assertEquals("This value should be in the middle of the range and therefore this value should be returned", 
				0.0, rangeObjectForContainsAndConstrainTest.constrain(0));
	}
		
	@Test
	public void testConstrainValueOnTheUpperBoundary() {
		assertEquals("This value should be on the upper boundary of the range and therefore this value should be returned", 
				10.0, rangeObjectForContainsAndConstrainTest.constrain(10));
	}
		
	@Test
	public void testConstrainValueAboveTheRange() {
		assertEquals("This value should be above the range and therefore the upper boundary should be returned", 
				10.0, rangeObjectForContainsAndConstrainTest.constrain(100));
	}
	
	//combine tests
	@Test
	public void testCombineWhereBothRangesAreEqual() {
		Range r1 = new Range(-1, 1);
		Range r2 = new Range(-1, 1);
		Range r3 = new Range(-1, 1);
		assertEquals("The expected Range should be -1 to 1", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRange1LowerAndUpperBoundariesAreLowerThanRange2LowerAndUpper() {
		Range r1 = new Range(-1, 1);
		Range r2 = new Range(3, 5);
		Range r3 = new Range(-1, 5);
		assertEquals("The expected Range should be -1 to 5", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRange2LowerAndUpperBoundariesAreLowerThanRange1LowerAndUpper() {
		Range r1 = new Range(3, 5);
		Range r2 = new Range(-1, 1);
		Range r3 = new Range(-1, 5);
		assertEquals("The expected Range should be -1 to 5", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRange1LowerBoundaryIsLowerThanRange2LowerBoundaryButRange1UpperHigherThanRange2Lower() {
		Range r1 = new Range(-6, -2);
		Range r2 = new Range(-4, 4);
		Range r3 = new Range(-6, 4);
		assertEquals("The expected Range should be -6 to 4", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRange2LowerBoundaryIsLowerThanRange1LowerBoundaryButRange2UpperHigherThanRange1Lower() {
		Range r1 = new Range(-4, 4);
		Range r2 = new Range(-6, -2);
		Range r3 = new Range(-6, 4);
		assertEquals("The expected Range should be -6 to 4", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRange2IsSubsetOfRange1() {
		Range r1 = new Range(-5, 5);
		Range r2 = new Range(-4, 4);
		Range r3 = new Range(-5, 5);
		assertEquals("The expected Range should be -5 to 5", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRange1IsSubsetOfRange2() {
		Range r1 = new Range(-4, 4);
		Range r2 = new Range(-5, 5);
		Range r3 = new Range(-5, 5);
		assertEquals("The expected Range should be -5 to 5", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRange1UpperIsTheSameAsRange2Lower() {
		Range r1 = new Range(-3, 3);
		Range r2 = new Range(3, 9);
		Range r3 = new Range(-3, 9);
		assertEquals("The expected Range should be -3 to 9", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRange2UpperIsTheSameAsRange1Lower() {
		Range r1 = new Range(3, 9);
		Range r2 = new Range(-3, 3);
		Range r3 = new Range(-3, 9);
		assertEquals("The expected Range should be -3 to 9", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRange1IsNull() {
		Range r1 = null;
		Range r2 = new Range(-10, 10);
		Range r3 = new Range(-10, 10);
		assertEquals("The expected Range should be -10 to 10", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRange2IsNull() {
		Range r1 = new Range(-10, 10);
		Range r2 = null;
		Range r3 = new Range(-10, 10);
		assertEquals("The expected Range should be -10 to 10", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWhereRangesAreNull() {
		Range r1 = null;
		Range r2 = null;
		Range r3 = null;
		assertEquals("The expected Range should be presumably null", r3, Range.combine(r1, r2));
	}
	
	@Test
	public void testGetLength() {
		
		Range r1 = new Range(2, 2);
		assertEquals("getLength: Did not return the expected output", 0.0, r1.getLength());
		
		Range r2 = new Range(4, 9);
		assertEquals("getLength: Did not return the expected output", 5.0, r2.getLength());
		
		Range r3 = new Range(-99, -99);
		assertEquals("getLength: Did not return the expected output", 0.0, r3.getLength());
		
		Range r4 = new Range(-11, -4);
		assertEquals("getLength: Did not return the expected output", 7.0, r4.getLength());
		
		Range r5 = new Range(-5, 3);
		assertEquals("getLength: Did not return the expected output", 8.0, r5.getLength());
	}

}
