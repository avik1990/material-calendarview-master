package com.prolificinteractive.materialcalendarview.sampletest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynamicSettersActivity extends AppCompatActivity implements OnDateLongClickListener, OnDateSelectedListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @BindView(R.id.calendarView)
    MaterialCalendarView widget;
    @BindView(R.id.parent)
    ViewGroup parent;

    private int currentTileSize;
    private int currentTileWidth;
    private int currentTileHeight;
    List<Date> listdate = new ArrayList<>();
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_setters);
        ButterKnife.bind(this);

        currentTileSize = MaterialCalendarView.DEFAULT_TILE_SIZE_DP;
        currentTileWidth = MaterialCalendarView.DEFAULT_TILE_SIZE_DP;
        currentTileHeight = MaterialCalendarView.DEFAULT_TILE_SIZE_DP;
        widget.setOnDateChangedListener(this);
        widget.setPagingEnabled(true);
        widget.setOnDateLongClickListener(this);

        String dtStart = "2018-07-26";
        String dtStart1 = "2018-07-28";
        String dtStart2 = "2018-08-28";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        listdate.clear();
        try {
            listdate.add(format.parse(dtStart));
            listdate.add(format.parse(dtStart1));
            listdate.add(format.parse(dtStart2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);

        // widget.setDateSelected(calendar, true);
        //widget.setDateSelected(CalendarDay.today(), true);
        for (int i = 0; i < listdate.size(); i++) {
            widget.setDateSelected(toCalendar(listdate.get(i)), true);
        }
    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    @Override
    public void onDateLongClick(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date) {
        Toast.makeText(this, FORMATTER.format(date.getDate()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date,
                               boolean selected) {
        Toast.makeText(this, "" + FORMATTER.format(date.getDate()), Toast.LENGTH_SHORT).show();
    }


}
