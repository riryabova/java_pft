package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"),"config/config_inc.php", "config/config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
app.ftp().restore("config/config_inc.php.bak","config/config_inc.php");
    app.stop();
  }

//  public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
//    MantisConnectPortType mc = app.soap().getMantisConnect();
//    IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
//    String status = issue.getStatus().getName();
//    if (status.equals("resolved")){
//      return false;
//    }
//    return true;
//  }

}
