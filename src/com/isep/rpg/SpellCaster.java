package com.isep.rpg;

public abstract class SpellCaster extends Hero{
    private int magic;

    public SpellCaster(String name, int hp, int strength, int defense,int magic) {
        super(name, hp, strength, defense);
        this.magic = magic;
    }

    //getter
    public int getmagic(){
        return this.magic;
    }
    public void setMagic(int magic){this.magic = magic;}

    public void changeEffect(int recovery) {
        magic += recovery;
    }

    public void looseMana(SpellCaster spellCaster){
        setMagic(spellCaster.getmagic() - 5);
    }
}
