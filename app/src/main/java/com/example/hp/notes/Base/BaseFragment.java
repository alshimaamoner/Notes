package com.example.hp.notes.Base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;


public class BaseFragment extends Fragment {
    protected  BaseActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(BaseActivity) context;
    }

    public MaterialDialog showMessage(int titleResId, int MessageResId, int posResText){

        return activity.showMessage(titleResId,MessageResId,posResText);
    }

    public MaterialDialog  showMessage(String title, String Message, String posText){
     return  activity.showMessage(title,Message,posText);
    }
    public MaterialDialog  showMessage(String title, String Message){
        return  activity.showMessage(title,Message);
    }
    public MaterialDialog  showMessage(int titleResId,int MessageResId){
        return  activity.showMessage(titleResId,MessageResId);
    }

    public MaterialDialog showProgressBar(){
      return activity.showProgressBar();
    }

    public MaterialDialog showConfirmMessage(int titleResId, int MessageResId, int posResText, MaterialDialog.SingleButtonCallback onpositiveAction){

        return activity.showConfirmMessage(titleResId,MessageResId,posResText,onpositiveAction);
    }
    @SuppressLint("ResourceAsColor")
    public MaterialDialog showConfirmMessage(String titleRes, String MessageRes, MaterialDialog.SingleButtonCallback onpositiveAction){
        return activity.showConfirmMessage(titleRes,MessageRes,onpositiveAction);
    }
    public MaterialDialog hideProgressBar(){
       return  activity.hideProgressBar();
    }}
