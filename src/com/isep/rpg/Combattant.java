package com.isep.rpg;

public abstract class Combattant {
    private String name;

    private final int maxhp;
    private int hp;

    private int strength;

    private int defense;

    public Combattant(String name,int hp, int strength, int defense){
        this.name = name;
        this.hp = hp;
        this.maxhp = hp;
        this.strength = strength;
        this.defense = defense;
    }

    public boolean alive() {
        return hp > 0;
    }

    //getter & setter
    public String getName(){
        return this.name;
    }
    public int gethp(){
        return this.hp;
    }
    public void setHp(int healingPoint){this.hp = healingPoint;}
    public int getstrength(){
        return this.strength;
    }
    public void setStrength(int strength){this.strength = strength;}
    public int getdefense() {return this.defense;}
    public void setDefense(int defense){this.defense = defense;}

    public void takeDamage(int damage){
        this.hp -= damage;
    }
    public void increasePv(int amount){this.hp += amount;
    }

    public void attack(Combattant target){
        int damage = this.getstrength();
        target.takeDamage(damage);
    }

    public abstract void changeEffect(int recovery);

    public void upHP(Combattant combattant){
        setHp(combattant.gethp() + 10);
    }

    public void upStrenght(Combattant combattant){
        setStrength(combattant.getstrength() + 5);
    }

    public void upDefense(Combattant combattant){
        setDefense(combattant.getdefense() + 10);
    }

}
