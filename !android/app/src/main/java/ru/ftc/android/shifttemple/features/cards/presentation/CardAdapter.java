package ru.ftc.android.shifttemple.features.cards.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:24
 */

final class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private final List<Card> cards = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectCardListener selectCardListener;

    CardAdapter(Context context, SelectCardListener selectCardListener) {
        inflater = LayoutInflater.from(context);
        this.selectCardListener = selectCardListener;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.book_item, parent, false);
        return new CardHolder(itemView, selectCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        holder.bind(cards.get(position));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setCards(List<Card> cardList) {
        cards.clear();
        cards.addAll(cardList);
        notifyDataSetChanged();
    }

    class CardHolder extends RecyclerView.ViewHolder {

        private final TextView CardNameView;
        private final TextView CardPhoneView;
        private final SelectCardListener selectCardListener;

        CardHolder(View view, SelectCardListener selectCardListener) {
            super(view);
            this.selectCardListener = selectCardListener;
            CardNameView = view.findViewById(R.id.book_item_name);
            CardPhoneView = view.findViewById(R.id.book_item_author);
        }

        void bind(final Card card) {
            CardNameView.setText(card.getName());
            CardPhoneView.setText(card.getPhone());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCardListener.onCardSelect(card);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectCardListener.onCardLongClick(card);
                    return true;
                }
            });

        }

    }

    interface SelectCardListener {

        void onCardSelect(Card card);

        void onCardLongClick(Card card);

    }

}
