package org.eclipse.emf.ecore.xcore.scoping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.util.EcoreXcoreBuilder;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.AbstractEObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScope;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.util.Strings;

import com.google.common.base.Predicate;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class XcoreImportedNamespaceAwareScopeProvider extends ImportedNamespaceAwareLocalScopeProvider
{
  private static final URI ECORE_XCORE_URI = URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.xcore");
  private static final URI ECORE_GEN_MODEL_URI = 
  		EMFPlugin.IS_ECLIPSE_RUNNING ?
  			URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.genmodel"):
  			URI.createURI(EcorePlugin.INSTANCE.getBaseURL().toString() + "model/Ecore.genmodel");
  
  @Inject
  private IQualifiedNameConverter nameConverter;
  
  @Inject
  Provider<EcoreXcoreBuilder> ecoreXcoreBuilderProvider;

  @Override
  protected List<ImportNormalizer> internalGetImportedNamespaceResolvers(EObject context, boolean ignoreCase)
  {
    if (!(context instanceof XPackage))
      return Collections.emptyList();
    List<ImportNormalizer> importedNamespaceResolvers = super.internalGetImportedNamespaceResolvers(context, ignoreCase);
    String name = ((XPackage)context).getName();
    if (!Strings.isEmpty(name)) 
    {
      importedNamespaceResolvers.add(new ImportNormalizer(nameConverter.toQualifiedName(name), true, ignoreCase));
    }
    return importedNamespaceResolvers;
  }
  @Override
  protected boolean isRelativeImport()
  {
    return false;
  }
  
  @Override
  protected List<ImportNormalizer> getImplicitImports(boolean ignoreCase)
  {
   return Collections.singletonList(new ImportNormalizer(nameConverter.toQualifiedName("java.lang"), true, ignoreCase));
  }
  
  protected static final EDataType [] IMPLICIT_ALIASES = 
    new EDataType[] 
    {
      EcorePackage.Literals.EBIG_DECIMAL,
      EcorePackage.Literals.EBIG_INTEGER,
      EcorePackage.Literals.EBOOLEAN,
      EcorePackage.Literals.EBOOLEAN_OBJECT,
      EcorePackage.Literals.EBYTE,
      EcorePackage.Literals.EBYTE_OBJECT,
      EcorePackage.Literals.ECHAR,
      EcorePackage.Literals.ECHARACTER_OBJECT,
      EcorePackage.Literals.EDATE,
      EcorePackage.Literals.EDOUBLE,
      EcorePackage.Literals.EDOUBLE_OBJECT,
      EcorePackage.Literals.EFLOAT,
      EcorePackage.Literals.EFLOAT_OBJECT,
      EcorePackage.Literals.EINT,
      EcorePackage.Literals.EINTEGER_OBJECT,
      EcorePackage.Literals.EJAVA_CLASS,
      EcorePackage.Literals.EJAVA_OBJECT,
      EcorePackage.Literals.ELONG,
      EcorePackage.Literals.ELONG_OBJECT,
      EcorePackage.Literals.ESHORT,
      EcorePackage.Literals.ESHORT_OBJECT,
      EcorePackage.Literals.ESTRING,
    };

  @Override
  protected IScope getGlobalScope(final Resource context, EReference reference, Predicate<IEObjectDescription> filter)
  {
    final IScope globalScope = super.getGlobalScope(context, reference, filter);
    if (reference == XcorePackage.Literals.XGENERIC_TYPE__TYPE)
    {
      return
        new AbstractScope(globalScope, false)
        {
          @Override
          protected Iterable<IEObjectDescription> getAllLocalElements()
          {
            ArrayList<IEObjectDescription> result = new ArrayList<IEObjectDescription>();
            for (final EDataType eDataType : IMPLICIT_ALIASES)
            {
              String instanceClassName = eDataType.getInstanceClassName();
              final QualifiedName actualQualifiedName = QualifiedName.create("org", "eclipse", "emf", "ecore", eDataType.getName());
              final QualifiedName qualifiedName = nameConverter.toQualifiedName(instanceClassName);
              result.add
                (new AbstractEObjectDescription()
                 {
                   public QualifiedName getQualifiedName()
                   {
                     return qualifiedName;
                   }
                     
                   public QualifiedName getName()
                   {
                     return qualifiedName;
                   }
                     
                   public URI getEObjectURI()
                   {
                  	 IEObjectDescription element = getElement();
                  	 return element == null ? getSyntheticEObjectURI() : element.getEObjectURI();
                   }
                     
                   public EObject getEObjectOrProxy()
                   {
                  	 IEObjectDescription element = getElement();
                  	 if (element == null)
                  	 {
                       InternalEObject genDataType = (InternalEObject)GenModelFactory.eINSTANCE.createGenDataType();
                       genDataType.eSetProxyURI(getSyntheticEObjectURI());
                       return genDataType;
                  		 
                  	 }
                  	 else
                  	 {
                  		 return element.getEObjectOrProxy();
                  	 }
                   }
                     
                   public EClass getEClass()
                   {
                     return GenModelPackage.Literals.GEN_DATA_TYPE;
                   }
                   
                   protected URI getSyntheticEObjectURI()
                   {
                  	 return ECORE_XCORE_URI.appendFragment("/1/ecore/" + eDataType.getName());
                   }

                   protected IEObjectDescription getElement()
                   {
                  	 IEObjectDescription element = globalScope.getSingleElement(actualQualifiedName);
                  	 if (element == null)
                  	 {
                 	     ResourceSet resourceSet = context.getResourceSet();
					             Resource ecoreXcoreResource = resourceSet.getResource(ECORE_XCORE_URI, false);
					             if (ecoreXcoreResource == null)
					             {
					               Resource genModelResource = resourceSet.getResource(ECORE_GEN_MODEL_URI, true);
                 	       GenModel genModel = (GenModel)genModelResource.getContents().get(0);
					               ecoreXcoreResource = resourceSet.getResource(ECORE_XCORE_URI, false);
					               EPackage ePackage = genModel.getGenPackages().get(0).getEcorePackage();
					               Resource ecoreResource = ePackage.eResource();
					               EcoreXcoreBuilder ecoreXcoreBuilder = ecoreXcoreBuilderProvider.get();
					               ecoreXcoreBuilder.initialize(genModel);
					               XPackage xPackage = ecoreXcoreBuilder.getXPackage(ePackage);
					               ecoreXcoreResource = resourceSet.createResource(ECORE_XCORE_URI);
					               ecoreXcoreResource.getContents().add(xPackage);
					               ecoreXcoreResource.getContents().add(genModel);
					               ecoreXcoreResource.getContents().add(ePackage);
					               ecoreXcoreBuilder.link();
					               resourceSet.getResources().remove(genModelResource);
					               resourceSet.getResources().remove(ecoreResource);
					             }
                  	 }
                  	 return element;
                   }
                 });
             }
             return result;
           }
        };
    }
    else
    {
     return globalScope;
    }
  
  }
}
