package com.isep.rpg;

public class Healer extends SpellCaster{
    private int mana;

    public Healer(String name, int hp, int strength, int defense, int magic) {
        super(name, hp, strength, defense, magic);
    }



    @Override
    public void chooseItem(Consumable item) {

    }

    @Override
    public void changeDamages(int c) {

    }
}
