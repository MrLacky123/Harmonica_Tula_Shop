package com.example.harmonicatulashop.ui.catalog.db.room;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.harmonicatulashop.MainActivity;
import com.example.harmonicatulashop.R;
import com.example.harmonicatulashop.ui.catalog.db.dao.AccordionDao;
import com.example.harmonicatulashop.ui.models.Accordion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Accordion.class}, version = 1, exportSchema = false)
public abstract class AccordionRoomDatabase extends RoomDatabase {

    public abstract AccordionDao accordionDao();

    private static volatile AccordionRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AccordionRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AccordionRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AccordionRoomDatabase.class, "accordion_list_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                AccordionDao dao = INSTANCE.accordionDao();
                dao.deleteAll();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.Instance.getApplicationContext().getResources(), R.drawable.bayan_1);
                bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, bos);
                byte[] blob = bos.toByteArray();

                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Accordion accordion = new Accordion(blob, "55/100", 115000, "пять регистров");
                dao.insert(accordion);
            });
        }
    };
}
