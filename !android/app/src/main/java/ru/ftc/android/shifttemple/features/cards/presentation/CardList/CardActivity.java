package ru.ftc.android.shifttemple.features.cards.presentation.CardList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.Image.CircleImageTransform;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.presentation.CardCreate.CardCreateActivity;

public class CardActivity extends BaseActivity implements CardListView {

    public static void start(final Context context) {
        Intent intent = new Intent(context, CardActivity.class);
        context.startActivity(intent);
    }

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ImageView createBookButton;
    private ImageView imageView;
    private CardAdapter adapter;
    private TextView textView;
    private Spinner spinner;

    protected CardListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        //Hide title bar.
        getSupportActionBar().hide();

        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.books_progress);
        recyclerView = findViewById(R.id.books_recycle_view);
        createBookButton = findViewById(R.id.addTask);
        imageView = findViewById(R.id.user_image_icon);
        textView = findViewById(R.id.userBalance);
        spinner = findViewById(R.id.spinnerCategory);

        //Load user image into ImageView
        Picasso.get().load(presenter.getUserImageUrl()).transform(new CircleImageTransform()).into(imageView);

        //Load user balance into TextView
        textView.setText(String.valueOf(presenter.getUserBalance()));

        createBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddTaskClicked();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                presenter.onSpinnerItemSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //Your code here
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
        }, this);

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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public int getSpinnerItemPosition() {
        return spinner.getSelectedItemPosition();
    }

    public void onAcceptButtonClick(Card card) {
        presenter.onAcceptButtonClicked(card);
    }

    @Override
    public void openCardCreateScreen() {
        CardCreateActivity.start(this);
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
