/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.ShieldParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.BuffParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.actions.SlimeSpawnAction;
import slimebound.orbs.TorchHeadSlime;
import slimebound.patches.AbstractCardEnum;
import slimebound.vfx.SlimeBuffParticleEffect;

import java.util.ArrayList;

/*    */
/*    */ public class DuplicateSlimes extends CustomCard
        /*    */ {
    /*    */   public static final String ID = "DuplicateSlimes";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/duplicateslimes.png";

    /*     */public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output
    /* 17 */   private static final CardType TYPE = CardType.SKILL;
    /* 18 */   private static final CardRarity RARITY = CardRarity.RARE;
    /* 19 */   private static final CardTarget TARGET = CardTarget.SELF;
    /*    */                private static final CardStrings cardStrings;

    /*    */   private static final int COST = 2;
    /*    */   private static final int BLOCK = 5;
    /*    */   private static final int UPGRADE_BONUS = 3;
    /*    */
    /*    */   public DuplicateSlimes()
    /*    */   {
        /* 27 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

        /*    */
        /* 29 */     this.exhaust=true;

        /*    */   }
    /*    */
    /*    */   public void use(AbstractPlayer p, AbstractMonster m)
    /*    */   {
        //AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new SlimeBuffParticleEffect(p.hb_x,p.hb_y,Color.GREEN), 2F, true));

        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new BorderFlashEffect(Color.GREEN, true), 0.05F, true));
        int orbsize = p.orbs.size();
        int i = 0;
        String[] tempOrbs = new String[orbsize];
        for (AbstractOrb o : p.orbs) {
            if (o.ID == "TorchHeadSlime" ||
                    o.ID == "AttackSlime" ||
                    o.ID == "PoisonSlime" ||
                    o.ID == "SlimingSlime" ||
                    o.ID == "BronzeSlime" ||
                    o.ID == "DebuffSlime" ||
                    o.ID == "CultistSlime" ||
                    o.ID == "HexSlime") {
                logger.info("Found a slime: "+ o.ID);
            tempOrbs[i] = o.ID;
            }
            i++;
        }
            logger.info("Moving to spawn mode.");
        for (String s : tempOrbs) { if(s!=null) {
            logger.info("Attempting to spawn a " + s);
            if (s == "TorchHeadSlime") {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.TorchHeadSlime(), false, false));
            }
            if (s == "AttackSlime") {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.AttackSlime(), false, false));
            }
            if (s == "PoisonSlime") {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.PoisonSlime(), false, false));
            }
            if (s == "SlimingSlime") {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SlimingSlime(), false, false));
            }
            if (s == "BronzeSlime") {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.BronzeSlime(), false, false));
            }
            if (s == "DebuffSlime") {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.DebuffSlime(), false, false));
            }
            if (s == "CultistSlime") {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.CultistSlime(), false, false));
            }
            if (s == "HexSlime") {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.HexSlime(), false, false));
            }

        }
        }

        logger.info("Finished.");
        }

        /* 35 */
    /*    */
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
    /*    */   public AbstractCard makeCopy()
    /*    */   {
        /* 40 */     return new DuplicateSlimes();
        /*    */   }
    /*    */
    /*    */   public void upgrade()
    /*    */   {
        /* 45 */     if (!this.upgraded)
            /*    */     {
            /* 47 */       upgradeName();
            upgradeBaseCost(1);


            /*    */     }
        /*    */   }
    /*    */ }

