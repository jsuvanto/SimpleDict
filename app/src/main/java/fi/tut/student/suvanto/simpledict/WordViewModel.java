package fi.tut.student.suvanto.simpledict;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/** A view model for remembering the words to display.
 */

public class WordViewModel extends AndroidViewModel {
    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application app) {
        super(app);
        mRepository = new WordRepository(app);
        mAllWords = mRepository.getAllWords();
    }

    /** Gets all the words in the view model.
     *
     * @return All the words.
     */
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    /** Adds a new word to the database through the local repository.
     *
     * @param word The word.
     */
    public void insert(Word word) {
        mRepository.insert(word);
    }

    /** Removes a word from the database through the local repository.
     *
     * @param word The word.
     */
    public void delete(Word word) {
        mRepository.delete(word);
    }
}
