package com.isep.rpg;

public class Enemy extends Combattant{


    public Enemy(String name, int hp, int strength, int defense) {
        super(name, hp, strength, defense);
    }

    @Override
    public void changeEffect(int recovery) {

    }
}
