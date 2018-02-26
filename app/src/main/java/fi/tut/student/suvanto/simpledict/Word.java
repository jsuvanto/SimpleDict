package fi.tut.student.suvanto.simpledict;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by JSuvanto on 19.2.2018.
 */

@Entity(tableName="words")
public class Word {

    @NonNull
    @ColumnInfo(name="word")
    @PrimaryKey
    private String mWord;

    @NonNull
    @ColumnInfo(name="language")
    private String mLanguage;

    @NonNull
    @ColumnInfo(name="wordClass")
    private String mWordClass;

    @NonNull
    @ColumnInfo(name="translation")
    private String mTranslation;

    public Word(@NonNull String word, @NonNull String language, @NonNull String wordClass, @NonNull String translation) {
        this.mWord = word;
        this.mLanguage = language;
        this.mWordClass = wordClass;
        this.mTranslation = translation;
    }

    @NonNull
    public String getWord() {
        return this.mWord;
    }

    @NonNull
    public String getLanguage() {
        return this.mLanguage;
    }

    @NonNull
    public String getWordClass() {
        return this.mWordClass;
    }

    @NonNull
    public String getTranslation() {
        return this.mTranslation;
    }
}

