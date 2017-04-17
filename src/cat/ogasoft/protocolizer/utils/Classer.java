package cat.ogasoft.protocolizer.utils;

import cat.ogasoft.protocolizer.exceptions.ClasserException;
import com.google.common.base.Strings;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Oscar Galera i Alfaro
 * @date   Mar 19, 2017 [5:28:01 PM]
 * 
 * @brief Class for create a java class.
 */
public class Classer {
    private final StringBuilder header;
    private final String packet; //<The packet of the class.
    private final String name; //<The name of the class.
    private final Set<Method> methods; //<The methods of the class.
    private final Set<String> packages; //<The imports of the class.

    public Classer(String packet, String name) {
        this.packet = packet;
        this.name = name;
        this.methods = new HashSet<>();
        this.packages = new HashSet<>();
        this.header = new StringBuilder();
        this.header.append(" * This class has been automatically created by the Classer on ").append(Utils.getDateTime());
    }
    
    public void addHeaderNewLine(){
        this.header.append("\n *");
    }
    
    public void addHeaderLine(String header){
        this.header.append("\n").append(" * ").append(header);
    }
    
    public void write(File fitxer) throws IOException{
        try(PrintWriter writer = new PrintWriter(fitxer)){
            writer.println("/**\n"+header.toString()+"\n */\n");
            writer.println("package "+packet+";");
            for(String p: packages){
                writer.println("import "+p+";");
            }
            writer.println();
            writer.println("public class "+name+"{");
            writer.println("}");
            writer.flush();
        }
    }
    
    public static class Method{
        private final String name; //<The name of the method.

        public Method(String nom) throws ClasserException{
            if(nom == null){
                throw new ClasserException("The name of a method can not be null");
            }
            this.name = nom;
        }
        
        @Override
        public boolean equals(Object obj) {
            if(obj == null) return false;
            if(!(obj instanceof Method)) return false;
            return ((Method)obj).name.equals(name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }

        @Override
        public String toString() {
            return "Method{" + "name=" + name + '}';
        }
        
    }
    
    public static class Field{
        
    }
}
