package com.isep.rpg;

public class Food extends Consumable{
    private int recov; // recover permet de récupérer des pv

    public Food(String name)
    {
        super(name);

    }

    public int cLembas = 3;
    public int recoverLembas = 10;

    public int cLimpë = 2;
    public int recoverLimpë = 20;

    public int cMiruvor = 1;
    public int recoverMiruvor = 30;

    public void useLembas(Combattant combattant){
        System.out.println(" Les Lembas permettent de générer +10 pv à votre hero ");
        cLembas -= 1;
        combattant.setHp(combattant.gethp() + recoverLembas);
    }

    public void useLimpë(Combattant combattant){
        System.out.println(" Des gorgées de Limpë permettent de générer +20 pv à votre hero ");
        cLimpë -= 1;
        combattant.setHp(combattant.gethp() + recoverLimpë);
    }

    public void useMiruvor(Combattant combattant){
        System.out.println(" Des gorgées de Miruvor permettent de générer +30 pv à votre hero ");
        cMiruvor -= 1;
        combattant.setHp(combattant.gethp() + recoverMiruvor);
    }

}
