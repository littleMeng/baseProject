package com.genlot.lottery.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Date 2018/3/29
 * Author littlemeng
 * Email yikaishao@163.com
 * Describe 公告
 */

@Entity
public class Notice {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String content;
    private String time;
    private boolean isShow;
    @Generated(hash = 1151362345)
    public Notice(Long id, String title, String content, String time,
            boolean isShow) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.isShow = isShow;
    }
    @Generated(hash = 1880392847)
    public Notice() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public boolean getIsShow() {
        return this.isShow;
    }
    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }
}
