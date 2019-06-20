package com.pzg.code.commons.excel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeConvert implements ExportConvert {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String handler(Object val) {
        try {
            if (val == null) {
                return "";
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                Date d = sdf.parse(val.toString());
                SimpleDateFormat simformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return simformat.format(d);
            }
        } catch (Exception e) {
            log.error("时间转换异常", e);
            return "";
        }
    }

}
