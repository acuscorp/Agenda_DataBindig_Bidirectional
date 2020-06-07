package com.acuscorp.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.acuscorp.agenda.databinding.ContactoFragmetBinding;


public class ContactoFragmet extends Fragment {
  public static final String  EXTRA_ID = "acuscorp.EXTRA_ID";
  public static final String  EXTRA_NOMBRE = "acuscorp.EXTRA_NOMBRE";
  public static final String  EXTRA_TELEFONO = "acuscorp.EXTRA_TELEFONO";
  public static final String  EXTRA_CUMPLE = "acuscorp.EXTRA_CUMPLE";
  public static final String  EXTRA_NOTA = "acuscorp.EXTRA_NOTA";


  private Button save;
  private ContactViewModel contactViewModel;
  private String nombre;
  private String telefono;
  private String cumple;
  private String nota;
  private int id;
  private boolean isNewContact =false;
  private Contact contact;
  private ContactObservable contactObservable;
  private ViewDataBinding binding;


  public ContactoFragmet() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    if (getArguments() != null) {
      contact = (Contact) getArguments().getSerializable("puto");    }
    else {
      contact = new Contact("","","","");
    }
    contactObservable = new ContactObservable(contact,R.layout.contacto_fragmet);

    contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
    contactObservable.setListener(contactViewModel);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    binding = DataBindingUtil.inflate(inflater,R.layout.contacto_fragmet,container,false);
    binding.setVariable(BR.contacto,contactObservable);
    return  binding.getRoot();
  }




}
