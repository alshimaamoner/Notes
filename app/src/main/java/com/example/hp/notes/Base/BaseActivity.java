package com.example.hp.notes.Base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.LightingColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hp.notes.R;


public class BaseActivity extends AppCompatActivity {
   protected AppCompatActivity activity;
   MaterialDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
    }
  public MaterialDialog showMessage(int titleResId,int MessageResId, int posResText){
   dialog=new MaterialDialog.Builder(this).title( titleResId).content(MessageResId).positiveText(posResText).onPositive(new MaterialDialog.SingleButtonCallback() {
       @Override
       public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
           dialog.dismiss();
       }
   })
           .show();
      return dialog;
  }
    public MaterialDialog showConfirmMessage(int titleResId, int MessageResId, int posResText, MaterialDialog.SingleButtonCallback onpositiveAction){
        dialog=new MaterialDialog.Builder(this).
                title( titleResId).content(MessageResId).
                positiveText(posResText).onPositive(onpositiveAction)
                .show();
        return dialog;
    }
    public MaterialDialog showConfirmMessage(String titleRes, String MessageRes, MaterialDialog.SingleButtonCallback onpositiveAction){
        dialog=new MaterialDialog.Builder(this).contentColorRes(R.color.colorAccent).
                title( titleRes).titleColorRes(R.color.colorAccent).content(MessageRes).
                onPositive(onpositiveAction)
                .show();
        dialog.getTitleView().setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);

        return dialog;
    }

    public MaterialDialog  showMessage(String title, String Message){
        dialog=new MaterialDialog.Builder(this).title( title).content(Message).show();

        return dialog;
    }
    public MaterialDialog  showMessage(String title, String Message, String posText){
        dialog=new MaterialDialog.Builder(this).title( title).content(Message).positiveText(posText).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        }).show();
        return dialog;
    }
    public MaterialDialog  showMessage(int titleResId,int MessageResId){
        dialog=new MaterialDialog.Builder(this).title( titleResId).content(MessageResId).show();
        return dialog;
    }

    public  MaterialDialog showProgressBar(){
   dialog=new  MaterialDialog.Builder(this).progress(true,0).cancelable(false). show();
   return dialog;
    }
    public MaterialDialog hideProgressBar(){
       if(dialog!=null &&dialog.isShowing())
           dialog.dismiss();

    return dialog;
    }}

