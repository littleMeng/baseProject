package com.genlot.lottery.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Date 2018/3/21
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 日志工具
 */

public class LogTool {
    private static final String _tag = "GenlotLogger";
    private static final int _length = 66;
    private static final int LEVEL_INFO = 1;
    private static final int LEVEL_WARN = 2;
    private static final int LEVEL_ERROR = 3;

    private static StackTraceElement getStackTraceElement()
    {
        return Thread.currentThread().getStackTrace()[4];
    }

    private static String logTime()
    {
        SimpleDateFormat simFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simFormat.format(date);
    }

    private static ArrayList<Integer> getSplitIndex(byte[] data, int splitSize)
    {
        ArrayList<Integer> _splitIndex = new ArrayList<>();
        int size = data.length;
        int index = 0;
        int offset = 0;
        while(index < size)
        {
            if((data[index] >= 0x20) && (data[index] <= 0x7F))
            {
                index++;
                offset++;
            }
            else
            {
                if((data[index] & 0xF0) == 0x80)
                {
                    index++;
                    offset++;
                }
                else if((data[index] & 0xF0) == 0xC0)
                {
                    index+=2;
                    offset+=2;
                }
                else if((data[index] & 0xF0) == 0xE0)
                {
                    index+=3;
                    offset+=3;
                }
                else if((data[index] & 0xF0) == 0xF0)
                {
                    index+=4;
                    offset+=4;
                }
                else
                {
                    index++;
                    offset++;
                }
            }
            if(offset >= splitSize)
            {
                offset = 0;
                _splitIndex.add(index);
            }
        }
        return _splitIndex;
    }

    private static void logLevel(int level, String tag, String msg)
    {
        if(level == LEVEL_INFO)
        {
            Log.i(tag, msg);
        }
        else if(level == LEVEL_WARN)
        {
            Log.w(tag, msg);
        }
        else if(level == LEVEL_ERROR)
        {
            Log.e(tag, msg);
        }
        else
        {
            Log.i(tag, msg);
        }
    }

    private static void formatLog(int level, String tag, String msg)
    {
        StringBuffer buffer = new StringBuffer();
        //第一行
        buffer.append("┌");
        for(int i = 0; i < _length; i++)
        {
            buffer.append("─");
        }
        buffer.append("┐");
        logLevel(level, _tag, buffer.toString());
        //打印第二行
        buffer.setLength(0);
        buffer.append("│");
        buffer.append(logTime());
        buffer.append(" (");
        buffer.append(tag);
        buffer.append(")  ");
        buffer.append("Tid:");
        buffer.append(android.os.Process.myTid());
        int length = buffer.toString().length();
        if(length < _length + 1)
        {
            for(int i = 0; i < _length + 1 - length; i++)
            {
                buffer.append(" ");
            }
        }
        logLevel(level, _tag, buffer.toString());
        //打印第三行
        buffer.setLength(0);
        buffer.append("├");
        for(int i = 0; i < _length; i++)
        {
            buffer.append("─");
        }
        buffer.append("┤");
        logLevel(level, _tag, buffer.toString());
        //打印第四行-日志内容
        buffer.setLength(0);
        byte[] data = msg.getBytes();
        length = data.length;
        if(length <= _length)
        {
            buffer.append("│");
            buffer.append(msg);
            logLevel(level, _tag, buffer.toString());
        }
        else
        {
            int size = 0;
            ArrayList<Integer> splitIndex = getSplitIndex(data, _length - 6);
            for(int i = 0; i < splitIndex.size(); i++)
            {
                buffer.setLength(0);
                buffer.append("│");
                if(i == 0)
                {
                    buffer.append(new String(data, 0, splitIndex.get(i)));
                }
                else
                {
                    size = splitIndex.get(i) - splitIndex.get(i - 1);
                    buffer.append(new String(data, splitIndex.get(i - 1), size));
                }
                logLevel(level, _tag, buffer.toString());
            }
            int index = splitIndex.get(splitIndex.size() - 1);
            if(index < length)
            {
                buffer.setLength(0);
                buffer.append("│");
                buffer.append(new String(data, index, data.length - index));
                logLevel(level, _tag, buffer.toString());
            }
        }
        buffer.setLength(0);
        buffer.append("└");
        for(int i = 0; i < _length; i++)
        {
            buffer.append("─");
        }
        buffer.append("┘");
        logLevel(level, _tag, buffer.toString());
    }

    public static String tag()
    {
        String name = getStackTraceElement().getFileName();
        int lineNo = getStackTraceElement().getLineNumber();
        return name + ":" + lineNo;
    }

    public static void logI(String tag, String msg)
    {
        formatLog(LEVEL_INFO, tag, msg);
    }

    public static void logW(String tag, String msg)
    {
        formatLog(LEVEL_WARN, tag, msg);
    }

    public static void logE(String tag, String msg)
    {
        formatLog(LEVEL_ERROR, tag, msg);
    }
}
