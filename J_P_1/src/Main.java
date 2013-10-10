import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
    dog x=new dog("nagazi","chiko",100);
        x.cick();
        x.cick("stick");
        x.cick("gun");
        x.cick("stick");
        ArrayList<dog> dogList;
        dogList = new ArrayList<dog>();
        dogList.add(x);
        dogList.add(new dog("avcharka", "lesi", 140));
        dogList.get(1).cick();
        dogList.get(1).showHealth();
    }
}
