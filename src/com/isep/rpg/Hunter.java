package com.isep.rpg;

public class Hunter extends Hero{
    private int stamina;

    public Hunter(String name, int hp, int strength, int defense,int stamina) {
        super(name, hp, strength, defense);
        this.stamina = stamina;
    }

    //getter
    public int getstamina(){
        return this.stamina;
    }

    @Override
    public void chooseItem(Consumable item) {

    }

    @Override
    public void changeDamages(int c) {

    }

    @Override
    public void changeEffect(int recovery) {
        stamina += recovery;
    }
}
