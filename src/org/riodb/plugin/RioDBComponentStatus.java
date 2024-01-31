/*
				RioDBPlugin

Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
 
*/

/*

	An object that encodes/decodes the status of a plugin
	
	www.riodb.org

*/

package org.riodb.plugin;

import java.util.concurrent.atomic.AtomicInteger;

public class RioDBComponentStatus {

	// static final list of status
	private static final String[] statusMap = { "STOPPED", "OK", "WARNING", "ERROR", "FATAL" };

	// static final list of status description
	private static final String[] statusDesc = { 
	        "Plugin initialized, but not started.", 
	        "Plugin Started",
			"Plugin encountered invalid data", 
			"Plugin encoutered errors internally, or with connectivity.",
			"Plugin is catastrophically non-operational. Nada."
			};

	// code (array index) of THIS RioDBPluginStatus object
	private AtomicInteger statusCode;

	// constructor. 0-3 are allowed.
	public RioDBComponentStatus() {
		this.statusCode = new AtomicInteger(0);
	}
	
	// constructor. 0-3 are allowed.
    public RioDBComponentStatus(int status) {
        this.statusCode = new AtomicInteger(status);
    }

	// setters
	public void setStopped() {
	    this.statusCode.set(0);
	}
	
	public void setOk() {
        this.statusCode.set(1);
    }
	
	public void setWarning() {
        this.statusCode.set(2);
    }
	
	public void setError() {
        this.statusCode.set(3);
    }
	
	public void setFatal() {
        this.statusCode.set(4);
    }
	

	// get status display name
	public String getStatus() {
		return statusMap[statusCode.get()];
	}

	// get status code
	public int getStatusCode() {
		return statusCode.get();
	}

	// get status description
	public String getStatusDesc() {
		return statusDesc[statusCode.get()];
	}

}
