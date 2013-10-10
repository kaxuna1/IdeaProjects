import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        int k=0;
        int[] mas;
         String[] Smas={"kaxa","salome", "irma"}; //masivze literalis minicheba
         int[] Imas=new int[100];//gansazgvruli zomis masivi Int tipis cvladebis shesanaxad

        mas = new int[]{100, 200};
        for (int i = 0; i<Imas.length;i++) {
                if(i<Smas.length){
                System.out.println(Smas[i]);
                }
                Imas[i]=(int)(Math.random()*i);
        }
        for(int i=0;i<Imas.length;i++){
            System.out.println(Imas[i]);
        }
    }
}
