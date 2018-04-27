package com.genlot.lottery.widget;

import android.content.Context;

import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

/**
 * Date 2018/4/17
 * Author littlemeng
 * Email yikai.shao@genlot.com
 * Describe 城市选择器
 */

public class CityPickerHelper extends CityPickerView {
    public CityPickerHelper() {
        super();
    }

    public void init(Context context) {
        super.init(context);
        CityConfig cityConfig = new CityConfig.Builder()
                .setCityWheelType(CityConfig.WheelType.PRO_CITY)
                .visibleItemsCount(7)
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .build();
        setConfig(cityConfig);
    }

    public void show() {
        showCityPicker();
    }
}
