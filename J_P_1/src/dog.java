

/**
 * Created with IntelliJ IDEA.
 * User: kakha
 * Date: 10/10/13
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class dog extends animal implements Pet {
    dog(String type,String name,int health){
        this.health=health;
        this.name=name;
        this.type=type;

    }
    @Override
    void setName(String name) {
     this.name=name;
    }

    @Override
    void setHealth(int health) {
       this.health=health;
    }

    @Override
    public void cick(String s) {
      if(s=="hand"){
          System.out.println("cicking with "+s+"...");
          this.health-=5;
          System.out.println("you cicked "+this.name+" with "+s);
      }  else if(s=="stick"){
          System.out.println("cicking with stick...");
          this.health-=10;
          System.out.println("you cicked "+this.name+" with "+s);


      }   else if(s=="gun"){
          System.out.println("killing with "+s+"...");
          this.health-=100;
          System.out.println("you killed "+this.name+" with "+s);

      }   else if(s=="saw"){
          System.out.println("sawing "+name+" with "+s+"...");
          this.health-=50;
          System.out.println("you cicked "+this.name+" with "+s);

      }       else{
          System.out.println("you don't have that weapon");
          System.out.println(this.name+" will kill you soon");

      }
        if(this.health<=0){
            System.out.println("Fuck you, your dog is dead");
        }
    }


    public void cick() {
       this.health-=5;
        System.out.println("you punched "+this.name+"with hand");
    }

    @Override
    public void showHealth() {
        System.out.println(this.name+"'s health: "+this.health);
    }
}
