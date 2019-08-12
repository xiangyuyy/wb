package com.example.demo.utils;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration.JerseyWebApplicationInitializer;

import com.example.demo.DemoApplication;

import lombok.experimental.var;
/*����ʱ������ΨһID*/
public class GenerateSequenceUtil {
 
    /** The FieldPosition. */
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
 
    /** This Format for format the data to special format. */
    private final static Format dateFormat = new SimpleDateFormat("MMddHHmmssS");
 
    /** This Format for format the number to special format. */
    private final static NumberFormat numberFormat = new DecimalFormat("0000");
 
    /** This int is the sequence number ,the default value is 0. */
    private static int seq = 0;
 
    private static final int MAX = 9;
 
    /**
     * ʱ���ʽ��������
     * @return String
     */
    public static synchronized String generateSequenceNo() {
 
        Calendar rightNow = Calendar.getInstance();
        StringBuffer sb = new StringBuffer();
 
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
 
        numberFormat.format(seq, sb, HELPER_POSITION);
 
        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        } 
        return sb.toString();
    }
    
    /**
     * ʱ���ʽ��������201901241432431
     * @return String
     */
    public static synchronized String generateSequenceNoByDate() {
		String str = DateUtil.getDateNow("yyyyMMddHHmmss");
		str += String.valueOf(seq);
        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        } 
        return str;
    }
/*    private static final Logger logger = LoggerFactory.getLogger(GenerateSequenceUtil.class);

	public static void main(String[] args) {
		logger.info(generateSequenceNoByDate());
		logger.info(generateSequenceNoByDate());
		logger.info(generateSequenceNo());
	}*/
}

