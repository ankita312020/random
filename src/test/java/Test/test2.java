package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test2 {
	
	@Test
	public void execution() {
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	

    // Navigate to the Udemy Business Plans page
    driver.get("https://business.udemy.com/plans/?utm_type=mx&ref=ufb_header&user_type=visitor");

    // Locate the table element containing plan comparison
    WebElement comparisonTable = driver.findElement(By.id("compare-features"));

    // Locate the rows for 'Enterprise' and 'Team' plans
    WebElement enterpriseRow = comparisonTable.findElement(By.xpath(".//tr[./td[contains(text(),'Enterprise')]]"));
    WebElement teamRow = comparisonTable.findElement(By.xpath(".//tr[./td[contains(text(),'Team')]]"));

    // Check 'Course assignment with custom messaging'
    checkFeatureAvailability(enterpriseRow, "Course assignment with custom messaging", true);
    checkFeatureAvailability(teamRow, "Course assignment with custom messaging", false);

    // Check 'Custom user groups'
    checkFeatureAvailability(enterpriseRow, "Custom user groups", true);
    checkFeatureAvailability(teamRow, "Custom user groups", false);

    // Check 'User adoption'//
    checkFeatureAvailability(enterpriseRow, "User adoption", true);
    checkFeatureAvailability(teamRow, "User adoption", true);

    // Check '24/7 customer support'
    checkFeatureAvailability(enterpriseRow, "24/7 customer support", true);
    checkFeatureAvailability(teamRow, "24/7 customer support", true);

    // Close the WebDriver
    driver.quit();
}

private static void checkFeatureAvailability(WebElement row, String featureName, boolean shouldBeAvailable) {
    WebElement featureCell = row.findElement(By.xpath(".//td[contains(text(),'" + featureName + "')]"));
    boolean isFeatureAvailable = !featureCell.findElements(By.xpath(".//span[text()='✓']")).isEmpty();

    if (isFeatureAvailable == shouldBeAvailable) {
        System.out.println(featureName + " is " + (shouldBeAvailable ? "available." : "not available."));
    } else {
        System.out.println(featureName + " is " + (shouldBeAvailable ? "not available." : "available."));
    }
}
	
	}

