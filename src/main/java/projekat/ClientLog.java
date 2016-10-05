/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author marko
 */
public class ClientLog {
    
    private Logger logger;
    public ClientLog(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }
    
    public void debug(String message){
        logger.debug(message);
    }
    
    public void info (String message) {
        logger.info(message);
    }
    
}
