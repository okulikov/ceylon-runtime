/*
 * Copyright 2012 Red Hat inc. and third party contributors as noted
 * by the author tags.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.ceylon.test.modules.interop.test;

import java.util.List;

import ceylon.modules.spi.Argument;
import ceylon.modules.spi.Constants;
import org.jboss.ceylon.test.modules.ModulesTest;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Aether enabled tests.
 *
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class AetherInteropTestCase extends ModulesTest {
    // Note: for this test to work, you need JBoss VFS and its dependencies in your Maven repository
    // See: io.xov.yalp.module.java for more details

    @Test
    @Ignore // TODO -- add all Aether CMR deps as modules
    public void testMultiJarUsage() throws Throwable {
        JavaArchive module = ShrinkWrap.create(JavaArchive.class, "io.xov.yalp-11.0.2.Final.car");
        module.addClasses(io.xov.yalp.module.class, io.xov.yalp.run.class);
        testArchive(module);
    }

    @Override
    protected void execute(String module, List<String> extra) throws Throwable {
        extra.add(Constants.CEYLON_ARGUMENT_PREFIX + Argument.REPOSITORY.toString());
        extra.add("aether"); // enable Aether repository
        super.execute(module, extra);
    }
}
