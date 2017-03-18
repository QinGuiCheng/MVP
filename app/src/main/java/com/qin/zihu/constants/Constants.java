package com.qin.zihu.constants;


import com.minzea.base.utils.SizeUtils;
import com.qin.zihu.app.ZiHuApp;

/**
 * Created by Qin on 2017/2/24.
 */

public class Constants {

    public static final int HTTP_STATUS_SUCCESS = 1;
    public static final int HTTP_STATUS_FAILED = 0;

    public static final String HTTP_CODE_UNLOGIN = "10001";

    public static final int ITEM_SPACE_10 = SizeUtils.dp2px(ZiHuApp.getInstance(), 10);
    public static final int ITEM_SPACE_12 = SizeUtils.dp2px(ZiHuApp.getInstance(), 12);
    public static final int ITEM_SPACE_15 = SizeUtils.dp2px(ZiHuApp.getInstance(), 15);

    private static final String HOST_URL_PRODUCTION = "https://www.baidu.com == 自己的生产环境URL"; //生产环境
    private static final String HOST_URL_TEST = "https://www.baidu.com == 自己的测试环境URL"; //测试环境
    public static final String HOST_URL = Config.DEBUG?HOST_URL_TEST:HOST_URL_PRODUCTION; // 是debug，则测试环境

}
