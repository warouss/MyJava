package com.warouss.java;
import java.io.Closeable;
import java.io.IOException;


public class EnumTest {
	
	public enum Day {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
	}
	
	public enum ProductType {
	    VOD(0), PACK(1), SVOD(2), BONUS(3);
	    
	    private int code;
	    
	    private ProductType(int code) {
	    	this.code=code;
	    }
	    
	    public int getCode() {
	    	return code;
	    }
	}
	
	public enum Device {
		EVOLUTION(-101, "SFR STB User Agent") {
			@Override
			public boolean isDisplayable(Integer productId) {
				return true;
			}
		},
		NETGEM_V5(-103, "Netgem v5") {
			@Override
			public boolean isDisplayable(Integer productId) {
				return productId>=1000;
			}
		},		
		NETGEM_V4(-102, "Netgem v4") {
			@Override
			public boolean isDisplayable(Integer productId) {
				return productId<1000;
			}
		};
		
		private int id;
		private String userAgent=null;
		
		private Device(int deviceId, String ua) {
			id = deviceId;
			userAgent = ua;
		}
		
		public abstract boolean isDisplayable(Integer productId);

		public int getId() {
			return id;
		}

		public String getUserAgent() {
			return userAgent;
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Test Enum.name() ");
		System.out.println("THURSTDAY.name() = "+Day.THURSDAY.name());
		System.out.println("ProductType.PACK.name() = "+ProductType.PACK.name());
		
		Day currentDay = Day.THURSDAY;
		for (Day d: Day.values()) {
			String st = "Day "+d;
			if (d.equals(currentDay)) {
				st = st+" is current day";
			}
			System.out.println(st);
			
		}
		
		System.out.println("List ProductType");
		ProductType pt = ProductType.VOD;
		ProductType pt2 = ProductType.valueOf("SVOD");
		for (ProductType t : ProductType.values()) {
			System.out.println(t.getCode()+ " : "+t);
			if (t.equals(pt)) {
				System.out.println(t+" == "+pt);
			}
			
			if (t.equals(pt2)) {
				System.out.println(t+" == valueOf('SVOD')");
			}
		}
		
		System.out.println("Test Device");
		for (Device device : Device.values()) {
			System.out.println(String.format("%s : id=%s userAgent=%s isDisplayable(1500)=%s", device.name(), device.getId(), device.getUserAgent(), device.isDisplayable(1500)));
		}

	}

}
