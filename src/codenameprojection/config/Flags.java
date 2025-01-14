/*
 * The MIT License
 *
 * Copyright 2020 Elias.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package codenameprojection.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonnelafin
 */
public class Flags {
    public static boolean soundEnabled = true;
    public static boolean secure = true;
    
    public static void loadAndSet(){
        try {
            HashMap<String, Object> loaded = ConfigReader.load();
            System.out.println(loaded);
            set(loaded);
        } catch (IOException iOException) {
            System.out.println("COULD NOT READ CONFIG FILE AT " + ConfigReader.configPath);
        }
    }
    static void set(HashMap<String, Object> loaded){
        loaded.keySet().forEach((k) -> {
            switch(k){
                case "soundEnabled":
                    soundEnabled = (boolean) loaded.get(k);
                    break;
                case "secure":
                    secure = (boolean) loaded.get(k);
                    break;
                default:
                    Logger.getGlobal().log(Level.WARNING, "No flag for \"{0}\"", k);
            }
        });
    }
    public static void main(String[] args) {
        loadAndSet();
    }
}
