/**
 * Created with IntelliJ IDEA.
 * User: kakha
 * Date: 10/10/13
 * Time: 12:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Person {
    private int age;
    private String name;
    Person(int age,String name){
           this.age=age;
           this.name=name;
    }
    public int getAge(){
        return this.age;
    }
    public String getName(){
        return this.name;
    }
}
