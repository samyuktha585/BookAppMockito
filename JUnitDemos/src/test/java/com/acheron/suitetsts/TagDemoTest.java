package com.acheron.suitetsts;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
@RunWith(JUnitPlatform.class)
@SelectPackages({"com.acheron"})
@IncludeTags(value = {"check","greeter","greet"})
class TagDemoTest {


}
