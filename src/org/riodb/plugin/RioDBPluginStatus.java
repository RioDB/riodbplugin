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

public class RioDBPluginStatus {

	// static final list of status
	private static final String[] statusMap = { "IDLE", "READY", "WARNING", "ERROR" };

	// static final list of status description
	private static final String[] statusDesc = { "Plugin initialized, but not started.", "Plugin ready",
			"Plugin encountered a non-fatal error", "Plugin stopped due to fatal error" };

	// code (array index) of THIS RioDBPluginStatus object
	private int statusCode;

	// constructor. 0-3 are allowed.
	public RioDBPluginStatus(int statusCode) {
		this.statusCode = statusCode;
		if(this.statusCode < 0) {
			this.statusCode = 0;
		} else if(this.statusCode >= statusMap.length) {
			this.statusCode = statusMap.length -1;
		}
	}

	// constructor. String must exist in statusMap
	public RioDBPluginStatus(String status) throws RioDBPluginException {
		if (status != null) {
			for (int i = 0; i < statusMap.length; i++) {
				if(statusMap[i].equals(status)) {
					this.statusCode = i;
					break;
				}
			}
		}
		throw new RioDBPluginException(
				"Attempted to set a plugin status to an invalid plugin status name: " + status);
	}

	// get status display name
	public String getStatus() {
		return statusMap[statusCode];
	}

	// get status code
	public int getStatusCode() {
		return statusCode;
	}

	// get status description
	public String getStatusDesc() {
		return statusDesc[statusCode];
	}

}
