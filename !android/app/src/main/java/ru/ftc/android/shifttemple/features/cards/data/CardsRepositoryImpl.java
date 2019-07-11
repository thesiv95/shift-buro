package ru.ftc.android.shifttemple.features.cards.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class CardsRepositoryImpl implements CardsRepository {

    private final CardsDataSource dataSource;

    public CardsRepositoryImpl(CardsDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadCards(Carry<List<Card>> carry) {
        dataSource.getCards(carry);
    }

    @Override
    public void loadInvitationCards(Carry<List<Card>> carry) {
        dataSource.getInvitationCards(carry);
    }

    @Override
    public void loadRequestCards(Carry<List<Card>> carry) {
        dataSource.getRequestCards(carry);
    }

    @Override
    public void loadCard(String id, Carry<Card> carry) {
        dataSource.getCard(id, carry);
    }

    @Override
    public void createCard(Card card, Carry<Card> carry) { dataSource.createCard(card, carry);
    }

    @Override
    public void deleteCard(String id, Carry<Success> carry) { dataSource.deleteCard(id, carry);
    }
}