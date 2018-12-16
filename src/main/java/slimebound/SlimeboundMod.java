package slimebound;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.SmokePuffEffect;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.cards.*;
import slimebound.characters.SlimeboundCharacter;
import slimebound.helpers.PoisonVariable;
import slimebound.helpers.SelfDamageVariable;
import slimebound.orbs.SpawnedSlime;
import slimebound.orbs.TorchHeadSlime;
import slimebound.patches.AbstractCardEnum;
import slimebound.patches.SlimeboundEnum;
import slimebound.potions.SlimedPotion;
import slimebound.relics.*;

import java.nio.charset.StandardCharsets;


@com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
public class SlimeboundMod implements PostBattleSubscriber, PostInitializeSubscriber, PreMonsterTurnSubscriber, OnCardUseSubscriber, basemod.interfaces.EditCharactersSubscriber, basemod.interfaces.EditRelicsSubscriber, basemod.interfaces.EditCardsSubscriber, basemod.interfaces.EditKeywordsSubscriber, EditStringsSubscriber, basemod.interfaces.PostDrawSubscriber, basemod.interfaces.PostPowerApplySubscriber, basemod.interfaces.OnStartBattleSubscriber {
    private static final com.badlogic.gdx.graphics.Color SLIME_COLOR = com.megacrit.cardcrawl.helpers.CardHelper.getColor(25.0F, 95.0F, 25.0F);

    private static final String SLIMEBOUNDMOD_ASSETS_FOLDER = "SlimeboundImages";

    private static final String ATTACK_CARD = "512/bg_attack_slimebound.png";
    private static final String SKILL_CARD = "512/bg_skill_slimebound.png";
    private static final String POWER_CARD = "512/bg_power_slimebound.png";
    private static final String ENERGY_ORB = "512/card_slimebound_orb.png";
    private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";

    private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_slimebound.png";
    private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_slimebound.png";
    private static final String POWER_CARD_PORTRAIT = "1024/bg_power_slimebound.png";
    private static final String ENERGY_ORB_PORTRAIT = "1024/card_slimebound_orb.png";

    private static final String CHAR_BUTTON = "charSelect/button.png";
    private static final String CHAR_PORTRAIT = "charSelect/portrait.png";
    public static int powersPlayedThisCombat;
    public static boolean slimeDelay;
    public static SlimeboundCharacter slimeboundCharacter;
    public static boolean slimeTalked = false;
    public static boolean slimeTalkedAcidL = false;
    public static boolean slimeTalkedAcidM = false;
    public static boolean slimeTalkedAcidS = false;
    public static boolean slimeTalkedSpikeL = false;
    public static boolean slimeTalkedSpikeM = false;
    public static boolean slimeTalkedSpikeS = false;
    public static int slimeTalkedDark = 0;
    public static boolean slimeTalkedCollector = false;
    public static boolean spritealtered = false;
    public static boolean bumpnextlime = false;
    public static boolean disabledStrikeVFX = false;
    public static SpawnedSlime mostRecentSlime;
    @SpireEnum public static AbstractCard.CardTags LICK;
    @SpireEnum public static AbstractCard.CardTags TACKLE;
    @SpireEnum public static AbstractCard.CardTags STUDY_HEXAGHOST;
    @SpireEnum public static AbstractCard.CardTags STUDY_AWAKENEDONE;
    @SpireEnum public static AbstractCard.CardTags STUDY_TIMEEATER;
    @SpireEnum public static AbstractCard.CardTags STUDY_CHAMP;
    @SpireEnum public static AbstractCard.CardTags STUDY_COLLECTOR;
    @SpireEnum public static AbstractCard.CardTags STUDY_SHAPES;
    @SpireEnum public static AbstractCard.CardTags STUDY_GUARDIAN;
    @SpireEnum public static AbstractCard.CardTags STUDY_AUTOMATON;
    @SpireEnum public static AbstractCard.CardTags STUDY;




    public static final String getResourcePath(String resource) {
        return "SlimeboundImages/" + resource;
    }

    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public SlimeboundMod() {

        BaseMod.subscribe(this);

        BaseMod.addColor(AbstractCardEnum.SLIMEBOUND,
                SLIME_COLOR, SLIME_COLOR, SLIME_COLOR, SLIME_COLOR, SLIME_COLOR, SLIME_COLOR, SLIME_COLOR,
                getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD),
                getResourcePath(POWER_CARD), getResourcePath(ENERGY_ORB),
                getResourcePath(ATTACK_CARD_PORTRAIT), getResourcePath(SKILL_CARD_PORTRAIT),
                getResourcePath(POWER_CARD_PORTRAIT), getResourcePath(ENERGY_ORB_PORTRAIT), getResourcePath(CARD_ENERGY_ORB));
    }

    public static void initialize() {
        new SlimeboundMod();
    }

    public void receiveEditCharacters() {

        slimeboundCharacter = new SlimeboundCharacter("TheSlimebound", SlimeboundEnum.SLIMEBOUND);
        BaseMod.addCharacter(slimeboundCharacter, getResourcePath("charSelect/button.png"), getResourcePath("charSelect/portrait.png"), SlimeboundEnum.SLIMEBOUND);

    }

    public void receivePostPowerApplySubscriber(AbstractPower power, AbstractCreature target, AbstractCreature source) {

        if (power.ID == "Strength") {

            if (target == AbstractDungeon.player) {

                for (AbstractOrb o : AbstractDungeon.player.orbs) {

                    if (o.ID == TorchHeadSlime.ID) {
                        logger.info("Sending power amount" + power.amount);
                        ((TorchHeadSlime) o).applyUniqueFocus(power.amount);
                    }
                }


            }
            ;
        }
    }

public static String printString(String s){
        logger.info(s);
        return s;
}
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new AbsorbEndCombat(), AbstractCardEnum.SLIMEBOUND);
        BaseMod.addRelicToCustomPool(new AbsorbEndCombatUpgraded(), AbstractCardEnum.SLIMEBOUND);
        BaseMod.addRelicToCustomPool(new AggressiveSlimeRelic(), AbstractCardEnum.SLIMEBOUND);
        BaseMod.addRelicToCustomPool(new MaxSlimesRelic(), AbstractCardEnum.SLIMEBOUND);
        BaseMod.addRelicToCustomPool(new PotencyRelic(), AbstractCardEnum.SLIMEBOUND);
        BaseMod.addRelicToCustomPool(new PreparedRelic(), AbstractCardEnum.SLIMEBOUND);
        BaseMod.addRelicToCustomPool(new SlimedTailRelic(), AbstractCardEnum.SLIMEBOUND);
        BaseMod.addRelicToCustomPool(new StudyCardRelic(), AbstractCardEnum.SLIMEBOUND);
    }


    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SelfDamageVariable());
        BaseMod.addDynamicVariable(new PoisonVariable());

        BaseMod.addCard(new slimebound.cards.Defend_Slimebound());
        BaseMod.addCard(new slimebound.cards.Strike_Slimebound());
        BaseMod.addCard(new SplitBronze());
        BaseMod.addCard(new LevelUp());
        BaseMod.addCard(new SplitAttack());
        BaseMod.addCard(new SplitTorchHead());
        BaseMod.addCard(new StudyTheSpire());
        BaseMod.addCard(new SplitCultist());
        BaseMod.addCard(new SplitPoison());
        BaseMod.addCard(new SplitShield());
        BaseMod.addCard(new SplitSliming());
        BaseMod.addCard(new slimebound.cards.SlimeSacrifice());
        //BaseMod.addCard(new slimebound.cards.zzzAbsorbAll());
        BaseMod.addCard(new Overexert());
        BaseMod.addCard(new Split());
        BaseMod.addCard(new SuperSplit());
        BaseMod.addCard(new DivideAndConquer());
        BaseMod.addCard(new LeadByExample());
        BaseMod.addCard(new slimebound.cards.SlimeTap());
        BaseMod.addCard(new Teamwork());
        BaseMod.addCard(new slimebound.cards.SlimeBarrage());
        BaseMod.addCard(new SlimeBrawl());
        //BaseMod.addCard(new slimebound.cards.zzzMaxSlimes());
        BaseMod.addCard(new SlimeSpikes());
        BaseMod.addCard(new FormABlockade());
        BaseMod.addCard(new SpikyOuterGoop());
        BaseMod.addCard(new MassRepurpose());
        BaseMod.addCard(new DouseInSlime());
        BaseMod.addCard(new Chomp());
        BaseMod.addCard(new StrayGoop());
        BaseMod.addCard(new OozeBath());
        //BaseMod.addCard(new zzzSoTasty());
        BaseMod.addCard(new LivingWall());
        BaseMod.addCard(new GangUp());
        BaseMod.addCard(new SolidOuterGoop());
        BaseMod.addCard(new slimebound.cards.Dissolve());
        BaseMod.addCard(new slimebound.cards.DuplicatedForm());
        BaseMod.addCard(new slimebound.cards.LeechingTouch());
        BaseMod.addCard(new Regenerate());
        BaseMod.addCard(new FormOfPuddle());
        BaseMod.addCard(new slimebound.cards.Lick());
        BaseMod.addCard(new slimebound.cards.MegaLick());

        BaseMod.addCard(new PressTheAttack());
        BaseMod.addCard(new SoulSicken());
       // BaseMod.addCard(new slimebound.cards.zzzFocusedLick());
        BaseMod.addCard(new HauntingLick());
        BaseMod.addCard(new AcidGelatin());
        BaseMod.addCard(new QuickLick());
        BaseMod.addCard(new slimebound.cards.TongueLash());
        BaseMod.addCard(new ItLooksTasty());
        BaseMod.addCard(new slimebound.cards.AcidTongue());
        BaseMod.addCard(new slimebound.cards.TendrilStrike());
        BaseMod.addCard(new slimebound.cards.PoisonLick());
        BaseMod.addCard(new slimebound.cards.WasteNot());
        BaseMod.addCard(new HungryTackle());
        BaseMod.addCard(new slimebound.cards.FlameTackle());
        BaseMod.addCard(new RollThrough());
        BaseMod.addCard(new ComboTackle());
        BaseMod.addCard(new GoopTackle());
        BaseMod.addCard(new VenomTackle());
        BaseMod.addCard(new slimebound.cards.Grow());
        BaseMod.addCard(new slimebound.cards.Prepare());
        BaseMod.addCard(new slimebound.cards.Gluttony());
        BaseMod.addCard(new slimebound.cards.UsefulSlime());
        BaseMod.addCard(new RainOfGoop());
        BaseMod.addCard(new slimebound.cards.GoopSpray());
        BaseMod.addCard(new slimebound.cards.MassFeed());
        BaseMod.addCard(new SlimeSmash());
        BaseMod.addCard(new slimebound.cards.LeechEnergy());
        BaseMod.addCard(new LeechLife());
        BaseMod.addCard(new Equalize());

        BaseMod.addCard(new DisruptingSlam());
        BaseMod.addCard(new slimebound.cards.ChargeUp());
        BaseMod.addCard(new slimebound.cards.GuardianWhirl());
        BaseMod.addCard(new slimebound.cards.CorrosiveSpit());
        BaseMod.addCard(new slimebound.cards.DefensiveMode());
        BaseMod.addCard(new PrepareCrush());
        BaseMod.addCard(new slimebound.cards.SlimeCrush());
        BaseMod.addCard(new SplitGhostflame());

        BaseMod.addCard(new slimebound.cards.Divider());
        BaseMod.addCard(new slimebound.cards.Sear());

        BaseMod.addCard(new HyperBeamSlimedbound());
        BaseMod.addCard(new slimebound.cards.Flail());
        BaseMod.addCard(new slimebound.cards.DefensiveStance());
        BaseMod.addCard(new slimebound.cards.FaceSlap());
        BaseMod.addCard(new slimebound.cards.LastStand());
        BaseMod.addCard(new slimebound.cards.Collect());
        BaseMod.addCard(new slimebound.cards.YouAreMine());
        BaseMod.addCard(new CaCaw());
        BaseMod.addCard(new slimebound.cards.DarkVoid());
        BaseMod.addCard(new slimebound.cards.Slimepotheosis());
        BaseMod.addCard(new slimebound.cards.FinishingTackle());
        BaseMod.addCard(new QuickStudy());
        BaseMod.addCard(new FirmFortitude());
        BaseMod.addCard(new Replication());
        BaseMod.addCard(new TimeRipple());
        BaseMod.addCard(new slimebound.cards.HeadSlam());
        BaseMod.addCard(new ManipulateTime());
        BaseMod.addCard(new DonusPower());
        BaseMod.addCard(new DecasProtection());
        BaseMod.addCard(new slimebound.cards.PolyBeam());
        BaseMod.addCard(new Repurpose());
        BaseMod.addCard(new GrowthPunch());
        BaseMod.addCard(new slimebound.cards.Recycling());
        BaseMod.addCard(new slimebound.cards.Recollect());
        UnlockTracker.unlockCard(Strike_Slimebound.ID);
        UnlockTracker.unlockCard(Defend_Slimebound.ID);
        UnlockTracker.unlockCard(SplitBronze.ID);
        UnlockTracker.unlockCard(LevelUp.ID);
        UnlockTracker.unlockCard(SplitTorchHead.ID);
        UnlockTracker.unlockCard(SplitAttack.ID);
        UnlockTracker.unlockCard(SplitCultist.ID);
        UnlockTracker.unlockCard(SplitShield.ID);
        UnlockTracker.unlockCard(SplitPoison.ID);
        UnlockTracker.unlockCard(SplitSliming.ID);
        UnlockTracker.unlockCard(SlimeSacrifice.ID);
        //UnlockTracker.unlockCard(zzzAbsorbAll.ID);
        UnlockTracker.unlockCard(Overexert.ID);
        UnlockTracker.unlockCard(Split.ID);
        UnlockTracker.unlockCard(SuperSplit.ID);
        UnlockTracker.unlockCard(DivideAndConquer.ID);
        UnlockTracker.unlockCard(LeadByExample.ID);
        UnlockTracker.unlockCard(SlimeTap.ID);
        UnlockTracker.unlockCard(RainOfGoop.ID);
        UnlockTracker.unlockCard(Teamwork.ID);
        UnlockTracker.unlockCard(SlimeBarrage.ID);
        UnlockTracker.unlockCard(SlimeBrawl.ID);
        //UnlockTracker.unlockCard(zzzMaxSlimes.ID);
        UnlockTracker.unlockCard(StudyTheSpire.ID);
        UnlockTracker.unlockCard(SolidOuterGoop.ID);
        UnlockTracker.unlockCard(SlimeSpikes.ID);
        UnlockTracker.unlockCard(SpikyOuterGoop.ID);
        UnlockTracker.unlockCard(MassRepurpose.ID);
        UnlockTracker.unlockCard(DouseInSlime.ID);
        UnlockTracker.unlockCard(Chomp.ID);
        UnlockTracker.unlockCard(StrayGoop.ID);
        UnlockTracker.unlockCard(OozeBath.ID);
        UnlockTracker.unlockCard(GangUp.ID);
      // UnlockTracker.unlockCard(zzzSoTasty.ID);
        UnlockTracker.unlockCard(LivingWall.ID);
        UnlockTracker.unlockCard(FormABlockade.ID);
        UnlockTracker.unlockCard(LeechingTouch.ID);
        UnlockTracker.unlockCard(DuplicatedForm.ID);
        UnlockTracker.unlockCard(FirmFortitude.ID);
        UnlockTracker.unlockCard(Dissolve.ID);
        UnlockTracker.unlockCard(RollThrough.ID);
        UnlockTracker.unlockCard(CorrosiveSpit.ID);
        UnlockTracker.unlockCard(Regenerate.ID);
        UnlockTracker.unlockCard(Recycling.ID);
        UnlockTracker.unlockCard(GrowthPunch.ID);
        UnlockTracker.unlockCard(Recollect.ID);
        UnlockTracker.unlockCard(FormOfPuddle.ID);
        UnlockTracker.unlockCard(Gluttony.ID);
        UnlockTracker.unlockCard(Lick.ID);
        UnlockTracker.unlockCard(MegaLick.ID);

        UnlockTracker.unlockCard(PressTheAttack.ID);
        UnlockTracker.unlockCard(SoulSicken.ID);
       // UnlockTracker.unlockCard(zzzFocusedLick.ID);
        UnlockTracker.unlockCard(HauntingLick.ID);
        UnlockTracker.unlockCard(AcidGelatin.ID);
        UnlockTracker.unlockCard(QuickLick.ID);
        UnlockTracker.unlockCard(Replication.ID);
        UnlockTracker.unlockCard(QuickStudy.ID);
        UnlockTracker.unlockCard(FinishingTackle.ID);
        UnlockTracker.unlockCard(Slimepotheosis.ID);
        UnlockTracker.unlockCard(TongueLash.ID);
        UnlockTracker.unlockCard(PoisonLick.ID);
        UnlockTracker.unlockCard(ItLooksTasty.ID);
        UnlockTracker.unlockCard(AcidTongue.ID);
        UnlockTracker.unlockCard(TendrilStrike.ID);
        UnlockTracker.unlockCard(WasteNot.ID);
        UnlockTracker.unlockCard(HungryTackle.ID);
        UnlockTracker.unlockCard(VenomTackle.ID);
        UnlockTracker.unlockCard(GoopTackle.ID);
        UnlockTracker.unlockCard(FlameTackle.ID);
        UnlockTracker.unlockCard(GoopSpray.ID);
        UnlockTracker.unlockCard(ComboTackle.ID);
        UnlockTracker.unlockCard(Grow.ID);
        UnlockTracker.unlockCard(Prepare.ID);
        UnlockTracker.unlockCard(MassFeed.ID);
        UnlockTracker.unlockCard(SlimeSmash.ID);
        UnlockTracker.unlockCard(LeechEnergy.ID);
        UnlockTracker.unlockCard(LeechLife.ID);
        UnlockTracker.unlockCard(Equalize.ID);

        UnlockTracker.unlockCard(DisruptingSlam.ID);
        UnlockTracker.unlockCard(PrepareCrush.ID);
        UnlockTracker.unlockCard(Repurpose.ID);


    }


    public void receiveEditKeywords() {

        BaseMod.addKeyword(new String[]{"absorb"}, "Recombine with a spawned Slime, healing 3 HP.");

        BaseMod.addKeyword(new String[]{"split"}, "Lose 3 HP and spawn a Slime minion, who attacks at the start of each turn.  Absorb your oldest one if you have no room, healing 3 HP.");

        BaseMod.addKeyword(new String[]{"slimed"}, "Increase the next attack's damage and cause it to restore HP, consuming the effect. Half is removed at end of turn.");

        BaseMod.addKeyword(new String[]{"potency"}, "Increases the damage of ALL of your Spawned Slimes.");
        BaseMod.addKeyword(new String[]{"torch-head"}, "Attacks for 9 each turn, and gains damage when you gain Strength.");
        BaseMod.addKeyword(new String[]{"bruiser"}, "Attacks for 5 each turn.");
        BaseMod.addKeyword(new String[]{"cultist"}, "Attacks for 6 each turn, then increases it's damage by 2.");
        BaseMod.addKeyword(new String[]{"leeching"}, "Attacks for 2 and grants you 2 Block each turn.");
        BaseMod.addKeyword(new String[]{"poisoning"}, "Attacks for 1 and applies 2 Poison each turn .");
        BaseMod.addKeyword(new String[]{"sliming"}, "Attacks for 1 and applies 2 Slimed each turn .");
        BaseMod.addKeyword(new String[]{"plated"}, "Increases Block each turn. Reduced when you take damage.");
        BaseMod.addKeyword(new String[]{"self-forming"}, "Taking damage from enemy attacks grants Block for next turn.");
        BaseMod.addKeyword(new String[]{"bronze"}, "Attacks for 6  and grants you 6 Block each turn.");
        BaseMod.addKeyword(new String[]{"tag-team"}, "Gain 1 Energy and draw 1 card per turn.");
        BaseMod.addKeyword(new String[]{"halved"}, "Your Max HP is cut in half this combat, losing HP if you are currently above half, and preventing healing beyond half.");
        BaseMod.addKeyword(new String[]{"lick"}, "0-cost cards that apply a variety of debuffs.");

        BaseMod.addKeyword(new String[]{"useful"}, "1-cost card that grants 2 energy.");
        BaseMod.addKeyword(new String[]{"ghostflame"}, "Does not attack and is unaffected by Potency. Provides 1 Strength, 1 Dexterity, and 3 Potency.");
        BaseMod.addKeyword(new String[]{"burn"}, "Deals damage each turn.  Does not decay.");
        BaseMod.addKeyword(new String[]{"morph"}, "Replace with a random new card of your class, regardless of type. It costs 1 less.");
        BaseMod.addKeyword(new String[]{"regen"}, "Heal HP equal to Regen amount at end of turn, then reduce Regen by 1.");
        BaseMod.addKeyword(new String[]{"purge"}, "Removed from the game entirely when played (not Exhausted).");
        BaseMod.addKeyword(new String[]{"slow"}, "Receives 10% more damage per card played in a turn.");
        BaseMod.addKeyword(new String[]{"tackle"}, "High-damage Attacks that also deal a small amount of damage to you.");


    }

    public void receiveEditStrings() {
        logger.info("begin editing strings");
        String relicStrings = Gdx.files.internal("localization/Slimebound-RelicStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal("localization/Slimebound-CardStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal("localization/Slimebound-PowerStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String monsterStrings = Gdx.files.internal("localization/Slimebound-MonsterStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);
        String potionStrings = Gdx.files.internal("localization/Slimebound-PotionStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
        String orbStrings = Gdx.files.internal("localization/Slimebound-OrbStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);
        logger.info("done editing strings");
    }

    public void receivePostBattle(AbstractRoom r) {

        AbstractPlayer p = AbstractDungeon.player;
        if (spritealtered){
            AbstractDungeon.effectsQueue.add(new SmokePuffEffect(p.hb.cX, p.hb.cY));
            // AbstractDungeon.actionManager.addToBottom(new VFXAction(new DoubleSlimeParticle(AbstractDungeon.player)));
            if (p instanceof SlimeboundCharacter) {
                SlimeboundCharacter hero = (SlimeboundCharacter) p;
                hero.setRenderscale(1F);
            }
                p.hb_x = p.hb_x - (100 * Settings.scale);
                p.drawX = p.drawX + (100 * Settings.scale);
                p.hb.cX = p.hb.cX - (100 * Settings.scale);


            spritealtered = false;
        }
    }

    public void receivePostDraw(AbstractCard c) {
        AbstractPlayer player = AbstractDungeon.player;

        if (c.cardID == "Slimed") {

            if (player.chosenClass.name() == "SLIMEBOUND") {


                AbstractCard slimeCard = CardLibrary.getCard(UsefulSlime.ID).makeCopy();
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, player.hand));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(slimeCard));
            }
        }
    }


    public void receivePostInitialize() {

        logger.info("Load Badge Image and mod options");
        // Load the Mod Badge
        Texture badgeTexture = new Texture(getResourcePath("badge.png"));

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("Slimebound Mod doesn't have any settings!", 400.0f, 700.0f,
                settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, "Slimebound", "Michael Mayhem", "Adds the Slimebound character to the game.", settingsPanel);

        logger.info("Done loading badge Image and mod options");

        BaseMod.addPotion(SlimedPotion.class, Color.GREEN, Color.GREEN, Color.GREEN, SlimedPotion.POTION_ID, SlimeboundEnum.SLIMEBOUND);
    }




    public void receiveCardUsed(AbstractCard c) {
        if (AbstractDungeon.player.hasRelic("CultistMask")) {
            CardCrawlGame.sound.playA("VO_CULTIST_1C", MathUtils.random(-0.8F, -0.6F));
            AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0F, "Caw...", true));
        }

        if (c.type == AbstractCard.CardType.POWER) {
            ++powersPlayedThisCombat;
        }


    }



    public boolean receivePreMonsterTurn(AbstractMonster abstractMonster) {
        slimeDelay = true;

        return true;
    }

    public void receiveOnBattleStart(AbstractRoom room) {
        powersPlayedThisCombat = 0;

    }

}



