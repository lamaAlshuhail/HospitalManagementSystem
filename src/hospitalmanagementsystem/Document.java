
package hospitalmanagementsystem;

/**
 *
 * @author lamashuhail
 */
import java.util.Date;
public class Document {
     private String documentId;
    private Date creationDate;
    private Date lastModified;
    private Procedure procedure;

    public Document(String documentId, Date creationDate) {
        this.documentId = documentId;
        this.creationDate = creationDate;
        this.lastModified = creationDate; //  lastModified is set at creation
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
