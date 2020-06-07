package com.acuscorp.agenda;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
  private ContactDAO contactDAO;
  private LiveData<List<Contact>> allContacts;
   public Repository(Application application){
     ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
     contactDAO = contactDatabase.constactDAO();
     allContacts = contactDAO.getAllContacts();
   }

   public void insert(Contact contact){
     new InsertContactAsyncTask(contactDAO).execute(contact);
   }

  public void update(Contact contact){
    new UpdateContactAsynkTask(contactDAO).execute(contact);
  }

  public  void delete(Contact contact){
    new DeleteContactAsynkTask(contactDAO).execute(contact);
  }
  public  void deleteAllContact(){
    new DeleteAllContactAsynkTask(contactDAO).execute();
  }

  public LiveData<List<Contact>> getAllContact() {
    return allContacts;
  }


  private class InsertContactAsyncTask extends AsyncTask<Contact,Void,Void> {
     private ContactDAO contactDAO;
    public InsertContactAsyncTask(ContactDAO contactDAO) {
      this.contactDAO = contactDAO;
    }

    @Override
    protected Void doInBackground(Contact... contacts) {
      contactDAO.insert(contacts[0]);
      return null;
    }
  }
  public static class UpdateContactAsynkTask extends AsyncTask<Contact,Void,Void>{
    private ContactDAO contactDAO;

    public UpdateContactAsynkTask(ContactDAO contactDAO) {
      this.contactDAO = contactDAO;
    }

    @Override
    protected Void doInBackground(Contact... contacts) {
      contactDAO.update(contacts[0]);
      return null;
    }
  }
  public static class DeleteContactAsynkTask extends AsyncTask<Contact,Void,Void>{
    private ContactDAO contactDAO;

    public DeleteContactAsynkTask(ContactDAO contactDAO) {
      this.contactDAO = contactDAO;
    }

    @Override
    protected Void doInBackground(Contact... contacts) {
      contactDAO.delete(contacts[0]);
      return null;
    }
  }
  public static class DeleteAllContactAsynkTask extends AsyncTask<Void,Void,Void>{
    private ContactDAO contactDAO;

    public DeleteAllContactAsynkTask(ContactDAO contactDAO) {
      this.contactDAO = contactDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      contactDAO.deleteAllContacts( );
      return null;
    }
  }
}
