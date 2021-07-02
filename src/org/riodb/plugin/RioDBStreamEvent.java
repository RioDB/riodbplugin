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

/*
 *   riodb.co
 *   
 *   This object represents an event after split into fields. 
 *   Numeric fields go into the double array
 *   Text fields go into the String array
 *   Timestamp is treated as numeric, in the double array. 
 *   
 */

/*
  
 	This class contains data of a stream event. 
 	It's an array of numbers, and an array of strings. 
 	
 	All data fields of a stream event are stored into these 2 arrays
 	as defined in the RioDBStreamEventDef  
  
 */

public class RioDBStreamEvent {

	private double doubleFields[];
	private String stringFields[];

	// constructor with explicit array sizes
	public RioDBStreamEvent(int doubleFieldCount, int stringFieldCount) {
		doubleFields = new double[doubleFieldCount];
		stringFields = new String[stringFieldCount];
	}

	// constructor with the definition object
	public RioDBStreamEvent(RioDBStreamEventDef eventDef) {
		doubleFields = new double[eventDef.getNumericFieldCount()];
		stringFields = new String[eventDef.getStringFieldCount()];
	}

	// get double at index
	// programmer is responsible for avoiding index out of bounds.
	public double getDouble(int index) {
		return doubleFields[index];
	}

	// get string at index
	// programmer is responsible for avoiding index out of bounds.
	public String getString(int index) {
		return stringFields[index];
	}

	// set double at index
	// programmer is responsible for avoiding index out of bounds.
	public void set(int index, double value) {
		doubleFields[index] = value;
	}

	// set string at index
	// programmer is responsible for avoiding index out of bounds.
	public void set(int index, String value) {
		stringFields[index] = value;
	}

	// get double fields[] length
	public int getDoubleFieldsCount() {
		return doubleFields.length;
	}

	// get double fields[] length
	public int getStringFieldsCount() {
		return stringFields.length;
	}

}
