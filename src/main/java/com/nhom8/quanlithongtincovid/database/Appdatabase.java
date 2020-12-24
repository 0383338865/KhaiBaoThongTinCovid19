package com.nhom8.quanlithongtincovid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nhom8.quanlithongtincovid.data.User;

// Khởi tạo database
@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class Appdatabase extends RoomDatabase {
    //gọi ra 1 instance của appdatabase
    private static Appdatabase INSTANCE;

    public abstract UserDao userDao();

    public static Appdatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            //Creates a RoomDatabase.Builder for an in memory database
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Appdatabase.class, "UserDatabase")
                    // vì app này nhỏ nên những việc xử lí data thì cta sẽ xử lí trực tiếp trên Mainthread
                    .allowMainThreadQueries()// run in Main Thread
                    //bởi vì trong android thì thường có 2 luồng thực thi nhiệm vụ chính
                    // 1. Là Mainthread(hay thường gọi là UI thread) để xử lí các tác vụ nhanh, gọn, nhẹ trên hình. còn các tác vụ tốn time
                    // như là download, load ảnh, xử lí data thì sẽ không nên xử lí ở UIthead để tránh việc block UI( đang sử dụng báo not responding
                    // hoặc là đơ màn hình, hoặc là chờ đợi lâu)
                    // 2. Là Worker Thread xử lí các tác vụ nặng lâu như mấy tác vụ vừa kể. thread sẽ được xử lí dưới background
                    // 2 luồng trên khi được tạo sẽ chạy song song với nhau.
                    .build();
        }
        return INSTANCE;
    }
}
