import crawler.WebCrawler;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.SwingTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.swing.SwingComponent;

import javax.swing.*;

public class CrawlerTest extends SwingTest {

    public CrawlerTest() {
        super(new WebCrawler());
    }

    @SwingComponent(name = "TextArea")
    JTextComponentFixture textArea;

    @DynamicTest(order = 1)
    CheckResult testWindowConfiguration() {
        if (frame.getDefaultCloseOperation() != JFrame.EXIT_ON_CLOSE) {
            return CheckResult
                .wrong("The default close operation for the window should be EXIT_ON_CLOSE");
        }

        return CheckResult.correct();
    }

    @DynamicTest(order = 2)
    CheckResult testTextArea() {
        requireVisible(textArea);
        requireDisabled(textArea);

        if (!textArea.text().equals("HTML code?")) {
            return CheckResult.wrong("TextArea should contain the text \"HTML code?\"");
        }

        return CheckResult.correct();
    }
}
