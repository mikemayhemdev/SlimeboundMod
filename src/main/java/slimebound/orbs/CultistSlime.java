/*    */ package slimebound.orbs;
/*    */ 

/*    */

/*    */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
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
/*    */ public class CultistSlime
/*    */   extends SpawnedSlime
/*    */ {
/*    */   public CultistSlime()
/*    */   {
/* 25 */     super("CultistSlime", 6, true, new Color(.4F,.45F,.63F,1),SlimeFlareEffect.OrbFlareColor.CULTIST,new Texture("SlimeboundImages/orbs/attackBuff.png"),"SlimeboundImages/orbs/cultist.png");
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
/* 38 */

/*    */
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),
            new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            this.applyUniqueFocus(2);

/*    */     }
/*    */
/*    */   
/*    */   public AbstractOrb makeCopy() {
/* 54 */     return new CultistSlime();
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\TheDisciple.jar!\chronomuncher\orbs\BronzeSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */