package com.isep.rpg;


import com.isep.utils.ConsoleParser;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    Hero c1 = new Warrior("Kratos",50,8,10,30);
    Hero c2 = new Hunter("Legolas",35,6,3,30);
    Hero c3 = new Mage("Wylandriah",30,1,3,100);
    Hero c4 = new Healer("Ange",30,7,3,100);
    Hero[] listhero = {c1,c2,c3,c4};




    public boolean IsHeroAlive(){
        for (Hero hero: listhero){
            if(hero.alive()){
                return true;
            }
        }
        return false;
    }
    public boolean IsEnnemyAlive(Enemy[] e){
        for (Enemy enemy: e){
            if(enemy.alive()){
                return true;
            }
        }
        return false;
    }

    private ArrayList<Consumable> inventaire = new ArrayList<>();

    public void start() {
        ConsoleParser parser = new ConsoleParser();
        //initialisation potion & food
        Potion potion = new Potion("Nb de Popo");
        Food food = new Food("Nb de nourriture et boisson");
        Combattant combattant = new Combattant("init", 0,0,0) {
            @Override
            public void changeEffect(int recovery) {

            }
        };
        Weapon weapon = new Weapon("init");
        weapon.equipLames(listhero[0]);
        weapon.equipArcs(listhero[1]);
        weapon.equipSceptre(listhero[2]);
        weapon.equipBaton(listhero[3]);



        //présentation histoire
        System.out.println(" Salutation fée, vous avez été choisi pour guider 4 valeureux aventuriers dans leur plus " +
                "grande quête. Votre objectif est de les mener dans les mines s d'Erebor afin de sauver le peuple nain " +
                "du terrible dragon qui s'y trouve et qui garde ce lieu. Pour ce faire vous allez devoir aider les " +
                "aventuriers à avancer dans 5 zones qui vont vous amener à la salle du dragon Smaug. Voici vos " +
                "aventuriers : " + "\n" + listhero[0].getName() + " un dieu de la guerre armé de ses lames du chaos " +
                "\n" + listhero[1].getName() + " un elf ayant hérité d'un arc des Galadhrims" + "\n" + listhero[2].getName() +
                " une sorcière possédant le sceptre de Racine " + "\n" + listhero[3].getName() + " a pour objectif de maintenir le groupe en vie à l'aide d'un bâton de soin ");
        System.out.println(" Voici les statistiques de vos aventuriers: " +
                "\n" + listhero[0].getName() + " pv: "+ listhero[0].gethp()  + " force: " + listhero[0].getstrength() + " defense: " + listhero[0].getdefense() +
                "\n" + listhero[1].getName() + " pv: "+ listhero[1].gethp()  + " force: " + listhero[1].getstrength() + " defense: " + listhero[1].getdefense() +
                "\n" + listhero[2].getName() + " pv: "+ listhero[2].gethp()  + " force: " + listhero[2].getstrength() + " defense: " + listhero[2].getdefense() +
                "\n" + listhero[3].getName() + " pv: "+ listhero[3].gethp()  + " force: " + listhero[3].getstrength() + " defense: " + listhero[3].getdefense());


        // round one
        //liste des ennemies pour la première vague
        Enemy[] listenemy1 = new Enemy[5];
        for (int i = 0; i < 5; i++) {
            Enemy orc = new Enemy("Orc", 25, 4, 0);
            listenemy1[i] = orc;
        }

        //phase d'interaction vague 1
        System.out.println("Vous pénétrez la mine et arrivez au niveau 0.");
        System.out.println("Attention ! " + listenemy1.length + " " + listenemy1[0].getName() + " viennent d'arriver que voulez-vous faire?");
        while(IsHeroAlive()){
            while(IsEnnemyAlive(listenemy1)){
                if(!IsHeroAlive()){
                    System.out.println("GAME OVER");
                    break;
                }else{
                    for(int i=0; i < listhero.length; i++){
                        if(!IsEnnemyAlive(listenemy1)){
                            break;
                        }
                        if(listhero[i].gethp() > 0){
                            System.out.println(" c'est au tour de " + listhero[i].getName() + " d'agir. ");
                            int chooseAction = parser.promptWithIntParser("Que voulez faire ?" + "\n1- Attaquer" + "\n2- Se défendre" + "\n3- Fouiller dans le sac sans fond !");
                            if(chooseAction == 1){
                                int compteur = 1;
                                for(int j=0; j < listenemy1.length; j++){ // boucle présentation ennemi
                                    if(listenemy1[j].gethp() <= 0){
                                        listenemy1[j].setHp(0);
                                    }
                                    System.out.println(compteur + "- " + listenemy1[j].getName() + " " + listenemy1[j].gethp() + " pv ");
                                    compteur ++;
                                }
                                for(int m = 0; m < 1; m++){ //boucle vérification ennemi en vie
                                    int chooseVictim = parser.promptWithIntParser("Qui souhaitez-vous attaquer?");
                                    int choixAttaque = parser.promptWithIntParser("Comment voulez-vous attaquer? \n1- Attaquer \n2- Ultime");
                                    if(listhero[i] == c4){ //boucle healer
                                        if(choixAttaque == 1){
                                            listhero[i].setStrength(listhero[i].getstrength() - weapon.batonDeSoin);
                                            if(listenemy1[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy1[chooseVictim-1]);
                                                if(listenemy1[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy1[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy1[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy1[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                            weapon.equipBaton(listhero[i]);
                                        }else if(choixAttaque ==2){
                                            for(int n = 0; n<1; n++){
                                                System.out.println("1-" + listhero[0].getName() + " : " + listhero[0].gethp() + " pv");
                                                System.out.println("2-" + listhero[1].getName() + " : " + listhero[1].gethp() + " pv");
                                                System.out.println("3-" + listhero[2].getName() + " : " + listhero[2].gethp() + " pv");
                                                System.out.println("4-" + listhero[3].getName() + " : " + listhero[3].gethp() + " pv");
                                                int choixHeal = parser.promptWithIntParser("Qui voulez-vous soigner?");
                                                if(listhero[choixHeal - 1].gethp() > 0){
                                                    listhero[choixHeal - 1].setHp(listhero[choixHeal - 1].gethp() + listhero[i].getstrength());
                                                }else{
                                                    System.out.println("ce héros est déjà mort... Ange ne peut pas le ramener à la vie.");
                                                    n--;
                                                }
                                            }
                                        }
                                    } else if(listhero[i] == c3){ //boucle mage
                                        if(choixAttaque == 1){
                                            listhero[i].setStrength(listhero[i].getstrength() - weapon.sceptreDeRacines);
                                            if(listenemy1[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy1[chooseVictim-1]);
                                                if(listenemy1[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy1[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy1[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy1[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                            weapon.equipSceptre(listhero[i]);
                                        }else if(choixAttaque == 2){
                                            if(listenemy1[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy1[chooseVictim-1]);
                                                if(listenemy1[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy1[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy1[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy1[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                        }
                                    } else {
                                        if(listenemy1[chooseVictim-1].gethp() > 0){
                                            listhero[i].attack(listenemy1[chooseVictim-1]);
                                            if(listenemy1[chooseVictim-1].gethp() > 0){
                                                System.out.println(listenemy1[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy1[chooseVictim - 1].gethp() + " pv");
                                            } else {
                                                System.out.println(listenemy1[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                            }
                                        } else{
                                            m--;
                                            System.out.println("Choisissez une cible en vie!");
                                        }
                                    }
                                }
                            }
                            if(chooseAction == 2){
                                listhero[i].setHp(listhero[i].gethp() + listhero[i].getdefense());
                            }
                            if(chooseAction == 3){
                                for(int k = 0; k < 1; k++){
                                    System.out.println(listhero[i].getName() + " décide de fouiller dans le sac sans fond et y découvre les objets suivants," +
                                            "\n1- Potion " + potion.cPotion + " disponible et fournit " + potion.recoverPotion + " 10 mana/rage/stamina." +
                                            "\n2- SuperPotion " + potion.cSuperPotion + " disponible et fournit " + potion.recoverSuperPotion + " 20 mana/stamina/rage." +
                                            "\n3- PotionMax " + potion.cPotionMax + " disponible et fournit " + potion.recoverPotionMax + " 30 mana/stamina/rage." +
                                            "\n4- Lembas " + food.cLembas + " disponible et fournit " + food.recoverLembas + " 10 pv." +
                                            "\n5- Limpë " + food.cLimpë + " disponible et fournit " + food.recoverLimpë + " 20 pv." +
                                            "\n6- Miruvor " + food.cMiruvor + " disponible et fournit " + food.recoverMiruvor + " 10 pv." );
                                    int chooseObject = parser.promptWithIntParser(" Quel item voulez vous choisir? ");
                                    if(chooseObject == 1 && potion.cPotion > 0){
                                        potion.usePotion(listhero[i]);
                                    }else if(chooseObject == 2 && potion.cSuperPotion>0){
                                        potion.useSuperPotion(listhero[i]);
                                    }else if(chooseObject == 3 && potion.cPotionMax>0){
                                        potion.usePotionMax(listhero[i]);
                                    }else if(chooseObject == 4 && food.cLembas>0){
                                        food.useLembas(listhero[i]);
                                    }else if(chooseObject == 5 && food.cLimpë>0){
                                        food.useLimpë(listhero[i] );
                                    }else if(chooseObject == 6 && food.cMiruvor>0){
                                        food.useMiruvor(listhero[i]);
                                    }else{
                                        k--;
                                        System.out.println("Choisissez un autre consommable.");
                                    }
                                }
                            }
                        }
                    }
                    for(int i=0; i < listenemy1.length; i++){
                        if(listenemy1[i].gethp() > 0){
                            System.out.println(" c'est au tour de " + listenemy1[i].getName() );
                            for(int l=0; l < 1; l++){
                                Random random2 = new Random();
                                int nbTarget = random2.nextInt(4);
                                if(listhero[nbTarget].gethp() > 0){
                                    System.out.println(listenemy1[i].getName() + " décide d'attaquer " + listhero[nbTarget].getName());
                                    listenemy1[i].attack(listhero[nbTarget]);
                                    if(listhero[nbTarget].gethp() > 0){
                                        System.out.println(listhero[nbTarget].getName() + " encaisse et passe à " + listhero[nbTarget].gethp() + " pv");
                                    } else {
                                        System.out.println(listhero[nbTarget].getName() + " reçoit le coup final ! valar morghulis..." );
                                        listhero[nbTarget].setHp(0);
                                    }
                                }else if(!IsHeroAlive()){
                                    break;
                                }else{
                                    l--;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Les Orcs sont vaincus! Vous décidez donc de descendre encore plus dans la mine");
            break;
        }


        //Upgrade heros
        for(int q=0; q < listhero.length; q++){
            System.out.println("Vous pouvez améliorer les caractéristiques de " + listhero[q].getName() + "\n1- Augmentation des PV (+10)" + "\n2- Augmentation de la Force (+5)" + "\n3- Augmentation de la défense (+10)" );
            int choixUpgrade = parser.promptWithIntParser("Que voulez-vous choisir?");
            if(choixUpgrade == 1){
                combattant.upHP(listhero[q]);
            }
            if(choixUpgrade == 2){
                combattant.upStrenght(listhero[q]);
            }
            if(choixUpgrade == 3){
                combattant.upDefense(listhero[q]);
            }
        }


        //round two
        Enemy[] listenemy2 = new Enemy[4];
        for (int i = 0; i < 4; i++) {
            Enemy minotaure = new Enemy("Minotaure", 40, 6, 0);
            listenemy2[i] = minotaure;
        }
        //phase d'interaction vague 2
        System.out.println("Vous descendez dans la mine et arrivez au niveau -1.");
        System.out.println("Attention ! " + listenemy2.length + " " + listenemy2[0].getName() + " viennent d'arriver que voulez-vous faire?");
        while(IsHeroAlive()){
            while(IsEnnemyAlive(listenemy2)){
                if(!IsHeroAlive()){
                    System.out.println("GAME OVER");
                    break;
                }else{
                    for(int i=0; i < listhero.length; i++){
                        if(!IsEnnemyAlive(listenemy2)){
                            break;
                        }
                        if(listhero[i].gethp() > 0){
                            System.out.println(" c'est au tour de " + listhero[i].getName() + " d'agir. ");
                            int chooseAction = parser.promptWithIntParser("Que voulez faire ?" + "\n1- Attaquer" + "\n2- Se défendre" + "\n3- Fouiller dans le sac sans fond !");
                            if(chooseAction == 1){
                                int compteur = 1;
                                for(int j=0; j < listenemy2.length; j++){ // boucle présentation ennemi
                                    if(listenemy2[j].gethp() <= 0){
                                        listenemy2[j].setHp(0);
                                    }
                                    System.out.println(compteur + "- " + listenemy2[j].getName() + " " + listenemy2[j].gethp() + " pv ");
                                    compteur ++;
                                }
                                for(int m = 0; m < 1; m++){ //boucle vérification ennemi en vie
                                    int chooseVictim = parser.promptWithIntParser("Qui souhaitez-vous attaquer?");
                                    int choixAttaque = parser.promptWithIntParser("Comment voulez-vous attaquer? \n1- Attaquer \n2- Ultime");
                                    if(listhero[i] == c4){ //boucle healer
                                        if(choixAttaque == 1){
                                            listhero[i].setStrength(listhero[i].getstrength() - weapon.batonDeSoin);
                                            if(listenemy2[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy2[chooseVictim-1]);
                                                if(listenemy2[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy2[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy2[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy2[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                            weapon.equipBaton(listhero[i]);
                                        }else if(choixAttaque ==2){
                                            for(int n = 0; n<1; n++){
                                                System.out.println("1-" + listhero[0].getName() + " : " + listhero[0].gethp() + " pv");
                                                System.out.println("2-" + listhero[1].getName() + " : " + listhero[1].gethp() + " pv");
                                                System.out.println("3-" + listhero[2].getName() + " : " + listhero[2].gethp() + " pv");
                                                System.out.println("4-" + listhero[3].getName() + " : " + listhero[3].gethp() + " pv");
                                                int choixHeal = parser.promptWithIntParser("Qui voulez-vous soigner?");
                                                if(listhero[choixHeal - 1].gethp() > 0){
                                                    listhero[choixHeal - 1].setHp(listhero[choixHeal - 1].gethp() + listhero[i].getstrength());
                                                }else{
                                                    System.out.println("ce héros est déjà mort... Ange ne peut pas le ramener à la vie.");
                                                    n--;
                                                }
                                            }
                                        }
                                    } else if(listhero[i] == c3){ //boucle mage
                                        if(choixAttaque == 1){
                                            listhero[i].setStrength(listhero[i].getstrength() - weapon.sceptreDeRacines);
                                            if(listenemy2[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy2[chooseVictim-1]);
                                                if(listenemy2[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy2[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy2[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy2[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                            weapon.equipSceptre(listhero[i]);
                                        }else if(choixAttaque == 2){
                                            if(listenemy2[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy2[chooseVictim-1]);
                                                if(listenemy2[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy2[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy2[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy2[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                        }
                                    } else {
                                        if(listenemy2[chooseVictim-1].gethp() > 0){
                                            listhero[i].attack(listenemy2[chooseVictim-1]);
                                            if(listenemy2[chooseVictim-1].gethp() > 0){
                                                System.out.println(listenemy2[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy2[chooseVictim - 1].gethp() + " pv");
                                            } else {
                                                System.out.println(listenemy2[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                            }
                                        } else{
                                            m--;
                                            System.out.println("Choisissez une cible en vie!");
                                        }
                                    }
                                }
                            }
                            if(chooseAction == 2){
                                listhero[i].setHp(listhero[i].gethp() + listhero[i].getdefense());
                            }
                            if(chooseAction == 3){
                                for(int k = 0; k < 1; k++){
                                    System.out.println(listhero[i].getName() + " décide de fouiller dans le sac sans fond et y découvre les objets suivants," +
                                            "\n1- Potion " + potion.cPotion + " disponible et fournit " + potion.recoverPotion + " 10 mana/rage/stamina." +
                                            "\n2- SuperPotion " + potion.cSuperPotion + " disponible et fournit " + potion.recoverSuperPotion + " 20 mana/stamina/rage." +
                                            "\n3- PotionMax " + potion.cPotionMax + " disponible et fournit " + potion.recoverPotionMax + " 30 mana/stamina/rage." +
                                            "\n4- Lembas " + food.cLembas + " disponible et fournit " + food.recoverLembas + " 10 pv." +
                                            "\n5- Limpë " + food.cLimpë + " disponible et fournit " + food.recoverLimpë + " 20 pv." +
                                            "\n6- Miruvor " + food.cMiruvor + " disponible et fournit " + food.recoverMiruvor + " 10 pv." );
                                    int chooseObject = parser.promptWithIntParser(" Quel item voulez vous choisir? ");
                                    if(chooseObject == 1 && potion.cPotion > 0){
                                        potion.usePotion(listhero[i]);
                                    }else if(chooseObject == 2 && potion.cSuperPotion>0){
                                        potion.useSuperPotion(listhero[i]);
                                    }else if(chooseObject == 3 && potion.cPotionMax>0){
                                        potion.usePotionMax(listhero[i]);
                                    }else if(chooseObject == 4 && food.cLembas>0){
                                        food.useLembas(listhero[i]);
                                    }else if(chooseObject == 5 && food.cLimpë>0){
                                        food.useLimpë(listhero[i] );
                                    }else if(chooseObject == 6 && food.cMiruvor>0){
                                        food.useMiruvor(listhero[i]);
                                    }else{
                                        k--;
                                        System.out.println("Choisissez un autre consommable.");
                                    }
                                }
                            }
                        }
                    }
                    for(int i=0; i < listenemy2.length; i++){
                        if(listenemy2[i].gethp() > 0){
                            System.out.println(" c'est au tour de " + listenemy2[i].getName() );
                            for(int l=0; l < 1; l++){
                                Random random2 = new Random();
                                int nbTarget = random2.nextInt(4);
                                if(listhero[nbTarget].gethp() > 0){
                                    System.out.println(listenemy2[i].getName() + " décide d'attaquer " + listhero[nbTarget].getName());
                                    listenemy2[i].attack(listhero[nbTarget]);
                                    if(listhero[nbTarget].gethp() > 0){
                                        System.out.println(listhero[nbTarget].getName() + " encaisse et passe à " + listhero[nbTarget].gethp() + " pv");
                                    } else {
                                        System.out.println(listhero[nbTarget].getName() + " reçoit le coup final ! valar morghulis..." );
                                        listhero[nbTarget].setHp(0);
                                    }
                                }else if(!IsHeroAlive()){
                                    break;
                                }else{
                                    l--;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Les Minotaures sont vaincus! Vous décidez donc de descendre encore plus dans la mine");
            break;
        }


        //Upgrade heros
        for(int q=0; q < listhero.length; q++){
            System.out.println("Vous pouvez améliorer les caractéristiques de " + listhero[q].getName() + "\n1- Augmentation des PV (+10)" + "\n2- Augmentation de la Force (+5)" + "\n3- Augmentation de la défense (+10)" );
            int choixUpgrade = parser.promptWithIntParser("Que voulez-vous choisir?");
            if(choixUpgrade == 1){
                combattant.upHP(listhero[q]);
            }
            if(choixUpgrade == 2){
                combattant.upStrenght(listhero[q]);
            }
            if(choixUpgrade == 3){
                combattant.upDefense(listhero[q]);
            }
        }



        //round three
        Enemy[] listenemy3 = new Enemy[3];
        for (int i = 0; i < 3; i++) {
            Enemy warg = new Enemy("Warg", 60, 8, 0);
            listenemy3[i] = warg;
        }
        //phase d'interaction vague 3
        System.out.println("Vous descendez dans la mine et arrivez au niveau -2.");
        System.out.println("Attention ! " + listenemy3.length + " " + listenemy3[0].getName() + " viennent d'arriver que voulez-vous faire?");
        while(IsHeroAlive()){
            while(IsEnnemyAlive(listenemy3)){
                if(!IsHeroAlive()){
                    System.out.println("GAME OVER");
                    break;
                }else{
                    for(int i=0; i < listhero.length; i++){
                        if(!IsEnnemyAlive(listenemy3)){
                            break;
                        }
                        if(listhero[i].gethp() > 0){
                            System.out.println(" c'est au tour de " + listhero[i].getName() + " d'agir. ");
                            int chooseAction = parser.promptWithIntParser("Que voulez faire ?" + "\n1- Attaquer" + "\n2- Se défendre" + "\n3- Fouiller dans le sac sans fond !");
                            if(chooseAction == 1){
                                int compteur = 1;
                                for(int j=0; j < listenemy3.length; j++){ // boucle présentation ennemi
                                    if(listenemy3[j].gethp() <= 0){
                                        listenemy3[j].setHp(0);
                                    }
                                    System.out.println(compteur + "- " + listenemy3[j].getName() + " " + listenemy3[j].gethp() + " pv ");
                                    compteur ++;
                                }
                                for(int m = 0; m < 1; m++){ //boucle vérification ennemi en vie
                                    int chooseVictim = parser.promptWithIntParser("Qui souhaitez-vous attaquer?");
                                    int choixAttaque = parser.promptWithIntParser("Comment voulez-vous attaquer? \n1- Attaquer \n2- Ultime");
                                    if(listhero[i] == c4){ //boucle healer
                                        if(choixAttaque == 1){
                                            listhero[i].setStrength(listhero[i].getstrength() - weapon.batonDeSoin);
                                            if(listenemy3[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy3[chooseVictim-1]);
                                                if(listenemy3[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy3[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy3[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy3[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                            weapon.equipBaton(listhero[i]);
                                        }else if(choixAttaque ==2){
                                            for(int n = 0; n<1; n++){
                                                System.out.println("1-" + listhero[0].getName() + " : " + listhero[0].gethp() + " pv");
                                                System.out.println("2-" + listhero[1].getName() + " : " + listhero[1].gethp() + " pv");
                                                System.out.println("3-" + listhero[2].getName() + " : " + listhero[2].gethp() + " pv");
                                                System.out.println("4-" + listhero[3].getName() + " : " + listhero[3].gethp() + " pv");
                                                int choixHeal = parser.promptWithIntParser("Qui voulez-vous soigner?");
                                                if(listhero[choixHeal - 1].gethp() > 0){
                                                    listhero[choixHeal - 1].setHp(listhero[choixHeal - 1].gethp() + listhero[i].getstrength());
                                                }else{
                                                    System.out.println("ce héros est déjà mort... Ange ne peut pas le ramener à la vie.");
                                                    n--;
                                                }
                                            }
                                        }
                                    } else if(listhero[i] == c3){ //boucle mage
                                        if(choixAttaque == 1){
                                            listhero[i].setStrength(listhero[i].getstrength() - weapon.sceptreDeRacines);
                                            if(listenemy3[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy3[chooseVictim-1]);
                                                if(listenemy3[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy3[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy3[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy3[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                            weapon.equipSceptre(listhero[i]);
                                        }else if(choixAttaque == 2){
                                            if(listenemy3[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy3[chooseVictim-1]);
                                                if(listenemy3[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy3[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy3[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy3[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                        }
                                    } else {
                                        if(listenemy3[chooseVictim-1].gethp() > 0){
                                            listhero[i].attack(listenemy3[chooseVictim-1]);
                                            if(listenemy3[chooseVictim-1].gethp() > 0){
                                                System.out.println(listenemy3[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy3[chooseVictim - 1].gethp() + " pv");
                                            } else {
                                                System.out.println(listenemy3[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                            }
                                        } else{
                                            m--;
                                            System.out.println("Choisissez une cible en vie!");
                                        }
                                    }
                                }
                            }
                            if(chooseAction == 2){
                                listhero[i].setHp(listhero[i].gethp() + listhero[i].getdefense());
                            }
                            if(chooseAction == 3){
                                for(int k = 0; k < 1; k++){
                                    System.out.println(listhero[i].getName() + " décide de fouiller dans le sac sans fond et y découvre les objets suivants," +
                                            "\n1- Potion " + potion.cPotion + " disponible et fournit " + potion.recoverPotion + " 10 mana/rage/stamina." +
                                            "\n2- SuperPotion " + potion.cSuperPotion + " disponible et fournit " + potion.recoverSuperPotion + " 20 mana/stamina/rage." +
                                            "\n3- PotionMax " + potion.cPotionMax + " disponible et fournit " + potion.recoverPotionMax + " 30 mana/stamina/rage." +
                                            "\n4- Lembas " + food.cLembas + " disponible et fournit " + food.recoverLembas + " 10 pv." +
                                            "\n5- Limpë " + food.cLimpë + " disponible et fournit " + food.recoverLimpë + " 20 pv." +
                                            "\n6- Miruvor " + food.cMiruvor + " disponible et fournit " + food.recoverMiruvor + " 10 pv." );
                                    int chooseObject = parser.promptWithIntParser(" Quel item voulez vous choisir? ");
                                    if(chooseObject == 1 && potion.cPotion > 0){
                                        potion.usePotion(listhero[i]);
                                    }else if(chooseObject == 2 && potion.cSuperPotion>0){
                                        potion.useSuperPotion(listhero[i]);
                                    }else if(chooseObject == 3 && potion.cPotionMax>0){
                                        potion.usePotionMax(listhero[i]);
                                    }else if(chooseObject == 4 && food.cLembas>0){
                                        food.useLembas(listhero[i]);
                                    }else if(chooseObject == 5 && food.cLimpë>0){
                                        food.useLimpë(listhero[i] );
                                    }else if(chooseObject == 6 && food.cMiruvor>0){
                                        food.useMiruvor(listhero[i]);
                                    }else{
                                        k--;
                                        System.out.println("Choisissez un autre consommable.");
                                    }
                                }
                            }
                        }
                    }
                    for(int i=0; i < listenemy3.length; i++){
                        if(listenemy3[i].gethp() > 0){
                            System.out.println(" c'est au tour de " + listenemy3[i].getName() );
                            for(int l=0; l < 1; l++){
                                Random random2 = new Random();
                                int nbTarget = random2.nextInt(4);
                                if(listhero[nbTarget].gethp() > 0){
                                    System.out.println(listenemy3[i].getName() + " décide d'attaquer " + listhero[nbTarget].getName());
                                    listenemy3[i].attack(listhero[nbTarget]);
                                    if(listhero[nbTarget].gethp() > 0){
                                        System.out.println(listhero[nbTarget].getName() + " encaisse et passe à " + listhero[nbTarget].gethp() + " pv");
                                    } else {
                                        System.out.println(listhero[nbTarget].getName() + " reçoit le coup final ! valar morghulis..." );
                                        listhero[nbTarget].setHp(0);
                                    }
                                }else if(!IsHeroAlive()){
                                    break;
                                }else{
                                    l--;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Les Wargs sont vaincus! Vous décidez donc de descendre encore plus dans la mine");
            break;
        }


        //Upgrade heros
        for(int q=0; q < listhero.length; q++){
            System.out.println("Vous pouvez améliorer les caractéristiques de " + listhero[q].getName() + "\n1- Augmentation des PV (+10)" + "\n2- Augmentation de la Force (+5)" + "\n3- Augmentation de la défense (+10)" );
            int choixUpgrade = parser.promptWithIntParser("Que voulez-vous choisir?");
            if(choixUpgrade == 1){
                combattant.upHP(listhero[q]);
            }
            if(choixUpgrade == 2){
                combattant.upStrenght(listhero[q]);
            }
            if(choixUpgrade == 3){
                combattant.upDefense(listhero[q]);
            }
        }



        //round four
        Enemy[] listenemy4 = new Enemy[2];
        for (int i = 0; i < 2; i++) {
            Enemy nazgul = new Enemy("Nazgul", 80, 10, 0);
            listenemy4[i] = nazgul;
        }
        //phase d'interaction vague 4
        System.out.println("Vous descendez dans la mine et arrivez au niveau -3.");
        System.out.println("Attention ! " + listenemy4.length + " " + listenemy4[0].getName() + " viennent d'arriver que voulez-vous faire?");
        while(IsHeroAlive()){
            while(IsEnnemyAlive(listenemy4)){
                if(!IsHeroAlive()){
                    System.out.println("GAME OVER");
                    break;
                }else{
                    for(int i=0; i < listhero.length; i++){
                        if(!IsEnnemyAlive(listenemy4)){
                            break;
                        }
                        if(listhero[i].gethp() > 0){
                            System.out.println(" c'est au tour de " + listhero[i].getName() + " d'agir. ");
                            int chooseAction = parser.promptWithIntParser("Que voulez faire ?" + "\n1- Attaquer" + "\n2- Se défendre" + "\n3- Fouiller dans le sac sans fond !");
                            if(chooseAction == 1){
                                int compteur = 1;
                                for(int j=0; j < listenemy4.length; j++){ // boucle présentation ennemi
                                    if(listenemy4[j].gethp() <= 0){
                                        listenemy4[j].setHp(0);
                                    }
                                    System.out.println(compteur + "- " + listenemy4[j].getName() + " " + listenemy4[j].gethp() + " pv ");
                                    compteur ++;
                                }
                                for(int m = 0; m < 1; m++){ //boucle vérification ennemi en vie
                                    int chooseVictim = parser.promptWithIntParser("Qui souhaitez-vous attaquer?");
                                    int choixAttaque = parser.promptWithIntParser("Comment voulez-vous attaquer? \n1- Attaquer \n2- Ultime");
                                    if(listhero[i] == c4){ //boucle healer
                                        if(choixAttaque == 1){
                                            listhero[i].setStrength(listhero[i].getstrength() - weapon.batonDeSoin);
                                            if(listenemy4[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy4[chooseVictim-1]);
                                                if(listenemy4[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy4[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy4[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy4[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                            weapon.equipBaton(listhero[i]);
                                        }else if(choixAttaque ==2){
                                            for(int n = 0; n<1; n++){
                                                System.out.println("1-" + listhero[0].getName() + " : " + listhero[0].gethp() + " pv");
                                                System.out.println("2-" + listhero[1].getName() + " : " + listhero[1].gethp() + " pv");
                                                System.out.println("3-" + listhero[2].getName() + " : " + listhero[2].gethp() + " pv");
                                                System.out.println("4-" + listhero[3].getName() + " : " + listhero[3].gethp() + " pv");
                                                int choixHeal = parser.promptWithIntParser("Qui voulez-vous soigner?");
                                                if(listhero[choixHeal - 1].gethp() > 0){
                                                    listhero[choixHeal - 1].setHp(listhero[choixHeal - 1].gethp() + listhero[i].getstrength());
                                                }else{
                                                    System.out.println("ce héros est déjà mort... Ange ne peut pas le ramener à la vie.");
                                                    n--;
                                                }
                                            }
                                        }
                                    } else if(listhero[i] == c3){ //boucle mage
                                        if(choixAttaque == 1){
                                            listhero[i].setStrength(listhero[i].getstrength() - weapon.sceptreDeRacines);
                                            if(listenemy4[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy4[chooseVictim-1]);
                                                if(listenemy4[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy4[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy4[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy4[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                            weapon.equipSceptre(listhero[i]);
                                        }else if(choixAttaque == 2){
                                            if(listenemy4[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy4[chooseVictim-1]);
                                                if(listenemy4[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy4[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy4[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy4[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                        }
                                    } else {
                                        if(listenemy4[chooseVictim-1].gethp() > 0){
                                            listhero[i].attack(listenemy4[chooseVictim-1]);
                                            if(listenemy4[chooseVictim-1].gethp() > 0){
                                                System.out.println(listenemy4[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy4[chooseVictim - 1].gethp() + " pv");
                                            } else {
                                                System.out.println(listenemy4[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                            }
                                        } else{
                                            m--;
                                            System.out.println("Choisissez une cible en vie!");
                                        }
                                    }
                                }
                            }
                            if(chooseAction == 2){
                                listhero[i].setHp(listhero[i].gethp() + listhero[i].getdefense());
                            }
                            if(chooseAction == 3){
                                for(int k = 0; k < 1; k++){
                                    System.out.println(listhero[i].getName() + " décide de fouiller dans le sac sans fond et y découvre les objets suivants," +
                                            "\n1- Potion " + potion.cPotion + " disponible et fournit " + potion.recoverPotion + " 10 mana/rage/stamina." +
                                            "\n2- SuperPotion " + potion.cSuperPotion + " disponible et fournit " + potion.recoverSuperPotion + " 20 mana/stamina/rage." +
                                            "\n3- PotionMax " + potion.cPotionMax + " disponible et fournit " + potion.recoverPotionMax + " 30 mana/stamina/rage." +
                                            "\n4- Lembas " + food.cLembas + " disponible et fournit " + food.recoverLembas + " 10 pv." +
                                            "\n5- Limpë " + food.cLimpë + " disponible et fournit " + food.recoverLimpë + " 20 pv." +
                                            "\n6- Miruvor " + food.cMiruvor + " disponible et fournit " + food.recoverMiruvor + " 10 pv." );
                                    int chooseObject = parser.promptWithIntParser(" Quel item voulez vous choisir? ");
                                    if(chooseObject == 1 && potion.cPotion > 0){
                                        potion.usePotion(listhero[i]);
                                    }else if(chooseObject == 2 && potion.cSuperPotion>0){
                                        potion.useSuperPotion(listhero[i]);
                                    }else if(chooseObject == 3 && potion.cPotionMax>0){
                                        potion.usePotionMax(listhero[i]);
                                    }else if(chooseObject == 4 && food.cLembas>0){
                                        food.useLembas(listhero[i]);
                                    }else if(chooseObject == 5 && food.cLimpë>0){
                                        food.useLimpë(listhero[i] );
                                    }else if(chooseObject == 6 && food.cMiruvor>0){
                                        food.useMiruvor(listhero[i]);
                                    }else{
                                        k--;
                                        System.out.println("Choisissez un autre consommable.");
                                    }
                                }
                            }
                        }
                    }
                    for(int i=0; i < listenemy4.length; i++){
                        if(listenemy4[i].gethp() > 0){
                            System.out.println(" c'est au tour de " + listenemy4[i].getName() );
                            for(int l=0; l < 1; l++){
                                Random random2 = new Random();
                                int nbTarget = random2.nextInt(4);
                                if(listhero[nbTarget].gethp() > 0){
                                    System.out.println(listenemy4[i].getName() + " décide d'attaquer " + listhero[nbTarget].getName());
                                    listenemy4[i].attack(listhero[nbTarget]);
                                    if(listhero[nbTarget].gethp() > 0){
                                        System.out.println(listhero[nbTarget].getName() + " encaisse et passe à " + listhero[nbTarget].gethp() + " pv");
                                    } else {
                                        System.out.println(listhero[nbTarget].getName() + " reçoit le coup final ! valar morghulis..." );
                                        listhero[nbTarget].setHp(0);
                                    }
                                }else if(!IsHeroAlive()){
                                    break;
                                }else{
                                    l--;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Les Nazguls sont vaincus! Vous décidez donc de descendre encore plus dans la mine");
            break;
        }


        //Upgrade heros
        for(int q=0; q < listhero.length; q++){
            System.out.println("Vous pouvez améliorer les caractéristiques de " + listhero[q].getName() + "\n1- Augmentation des PV (+10)" + "\n2- Augmentation de la Force (+5)" + "\n3- Augmentation de la défense (+10)" );
            int choixUpgrade = parser.promptWithIntParser("Que voulez-vous choisir?");
            if(choixUpgrade == 1){
                combattant.upHP(listhero[q]);
            }
            if(choixUpgrade == 2){
                combattant.upStrenght(listhero[q]);
            }
            if(choixUpgrade == 3){
                combattant.upDefense(listhero[q]);
            }
        }

        //round five
        Enemy[] listenemy5 = new Enemy[1];
        for (int i = 0; i < 1; i++) {
            Enemy tiamat = new Enemy("Tiamat", 250, 25, 0);
            listenemy5[i] = tiamat;
        }
        //phase d'interaction vague 5
        System.out.println("Vous arrivez enfin dans la salle la plus profonde des mines d'Erebor, vos aventuriers " +
                "s'attendent à tomber sur Smaug mais ils remarquent que ce dragon est bien plus impressionant... " +
                "Il s'agit de " + listenemy5[0].getName() + " qui vient d'arriver que voulez-vous faire?");
        while(IsHeroAlive()){
            while(IsEnnemyAlive(listenemy5)){
                if(!IsHeroAlive()){
                    System.out.println("GAME OVER");
                    break;
                }else{
                    for(int i=0; i < listhero.length; i++){
                        if(!IsEnnemyAlive(listenemy5)){
                            break;
                        }
                        if(listhero[i].gethp() > 0){
                            System.out.println(" c'est au tour de " + listhero[i].getName() + " d'agir. ");
                            int chooseAction = parser.promptWithIntParser("Que voulez faire ?" + "\n1- Attaquer" + "\n2- Se défendre" + "\n3- Fouiller dans le sac sans fond !");
                            if(chooseAction == 1){
                                int compteur = 1;
                                for(int j=0; j < listenemy5.length; j++){ // boucle présentation ennemi
                                    if(listenemy5[j].gethp() <= 0){
                                        listenemy5[j].setHp(0);
                                    }
                                    System.out.println(compteur + "- " + listenemy5[j].getName() + " " + listenemy5[j].gethp() + " pv ");
                                    compteur ++;
                                }
                                for(int m = 0; m < 1; m++){ //boucle vérification ennemi en vie
                                    int chooseVictim = parser.promptWithIntParser("Qui souhaitez-vous attaquer?");
                                    int choixAttaque = parser.promptWithIntParser("Comment voulez-vous attaquer? \n1- Attaquer \n2- Ultime");
                                    if(listhero[i] == c4){ //boucle healer
                                        if(choixAttaque == 1){
                                            listhero[i].setStrength(listhero[i].getstrength() - weapon.batonDeSoin);
                                            if(listenemy5[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy5[chooseVictim-1]);
                                                if(listenemy5[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy5[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy5[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy5[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                            weapon.equipBaton(listhero[i]);
                                        }else if(choixAttaque ==2){
                                            for(int n = 0; n<1; n++){
                                                System.out.println("1-" + listhero[0].getName() + " : " + listhero[0].gethp() + " pv");
                                                System.out.println("2-" + listhero[1].getName() + " : " + listhero[1].gethp() + " pv");
                                                System.out.println("3-" + listhero[2].getName() + " : " + listhero[2].gethp() + " pv");
                                                System.out.println("4-" + listhero[3].getName() + " : " + listhero[3].gethp() + " pv");
                                                int choixHeal = parser.promptWithIntParser("Qui voulez-vous soigner?");
                                                if(listhero[choixHeal - 1].gethp() > 0){
                                                    listhero[choixHeal - 1].setHp(listhero[choixHeal - 1].gethp() + listhero[i].getstrength());
                                                }else{
                                                    System.out.println("ce héros est déjà mort... Ange ne peut pas le ramener à la vie.");
                                                    n--;
                                                }
                                            }
                                        }
                                    } else if(listhero[i] == c3){ //boucle mage
                                        if(choixAttaque == 1){
                                            listhero[i].setStrength(listhero[i].getstrength() - weapon.sceptreDeRacines);
                                            if(listenemy5[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy5[chooseVictim-1]);
                                                if(listenemy5[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy5[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy5[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy5[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                            weapon.equipSceptre(listhero[i]);
                                        }else if(choixAttaque == 2){
                                            if(listenemy5[chooseVictim-1].gethp() > 0){
                                                listhero[i].attack(listenemy5[chooseVictim-1]);
                                                if(listenemy5[chooseVictim-1].gethp() > 0){
                                                    System.out.println(listenemy5[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy5[chooseVictim - 1].gethp() + " pv");
                                                } else {
                                                    System.out.println(listenemy5[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                                }
                                            } else{
                                                m--;
                                                System.out.println("Choisissez une cible en vie!");
                                            }
                                        }
                                    } else {
                                        if(listenemy5[chooseVictim-1].gethp() > 0){
                                            listhero[i].attack(listenemy5[chooseVictim-1]);
                                            if(listenemy5[chooseVictim-1].gethp() > 0){
                                                System.out.println(listenemy5[chooseVictim - 1].getName() + " encaisse et passe à " + listenemy5[chooseVictim - 1].gethp() + " pv");
                                            } else {
                                                System.out.println(listenemy5[chooseVictim - 1].getName() + " reçoit le coup final ! " );
                                            }
                                        } else{
                                            m--;
                                            System.out.println("Choisissez une cible en vie!");
                                        }
                                    }
                                }
                            }
                            if(chooseAction == 2){
                                listhero[i].setHp(listhero[i].gethp() + listhero[i].getdefense());
                            }
                            if(chooseAction == 3){
                                for(int k = 0; k < 1; k++){
                                    System.out.println(listhero[i].getName() + " décide de fouiller dans le sac sans fond et y découvre les objets suivants," +
                                            "\n1- Potion " + potion.cPotion + " disponible et fournit " + potion.recoverPotion + " 10 mana/rage/stamina." +
                                            "\n2- SuperPotion " + potion.cSuperPotion + " disponible et fournit " + potion.recoverSuperPotion + " 20 mana/stamina/rage." +
                                            "\n3- PotionMax " + potion.cPotionMax + " disponible et fournit " + potion.recoverPotionMax + " 30 mana/stamina/rage." +
                                            "\n4- Lembas " + food.cLembas + " disponible et fournit " + food.recoverLembas + " 10 pv." +
                                            "\n5- Limpë " + food.cLimpë + " disponible et fournit " + food.recoverLimpë + " 20 pv." +
                                            "\n6- Miruvor " + food.cMiruvor + " disponible et fournit " + food.recoverMiruvor + " 10 pv." );
                                    int chooseObject = parser.promptWithIntParser(" Quel item voulez vous choisir? ");
                                    if(chooseObject == 1 && potion.cPotion > 0){
                                        potion.usePotion(listhero[i]);
                                    }else if(chooseObject == 2 && potion.cSuperPotion>0){
                                        potion.useSuperPotion(listhero[i]);
                                    }else if(chooseObject == 3 && potion.cPotionMax>0){
                                        potion.usePotionMax(listhero[i]);
                                    }else if(chooseObject == 4 && food.cLembas>0){
                                        food.useLembas(listhero[i]);
                                    }else if(chooseObject == 5 && food.cLimpë>0){
                                        food.useLimpë(listhero[i] );
                                    }else if(chooseObject == 6 && food.cMiruvor>0){
                                        food.useMiruvor(listhero[i]);
                                    }else{
                                        k--;
                                        System.out.println("Choisissez un autre consommable.");
                                    }
                                }
                            }
                        }
                    }
                    for(int i=0; i < listenemy5.length; i++){
                        if(listenemy5[i].gethp() > 0){
                            System.out.println(" c'est au tour de " + listenemy5[i].getName() );
                            for(int l=0; l < 1; l++){
                                Random random2 = new Random();
                                int nbTarget = random2.nextInt(4);
                                if(listhero[nbTarget].gethp() > 0){
                                    System.out.println(listenemy5[i].getName() + " décide d'attaquer " + listhero[nbTarget].getName());
                                    listenemy5[i].attack(listhero[nbTarget]);
                                    if(listhero[nbTarget].gethp() > 0){
                                        System.out.println(listhero[nbTarget].getName() + " encaisse et passe à " + listhero[nbTarget].gethp() + " pv");
                                    } else {
                                        System.out.println(listhero[nbTarget].getName() + " reçoit le coup final ! valar morghulis..." );
                                        listhero[nbTarget].setHp(0);
                                    }
                                }else if(!IsHeroAlive()){
                                    break;
                                }else{
                                    l--;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Tiamat est térrassé, la mine est récupéré ! L'aventure touche à sa fin... ");
            break;
        }




    }
}
