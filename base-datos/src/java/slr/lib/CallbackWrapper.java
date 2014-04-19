/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slr.lib;

import java.util.TreeMap;

/**
 *
 * @author aaron
 */
public abstract class CallbackWrapper<T> {
    public abstract void exec(T arg);

    public TreeMap<String, Object> data;

    public CallbackWrapper(){
        data = new TreeMap<>();
    }
}
