package se.wetcat.qatja.messages;

/*
 * Copyright (C) 2014 Andreas Goransson
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

import se.wetcat.qatja.MQTTException;

import java.io.IOException;

/**
 * A {@link #SUBACK} Packet is sent by the Server to the Client to confirm
 * receipt and processing of a {@link #SUBSCRIBE} Packet.
 * 
 * A {@link #SUBACK} Packet contains a list of return codes, that specify the 
 * maximum QoS level that was granted in each Subscription that was requested
 * by the {@link #SUBSCRIBE}.
 * 
 * @author  Andreas Goransson
 * @version 1.0.0
 * @since   2017-05-07
 */
public class MQTTSuback extends MQTTMessage {

  /**
   * Construct a {@link #SUBACK} message from a buffer
   *
   * @param buffer
   *            The buffer
   */
  public MQTTSuback(byte[] buffer) {

    int i = 0;
    // Type (just for clarity sake we'll set it...)
    this.setType((byte) ((buffer[i++] >> 4) & 0x0F));

    // Remaining length
    int multiplier = 1;
    int len = 0;
    byte digit = 0;
    do {
      digit = buffer[i++];
      len += (digit & 127) * multiplier;
      multiplier *= 128;
    } while ((digit & 128) != 0);
    this.setRemainingLength(len);

    // Get variable header (always length 2 in SUBACK)
    variableHeader = new byte[2];
    System.arraycopy(buffer, i, variableHeader, 0, variableHeader.length);

    // Get payload
    payload = new byte[remainingLength - variableHeader.length];
    if (payload.length > 0)
      System.arraycopy(buffer, i + variableHeader.length, payload, 0, remainingLength - variableHeader.length);

    // Get package identifier
    packageIdentifier = (variableHeader[variableHeader.length - 2] >> 8 & 0xFF)
        | (variableHeader[variableHeader.length - 1] & 0xFF);
  }

  @Override
  protected byte[] generateFixedHeader() throws MQTTException, IOException {
    // Client doesn't create SUBACK
    return null;
  }

  @Override
  protected byte[] generateVariableHeader() throws MQTTException, IOException {
    // Client doesn't create SUBACK
    return null;
  }

  @Override
  protected byte[] generatePayload() throws MQTTException, IOException {
    // Client doesn't create SUBACK
    return null;
  }

}
