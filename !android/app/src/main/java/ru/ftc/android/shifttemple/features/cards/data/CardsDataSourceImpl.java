package ru.ftc.android.shifttemple.features.cards.data;

import java.util.List;


import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;
import ru.ftc.android.shifttemple.network.DefaultCallback;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:57
 */

public final class CardsDataSourceImpl implements CardsDataSource {

    private final CardsApi cardsApi;

    public CardsDataSourceImpl(CardsApi cardsApi) {
        this.cardsApi = cardsApi;
    }

    @Override
    public void getCards(final Carry<List<Card>> carry) {
        cardsApi.getCardList().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getInvitationCards(Carry<List<Card>> carry) {
        cardsApi.getTypedCardList("Предложение").enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getRequestCards(Carry<List<Card>> carry) {
        cardsApi.getTypedCardList("Просьба").enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getCard(String id, Carry<Card> carry) {
        cardsApi.getCard(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void createCard(Card card, Carry<Void> carry) {
        cardsApi.addCard(card).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteCard(String id, Carry<Success> carry) {
        cardsApi.deleteCard(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void updateCardStatus(Integer cardId, Integer userId, Carry<Void> carry) {
        cardsApi.updateCardStatus(cardId, userId).enqueue(new DefaultCallback(carry));
    }
}