package org.eclipse.emf.ecore.xcore.util

import com.google.inject.Inject
import java.util.Collections
import java.util.HashSet
import org.eclipse.emf.codegen.ecore.genmodel.GenClass
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.xcore.XClass
import org.eclipse.emf.ecore.xcore.XDataType
import org.eclipse.emf.ecore.xcore.XOperation
import org.eclipse.emf.ecore.xcore.XPackage
import org.eclipse.emf.ecore.xcore.XStructuralFeature
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper

import static extension org.eclipse.xtext.xtend2.lib.EObjectExtensions.*

class XcoreGenmodelBuilder {
	
	@Inject extension XcoreMapper mapper
	
	def GenModel getGenModel(XPackage pack) {
		val ePackage = pack.mapping.getEPackage
		val genModel =  GenModelFactory::eINSTANCE.createGenModel();
      	genModel.initialize(Collections::singleton(ePackage));
      	pack.eResource.getContents().add(1, genModel);
      	genModel.initialize();
      	buildMap(genModel);
      	return genModel
   	}
     
	def buildMap(GenModel genModel) {
       for (genElement : genModel.allContentsIterable)
       {
       	switch genElement {
       		GenPackage : 
       		{
       			val xPack = genElement.ecorePackage.toXcoreMapping.xcoreElement as XPackage
 				xPack.mapping.genPackage = genElement
 				genElement.toXcoreMapping.xcoreElement = xPack    			
       		}
       		GenClass :
       		{
       			val xClass = genElement.ecoreClass.toXcoreMapping.xcoreElement as XClass
 				xClass.mapping.genClass = genElement
 				genElement.toXcoreMapping.xcoreElement = xClass    			
       		}
       		GenDataType :
       		{
       			val xDataType = genElement.ecoreDataType.toXcoreMapping.xcoreElement as XDataType
 				xDataType.mapping.genDataType = genElement
 				genElement.toXcoreMapping.xcoreElement = xDataType    			
       		}
       		GenFeature :
       		{
       			val xFeature = genElement.ecoreFeature.toXcoreMapping.xcoreElement as XStructuralFeature
 				xFeature.mapping.genFeature = genElement
 				genElement.toXcoreMapping.xcoreElement = xFeature    			
       		}
       		GenOperation :
       		{
       			val xOperation = genElement.ecoreOperation.toXcoreMapping.xcoreElement as XOperation
 				xOperation.mapping.genOperation = genElement
 				genElement.toXcoreMapping.xcoreElement = xOperation    			
       		}
       	}
       }
	}
	
	def initializeUsedGenPackages(GenModel genModel) {
      	val referencedEPackages = new HashSet<EPackage>();
      	for (genPackage : genModel.genPackages)
      	{
      		for (eObject : genPackage.ecorePackage.allContentsIterable)
      		{
      			for (eCrossReference : eObject.eCrossReferences)
        		{
        			switch eCrossReference
        			{
        				EClassifier :
        				{
        					referencedEPackages.add(eCrossReference.getEPackage);
        				}
        				EStructuralFeature :
        				{
        					referencedEPackages.add(eCrossReference.getEContainingClass().getEPackage);
        				}
        			}
        		}
      		}
     	}
     	for (referencedEPackage : referencedEPackages)
     	{
     	  if (genModel.findGenPackage(referencedEPackage) == null)
     	  {
     	  	var usedGenPackage = mapper.getGen(mapper.getToXcoreMapping(referencedEPackage).xcoreElement) as GenPackage
     	  	if (usedGenPackage != null)
     	  	{
     	  	  genModel.usedGenPackages.add(usedGenPackage);
     	  	}
     	  	else
     	  	{
     	  		for (resource : genModel.eResource.resourceSet.resources)
     	  		{
     	  			if ("genmodel".equals(resource.URI.fileExtension))
     	  			{
     	  				usedGenPackage = (resource.contents.get(0) as GenModel).findGenPackage(referencedEPackage);
     	  				if (usedGenPackage != null)
     	  				{
     					  	 genModel.usedGenPackages.add(usedGenPackage);
     					  	 // TODO break
     	  				}
     	  			}
     	  		}
     	  	}
     	  }
     	}
	}
}