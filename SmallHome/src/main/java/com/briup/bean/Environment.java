package com.briup.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Environment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 环境名称
	 */
	private String name;
	/**
	 * 发送端id
	 */
	private String SrcId;
	/**
	 * 树莓派系统id
	 */
	private String DstId;
	/**
	 * 实验箱区域模块id(1-8)
	 */
	private String DevId;
	/**
	 * 模块上传感器地址 
	 */
	private String SensorAddress;
	/**
	 * 传感器个数
	 */
	private int Counter;
	/**
	 * 指令标号(3表示需要接受数据  16表示需要发送数据)
	 */
	private String Cmd;
	/**
	 * 数据16进制,需要转换成10进制(如果是16 前两个字节是    
         温度数据,中间两个字节是湿度数据。如果不是16 前两个字
         节就是对应的数据)
	 */
	private float Data;
	/**
	 * 状态标示(默认为1表示成功)
	 */
	private int Status;
	/**
	 * 采集时间(单位时秒)
	 */
	private Timestamp Gather_time;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrcId() {
		return SrcId;
	}
	public void setSrcId(String srcId) {
		SrcId = srcId;
	}
	public String getDstId() {
		return DstId;
	}
	public void setDstId(String dstId) {
		DstId = dstId;
	}
	public String getDevId() {
		return DevId;
	}
	public void setDevId(String devId) {
		DevId = devId;
	}
	public String getSensorAddress() {
		return SensorAddress;
	}
	public void setSensorAddress(String sensorAddress) {
		SensorAddress = sensorAddress;
	}
	public int getCounter() {
		return Counter;
	}
	public void setCounter(int counter) {
		Counter = counter;
	}
	public String getCmd() {
		return Cmd;
	}
	public void setCmd(String cmd) {
		Cmd = cmd;
	}
	public float getData() {
		return Data;
	}
	public void setData(float data) {
		Data = data;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public Timestamp getGather_time() {
		return Gather_time;
	}
	public void setGather_time(Timestamp gather_time) {
		Gather_time = gather_time;
	}
	@Override
	public String toString() {
		return "Environment [name=" + name + ", SrcId=" + SrcId + ", DstId="
				+ DstId + ", DevId=" + DevId + ", SensorAddress="
				+ SensorAddress + ", Counter=" + Counter + ", Cmd=" + Cmd
				+ ", Data=" + Data + ", Status=" + Status + ", Gather_time="
				+ Gather_time + "]";
	}

}
