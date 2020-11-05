package domgpnwzt;

public class Szemely {
    private int id;
    private String name;
    private int age;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Szemely(int id,String name, int age, String city) {
        this.id=id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public Szemely(int id,String name,int age){
        this.id=id;
        this.name=name;
        this.age=age;
        this.city="unknown";
    }
}
