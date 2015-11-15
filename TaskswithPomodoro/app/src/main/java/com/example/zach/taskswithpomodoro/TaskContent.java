package com.example.zach.taskswithpomodoro;

import com.example.zach.taskswithpomodoro.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zach on 11/14/2015.
 */
public class TaskContent {

    public static List<Task> ITEMS = new ArrayList<Task>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.

            addItem(createTask("sample",String.valueOf(1)));

    }

    public static void addItem(Task item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }

    private static Task createTask(String title, String details) {
        return new Task(title,details);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


    public static class Task {
        public String title;
        public String details;
        public int numPomodoros=1;

        public Task(String title, String details) {
            this.title  = title;
            this.details = details;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}