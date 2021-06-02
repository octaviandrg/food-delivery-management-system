package Data_Layer;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private static final AtomicInteger count = new AtomicInteger(0);
    private String user;
    private String pass;
    private Integer id;

    public Account(String user, String pass){
        id = count.incrementAndGet();
        this.user = user;
        this.pass = pass;
    }

    public int hashCode(){
        return  Objects.hash(user,pass,id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", id=" + id +
                '}';
    }

    public String getUser() {
        return user;
    }


    public String getPass() {
        return pass;
    }


    public Integer getId() {
        return id;
    }

}
