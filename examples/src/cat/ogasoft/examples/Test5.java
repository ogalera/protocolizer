/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.examples;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 29, 2017 [3:13:24 PM]
 *
 * @brief DESCRIPTION
 */
@ProtoFileV2.File(pJavaName = "prova7", generateSource = true)
@ProtoFileV2.Dumpper
@ProtoFileV2.File.Import(importClass = Test4.class)
public class Test5 {

    @ProtoFileV2.File.Message
    public static class C1 {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Test4.M1 aaa;

        public Test4.M1 getAaa() {
            return aaa;
        }

        public void setAaa(Test4.M1 aaa) {
            this.aaa = aaa;
        }
        
        @ProtoFileV2.File.Message.Field
        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

    }
}
