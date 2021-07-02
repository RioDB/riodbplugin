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

	Interface describing what an Output plugin should be made like

*/

package org.riodb.plugin;

public interface RioDBOutput {

	// get output type (or plugin name)
	public String getType();

	// initialize plugin since it can't be done in constructor
	public void init(String outputParams, String[] columnHeaders) throws RioDBPluginException;

	// Post an array of selected columns to the output plugin
	public void post(String[] columns);

	// get the plugin status
	public RioDBPluginStatus status();

}
