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

package org.riodb.plugin;

import java.util.LinkedList;

public interface RioDBParserPlugin {

    // clear warnings
    public void clearWarnings();

    // get plugin details (name, version, author, website)
    public String describe();

    // initializer
    public void init(String params, int fieldCount) throws RioDBPluginException;

    // Returns parsed fields of multiple records as list of arrays of strings.
    public LinkedList<String[]> parse(String batch) throws RioDBPluginException;

    // get status of the data source
    public RioDBComponentStatus status();

    // Gets plugin type
    public String type();

    // Gets plugin name and version.
    public String version();
}
