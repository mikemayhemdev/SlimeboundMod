/*    */ package slimebound.powers;
/*    */
/*    */

/*    */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;

/*    */
/*    */
        /*    */
        /*    */

/*    */
/*    */ public class DuplicatedFormPower extends AbstractPower
/*    */ {
    /*    */   public static final String POWER_ID = "DuplicatedFormPower";
    /*    */   public static final String NAME = "Potency";
                public static PowerType POWER_TYPE = PowerType.BUFF;
    /*    */   public static final String IMG = "powers/DuplicatedEchoS.png";
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

    /* 14 */   public static String[] DESCRIPTIONS;
    /*    */   private AbstractCreature source;
    /* 18 */   private int cardsDoubledThisTurn = 0;

    /*    */
    /*    */
    /*    */
    public DuplicatedFormPower(AbstractCreature owner, AbstractCreature source, int amount)
    /*    */ {
        /* 23 */
        this.name = NAME;
        /* 24 */
        this.ID = POWER_ID;

        /* 25 */
        this.owner = owner;
        /* 26 */
        this.source = source;
        /*    */
        /* 28 */
        this.img = new com.badlogic.gdx.graphics.Texture(SlimeboundMod.getResourcePath(IMG));
        /* 29 */
        this.type = POWER_TYPE;
        /* 30 */
        this.amount = amount;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        /*  84 */
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        /* 31 */
        updateDescription();
        /*    */
    }

    public void atStartOfTurn() {
        this.cardsDoubledThisTurn = 0;
    }

    /*    */
    /*    */
    public void updateDescription()
    /*    */ {
        /* 36 */
        /* 37 */
        if (this.amount == 1) {
            /* 33 */       this.description = DESCRIPTIONS[0];
            /*    */     } else {
            /* 35 */       this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
            /*    */     }
        /*    */
        /*    */
    }


    public void onUseCard(AbstractCard card, UseCardAction action)
        /*    */   {
        /* 46 */     if ((!card.purgeOnUse) && (this.amount > 0) && (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - this.cardsDoubledThisTurn <= this.amount))
            /*    */     {
            /* 48 */       this.cardsDoubledThisTurn += 1;
            /* 49 */       flash();
            /* 50 */       AbstractMonster m = null;
            /*    */
            /* 52 */       if (action.target != null) {
                /* 53 */         m = (AbstractMonster)action.target;
                /*    */       }
            /*    */
            /* 56 */       AbstractCard tmp = card.makeSameInstanceOf();
            /* 57 */       AbstractDungeon.player.limbo.addToBottom(tmp);
            /* 58 */       tmp.current_x = card.current_x;
            /* 59 */       tmp.current_y = card.current_y;
            /* 60 */       tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
            /* 61 */       tmp.target_y = (Settings.HEIGHT / 2.0F);
            /* 62 */       tmp.freeToPlayOnce = true;
            /*    */
            /* 64 */       if (m != null) {
                /* 65 */         tmp.calculateCardDamage(m);
                /*    */       }
            /*    */
            /* 68 */       tmp.purgeOnUse = true;
            /* 69 */       AbstractDungeon.actionManager.cardQueue.add(new com.megacrit.cardcrawl.cards.CardQueueItem(tmp, m, card.energyOnUse));
            /*    */     }
        /*    */   }
}
/*    */


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\powers\SearingPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */