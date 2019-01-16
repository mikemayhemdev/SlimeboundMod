package slimebound.cards;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import slimebound.SlimeboundMod;


public abstract class AbstractSlimeboundCard extends CustomCard {
    public AbstractSlimeboundCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color,
                               CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type,
                color, rarity, target);
    }

    public int selfDamage;
    public boolean upgradeSelfDamage;
    public boolean isSelfDamageModified;
    public int poison;
    public boolean upgradePoison;
    public boolean isPoisonModified;

    public int slimed;
    public int baseSlimed;
    public boolean isSlimedModified;
    public boolean upgradeSlimed;

    public void upgradeSlimed(int amount) {
        this.baseSlimed += amount;
        this.slimed = this.baseSlimed + SlimeboundMod.getAcidTongueBonus(AbstractDungeon.player);
        if (this.slimed > this.baseSlimed || amount > 0) this.isSlimedModified = true;
    }

    public void upgradeLickSlimed(int amount) {
        this.baseSlimed += amount;
        this.slimed = this.baseSlimed + SlimeboundMod.getGluttonyBonus(AbstractDungeon.player);
        if (this.slimed > this.baseSlimed || amount > 0) this.isSlimedModified = true;
    }

}