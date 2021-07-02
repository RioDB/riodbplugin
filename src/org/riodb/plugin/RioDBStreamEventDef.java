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
  
 	This class defines the field structure of a stream event. 
 	
 	A stream event is made of 2 arrays: of number and of strings
 	
 	This class details what stream fields are mapped to what
 	array elements in the event object. 
 	
 	for example:
 	consider stream of stock bids:
 		timestamp	number
 		symbol		string
 		bid			number
 		quantity	number
 		
 	The definition would explain that the event arrays are organized as
 	
 	double[3] // timestamp, bid, quantity
 	String[1] // symbol
 	
 	The 1st field of the stream (timestamp) is the first element in the
 	array of numbers. 

 	The 2nd field of the stream (symbol) is the first element in the
 	array of strings. 
 	
 	The 3rd field of the stream (bid) is the 2nd element in the
 	array of numbers.
 	
 	The 3rd field of the stream (bid) is the 2nd element in the
 	array of numbers. 
 	 
  
 */

package org.riodb.plugin;

public class RioDBStreamEventDef {

	// an array of fields (each field has a field name, type, etc)
	private RioDBStreamEventField fields[];

	// IF a numeric field is the timestamp to be used by windows,
	// what is the element of that field
	private int timestampNumericFieldId;

	// constructor
	public RioDBStreamEventDef() {
		fields = new RioDBStreamEventField[0];
		setTimestampNumericFieldId(-1);
	}

	// add fields to the definition
	public void addField(RioDBStreamEventField newField) {

		// expand array to fit a new field.
		RioDBStreamEventField newFields[] = new RioDBStreamEventField[fields.length + 1];
		for (int i = 0; i < fields.length; i++) {
			newFields[i] = fields[i];
		}
		newFields[fields.length] = newField;
		fields = newFields;
	}

	// check if definition contains field by field name.
	public boolean containsField(String fieldName) {

		if (fieldName == null || fieldName.length() == 0)
			return false;
		for (RioDBStreamEventField f : fields) {
			if (fieldName.equals(f.getName())) {
				return true;
			}
		}
		return false;
	}

	// get array of booleans, true for numeric fields, false for string fields
	public boolean[] getAllNumericFlags() {
		boolean b[] = new boolean[fields.length];
		for (int i = 0; i < fields.length; i++) {
			b[i] = fields[i].isNumeric();
		}
		return b;
	}

	// get fieldId number by name. non case-sensitive
	// field ID is the same as the index position in field array.
	// it may be different than index position in event class double[] or String[]
	// arrays
	public int getFieldId(String fieldName) {
		int id = -1;
		if (fieldName == null || fieldName.length() == 0)
			return id;
		for (int i = 0; i < fields.length; i++) {
			if (fieldName.toLowerCase().equals(fields[i].getName().toLowerCase())) {
				return i;
			}
		}
		return id;
	}

	// get fieldId by name, case sensitive.
	public int getFieldIdCaseSensitive(String fieldName) {
		int id = -1;
		if (fieldName == null || fieldName.length() == 0)
			return id;
		for (int i = 0; i < fields.length; i++) {
			if (fieldName.equals(fields[i].getName())) {
				return i;
			}
		}
		return id;
	}

	// Get list of fields in JSON format
	public String getFieldList() {
		String s = "";
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isNumeric())
				s = s + "\n{\"order\":" + String.valueOf(i) + ",\"name\":\"" + fields[i].getName()
						+ "\",\"type\":\"NUMBER\"},";
			else
				s = s + "\n{\"order\":" + String.valueOf(i) + ",\"name\":\"" + fields[i].getName()
						+ "\",\"type\":\"STRING\"},";
		}
		if (s.length() > 1)
			s = s.substring(0, s.length() - 1);
		return s;
	}

	// get field name, by id (or by index position)
	// programmer is responsible for avoiding index out of bounds.
	public String getFieldName(int index) {
		return fields[index].getName();
	}

	// get count of how many fields are numeric
	public int getNumericFieldCount() {

		int count = 0;

		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isNumeric())
				count++;
		}
		return count;
	}

	/*
	 * Get the placement of a numeric field among other numeric fields in a stream.
	 * 
	 * for example: timestamp, symbol, bid, quantity: timestamp, bid and quantity
	 * are numeric.
	 * 
	 * Counting from zero, 'quantity' is the field 3.
	 * 
	 * But among numeric fields only, 'quantity' is the field 2.
	 * 
	 * This means that in an Event (double[], string[]) quantity field is found in
	 * 
	 * float[2]
	 * 
	 * 
	 * programmer is responsible for avoiding index out of bounds.
	 */
	public int getNumericFieldIndex(int fieldId) {
		int count = 0;
		for (int i = fieldId; i >= 0; i--) {
			if (fields[i].isNumeric())
				count++;
		}
		return count - 1;
	}

	// get name of a numeric field by its index in the event double[] array
	// programmer is responsible for avoiding index out of bounds.
	public String getNumericFieldName(int numericFieldIndex) {
		int count = 0;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isNumeric()) {
				if (count == numericFieldIndex) {
					return fields[i].getName();
				}
				count++;
			}

		}
		return null;
	}

	// get the Field object by index position
	// programmer is responsible for avoiding index out of bounds.
	public RioDBStreamEventField getStreamField(int index) {
		return fields[index];
	}

	// get the array of Field[]
	public RioDBStreamEventField[] getStreamFields() {
		return fields;
	}

	// get the count of String fields
	public int getStringFieldCount() {

		int count = 0;

		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isNumeric())
				count++;
		}
		return count;
	}

	// see getNumericFieldIndex(int). Same thing but for String fields 
	// programmer is responsible for avoiding index out of bounds.
	public int getStringFieldIndex(int fieldId) {

		int count = 0;
		for (int i = fieldId; i >= 0; i--) {
			if (!fields[i].isNumeric())
				count++;
		}
		return count - 1;
	}

	// get name of String field by index position in events String[]
	// programmer is responsible for avoiding index out of bounds.
	public String getStringFieldName(int stringFieldIndex) {
		int count = 0;
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isNumeric()) {
				if (count == stringFieldIndex) {
					return fields[i].getName();
				}
				count++;
			}

		}
		return null;
	}

	// get the name of the field defined as timestamp
	public String getTimestampFieldName() {
		if (timestampNumericFieldId >= 0)
			return getNumericFieldName(timestampNumericFieldId);
		return null;
	}

	// get the index position of the timestamp field in event double[]
	public int getTimestampNumericFieldId() {
		return timestampNumericFieldId;
	}

	// check if a field is defined as number or String
	// programmer is responsible for avoiding index out of bounds.
	public boolean isNumeric(int fieldId) {
		return fields[fieldId].isNumeric();
	}

	// set what fields should be defined as a timestamp for the events
	public void setTimestampNumericFieldId(int timestampNumericFieldId) {
		this.timestampNumericFieldId = timestampNumericFieldId;
	}

	// get count of how many fields are defined in a stream event definition. 
	public int size() {
		return fields.length;
	}
}
