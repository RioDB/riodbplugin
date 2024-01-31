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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class PluginUtils {

    /*
     * function to test if a string is numeric.
     */
    public static boolean isNumber(String s) {
        if (s == null) {
            return false;
        }
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /*
     * function to test if a string is a positive integer. Useful to validate port
     * number, etc
     */
    public static boolean isPositiveInteger(String s) {
        if (s == null)
            return false;
        try {
            if (Integer.parseInt(s) > 0) {
                return true;
            }
            return false;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /*
     * getParameter
     *
     * function to obtain a parameter value from an array of words For a parameter
     * key param[i], the value is param[i+1]
     */
    public static String getParameter(String params[], String key) {
        for (int i = 0; i < params.length; i++) {
            if (key.equals(params[i].toLowerCase()) && i < params.length - 1) {
                return params[i + 1];
            }
        }
        return null;
    }

    /*
     * hasParameter
     * Function to check if an array of words contains a parameter name.
     */
    public static boolean hasParameter(String params[], String key) {
        for (String s : params) {
            if (s.equals(key))
                return true;
        }
        return false;
    }

    
    /*
     * inputStreamToString
     * 
     * converts input stream to a string
     * Many plugins need this,so it's here once so that it doesn't have to be re-written everywhere
     * 
     */
    public String inputStreamToString(InputStream inputStream) throws RioDBPluginException{

        String response = "";
        StringBuilder strBuilder = new StringBuilder();
        Reader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())));
        int c = 0;
        try {
            while ((c = reader.read()) != -1) {
                strBuilder.append((char) c);
            }
            response = strBuilder.toString();
        } catch (IOException e) {
            throw new RioDBPluginException("IOException while converting InputStream to String");
        }

        return response;

    }
    

}
