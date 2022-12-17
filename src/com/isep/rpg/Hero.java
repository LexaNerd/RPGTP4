package com.isep.rpg;

public abstract class Hero extends Combattant{
    public Hero(String name, int hp, int strength, int defense) {
        super(name, hp, strength, defense);
    }


    public abstract void chooseItem(Consumable item);
    // public abstract void chooseWeapons(int n);
    public abstract void changeDamages(int c);



}
