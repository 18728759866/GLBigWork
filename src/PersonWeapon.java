import java.util.HashMap;

public class PersonWeapon {
    HashMap<String,Double> weaponset=new HashMap<String,Double>();

    public void add(String name,double ATK)
    {
        weaponset.put(name,ATK);
    }
    public double getATK(String name){
        return weaponset.get(name);
    }
}

