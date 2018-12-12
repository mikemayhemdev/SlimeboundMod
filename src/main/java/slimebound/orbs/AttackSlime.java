package slimebound.orbs;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import slimebound.actions.SlimeAutoAttacking;
import slimebound.vfx.SlimeFlareEffect;


public class AttackSlime
        extends SpawnedSlime {
    public static final String ID = "Slimebound:AttackSlime";

    public AttackSlime() {

        super(ID, 5, 3, true, new Color(.45F, .58F, .58F, 1), SlimeFlareEffect.OrbFlareColor.AGGRESSIVE, new Texture("SlimeboundImages/orbs/3.png"), "SlimeboundImages/orbs/aggressive.png");

    }


    public void updateDescription() {
        this.description = this.descriptions[0] + this.passiveAmount + this.descriptions[1];
    }


    public void activateEffectUnique() {


        AbstractDungeon.actionManager.addToBottom(new SlimeAutoAttacking(AbstractDungeon.player,this.passiveAmount, AbstractGameAction.AttackEffect.BLUNT_LIGHT,this));


    }


    public AbstractOrb makeCopy() {
        return new AttackSlime();
    }
}


