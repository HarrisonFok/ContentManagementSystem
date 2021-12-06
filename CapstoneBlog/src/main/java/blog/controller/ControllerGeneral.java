/*
*   Joshua Martel
*   jophmartel@gmail.com
*   
*
*/

package blog.controller;

import blog.servicelayer.ServiceLayerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joshua Martel
 */
@RestController
@RequestMapping("/capstone/general")
@ComponentScan(basePackageClasses = ServiceLayerImpl.class)
public class ControllerGeneral {
    
    @Autowired
    private ServiceLayerImpl service;

    public ControllerGeneral(ServiceLayerImpl service) {
        this.service = service;
    }
    
    
}
