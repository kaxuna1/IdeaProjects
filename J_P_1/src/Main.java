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
        int[] IMas={1,2,3,4,5};              //მასივის დეკლარაცია ლიტერალის საშუალებით
        int[] IMas2=new int[5];          //ცარიელი 5 ელემენტიანი მასივის დეკლარაცია
        System.arraycopy(IMas,0,IMas2,0,5);       //1 მასივის ელემენტების გადაკოპირება მეორე მასივში
        System.out.println(IMas2[2]);               //ეკრანზე გამოტანა
        pr(IMas2);
        pr(IMas2);
    }
    static void pr(int[] k){         //მასივი გადაეცემა პარამეტრად       //ფუნქცია უნდა იყოს სტატიკური
                                                                       //რათა შევძლოთ გამოძახება...
                  for(int i=0;i<k.length;i++){

                      System.out.println(k[i]);
                      k[i]=i;      //მასივი გადმოეცა მეთოდს მიმთითებლით ამიტომაც
                                   //აქ შეტანილი ცვლილება გადაეცემა ინახება იმ ობიექტში,
                                   //რომელიც გადმოვეცით ამ მეთოდს...0
                  }

    }
}
