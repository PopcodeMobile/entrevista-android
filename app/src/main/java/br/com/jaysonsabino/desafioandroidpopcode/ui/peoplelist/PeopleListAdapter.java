package br.com.jaysonsabino.desafioandroidpopcode.ui.peoplelist;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.jaysonsabino.desafioandroidpopcode.databinding.CharacterListItemBinding;
import br.com.jaysonsabino.desafioandroidpopcode.entities.Character;

public class PeopleListAdapter extends PagedListAdapter<Character, PeopleListAdapter.CharacterViewHolder> {

    private Context context;
    private OnClickListener onClickListener;

    PeopleListAdapter(Context context) {
        super(new DiffPeopleCallback());
        this.context = context;
    }

    public void setOnItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        CharacterListItemBinding itemBinding = CharacterListItemBinding.inflate(inflater, viewGroup, false);

        CharacterViewHolder characterViewHolder = new CharacterViewHolder(itemBinding);
        if (onClickListener != null) {
            characterViewHolder.setOnClickListener(onClickListener);
        }
        return characterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder characterViewHolder, int i) {
        characterViewHolder.bind(getItem(i));
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {

        CharacterListItemBinding itemBinding;

        CharacterViewHolder(@NonNull CharacterListItemBinding itemBinding) {
            super(itemBinding.getRoot());

            this.itemBinding = itemBinding;
        }

        void bind(Character character) {
            itemBinding.setListItemCharacter(character);
        }

        void setOnClickListener(final OnClickListener onClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Character item = getItem(getAdapterPosition());

                    onClickListener.onClick(v, item);
                }
            });
        }
    }

    public interface OnClickListener {

        void onClick(View v, Character character);
    }
}
