import java.io.Serializable;

public class Settings implements Serializable {
    private String host;
    private String username;
    private String password;
    public Settings(String host,String username,String password){
        this.host=host;
        this.username=username;
        this.password=password;
    }

    public void setHost(String host){
        this.host=host;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getHost(){
        return host;
    }
    public String getUsername(){
        return username;

    }
    public String getPassword(){
        return password;
    }
}
