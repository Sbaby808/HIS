package com.his.bean;

/**  
* @ClassName: One  
* @Description: TODO(周一)  
* @author TRC
* @date 2019年8月26日  上午9:48:16
*    
*/
public class One {
	private White white;
	private Night night;
	public One() {}
	public One(White white, Night night) {
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