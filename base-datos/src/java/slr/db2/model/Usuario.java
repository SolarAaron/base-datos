/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slr.db2.model;

import java.math.BigDecimal;

/**
 *
 * @author aaron
 */
public class Usuario{
    private static final String[] campos = {"Id", "Login", "Password", "Llave_sec"};

    private BigDecimal Id;
    private String Login, Password, Llave_sec;

    public Usuario(){
        this.Id = new BigDecimal("0");
        Login = Password = Llave_sec = "";
    }

    public Usuario(BigDecimal Id, String Login, String Password, String Llave_sec) throws Exception{
        this.Id = Id;
        this.Login = Login;
        this.Password = Password;
        this.Llave_sec = Llave_sec;
    }

    public String[] columns(){
        return campos;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal Id) {
        this.Id = Id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getLlave_sec() {
        return Llave_sec;
    }

    public void setLlave_sec(String Llave_sec) {
        this.Llave_sec = Llave_sec;
    }

    public void set(String col, Object val){
        switch(col){
            case "Id":
                setId((BigDecimal) val);
                break;
            case "Login":
                setLogin((String) val);
                break;
            case "Password":
                setPassword((String) val);
                break;
            case "Llave_sec":
                setLlave_sec((String) val);
                break;
            default:
                throw new Error("Columna desconocida");
        }
    }

    public Object get(String col){
        switch(col){
            case "Id":
                return getId();
            case "Login":
                return getLogin();
            case "Password":
                return getPassword();
            case "Llave_sec":
                return getLlave_sec();
            default:
                throw new Error("Columna desconocida");
        }
    }
}
