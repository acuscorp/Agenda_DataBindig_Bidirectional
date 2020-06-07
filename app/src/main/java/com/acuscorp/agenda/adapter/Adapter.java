package com.acuscorp.agenda.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.acuscorp.agenda.ContactObservable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Adapter<T extends MyObservable> extends RecyclerView.Adapter<Adapter.MyViewHolder> {


  private List<T> myObservables;
  public Adapter(){
    myObservables = new LinkedList<>();
  }

  public void insertItem(T myObservable){
    this.myObservables.add(myObservable);

  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    return new MyViewHolder(
            DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    viewType,
                    parent,
                    false)
    );
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.bind(myObservables.get(position));

  }

  @Override
  public int getItemCount() {
    return myObservables.size();
  }

  @Override
  public int getItemViewType(int position) {
    MyObservable child = myObservables.get(position);
    return child.layout();
  }

  public void setChildren(List<T> observables) {
    this.myObservables = observables;
    notifyDataSetChanged();

  }

  static class MyViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding viewDataBinding;

    public MyViewHolder( @NonNull ViewDataBinding viewDataBinding) {
      super(viewDataBinding.getRoot());

      this.viewDataBinding = viewDataBinding;
    }
    public void bind(MyObservable observable){
      viewDataBinding.setVariable(observable.variable(),observable);

    }
  }


}
