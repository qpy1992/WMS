package com.example.administrator.wms.util;

import android.content.Context;
import android.util.Log;

import com.example.administrator.wms.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/6 0006.
 */

public class UserUtil {

    private static String TAG = "UserUtil";

    /* 保存用户登录信息列表 */
    public static void saveUserList(Context context, ArrayList<User> users)
            throws Exception {
		/* 保存 */
        Log.i(TAG, "正在保存");
        Writer writer = null;
        OutputStream out = null;
        JSONArray array = new JSONArray();
        for (User user : users) {
            array.put(user.toJSON());
        }
        try {
            out = context.openFileOutput(Consts.FILENAME, Context.MODE_PRIVATE); // 覆盖
            writer = new OutputStreamWriter(out);
            Log.i(TAG, "json的值:" + array.toString());
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }

    }

    /* 获取用户登录信息列表 */
    public static ArrayList<User> getUserList(Context context) {
		/* 加载 */
        FileInputStream in = null;
        ArrayList<User> users = new ArrayList<User>();
        try {

            in = context.openFileInput(Consts.FILENAME);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            JSONArray jsonArray = new JSONArray();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            Log.i(TAG, jsonString.toString());
            jsonArray = (JSONArray) new JSONTokener(jsonString.toString())
                    .nextValue(); // 把字符串转换成JSONArray对象
            for (int i = 0; i < jsonArray.length(); i++) {
                User user = new User(jsonArray.getJSONObject(i));
                users.add(user);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
