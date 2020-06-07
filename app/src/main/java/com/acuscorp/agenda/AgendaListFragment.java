package com.acuscorp.agenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acuscorp.agenda.adapter.Adapter;
import com.acuscorp.agenda.adapter.MyObservable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class AgendaListFragment extends Fragment implements ContactObservable.ContactoListener {

  public static final int ADD_CONTACT_REQUEST = 1;
  public static final int EDIT_CONTACT_REQUEST = 2;
  private View view;
  private View popupInputDialogView;
  private ContactViewModel contactViewModel;
  private ContactAdapter contactAdapter;
  private ContactoFragmet contactoFragmet;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    view = inflater.inflate(R.layout.agenda_list_fragment, container, false);
    FloatingActionButton buttonAddContact = view.findViewById(R.id.button_add_contact);

    contactoFragmet = new ContactoFragmet();
    buttonAddContact.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(getContext(), "Hello you clicked add", Toast.LENGTH_LONG).show();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contact_fragmet,contactoFragmet)
                .addToBackStack(null)
                .commit();
      }
    });
    init();
    return view;
  }

  private void init() {
    LayoutInflater layoutInflater = LayoutInflater.from(getContext());
    popupInputDialogView = layoutInflater.inflate(R.layout.promtp, null);
    RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
    recyclerView.setHasFixedSize(true);

//    contactAdapter = new ContactAdapter();
    final Adapter adapter = new Adapter();
    recyclerView.setAdapter(adapter);

    contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
    contactViewModel.getAllContacts().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
      @Override
      public void onChanged(List<Contact> contacts) {
        List<MyObservable> observables = new ArrayList<>();
        for(Contact contact: contacts){
          ContactObservable contactObservable = new ContactObservable(contact,R.layout.contact_itmem);
          contactObservable.setListener(AgendaListFragment.this);
          observables.add(contactObservable);
        }
        adapter.setChildren(observables);
//        contactAdapter.submitList(contacts);
  }
});

    new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        Contact contact = contactAdapter.getcontactAt(viewHolder.getAdapterPosition());
        alertDialog(contact);


      }
    }).attachToRecyclerView(recyclerView);


//    contactAdapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
//      @Override
//      public void onItemClick(Contact contact) {
//        Bundle bundle = new Bundle();
//        String nombre = contact.getNombre();
//        String cumple = contact.getCumple();
//        String telefono = contact.getTelefono();
//        String nota  = contact.getNota();
//        int id = contact.getId();
//        bundle.putSerializable("puto",contact);
//        contactoFragmet.setArguments(bundle);
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.contact_fragmet,contactoFragmet)
//                .addToBackStack(null)
//                .commit();
//      }
//    });
  }

  private void alertDialog(final Contact contact) {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
    alertDialogBuilder.setTitle("AusCorp");
    alertDialogBuilder.setIcon(R.drawable.ic_save);
    alertDialogBuilder.setCancelable(false);
    alertDialogBuilder.setView(popupInputDialogView);

    alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int id) {
                        contactViewModel.delete(contact);
                        Toast.makeText(getActivity(), "Contact deleted", Toast.LENGTH_SHORT).show();
                      }
                    })
            .setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(getActivity(), "Contact not deleted", Toast.LENGTH_SHORT).show();
                      }
                    });


    // create alert dialog
    AlertDialog alertDialog = alertDialogBuilder.create();

    // show it
    alertDialog.show();


  }


  @Override
  public void onConctactChange(Contact contact) {
    Bundle bundle = new Bundle();
        String nombre = contact.getNombre();
        String cumple = contact.getCumple();
        String telefono = contact.getTelefono();
        String nota  = contact.getNota();
        int id = contact.getId();
        bundle.putSerializable("puto",contact);
        contactoFragmet.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contact_fragmet,contactoFragmet)
                .addToBackStack(null)
                .commit();

  }
}
