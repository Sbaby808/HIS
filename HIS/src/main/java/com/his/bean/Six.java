package com.his.bean;

/**  
* @ClassName: Six  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author TRC
* @date 2019年8月26日  上午9:51:11
*    
*/
public class Six {
	private White white;
	private Night night;
	public Six() {}
	public Six(White white, Night night) {
		super();
		this.white = white;
		this.night = night;
	}
	public White getWhite() {
		return white;
	}
	public void setWhite(White white) {
		this.white = white;
	}
	public Night getNight() {
		return night;
	}
	public void setNight(Night night) {
		this.night = night;
	}
}
