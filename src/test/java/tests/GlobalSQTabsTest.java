package tests;

import base.BaseClassGlobalsQa;
import pages.GlobalSQTabs;
import util.SoftAssertUtil;

public class GlobalSQTabsTest extends BaseClassGlobalsQa {

   // @Test
    public void verifySimpleAccordionSection1(){
        GlobalSQTabs globalSQTabs = new GlobalSQTabs(driver,Integer.parseInt(configReader.getProperty("timeout")));
        globalSQTabs.openTabPage();
        globalSQTabs.clickSimpleAccordionTab();
        globalSQTabs.switchToIframe();
        globalSQTabs.clickSection1();
        String content = globalSQTabs.getSectionText();
        System.out.println("Content of section 1 : "+content);
        globalSQTabs.switchBackToMainPage();
        SoftAssertUtil.assertTrue(content.contains("Mauris mauris ante"), "Should match the expected section 1");
        SoftAssertUtil.assertAll();
    }

}
