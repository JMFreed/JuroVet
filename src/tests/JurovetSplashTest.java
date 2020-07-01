package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.JurovetSplash;

class JurovetSplashTest {
	
	JurovetSplash splash;

	@BeforeEach
	void setUp() throws Exception {
		splash = JurovetSplash.getInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		splash = null;
	}

	@Test
	void testSplashProperties() {
		if (splash.isUndecorated() &&
				splash.getWidth() == 587 &&
				splash.getHeight() == 352) {
			return;
		}
		System.out.println("Height: " + splash.getHeight());
		System.out.println("Width: " + splash.getWidth());
		fail();
	}

}
