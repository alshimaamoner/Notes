package com.example.hp.notes;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.hp.notes.Adapter.NoteAdapter;
import com.example.hp.notes.Base.BaseActivity;
import com.example.hp.notes.DataBase.Model.MyDataBase;
import com.example.hp.notes.DataBase.Todo;

import java.util.List;

public class HomeActivity extends BaseActivity {
    protected Toolbar toolbar;
    protected AppBarLayout appBarLayout;
    protected FloatingActionButton fab;
    NoteAdapter Adapter;
    RecyclerView NoteRecyle;
    List<Todo> items;
    RecyclerView.LayoutManager manager;
    SwipeController swipeController = null;
    static int ID ;
    static String title,date,content;
    ConstraintLayout constraintLayout;
   static Todo  mRecentlyDeletedItem;
    int mRecentlyDeletedItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initView();
        manager = new LinearLayoutManager(activity);
        Adapter = new NoteAdapter(null);
        setSupportActionBar(toolbar);
        NoteRecyle.setAdapter(Adapter);
        NoteRecyle.setLayoutManager(manager);
        setupRecyclerView();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, AddTodoActivity.class);
            startActivity(intent);
        });
        Adapter.setOnItemClickListener((id, Title, Date, Content) -> {
            Intent intent = new Intent(HomeActivity.this, UpdateActivity.class);
            ID=id;
            title= Title;
          date=Date;
          content=Content;
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
         items = MyDataBase.getInstance(activity).TodoDao().selectAllTodo();
        Adapter.ChangeData(items);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appBarLayout);
        NoteRecyle = findViewById(R.id.NoteRecyle);
        fab = findViewById(R.id.fab);
    }
int pos;
    private void setupRecyclerView() {
        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(final int position) {
                pos=position;
                mRecentlyDeletedItem=items.get(position);
                MyDataBase.getInstance(activity).TodoDao().deletItem(items.get(position).getId());
                items.remove(position);
                Adapter.notifyDataSetChanged();
                showUndoSnackbar();
        }
        });


        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(NoteRecyle);


        NoteRecyle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });


    }
    public void showUndoSnackbar() {
        View view = activity.findViewById(R.id.constraint);
        Snackbar snackbar = Snackbar.make(view, R.string.snack_bar_text,
                Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.snack_bar_undo, v -> undoDelete());
        snackbar.show();
    }

    private void undoDelete() {
        items.add(pos,mRecentlyDeletedItem);
     MyDataBase.getInstance(activity).TodoDao().insertion(mRecentlyDeletedItem);
     Adapter.ChangeData(items);
    }

}