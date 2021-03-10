package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_AddUserView;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorComplementariosTests {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizaciones
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\34684\\Desktop\\SDI\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// PR01. Registro de profesores con datos válidos.
	@Test
	public void PR01() {
		PO_LoginView.login(driver, "Notas del usuario", "99999988F", "123456");
		driver.navigate().to("http://localhost:8080/user/add");
		PO_AddUserView.fillForm(driver, "12345678A", "nombre", "apellido", "password", "profesor");
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran");
	}
	
	// PR02. Registro de profesores con datos inválidos (nombre y categoría).
	@Test
	public void PR02() {
		PO_LoginView.login(driver, "Notas del usuario", "99999988F", "123456");
		driver.navigate().to("http://localhost:8080/user/add");
		//Nombre inválido
		PO_AddUserView.fillForm(driver, "12345678A", "n", "apellido", "password", "profesor");
		PO_View.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
	}
	
	// PR03. Verificar que solo los usuarios autorizados pueden dar de alta un profesor.
	@Test
	public void PR03A() {
		PO_LoginView.login(driver, "Notas del usuario", "99999999A", "123456");
		driver.navigate().to("http://localhost:8080/user/add");
		SeleniumUtils.textoNoPresentePagina(driver, "Añadir usuario");
	}
	
	@Test
	public void PR03B() {
		PO_LoginView.login(driver, "Notas del usuario", "99999993D", "123456");
		driver.navigate().to("http://localhost:8080/user/add");
		SeleniumUtils.textoNoPresentePagina(driver, "Añadir usuario");
	}
}
