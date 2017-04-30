/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.examples;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 30, 2017 [3:11:23 PM]
 *
 * @brief DESCRIPTION
 */
//@ProtoFileV2.File(pJavaName = "prova10", generateSource = true)
//@ProtoFileV2.Dumpper
public class Test10 {
    @ProtoFileV2.File.Message
    public static class Prova{
        @ProtoFileV2.File.Message.Field
        private int c;

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return "Prova{" + "c=" + c + '}';
        }
    }
}
