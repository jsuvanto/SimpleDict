package fi.tut.student.suvanto.simpledict;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private WordViewModel wordViewModel;

    private EditText et_word;
    private EditText et_language;
    private EditText et_translation;
    private Spinner sp_class;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_word = findViewById(R.id.et_word);
        et_language = findViewById(R.id.et_language);
        et_translation = findViewById(R.id.et_translation);
        sp_class = findViewById(R.id.sp_class);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.wordclasses, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_class.setAdapter(spinnerAdapter);


        recyclerView = findViewById(R.id.rv_words);
        final WordListAdapter wordListAdapter = new WordListAdapter(this);
        recyclerView.setAdapter(wordListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                wordListAdapter.setWords(words);
            }
        });

    }

    public void addWord(View view) {

        String newWord = et_word.getText().toString();
        String newWordLanguage = et_language.getText().toString();
        String newWordTranslation = et_translation.getText().toString();
        String newWordClass = sp_class.getSelectedItem().toString();

        if (!newWord.isEmpty() && !newWordLanguage.isEmpty() && !newWordTranslation.isEmpty()) {
            Word word = new Word(newWord, newWordLanguage, newWordClass, newWordTranslation);
            wordViewModel.insert(word);
        } else {
            Toast.makeText(this, R.string.not_added, Toast.LENGTH_SHORT).show();

        }
    }

    public void deleteWord(View view) {

        

    }

}
