/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slr.db2;

import java.util.TreeMap;

/**
 *
 * @author T201
 */
public class Pod {
    private TreeMap<String, String> data;
    
    public static Pod cloneObject(Pod obj){
        Pod res = new Pod(obj);
        return res;
    }
    
    public Pod(String[] args){
        data = new TreeMap<>();
        for(String i: args){
            data.put(i, "");
        }
    }
    
    protected Pod(Pod other){
        data = new TreeMap<>();
        for(String i: other.data.keySet()){
            data.put(i, "");
        }
    }
    
    public String get(String fld){
        return data.get(fld);
    }
    
    public void set(String fld, String dta) throws Exception{
        if(data.containsKey(fld)){
            data.put(fld, dta);
        } else {
            throw new Exception("No existe ese campo");
        }
    }
}
