/*    */ package slimebound.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.ShieldParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.BuffParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.PowerBuffEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import slimebound.orbs.SpawnedSlime;

/*    */
/*    */ public class SlimeSacrificePower extends AbstractPower
/*    */ {
    /*    */   public static final String POWER_ID = "SlimeSacrifice";
    /*    */   public static final String NAME = "Slime Sacrifice";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    /*    */   public static final String IMG = "powers/SlimeSacrificeS.png";

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;
/*    */   
/*    */   public SlimeSacrificePower(AbstractCreature owner, int bufferAmt) {
/* 16 */      this.name = NAME;
    /* 24 */
    this.ID = POWER_ID;
    this.amount = bufferAmt;
    /* 25 */
    this.owner = owner;
    /* 26 */
    /*    */
    /* 28 */
    this.img = new com.badlogic.gdx.graphics.Texture(slimebound.SlimeboundMod.getResourcePath(IMG));
    /* 29 */
    this.type = POWER_TYPE;
    /* 30 */
    DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
    /*  84 */
    this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
/* 20 */     updateDescription();
/* 21 */
/*    */   }



    /*    */
/*    */    public int onAttacked(DamageInfo info, int damageAmount)
/*    */ {
    /* 26 */
        if (info.type == DamageInfo.DamageType.NORMAL) {
            if (damageAmount > AbstractDungeon.player.currentBlock) {
                if (!AbstractDungeon.player.orbs.isEmpty()) {
                    for (AbstractOrb o : AbstractDungeon.player.orbs) {

                        if (o.ID == "TorchHeadSlime" ||
                                o.ID == "AttackSlime" ||
                                o.ID == "PoisonSlime" ||
                                o.ID == "SlimingSlime" ||
                                o.ID == "BronzeSlime" ||
                                o.ID == "DebuffSlime" ||
                                o.ID == "CultistSlime" ||
                                o.ID == "HexSlime") { // when equipped (picked up) this relic counts how many ethereal cards are in the player's deck

                            o.evokeAmount = 0;
                            this.flash();
                            com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new VFXAction(new ShieldParticleEffect(o.cX, o.cY)));
                            com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, this.ID, 1));
                            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.defect.EvokeOrbAction(1));
                            return AbstractDungeon.player.currentBlock;

                        }
                    }
                }
            }
    }
    return damageAmount;
}
/*    */   
/*    */   public void stackPower(int stackAmount)
/*    */   {
/* 34 */     this.fontScale = 8.0F;
/* 35 */     this.amount += stackAmount;
/*    */   }

    public void atEndOfRound()
        /*    */ {
        /* 38 */
        /* 42 */    // if (AbstractDungeon.player.hasRelic("WalkingCane")) {
        /* 43 */      // AbstractDungeon.player.getRelic("WalkingCane").flash();
        /* 44 */     //  return;
        /*    */    // }
        /* 46 */

            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "SlimeSacrifice"));

    }
/*    */   
/*    */   public void updateDescription()
/*    */   {
/* 40 */     if (this.amount <= 1) {
/* 41 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 43 */       this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\powers\SlimeSacrificePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */