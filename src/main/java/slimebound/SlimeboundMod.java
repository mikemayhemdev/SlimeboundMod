/*     */ package slimebound;
/*     */ 
/*     */ import basemod.BaseMod;
/*     */ import basemod.interfaces.EditStringsSubscriber;
/*     */ import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PreMonsterTurnSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import basemod.interfaces.OnCardUseSubscriber;
import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import slimebound.cards.SlimeRitual;
import slimebound.characters.SlimeboundCharacter;
import slimebound.orbs.TorchHeadSlime;
import slimebound.patches.SlimeboundEnum;
import slimebound.potions.SlimedPotion;
import slimebound.relics.*;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;

import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
/*     */ import slimebound.patches.AbstractCardEnum;

/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

/*     */
/*     */ @com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
/*     */ public class SlimeboundMod implements PostInitializeSubscriber, PreMonsterTurnSubscriber, OnCardUseSubscriber, basemod.interfaces.EditCharactersSubscriber, basemod.interfaces.EditRelicsSubscriber, basemod.interfaces.EditCardsSubscriber, basemod.interfaces.EditKeywordsSubscriber, EditStringsSubscriber, basemod.interfaces.PostDrawSubscriber, basemod.interfaces.PostPowerApplySubscriber, basemod.interfaces.OnStartBattleSubscriber
/*     */ {
/*  37 */   private static final com.badlogic.gdx.graphics.Color SLIME_COLOR = com.megacrit.cardcrawl.helpers.CardHelper.getColor(25.0F, 95.0F, 25.0F);
/*     */   
/*     */   private static final String SLIMEBOUNDMOD_ASSETS_FOLDER = "SlimeboundImages";
/*     */   
/*     */   private static final String ATTACK_CARD = "512/bg_attack_slimebound.png";
/*     */   private static final String SKILL_CARD = "512/bg_skill_slimebound.png";
/*     */   private static final String POWER_CARD = "512/bg_power_slimebound.png";
/*     */   private static final String ENERGY_ORB = "512/card_slimebound_orb.png";
            private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";

/*     */   private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_slimebound.png";
/*     */   private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_slimebound.png";
/*     */   private static final String POWER_CARD_PORTRAIT = "1024/bg_power_slimebound.png";
/*     */   private static final String ENERGY_ORB_PORTRAIT = "1024/card_slimebound_orb.png";

/*     */   private static final String CHAR_BUTTON = "charSelect/button.png";
/*     */   private static final String CHAR_PORTRAIT = "charSelect/portrait.png";
    public static int powersPlayedThisCombat;
    public static boolean slimeDelay;
    public static SlimeboundCharacter slimeboundCharacter;
/*     */   
/*     */   public static final String getResourcePath(String resource)
/*     */   {
/*  54 */     return "SlimeboundImages/" + resource;
/*     */   }
/*     */   
/*  57 */   public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());
/*     */   
/*     */   public SlimeboundMod() {
    /*  60 */
    BaseMod.subscribe(this);

    /*  62 */
    BaseMod.addColor(AbstractCardEnum.SLIMEBOUND,
            SLIME_COLOR, SLIME_COLOR, SLIME_COLOR, SLIME_COLOR, SLIME_COLOR, SLIME_COLOR, SLIME_COLOR,
            getResourcePath(ATTACK_CARD), getResourcePath(SKILL_CARD),
            getResourcePath(POWER_CARD), getResourcePath(ENERGY_ORB),
            getResourcePath(ATTACK_CARD_PORTRAIT), getResourcePath(SKILL_CARD_PORTRAIT),
            getResourcePath(POWER_CARD_PORTRAIT), getResourcePath(ENERGY_ORB_PORTRAIT), getResourcePath(CARD_ENERGY_ORB));
}
/*     */   public static void initialize() {
/*  71 */     new SlimeboundMod();
/*     */   }
/*     */   
/*     */   public void receiveEditCharacters()
/*     */   {
/*  76 */
    slimeboundCharacter = new SlimeboundCharacter("TheSlimebound", SlimeboundEnum.SLIMEBOUND);
    BaseMod.addCharacter(slimeboundCharacter, getResourcePath("charSelect/button.png"), getResourcePath("charSelect/portrait.png"), SlimeboundEnum.SLIMEBOUND);

    /*     */   }
/*     */   
/*     */ public void receivePostPowerApplySubscriber(AbstractPower power,AbstractCreature target, AbstractCreature source){

    if (power.ID == "Strength"){

        if (target == AbstractDungeon.player) {

            for (AbstractOrb o : AbstractDungeon.player.orbs) {

                if (o.ID == "TorchHeadSlime") { // when equipped (picked up) this relic counts how many ethereal cards are in the player's deck
                    logger.info("Sending power amount" + power.amount);
                    ((TorchHeadSlime) o).applyUniqueFocus(power.amount);
                }
            }


        };
    }
}


/*     */ 
/*     */   public void receiveEditRelics()
/*     */   {
/*  85 *
/*     */
/*     */ 
/*  94 */     BaseMod.addRelicToCustomPool(new AbsorbEndCombat(), AbstractCardEnum.SLIMEBOUND);
    /*  94 */     BaseMod.addRelicToCustomPool(new AbsorbEndCombatUpgraded(), AbstractCardEnum.SLIMEBOUND);
    /*  94 */     BaseMod.addRelicToCustomPool(new AggressiveSlimeRelic(), AbstractCardEnum.SLIMEBOUND);
    /*  94 */     BaseMod.addRelicToCustomPool(new MaxSlimesRelic(), AbstractCardEnum.SLIMEBOUND);
    /*  94 */     BaseMod.addRelicToCustomPool(new PotencyRelic(), AbstractCardEnum.SLIMEBOUND);
    /*  94 */     BaseMod.addRelicToCustomPool(new PreparedRelic(), AbstractCardEnum.SLIMEBOUND);
    /*  94 */     BaseMod.addRelicToCustomPool(new SlimedTailRelic(), AbstractCardEnum.SLIMEBOUND);
    /*  94 */     BaseMod.addRelicToCustomPool(new StudyCardRelic(), AbstractCardEnum.SLIMEBOUND);
/*     */   }
/*     */   
/*     */ 
/*     */   public void receiveEditCards()
/*     */   {
/* 108 */
/*     */ 
/* 111 */     BaseMod.addCard(new slimebound.cards.Defend_Slimebound());
/* 112 */     BaseMod.addCard(new slimebound.cards.Strike_Slimebound());
    /* 112 */ BaseMod.addCard(new slimebound.cards.BronzeSlime());
    /* 112 */ BaseMod.addCard(new slimebound.cards.PotencyGainCard());
    /* 112 */ BaseMod.addCard(new slimebound.cards.AttackSlime());
    /* 112 */ BaseMod.addCard(new slimebound.cards.TorchHeadSlime());
    /* 112 */ BaseMod.addCard(new slimebound.cards.CultistSlime());
    /* 112 */ BaseMod.addCard(new slimebound.cards.PoisonSlime());
    /* 112 */ BaseMod.addCard(new slimebound.cards.DebuffSlime());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SlimingSlime());
    /* 112 */ BaseMod.addCard(new slimebound.cards.DuplicateSlimes());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SlimeSacrifice());
    /* 112 */ BaseMod.addCard(new slimebound.cards.AbsorbAll());
    /* 112 */ BaseMod.addCard(new slimebound.cards.AbsorbAllPotency());
    /* 112 */ BaseMod.addCard(new slimebound.cards.RandomSlimeCard());
    /* 112 */ BaseMod.addCard(new slimebound.cards.RandomSlimeCard4());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SplittingStrike());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SplitForLess());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SlimeTap());
    /* 112 */ BaseMod.addCard(new slimebound.cards.CoordinatedStrike());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SlimeBarrage());
    /* 112 */ BaseMod.addCard(new slimebound.cards.AllTogetherNow());
    /* 112 */ BaseMod.addCard(new slimebound.cards.MaxSlimes());
    /* 112 */ BaseMod.addCard(new slimebound.cards.QuickSpikes());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SlimeBlockade());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Spiked());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Hardened());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Dissolve());
    /* 112 */ BaseMod.addCard(new slimebound.cards.DuplicatedForm());
    /* 112 */ BaseMod.addCard(new slimebound.cards.LeechingTouch());
    /* 112 */ BaseMod.addCard(new slimebound.cards.RapidHeal());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Puddle());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Lick());
    /* 112 */ BaseMod.addCard(new slimebound.cards.MegaLick());
    /* 112 */ //BaseMod.addCard(new slimebound.cards.StrikeFrailPoint());
    /* 112 */ BaseMod.addCard(new slimebound.cards.StrikeWeakPoint());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SickeningTendril());
    /* 112 */ BaseMod.addCard(new slimebound.cards.FocusedLick());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SlimedLick());
    /* 112 */ BaseMod.addCard(new slimebound.cards.PoisonThorns());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SamplingLick());
    /* 112 */ BaseMod.addCard(new slimebound.cards.TongueLash());
    /* 112 */ BaseMod.addCard(new slimebound.cards.LooksTasty());
    /* 112 */ BaseMod.addCard(new slimebound.cards.AcidTongue());
    /* 112 */ BaseMod.addCard(new slimebound.cards.TendrilStrike());
    /* 112 */ BaseMod.addCard(new slimebound.cards.PoisonLick());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Overexertion());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Tackle());
    /* 112 */ BaseMod.addCard(new slimebound.cards.FlameTackle());
    /* 112 */ BaseMod.addCard(new slimebound.cards.BodyBlow());
    /* 112 */ BaseMod.addCard(new slimebound.cards.QuickTackle());
    /* 112 */ BaseMod.addCard(new slimebound.cards.CorrosiveTackle());
    /* 112 */ BaseMod.addCard(new slimebound.cards.PoisonTackle());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Grow());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Prepare());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Gluttony());
    /* 112 */ BaseMod.addCard(new slimebound.cards.UsefulSlime());
             BaseMod.addCard(new slimebound.cards.TendrilFlail());
    /* 112 */ BaseMod.addCard(new slimebound.cards.GoopSpray());
    /* 112 */ BaseMod.addCard(new slimebound.cards.MassFeed());
    /* 112 */ BaseMod.addCard(new slimebound.cards.TendrilSlash());
    /* 112 */ BaseMod.addCard(new slimebound.cards.LeechEnergy());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Leech());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Retaliate());
    /* 112 */ //BaseMod.addCard(new slimebound.cards.RollThrough());
    /* 112 */ BaseMod.addCard(new slimebound.cards.ViolentStop());
    /* 112 */ BaseMod.addCard(new slimebound.cards.ChargeUp());
    /* 112 */ BaseMod.addCard(new slimebound.cards.GuardianWhirl());
    /* 112 */ BaseMod.addCard(new slimebound.cards.CorrosiveSpit());
    /* 112 */ BaseMod.addCard(new slimebound.cards.DefensiveMode());
    /* 112 */ BaseMod.addCard(new slimebound.cards.PrepareSlimeCrush());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SlimeCrush());
    /* 112 */ BaseMod.addCard(new slimebound.cards.HexSlime());
    /* 112 */ BaseMod.addCard(new slimebound.cards.PrepareDivider());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Divider());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Sear());
    /* 112 */ BaseMod.addCard(new slimebound.cards.PrepareBeam());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SlimeHyperBeam());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Flail());
    /* 112 */ BaseMod.addCard(new slimebound.cards.DefensiveStance());
    /* 112 */ BaseMod.addCard(new slimebound.cards.FaceSlap());
    /* 112 */ BaseMod.addCard(new slimebound.cards.LastStand());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Collect());
    /* 112 */ BaseMod.addCard(new slimebound.cards.YouAreMine());
    /* 112 */ BaseMod.addCard(new SlimeRitual());
    /* 112 */ BaseMod.addCard(new slimebound.cards.DarkVoid());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Ripple());
    /* 112 */ BaseMod.addCard(new slimebound.cards.HeadSlam());
    /* 112 */ BaseMod.addCard(new slimebound.cards.StopTime());
    /* 112 */ BaseMod.addCard(new slimebound.cards.CircleOfPower());
    /* 112 */ BaseMod.addCard(new slimebound.cards.SquareOfProtection());
    /* 112 */ BaseMod.addCard(new slimebound.cards.PolyBeam());
    /* 112 */ BaseMod.addCard(new slimebound.cards.StudyAutomaton());
    /* 112 */ BaseMod.addCard(new slimebound.cards.StudyAwakened());
    /* 112 */ BaseMod.addCard(new slimebound.cards.StudyChamp());
    /* 112 */ BaseMod.addCard(new slimebound.cards.StudyCollector());
    /* 112 */ BaseMod.addCard(new slimebound.cards.StudyGuardian());
    /* 112 */ BaseMod.addCard(new slimebound.cards.StudyHexaghost());
    /* 112 */ BaseMod.addCard(new slimebound.cards.StudyShapes());
    /* 112 */ BaseMod.addCard(new slimebound.cards.StudyTimeEater());
    /* 112 */ BaseMod.addCard(new slimebound.cards.MorphCard());
    /* 112 */ BaseMod.addCard(new slimebound.cards.GrowingTendril());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Recycling());
    /* 112 */ BaseMod.addCard(new slimebound.cards.Recollect());
                  UnlockTracker.unlockCard("Strike_Slimebound");
               UnlockTracker.unlockCard("Defend_Slimebound");
    UnlockTracker.unlockCard("BronzeSlime");
    UnlockTracker.unlockCard("PotencyGainCard");
    UnlockTracker.unlockCard("TorchHeadSlime");
    UnlockTracker.unlockCard("AttackSlime");
    UnlockTracker.unlockCard("CultistSlime");
    UnlockTracker.unlockCard("DebuffSlime");
    UnlockTracker.unlockCard("PoisonSlime");
    UnlockTracker.unlockCard("SlimingSlime");
    UnlockTracker.unlockCard("SlimeSacrifice");
    UnlockTracker.unlockCard("DuplicateSlimes");
    UnlockTracker.unlockCard("AbsorbAll");
    UnlockTracker.unlockCard("AbsorbAllPotency");
    UnlockTracker.unlockCard("RandomSlimeCard");
    UnlockTracker.unlockCard("RandomSlimeCard4");
    UnlockTracker.unlockCard("SplittingStrike");
    UnlockTracker.unlockCard("SplitForLess");
    UnlockTracker.unlockCard("SlimeTap");
    UnlockTracker.unlockCard("TendrilFlail");
    UnlockTracker.unlockCard("CoordinatedStrike");
    UnlockTracker.unlockCard("SlimeBarrage");
    UnlockTracker.unlockCard("AllTogetherNow");
    UnlockTracker.unlockCard("MaxSlimes");
    UnlockTracker.unlockCard("Hardened");
    UnlockTracker.unlockCard("QuickSpikes");
    UnlockTracker.unlockCard("Spiked");
    UnlockTracker.unlockCard("SlimeBlockade");
    UnlockTracker.unlockCard("LeechingTouch");
    UnlockTracker.unlockCard("DuplicatedForm");
    UnlockTracker.unlockCard("Dissolve");
    UnlockTracker.unlockCard("BodyBlow");
    UnlockTracker.unlockCard("CorrosiveSpit");
    UnlockTracker.unlockCard("RapidHeal");
    UnlockTracker.unlockCard("Recycling");
    UnlockTracker.unlockCard("GrowingTendril");
    UnlockTracker.unlockCard("Recollect");
    UnlockTracker.unlockCard("Puddle");
    UnlockTracker.unlockCard("Gluttony");
    /* 112 */ UnlockTracker.unlockCard("Lick");
    /* 112 */ UnlockTracker.unlockCard("MegaLick");
    /* 112 */ //UnlockTracker.unlockCard("StrikeFrailPoint");
    /* 112 */ UnlockTracker.unlockCard("StrikeWeakPoint");
    /* 112 */ UnlockTracker.unlockCard("SickeningTendril");
    /* 112 */ UnlockTracker.unlockCard("FocusedLick");
    /* 112 */ UnlockTracker.unlockCard("SlimedLick");
    /* 112 */ UnlockTracker.unlockCard("PoisonThorns");
    /* 112 */ UnlockTracker.unlockCard("SamplingLick");
    /* 112 */ UnlockTracker.unlockCard("TongueLash");
    /* 112 */ UnlockTracker.unlockCard("PoisonLick");
    /* 112 */ UnlockTracker.unlockCard("LooksTasty");
    /* 112 */ UnlockTracker.unlockCard("AcidTongue");
    /* 112 */ UnlockTracker.unlockCard("TendrilStrike");
    /* 112 */ UnlockTracker.unlockCard("Overexertion");
    /* 112 */ UnlockTracker.unlockCard("Tackle");
    /* 112 */ UnlockTracker.unlockCard("PoisonTackle");
    /* 112 */ UnlockTracker.unlockCard("CorrosiveTackle");
    /* 112 */ UnlockTracker.unlockCard("FlameTackle");
    /* 112 */ UnlockTracker.unlockCard("GoopSpray");
    /* 112 */ UnlockTracker.unlockCard("QuickTackle");
    /* 112 */ UnlockTracker.unlockCard("Grow");
    /* 112 */ UnlockTracker.unlockCard("Prepare");
    /* 112 */ UnlockTracker.unlockCard("MassFeed");
    /* 112 */ UnlockTracker.unlockCard("TendrilSlash");
    /* 112 */ UnlockTracker.unlockCard("LeechEnergy");
    /* 112 */ UnlockTracker.unlockCard("Leech");
    /* 112 */ UnlockTracker.unlockCard("Retaliate");
    /* 112 */ //UnlockTracker.unlockCard("RollThrough");
    /* 112 */ UnlockTracker.unlockCard("ViolentStop");
    /* 112 */ UnlockTracker.unlockCard("ChargeUp");
    /* 112 */ UnlockTracker.unlockCard("GuardianWhirl");
    /* 112 */ UnlockTracker.unlockCard("DefensiveMode");
    /* 112 */ UnlockTracker.unlockCard("PrepareSlimeCrush");
    /* 112 */ UnlockTracker.unlockCard("SlimeCrush");
    /* 112 */ UnlockTracker.unlockCard("HexSlime");
    /* 112 */ UnlockTracker.unlockCard("PrepareDivider");
    /* 112 */ UnlockTracker.unlockCard("Divider");
    /* 112 */ UnlockTracker.unlockCard("Sear");
    /* 112 */ UnlockTracker.unlockCard("PrepareBeam");
    /* 112 */ UnlockTracker.unlockCard("SlimeHyperBeam");
    /* 112 */ UnlockTracker.unlockCard("Flail");
    /* 112 */ UnlockTracker.unlockCard("DefensiveStance");
    /* 112 */ UnlockTracker.unlockCard("FaceSlap");
    /* 112 */ UnlockTracker.unlockCard("LastStand");
    /* 112 */ UnlockTracker.unlockCard("Collect");
    /* 112 */ UnlockTracker.unlockCard("YouAreMine");
    /* 112 */ UnlockTracker.unlockCard("SlimeRitual");
    /* 112 */ UnlockTracker.unlockCard("DarkVoid");
    /* 112 */ UnlockTracker.unlockCard("Ripple");
    /* 112 */ UnlockTracker.unlockCard("HeadSlam");
    /* 112 */ UnlockTracker.unlockCard("StopTime");
    /* 112 */ UnlockTracker.unlockCard("CircleOfPower");
    /* 112 */ UnlockTracker.unlockCard("SquareOfProtection");
    /* 112 */ UnlockTracker.unlockCard("PolyBeam");
    /* 112 */ UnlockTracker.unlockCard("StudyAutomaton");
    /* 112 */ UnlockTracker.unlockCard("StudyAwakened");
    /* 112 */ UnlockTracker.unlockCard("StudyChamp");
    /* 112 */ UnlockTracker.unlockCard("StudyCollector");
    /* 112 */ UnlockTracker.unlockCard("StudyGuardian");
    /* 112 */ UnlockTracker.unlockCard("StudyHexaghost");
    /* 112 */ UnlockTracker.unlockCard("StudyShapes");
    /* 112 */ UnlockTracker.unlockCard("StudyTimeEater");
    /* 112 */ UnlockTracker.unlockCard("MorphCard");
/* 113 */
/*     */     
/*     */
/*     */     
/*     */
/*     */   }
/*     */

/*     */   public void receiveEditKeywords()
/*     */   {
/* 198 */
/* 200 */     BaseMod.addKeyword(new String[] { "absorb" }, "Recombine with a spawned Slime, gaining 3 Block and 3 HP.");
/*     */     
/* 202 */     BaseMod.addKeyword(new String[] { "split" }, "Lose 3 Health and spawn a Slime minion, who attacks at the start of each turn.  Absorb your oldest one if you have no room, gaining 3 Block and 3 HP.");
/*     */     
/* 204 */     BaseMod.addKeyword(new String[] { "slimed" }, "Increase the next attack's damage and cause it to restore health, consuming the effect.");

    /* 204 */ BaseMod.addKeyword(new String[] { "potency" }, "Increases the effectiveness of your Spawned Slimes.");
    BaseMod.addKeyword(new String[] { "torch-head" }, "Attacks for 9 each turn, and gains Strength when you do.");
    BaseMod.addKeyword(new String[] { "aggressive" }, "Attacks for 5 each turn.");
    BaseMod.addKeyword(new String[] { "cultist" }, "Attacks for 6 each turn, then increases its Strength by 2.");
    BaseMod.addKeyword(new String[] { "licking" }, "Attacks for 2 each turn and applies 1 Weaken.");
    BaseMod.addKeyword(new String[] { "toxic" }, "Applies 3 Poison each turn.");
    BaseMod.addKeyword(new String[] { "sludging" }, "Applies 3 Slimed each turn.");
    BaseMod.addKeyword(new String[] { "plated" }, "Increases Block each turn.  Reduced when you take damage.");
    BaseMod.addKeyword(new String[] { "self-forming" }, "Taking damage from enemy attacks grant Block for next turn.");
    BaseMod.addKeyword(new String[] { "bronze" }, "Attacks for 5 each turn and grants you an equal amount of Block.");
    BaseMod.addKeyword(new String[] { "tag-team" }, "Gain 1 Energy and draw 1 card per turn.");
    BaseMod.addKeyword(new String[] { "halved" }, "You cannot heal beyond half of your maximum Health.");
    BaseMod.addKeyword(new String[] { "lick" }, "0-cost cards that apply a variety of debuffs.");
   // BaseMod.addKeyword(new String[] { "momentum" }, "Increases the damage and self-damage of Tackle attacks.");
    BaseMod.addKeyword(new String[] { "useful" }, "1-cost card that grants 2 energy.");
    BaseMod.addKeyword(new String[] { "ghostflame" }, "Does not attack. Gain 1 Strength and Potency and 3 extra Block and HP when Absorbed. Absorb all slimes if you have 6 Ghostflames.");
    BaseMod.addKeyword(new String[] { "burn" }, "Deals damage each turn.  Does not decay.");
    BaseMod.addKeyword(new String[] { "transform" }, "Replace with a random new card.");

/*     */   }
/*     */   
/*     */    public void receiveEditStrings() {
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

    /*     */   public void receivePostDraw(AbstractCard c)
/*     */   {
/* 210 */     AbstractPlayer player = AbstractDungeon.player;

        if (c.cardID == "Slimed"){

/*     */     if (player.chosenClass.name() == "SLIMEBOUND"){


            AbstractCard slimeCard = CardLibrary.getCard("UsefulSlime").makeCopy();
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c,player.hand));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(slimeCard));
        }
        }
    }


    public void receivePostInitialize() {


        BaseMod.addPotion(SlimedPotion.class, Color.GREEN, Color.GREEN, Color.GREEN, "SlimedPotion", SlimeboundEnum.SLIMEBOUND);
    }

/* 212 */
/*     */
/*     */   
/*     */
/*     */   
/*     */   public static boolean hasDebuff(AbstractCreature c) {
/* 226 */     for (AbstractPower power : c.powers) {
/* 227 */       if (power.type == AbstractPower.PowerType.DEBUFF) {
/* 228 */         return true;
/*     */       }
/*     */     }
/* 231 */     return false;
/*     */   }


    public void receiveCardUsed(AbstractCard c) {
        if (AbstractDungeon.player.hasRelic("CultistMask")) {
            CardCrawlGame.sound.playA("VO_CULTIST_1C", MathUtils.random(-0.8F, -0.6F));
            AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0F, "Caw...", true));
        }

        if(c.type == AbstractCard.CardType.POWER) {
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



/*     */ }



/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\SlimeboundMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */