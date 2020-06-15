public class Creture {
    String name;
    double ATK;
    double DEF;
    double HP;
    boolean IsAlive=true;
    String CretureSkill;
    public Creture(String name,double ATK,double DEF,double HP,String cretureSkill){
        this.name=name;
        this.ATK=ATK;
        this.DEF=DEF;
        this.HP=HP;
        this.CretureSkill=cretureSkill;
    }
}
