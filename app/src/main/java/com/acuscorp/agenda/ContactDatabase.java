package com.acuscorp.agenda;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase{

  public static volatile ContactDatabase instance;
  public abstract ContactDAO constactDAO();
  private static final int NUMBER_OF_THREADS = 4;
  static final ExecutorService databaseWriteExecutor =
          Executors.newFixedThreadPool(NUMBER_OF_THREADS);

  public static synchronized ContactDatabase getInstance(Context context){
    if(instance==null){
      instance = Room.databaseBuilder(context.getApplicationContext(),ContactDatabase.class,"contact_database").build();
    }
    return instance;
  }
  private static RoomDatabase.Callback roomCallback  = new RoomDatabase.Callback(){
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
    }
  };
}
