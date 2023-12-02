/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

import java.util.Date;

/**
 *
 * @author lamashuhail
 */
public class BillingStatement  extends Document {
        private double totalAmount;
        private double paidAmount;
        private Date dueDate;
        private Procedure procedure;

    public BillingStatement(String documentId, Date creationDate, Procedure procedure, double totalAmount, double paidAmount, Date dueDate) {
        super(documentId);
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.dueDate = dueDate;
        this.procedure = procedure;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

   public Procedure getProcedure() {
        return procedure;
}
}
