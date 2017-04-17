/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.protocolizer.processor;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

/**
 *
 * @author Oscar Galera i Alfaro
 * @date Mar 21, 2017 [3:43:43 PM]
 */
public class Prova {

    public static void main(String... args) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("cat/ogasoft/protocolizer/templates/prova2.twig");
        JtwigModel model = JtwigModel.newModel().with("var", "oscar");
        template.render(model, System.out);
    }
}
