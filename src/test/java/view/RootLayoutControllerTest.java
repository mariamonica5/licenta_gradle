package test.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import view.RootLayoutController;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

/**
 * Created by Moni on 6/2/2017.
 */
public class RootLayoutControllerTest extends GuiTest{

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("../../view/RootLayout.fxml"));
            return parent;
        } catch (IOException ex) {
            // TODO ...
        }
        return parent;
    }

    @Test
    public void setMainApplic() throws Exception {

    }

    @Test
    public void testMenuItems() throws Exception {
        click("File");
        sleep(2,SECONDS);
        click("Discipline");
        sleep(2, SECONDS);
        click("File");
        sleep(2, SECONDS);
        click("Students");
        sleep(2, SECONDS);
        click("Search");
        sleep(2, SECONDS);
        click("Students ...");
        sleep(2, SECONDS);
        click("Help");
        sleep(2, SECONDS);
        click("About");
        sleep(2, SECONDS);

    }


}