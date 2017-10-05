package br.com.leonardoferreira.cepapi;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author leonardo.ferreira
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 10/5/17 6:44 PM
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = "pretty", features = "src/test/resources/features")
public class CucumberTest {
}
