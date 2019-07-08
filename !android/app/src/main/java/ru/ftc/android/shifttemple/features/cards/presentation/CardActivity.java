package ru.ftc.android.shifttemple.features.cards.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.service.quicksettings.Tile;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.Image.CircleImageTransform;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.login.domain.SessionInteractor;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public final class CardActivity extends BaseActivity implements CardListView {

    public static void start(final Context context) {
        Intent intent = new Intent(context, CardActivity.class);
        context.startActivity(intent);
    }

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button createBookButton;
    private ImageView imageView;
    private CardAdapter adapter;

    private CardListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        //hide the title bar.
        getSupportActionBar().hide();

        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.books_progress);
        recyclerView = findViewById(R.id.books_recycle_view);
        createBookButton = findViewById(R.id.create_button);
        imageView = findViewById(R.id.user_image_icon);

        //Load user image into ImageView
        Picasso.get().load(presenter.getUserImageUrl()).transform(new CircleImageTransform(Color.red(2))).into(imageView);

        createBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateCardClicked();
            }
        });

        adapter = new CardAdapter(this, new CardAdapter.SelectCardListener() {
            @Override
            public void onCardSelect(Card card) {
                presenter.onCardSelected(card);
            }

            @Override
            public void onCardLongClick(Card card) {
                presenter.onCardLongClicked(card);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCardList(List<Card> list) {
        adapter.setCards(list);
    }

    @Override
    public void showError(String message) {
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Toast.makeText(this, presenter.getUserInfo(), Toast.LENGTH_LONG).show();
        List<Card> list = new ArrayList<>();
        list.add(new Card("name", "89122344", "Description"));
        list.add(new Card("name", "89122344", "Description"));
        list.add(new Card("name", "89122344", "Description"));
        adapter.setCards(list);
    }

    @Override
    protected MvpPresenter<CardListView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}
