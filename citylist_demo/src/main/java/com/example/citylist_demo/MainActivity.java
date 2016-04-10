package com.example.citylist_demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private CityListBean cityListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url="http://api.m.mtime.cn/Showtime/HotCitiesByCinema.api";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "e=" + e.toString());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "success");
                        cityListBean = new Gson().fromJson(response, CityListBean.class);
                        List<CityListBean.PEntity> p = cityListBean.getP();
                        Map<String, List<CityListBean.PEntity>> sortCityListMap = CityListSort.putListData(p);

                        List<CityListBean.PEntity> a = sortCityListMap.get("B");
                        for (int i = 0; i < a.size(); i++) {
                            Log.e(TAG, "a="+a.get(i).getN());
                        }
                    }
                });

//
//        Map<String, List<CityListBean.PEntity>> cityListMap = new HashMap<>();
//        List<CityListBean.PEntity> itemList = new ArrayList<>();

//        for (int i = 0; i < p.size(); i++) {
//            //当前字母
//            String currentCharactor = p.get(i).getPinyinShort().substring(0, 1);
//
//            if (i == 0) {
//                itemList.add(p.get(0));//添加第一个
//                cityListMap.put(currentCharactor, itemList);
//            } else {
//                String preCharactor = p.get(i - 1).getPinyinShort().substring(0, 1);
//                if (currentCharactor.equals(preCharactor)) {//表示同一个字母
//                    itemList.add(p.get(i));
//                } else {//此时到下一个字母
//                    //将所有的同一字母的集合添加到map中，以首字母为key
//                    cityListMap.put(preCharactor, itemList);
//                    itemList.clear();//清空集合
//                    itemList.add(p.get(i));
//                }
//            }
//
//        }
    }
}
