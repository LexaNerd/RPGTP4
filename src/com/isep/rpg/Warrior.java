package com.isep.rpg;

public class Warrior extends Hero{
    private int rage;

    public Warrior(String name, int hp, int strength, int defense,int rage) {
        super(name, hp, strength, defense);
        this.rage = rage;
    }

    //getter
    public int getrage(){
        return this.rage;
    }

    @Override
    public void chooseItem(Consumable item) {

    }

    @Override
    public void changeDamages(int c) {

    }

    @Override
    public void changeEffect(int recovery) {
        rage += recovery;
    }
}
