package com.briup.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.util.ILog;

public class IGather implements Gather{

	List<Environment> list = new ArrayList<Environment>();
	//采集文件
	String gatherPath = null;
	//备份文件
	String copyPath = null;
	//日志
	private static ILog log = new ILog();
	public void init(Properties properties) throws Exception {
		gatherPath = properties.getProperty("path");
		copyPath = properties.getProperty("backup");
	}

	public Collection<Environment> gather() throws Exception {
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		String line = null;
		int count1 = 0;//温度和湿度条数
		int count2 = 0;//光照强度条数
		int count3 = 0;//二氧化度条数
		File copyfile = new File(copyPath);
		File gatherfile = new File(gatherPath);
		fr = new FileReader(gatherfile);
		br = new BufferedReader(fr);
		if(copyfile.length()-14==gatherfile.length()) {
			br.skip(0);
			fw = new FileWriter(copyfile);
		} else {
			br.skip(copyfile.length());
			fw = new FileWriter(copyfile,true);
		}
		bw = new BufferedWriter(fw);
		try {
			System.out.println(copyfile.length()+" = = "+gatherfile.length());
			while((line = br.readLine())!= null) {
				bw.write(line);
				bw.newLine();
				bw.flush();
				String[] split = line.split("\\|");
				Environment en = new Environment();					
				Environment en0 = new Environment();	
				en.setSrcId(split[0]);
				en.setDstId(split[1]);
				en.setDevId(split[2]);
				en.setSensorAddress(split[3]);
				en.setCounter(Integer.parseInt(split[4]));
				en.setCmd(split[5]);
				en.setStatus(Integer.parseInt(split[7]));
				Long time = new Long(split[8]);
				Timestamp timestamp = new Timestamp(time);
				en.setGather_time(timestamp);
				if(Integer.parseInt(split[3])==16) {
					en0.setSrcId(split[0]);
					en0.setDstId(split[1]);
					en0.setDevId(split[2]);
					en0.setSensorAddress(split[3]);
					en0.setCounter(Integer.parseInt(split[4]));
					en0.setCmd(split[5]);
					en0.setStatus(Integer.parseInt(split[7]));
					en0.setGather_time(timestamp);
					//含有温度
					count1++;
					int value = Integer.valueOf(split[6].substring(0, 4),16);
					en.setName("温度");
					float v = (float)(value*0.00268127-46.85);
					DecimalFormat df = new DecimalFormat("#.0000");//四位小数点
					en.setData(Float.parseFloat(df.format(v)));	
					//含有湿度
					int value0 = Integer.valueOf(split[6].substring(4, 8),16);
					float x = (float)(value0*0.00190735-6);
					en0.setName("湿度");
					en0.setData(Float.parseFloat(df.format(x)));
					list.add(en0);		
				} else if (Integer.parseInt(split[3])==256) {
					count2++;
					en.setName("光照强度");
					en.setData(Integer.valueOf(split[6].substring(0, 4),16));
				} else if (Integer.parseInt(split[3])==1280) {
					count3++;
					en.setName("二氧化碳");
					en.setData(Integer.valueOf(split[6].substring(0, 4),16));
				}
				list.add(en);
			}
			log.info("温度和湿度条数:"+count1);
			log.info("光照强度条数:"+count2);
			log.info("二氧化度条数:"+count3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.flush();
			bw.close();
			fw.close();
			fr.close();
			br.close();
			System.out.println("jieshu");
			log.info("数据采集结束");
			
		}
		
		return list;
	}

}
