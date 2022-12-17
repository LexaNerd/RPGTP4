package com.isep.rpg;

public class Weapon extends Item{
    private int damages;
    private String name;

    public Weapon(String name)
    {
        super(name);
    }
    public int lamesDuChaos = 7;
    public int arcGaladhrim = 11;
    public int sceptreDeRacines = 29;
    public int batonDeSoin = 13;

    public void equipLames(Combattant combattant){
        combattant.setStrength(combattant.getstrength() + lamesDuChaos);
    }

    public void equipArcs(Combattant combattant){
        combattant.setStrength(combattant.getstrength() + arcGaladhrim);
    }

    public void equipSceptre(Combattant combattant){
        combattant.setStrength(combattant.getstrength() + sceptreDeRacines);
    }

    public void equipBaton(Combattant combattant){
        combattant.setStrength(combattant.getstrength() + batonDeSoin);
    }


}
