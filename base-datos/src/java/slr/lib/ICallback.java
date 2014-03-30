/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slr.lib;

import java.util.ArrayList;

/**
 *
 * @author aaron
 */
public abstract class ICallback<T> {
    public abstract void exec(T arg);

    public ArrayList<Object> data;

    public ICallback(){
        data = new ArrayList<>();
    }
}
