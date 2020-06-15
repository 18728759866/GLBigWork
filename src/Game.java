import java.util.Scanner;

public class Game {
    private Room currentRoom;

    public Game()
    {
        createRooms();
    }

    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom;

        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("狼穴");
        pub = new Room("虎窟");
        study = new Room("蛇穴");
        bedroom = new Room("青蛙洞");

        //	初始化房间的出口
        outside.setExits(null, lobby, study, pub);
        lobby.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        study.setExits(outside, bedroom, null, null);
        bedroom.setExits(null, null, null, study);

        currentRoom = outside;  //	从城堡门外开始
    }

    private void printWelcome() {
        System.out.println("----------------------------------------------------------------------------------\n");
        System.out.println("--------------你是一个勇敢的勇士，为了迎娶公主，你必须勇敢闯入怪兽洞，证明你的勇敢！-------------\n");
        System.out.println("-------欢迎来到城堡砍怪游戏，在这里你可以选择你的英雄和你想打的怪兽！-------\n");
        System.out.println("--------同时你会探索一个未知的时间-------\n");
        System.out.println("-------如果需要帮助，请输入 'help' ---------");
        System.out.println();
        System.out.println("---------------现在你在" + currentRoom+"----------\n");
        System.out.print("----------出口有：");
        if(currentRoom.northExit != null)
            System.out.print("north ");
        if(currentRoom.eastExit != null)
            System.out.print("east ");
        if(currentRoom.southExit != null)
            System.out.print("south ");
        if(currentRoom.westExit != null)
            System.out.print("west ");
        System.out.println("\n");
    }


    public void printHelp()
    {
        System.out.print("-------迷路了吗？你可以做的命令有：go bye help-------\n");
        System.out.println("------如：\tgo east-------");
    }

    private void goRoom(String direction,Person obj,Creture name1,Creture name2,Creture name3,Creture name4)
    {
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }

        if (nextRoom == null) {
            System.out.println("-------那里没有门！--------\n");
        }
        else {
            boolean in=true;
            currentRoom = nextRoom;
            Game game1=new Game();
            Creture cre=game1.creture(currentRoom.description,name1,name2,name3,name4);
            System.out.println("-------你在" + currentRoom+"-------\n");
            PersonWeapon personweapon = new PersonWeapon();
            personweapon.add("屠龙刀", 20);
            personweapon.add("拳头", 0);
            if(!currentRoom.description.equals("城堡外") && cre.IsAlive==true){
                Scanner x = new  Scanner(System.in);
                System.out.println("-------请输入您想选择的武器（1.屠龙刀   2.拳头)-----\n");
                String weapon = x.next();
                if(weapon.equals("1")){
                    weapon="屠龙刀";
                }
                else if(weapon.equals("2")){
                    weapon="拳头";
                }
                if (!currentRoom.description.equals("城堡外") && cre.IsAlive==true) {
                    while (in) {
                        if (obj.HP > 0) {
                            double hp = (obj.ATK + personweapon.getATK(weapon)) / (100.0 / cre.DEF);
                            cre.HP = cre.HP - hp;
                            if (weapon.equals("屠龙刀")) {
                                if(cre.HP<=0){
                                    cre.HP=0;
                                }
                                System.out.println("----你使用" + weapon + "触发的旋风斩对"+cre.name+"造成" + hp + "点伤害" + ","+cre.name+"血量还剩" + cre.HP+"----\n");
                            }
                            if (weapon.equals("拳头")) {
                                if(obj.HP<=0){
                                    obj.HP=0;
                                }
                                System.out.println("----你使用" + weapon + "触发的连环拳对"+cre.name+"造成" + hp + "点伤害" + ","+cre.name+"血量还剩" + cre.HP+"----\n");
                            }
                        }
                        if(cre.HP>0){
                            double hp1= cre.ATK / (100 / obj.DEF);
                            obj.HP = obj.HP - hp1;
                            System.out.println("----"+cre.name+"使用"+cre.CretureSkill+"对你造成"+hp1+"的伤害,你的血量还剩"+obj.HP+"----\n");}
                        if (cre.HP <= 0.0 || obj.HP <= 0.0) {
                            in=false;
                        }
                    }
                    if (obj.HP <= 0.0) {
                        System.out.println("-------你被"+cre.name+"击败！------------\n");
                        System.out.println("----------游戏结束！你挂了！--------\n");
                    }
                    if (cre.HP <= 0.0) {
                        System.out.println("--------你击败了"+cre.name+"！--------\n");
                        cre.IsAlive=false;
                    }
                    cre.HP = 100;
                    obj.HP = 100;
                }
            }
            else if(currentRoom.description.equals("城堡外")||cre.IsAlive == false){
                if(currentRoom.description.equals("城堡外")){
                    System.out.println("----------你回到了起点！----------\n");
                }
                else if(!currentRoom.description.equals("城堡外")){
                    System.out.println("--------你已击败"+cre.name+",你可自由通行！---------\n");
                }
            }
            else if(currentRoom.description.equals("城堡外")){
            }
            System.out.print("------出口有: ");
            if(currentRoom.northExit != null)
                System.out.print("north ");
            if(currentRoom.eastExit != null)
                System.out.print("east ");
            if(currentRoom.southExit != null)
                System.out.print("south ");
            if(currentRoom.westExit != null)
                System.out.print("west ");

            System.out.println("\n");
        }
    }
    public Person SelectHero(String personname){

        if(personname.equals("1")){
            Person tom = new Person("tom", 20.0, 50.0, 100.0);
            return tom;
        }
        else if(personname.equals("2")){
            Person james = new Person("james", 10, 24, 100);
            return james;
        }
        else if(personname.equals("3")){
            Person queen =new Person("queen",25,60,150);
            return queen;
        }
        return null;
    }
    public Creture creture(String name,Creture name1,Creture name2,Creture name3,Creture name4){
        if(name.equals("狼穴")){
            return name1;
        }
        else if(name.equals("虎窟")){
            return name2;
        }
        else if(name.equals("蛇穴")){
            return name3;
        }
        else if(name.equals("青蛙洞")){
            return name4;
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Game game = new Game();
        game.printWelcome();
        Creture wolf = new Creture("狼妖", 30, 40, 100,"灭天狼爪");
        Creture tiger = new Creture("虎妖", 50, 40, 100,"恶虎咆哮");
        Creture snake = new Creture("蛇妖", 50, 40, 100,"致命毒液");
        Creture frog = new Creture("青蛙妖", 20, 70, 100,"长舌缠绕");
        Scanner s = new Scanner(System.in);
        System.out.println("-------请输入您想选择的英雄(1.斗气者   2.最强斗士   3.隐忍的王子)-------   注:选择英雄后就不能更改");
        String personname = s.next();
        while ( true ) {
            System.out.println("------请输入要执行的操作：---------");
            String line = in.nextLine();
            String[] words = line.split(" ");
            if ( words[0].equals("help") ) {
                game.printHelp();
            } else if (words[0].equals("go") ) {
                game.goRoom(words[1],game.SelectHero(personname),wolf,tiger,snake,frog);
            } else if ( words[0].equals("bye") ) {
                break;
            }
        }
        System.out.println("-------感谢您的光临。再见！--------");
        System.out.println("-------生活不易，请双击666,点个关注再走。谢谢！-----------");
        in.close();
    }

}

