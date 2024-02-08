package com.imooc.imooc_expandablelistview.biz;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.imooc.imooc_expandablelistview.bean.Chapter;
import com.imooc.imooc_expandablelistview.bean.ChapterItem;
import com.imooc.imooc_expandablelistview.dao.ChapterDao;
import com.imooc.imooc_expandablelistview.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class ChapterBiz {
    private static final String TAG = "ChapterBiz";
    ChapterDao mChapterDao = new ChapterDao();
    public void loadDatas(Context context, CallBack callback,boolean useCache){
        AsyncTask<Boolean,Void, List<Chapter>> asyncTask=new AsyncTask<Boolean, Void, List<Chapter>>() {
            private Exception ex;
            @Override
            protected List<Chapter> doInBackground(Boolean... booleans) {
                boolean isUseCache = booleans[0];
                List<Chapter> chapterList = new ArrayList<>();
                try {
                    if(isUseCache){
                        //load datas from db
                        List<Chapter> chapterListFromDb = mChapterDao.loadFromDb(context);
                        Log.d(TAG, "doInBackground: chapterListFromDb "+chapterListFromDb);
                        chapterList.addAll(chapterListFromDb);
                    }

                    if(chapterList.isEmpty()){
                        //load from net
                        List<Chapter> chapterListFromNet = loadFromNet(context);
                        chapterList.addAll(chapterListFromNet);
                        //cache to db
                        mChapterDao.insertToDb(context,chapterListFromNet);
                    }
                } catch (Exception ex) {
                   ex.printStackTrace();
                   this.ex=ex;
                }

                return chapterList;
            }



            @Override
            protected void onPostExecute(List<Chapter> chapters) {
                if(ex!=null){
                    callback.onFailed(ex);
                    return;
                }
                callback.onSuccess(chapters);
            }
        };
        asyncTask.execute(useCache);
    }

    private List<Chapter> loadFromNet(Context context) {
        List<Chapter> chapterList = new ArrayList<>();
        String url = "https://www.imooc.com/api/expandablelistview";
        //1. request
        String content = HttpUtils.doGet(url);
        Log.d(TAG, "loadFromNet: Content "+content);
        //2. content->List<Chapter>
        if(content!=null){

             chapterList = parseContent(content);
            Log.d(TAG, "loadFromNet: parse finish chapterList= "+chapterList);
        }
        return chapterList;
    }

    private List<Chapter> parseContent(String content) {
        List<Chapter> chapterList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(content);
            int errorCode=root.optInt("errorCode");
            if(errorCode==0){
                JSONArray dataJsonArray = root.optJSONArray("data");
                for (int i = 0; i < dataJsonArray.length(); i++) {
                    //chapter
                    JSONObject chapterJsonObj = dataJsonArray.getJSONObject(i);
                    int id = chapterJsonObj.optInt("id");
                    String name = chapterJsonObj.optString("name");
                    Chapter chapter = new Chapter(id, name);
                    chapterList.add(chapter);

                    //chapter items
                    JSONArray childrenJsonArray = chapterJsonObj.optJSONArray("children");
                    for (int j = 0; j < childrenJsonArray.length(); j++) {
                        JSONObject itemjsonObject = childrenJsonArray.getJSONObject(j);
                        int id1 = itemjsonObject.optInt("id");
                        String name1 = itemjsonObject.optString("name");
                        ChapterItem chapterItem = new ChapterItem(id1, name1);
                        chapter.addChild(chapterItem);

                    }

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
return chapterList;
    }

    public static interface CallBack{
        void onSuccess(List<Chapter> chapterList);

        void onFailed(Exception exception);
    }
}
