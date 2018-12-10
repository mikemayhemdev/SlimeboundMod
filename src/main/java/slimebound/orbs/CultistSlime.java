package slimebound.orbs;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import slimebound.actions.SlimeAutoAttacking;
import slimebound.actions.SlimeAutoCultist;
import slimebound.vfx.SlimeFlareEffect;


public class CultistSlime
        extends SpawnedSlime {
    public CultistSlime() {
        super("CultistSlime", 6,0, true, new Color(.4F, .45F, .63F, 1), SlimeFlareEffect.OrbFlareColor.CULTIST, new Texture("SlimeboundImages/orbs/attackBuff.png"), "SlimeboundImages/orbs/cultist.png");
    }


    public void updateDescription() {
        this.description = this.descriptions[0] + this.passiveAmount + this.descriptions[1];
    }


    public void activateEffectUnique() {

        AbstractDungeon.actionManager.addToBottom(new SlimeAutoCultist(AbstractDungeon.player,this.passiveAmount, AbstractGameAction.AttackEffect.SLASH_DIAGONAL,this));


    }


    public AbstractOrb makeCopy() {
        return new CultistSlime();
    }
}


