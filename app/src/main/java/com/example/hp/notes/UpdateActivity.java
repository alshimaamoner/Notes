package com.example.hp.notes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hp.notes.Base.BaseActivity;
import com.example.hp.notes.DataBase.Model.MyDataBase;
import com.example.hp.notes.DataBase.Todo;

import java.util.Calendar;

public class UpdateActivity extends BaseActivity implements View.OnClickListener {

    protected Toolbar toolbar;
    protected EditText title;
    protected EditText date;
    protected EditText content;
    protected Button update;
    String type;
  //  Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_update);
        initView();
      //  int id=getIntent().getIntExtra("id",1);
      //  Log.e("id",id+"");
        Todo todo=MyDataBase.getInstance(activity).TodoDao().selectTodo(HomeActivity.ID);
        title.setText(HomeActivity.title);
        date.setText(HomeActivity.date);
        content.setText(HomeActivity.content);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.update) {

           MyDataBase.getInstance(activity).
                    TodoDao().
                        update(HomeActivity.ID,title.getText().toString(),date.getText().toString(),content.getText().toString());
            /*todo.setId(HomeActivity.ID);
            todo.setTitle(title.getText().toString());
             todo.setDate(date.getText().toString());
             todo.setContent(content.getText().toString());

            MyDataBase.getInstance(activity).TodoDao().UpdatTask(todo);
            */
            showConfirmMessage(R.string.update,R.string.success, android.R.string.ok, new MaterialDialog.SingleButtonCallback() {
              @Override
              public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                  Intent intent = new Intent(UpdateActivity.this,HomeActivity.class);
                  startActivity(intent);
              }
          });
        }
    }

    private void initView() {
        toolbar =  findViewById(R.id.toolbar);

        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        content =  findViewById(R.id.content);
        update =  findViewById(R.id.update);
        update.setOnClickListener(UpdateActivity.this);
    }
    public void setAlarmForTodo(){
        AlarmManager alarmManager= (AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hours);
        calendar.set(Calendar.MINUTE,minutes);
        Intent  alarmIntent=new Intent(activity,TaskAlarmBroadCastReciever.class);
        alarmIntent.putExtra("title",title.getText().toString());
        alarmIntent.putExtra("desc",content.getText().toString());
        PendingIntent pendingIntent=PendingIntent.getBroadcast(activity,
                0,alarmIntent,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),pendingIntent);
    }

    int hours=-1;int minutes=-1;
    public void openDatePicker(View view) {
        Calendar calendar=Calendar.getInstance();
        TimePickerDialog timePickerDialog=new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay < 12) {
                    type = "AM";
                } else {
                    type = "PM";
                }
                hours=hourOfDay;
                minutes=minute;
                if(minute<10)
                date.setText(hourOfDay+":"+"0"+minute+""+type);
                else
                    date.setText(hourOfDay+":"+minute+""+type);
            }
        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false);
        timePickerDialog.show();
    }
}
