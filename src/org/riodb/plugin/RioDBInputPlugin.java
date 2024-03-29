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

	An interface to describe what every RioDBInputPlugin
	class should be made like.
	
	www.riodb.org

*/

package org.riodb.plugin;

public interface RioDBInputPlugin {

    // clear warnings
    public void clearWarnings();

    // get plugin details (name, version, author, website)
    public String describe();

    // Method for pulling next event from the data source
    public String getNextInputMessage() throws RioDBPluginException;

    // method for checking the data source awaiting event queue size.
    public int getQueueSize();

    // initialize plugin to be used as INPUT stream
    public void init(String inputParams) throws RioDBPluginException;

    // starting the data source (most use a Runnable Thread)
    public void start() throws RioDBPluginException;

    // get status of the data source
    public RioDBComponentStatus status();

    // stop the data source (most use a Runnable thread)
    public void stop() throws RioDBPluginException;

    // get type (or plugin name)
    public String type();

    // Gets plugin name and version.
    public String version();

}
