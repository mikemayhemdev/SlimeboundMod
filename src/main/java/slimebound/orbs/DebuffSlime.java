/*    */ package slimebound.orbs;
/*    */ 

/*    */

/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import slimebound.vfx.SlimeFlareEffect;

import java.util.Random;

/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DebuffSlime
        extends SpawnedSlime
/*    */ {

    public DebuffSlime()
    /*    */ {

        super("DebuffSlime", 2,true, new Color(.83F,.83F,.39F,1), SlimeFlareEffect.OrbFlareColor.LICKING, new Texture("SlimeboundImages/orbs/attackDebuff.png"),"SlimeboundImages/orbs/licking.png");
    }

    /*    */
    /*    */
    /*    */
    public void updateDescription()

    /*     */ {
        this.description = this.descriptions[0] + this.passiveAmount + this.descriptions[1];
    }


    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    public void activateEffectUnique()
    /*    */ {
        /* 38 */


        /*    */
        AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(true);

        AbstractDungeon.actionManager.addToBottom(new DamageAction(mo,
                new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        Random random = new Random();


            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new WeakPower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));


        }
        /*    */
        /*    */
        /*    */
        public AbstractOrb makeCopy() {return new DebuffSlime();}
            /*    */   }






/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\TheDisciple.jar!\chronomuncher\orbs\BronzeSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */