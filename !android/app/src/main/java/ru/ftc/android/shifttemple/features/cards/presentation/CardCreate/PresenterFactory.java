package ru.ftc.android.shifttemple.features.cards.presentation.CardCreate;

import android.content.Context;

import ru.ftc.android.shifttemple.App;
import ru.ftc.android.shifttemple.features.cards.data.CardsApi;
import ru.ftc.android.shifttemple.features.cards.data.CardsDataSource;
import ru.ftc.android.shifttemple.features.cards.data.CardsDataSourceImpl;
import ru.ftc.android.shifttemple.features.cards.data.CardsRepository;
import ru.ftc.android.shifttemple.features.cards.data.CardsRepositoryImpl;
import ru.ftc.android.shifttemple.features.cards.domain.CardsInteractorImpl;
import ru.ftc.android.shifttemple.features.login.data.SessionDataSource;
import ru.ftc.android.shifttemple.features.login.data.SessionDataSourceImpl;
import ru.ftc.android.shifttemple.features.login.data.SessionRepository;
import ru.ftc.android.shifttemple.features.login.data.SessionRepositoryImpl;
import ru.ftc.android.shifttemple.features.login.domain.SessionInteractor;
import ru.ftc.android.shifttemple.features.login.domain.SessionInteractorImpl;

final class PresenterFactory {

    static CardCreatePresenter createPresenter(Context context) {
        final CardsApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(CardsApi.class);

        final CardsDataSource dataSource = new CardsDataSourceImpl(api);
        final CardsRepository repository = new CardsRepositoryImpl(dataSource);
        final CardsInteractorImpl interactor = new CardsInteractorImpl(repository);

        final SessionDataSource sessionDataSource = new SessionDataSourceImpl(context);
        final SessionRepository sessionRepository = new SessionRepositoryImpl(sessionDataSource);
        final SessionInteractor sessionInteractor = new SessionInteractorImpl(sessionRepository);
        return new CardCreatePresenter(interactor, sessionInteractor);
    }

}