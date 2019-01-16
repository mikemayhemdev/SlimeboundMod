package slimebound.potions;


import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import slimebound.SlimeboundMod;
import slimebound.powers.SlimedPower;


public class SlimedPotion extends CustomPotion {
    public static final String POTION_ID = "Slimebound:SlimedPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;


    public SlimedPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.BOTTLE, PotionColor.WEAK);
        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1]);
        this.isThrown = true;
        this.targetRequired = true;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(target, AbstractDungeon.player, new SlimedPower(target, AbstractDungeon.player, this.potency), this.potency));
    }


    public CustomPotion makeCopy() {
        return new SlimedPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 10 + SlimeboundMod.getAcidTongueBonus(AbstractDungeon.player);
    }
}


