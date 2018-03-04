package fi.tut.student.suvanto.simpledict;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/** The data access object for words.
 *
 * Defines methods to add, remove and list words.
 */

@Dao
public interface WordDao {

    /** Add a new word to the database.
     *
     * @param word The word.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    /** Remove a word from the database.
     *
     * @param word The word.
     */
    @Delete
    void delete(Word word);

    /** Fetch all words in ascending order from the database.
     *
     * @return All words.
     */
    @Query("SELECT * FROM words ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

}
