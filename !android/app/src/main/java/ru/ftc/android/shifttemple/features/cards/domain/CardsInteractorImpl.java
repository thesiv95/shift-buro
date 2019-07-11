package ru.ftc.android.shifttemple.features.cards.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.cards.data.CardsRepository;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class CardsInteractorImpl implements CardsRepository, CardsInteractor {

    private final CardsRepository repository;

    public CardsInteractorImpl(CardsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadCards(Carry<List<Card>> carry) {
        repository.loadCards(carry);
    }

    @Override
    public void loadInvitationCards(Carry<List<Card>> carry) {
        repository.loadInvitationCards(carry);
    }

    @Override
    public void loadRequestCards(Carry<List<Card>> carry) {
        repository.loadRequestCards(carry);
    }

    @Override
    public void loadCard(String id, Carry<Card> carry) {
        repository.loadCard(id, carry);
    }

    @Override
    public void createCard(Card card, Carry<Card> carry) {
        repository.createCard(card, carry);
    }

    @Override
    public void deleteCard(String id, Carry<Success> carry) {
        repository.deleteCard(id, carry);
    }
}