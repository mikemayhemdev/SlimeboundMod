/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
/*    */

/*    */
/*    */ public class CoordinatedStrike extends CustomCard
/*    */ {
/*    */   public static final String ID = "Coordinated";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
/*    */   public static final String IMG_PATH = "cards/coordinatedstrike.png";
/* 19 */   private static final CardType TYPE = CardType.ATTACK;
/* 20 */   private static final CardRarity RARITY = CardRarity.UNCOMMON;
/* 21 */   private static final CardTarget TARGET = CardTarget.ENEMY;

    /*    */                private static final CardStrings cardStrings;
/*    */   private static final int COST = -1;
/*    */   private static final int POWER = 6;
/*    */   private static final int UPGRADE_BONUS = 3;
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

/*    */   public CoordinatedStrike()
/*    */   {
/* 29 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
/*    */
/* 31 */     this.baseDamage = 3;
/* 33 */     this.tags.add(CardTags.STRIKE);
            this.isMultiDamage = true;
            this.exhaust = true;
/*    */   }
/*    */
/*    */   public void use(AbstractPlayer p, AbstractMonster m)
/*    */   {
                if (this.energyOnUse < EnergyPanel.totalCount) {
                 this.energyOnUse = EnergyPanel.totalCount;
            }
/* 38 */     com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new slimebound.actions.CoordinateAction(p,m, this.baseDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));


/*    */       }


    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
/*    */   
/*    */ 
/*    */   public AbstractCard makeCopy()
/*    */   {
/* 44 */     return new CoordinatedStrike();
/*    */   }
/*    */   
/*    */   public void upgrade()
/*    */   {
/* 49 */     if (!this.upgraded)
/*    */     {
/* 51 */       upgradeName();
/* 52 */       this.exhaust = false;
        this.rawDescription = UPGRADED_DESCRIPTION;
        this.initializeDescription();
/*    */     }
/*    */   }


/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\cards\Strike_Slimebound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */