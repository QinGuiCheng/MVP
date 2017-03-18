package com.qin.zihu.data.model;

/**
 * Created by Qin on 2017/2/24.
 */

public class User {

    public String hxUid;
    public String pushAlias;
    public String uid;
    public String uname;
    public String realname;
    public String phone;
    public String isCategory;

    public User() {

    }

    public User(String hxUid, String pushAlias, String uid, String uname) {
        this.hxUid = hxUid;
        this.pushAlias = pushAlias;
        this.uid = uid;
        this.uname = uname;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof User) {
            User temp = (User) object;
            if (temp.uid.equals(this.uid)) {
                return true;
            }
        }
        return false;
    }
}
