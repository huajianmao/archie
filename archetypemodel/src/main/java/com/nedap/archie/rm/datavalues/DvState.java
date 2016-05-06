package com.nedap.archie.rm.datavalues;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by pieter.bos on 04/11/15.
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "DV_STATE", propOrder = {
        "value",
        "terminal"
})
public class DvState extends DataValue implements SingleValuedDataValue<DvCodedText> {

    private boolean terminal;
    private DvCodedText value;

    @XmlElement(name = "is_terminal")
    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public DvCodedText getValue() {
        return value;
    }

    public void setValue(DvCodedText value) {
        this.value = value;
    }
}
