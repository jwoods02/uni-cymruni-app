package com.example.c1629177.cymruniapp;

import android.support.v4.app.NotificationCompat;
import android.widget.ListAdapter;
import android.widget.ListView;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by James on 03/04/2017.
 */
public class MainActivityTest extends TestCase {


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        List<String> DBNames;
        List<String> DBBeacons;


        NotificationCompat.Builder notification;

        DBNames = new ArrayList<>() ;
        DBNames.addAll(Arrays.asList("Scholar", "The Bay", "Academy"));
        DBBeacons = new ArrayList<>() ;
        DBBeacons.addAll(Arrays.asList("ABC-12D-123", "FHD-24F-EF8", "WSE-234-DBE"));
    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void forceAddBeaconTest() throws Exception {
        ArrayList<String> beaconsDetected;
        List<String> printedBusinessList;
        String currentBeacon;
        int DBIndex;



        MainActivity mainActivity = new MainActivity();
        printedBusinessList = new ArrayList<>();
        mainActivity.forceAddBeacon("ABC-12D-123");
        assertEquals(printedBusinessList, "ABC-12D-123");

    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}