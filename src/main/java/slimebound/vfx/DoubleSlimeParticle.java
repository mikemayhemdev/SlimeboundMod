package slimebound.vfx;


import basemod.BaseMod;
import basemod.animations.AbstractAnimation;
import basemod.animations.SpineAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.ShaderHelper;
import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;


public class DoubleSlimeParticle extends com.megacrit.cardcrawl.vfx.AbstractGameEffect {
    private float scale = 1.5F;
    private static int W;
    private Texture img;
    private float x;
    private float px;
    public static SkeletonMeshRenderer sr;
    private AbstractCreature.CreatureAnimation animation;
    private float animationTimer;
    private TextureAtlas atlas;
    private Skeleton skeleton;
    public AnimationState state;
    private AnimationStateData stateData;
    private AbstractAnimation animationA;
    public AbstractPlayer p;
    private float y;

    public DoubleSlimeParticle(AbstractPlayer p) {
        this.atlas = new TextureAtlas(Gdx.files.internal("SlimeboundImages/char/skeleton.atlas"));
        SkeletonJson json = new SkeletonJson(this.atlas);



        json.setScale(Settings.scale / scale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("SlimeboundImages/char/skeleton.json"));
        this.skeleton = new Skeleton(skeletonData);
        this.skeleton.setColor(Color.WHITE);
        this.stateData = new AnimationStateData(skeletonData);
        this.state = new AnimationState(this.stateData);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.state.addListener(new SlimeAnimListener());
        this.duration = 0.05F;
        this.p = p;
        this.px = p.hb.cX;
        this.x = ((p.hb.cX - W / 2.0F) + (100 * Settings.scale));
        this.y = ((p.hb.cY - W / 2.0F) - (95 * Settings.scale));
        this.renderBehind = true;
       // this.animationA.renderModel(batch, env);
        //BaseMod.publishAnimationRender(sb);
    }



    public void dispose() {


    }
    public void update() {


    }
    public void finish(){
        this.isDone = true;

    }


    public void render(SpriteBatch sb) {


        if (this.atlas == null) {
            sb.setColor(new Color(1F, 1F, 1F, 2F));
            sb.draw(this.img, this.x - (float) this.img.getWidth() * Settings.scale / 2.0F, this.y + AbstractDungeon.sceneOffsetY, (float) this.img.getWidth() * Settings.scale, (float) this.img.getHeight() * Settings.scale, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
        } else {
            this.state.update(Gdx.graphics.getDeltaTime());
            this.state.apply(this.skeleton);
            this.skeleton.updateWorldTransform();
            this.skeleton.setPosition(this.x, this.y + AbstractDungeon.sceneOffsetY);
            this.skeleton.setColor(new Color(1F, 1F, 1F, 2F));
            // this.skeleton.setFlip(this.flipHorizontal, this.flipVertical);
            sb.end();
            CardCrawlGame.psb.begin();
            AbstractMonster.sr.draw(CardCrawlGame.psb, this.skeleton);
            CardCrawlGame.psb.end();
            sb.begin();
            sb.setBlendFunction(770, 771);


        }
    }




}


