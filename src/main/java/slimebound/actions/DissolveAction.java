//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package slimebound.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import slimebound.powers.PotencyPower;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class DissolveAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean anyNumber;
    private boolean canPickZero;
    private int block;
    private int extraCards;
    public static int numExhausted;

    public DissolveAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, int block, int blockUnc) {
        this(target, source, amount, isRandom, false, false, block, blockUnc);
    }

    public DissolveAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, int block, int extraCards) {
        this.canPickZero = false;
        this.anyNumber = anyNumber;
        this.canPickZero = canPickZero;
        this.p = (AbstractPlayer)target;
        this.isRandom = isRandom;
        this.setValues(target, source, amount);
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
        this.block=block;
        this.extraCards=extraCards;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }

            int i;
            if (!this.anyNumber && this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                numExhausted = this.amount;
               int i2 = this.p.hand.size();

                for(i = 0; i < i2; ++i) {
                    AbstractCard c = this.p.hand.getTopCard();
                    this.p.hand.moveToExhaustPile(c);
                    dissolveEffect(c);

                }

                CardCrawlGame.dungeon.checkForPactAchievement();
                return;
            }

            if (!this.isRandom) {
                numExhausted = this.amount;
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
                this.tickDuration();
                return;
            }

            for(i = 0; i < this.amount; ++i) {
                AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                this.p.hand.moveToExhaustPile(c);
                dissolveEffect(c);

            }

            CardCrawlGame.dungeon.checkForPactAchievement();
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            Iterator var4 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while(var4.hasNext()) {
                AbstractCard c = (AbstractCard)var4.next();
                this.p.hand.moveToExhaustPile(c);
               dissolveEffect(c);
            }

            CardCrawlGame.dungeon.checkForPactAchievement();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }

    public void dissolveEffect(AbstractCard c2) {
        ArrayList<AbstractCard> list = new ArrayList();
        Iterator var1 = srcCommonCardPool.group.iterator();

        AbstractCard c3;


        for (int i = 0; i < (c2.cost + this.extraCards); i++) {
            while(var1.hasNext()) {
                c3 = (AbstractCard)var1.next();
                if (c3.cost==0) {
                    list.add(c3);
                }
            }

            var1 = srcUncommonCardPool.group.iterator();

            while(var1.hasNext()) {
                c3 = (AbstractCard)var1.next();
                if (c3.cost==0) {
                    list.add(c3);
                }
            }

            var1 = srcRareCardPool.group.iterator();

            while(var1.hasNext()) {
                c3 = (AbstractCard)var1.next();
                if (c3.cost==0) {
                    list.add(c3);
                }
            }

            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(list.get(cardRandomRng.random(list.size() - 1))));

        }
        /*
        if (c.rarity == AbstractCard.CardRarity.UNCOMMON) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,this.blockUnc));
        }
        if (c.rarity == AbstractCard.CardRarity.RARE) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower( p, 1), 1));

        }
        */
    }
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
        TEXT = uiStrings.TEXT;
    }
}
