/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Copyright (C) 2008 Cheok YanCheng <yccheok@yahoo.com>
 */

package org.yccheok.jstock.portfolio;

import org.yccheok.jstock.gui.treetable.AbstractTreeTableable;
import org.yccheok.jstock.gui.treetable.TreeTableable;
import org.yccheok.jstock.engine.*;

/**
 *
 * @author Owner
 */
public class Transaction extends AbstractTreeTableable {
    public Transaction(Contract contract, Broker broker, StampDuty stampDuty, ClearingFee clearingFee)
    {
        this.contract = contract;
        this.broker = broker;
        this.stampDuty = stampDuty;
        this.clearingFee = clearingFee;
        
        this.calculatedBroker = broker.calculate(contract);
        this.calculatedStampDuty = stampDuty.calculate(contract);
        this.calculatdClearingFee = clearingFee.calculate(contract);
     
        if(contract.getType() == Contract.Type.Buy)
            netTotal = this.contract.getTotal() + this.calculatedBroker + this.calculatedStampDuty + this.calculatdClearingFee;
        else
            netTotal = this.contract.getTotal() - this.calculatedBroker - this.calculatedStampDuty - this.calculatdClearingFee;            
    }
    
    public void setTransactionSummary(TransactionSummary transactionSummary) {
        this.transactionSummary = transactionSummary;        
    }
    
    public TransactionSummary getTransactionSummary() {
        return this.transactionSummary;
    }
    
    private TransactionSummary transactionSummary;
    
    private Contract contract;
    private Broker broker;
    private StampDuty stampDuty;
    private ClearingFee clearingFee;
    
    private double calculatedBroker;
    private double calculatedStampDuty;
    private double calculatdClearingFee;
    private double netTotal;
    
    public Contract getContract() {
        return contract;
    }

    public Broker getBroker() {
        return broker;
    }

    public StampDuty getStampDuty() {
        return stampDuty;
    }

    public ClearingFee getClearingFee() {
        return clearingFee;
    }

    public double getCalculatedBroker() {
        return calculatedBroker;
    }

    public double getCalculatedStampDuty() {
        return calculatedStampDuty;
    }

    public double getCalculatdClearingFee() {
        return calculatdClearingFee;
    }
    
    public double getTotal() {
        return contract.getTotal();
    }
    
    public int getQuantity() {
        return contract.getQuantity();
    }
    
    public SimpleDate getDate() {
        return contract.getDate();
    }
    
    public double getNetTotal() {
        return netTotal;
    }

    public TreeTableable getParent() {
        return this.getTransactionSummary();
    }

    public int getSize() {
        return 0;
    }

    public TreeTableable getChild(int index) {
        return null;
    }
    
    public String toString() {
        return contract.getStock().getSymbol();
    }
}
