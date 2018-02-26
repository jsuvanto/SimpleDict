package fi.tut.student.suvanto.simpledict;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by JSuvanto on 19.2.2018.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvWord;
        private final TextView tvClass;
        private final TextView tvLang;
        private final TextView tvTrans;

        private WordViewHolder(View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tv_word);
            tvClass = itemView.findViewById(R.id.tv_class);
            tvLang = itemView.findViewById(R.id.tv_language);
            tvTrans = itemView.findViewById(R.id.tv_translation);
        }
    }

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words

    WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.tvWord.setText(current.getWord());
            holder.tvLang.setText(String.format("%s:", current.getLanguage()));
            holder.tvClass.setText(current.getWordClass());
            holder.tvTrans.setText(String.format("\"%s\"", current.getTranslation()));
        } else {
            // Covers the case of data not being ready yet.
            holder.tvWord.setText("No Word");
        }
    }

    void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}