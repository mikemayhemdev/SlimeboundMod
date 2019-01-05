package slimebound;

import basemod.BaseMod;
import basemod.ModButton;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.abstracts.CustomUnlockBundle;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.custom.CustomMod;
import com.megacrit.cardcrawl.unlock.AbstractUnlock;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.SmokePuffEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.cards.*;
import slimebound.characters.SlimeboundCharacter;
import slimebound.dailymods.AllSplit;
import slimebound.events.Hunted;
import slimebound.events.WorldOfGoopSlimebound;
import slimebound.helpers.PoisonVariable;
import slimebound.helpers.SelfDamageVariable;
import slimebound.helpers.SlimedVariable;
import slimebound.orbs.SpawnedSlime;
import slimebound.orbs.TorchHeadSlime;
import slimebound.patches.AbstractCardEnum;
import slimebound.patches.SlimeboundEnum;
import slimebound.potions.SlimedPotion;
import slimebound.potions.SlimyTonguePotion;
import slimebound.potions.SpawnSlimePotion;
import slimebound.potions.ThreeZeroPotion;
import slimebound.powers.AcidTonguePowerUpgraded;
import slimebound.relics.*;

import java.nio.charset.StandardCharsets;
import java.util.List;


@com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
public class SlimeboundMod implements  SetUnlocksSubscriber, AddCustomModeModsSubscriber, PostDungeonInitializeSubscriber, PostBattleSubscriber, PostInitializeSubscriber, PreMonsterTurnSubscriber, OnCardUseSubscriber, basemod.interfaces.EditCharactersSubscriber, basemod.interfaces.EditRelicsSubscriber, basemod.interfaces.EditCardsSubscriber, basemod.interfaces.EditKeywordsSubscriber, EditStringsSubscriber, basemod.interfaces.PostDrawSubscriber,  basemod.interfaces.OnStartBattleSubscriber {
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
    public static boolean scrapping;
    public static SlimeboundCharacter slimeboundCharacter;

    private ModLabel modOptionsLabel;
    private ModButton modOptionsButton;
    private ModPanel settingsPanel;

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
    @SpireEnum
    public static AbstractCard.CardTags LICK;
    @SpireEnum
    public static AbstractCard.CardTags TACKLE;
    @SpireEnum
    public static AbstractCard.CardTags STUDY_HEXAGHOST;
    @SpireEnum
    public static AbstractCard.CardTags STUDY_AWAKENEDONE;
    @SpireEnum
    public static AbstractCard.CardTags STUDY_TIMEEATER;
    @SpireEnum
    public static AbstractCard.CardTags STUDY_CHAMP;
    @SpireEnum
    public static AbstractCard.CardTags STUDY_COLLECTOR;
    @SpireEnum
    public static AbstractCard.CardTags STUDY_SHAPES;
    @SpireEnum
    public static AbstractCard.CardTags STUDY_GUARDIAN;
    @SpireEnum
    public static AbstractCard.CardTags STUDY_AUTOMATON;
    @SpireEnum
    public static AbstractCard.CardTags STUDY;


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


    public void receivePostDungeonInitialize() {

        slimeTalked = false;
        slimeTalkedAcidL = false;
        slimeTalkedAcidM = false;
        slimeTalkedAcidS = false;
        slimeTalkedSpikeL = false;
        slimeTalkedSpikeM = false;
        slimeTalkedSpikeS = false;
        slimeTalkedDark = 0;
        slimeTalkedCollector = false;
        if (AbstractDungeon.player != null) {
            if (AbstractDungeon.player instanceof SlimeboundCharacter) {
                ((SlimeboundCharacter) AbstractDungeon.player).foughtSlimeBoss = false;
                SlimeboundMod.logger.info("Reset Hunted event bool.");
            }
            if (CardCrawlGame.trial != null && CardCrawlGame.trial.dailyModIDs().contains(AllSplit.ID)) {
                //logger.info("Daily Mod detecthed");
                RelicLibrary.getRelic(DailySplitModRelic.ID).makeCopy().instantObtain();


            }
        }

    }

    @Override
    public void receiveSetUnlocks() {
        BaseMod.addUnlockBundle(new CustomUnlockBundle(
                RollThrough.ID, Chomp.ID, CheckThePlaybook.ID
        ), SlimeboundEnum.SLIMEBOUND, 1);

        BaseMod.addUnlockBundle(new CustomUnlockBundle(
                Dissolve.ID, Repurpose.ID, MassRepurpose.ID
        ), SlimeboundEnum.SLIMEBOUND, 2);

        BaseMod.addUnlockBundle(new CustomUnlockBundle(AbstractUnlock.UnlockType.RELIC,
                AggressiveSlimeRelic.ID, PotencyRelic.ID, MaxSlimesRelic.ID
        ), SlimeboundEnum.SLIMEBOUND, 3);

        BaseMod.addUnlockBundle(new CustomUnlockBundle(
                HungryTackle.ID, Recollect.ID, Recycling.ID
        ), SlimeboundEnum.SLIMEBOUND, 4);

        BaseMod.addUnlockBundle(new CustomUnlockBundle(AbstractUnlock.UnlockType.RELIC,
                PreparedRelic.ID, SlimedTailRelic.ID, SlimedSkullRelic.ID
        ), SlimeboundEnum.SLIMEBOUND, 5);
    }


    public static int getAcidTongueBonus(AbstractCreature source) {
        int bonus = 0;
        if (source != null) {
            if (source.hasPower(AcidTonguePowerUpgraded.POWER_ID)) {
                bonus = source.getPower(AcidTonguePowerUpgraded.POWER_ID).amount;
            }
        }
        return bonus;
    }

    public void printEnemies(){
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            logger.info(monster.name + " HP " + monster.currentHealth);
        }
    }


    public static String printString(String s) {
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
        BaseMod.addRelicToCustomPool(new SlimedSkullRelic(), AbstractCardEnum.SLIMEBOUND);
        BaseMod.addRelicToCustomPool(new ScrapOozeRelic(), AbstractCardEnum.SLIMEBOUND);
        BaseMod.addRelicToCustomPool(new GreedOozeRelic(), AbstractCardEnum.SLIMEBOUND);
        BaseMod.addRelicToCustomPool(new DailySplitModRelic(), AbstractCardEnum.SLIMEBOUND);

    }


    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SelfDamageVariable());
        BaseMod.addDynamicVariable(new PoisonVariable());
        BaseMod.addDynamicVariable(new SlimedVariable());

        BaseMod.addCard(new slimebound.cards.Defend_Slimebound());
        BaseMod.addCard(new slimebound.cards.Strike_Slimebound());
        BaseMod.addCard(new SplitBronze());
        BaseMod.addCard(new LevelUp());
        BaseMod.addCard(new SplitBruiser());
        BaseMod.addCard(new SplitTorchHead());
        BaseMod.addCard(new StudyTheSpire());
        BaseMod.addCard(new SplitCultist());
        BaseMod.addCard(new SplitAcid());
        BaseMod.addCard(new SplitLeeching());
        BaseMod.addCard(new SplitLicking());
        BaseMod.addCard(new ProtectTheBoss());
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
        BaseMod.addCard(new SelfFormingGoo());
        BaseMod.addCard(new slimebound.cards.Dissolve());
        BaseMod.addCard(new slimebound.cards.DuplicatedForm());
        BaseMod.addCard(new slimebound.cards.LeechingTouch());
        BaseMod.addCard(new SamplingLick());
        BaseMod.addCard(new FormOfPuddle());
        BaseMod.addCard(new slimebound.cards.Lick());
        BaseMod.addCard(new slimebound.cards.MegaLick());

        BaseMod.addCard(new PressTheAttack());
        BaseMod.addCard(new SoulSicken());
        // BaseMod.addCard(new slimebound.cards.zzzFocusedLick());
        BaseMod.addCard(new HauntingLick());
        BaseMod.addCard(new AcidGelatin());
        BaseMod.addCard(new RejuvenatingLick());
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
        BaseMod.addCard(new ViciousTackle());
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

        BaseMod.addCard(new Hexaburn());
        BaseMod.addCard(new slimebound.cards.Sear());

        BaseMod.addCard(new SlimeBeam());
        BaseMod.addCard(new slimebound.cards.Flail());
        BaseMod.addCard(new slimebound.cards.DefensiveStance());
        BaseMod.addCard(new slimebound.cards.FaceSlap());
        BaseMod.addCard(new slimebound.cards.LastStand());
        BaseMod.addCard(new slimebound.cards.Collect());
        BaseMod.addCard(new slimebound.cards.YouAreMine());
        BaseMod.addCard(new CaCaw());
        BaseMod.addCard(new slimebound.cards.DarkVoid());
        //BaseMod.addCard(new zzzSlimepotheosis());
        BaseMod.addCard(new slimebound.cards.FinishingTackle());
        BaseMod.addCard(new QuickStudy());
        BaseMod.addCard(new FirmFortitude());
        BaseMod.addCard(new Replication());
        BaseMod.addCard(new CheckThePlaybook());
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






    }

    public void unlockEverything(){
        UnlockTracker.unlockCard(Strike_Slimebound.ID);
        UnlockTracker.unlockCard(Defend_Slimebound.ID);
        UnlockTracker.unlockCard(SplitBronze.ID);
        UnlockTracker.unlockCard(LevelUp.ID);
        UnlockTracker.unlockCard(SplitTorchHead.ID);
        UnlockTracker.unlockCard(SplitBruiser.ID);
        UnlockTracker.unlockCard(SplitCultist.ID);
        UnlockTracker.unlockCard(SplitLeeching.ID);
        UnlockTracker.unlockCard(SplitAcid.ID);
        UnlockTracker.unlockCard(SplitLicking.ID);
        UnlockTracker.unlockCard(ProtectTheBoss.ID);
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
        UnlockTracker.unlockCard(SelfFormingGoo.ID);
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
        UnlockTracker.unlockCard(SamplingLick.ID);
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
        UnlockTracker.unlockCard(RejuvenatingLick.ID);
        UnlockTracker.unlockCard(Replication.ID);
        UnlockTracker.unlockCard(QuickStudy.ID);

        UnlockTracker.unlockCard(CheckThePlaybook.ID);
        UnlockTracker.unlockCard(FinishingTackle.ID);
        //UnlockTracker.unlockCard(zzzSlimepotheosis.ID);
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
        UnlockTracker.unlockCard(ViciousTackle.ID);
        UnlockTracker.unlockCard(LeechEnergy.ID);
        UnlockTracker.unlockCard(LeechLife.ID);
        UnlockTracker.unlockCard(Equalize.ID);

        UnlockTracker.unlockCard(DisruptingSlam.ID);
        UnlockTracker.unlockCard(PrepareCrush.ID);
        UnlockTracker.unlockCard(Repurpose.ID);

        UnlockTracker.lockedRelics.remove(SlimedTailRelic.ID);
        UnlockTracker.lockedRelics.remove(SlimedSkullRelic.ID);
        UnlockTracker.lockedRelics.remove(AggressiveSlimeRelic.ID);
        UnlockTracker.lockedRelics.remove(PotencyRelic.ID);
        UnlockTracker.lockedRelics.remove(MaxSlimesRelic.ID);
        UnlockTracker.lockedRelics.remove(PreparedRelic.ID);

        //UnlockTracker.addScore(SlimeboundEnum.SLIMEBOUND, 1000000);

        modOptionsLabel.text = "Now restart your client and all will be unlocked.";




    }


    public void receiveEditKeywords() {

        BaseMod.addKeyword(new String[]{"absorb"}, "Recombine with a spawned Slime, healing 3 HP.");

        BaseMod.addKeyword(new String[]{"split"}, "Lose 3 HP and spawn a Slime minion, who attacks at the start of each turn.  Absorb your oldest one if you have no room, healing 3 HP.");

        BaseMod.addKeyword(new String[]{"slimed"}, "The next attack deals increased damage, consuming the Slimed effect, and healing you for half the amount consumed. Half of Slimed is removed at end of turn.");

        BaseMod.addKeyword(new String[]{"potency"}, "Increases the damage of ALL of your Spawned Slimes.");
        BaseMod.addKeyword("Torch Head Slime",new String[]{"torch head slime","torch_head_slime"}, "Attacks for 9 each turn, and gains 1 damage when you play a Power.");
        BaseMod.addKeyword("Bruiser Slime",new String[]{"bruiser slime","bruiser_slime","bruiser slimes","bruiser_slimes"}, "Attacks for 4 each turn.");
        BaseMod.addKeyword("Cultist Slime",new String[]{"cultist slime","cultist_slime"}, "Attacks for 6 each turn, then increases it's damage by 2.");
        BaseMod.addKeyword("Leeching Slime",new String[]{"leeching slime","leeching_slime","leeching slimes","leeching_slimes"}, "Attacks for 2 and grants you 2 Block each turn.");
        BaseMod.addKeyword("Acid Slime",new String[]{"acid slime","acid_slime","acid slimes","acid_slimes"}, "Attacks for 1 and applies 2 Poison each turn.");
        BaseMod.addKeyword("Mire Slime",new String[]{"mire slime","mire_slime","mire slimes","mire_slimes"}, "Attacks for 1 and applies 2 Slimed each turn.");
        BaseMod.addKeyword("Plated Armor", new String[]{"plated armor","plated_armor"}, "Increases Block each turn. Reduced when you take damage.");
        BaseMod.addKeyword(new String[]{"self-forming"}, "Taking damage from enemy attacks grants Block for next turn.");
        BaseMod.addKeyword("Bronze Slime",new String[]{"bronze slime","bronze_slime"}, "Attacks for 6  and grants you 6 Block each turn.");
        //BaseMod.addKeyword(new String[]{"tag-team"}, "Gain 1 Energy and draw 1 card per turn.");
        BaseMod.addKeyword(new String[]{"halved"}, "Your Max HP is cut in half this combat, losing HP if you are currently above half, and preventing healing beyond half.");
        BaseMod.addKeyword(new String[]{"lick"}, "0-cost cards that apply a variety of debuffs.");

        //BaseMod.addKeyword(new String[]{"useful"}, "1-cost card that grants 2 energy.");
        BaseMod.addKeyword("Ghostflame Slime",new String[]{"ghostflame slime","ghostflame_slime"}, "Does not attack and is unaffected by Potency. Provides 1 Strength, 1 Dexterity, and 2 Potency.");
        //BaseMod.addKeyword(new String[]{"burn"}, "Deals damage each turn.  Does not decay.");
        BaseMod.addKeyword(new String[]{"morph"}, "Replace with a random new card of your class, regardless of type. It costs 1 less.");
        //BaseMod.addKeyword(new String[]{"regen"}, "Heal HP equal to Regen amount at end of turn, then reduce Regen by 1.");
        //BaseMod.addKeyword(new String[]{"purge"}, "Removed from the game entirely when played (not Exhausted).");
        BaseMod.addKeyword(new String[]{"slow"}, "Receives 10% more damage per card played in a turn.");
        BaseMod.addKeyword(new String[]{"tackle"}, "High-damage Attacks that also deal a small amount of damage to you.");
        BaseMod.addKeyword("Spire Boss", new String[]{"spire boss","spire_boss"}, "Powerful 0-cost cards, inspired by the bosses of the Spire.");


    }

    public void receiveEditStrings() {

        String language = "eng";

        if (Settings.language == Settings.GameLanguage.ZHS) language = "zhs";

        logger.info("begin editing strings");
        String relicStrings = Gdx.files.internal("localization/" + language + "/Slimebound-RelicStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal("localization/" + language + "/Slimebound-CardStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal("localization/" + language + "/Slimebound-PowerStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String monsterStrings = Gdx.files.internal("localization/" + language + "/Slimebound-MonsterStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);
        String potionStrings = Gdx.files.internal("localization/" + language + "/Slimebound-PotionStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
        String orbStrings = Gdx.files.internal("localization/" + language + "/Slimebound-OrbStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);
        String eventStrings = Gdx.files.internal("localization/" + language + "/Slimebound-EventStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
        String modStrings = Gdx.files.internal("localization/" + language + "/Slimebound-DailyModStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RunModStrings.class, modStrings);
        String charStrings = Gdx.files.internal("localization/" + language + "/Slimebound-CharacterStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CharacterStrings.class, charStrings);
        logger.info("done editing strings");
    }

    public void receivePostBattle(AbstractRoom r) {

        AbstractPlayer p = AbstractDungeon.player;
        if (spritealtered) {
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
        if (p instanceof SlimeboundCharacter) {
            SlimeboundCharacter hero = (SlimeboundCharacter) p;
            hero.leftScale = 0.15F;

            ((SlimeboundCharacter) AbstractDungeon.player).xStartOffset = (float)Settings.WIDTH * 0.23F;
            ((SlimeboundCharacter) AbstractDungeon.player).initializeSlotPositions();

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

        settingsPanel = new ModPanel();

        modOptionsLabel = new ModLabel("Press this button to skip progress (unlock all cards/relics)", 400.0f, 700.0f,
                settingsPanel, (me) -> {
        });
                settingsPanel.addUIElement(modOptionsLabel);

            modOptionsButton = new ModButton( 400.0f, 400.0f,
                    settingsPanel, (me) -> {unlockEverything();
            });
        settingsPanel.addUIElement(modOptionsButton);

        BaseMod.registerModBadge(badgeTexture, "Slimebound", "Michael Mayhem", "Adds the Slimebound character to the game.", settingsPanel);

        logger.info("Done loading badge Image and mod options");

        BaseMod.addPotion(SlimedPotion.class, Color.PURPLE, Color.PURPLE, Color.MAROON, SlimedPotion.POTION_ID);
        BaseMod.addPotion(SlimyTonguePotion.class, Color.PURPLE, Color.PURPLE, Color.MAROON, SlimyTonguePotion.POTION_ID, SlimeboundEnum.SLIMEBOUND);
        BaseMod.addPotion(SpawnSlimePotion.class, Color.GREEN, Color.FOREST, Color.BLACK, SpawnSlimePotion.POTION_ID, SlimeboundEnum.SLIMEBOUND);
        BaseMod.addPotion(ThreeZeroPotion.class, Color.FOREST, Color.BLACK, Color.BLACK, ThreeZeroPotion.POTION_ID);

        BaseMod.addEvent(Hunted.ID, Hunted.class, TheCity.ID);
        BaseMod.addEvent(Hunted.ID, Hunted.class, TheBeyond.ID);

        BaseMod.addEvent(WorldOfGoopSlimebound.ID, WorldOfGoopSlimebound.class, Exordium.ID);

    }


    public void receiveCardUsed(AbstractCard c) {

        if (c.type == AbstractCard.CardType.POWER) {
            ++powersPlayedThisCombat;
            for (AbstractOrb o : AbstractDungeon.player.orbs) {

                if (o.ID == TorchHeadSlime.ID) {
                    logger.info("Sending power amount" + 1);
                    ((TorchHeadSlime) o).applyUniqueFocus(1);
                }
            }
        }


           // this.printEnemies();



    }


    public boolean receivePreMonsterTurn(AbstractMonster abstractMonster) {
        slimeDelay = true;
         //   this.printEnemies();

        return true;
    }

    public void receiveCustomModeMods(List<CustomMod> l) {

        l.add(new CustomMod(AllSplit.ID, "r", true));
    }

    public void receiveOnBattleStart(AbstractRoom room) {
        powersPlayedThisCombat = 0;




    }

}



