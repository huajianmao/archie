package com.nedap.archie.rminfo;

import com.nedap.archie.aom.Archetype;
import com.nedap.archie.aom.CObject;
import com.nedap.archie.aom.CPrimitiveObject;
import com.nedap.archie.aom.primitives.*;

import com.nedap.archie.base.Interval;
import com.nedap.archie.base.terminology.TerminologyCode;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.*;
import com.nedap.archie.rm.composition.*;
import com.nedap.archie.rm.changecontrol.*;

import com.nedap.archie.rm.datastructures.*;
import com.nedap.archie.rm.datatypes.*;
import com.nedap.archie.rm.datavalues.encapsulated.*;
import com.nedap.archie.rm.datavalues.quantity.datetime.*;
import com.nedap.archie.rm.datavalues.quantity.*;
import com.nedap.archie.rm.datavalues.*;
import com.nedap.archie.rm.*;
import com.nedap.archie.rm.datavalues.timespecification.*;
import com.nedap.archie.rm.demographic.*;
import com.nedap.archie.rm.directory.*;
import com.nedap.archie.rm.generic.*;
import com.nedap.archie.rm.ehr.*;
import com.nedap.archie.rm.integration.*;
import com.nedap.archie.rm.security.*;
import com.nedap.archie.rm.support.identification.*;
import com.nedap.archie.rm.support.*;

import java.lang.reflect.Method;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by pieter.bos on 02/02/16.
 */
public class ArchieRMInfoLookup extends ReflectionModelInfoLookup {

    private static ArchieRMInfoLookup instance;

    private ArchieRMInfoLookup() {
        super(new ArchieModelNamingStrategy(), RMObject.class);
    }

    @Override
    protected void addTypes(Class baseClass) {
        addClass(Interval.class); //extra class from the base package. No RMObject because it is also used in the AOM
        addClass(AuditDetails.class);
        addClass(Ehr.class);
        addClass(DvTime.class);
        addClass(RevisionHistoryItem.class);
        addClass(PartyIdentity.class);
        addClass(DvParsable.class);
        addClass(DvDuration.class);
        addClass(DvDateTime.class);
        addClass(DvCount.class);
        addClass(Cluster.class);
        addClass(IsoOID.class);
        addClass(PartyRelated.class);
        addClass(Instruction.class);
        addClass(Person.class);
        addClass(GenericId.class);
        addClass(Evaluation.class);
        addClass(DvAmount.class);
        addClass(Capability.class);
        addClass(UID.class);
        addClass(Item.class);
        addClass(Contribution.class);
        addClass(OriginalVersion.class);
        addClass(FeederAuditDetails.class);
        addClass(PartyProxy.class);
        addClass(PointEvent.class);
        addClass(CodePhrase.class);
        addClass(InstructionDetails.class);
        addClass(DvTimeSpecification.class);
        addClass(DvAbsoluteQuantity.class);
        addClass(FeederAudit.class);
        addClass(Party.class);
        addClass(ItemSingle.class);
        addClass(EventContext.class);
        addClass(DvProportion.class);
        addClass(DvQuantity.class);
        addClass(DvOrdered.class);
        addClass(ContentItem.class);
        addClass(DataValue.class);
        addClass(DvOrdinal.class);
        addClass(Agent.class);
        addClass(InternetId.class);
        addClass(Role.class);
        addClass(Group.class);
        addClass(ObjectId.class);
        addClass(UIDBasedId.class);
        addClass(VersionedEhrStatus.class);
        addClass(PartySelf.class);
        addClass(DvMultimedia.class);
        addClass(Actor.class);
        addClass(VersionTreeId.class);
        addClass(DvParagraph.class);
        addClass(ReferenceRange.class);
        addClass(CareEntry.class);
        addClass(ItemTree.class);
        addClass(Element.class);
        addClass(DvGeneralTimeSpecification.class);
        addClass(DvDate.class);
        addClass(Version.class);
        addClass(DvState.class);
        addClass(AccessControlSettings.class);
        addClass(ItemList.class);
        addClass(DataStructure.class);
        addClass(History.class);
        addClass(DvPeriodicTimeSpecification.class);
        addClass(Contact.class);
        addClass(TermMapping.class);
        addClass(Event.class);
        addClass(Observation.class);
        addClass(Locatable.class);
        addClass(UUID.class);
        addClass(DvTemporal.class);
        addClass(IsmTransition.class);
        addClass(Folder.class);
        addClass(Participation.class);
        addClass(VersionedComposition.class);
        addClass(ObjectVersionId.class);
        addClass(Entry.class);
        addClass(DvInterval.class);
        addClass(Organisation.class);
        addClass(VersionedObject.class);
        addClass(DvEncapsulated.class);
        addClass(VersionedFolder.class);
        addClass(IntervalEvent.class);
        addClass(ItemTable.class);
        addClass(Attestation.class);
        addClass(Address.class);
        addClass(RevisionHistory.class);
        addClass(DvIdentifier.class);
        addClass(DvCodedText.class);
        addClass(PartyRelationship.class);
        addClass(LocatableRef.class);
        addClass(Pathable.class);
        addClass(EhrAccess.class);
        addClass(DvEHRURI.class);
        addClass(ArchetypeID.class);
        addClass(RMObject.class);
        addClass(PartyRef.class);
        addClass(TemplateId.class);
        addClass(AdminEntry.class);
        addClass(VersionedEhrAccess.class);
        addClass(PartyIdentified.class);
        addClass(Composition.class);
        addClass(EhrStatus.class);
        addClass(AccessGroupRef.class);
        addClass(ObjectRef.class);
        addClass(GenericEntry.class);
        addClass(DvQuantified.class);
        addClass(ImportedVersion.class);
        addClass(DvBoolean.class);
        addClass(DvURI.class);
        addClass(DvText.class);
        addClass(Action.class);
        addClass(ItemStructure.class);
        addClass(HierObjectId.class);
        addClass(Section.class);
        addClass(Activity.class);
        addClass(TerminologyId.class);
    }

    public static ArchieRMInfoLookup getInstance() {
        if(instance == null) {
            instance = new ArchieRMInfoLookup();
        }
        return instance;
    }

    @Override
    public Class getClassToBeCreated(String rmTypename) {
        if(rmTypename.equals("EVENT")) {
            //this is an abstract class and cannot be created. Create point event instead
            return PointEvent.class;
        }
        return getClass(rmTypename);
    }

    @Override
    public Object convertToConstraintObject(Object object, CPrimitiveObject cPrimitiveObject) {
        if(cPrimitiveObject instanceof CTerminologyCode) {
            if(object instanceof DvCodedText) {
                return convertCodePhrase(((DvCodedText) object).getDefiningCode());
            } else if (object instanceof CodePhrase) {
                return convertCodePhrase((CodePhrase) object);
            }
        }
        return object;
    }

    private TerminologyCode convertCodePhrase(CodePhrase codePhrase) {
        TerminologyCode result = new TerminologyCode();
        result.setCodeString(codePhrase.getCodeString());
        result.setTerminologyId(codePhrase.getTerminologyId() == null ? null : codePhrase.getTerminologyId().getValue());
        return result;
    }

    public Object convertConstrainedPrimitiveToRMObject(Object object) {
        if(object instanceof TerminologyCode) {
            return convertTerminologyCode((TerminologyCode) object);
        }
        return object;
    }

    @Override
    protected boolean shouldAdd(Method method) {
        if(method.getName().equals("getPathSegments")) {
            return false;
        }
        return super.shouldAdd(method);
    }

    private CodePhrase convertTerminologyCode(TerminologyCode terminologyCode) {
        CodePhrase result = new CodePhrase();
        result.setCodeString(terminologyCode.getCodeString());
        result.setTerminologyId(terminologyCode == null ? null : new TerminologyId(terminologyCode.getTerminologyId()));
        return result;
    }

    @Override
    public void processCreatedObject(Object createdObject, CObject constraint) {
        if (createdObject instanceof Locatable) { //and most often, it will be
            Locatable locatable = (Locatable) createdObject;
            locatable.setArchetypeNodeId(constraint.getNodeId());
            locatable.setNameAsString(constraint.getMeaning());
        }
    }

    @Override
    public String getArchetypeNodeIdFromRMObject(Object rmObject) {
        if(rmObject == null) {
            return null;
        }
        if(rmObject instanceof Locatable) {
            Locatable locatable = (Locatable) rmObject;
            return locatable.getArchetypeNodeId();
        }
        return null;
    }

    @Override
    public String getNameFromRMObject(Object rmObject) {
        if(rmObject == null) {
            return null;
        }
        if(rmObject instanceof Locatable) {
            Locatable locatable = (Locatable) rmObject;
            return locatable.getNameAsString();
        }
        return null;
    }

    @Override
    public Object clone(Object rmObject) {
        if(rmObject instanceof RMObject) {
            return ((RMObject) rmObject).clone();
        }
        throw new IllegalArgumentException("The ArchieRMInfoLookup can only clone openehr reference model objects");
    }

    /**
     * Notification that a value at a given path has been updated in the given archetype. Perform tasks here to make sure
     * every other paths are updated as well.
     * @param rmObject
     * @param archetype
     * @param pathOfParent
     * @param parent
     */
    @Override
    public Map<String, Object> pathHasBeenUpdated(Object rmObject, Archetype archetype, String pathOfParent, Object parent) {
        return UpdatedValueHandler.pathHasBeenUpdated(rmObject, archetype, pathOfParent, parent);
    }

    @Override
    public boolean validatePrimitiveType(String rmTypeName, String rmAttributeName, CPrimitiveObject cObject) {
        RMAttributeInfo attributeInfo = this.getAttributeInfo(rmTypeName, rmAttributeName);
        if(attributeInfo == null) {
            return true;//cannot validate
        }
        Class typeInCollection = attributeInfo.getTypeInCollection();
        if(cObject instanceof CInteger) {
            return typeInCollection.equals(Long.class) || typeInCollection.getName().equals("long");
        } else if(cObject instanceof CReal) {
            return typeInCollection.equals(Double.class) || typeInCollection.getName().equals("double");
        } else if(cObject instanceof CString) {
            return typeInCollection.equals(String.class);
        } else if(cObject instanceof CDate) {
            return typeInCollection.equals(String.class) ||
                    typeInCollection.isAssignableFrom(Temporal.class);
        } else if(cObject instanceof CDateTime) {
            return typeInCollection.equals(String.class) ||
                    typeInCollection.isAssignableFrom(Temporal.class);
        } else if(cObject instanceof CDuration) {
            return typeInCollection.equals(String.class) ||
                    typeInCollection.isAssignableFrom(TemporalAccessor.class) ||
                    typeInCollection.isAssignableFrom(TemporalAmount.class);
        } else if(cObject instanceof CTime) {
            return typeInCollection.equals(String.class) ||
                    typeInCollection.isAssignableFrom(TemporalAccessor.class);
        } else if(cObject instanceof CTerminologyCode) {
            return typeInCollection.equals(CodePhrase.class) ||
                    typeInCollection.equals(DvCodedText.class);
        } else if(cObject instanceof CBoolean) {
            return typeInCollection.equals(Boolean.class) || typeInCollection.getName().equals("boolean");
        }
        return false;

    }

    @Override
    public Collection<RMPackageId> getId() {
        List<RMPackageId> result = new ArrayList<>();
        result.add(new RMPackageId("openEHR", "EHR"));
        result.add(new RMPackageId("openEHR", "DEMOGRAPHIC"));
        return result;
    }

}

