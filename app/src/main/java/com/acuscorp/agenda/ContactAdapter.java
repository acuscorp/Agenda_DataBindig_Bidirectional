package com.acuscorp.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.acuscorp.agenda.databinding.ContactItmemBinding;

public class ContactAdapter extends ListAdapter<Contact,ContactAdapter.ContactHolder> {

  public ContactAdapter() {
    super(DIFF_CALLBACK);

  }
  private static final DiffUtil.ItemCallback<Contact> DIFF_CALLBACK = new DiffUtil.ItemCallback<Contact>() {
    @Override
    public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {

      return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
      return oldItem.getNombre().equals(newItem.getNombre()) &&
              oldItem.getCumple().equals(newItem.getCumple());
    }
  };


  private  OnItemClickListener listener;
  @Override
  public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


    LayoutInflater layoutInflater =
            LayoutInflater.from(parent.getContext());
    ContactItmemBinding itemBinding =
            ContactItmemBinding.inflate(layoutInflater, parent, false);


    return new ContactHolder(itemBinding);
  }

  @Override
  public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
    Contact currentContact = getItem(position);
    holder.bind(currentContact);
  }
  public Contact getcontactAt(int position){
    return getItem(position);
  }



  class ContactHolder extends RecyclerView.ViewHolder{
//    private TextView textViewNombre, textViewTelefono, textViewCumple, textViewNota;
    private final ContactItmemBinding binding;
    public ContactHolder(ContactItmemBinding binding) {
//      super(itemView);
      super(binding.getRoot());
      this.binding = binding;

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          int position  = getAdapterPosition();
          if(listener!=null && position != RecyclerView.NO_POSITION)
          {
            listener.onItemClick(getItem(position));
          }
        }
      });
    }
    public void bind(Contact item) {
//      binding.setContacto(item);
//      binding.executePendingBindings();
    }
  }

  public Contact getContactAt(int position){
    return getItem(position);
  }
  public interface OnItemClickListener{
    void onItemClick(Contact contact);
  }

  public void setOnItemClickListener(OnItemClickListener listener){
    this.listener = listener;
  }
}
