package fi.tut.student.suvanto.simpledict;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by JSuvanto on 19.2.2018.
 */

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Delete
    void delete(Word word);

    @Query("DELETE FROM words")
    void deleteAll();

    @Query("SELECT * FROM words ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    @Query("SELECT * FROM words WHERE language IS :language ORDER BY word ASC")
    LiveData<List<Word>> getWordsByLanguage(String language);

    @Query("SELECT * FROM words ORDER BY :order ASC")
    LiveData<List<Word>> getOrderedWords(String order);


}
