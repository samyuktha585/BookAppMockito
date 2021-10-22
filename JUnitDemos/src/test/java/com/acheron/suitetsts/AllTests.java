package com.acheron.suitetsts;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.IncludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@SelectPackages(value = {"com.acheron.newtests"})//select all classes in same and subpackage
@IncludePackages(value= {"com.acheron.newtests.check"})//only test the classes in tis package
//@ExcludePackages(value = {"com.acheron.newtests.check"})//only this package will execute

@RunWith(JUnitPlatform.class)
class AllTests {

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
