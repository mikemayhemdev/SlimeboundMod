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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.PoisonPower;
import slimebound.powers.SlimedPower;
import slimebound.vfx.SlimeFlareEffect;

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
/*    */ public class SlimingSlime
/*    */   extends SpawnedSlime
/*    */ {
/*    */   public SlimingSlime()
/*    */   {
/* 25 */     super("SlimingSlime", 3, true, new Color(.6F,.47F,.59F,1),SlimeFlareEffect.OrbFlareColor.SLIMING,new Texture("SlimeboundImages/orbs/debuff2.png"),"SlimeboundImages/orbs/sliming.png");
            }
/*    */
/*    */
/*    */    public void updateDescription()

/*     */ {
    this.description = this.descriptions[0] + this.passiveAmount + this.descriptions[1];}


/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */   public void activateEffectUnique()
/*    */   {


/*    */
                AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(true);
                 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, AbstractDungeon.player, new SlimedPower(mo, AbstractDungeon.player, this.passiveAmount), this.passiveAmount, true, AbstractGameAction.AttackEffect.POISON));
    /*    */     }
/*    */
/*    */   
/*    */   public AbstractOrb makeCopy() {
/* 54 */     return new SlimingSlime();
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\TheDisciple.jar!\chronomuncher\orbs\BronzeSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */