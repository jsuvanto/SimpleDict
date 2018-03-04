package fi.tut.student.suvanto.simpledict;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/** Main activity for the program.
 *
 * Shows the current database contents, as well as fields and buttons to add words, and a button to
 * delete the first word.
 */

public class MainActivity extends AppCompatActivity {

    private WordViewModel wordViewModel;
    private EditText et_word;
    private EditText et_language;
    private EditText et_translation;
    private Spinner sp_class;
    private RecyclerView recyclerView;
    private WordListAdapter wordListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_word = findViewById(R.id.et_word);
        et_language = findViewById(R.id.et_language);
        et_translation = findViewById(R.id.et_translation);
        sp_class = findViewById(R.id.sp_class);

        // Populate the spinner.
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.wordclasses, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_class.setAdapter(spinnerAdapter);

        // Populate the recycler view.
        recyclerView = findViewById(R.id.rv_words);
        wordListAdapter = new WordListAdapter(this);
        recyclerView.setAdapter(wordListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Link the view model to the recycler view.
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                wordListAdapter.setWords(words);
            }
        });

        // Delete words from database when swiping them right.
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getLayoutPosition();
                Word word = wordListAdapter.getItem(index);
                wordViewModel.delete(word);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    /** Creates the main menu.
     *
     * @param menu The menu.
     * @return Propagate call to parent.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /** Add a word to the database.
     *
     * @param view The current view.
     */
    public void addWord(View view) {

        String newWord = et_word.getText().toString();
        String newWordLanguage = et_language.getText().toString();
        String newWordTranslation = et_translation.getText().toString();
        String newWordClass = sp_class.getSelectedItem().toString();

        if (!newWord.isEmpty() && !newWordLanguage.isEmpty() && !newWordTranslation.isEmpty()) {
            Word word = new Word(newWord, newWordLanguage, newWordClass, newWordTranslation);
            wordViewModel.insert(word);

            et_word.setText("");
            et_language.setText("");
            et_translation.setText("");

        } else {
            Toast.makeText(this, R.string.not_added, Toast.LENGTH_SHORT).show();

        }
    }

    /** Remove the first word from the database.
     *
     * @param view The current view.
     */
    public void deleteWord(View view) {
        if (wordListAdapter.getItemCount() > 0) {
            Word word = wordListAdapter.getItem(0);
            wordViewModel.delete(word);
        }
    }
}
