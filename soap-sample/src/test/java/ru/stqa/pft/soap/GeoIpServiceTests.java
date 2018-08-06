package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {


  @Test
  public void testMyIp() {
    //geoIp нет в новых wsdl
//    http://wsgeoip.lavasoft.com/ipservice.asmx?
    //http://wsgeoip.lavasoft.com/ipservice.asmx?WSDL
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("176.226.145.148");
    System.out.println(ipLocation);
//    Assert.assertEquals(ipLocation.c);
  }

  @Test
  public void testInvalidIp() {
    //geoIp нет в новых wsdl
//    http://wsgeoip.lavasoft.com/ipservice.asmx?
    //http://wsgeoip.lavasoft.com/ipservice.asmx?WSDL
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("176.226.145.xxx");
    System.out.println(ipLocation);

  }

}
