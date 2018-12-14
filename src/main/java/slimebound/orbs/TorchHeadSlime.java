package slimebound.orbs;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.TorchHeadFireEffect;
import slimebound.actions.SlimeAutoAttacking;
import slimebound.vfx.SlimeFlareEffect;


public class TorchHeadSlime
        extends SpawnedSlime {
    public static final String ID = "Slimebound:TorchHeadSlime";


    private float fireTimer = 0.0F;
    private static final float FIRE_TIME = 0.04F;

    public TorchHeadSlime() {

        super(ID, "images/monsters/theBottom/slimeAltM/skeleton.atlas","images/monsters/theBottom/slimeAltM/skeleton.json","idle",1.5F,new Color(0.75F,0.75F,0.75F,2F),9, 0,true, new Color(.65F, .65F, .57F, 1), SlimeFlareEffect.OrbFlareColor.TORCHHEAD, new Texture("SlimeboundImages/orbs/5.png"), "SlimeboundImages/orbs/Torchhead.png");

    }


    public void updateDescription() {
        this.description = this.descriptions[0] + this.passiveAmount + this.descriptions[1];
    }


    public void activateEffectUnique() {

        AbstractDungeon.actionManager.addToBottom(new SlimeAutoAttacking(AbstractDungeon.player,this.passiveAmount, AbstractGameAction.AttackEffect.FIRE,this));



    }


    public AbstractOrb makeCopy() {

        return new TorchHeadSlime();

    }


    public void update() {
        super.update();

        this.fireTimer -= Gdx.graphics.getDeltaTime();
        if (this.fireTimer < 0.0F) {
            this.fireTimer = 0.04F;
            AbstractDungeon.effectList.add(new TorchHeadFireEffect(this.cX, this.cY + 15));

        }

    }
}


