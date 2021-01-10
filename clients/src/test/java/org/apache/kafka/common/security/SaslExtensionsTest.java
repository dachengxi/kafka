/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.common.security;

import org.apache.kafka.common.security.auth.SaslExtensions;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

public class SaslExtensionsTest {
    Map<String, String> map;

    @Before
    public void setUp() {
        this.map = new HashMap<>();
        this.map.put("what", "42");
        this.map.put("who", "me");
    }

    @Test
    public void testReturnedMapIsImmutable() {
        SaslExtensions extensions = new SaslExtensions(this.map);
        assertThrows(UnsupportedOperationException.class, () -> extensions.map().put("hello", "test"));
    }

    @Test
    public void testCannotAddValueToMapReferenceAndGetFromExtensions() {
        SaslExtensions extensions = new SaslExtensions(this.map);

        assertNull(extensions.map().get("hello"));
        this.map.put("hello", "42");
        assertNull(extensions.map().get("hello"));
    }
}
