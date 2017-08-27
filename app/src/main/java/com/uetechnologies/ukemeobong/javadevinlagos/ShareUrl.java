package com.uetechnologies.ukemeobong.javadevinlagos;

/**
 * Created by Ukemeobong on 8/13/2017.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
public class ShareUrl {

    public static void share(String message, Context context)
    {
        String text = message;
        List<Intent> targetShareIntents=new ArrayList<Intent>();
        Intent shareIntent=new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        List<ResolveInfo> resInfor = context.getPackageManager().queryIntentActivities(shareIntent, 0);

        if(!resInfor.isEmpty()){
            for(ResolveInfo resInfo : resInfor){
                String packageName=resInfo.activityInfo.packageName;

                Intent intent=new Intent();
                intent.setComponent(new ComponentName(packageName, resInfo.activityInfo.name));
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/*");
                intent.putExtra(Intent.EXTRA_TEXT, text);
                intent.setPackage(packageName);
                targetShareIntents.add(intent);

            }

            if(!targetShareIntents.isEmpty()){
                Intent intent=Intent.createChooser(targetShareIntents.remove(0), "Share with Friends");
                intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetShareIntents.toArray(new Parcelable[]{}));
                context.startActivity(intent);
            }else{
            }
        }
    }
}
