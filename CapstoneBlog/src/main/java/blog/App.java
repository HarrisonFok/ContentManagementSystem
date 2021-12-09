/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Joshua Martel
 */
@EnableAutoConfiguration
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
