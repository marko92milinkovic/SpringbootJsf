/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.mb;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Marko
 */
@ManagedBean
public class ImagesView {

    private List<String> images;

    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            images.add("https://www.google.rs/images/srpr/logo11w.png");
        }
    }

    public List<String> getImages() {
        return images;
    }

}
