package com.acuscorp.agenda;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class ContactViewModel extends AndroidViewModel implements ContactObservable.ContactoListener {

    private Repository repository;
    private LiveData<List<Contact>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allContacts = repository.getAllContact();
    }
    public void insert(Contact contact){
        repository.insert(contact);

    }
    public void update(Contact contact){
        repository.update(contact);

    }
    public void delete(Contact contact){
        repository.delete(contact);

    }
    public void deleteAllContact(){
        repository.deleteAllContact();

    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

  @Override
  public void onConctactChange(Contact contact) {
      if (contact.getId() > 0) {
            repository.update(contact);
      }else {
          repository.insert(contact);
      }
  }
}
