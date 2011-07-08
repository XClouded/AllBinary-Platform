/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.content;

import java.util.Hashtable;

import javax.microedition.lcdui.Command;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.resource.ResourceUtil;
import android.content.Intent;
import android.net.Uri;

public class CommandUriAction
{
    private static final CommandUriAction instance = new CommandUriAction();
 
    public static CommandUriAction getInstance()
    {
        return instance;
    }
    
    private Hashtable hashtable = new Hashtable();

    public void add(Command command, String url)
    {
        hashtable.put(command, url);
    }

    public void process(Command command)
    {
        try
        {
            /*
             * Intent intent = new Intent();
             * 
             * ComponentName comp = new ComponentName(
             * "com.google.android.browser",
             * "com.google.android.browser.BrowserActivity");
             * intent.setComponent(comp);
             * intent.setAction("android.intent.action.VIEW");
             * intent.addCategory("android.intent.category.BROWSABLE"); Uri uri =
             * Uri.parse(BrowserLoader.url); intent.setData(uri);
             */

            Intent intent = getIntent(command);
            ResourceUtil.getInstance().getContext().startActivity(intent);

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().PROCESS, e));
        }
    }
    
    public Intent getIntent(Command command)
    {
        String url = (String) hashtable.get(command);
        
        Uri uri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        return intent;
    }
}
