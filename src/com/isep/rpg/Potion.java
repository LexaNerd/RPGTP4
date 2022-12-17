package com.isep.rpg;

public class Potion extends Consumable{
    private int recov; // recover permet de récupérer des pv

    public Potion(String name)
    {
        super(name);

    }

    public int cPotion = 3;
    public int recoverPotion = 10;

    public int cSuperPotion = 2;
    public int recoverSuperPotion = 20;

    public int cPotionMax = 1;
    public int recoverPotionMax = 30;

    public void usePotion(Combattant combattant){
        System.out.println(" La Potion permet de générer +10 pour la bar de Mana/Stamina/Rage du hero ");
        cPotion -= 1;
        combattant.changeEffect(recoverPotion);
    }

    public void useSuperPotion(Combattant combattant){
        System.out.println(" La SuperPotion permet de générer +20 pour la bar de Mana/Stamina/Rage du hero ");
        cSuperPotion -= 1;
        combattant.changeEffect(recoverSuperPotion);
    }

    public void usePotionMax(Combattant combattant){
        System.out.println(" La PotionMax permet de générer +30 pour la bar de Mana/Stamina/Rage du hero ");
        cPotionMax -= 1;
        combattant.changeEffect(recoverPotionMax);
    }


}
