package org.jfree.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
