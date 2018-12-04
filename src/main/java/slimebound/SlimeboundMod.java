package slimebound;

import basemod.BaseMod;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnCardUseSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PreMonsterTurnSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.cards.SlimeRitual;
import slimebound.characters.SlimeboundCharacter;
import slimebound.orbs.TorchHeadSlime;
import slimebound.patches.AbstractCardEnum;
import slimebound.patches.SlimeboundEnum;
import slimebound.potions.SlimedPotion;
import slimebound.relics.*;

import java.nio.charset.StandardCharsets;


@com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
public class SlimeboundMod implements PostInitializeSubscriber, PreMonsterTurnSubscriber, OnCardUseSubscriber, basemod.interfaces.EditCharactersSubscriber, basemod.interfaces.EditRelicsSubscriber, basemod.interfaces.EditCardsSubscriber, basemod.interfaces.EditKeywordsSubscriber, EditStringsSubscriber, basemod.interfaces.PostDrawSubscriber, basemod.interfaces.PostPowerApplySubscriber, basemod.interfaces.OnStartBattleSubscriber {
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

                    if (o.ID == "TorchHeadSlime") {
                        logger.info("Sending power amount" + power.amount);
                        ((TorchHeadSlime) o).applyUniqueFocus(power.amount);
                    }
                }


            }
            ;
        }
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


        BaseMod.addCard(new slimebound.cards.Defend_Slimebound());
        BaseMod.addCard(new slimebound.cards.Strike_Slimebound());
        BaseMod.addCard(new slimebound.cards.BronzeSlime());
        BaseMod.addCard(new slimebound.cards.PotencyGainCard());
        BaseMod.addCard(new slimebound.cards.AttackSlime());
        BaseMod.addCard(new slimebound.cards.TorchHeadSlime());
        BaseMod.addCard(new slimebound.cards.StudyRandomBoss());
        BaseMod.addCard(new slimebound.cards.CultistSlime());
        BaseMod.addCard(new slimebound.cards.PoisonSlime());
        BaseMod.addCard(new slimebound.cards.DebuffSlime());
        BaseMod.addCard(new slimebound.cards.SlimingSlime());
        BaseMod.addCard(new slimebound.cards.DuplicateSlimes());
        BaseMod.addCard(new slimebound.cards.SlimeSacrifice());
        BaseMod.addCard(new slimebound.cards.AbsorbAll());
        BaseMod.addCard(new slimebound.cards.AbsorbAllPotency());
        BaseMod.addCard(new slimebound.cards.RandomSlimeCard());
        BaseMod.addCard(new slimebound.cards.RandomSlimeCard4());
        BaseMod.addCard(new slimebound.cards.SplittingStrike());
        BaseMod.addCard(new slimebound.cards.SplitForLess());
        BaseMod.addCard(new slimebound.cards.SlimeTap());
        BaseMod.addCard(new slimebound.cards.CoordinatedStrike());
        BaseMod.addCard(new slimebound.cards.SlimeBarrage());
        BaseMod.addCard(new slimebound.cards.AllTogetherNow());
        BaseMod.addCard(new slimebound.cards.MaxSlimes());
        BaseMod.addCard(new slimebound.cards.QuickSpikes());
        BaseMod.addCard(new slimebound.cards.SlimeBlockade());
        BaseMod.addCard(new slimebound.cards.Spiked());
        BaseMod.addCard(new slimebound.cards.MorphEverything());
        BaseMod.addCard(new slimebound.cards.SuperCorrosiveSpit());
        BaseMod.addCard(new slimebound.cards.SuperTackle());
        BaseMod.addCard(new slimebound.cards.SelfDamageSlimed());
        BaseMod.addCard(new slimebound.cards.DoubleEverything());
        BaseMod.addCard(new slimebound.cards.MultiLick());
        BaseMod.addCard(new slimebound.cards.Slimesplosion());
        BaseMod.addCard(new slimebound.cards.Hardened());
        BaseMod.addCard(new slimebound.cards.Dissolve());
        BaseMod.addCard(new slimebound.cards.DuplicatedForm());
        BaseMod.addCard(new slimebound.cards.LeechingTouch());
        BaseMod.addCard(new slimebound.cards.RapidHeal());
        BaseMod.addCard(new slimebound.cards.Puddle());
        BaseMod.addCard(new slimebound.cards.Lick());
        BaseMod.addCard(new slimebound.cards.MegaLick());

        BaseMod.addCard(new slimebound.cards.StrikeWeakPoint());
        BaseMod.addCard(new slimebound.cards.SickeningTendril());
        BaseMod.addCard(new slimebound.cards.FocusedLick());
        BaseMod.addCard(new slimebound.cards.SlimedLick());
        BaseMod.addCard(new slimebound.cards.PoisonThorns());
        BaseMod.addCard(new slimebound.cards.SamplingLick());
        BaseMod.addCard(new slimebound.cards.TongueLash());
        BaseMod.addCard(new slimebound.cards.LooksTasty());
        BaseMod.addCard(new slimebound.cards.AcidTongue());
        BaseMod.addCard(new slimebound.cards.TendrilStrike());
        BaseMod.addCard(new slimebound.cards.PoisonLick());
        BaseMod.addCard(new slimebound.cards.Overexertion());
        BaseMod.addCard(new slimebound.cards.Tackle());
        BaseMod.addCard(new slimebound.cards.FlameTackle());
        BaseMod.addCard(new slimebound.cards.BodyBlow());
        BaseMod.addCard(new slimebound.cards.QuickTackle());
        BaseMod.addCard(new slimebound.cards.CorrosiveTackle());
        BaseMod.addCard(new slimebound.cards.PoisonTackle());
        BaseMod.addCard(new slimebound.cards.Grow());
        BaseMod.addCard(new slimebound.cards.Prepare());
        BaseMod.addCard(new slimebound.cards.Gluttony());
        BaseMod.addCard(new slimebound.cards.UsefulSlime());
        BaseMod.addCard(new slimebound.cards.TendrilFlail());
        BaseMod.addCard(new slimebound.cards.GoopSpray());
        BaseMod.addCard(new slimebound.cards.MassFeed());
        BaseMod.addCard(new slimebound.cards.TendrilSlash());
        BaseMod.addCard(new slimebound.cards.LeechEnergy());
        BaseMod.addCard(new slimebound.cards.Leech());
        BaseMod.addCard(new slimebound.cards.Retaliate());

        BaseMod.addCard(new slimebound.cards.ViolentStop());
        BaseMod.addCard(new slimebound.cards.ChargeUp());
        BaseMod.addCard(new slimebound.cards.GuardianWhirl());
        BaseMod.addCard(new slimebound.cards.CorrosiveSpit());
        BaseMod.addCard(new slimebound.cards.DefensiveMode());
        BaseMod.addCard(new slimebound.cards.PrepareSlimeCrush());
        BaseMod.addCard(new slimebound.cards.SlimeCrush());
        BaseMod.addCard(new slimebound.cards.HexSlime());

        BaseMod.addCard(new slimebound.cards.Divider());
        BaseMod.addCard(new slimebound.cards.Sear());

        BaseMod.addCard(new slimebound.cards.SlimeHyperBeam());
        BaseMod.addCard(new slimebound.cards.Flail());
        BaseMod.addCard(new slimebound.cards.DefensiveStance());
        BaseMod.addCard(new slimebound.cards.FaceSlap());
        BaseMod.addCard(new slimebound.cards.LastStand());
        BaseMod.addCard(new slimebound.cards.Collect());
        BaseMod.addCard(new slimebound.cards.YouAreMine());
        BaseMod.addCard(new SlimeRitual());
        BaseMod.addCard(new slimebound.cards.DarkVoid());
        BaseMod.addCard(new slimebound.cards.Ripple());
        BaseMod.addCard(new slimebound.cards.HeadSlam());
        BaseMod.addCard(new slimebound.cards.StopTime());
        BaseMod.addCard(new slimebound.cards.CircleOfPower());
        BaseMod.addCard(new slimebound.cards.SquareOfProtection());
        BaseMod.addCard(new slimebound.cards.PolyBeam());
        BaseMod.addCard(new slimebound.cards.StudyAutomaton());
        BaseMod.addCard(new slimebound.cards.StudyAwakened());
        BaseMod.addCard(new slimebound.cards.StudyChamp());
        BaseMod.addCard(new slimebound.cards.StudyCollector());
        BaseMod.addCard(new slimebound.cards.StudyGuardian());
        BaseMod.addCard(new slimebound.cards.StudyHexaghost());
        BaseMod.addCard(new slimebound.cards.StudyShapes());
        BaseMod.addCard(new slimebound.cards.StudyTimeEater());
        BaseMod.addCard(new slimebound.cards.MorphCard());
        BaseMod.addCard(new slimebound.cards.GrowingTendril());
        BaseMod.addCard(new slimebound.cards.Recycling());
        BaseMod.addCard(new slimebound.cards.Recollect());
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
        UnlockTracker.unlockCard("StudyRandomBoss");
        UnlockTracker.unlockCard("Hardened");
        UnlockTracker.unlockCard("QuickSpikes");
        UnlockTracker.unlockCard("Spiked");
        UnlockTracker.unlockCard("MorphEverything");
        UnlockTracker.unlockCard("SuperCorrosiveSpit");
        UnlockTracker.unlockCard("SuperTackle");
        UnlockTracker.unlockCard("SelfDamageSlimed");
        UnlockTracker.unlockCard("DoubleEverything");
        UnlockTracker.unlockCard("MultiLick");
        UnlockTracker.unlockCard("Slimesplosion");
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
        UnlockTracker.unlockCard("Lick");
        UnlockTracker.unlockCard("MegaLick");

        UnlockTracker.unlockCard("StrikeWeakPoint");
        UnlockTracker.unlockCard("SickeningTendril");
        UnlockTracker.unlockCard("FocusedLick");
        UnlockTracker.unlockCard("SlimedLick");
        UnlockTracker.unlockCard("PoisonThorns");
        UnlockTracker.unlockCard("SamplingLick");
        UnlockTracker.unlockCard("TongueLash");
        UnlockTracker.unlockCard("PoisonLick");
        UnlockTracker.unlockCard("LooksTasty");
        UnlockTracker.unlockCard("AcidTongue");
        UnlockTracker.unlockCard("TendrilStrike");
        UnlockTracker.unlockCard("Overexertion");
        UnlockTracker.unlockCard("Tackle");
        UnlockTracker.unlockCard("PoisonTackle");
        UnlockTracker.unlockCard("CorrosiveTackle");
        UnlockTracker.unlockCard("FlameTackle");
        UnlockTracker.unlockCard("GoopSpray");
        UnlockTracker.unlockCard("QuickTackle");
        UnlockTracker.unlockCard("Grow");
        UnlockTracker.unlockCard("Prepare");
        UnlockTracker.unlockCard("MassFeed");
        UnlockTracker.unlockCard("TendrilSlash");
        UnlockTracker.unlockCard("LeechEnergy");
        UnlockTracker.unlockCard("Leech");
        UnlockTracker.unlockCard("Retaliate");

        UnlockTracker.unlockCard("ViolentStop");
        UnlockTracker.unlockCard("PrepareSlimeCrush");
        UnlockTracker.unlockCard("MorphCard");


    }


    public void receiveEditKeywords() {

        BaseMod.addKeyword(new String[]{"absorb"}, "Recombine with a spawned Slime, healing 3 HP.");

        BaseMod.addKeyword(new String[]{"split"}, "Lose 3 Health and spawn a Slime minion, who attacks at the start of each turn.  Absorb your oldest one if you have no room, healing 3 HP.");

        BaseMod.addKeyword(new String[]{"slimed"}, "Increase the next attack's damage and cause it to restore health, consuming the effect.");

        BaseMod.addKeyword(new String[]{"potency"}, "Increases the effectiveness of your Spawned Slimes.");
        BaseMod.addKeyword(new String[]{"torch-head"}, "Attacks for 9 each turn, and gains Strength when you do.");
        BaseMod.addKeyword(new String[]{"attacking"}, "Attacks for 5 each turn.");
        BaseMod.addKeyword(new String[]{"cultist"}, "Attacks for 6 each turn, then increases its Strength by 2.");
        BaseMod.addKeyword(new String[]{"weakening"}, "Attacks for 2 each turn and applies 1 Weaken.");
        BaseMod.addKeyword(new String[]{"poisoning"}, "Applies 3 Poison each turn.");
        BaseMod.addKeyword(new String[]{"sliming"}, "Applies 3 Slimed each turn.");
        BaseMod.addKeyword(new String[]{"plated"}, "Increases Block each turn.  Reduced when you take damage.");
        BaseMod.addKeyword(new String[]{"self-forming"}, "Taking damage from enemy attacks grant Block for next turn.");
        BaseMod.addKeyword(new String[]{"bronze"}, "Attacks for 5 each turn and grants you an equal amount of Block.");
        BaseMod.addKeyword(new String[]{"tag-team"}, "Gain 1 Energy and draw 1 card per turn.");
        BaseMod.addKeyword(new String[]{"halved"}, "You cannot heal beyond half of your maximum Health.");
        BaseMod.addKeyword(new String[]{"lick"}, "0-cost cards that apply a variety of debuffs.");

        BaseMod.addKeyword(new String[]{"useful"}, "1-cost card that grants 2 energy.");
        BaseMod.addKeyword(new String[]{"ghostflame"}, "Does not attack. Gain 1 Strength and Potency, 6 Block and heal 6 HP when Absorbed. Absorb all slimes if you have 6 Ghostflames.");
        BaseMod.addKeyword(new String[]{"burn"}, "Deals damage each turn.  Does not decay.");
        BaseMod.addKeyword(new String[]{"transform"}, "Replace with a random new card.");

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

    public void receivePostDraw(AbstractCard c) {
        AbstractPlayer player = AbstractDungeon.player;

        if (c.cardID == "Slimed") {

            if (player.chosenClass.name() == "SLIMEBOUND") {


                AbstractCard slimeCard = CardLibrary.getCard("UsefulSlime").makeCopy();
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, player.hand));
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(slimeCard));
            }
        }
    }


    public void receivePostInitialize() {


        BaseMod.addPotion(SlimedPotion.class, Color.GREEN, Color.GREEN, Color.GREEN, "SlimedPotion", SlimeboundEnum.SLIMEBOUND);
    }


    public static boolean hasDebuff(AbstractCreature c) {
        for (AbstractPower power : c.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                return true;
            }
        }
        return false;
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



