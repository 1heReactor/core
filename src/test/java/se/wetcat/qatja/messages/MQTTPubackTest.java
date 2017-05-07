package se.wetcat.qatja.messages;

/*
 * Copyright (C) 2017 Andreas Goransson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static se.wetcat.qatja.MQTTConstants.PUBACK;

public class MQTTPubackTest {

  private MQTTPuback msg;

  @Before
  public void setup() {
    msg = new MQTTPuback(2);
  }

  @After
  public void teardown() {
    msg = null;
  }

  @Test
  public void testType() {
    byte expected = PUBACK;
    byte actual = msg.getType();
    assertEquals(expected, actual);
  }

}