package slimebound.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.LizardTail;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedWordEffect;
import com.megacrit.cardcrawl.vfx.combat.HbBlockBrokenEffect;
import com.megacrit.cardcrawl.vfx.combat.StrikeEffect;
import slimebound.cards.CorrosiveSpit;
import slimebound.cards.Defend_Slimebound;
import slimebound.cards.Split;
import slimebound.cards.Strike_Slimebound;
import slimebound.patches.AbstractCardEnum;
import slimebound.patches.SlimeboundEnum;
import slimebound.relics.AbsorbEndCombat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SlimeboundCharacter extends CustomPlayer {
    public static Color cardRenderColor = new Color(0.0F, 0.1F, 0.0F, 1.0F);
    public float renderscale = 1.0F;
    public float hatX;
    public float hatY;
    public boolean damageVFXDisabled;

    public static final String[] orbTextures = {"SlimeboundImages/char/orb/layer1.png", "SlimeboundImages/char/orb/layer2.png", "SlimeboundImages/char/orb/layer3.png", "SlimeboundImages/char/orb/layer4.png", "SlimeboundImages/char/orb/layer5.png", "SlimeboundImages/char/orb/layer6.png", "SlimeboundImages/char/orb/layer1d.png", "SlimeboundImages/char/orb/layer2d.png", "SlimeboundImages/char/orb/layer3d.png", "SlimeboundImages/char/orb/layer4d.png", "SlimeboundImages/char/orb/layer5d.png"};

    public void setRenderscale(float renderscale) {
        this.renderscale = renderscale;
        reloadAnimation();
    }

    public SlimeboundCharacter(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures, "SlimeboundImages/char/orb/vfx.png", (String) null, (String) null);
        this.initializeClass((String) null, "SlimeboundImages/char/shoulder2.png", "SlimeboundImages/char/shoulder.png", "SlimeboundImages/char/corpse.png", this.getLoadout(), 0.0F, 0.0F, 300.0F, 180.0F, new EnergyManager(3));
        this.reloadAnimation();
        this.dialogX = -200;
        this.dialogY = -200;
    }

    @Override
    public Texture getCutsceneBg() {
        return ImageMaster.loadImage("images/scenes/greenBg.jpg");

    }


    @Override
    public List<CutscenePanel> getCutscenePanels() {
        List<CutscenePanel> panels = new ArrayList();
        panels.add(new CutscenePanel("SlimeboundImages/scenes/slimebound1.png", "VO_SLIMEBOSS_1A"));
        panels.add(new CutscenePanel("SlimeboundImages/scenes/slimebound2.png"));
        panels.add(new CutscenePanel("SlimeboundImages/scenes/slimebound3.png"));
        return panels;
    }




    public void reloadAnimation() {
        this.loadAnimation("SlimeboundImages/char/skeleton.atlas", "SlimeboundImages/char/skeleton.json", renderscale);
        TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.state.addListener(new SlimeAnimListener());
    }


    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add(Strike_Slimebound.ID);
        retVal.add(Strike_Slimebound.ID);
        retVal.add(Strike_Slimebound.ID);
        retVal.add(Strike_Slimebound.ID);
        retVal.add(Defend_Slimebound.ID);
        retVal.add(Defend_Slimebound.ID);
        retVal.add(Defend_Slimebound.ID);
        retVal.add(Defend_Slimebound.ID);
        retVal.add(Split.ID);
        retVal.add(CorrosiveSpit.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList();
        retVal.add(AbsorbEndCombat.ID);
        UnlockTracker.markRelicAsSeen(AbsorbEndCombat.ID);
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo("The Slimebound", "A rogue minion of the Spire, driven to conquer it.", 60, 60, 4, 99, 5, this,

                getStartingRelics(), getStartingDeck(), false);
    }

    public String getTitle(PlayerClass playerClass) {
        return "The Slimebound";
    }

    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.SLIMEBOUND;
    }

    public Color getCardRenderColor() {

        return cardRenderColor;
    }


    public AbstractCard getStartCardForEvent() {
        return new Strike_Slimebound();
    }

    public Color getCardTrailColor() {
        return cardRenderColor.cpy();
    }

    public int getAscensionMaxHPLoss() {
        return 10;
    }

    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontGreen;
    }

    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("SLIME_SPLIT", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    public String getCustomModeCharacterButtonSoundKey() {
        return "SLIME_SPLIT";
    }

    public String getLocalizedCharacterName() {
        return "The Slimebound";
    }

    public AbstractPlayer newInstance() {
        return new SlimeboundCharacter("The Slimebound", SlimeboundEnum.SLIMEBOUND);
    }

    public String getSpireHeartText() {
        return "Must... absorb... the Heart...";
    }

    public Color getSlashAttackColor() {
        return Color.GREEN;
    }

    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }


    public String getVampireText() {
        return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[5];
    }

    @Override
    public void applyStartOfTurnCards() {
        super.applyStartOfTurnCards();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        this.hatX = this.skeleton.findBone("eyeback1").getX();
        this.hatY = this.skeleton.findBone("eyeback1").getY();

    }


}


