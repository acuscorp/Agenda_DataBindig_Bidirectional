package com.acuscorp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class AgendaActivity extends AppCompatActivity{

  private AgendaListFragment agenda_list_fragment;
  private ContactViewModel contactViewModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_agenda);



    agenda_list_fragment = new AgendaListFragment();
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.contact_fragmet, agenda_list_fragment)
            .commit();

  }
}
