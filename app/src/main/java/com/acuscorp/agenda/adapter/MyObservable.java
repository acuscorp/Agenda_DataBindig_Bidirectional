package com.acuscorp.agenda.adapter;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.databinding.BaseObservable;

public abstract class MyObservable extends BaseObservable {
  @LayoutRes
  public abstract int layout();
  @IdRes
  public abstract int variable();


}
