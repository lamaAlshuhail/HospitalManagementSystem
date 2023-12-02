
package hospitalmanagementsystem;

/**
 *
 * @author lamashuhail
 */
import java.util.Date;
public class Document {
     private String documentId;
    private Procedure procedure;

 
    public Document(String documentId) {
        this.documentId = documentId;
 //  lastModified is set at creation
    }
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }


}
