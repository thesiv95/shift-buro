package ru.ftc.android.shifttemple.features.cards.presentation.CardList;

import android.util.Log;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.cards.domain.CardsInteractor;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.domain.model.Success;
import ru.ftc.android.shifttemple.features.login.domain.SessionInteractor;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:43
 */

final class CardListPresenter extends MvpPresenter<CardListView> {

    private final CardsInteractor interactor;
    private final SessionInteractor sessionInteractor;

    CardListPresenter(CardsInteractor interactor, SessionInteractor sessionInteractor) {
        this.interactor = interactor;
        this.sessionInteractor = sessionInteractor;
    }

    @Override
    protected void onViewReady() {
        loadCards();
    }

    protected String getUserInfo() {
        String userInfo = "";
        userInfo += "User ID: " + sessionInteractor.getUserId().toString() + "\n";
        userInfo += "User name: " + sessionInteractor.getUserName() + "\n";
        userInfo += "User phone: " + sessionInteractor.getUserPhone() + "\n";
        userInfo += "User City: " + sessionInteractor.getUserCity() + "\n";
        userInfo += "User Status: " + sessionInteractor.getUserStatus().toString() + "\n";
        userInfo += "User Description: " + sessionInteractor.getUserDescription() + "\n";
        userInfo += "User Age: " + sessionInteractor.getUserAge() + "\n";
        userInfo += "User Balance: " + sessionInteractor.getUserBalance().toString() + "\n";
        return userInfo;
    }

    protected String getUserImageUrl() {
        return sessionInteractor.getUserPicUrl();
    }

    protected  Integer getUserBalance() {
        return sessionInteractor.getUserBalance();
    }

    private void loadCards() {
        view.showProgress();
        int spinnerPosition = view.getSpinnerItemPosition();
        onSpinerItemSelected(spinnerPosition);
    }

    void onSpinerItemSelected(int position) {
        view.showProgress();
        switch (position) {
            case 0:
                interactor.loadCards(new Carry<List<Card>>() {
                    @Override
                    public void onSuccess(List<Card> result) {
                        view.showCardList(result);
                        view.hideProgress();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        view.hideProgress();
                        view.showError(throwable.getMessage());
                    }
                });
                break;
            case 1:
                interactor.loadRequestCards(new Carry<List<Card>>() {
                    @Override
                    public void onSuccess(List<Card> result) {
                        view.showCardList(result);
                        view.hideProgress();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        view.hideProgress();
                        view.showError(throwable.getMessage());
                    }
                });
                break;
            case 2:
                interactor.loadInvitationCards(new Carry<List<Card>>() {
                    @Override
                    public void onSuccess(List<Card> result) {
                        view.showCardList(result);
                        view.hideProgress();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        view.hideProgress();
                        view.showError(throwable.getMessage());
                    }
                });
                break;
            default:
                view.hideProgress();
                break;
        }
    }

    void onCardSelected(Card card) {
        view.showProgress();
        interactor.loadCard(String.valueOf(card.getId()), new Carry<Card>() {

            @Override
            public void onSuccess(Card result) {
                view.hideProgress();
                // do something
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });
    }

    void onCardLongClicked(Card card) {
        view.showProgress();
        interactor.deleteCard(String.valueOf(card.getId()), new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                loadCards();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    public void onAddTaskClicked() {
        view.openCardCreateScreen();
    }

}