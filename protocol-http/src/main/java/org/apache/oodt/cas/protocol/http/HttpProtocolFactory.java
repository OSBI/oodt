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
package org.apache.oodt.cas.protocol.http;

//JDK imports
import java.util.logging.Level;
import java.util.logging.Logger;

//OODT imports
import org.apache.oodt.cas.protocol.ProtocolFactory;

/**
 * Factory for creating {@link HttpProtocol}s
 * 
 * @author bfoster
 * @version $Revision$
 */
public class HttpProtocolFactory implements ProtocolFactory {

	private static final Logger LOG = Logger.getLogger(HttpProtocolFactory.class.getName());
	
    public HttpProtocol newInstance() {
        try {
            return new HttpProtocol();
        } catch (Exception e) {
        	LOG.log(Level.SEVERE, "Failed to create HttpProtocol : " + e.getMessage(), e);
            return null;
        }
    }

	public String getSchema() {
		return "http";
	}
}