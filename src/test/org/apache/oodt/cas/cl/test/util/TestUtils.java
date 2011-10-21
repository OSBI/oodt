/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.oodt.cas.cl.test.util;

//JDK imports
import java.util.Collections;

//OODT imports
import org.apache.oodt.cas.cl.action.CmdLineAction;
import org.apache.oodt.cas.cl.option.ActionCmdLineOption;
import org.apache.oodt.cas.cl.option.AdvancedCmdLineOption;
import org.apache.oodt.cas.cl.option.CmdLineOption;
import org.apache.oodt.cas.cl.option.CmdLineOptionInstance;
import org.apache.oodt.cas.cl.option.GroupCmdLineOption;
import org.apache.oodt.cas.cl.option.SimpleCmdLineOption;
import org.apache.oodt.cas.cl.option.require.ActionDependency;
import org.apache.oodt.cas.cl.option.require.RequirementRule;
import org.apache.oodt.cas.cl.option.require.StdRequirementRule;
import org.apache.oodt.cas.cl.option.require.RequirementRule.Relation;
import org.apache.oodt.cas.cl.option.validator.CmdLineOptionValidator;

//Google imports
import com.google.common.collect.Lists;

//JUnit imports
import junit.framework.TestCase;

/**
 * Base Test case for CAS-CL unit tests.
 *
 * @author bfoster (Brian Foster)
 */
public class TestUtils {

	public static CmdLineAction createAction(String name) {
		return new CmdLineAction(name, "This is an action description") {

			@Override
			public void execute() {
				// do nothing
			}

		};
	}

	public static GroupCmdLineOption createGroupOption(String longName,
			boolean required) {
		GroupCmdLineOption option = new GroupCmdLineOption();
		option.setLongOption(longName);
		option.setShortOption(longName);
		option.setRequired(required);
		return option;
	}

	public static SimpleCmdLineOption createSimpleOption(String longName,
			boolean required) {
		return createSimpleOption(longName, longName, required);
	}

	public static SimpleCmdLineOption createSimpleOption(String shortName,
			String longName, boolean required) {
		SimpleCmdLineOption option = new SimpleCmdLineOption();
		option.setShortOption(shortName);
		option.setLongOption(longName);
		option.setRequired(required);
		return option;
	}

	public static SimpleCmdLineOption createSimpleOption(String longName,
			RequirementRule rule) {
		return createSimpleOption(longName, longName, rule);
	}

	public static SimpleCmdLineOption createSimpleOption(String shortName,
			String longName, RequirementRule rule) {
		SimpleCmdLineOption option = new SimpleCmdLineOption();
		option.setShortOption(shortName);
		option.setLongOption(longName);
		option.setRequirementRules(Collections.singletonList(rule));
		return option;
	}

	public static AdvancedCmdLineOption createValidationOption(String longName, CmdLineOptionValidator... validators) {
		AdvancedCmdLineOption option = new AdvancedCmdLineOption();
		option.setLongOption(longName);
		option.setShortOption(longName);
		option.setValidators(Lists.newArrayList(validators));
		return option;
	}

	public static ActionCmdLineOption createActionOption(String longName) {
		ActionCmdLineOption option = new ActionCmdLineOption();
		option.setLongOption(longName);
		option.setShortOption(longName);
		return option;
	}

	public static CmdLineOptionInstance createOptionInstance(
			CmdLineOption option, String... values) {
		return new CmdLineOptionInstance(option, Lists.newArrayList(values));
	}

	public static RequirementRule createRequiredRequirementRule(
			CmdLineAction action) {
		StdRequirementRule rule = new StdRequirementRule();
		rule.addActionDependency(new ActionDependency(action.getName(),
				Relation.REQUIRED));
		return rule;
	}

	public static RequirementRule createOptionalRequirementRule(
			CmdLineAction action) {
		StdRequirementRule rule = new StdRequirementRule();
		rule.addActionDependency(new ActionDependency(action.getName(),
				Relation.OPTIONAL));
		return rule;
	}
}
