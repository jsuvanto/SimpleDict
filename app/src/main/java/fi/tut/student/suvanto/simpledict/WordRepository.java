package fi.tut.student.suvanto.simpledict;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/** Local repository for words in the database.
 */

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application app) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(app);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    /** Gets all the words in the local repository.
     *
     * @return All the words.
     */
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    /** Creates a new async task to add a new word in the database.
     *
     * @param word The word.
     */
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    /** Creates a new async task to remove a word from the database.
     *
     * @param word The word.
     */
    public void delete(Word word) {
        new deleteAsyncTask(mWordDao).execute(word);
    }

    /** An async task class to add a new word in the database.
     *
     */
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;
        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

    /** An async task class to remove a word from the database.
     *
     */
    private static class deleteAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;
        deleteAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncTaskDao.delete(words[0]);
            return null;
        }
    }
}
