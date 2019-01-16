/*     */ package slimebound.actions;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
/*     */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class OverexertionAction extends AbstractGameAction
/*     */ {
    /*     */   private AbstractPlayer p;
    /*     */   private final boolean upgrade;

    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

    /*     */
    /*     */
    public OverexertionAction(boolean upgrade) {
        /*  26 */
        this.upgrade = upgrade;
        /*  27 */
        this.p = AbstractDungeon.player;
        /*  28 */
        setValues(this.p, AbstractDungeon.player, this.amount);
        /*  29 */
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        /*  30 */
        this.duration = Settings.ACTION_DUR_FAST;
        /*     */
    }

    /*     */
    /*     */
    public void update() {
        /*     */
        /*  35 */
        if (this.duration == Settings.ACTION_DUR_FAST)
            /*     */ {
            /*  37 */
            if (this.p.exhaustPile.isEmpty()) {
                /*  45 */
                logger.info("Exhaust is empty");
                this.isDone = true;
                return;
                /*     */
            }

            int exhaustSize = p.exhaustPile.size();
            CardGroup cardsToReturn = AbstractDungeon.player.exhaustPile;
            logger.info("Exhaust size:" + exhaustSize);
            /*     */
            for (AbstractCard c : cardsToReturn.group) {
                /*  44 */

                /*  57 */

                /*  58 */
                logger.info("Add to discard");
                this.p.drawPile.addToRandomSpot(c);

                /*  61 */

                logger.info("Modify Cost");
                c.modifyCostForCombat(-1);
                /*     */
                /*  63 */


                /*  64 */
                /*  67 */


                /*  69 */
                /*     */
            }
            for (int x = 0; x <= exhaustSize - 1; x++){
                logger.info("Exhausting card");
                AbstractCard cardReturning = p.exhaustPile.getBottomCard();
                this.p.exhaustPile.removeCard(cardReturning);

                logger.info("Unfadeout");
                cardReturning.unfadeOut();

                logger.info("Unhover");
                cardReturning.unhover();
                /*  68 */

                logger.info("Fading out false");
                cardReturning.fadingOut = false;



        }


            logger.info("Losing HP");
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player,  AbstractDungeon.player, exhaustSize));
        /* 54 */
        this.isDone = true;
            /*  93 */
            return;
            /*     */
        }

        tickDuration();
    }
}


        /*     */
