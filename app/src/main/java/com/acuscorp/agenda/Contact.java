package com.acuscorp.agenda;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.acuscorp.agenda.BR;

import java.io.Serializable;
import java.util.Objects;


@Entity(tableName = "contact_table")
public class Contact extends BaseObservable implements Serializable {
  @PrimaryKey(autoGenerate = true)
  private int id;
  private String nombre;
  private String telefono;
  private String cumple;
  private String nota;
  @Ignore
  public Contact() {
  }

  public Contact(String nombre, String telefono, String cumple, String nota) {
    this.nombre = nombre;
    this.telefono = telefono;
    this.cumple = cumple;
    this.nota = nota;

  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;

  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;

  }

  @Bindable
  public String getCumple() {
    return cumple;
  }

  public void setCumple(String cumple) {
    this.cumple = cumple;

  }

  @Bindable
  public String getNota() {
    return nota;
  }

  public void setNota(String nota) {
    this.nota = nota;
  }
  @Override
  public String toString() {
    return "Contact{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", telefono='" + telefono + '\'' +
            ", cumple='" + cumple + '\'' +
            ", nota='" + nota + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Contact contact = (Contact) o;
    return id == contact.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

