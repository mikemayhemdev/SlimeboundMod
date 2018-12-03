/*    */ package slimebound.orbs;
/*    */ 

/*    */

/*    */ import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */
        /*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.relics.Orichalcum;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
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
/*    */ public class BronzeSlime
/*    */   extends SpawnedSlime
/*    */ {
/*    */   public BronzeSlime()
/*    */   {
/* 25 */     super("BronzeSlime", 6, false, new Color(.63F,.58F,.41F,1),SlimeFlareEffect.OrbFlareColor.BRONZE,new Texture("SlimeboundImages/orbs/attackDefend.png"),"SlimeboundImages/orbs/bronzeslime.png");
            }
/*    */   
/*    */
/*    */    public void updateDescription()

/*     */ {
    this.description = this.descriptions[0] + this.passiveAmount + this.descriptions[1] + this.passiveAmount + this.descriptions[2];}


/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void activateEffectUnique()
/*    */   {
/* 38 */
            AbstractMonster mo = AbstractDungeon.getMonsters().getRandomMonster(true);
/*    */AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(this.cX,this.cY, mo.hb.cX, mo.hb.cY) ));

    AbstractDungeon.actionManager.addToBottom(new DamageAction(mo,
            new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS),
            AbstractGameAction.AttackEffect.NONE));

            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.passiveAmount));
/*    */
/*    */}
/*    */   
/*    */   public AbstractOrb makeCopy() {return new BronzeSlime();}
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\TheDisciple.jar!\chronomuncher\orbs\BronzeSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */