 package slimebound.orbs;






import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import slimebound.vfx.SlimeFlareEffect;


 public class AttackSlime
   extends SpawnedSlime

 {

   public AttackSlime()
   {
     super("AttackSlime", 5, true, new Color(.45F,.58F,.58F,1), SlimeFlareEffect.OrbFlareColor.AGGRESSIVE,  new Texture("SlimeboundImages/orbs/3.png"),"SlimeboundImages/orbs/aggressive.png");

            }


    public void updateDescription()

 {
    this.description = this.descriptions[0] + this.passiveAmount + this.descriptions[1];}








   public void activateEffectUnique()
   {






            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.getMonsters().getRandomMonster(true),
            new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS),
            AbstractGameAction.AttackEffect.BLUNT_HEAVY));


     }


   public AbstractOrb makeCopy() {
     return new AttackSlime();
   }
 }


