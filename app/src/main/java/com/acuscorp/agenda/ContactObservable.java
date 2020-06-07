package com.acuscorp.agenda;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.databinding.Bindable;

import com.acuscorp.agenda.adapter.MyObservable;


public class ContactObservable extends MyObservable {
  @LayoutRes
  private int layout;
  private Contact contact;
  private ContactoListener listener;
  public ContactObservable(Contact contact,@LayoutRes int layout){
    this.contact = contact;
    this.layout = layout;
  }

  public Contact getContact() {
    return contact;
  }

  public ContactoListener getListener() {
    return listener;
  }

  public void setListener(ContactoListener listener) {
    this.listener = listener;
  }

  @Bindable
  public int getId() {
    return contact.getId();
  }

  public void setId(int id) {
    contact.setId(id);
  }

  @Bindable
  public String getNombre() {
    return contact.getNombre();
  }

  public void setNombre(String nombre) {
    contact.setNombre(nombre);
    notifyPropertyChanged(com.acuscorp.agenda.BR.nombre);
  }

  @Bindable
  public String getTelefono() {
    return contact.getTelefono();
  }

  public void setTelefono(String telefono) {
    contact.setTelefono(telefono);
    notifyPropertyChanged(BR.telefono);
  }

  @Bindable
  public String getCumple() {
    return contact.getCumple();
  }

  public void setCumple(String cumple) {
    contact.setCumple(cumple);
    notifyPropertyChanged(BR.cumple);
  }

  @Bindable
  public String getNota() {
    return contact.getNota();
  }

  public void setNota(String nota) {
    contact.setNota(nota);
    notifyPropertyChanged(BR.nota); //bindable resource

  }

  public void onClick(View v) {
    if (layout == R.layout.contact_itmem) {
      listener.onConctactChange(getContact());

    }
    if (layout == R.layout.contacto_fragmet) {
      listener.onConctactChange(getContact());
    }
  }
  @Override
  public int layout() {
    return layout;
  }

  @Override
  public int variable() {
    return BR.contacto;
  }

  public interface ContactoListener {
    void onConctactChange(Contact contact);

  }

}
